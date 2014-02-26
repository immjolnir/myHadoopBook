1. J2SE java.io.
	Interface Serializable
http://docs.oracle.com/javase/1.5.0/docs/api/java/io/Serializable.html

public interface Serializable

Serializability of a class is enabled by the class implementing the java.io.Serializable interface. Classes that do not implement this interface will not have any of their state serialized or deserialized. All subtypes of a serializable class are themselves serializable. The serialization interface has no methods or fields and serves only to identify the semantics of being serializable.

To allow subtypes of non-serializable classes to be serialized, the subtype may assume responsibility for saving and restoring the state of the supertype's public, protected, and (if accessible) package fields. The subtype may assume this responsibility only if the class it extends has an accessible no-arg constructor to initialize the class's state. It is an error to declare a class Serializable if this is not the case. The error will be detected at runtime.

During deserialization, the fields of non-serializable classes will be initialized using the public or protected no-arg constructor of the class. A no-arg constructor must be accessible to the subclass that is serializable. The fields of serializable subclasses will be restored from the stream.

When traversing a graph, an object may be encountered that does not support the Serializable interface. In this case the NotSerializableException will be thrown and will identify the class of the non-serializable object.

Classes that require special handling during the serialization and deserialization process must implement special methods with these exact signatures:

 private void writeObject(java.io.ObjectOutputStream out)
     throws IOException
 private void readObject(java.io.ObjectInputStream in)
     throws IOException, ClassNotFoundException;
 

The writeObject method is responsible for writing the state of the object for its particular class so that the corresponding readObject method can restore it. The default mechanism for saving the Object's fields can be invoked by calling out.defaultWriteObject. The method does not need to concern itself with the state belonging to its superclasses or subclasses. State is saved by writing the individual fields to the ObjectOutputStream using the writeObject method or by using the methods for primitive data types supported by DataOutput.

The readObject method is responsible for reading from the stream and restoring the classes fields. It may call in.defaultReadObject to invoke the default mechanism for restoring the object's non-static and non-transient fields. The defaultReadObject method uses information in the stream to assign the fields of the object saved in the stream with the correspondingly named fields in the current object. This handles the case when the class has evolved to add new fields. The method does not need to concern itself with the state belonging to its superclasses or subclasses. State is saved by writing the individual fields to the ObjectOutputStream using the writeObject method or by using the methods for primitive data types supported by DataOutput.

Serializable classes that need to designate an alternative object to be used when writing an object to the stream should implement this special method with the exact signature:

 ANY-ACCESS-MODIFIER Object writeReplace() throws ObjectStreamException;
 

This writeReplace method is invoked by serialization if the method exists and it would be accessible from a method defined within the class of the object being serialized. Thus, the method can have private, protected and package-private access. Subclass access to this method follows java accessibility rules.

Classes that need to designate a replacement when an instance of it is read from the stream should implement this special method with the exact signature.

 ANY-ACCESS-MODIFIER Object readResolve() throws ObjectStreamException;
 

This readResolve method follows the same invocation rules and accessibility rules as writeReplace.

The serialization runtime associates with each serializable class a version number, called a serialVersionUID, which is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. If the receiver has loaded a class for the object that has a different serialVersionUID than that of the corresponding sender's class, then deserialization will result in an InvalidClassException. A serializable class can declare its own serialVersionUID explicitly by declaring a field named "serialVersionUID" that must be static, final, and of type long:

 ANY-ACCESS-MODIFIER static final long serialVersionUID = 42L;
 

If a serializable class does not explicitly declare a serialVersionUID, then the serialization runtime will calculate a default serialVersionUID value for that class based on various aspects of the class, as described in the Java(TM) Object Serialization Specification. However, it is strongly recommended that all serializable classes explicitly declare serialVersionUID values, since the default serialVersionUID computation is highly sensitive to class details that may vary depending on compiler implementations, and can thus result in unexpected InvalidClassExceptions during deserialization. Therefore, to guarantee a consistent serialVersionUID value across different java compiler implementations, a serializable class must declare an explicit serialVersionUID value. It is also strongly advised that explicit serialVersionUID declarations use the private modifier where possible, since such declarations apply only to the immediately declaring class--serialVersionUID fields are not useful as inherited members. 

