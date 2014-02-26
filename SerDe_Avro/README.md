1. There are two ways to generate the User class according to the user.avsc.
  1st way,  by using the maven plugin
          <plugin>
                <groupId>org.apache.avro</groupId>
                <artifactId>avro-maven-plugin</artifactId>
                <version>1.7.6</version>
                <executions>
                    <execution>
                        <phase>generate-sources</phase>
                        <goals>
                            <goal>schema</goal>
                        </goals>
                        <configuration>
                            <sourceDirectory>${project.basedir}/src/main/resources</sourceDirectory>
                            <outputDirectory>${project.basedir}/src/main/java/</outputDirectory>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>1.6</source>
                    <target>1.6</target>
                </configuration>
            </plugin>
 $ mvn generate-sources
  2nd way, useing Avro tool, refer the "Hadoop Definitive Guide Edition2" p 108

How to use it? refer to UserTest.java and PairAvroTest.java

2. $ mvn assembly:single
https://maven.apache.org/plugins/maven-assembly-plugin/index.html
https://maven.apache.org/plugins/maven-assembly-plugin/descriptor-refs.html#jar-with-dependencies
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-assembly-plugin</artifactId>
                <version>2.4</version>
                <configuration>
                <descriptorRefs>
                    <descriptorRef>jar-with-dependencies</descriptorRef>
                </descriptorRefs>
            </configuration>
            </plugin>

3. https://github.com/phunt/avro-rpc-quickstart/blob/master/src/main/java/example/Main.java

4. Avro API

4.1 DatumReader: interface,Determines the in-memory data representation
    D read(D reuse,Decoder in) throws IOException
        Read a datum. Traverse the schema, depth-first, reading all leaf values in the schema into a datum that is returned. If the provided datum is non-null it may be reused and returned.
4.2 Decoder: class, Low-level support for de-serializing Avro values.
  This class has two types of methods. One type of methods support the reading of leaf values (for example, readLong() and readString(org.apache.avro.util.Utf8)).

 The other type of methods support the reading of maps and arrays. These methods are readArrayStart(), arrayNext(), and similar methods for maps). See readArrayStart() for details on these methods.)

 DecoderFactory contains Decoder construction and configuration facilities.
