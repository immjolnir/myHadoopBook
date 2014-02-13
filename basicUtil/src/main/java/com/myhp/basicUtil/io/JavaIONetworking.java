package com.myhp.basicUtil.io;

/**
 * Created on 2/13/14.
 * <p/>
 * http://tutorials.jenkov.com/java-io/networking.html
 * The details of networking in Java is somewhat out of the scope of this Java IO tutorial. Java Networking will described in more detail in its own tutorial, in the future. But, since network connections are a common source or destination of data, and because you use the Java IO API to communicate over a network connection, this text will briefly touch upon Java networking.
 * Once a network connection is established between two processes, the processes communicates via the network connection just like they would with a file: Using an InputStream to read data, and an OutputStream to write data. In other words, Java IO is being used to pass the data to send to the Java networking API.
 * Basically this means that if you have code that is capable of writing something to a file, that same something could easily be written to a network connection. All that is required is that your component doing the writing depends on an InputStream instead of a FileInputStream. Since FileInputStream is a subclass of InputStream this should be no problem.
 * The same is true for reading from a file, actually. A component capable of reading some data from a file, can easily be made read that same data from a network connection. Just make sure you reading component depends on an InputStream for reading it's data, and not a FileInputStream.
 */
public class JavaIONetworking {
}