2. http://www.jusfortechies.com/java/core-java/serialVersionUID.php

 When you serialize an object using Serialization mechanism (by implementing Serializable interface), there is a possibility that you may face versioning issues and because of these versioning issues, you will not be able to deserialize the object. Thats not a good thing. But first, what is this versioning issue that is troubling your serialization process?

Well, lets say you created a class, instantiated it, and wrote it out to an object stream. That flattened object sits in the file system for some time. Meanwhile, you update the class file, perhaps adding a new field. Now try to read the flattened object. hmmmm.. An exception "java.io.InvalidClassException" will be thrown. You dont understand where it went wrong because the changes in the class seem perfectly fine for you.

What is serialVersionUID?

Before we start discussing about the solution for this problem, lets first see what is actually causing this problem? Why should any change in a serialized class throw InvalidClassException? During object serialization, the default Java serialization mechanism writes the metadata about the object, which includes the class name, field names and types, and superclass. All this information is stored as part of the serialized object. When you deserialize the object, this information is read to reconsitute the object. But to perform the deserialization, the object needs to be identified first and this will be done by serialVersionUID. So everytime an object is serialized the java serialization mechanism automatically computes a hash value using ObjectStreamClass’s computeSerialVersionUID() method by passing the class name, sorted member names, modifiers, and interfaces to the secure hash algorithm (SHA), which returns a hash value, the serialVersionUID.

Now when the serilaized object is retrieved, the JVM first evaluates the serialVersionUID of the serialized class and compares the serialVersionUID value with the one of the object. If the sserialVersionUID values match then the object is said to be compatible with the class and hence it is de-serialized. If not InvalidClassException exception is thrown. 

 The above issue not only occurs when the object is flattened and saved but also when the object is flattened and sent to other JVMs when you implement RMI. Lets assume you have a client/server environment where client is using SUN's JVM in windows while server is using JRockit in Linux. Client sends a serializable class with default generated serialVersionUID (e.g 123L) to server over socket, while server may generate a different serialVersionUID (e.g 124L) during deserialization process, and raise an unexpected InvalidClassExceptions. Since the default serialVersionUID computation is highly sensitive to class details and may vary from different JVM implementation, an unexpected InvalidClassExceptions will result here.

What's the solution for this versioning issue?

The solution is very simple. Instead of relying on the JVM to generate the serialVersionUID, you explicitly mention (generate) the serialVersionUID in your class. The syntax is:

private final static long serialVersionUID = <integer value>

Yes, its a static, private variable in the class. Once you define the serialVersionUID in your class explicitly, you dont need to update it until and unless you make the incompatible changes. Look at the example below that explains the issue and importance of maintaining serialVersionUID.


class TestSUID implements Serializable {
    private static final long serialVersionUID = 1L;

    private int someId;

    public TestSUID (int someId) {
        this.someId = someId;
    }

    public int getSomeId() {
        return someId;
    }
}
 

public class SUIDTester {
    public static void main(String args[]) throws Exception {
        File file = new File("temp.ser");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        TestSUID writeSUID = new TestSUID(1);
        oos.writeObject(writeSUID);
        oos.close();

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        TestSUID readSUID = (TestSUID) ois.readObject();
        System.out.println("someId : " + readSUID.getSomeId());
        ois.close();
    }
}
 

In this example, we have created a Serializable class with serialVersionUID = 1L and saved the "some id" value in the "temp.ser" file. Now change the serialVersionUID value of "TestSUID" class to 2L and try to just read the "temp.ser" file. It will throw "InvalidClassException". The reason is the version change and exactly this is the reason for maintaining the version.


Exception in thread "main" java.io.InvalidClassException:
SerializeMe; local class incompatible: stream classdesc
serialVersionUID = 1, local class serialVersionUID = 2

When should you update serialVersionUID?
Adding serialVersinUID manually to the class does not mean that it should never be updated and never need not be updated. There is no need to update the serialVersionUID if the change in the class is compatible but it should be updated if the change is incompatible. What are compatible and incompatible changes? A compatible change is a change that does not affect the contract between the class and the callers.

