package com.myhp.basicUtil.io;

/**
 * Created on 2/13/14.
 * <p/>
 * Link: http://tutorials.jenkov.com/java-io/pipes.html
 * Pipes in Java IO provides the ability for two threads running in the same JVM to communicate. As such pipes are a common source or destination of data.
 * The pipe concept in Java is thus a bit different from the pipe concept in Unix / Linux, where two processes running in different address spaces can communicate via a pipe. In Java, the communicating parties must be running in the same process (same virtual machine), and should be different threads.
 * <p/>
 * Creating Pipes via Java IO
 * Creating a pipe using Java IO is done via the PipedOutputStream and PipedInputStream classes. A PipedInputStream should be connected to a PipedOutputStream. The data written to the PipedOutputStream by one thread, can thus be read from the connected PipedInputStream by another thread.
 * <p/>
 * Pipe Example using Java IO
 * Here is a simple example of how to connect a PipedInputStream to a PipedOutputStream:
 * PipedOutputStream output = new PipedOutputStream();
 * PipedInputStream input = new PipedInputStream(output);
 * You can also connect the two pipe streams using their connect() methods. Both PipedInputStream and PipedOutputStream has a connect() method that can connect one to the other.
 * <p/>
 * Pipes and Threads
 * Remember, when using the two connected pipe streams, pass one stream to one thread, and the other stream to another thread. The read() and write() calls on the streams are blocking, meaning if you try to use the same thread to both read and write, this may result in the thread deadlocking itself.
 */
public class JavaIOPipes {
}
