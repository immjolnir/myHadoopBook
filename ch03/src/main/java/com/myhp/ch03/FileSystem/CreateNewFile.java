package com.myhp.ch03.FileSystem;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 2/24/14
 * Time: 9:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class CreateNewFile {

    public static void createNewFileTest(){
        try {
            if ( new File("hello_newfilie").createNewFile()) {
                System.out.println("new file created");
            }else {
                System.out.println("new file already exist ");
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        try {
            if ( new File("hello_newfilie").createNewFile()) {
                System.out.println("new file created");
            }else {
                System.out.println("new file already exist ");
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static void newFileWithCloseTest(){
        File file = new File("new_file");
        //file.close();
    }

    public static void main(String[] args) {
        createNewFileTest();
    }
}
/*
* public boolean createNewFile()
                      throws IOException

Atomically creates a new, empty file named by this abstract pathname if and only if a file with this name does not yet exist. The check for the existence of the file and the creation of the file if it does not exist are a single operation that is atomic with respect to all other filesystem activities that might affect the file.

Note: this method should not be used for file-locking, as the resulting protocol cannot be made to work reliably. The FileLock facility should be used instead.

Returns:
    true if the named file does not exist and was successfully created; false if the named file already exists

* public File(String pathname)  -- not create a physic file on fileSytem, but return a Fileinstance.

Creates a new File instance by converting the given pathname string into an abstract pathname.
If the given string is the empty string, then the result is the empty abstract pathname.
* */
