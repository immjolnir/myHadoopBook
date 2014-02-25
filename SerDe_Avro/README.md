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

