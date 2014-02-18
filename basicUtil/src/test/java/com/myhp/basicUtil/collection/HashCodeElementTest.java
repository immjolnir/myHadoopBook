package com.myhp.basicUtil.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 2/17/14
 * Time: 8:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class HashCodeElementTest {

    @Test
    public void testHashCode() {
        HashMap<HashCodeElement, String> map = new HashMap<HashCodeElement, String>();
        map.put(new HashCodeElement(1), "1");
        map.put(new HashCodeElement(2), "2");       //add debug on this line
        for (Map.Entry<HashCodeElement, String> entry : map.entrySet()) {
            System.out.println("key: " + entry.getKey().hashCode() + ", Value: " + entry.getValue());
        }
        /* output
        * key: 1, Value: 1
key: 2, Value: 2
        * */
    }
}



