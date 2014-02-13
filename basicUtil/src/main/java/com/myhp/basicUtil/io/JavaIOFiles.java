package com.myhp.basicUtil.io;

/**
 * Created on 2/13/14.
 * link http://tutorials.jenkov.com/java-io/files.html
 * Files are a common source or destination of data in Java application.
 * Therefore this text will give you a brief overview of working with files in Java.
 * It is not the intention to explain every technique in detail here, but rather to provide you with enough knowledge to decide on a file access method. Separate pages will describe each of these methods or classes in more detail, including examples of their usage etc.
 * <p/>
 * <p/>
 * 1. Reading Files via Java IO
 *  If you need to read a file from one end to the other you can use a FileInputStream.
 *  If you need to jump around the file and read only parts of it from here and there, you can use a RandomAccessFile.
 * <p/>
 * 2. Writing File via Java IO
 * If you need to write a file from one end to the other you can use FileOutputStream.
 * If you need to skip around a file and write to it in various places, for instance appending to the end of the file, you can use a RandomAccessFile.
 * <p/>
 * 3. Random Access to Files via Java IO
 * You can get random access to files via Java IO. Random doesn't mean that you read or write from truly random places.
 * It just means that you can skip around the file and read from or write to it at the same time.
 * This makes it possible to write only parts of an existing file, to append to it, or delete from it.
 * <p/>
 * 4. File and Directory Info Access
 * Sometimes you may need access to information about a file rather than its content.
 * For instance, if you need to know the file size or the file attributes of a file. The same may be true for a directory.
 * For instance, you may want to get a list of all files in a given directory. Both file and directory information is available via the File class.
 */
public class JavaIOFiles {
}
