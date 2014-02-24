package com.myhp.serDe.avro;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.file.SeekableFileInput;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.Test;


import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 2/24/14
 * Time: 9:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserTest {
    private User user1, user2, user3;
    private static final String avro_diskFile = "users.avro";


    private void createUsers(){
        user1 = new User();
        user1.setName("Alyssa");
        user1.setFavoriteNumber(256);
// Leave favorite color null

// Alternate constructor
        user2 = new User("Ben", 7, "red");

// Construct via builder
        user3 = User.newBuilder()
                .setName("Charlie")
                .setFavoriteColor("blue")
                .setFavoriteNumber(null)
                .build();
    }

    @Test
    public void serializationTest() throws IOException {
        createUsers();

        // Serialize user1 and user2 to disk
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
        dataFileWriter.create(user1.getSchema(), new File(avro_diskFile));

        dataFileWriter.append(user1);
        dataFileWriter.append(user2);
        dataFileWriter.append(user3);
        dataFileWriter.close();
    }

    @Test
    public void deserializationTest() throws IOException {
        // Deserialize Users from disk
        DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
        DataFileReader<User> dataFileReader = new DataFileReader<User>(new File(avro_diskFile), userDatumReader);
        User user = null;

        while (dataFileReader.hasNext()) {
// Reuse user object by passing it to next(). This saves us from
// allocating and garbage collecting many objects for files with
// many items.
            user = dataFileReader.next(user);
            System.out.println(user);
        }
        /*
        * output
        * {"name": "Alyssa", "favorite_number": 256, "favorite_color": null}
{"name": "Ben", "favorite_number": 7, "favorite_color": "red"}
{"name": "Charlie", "favorite_number": null, "favorite_color": "blue"}

        *  */
    }

    @Test
    public void FileClassTest() throws IOException {
        if ( new File("hello_newfilie").createNewFile()) {
            System.out.println("new file created");
        }else {
            System.out.println("new file not exist ");
        }

        if ( new File("hello_newfilie").createNewFile()) {
            System.out.println("new file created");
        }else {
            System.out.println("new file already exist ");
        }
    }

}
