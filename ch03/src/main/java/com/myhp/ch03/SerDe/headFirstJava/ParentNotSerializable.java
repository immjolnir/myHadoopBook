package com.myhp.ch03.SerDe.headFirstJava;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 2/24/14
 * Time: 5:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class ParentNotSerializable {
    public ParentNotSerializable(String str){
        System.out.println("ParentNotSerializable(with no-arg constructor) invoked: " + str);
        // expected Error during Deserialize
        //java.io.InvalidClassException: com.myhp.ch03.SerDe.headFirstJava.GameCharacter; no valid constructor

    }
}
