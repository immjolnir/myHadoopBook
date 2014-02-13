package com.myhp.basicUtil.character;

import org.junit.Assert;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

/**
 * Created on 2/13/14.
 * {@code StringCharacterIterator} http://docs.oracle.com/javase/7/docs/api/java/text/StringCharacterIterator.html
 * StringCharacterIterator implements the CharacterIterator protocol for a String.
 * The StringCharacterIterator class iterates over the entire String.
 * <p/>
 * <p/>
 * {@code CharacterIterator}
 * Constant that is returned when the iterator has reached either the end
 * or the beginning of the text. The value is '\\uFFFF', the "not a
 * character" value which should not occur in any valid Unicode string.
 * <p/>
 * public static final char DONE = '\uFFFF';
 * <p/>
 * StringCharacterIterator.DONE is the same as CharacterIterator.DONE, they are IS-A.
 *
 * Only next() could change the iterator.
 * char next(), Increments the iterator's index by one and returns the character at the new index.
 * If the resulting index is greater or equal to getEndIndex(), the current index is reset to getEndIndex() and a value of DONE is returned.
 */
public class StringSplitWithCharacterIterator {

    final static String string = "public final class StringCharacterIterator implements CharacterIterator";

    public static void main(String[] args) {
        StringCharacterIterator characterIterator = new StringCharacterIterator(string);
        int start = characterIterator.getBeginIndex();
        Assert.assertEquals(characterIterator.getBeginIndex(), 0);
//        Assert.assertEquals(characterIterator.getEndIndex(), StringCharacterIterator.DONE);//java.lang.AssertionError: expected:<71> but was:<65535>
        printEachChar(characterIterator, string);
    }

    public static void printEachChar(CharacterIterator characterIterator, String source) {
        int start = characterIterator.getBeginIndex();
        int end = characterIterator.getEndIndex();
        int next;
        char ch;
        Assert.assertEquals(end, source.length());

//        for (ch = characterIterator.first(), next = characterIterator.getIndex(); start != end; start = next, ch = characterIterator.next(), next = characterIterator.getIndex()) {
//            Assert.assertEquals(ch, characterIterator.next());
//            Assert.assertEquals(ch, source.substring(start, next));
//        }
    }
}
