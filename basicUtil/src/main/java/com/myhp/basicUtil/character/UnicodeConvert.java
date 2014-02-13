package com.myhp.basicUtil.character;

import org.junit.Assert;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created on 2/12/14.
 * Internally in java, all strings are kept in unicode. Since not all text received from users or the outside world is in unicode,
 * your application may have to convert from non-unicode to unicode.
 * Additionally, when the application outputs text is may have to convert the internal unicode format to whatever format the outside world needs.
 * <p/>
 * Java has a few different methods you can use to convert text to and from unicode. There methods are:
 * The String class
 * The Reader and Writer classes and subclasses.
 */
public class UnicodeConvert {
    final static String CHARSET_NAME = "UTF-8";

    /*
    * It first creates a byte array. the byte array does not actually contain any sensible data, but for the sake of the example,
    * that does not matter. It then creates a new String, passing the byte array and the character set of the characters in the byte
    * array as parameters to the constructor. The String constructer will then convert the bytes from the character set of the byte array to unicode.
    * */
    static void convertByBytes2String() {
        byte[] bytes = new byte[10];
        String str = new String(bytes, Charset.forName(CHARSET_NAME));
        System.out.println(str);
    }

    static byte[] convertString2Bytes(String string) {
        byte[] bytes = string.getBytes(Charset.forName(CHARSET_NAME));
        return bytes;
    }

    /*
    * http://tutorials.jenkov.com/java-internationalization/unicode.html
    * Converting to and from Unicode Using the {@code Reader} and {@code Writer} Classes
    * The Reader and Writer classes are stream oriented classes that enable a Java application to read and write streams of characters.
    * Both classes are explained in Java IO tutorial. Go to Reader or Writer to read more.
    * Here is an example that uses an InputStreamReader to convert from a certain character set (UTF-8) to unicode:
    * This example creates a FileInputStream and wraps it in a InputStreamReader. The InputStreamReader is told to interprete the characters in the file as UTF-8 characters.
     * This is done using the second constructor paramter in the InputStreamReader class.
    * */
    static void convertUsingReader() throws IOException, ClassNotFoundException{
        InputStream inputStream = new FileInputStream("/home/zhishan/workspace/git/myHadoopBook/basicUtil/src/main/java/resources/utf-8-text.txt");
        Reader reader = new InputStreamReader(inputStream,
                Charset.forName("UTF-8"));

        int data = reader.read();
        while (data != -1) {
            char theChar = (char) data;
            data = reader.read();
        }
        reader.close();
    }
/*
*
* Here is an example writing a stream of characters back out to UTF-8:
* This example creates an OutputStreamWriter which converts the string written through it to the UTF-8 character set.
* */

    static void convertUsingWriter() throws IOException {
        OutputStream outputStream = new FileOutputStream("c:\\data\\output.txt");
        Writer writer = new OutputStreamWriter(outputStream,
                Charset.forName("UTF-8")); //Creates an OutputStreamWriter that uses the given charset.

        writer.write("Hello World");

        writer.close();
    }

    public static void main(String[] args) throws Exception{
        convertByBytes2String();

        String string = "15";
        System.out.println(convertString2Bytes(string));
        Assert.assertArrayEquals(convertString2Bytes(string), new byte[]{49, 53});
        /*
        * ASCII stands for American Standard Code for Information Interchange.
        * in ASCII table
        * Char  Dec
        * 0 48
        * 1 49
        * A 65
        * Z 90
        * a 97
        * z 122
        * */

        convertUsingReader();
    }
}
