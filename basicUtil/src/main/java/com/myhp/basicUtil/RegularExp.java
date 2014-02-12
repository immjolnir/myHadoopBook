package com.myhp.basicUtil;


import org.junit.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 2/12/14
 * Time: 7:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class RegularExp {
    static boolean PatternExample(String ipPattern, String input) {
        Pattern pattern = Pattern.compile(ipPattern);
        Matcher matcher = pattern.matcher(input);

        //System.out.println("Input String matches regex - " + matcher.matches());
        // bad regular expression
        // Exception in thread "main" java.util.regex.PatternSyntaxException: Dangling meta character '*' near index 0
        // *xx*
        // pattern = Pattern.compile("*xx*");
        return matcher.matches();
    }

    public static void main(String[] args) {
//        PatternExample(new String(".xx."), new String("MxxL"));  // true
//        new String(".xx.") is redundant.
//        Reports any attempt to instantiate a new String object by copying an existing string.
//        Constructing new String Objects in this way is rarely necessary, and may cause performance problems if done often enough.
//        correct it as below
        Assert.assertTrue(PatternExample(".xx.", "MxxL"));
        //PatternExample(new String(".xx."), new String("MAxxL")); //false
        Assert.assertFalse(PatternExample(".xx", "MAxxL"));

        String str = "bbb";
        //String class provide a matches method that does pattern matching. Internally it uses Pattern and Matcher classes to do the processing but obviously it reduces the code lines.
        Assert.assertTrue(str.matches(".bb"));
        Assert.assertTrue(Pattern.matches(".bb", str));

        Assert.assertTrue(PatternExample("\\.xx.", ".xxL"));
        Assert.assertTrue(PatternExample("\\.*xx.", "..xxL"));
        Assert.assertTrue(PatternExample("\\.*xx.", "xxL"));
        Assert.assertFalse(PatternExample("\\.+xx.", "xxL"));

        Assert.assertTrue(".".matches("^[.].*"));
        Assert.assertFalse("..A".matches("^[.]*"));
        Assert.assertFalse("A".matches("^[.]*"));
        Assert.assertTrue("..".matches("^[.]\\.*"));
        Assert.assertFalse(".A".matches("^[.]\\."));
        Assert.assertTrue(".A".matches("^[.].*"));

        System.out.println("Using String matches method: " + new String("hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input/.ab").matches(".*/[_.].*$")); //true
        System.out.println("Using String matches method: " + new String("hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input/_ab").matches(".*/[_.].*$")); //true

    /*    Backslashes in Java
        The backslash \ is an escape character in Java Strings. That means backslash has a predefined meaning in Java.
        You have to use double backslash \\ to define a single backslash. If you want to define \w, then you must be using \\w in your regex.
        If you want to use backslash as a literal, you have to type \\\\ as \ is also an escape character in regular expressions.*/

        // Grouping exmaple
        // Removes whitespace between a word character and . or ,
        String  EXAMPLE_TEST="This .is ,my ,small ,example .string which I'm going to use for pattern matching.";
        String pattern = "(\\w)(\\s+)([.,])";
        Assert.assertEquals(EXAMPLE_TEST.replaceAll(pattern, "$1$3"), "This.is,my,small,example.string which I'm going to use for pattern matching.");
        /*
        *     public String replaceAll(String regex, String replacement) {
        return Pattern.compile(regex).matcher(this).replaceAll(replacement);
    }
        * */


        //Negative Lookahead, defined via (?!pattern). For example, the following will match "a" if "a" is not followed by "b".
        Assert.assertFalse("ab".matches("a(?!b)"));
        Assert.assertFalse(Pattern.compile("a(?!b)").matcher("ab").matches());
        Assert.assertTrue("a".matches("a(?!b)"));
    }
}

/*
Using Regular Expressions with String.matches()
Strings in Java have built-in support for regular expressions. Strings have three built-in methods for regular expressions,
i.e., matches(), split()), replace().
 s.split("regex") 	Creates an array with substrings of s divided at occurrence of "regex". "regex" is not included in the result.
* */
