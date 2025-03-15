import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class HashtableExperiment {

    private static class TestArgs {
        int dataSource;
        double loadFactor;
        int debugLevel;
    }

    private interface TestDataGenerator<T> {
        T getNext();
        String getInputName();
        void reset();
        void close();
    }

    private static class RandomIntGenerator implements TestDataGenerator<Integer> {
        private Random random = new Random();
        @Override
        public Integer getNext() {
            return random.nextInt();
        }
        @Override
        public String getInputName() { return "Random-Numbers"; }
        @Override
        public void reset() {}
        @Override
        public void close() {}
    }

    private static class DateSequenceGenerator implements TestDataGenerator<Date> {
        private long current = new Date().getTime();
        @Override
        public Date getNext() {
            current += 1000;
            return new Date(current);
        }
        @Override
        public String getInputName() { return "Random-Dates"; }
        @Override
        public void reset() { current = new Date().getTime(); }
        @Override
        public void close() {}
    }

    private static class WordFileGenerator implements TestDataGenerator<String> {
        private Scanner scnr;
        public WordFileGenerator() {
            reset();
        }
        @Override
        public String getInputName() { return "Word-List"; }
        @Override
        public String getNext() {
            if (scnr == null) {
                throw new RuntimeException("Scanner is closed.");
            }
            if (scnr.hasNext()) {
                return scnr.next();
            } else {
                throw new RuntimeException("Generator exhausted.");
            }
        }
        @Override
        public void reset() {
            if (scnr != null) {
                scnr.close();
            }
            try {
                scnr = new Scanner(new File("word-list.txt"));
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
                throw new RuntimeException("Error: " + e.getMessage(), e);
            }
            scnr.useDelimiter(System.lineSeparator());
        }
        @Override
        public void close() {
            if (scnr != null) {
                scnr.close();
                scnr = null;
            }
        }
    }

    public static void main(String[] args) {
        TestArgs testArgs = new TestArgs();
        parseArgs(args, testArgs);

        int tableSize = TwinPrimeGenerator.generateTwinPrime(95500, 96000);
        System.out.println("HashtableExperiment: Found a twin prime table capacity: " + tableSize);

        Hashtable linearProbingHashTable = new LinearProbing(tableSize);
        Hashtable doubleHashingHashTable = new DoubleHashing(tableSize);

        TestDataGenerator<?> testDataGenerator = buildTestDataGenerator(testArgs);

        System.out.println("HashtableExperiment: Input: " + testDataGenerator.getInputName() + "\tLoadfactor: " + String.format("%.2f", testArgs.loadFactor));

        int numObjects = (int) Math.ceil(testArgs.loadFactor * tableSize);

        runTest("Linear Probing", linearProbingHashTable, numObjects, testDataGenerator, testArgs, "linear-dump.txt");
        testDataGenerator.reset();
        System.out.println();
        runTest("Double Hashing", doubleHashingHashTable, numObjects, testDataGenerator, testArgs, "double-dump.txt");
        testDataGenerator.close();
    }

    private static void parseArgs(String[] args, TestArgs testArgs) {
        if (args.length < 2 || args.length > 3) {
            exitWithError();
        }
        try {
            testArgs.dataSource = Integer.parseInt(args[0]);
            testArgs.loadFactor = Double.parseDouble(args[1]);
            testArgs.debugLevel = args.length == 3 ? Integer.parseInt(args[2]) : 0;
        } catch (NumberFormatException e) {
            exitWithError();
        }
        if ((testArgs.dataSource < 1 || testArgs.dataSource > 3) || 
            (testArgs.loadFactor < 0.0 || testArgs.loadFactor > 1.0) || 
            (testArgs.debugLevel < 0 || testArgs.debugLevel > 2)) {
            exitWithError();
        }
    }

    private static void printUsage() {
        System.out.println("Usage: java HashtableExperiment <dataType> <loadFactor> [<debugLevel>]");
        System.out.println("       <dataSource>: 1 ==> random numbers");
        System.out.println("                     2 ==> date value as a long");
        System.out.println("                     3 ==> word list");
        System.out.println("       <loadFactor>: The ratio of objects to table size, ");
        System.out.println("                       denoted by alpha = n/m");
        System.out.println("       <debugLevel>: 0 ==> print summary of experiment");
        System.out.println("                     1 ==> save the two hash tables to a file at the end");
        System.out.println("                     2 ==> print debugging output for each insert");
    }

    private static void exitWithError() {
        printUsage();
        System.exit(1);
    }

    private static TestDataGenerator<?> buildTestDataGenerator(TestArgs testArgs) {
        switch (testArgs.dataSource) {
            case 1:
                return new RandomIntGenerator();
            case 2:
                return new DateSequenceGenerator();
            case 3:
                return new WordFileGenerator();
            default:
                throw new RuntimeException("Invalid data source: " + testArgs.dataSource);
        }
    }

    private static void runTest(String using, Hashtable table, int numObjects, TestDataGenerator<?> testDataGenerator, TestArgs testArgs, String fileName) {
        System.out.println("\tUsing " + using);
        System.out.println("HashtableExperiment: size of hash table is " + numObjects);
        loadHashtable(table, numObjects, testDataGenerator, testArgs);
        int insertions = table.getInsertionCount();
        int duplicates = table.getDuplicateCount();
        System.out.println("\tInserted " + insertions + " elements, of which " + duplicates + " were duplicates");
        if (testArgs.debugLevel == 1) {
            try {
                table.dumpToFile(fileName);
                System.out.println("HashtableExperiment: Saved dump of hash table");
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
                throw new RuntimeException("Error: " + e.getMessage(), e);
            }
        }
    }

    private static void loadHashtable(Hashtable table, int numObjects, TestDataGenerator<?> testDataGenerator, TestArgs testArgs) {
        int insertedObjects = 0;
        while (insertedObjects < numObjects) {
            Object key = testDataGenerator.getNext();
            int idx = table.insert(key);
            if (idx != -1) {
                insertedObjects++;
            }
            if (testArgs.debugLevel == 2) {
                if (idx == -1) {
                    System.out.println("Duplicate: " + key);
                } else {
                    System.out.println("Inserted: " + key);
                }
            }
        }
    }
}