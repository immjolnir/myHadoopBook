package com.myhp.basicUtil.character;

import org.junit.Assert;

import java.text.BreakIterator;
import java.util.Locale;

/**
 * Created on 2/13/14.
 * http://tutorials.jenkov.com/java-internationalization/breakiterator.html
 * <p/>
 * {@code Locale} http://docs.oracle.com/javase/7/docs/api/java/util/Locale.html
 * A Locale object represents a specific geographical, political, or cultural region.
 * An operation that requires a Locale to perform its task is called locale-sensitive and uses the Locale to tailor information for the user.
 * For example, displaying a number is a locale-sensitive operation— the number
 * should be formatted according to the customs and conventions of the user's native country, region, or culture.
 * <p/>
 * <p/>
 * {@code BreakIterator} http://docs.oracle.com/javase/6/docs/api/java/text/BreakIterator.html
 * The BreakIterator class implements methods for finding the location of boundaries in text.
 * Instances of BreakIterator maintain a current position and scan over text returning the index of characters where boundaries occur.
 * Internally, BreakIterator scans text using a CharacterIterator, and is thus able to scan text held by
 * any object implementing that protocol.
 * <p/>
 * <p/>
 * A StringCharacterIterator is used to scan String objects passed to setText.
 * You use the factory methods provided by this class to create instances of various types of break iterators.
 * In particular, use getWordIterator, getLineIterator, getSentenceIterator, and getCharacterIterator to create BreakIterators that
 * perform word, line, sentence, and character boundary analysis respectively.
 * A single BreakIterator can work only on one unit (word, line, sentence, and so on).
 * You must use a different iterator for each unit boundary analysis you wish to perform.
 * getCharacterInstance()
 * getLineInstance()
 * getSentenceInstance()
 * getWordInstance()
 * <p/>
 * BreakIterator.DONE is distinct with CharacterIterator.DONE, because it not the sub-class of CharacterIterator.
 *
 * DONE is returned by previous(), next(), next(int), preceding(int)
 * and following(int) when either the first or last text boundary has been
 * reached.
 * public static final int DONE = -1;
 */
public class TextSplitWithBreakIterator {


    public static void main(String[] args) {
        //   Gets the current value of the default locale for this instance of the Java Virtual Machine.
        Assert.assertEquals(Locale.getDefault().toString(), "en_US");
        String text = "You must use a different iterator for each unit boundary analysis you wish to perform.";
        wordBreaker(text);

    }

    public static void wordBreaker(String text) {
        Locale locale = Locale.UK;
        BreakIterator breakIterator = BreakIterator.getWordInstance(locale);
        breakIterator.setText(text);
        printEachForward(breakIterator, text);

    }

    public static void printEachForward(BreakIterator boundary, String source) {
        int start = boundary.first();
        for (int end = boundary.next();
             end != BreakIterator.DONE;
             start = end, end = boundary.next()) {
            System.out.println(source.substring(start, end));
        }
    }
}
