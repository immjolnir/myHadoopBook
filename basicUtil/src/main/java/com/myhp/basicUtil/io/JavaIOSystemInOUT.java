package com.myhp.basicUtil.io;

/**
 * Created on 2/13/14.
 * http://tutorials.jenkov.com/java-io/system-in-out-error.html
 *
 * The 3 streams System.in, System.out, and System.err are also common sources or destinations of data. Most commonly used is probably System.out for writing output to the console from console programs.
 * These 3 streams are initialized by the Java runtime when a JVM starts up, so you don't have to instantiate any streams yourself (although you can exchange them at runtime).
 * <p/>
 * System.in
 * System.in is an InputStream which is typically connected to keyboard input of console programs. System.in is not used as often since data is commonly passed to a command line Java application via command line arguments, or configuration files. In applications with GUI the input to the application is given via the GUI. This is a separate input mechanism from Java IO.
 * <p/>
 * System.out
 * System.out is a PrintStream. System.out normally outputs the data you write to it to the console. This is often used from console-only programs like command line tools. This is also often used to print debug statements of from a program (though it may arguably not be the best way to get debug info out of a program).
 * <p/>
 * System.err
 * System.err is a PrintStream. System.err works like System.out except it is normally only used to output error texts. Some programs (like Eclipse) will show the output to System.err in red text, to make it more obvious that it is error text.
 * <p/>
 * Simple System.out + System.err Example:
 * Here is a simple example that uses System.out and System.err:
 * try {
 * InputStream input = new FileInputStream("c:\\data\\...");
 * System.out.println("File opened...");
 * <p/>
 * } catch (IOException e){
 * System.err.println("File opening failed:");
 * e.printStackTrace();
 * }
 * <p/>
 * Exchanging System Streams
 * Even if the 3 System streams are static members of the java.lang.System class, and are pre-instantiated at JVM startup, you can change what streams to use for each of them. Just set a new InputStream for System.in or a new OutputStream for System.out or System.err, and all further data will be read / written to the new stream.
 * To set a new System stream, use one of th emethods System.setIn(), System.setOut() or System.setErr(). Here is a simple example:
 * OutputStream output = new FileOutputStream("c:\\data\\system.out.txt");
 * PrintStream printOut = new PrintStream(output);
 * <p/>
 * System.setOut(printOut);
 * Now all data written to System.out should be redirected into the file "c:\\data\\system.out.txt". Keep in mind though, that you should make sure to flush System.out and close the file before the JVM shuts down, to be sure that all data written to System.out is actually flushed to the file.
 */
public class JavaIOSystemInOUT {
}
