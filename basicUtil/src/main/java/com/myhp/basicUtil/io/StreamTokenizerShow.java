package com.myhp.basicUtil.io;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;

/**
 * Created on 2/13/14.
 */
public class StreamTokenizerShow {
    public static void main(String[] args) throws IOException{
        Reader reader = new StringReader("Mary had 1 little lamb ...");
        printStreamTokenizer(reader);
    }

    static void printStreamTokenizer(Reader reader) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(reader);

        while(tokenizer.nextToken() != StreamTokenizer.TT_EOF ){ //TT_EOF indicates that the end of the input stream has been reached.
            if(tokenizer.ttype == StreamTokenizer.TT_WORD) { // TT_WORD indicates that the token is a word.
                System.out.println(tokenizer.sval);
            } else if (tokenizer.ttype == StreamTokenizer.TT_NUMBER){ //TT_NUMBER indicates that the token is a number.
                System.out.println(tokenizer.nval);
            } else if (tokenizer.ttype == StreamTokenizer.TT_EOL) { //TT_EOL indicates that the end of line has been read. The field can only have this value if the eolIsSignificant method has been called with the argument true.
                System.out.println();
            }
        }
    }
}
