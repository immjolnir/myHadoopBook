package com.myhp.basicUtil.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 2/17/14
 * Time: 9:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class HashCodeElementConstantValTest {
    @Test
    public void testHashCode() throws Exception {
        HashMap<HashCodeElementConstantVal, String> map = new HashMap<HashCodeElementConstantVal, String>();
        map.put(new HashCodeElementConstantVal(1), "1");
        map.put(new HashCodeElementConstantVal(2), "2");  //add debug on this line
        for(Map.Entry<HashCodeElementConstantVal, String> entry : map.entrySet()) {
            System.out.println("key: " + entry.getKey().hashCode() + ", Value: " + entry.getValue());
        }
        /*output
        * key: 1, Value: 2
key: 1, Value: 1
        * */
    }
}
