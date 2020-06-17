# ExternalSortingBinaryData
This project is an implementation of an external sorting algorithm for binary data.

# Input data
The input data needs to consist of 8N blocks of data, where a block is 8,192 bytes. Each block will contain a
series of records, where each record has 16 bytes. The first 8-byte field is a non-negative integer
value (long) for the record ID and the second 8-byte field is a double value for the key, which will
be used for sorting. Thus each block contains 512 records. 

# Implementation
This algorithm sorts the file in ascending order of their key values. The algorithm uses replacement selection to sort
sections of the file in working memory that is 8 blocks long. </br>
Initially, the program reads the first 8 blocks of the input file into memory and then uses replacement selection to create the longest possible run. As it is being created the run is ouput to the one block output buffer which is written to file when it becomes full. Therefore each run will contain a certain number of runs being atleast 8 blocks long where the data within the run will be sorted. </br>
The algorithm then utilizes a multi way merge to combine the runs into a single sorted file. This creates a sorted output for binary data. 

# Invocation
java Externalsort <record file name>
