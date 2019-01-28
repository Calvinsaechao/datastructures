1. Calvin Saechao (1 person)
2. 2 weeks
3. avltree
4. It ended up the binary search tree was the fastest and Hash tables came up second. I thought that
when the tree is balanced, it is faster to insert and search but probably given the the set of data,
it wasn't enough to make an impact.
5. I found that hash table was easier to implement and easier to debug but it took more space; but since
BST was the fastest, it would say it is objectively better for this specific scenario.
6. All the data structures worked the same in the correlator in terms of reaching to the same conclusion.
7. I don't think Bacon wrote Shakespeare's plays based on my data since when looking at the word
counts using my program, Keyword.java (finds the difference between the same words in each book),
for Hamlet and The New Atlantis, there are certain unique words that I found is more stylistic to use
than based on the context of the book. Those words I found were: thou, for, ye, your, shall, may,
how, must, hath, thee.
8. I am measuring the nanotime using System.nanoTime() then subtracting the endTime with the startTime
to get a total runtime for each data structure. Based on my measurement, the better data structure
would be the one with the lowest runtime. This measurement shows how efficient the data structure is
in performing the given job. Some sources of error may be that I am not only measuring the insertion
and searching of each algorithm but also adding on the runtime of creating an array of DataCount; but
in the end it doesn't matter since the runtime for creating an array of DataCount is a constant that
is added on to each test. I concluded that BST is the best for the amount of data we are using.
I concluded this by looking at the shortest runtime given by each data structure using my program,
Benchmark.java.
9. I enjoyed implementing each data structure and figuring out how the non-given data structures would
work with the algorithms given. I didn't like doing the statistical anyalsis.

How to run each program:
	1. Compile the .java files into .class files
	2. Go to command line and change directory to the .class files
	3. Type one of the following:

		java WordCount [ -b | -a | -h ] [ -frequency | -num_unique ] <filename>
		java Correlator [ -b | -a | -h ] <filename1> <filename2>
		java Keywords <filename1> <filename2>
		java Benchmark <filename>

Note: Since each file is in the texts folder, the file name should be <texts/filename>
	EX: java Benchmark texts/hamlet.txt