The compatible changes to a class are handled as follows:

    Adding fields - When the class being reconstituted has a field that does not occur in the stream, that field in the object will be initialized to the default value for its type. If class-specific initialization is needed, the class may provide a readObject method that can initialize the field to nondefault values.
    Adding classes - The stream will contain the type hierarchy of each object in the stream. Comparing this hierarchy in the stream with the current class can detect additional classes. Since there is no information in the stream from which to initialize the object, the class’s fields will be initialized to the default values.
    Removing classes - Comparing the class hierarchy in the stream with that of the current class can detect that a class has been deleted. In this case, the fields and objects corresponding to that class are read from the stream. Primitive fields are discarded, but the objects referenced by the deleted class are created, since they may be referred to later in the stream. They will be garbage-collected when the stream is garbage-collected or reset. 

 Adding writeObject/readObject methods - If the version reading the stream has these methods then readObject is expected, as usual, to read the required data written to the stream by the default serialization. It should call defaultReadObject first before reading any optional data. The writeObject method is expected as usual to call defaultWriteObject to write the required data and then may write optional data.
Removing writeObject/readObject methods - If the class reading the stream does not have these methods, the required data will be read by default serialization, and the optional data will be discarded.
Adding java.io.Serializable - This is equivalent to adding types. There will be no values in the stream for this class so its fields will be initialized to default values. The support for subclassing nonserializable classes requires that the class’s supertype have a no-arg constructor and the class itself will be initialized to default values. If the no-arg constructor is not available, the InvalidClassException is thrown.
Changing the access to a field - The access modifiers public, package, protected, and private have no effect on the ability of serialization to assign values to the fields.
Changing a field from static to nonstatic or transient to nontransient - When relying on default serialization to compute the serializable fields, this change is equivalent to adding a field to the class. The new field will be written to the stream but earlier classes will ignore the value since serialization will not assign values to static or transient fields. 

 Incompatible changes to classes are those changes for which the guarantee of interoperability cannot be maintained. The incompatible changes that may occur while evolving a class are:

    Deleting fields - If a field is deleted in a class, the stream written will not contain its value. When the stream is read by an earlier class, the value of the field will be set to the default value because no value is available in the stream. However, this default value may adversely impair the ability of the earlier version to fulfill its contract.
    Moving classes up or down the hierarchy - This cannot be allowed since the data in the stream appears in the wrong sequence.
    Changing a nonstatic field to static or a nontransient field to transient - When relying on default serialization, this change is equivalent to deleting a field from the class. This version of the class will not write that data to the stream, so it will not be available to be read by earlier versions of the class. As when deleting a field, the field of the earlier version will be initialized to the default value, which can cause the class to fail in unexpected ways.
    Changing the declared type of a primitive field - Each version of the class writes the data with its declared type. Earlier versions of the class attempting to read the field will fail because the type of the data in the stream does not match the type of the field.
    Changing the writeObject or readObject method so that it no longer writes or reads the default field data or changing it so that it attempts to write it or read it when the previous version did not. The default field data must consistently either appear or not appear in the stream.
    Changing a class from Serializable to Externalizable or visa-versa is an incompatible change since the stream will contain data that is incompatible with the implementation in the available class.
    Removing either Serializable or Externalizable is an incompatible change since when written it will no longer supply the fields needed by older versions of the class. 

     Adding the writeReplace or readResolve method to a class is incompatible if the behavior would produce an object that is incompatible with any older version of the class.

How to generate a serialVersionUID?

There are two ways to generate the serialVersionUID.

    Go to commanline and type "serialver <>. SerialVersionUID wil be generated. Copy, paste the same into your class.
    In Windows, generate serialVersionUID using the JDK's graphical tool like so : use Control Panel | System | Environment to set the classpath to the correct directory
    run serialver -show from the command line
    point the tool to the class file including the package, for example, finance.stock.Account - without the .class
    (here are the serialver docs for both Win and Unix)
    One way is through Eclipse IDE. After you implement Serializable interface and save the class, eclipse will show a warning asking you to add the serialVersionUID and it provides you the option to generate it or use the default one. Click on the link to generate the serialVersionUID and it will generate it for you and adds it to the class.

Finally few guidelines for serialVersionUID :

    always include it as a field, for example: "private static final long serialVersionUID = 7526472295622776147L; " include this field even in the first version of the class, as a reminder of its importance
    do not change the value of this field in future versions, unless you are knowingly making changes to the class which will render it incompatible with old serialized objects
    new versions of Serializable classes may or may not be able to read old serialized objects; it depends upon the nature of the change; provide a pointer to Sun's guidelines for what constitutes a compatible change, as a convenience to future maintainers

