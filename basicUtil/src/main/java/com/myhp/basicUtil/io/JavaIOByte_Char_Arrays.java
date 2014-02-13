package com.myhp.basicUtil.io;

/**
 * Created on 2/13/14.
 * http://tutorials.jenkov.com/java-io/arrays.html
 * <p/>
 * Byte and char arrays are often used in Java to temporarily store data internally in an application. As such arrays are also a common source or destination of data. You may also prefer to load a file into an array, if you need to access the contents of that file a lot while the program is running. Of course you can access these arrays directly by indexing into them. But what if you have a component that is designed to read some specific data from an InputStream or Reader and not an array?
 * <p/>
 * Reading Arrays via InputStream or Reader
 * To make such a component read from the data from an array, you will have to wrap the byte or char array in an ByteArrayInputStream or CharArrayReader. This way the bytes or chars available in the array can be read through the wrapping stream or reader.
 * Here is a simple example:
 * byte[] bytes = new byte[1024];
 * <p/>
 * //write data into byte array...
 * <p/>
 * InputStream input = new ByteArrayInputStream(bytes);
 * <p/>
 * //read data from InputStream.
 * To do the same with a char array is pretty analogous to this example. Just wrap the char array in a CharArrayReader and you are good to go.
 * <p/>
 * Writing to Arrays via OutputStream or Writer
 * It is also possible to write data to an ByteArrayOutputStream or CharArrayWriter. All you have to do is to create eiter a ByteArrayOutputStream or CharArrayWriter, and write your data to it, as you would to any other stream or writer. Once all the data is written to it, simply call the method toByteArray() or toCharArray, and all the data written is returned in array form.
 * Here is a simple example:
 * OutputStream output = new ByteArrayOutputStream();
 * <p/>
 * //write data to output stream...
 * <p/>
 * byte[] bytes = output.toByteArray();
 * To do the same with a char array is pretty analogous to this example. Just wrap the char array in a CharArrayWriter and you are good to go.
 */
public class JavaIOByte_Char_Arrays {
}
