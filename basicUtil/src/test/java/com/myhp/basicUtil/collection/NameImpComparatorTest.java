package com.myhp.basicUtil.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 2/17/14
 * Time: 8:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class NameImpComparatorTest {

    @Test
    public void testCompare() {
        NameImpComparator nameArray[] = {
                new NameImpComparator("John", "Smith"),
                new NameImpComparator("Karl", "Ng"),
                new NameImpComparator("Jeff", "Smith"),
                new NameImpComparator("Tom", "Rich")
        };

        List<NameImpComparator> names = Arrays.asList(nameArray);

        Collections.sort(names,NameImpComparator.SENIORITY_ORDER);

        System.out.println(names);
        /*  output
        * compareTo()
Comparator compare()
Comparator compare()
Comparator compare()
Comparator compare()
Comparator compare()
Comparator compare()
toString() // print by System.out.println
toString()
toString()
toString()
[Karl Ng, Tom Rich, Jeff Smith, John Smith]
        * */
    }
}
