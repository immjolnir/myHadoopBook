package com.myhp.ch03.SerDe.headFirstJava;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class Person implements Serializable {

    private static class InstanceHolder {
        private static final Person instatnce = new Person("John", 31, Gender.MALE);
    }

    public static Person getInstance() {
        return InstanceHolder.instatnce;
    }

    private String name = null;

    private Integer age = null;

    private Gender gender = null;

    private Person() {
        System.out.println("none-arg constructor");
    }

    @Override
    public String toString() {
        return new String("{name: " + name + ", age: " + age +", Gender: " + gender+"}");
    }

    private Person(String name, Integer age, Gender gender) {
        System.out.println("arg constructor");
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    /*
    * Called during Deserialize stage by readObject method.
    * it call resolve SingleStone class issue.
    *
    * Here is an example to return an instance during the deserialize. but the deserialzied object is recycled at the same time.
    * */
    private Object readResolve() throws ObjectStreamException {
        System.out.println("resolveObject is called");
        return InstanceHolder.instatnce;
    }
}
