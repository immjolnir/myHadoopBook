package com.myhp.ch03.SerDe.headFirstJava;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;

/**
 * Created on 2/23/14.
 */
public class GameCharacterTest {
    private String file = "Game.ser";

    @Ignore
    @Test
    public void getCurrentPathTest() {
        Assert.assertEquals(new File(".").getAbsolutePath(), "/home/zhishan/workspace/git/myHadoopBook/.");
        File path = new File("src/test/resources/data/SerDe");
//        Assert.assertEquals(path.getAbsolutePath(), "");
        Assert.assertEquals(path.getPath(), "");

    }

    @Test
    public void serializeGameObjectTest() {
        GameCharacter one = new GameCharacter(50, "Elf", new String[]{"bow", "sword", "dust"});
        GameCharacter two = new GameCharacter(200, "Troll", new String[]{"bare hands", "big ax"});
        GameCharacter three = new GameCharacter(120, "Magician", new String[]{"spells", "invisibility"});

        try {
            File serOutput = new File(file);
            FileOutputStream fileOutputStream = new FileOutputStream(serOutput);
            ObjectOutputStream os = new ObjectOutputStream(fileOutputStream);
            os.writeObject(one);
            os.writeObject(two);
            os.writeObject(three);

            os.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void deSerializeGameObjectTest()  {
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
            GameCharacter oneRestore = (GameCharacter) is.readObject();
            GameCharacter twoRestore = (GameCharacter) is.readObject();
            GameCharacter threeRestore = (GameCharacter) is.readObject();
            System.out.println("One's type: " + oneRestore.getType());
            System.out.println("Two's type: " + twoRestore.getType());
            System.out.println("Three's type: " + threeRestore.getType());

        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

/*
* in ObjectOutputStream.java
*
            * // remaining cases
            if (obj instanceof String) {
                writeString((String) obj, unshared);
            } else if (cl.isArray()) {
                writeArray(obj, desc, unshared);
            } else if (obj instanceof Enum) {
                writeEnum((Enum) obj, desc, unshared);
            } else if (obj instanceof Serializable) {
                writeOrdinaryObject(obj, desc, unshared);
            } else {
                if (extendedDebugInfo) {
                    throw new NotSerializableException(
                        cl.getName() + "\n" + debugInfoStack.toString());
                } else {
                    throw new NotSerializableException(cl.getName());
                }
            }
 So, String, Array, Enum and those classes implements Serialiable interfaces are Serialiablable!!

 If GameCharacter do not implements serializable interface,
 "java.io.NotSerializableException: com.myhp.ch03.SerDe.headFirstJava.GameCharacter" is issued.
* */


/*
* deserialize error:
*
* java.io.InvalidClassException: com.myhp.ch03.SerDe.headFirstJava.GameCharacter; local class incompatible:
* stream classdesc serialVersionUID = 6052424284110960213, <== before modify the GameCharacter.java class
* local class serialVersionUID = 1052424284110960213, <== after modification.
*
* serialVersionUID
*     /**
     * Return the serialVersionUID for this class.  The serialVersionUID
     * defines a set of classes all with the same name that have evolved from a
     * common root class and agree to be serialized and deserialized using a
     * common format.  NonSerializable classes have a serialVersionUID of 0L.
     *
     * @return  the SUID of the class described by this descriptor
     *
      *  serialVersionUID of represented class (null if not computed yet)
        private volatile Long suid; *

public long getSerialVersionUID() {
    // REMIND: synchronize instead of relying on volatile?
    if (suid == null) {
        suid = AccessController.doPrivileged(
                new PrivilegedAction<Long>() {
                    public Long run() {
                        return computeDefaultSUID(cl);
                    }
                }
        );
    }
    return suid.longValue();
}

    /**
     * Returns explicit serial version UID value declared by given class, or
     * null if none.

private static Long getDeclaredSUID(Class<?> cl) {
    try {
        Field f = cl.getDeclaredField("serialVersionUID");
        int mask = Modifier.STATIC | Modifier.FINAL;
        if ((f.getModifiers() & mask) == mask) {
            f.setAccessible(true);
            return Long.valueOf(f.getLong(null));
        }
    } catch (Exception ex) {
    }
    return null;
}

It required to define the uid as the format:
"static final long serialVersionUID = 1052424284110960213L;"
* */