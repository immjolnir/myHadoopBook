package com.myhp.basicUtil.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 2/17/14
 * Time: 7:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class NameImpComparableTest {
    final String first = "DAY";
    final String first2 = "DAY";
    final String last = "DAVE";


    @Test
    public void testEqual_HashCode() throws Exception {

        NameImpComparable nameable = new NameImpComparable("DAY", "DAVE");
        NameImpComparable nameable2 = new NameImpComparable("DAY", "DAVE");

        Assert.assertEquals(nameable.firstName(), first);
        Assert.assertEquals(nameable.lastName(), last);
        Assert.assertTrue(nameable.equals(nameable2)); //only equal() called
        Assert.assertTrue(nameable.hashCode() == nameable2.hashCode());// only hashCode called

    }

    @Test
    public void testEqual_HashCode_Failure() throws Exception {

        final String first = "DAY";
        final String last = "DAVE";

        NameImpComparable nameable = new NameImpComparable(new String("DAY"), new String("DAVE"));
        NameImpComparable nameable2 = new NameImpComparable(new String("DAY"), new String("DAVE"));

        Assert.assertEquals(nameable.firstName(), first);
        Assert.assertEquals(nameable.lastName(), last);

        Assert.assertTrue(nameable.hashCode() == nameable2.hashCode());// only hashCode called
        // why1 ??

        Assert.assertTrue(nameable.equals(nameable2)); //only equal() called
        Assert.assertFalse(nameable == nameable2);
        // why 2? == fail.

        // if two objects are equal accorings to the equal() method, than calling the hashCode, should return the same integer.

    }

    @Test
    public void testString() {
        Assert.assertTrue(first.equals(first2));
        Assert.assertTrue(first.hashCode() == first2.hashCode());
        Assert.assertTrue(first == first2);

        String firstInMem = new String(first);
        String firstInMem2 = new String(first);
        Assert.assertFalse(firstInMem == firstInMem2); // firstInMem's reference is not equal to firstInMem2 in memeroy.
    }

    @Test
    public void testCompareTo() {
        NameImpComparable nameArray[] = {
                new NameImpComparable("John", "Smith"),
                new NameImpComparable("Karl", "Ng"),
                new NameImpComparable("Jeff", "Smith"),
                new NameImpComparable("Tom", "Rich")
        };

        List<NameImpComparable> names = Arrays.asList(nameArray);
        Collections.sort(names);
        System.out.println(names);
        /*  output
        * compareTo()
compareTo()
compareTo()
compareTo()
compareTo()
compareTo()
toString() // print by System.out.println
toString()
toString()
toString()
[Karl Ng, Tom Rich, Jeff Smith, John Smith]
        * */
    }
}