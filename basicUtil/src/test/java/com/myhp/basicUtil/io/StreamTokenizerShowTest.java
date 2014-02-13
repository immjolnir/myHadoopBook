package com.myhp.basicUtil.io;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * Created on 2/13/14.
 */
public class StreamTokenizerShowTest {
    @Test
    public void printStreamTokenizerTest()  throws IOException{
        Reader reader = new StringReader("Mary had 1 little lamb ...");
        StreamTokenizerShow.printStreamTokenizer(reader);
    }
}
