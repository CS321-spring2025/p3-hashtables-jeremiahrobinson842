# Project 3: Experiments with Hashing

* Author: Jeremiah Robinson 
* Class: CS321 Section 2
* Semester: Spring 2025

## Overview

This program focuses on building a Hashtable using open addressing to study how different load factors impact the efficiency of linear probing and double hashing. The aim is to test different hashing techniques and evaluate performance by inserting objects until a specified load factor is reached.

## Reflection

There were several challenging parts of this project and required me to "nuke" a couple classes entirely to get it all to work. I think the hardest part was getting my insert method to work initially. As time went on, I still had the same issue of the program not reading every word and skipping the placement in the hash table or incorrectly counting the duplicates. The issue was in the implementation of reading the files.

This project really tested my patience at certain points, but I did learn how vital it is to test each method by including debug statements and working on issues prior to integrating them with the rest of the program. I also learned to include a main method in any class that is able to run on its own to be able to debug any issues as well.

## Compiling and Using

Compile with "javac HashtableExperiment.java"
Run with "java HashtableExperiment <dataSource> <loadFactor> [<debugLevel>]"
        where:
       <dataSource>: 1 ==> random numbers
                     2 ==> date value as a long
                     3 ==> word list
       <loadFactor>: The ratio of objects to table size, 
                       denoted by alpha = n/m
       <debugLevel>: 0 ==> print summary of experiment
                     1 ==> save the two hash tables to a file at the end
                     2 ==> print debugging output for each insert


## Results 

This program passes all of the tests found within the file "run-tests.sh"

## Sources used

Java util.scanner - https://www.w3schools.com/java/java_user_input.asp

Java printwriter - https://www.geeksforgeeks.org/java-io-printwriter-class-java-set-1/