3. http://docs.oracle.com/javase/6/docs/platform/serialization/spec/class.html#4100

Stream Unique Identifiers

    Each versioned class must identify the original class version for which it is capable of writing streams and from which it can read. For example, a versioned class must declare:

        private static final long serialVersionUID = 3487495895819393L;

    The stream-unique identifier is a 64-bit hash of the class name, interface class names, methods, and fields. The value must be declared in all versions of a class except the first. It may be declared in the original class but is not required. The value is fixed for all compatible classes. If the SUID is not declared for a class, the value defaults to the hash for that class. The serialVersionUID for dynamic proxy classes and enum types always have the value 0L. Array classes cannot declare an explicit serialVersionUID, so they always have the default computed value, but the requirement for matching serialVersionUID values is waived for array classes.

        Note - It is strongly recommended that all serializable classes explicitly declare serialVersionUID values, since the default serialVersionUID computation is highly sensitive to class details that may vary depending on compiler implementations, and can thus result in unexpected serialVersionUID conflicts during deserialization, causing deserialization to fail. 

    The initial version of an Externalizable class must output a stream data format that is extensible in the future. The initial version of the method readExternal has to be able to read the output format of all future versions of the method writeExternal.

    The serialVersionUID is computed using the signature of a stream of bytes that reflect the class definition. The National Institute of Standards and Technology (NIST) Secure Hash Algorithm (SHA-1) is used to compute a signature for the stream. The first two 32-bit quantities are used to form a 64-bit hash. A java.lang.DataOutputStream is used to convert primitive data types to a sequence of bytes. The values input to the stream are defined by the JavaTM Virtual Machine (VM) specification for classes. Class modifiers may include the ACC_PUBLIC, ACC_FINAL, ACC_INTERFACE, and ACC_ABSTRACT flags; other flags are ignored and do not affect serialVersionUID computation. Similarly, for field modifiers, only the ACC_PUBLIC, ACC_PRIVATE, ACC_PROTECTED, ACC_STATIC, ACC_FINAL, ACC_VOLATILE, and ACC_TRANSIENT flags are used when computing serialVersionUID values. For constructor and method modifiers, only the ACC_PUBLIC, ACC_PRIVATE, ACC_PROTECTED, ACC_STATIC, ACC_FINAL, ACC_SYNCHRONIZED, ACC_NATIVE, ACC_ABSTRACT and ACC_STRICT flags are used. Names and descriptors are written in the format used by the java.io.DataOutputStream.writeUTF method.

    The sequence of items in the stream is as follows:

    The class name.
    The class modifiers written as a 32-bit integer.
    The name of each interface sorted by name.
    For each field of the class sorted by field name (except private static and private transient fields:
        The name of the field.
        The modifiers of the field written as a 32-bit integer.
        The descriptor of the field. 
    If a class initializer exists, write out the following:
        The name of the method, <clinit>.
        The modifier of the method, java.lang.reflect.Modifier.STATIC, written as a 32-bit integer.
        The descriptor of the method, ()V. 
    For each non-private constructor sorted by method name and signature:
        The name of the method, <init>.
        The modifiers of the method written as a 32-bit integer.
        The descriptor of the method. 
    For each non-private method sorted by method name and signature:
        The name of the method.
        The modifiers of the method written as a 32-bit integer.
        The descriptor of the method. 
    The SHA-1 algorithm is executed on the stream of bytes produced by DataOutputStream and produces five 32-bit values sha[0..4].
    The hash value is assembled from the first and second 32-bit values of the SHA-1 message digest. If the result of the message digest, the five 32-bit words H0 H1 H2 H3 H4, is in an array of five int values named sha, the hash value would be computed as follows:

          long hash = ((sha[0] >>> 24) & 0xFF) |
        	      ((sha[0] >>> 16) & 0xFF) << 8 |
        	      ((sha[0] >>> 8) & 0xFF) << 16 |
        	      ((sha[0] >>> 0) & 0xFF) << 24 |
        	      ((sha[1] >>> 24) & 0xFF) << 32 |
        	      ((sha[1] >>> 16) & 0xFF) << 40 |
        	      ((sha[1] >>> 8) & 0xFF) << 48 |
        	      ((sha[1] >>> 0) & 0xFF) << 56;
        			 

4. http://www.jusfortechies.com/java/core-java/static-blocks.php

