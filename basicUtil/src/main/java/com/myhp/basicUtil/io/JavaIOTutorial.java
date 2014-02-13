package com.myhp.basicUtil.io;

/**
 * Created on 2/13/14.
 * <p/>
 * baselink:http://tutorials.jenkov.com/java-io/index.html
 * http://tutorials.jenkov.com/java-howto/index.html
 * <p/>
 * 1. Input and Output - Source and Destination
 * In an attempt to clear out this possible confusion, I have tried to put some different names on input and output to try to link them conceptually to where the input comes from, and where the output goes.
 * Java's IO package mostly concerns itself with the reading of raw data from a source and writing of raw data to a destination. The most typical sources and destinations of data are these:
 * Files
 * Pipes
 * Network Connections
 * In-memory Buffers (e.g. arrays)
 * System.in, System.out, System.error
 * The diagram below illustrates the principle of a program reading data from a source and writing it to some destination:
 * source --> program --> destination
 * <p/>
 * A program that needs to read data from some source needs an input stream or Reader. A program that needs to write data to some destination needs an output stream or writer. This is also illustrated in the diagram below:
 * source --> inputStream/Reader --> Program
 * program --> outputStream/Writer --> Destination
 * <p/>
 * An InputStream or Reader is linked to a source of data. An OutputStream or Writer is linked to a destination of data.
 * 2. Java IO Purposes and Features
 * The Java IO classes, which mostly consists of streams and readers / writers, are addressing various purposes. That is why there are so many different classes. The purposes addressed are summarized below:
 * File Access
 * Network Access
 * Internal Memory Buffer Access
 * Inter-Thread Communication (Pipes)
 * Buffering
 * Filtering
 * Parsing
 * Reading and Writing Text (Readers / Writers)
 * Reading and Writing Primitive Data (long, int etc.)
 * Reading and Writing Objects
 * These purposes are nice to know about when reading through the Java IO classes.
 * They make it somewhat easier to understand what the classes are targeting.
 * <p/>
 * Java IO classes divided by input, output, being byte based or character based,
 * and any more specific purpose they may be addressing, like buffering, parsing etc.
 * such as:
 * Byte Based input: .*InputStream, output, .*OutputStream
 * Character Based input: .*Reader, output, .*Writer
 * <p/>
 *
 * Byte Based	Character Based
 * Input	Output	Input	Output
 * Basic	InputStream	OutputStream	Reader
 * InputStreamReader	Writer
 * OutputStreamWriter
 * Arrays	ByteArrayInputStream	ByteArrayOutputStream	CharArrayReader	CharArrayWriter
 * Files	FileInputStream
 * RandomAccessFile	FileOutputStream
 * RandomAccessFile	FileReader	FileWriter
 * Pipes	PipedInputStream	PipedOutputStream	PipedReader	PipedWriter
 * Buffering	BufferedInputStream	BufferedOutputStream	BufferedReader	BufferedWriter
 * Filtering	FilterInputStream	FilterOutputStream	FilterReader	FilterWriter
 * Parsing	PushbackInputStream
 * StreamTokenizer	 	PushbackReader
 * LineNumberReader
 * Strings	 	 	StringReader	StringWriter
 * Data	DataInputStream	DataOutputStream
 * Data - Formatted	 	PrintStream	 	PrintWriter
 * Objects	ObjectInputStream	ObjectOutputStream
 * Utilities	SequenceInputStream
 */
public class JavaIOTutorial {
}
