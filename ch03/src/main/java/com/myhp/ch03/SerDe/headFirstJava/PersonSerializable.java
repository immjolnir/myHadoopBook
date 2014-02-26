package com.myhp.ch03.SerDe.headFirstJava;

import java.io.*;

public class PersonSerializable {

    public static void main(String[] args) throws Exception {

        File file = new File("person.out");
        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file));
        oout.writeObject(Person.getInstance());
        System.out.println(Person.getInstance());
        oout.close();

        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));
        Object newPerson = oin.readObject();
        oin.close();
        System.out.println(newPerson);

        System.out.println(Person.getInstance() == newPerson);
    }
}

/*
* 1st time try
* arg constructor
{name: John, age: 31, Gender: MALE}
{name: John, age: 31, Gender: MALE}
false
*
*
* 2nd time try: with method "Object readResolve() throws ObjectStreamException" added
*
* arg constructor
{name: John, age: 31, Gender: MALE}
resolveObject is called
{name: John, age: 31, Gender: MALE}
true

* */
