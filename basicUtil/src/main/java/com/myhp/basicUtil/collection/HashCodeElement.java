package com.myhp.basicUtil.collection;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 2/17/14
 * Time: 8:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class HashCodeElement {
    public int val;

    public HashCodeElement(int val) {
        this.val = val;
    }

    @Override
    public int hashCode() { // ..........hashCode...hashCode...........
        return val;
    }
}
