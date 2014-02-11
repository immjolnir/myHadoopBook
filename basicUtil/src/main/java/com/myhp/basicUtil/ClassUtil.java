package com.myhp.basicUtil;

/**
 * Copied from org.apache.hadoop.util.ClassUtil
 */

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

import static java.lang.System.*;


public class ClassUtil {
    /**
     * Find a jar that contains a class of the same name, if any.
     * It will return a jar file, even if that is not the first thing
     * on the class path that has a class with the same name.
     * if ("jar".equals(url.getProtocol()))
     *
     * Protocol handlers for the following protocols are guaranteed
     * to exist on the search path :-
     *
     *     http, https, ftp, file(by java runCmd ), and jar(by hadoop jar runCmd)
     *
     * @param clazz the class to find.
     * @return a jar file that contains the class, or null.
     * @throws IOException
     */
    public static String findContainingJar(Class clazz) {
        ClassLoader loader = clazz.getClassLoader();
        String classFile = clazz.getName().replaceAll("\\.", "/") + ".class";
        try {
            for (Enumeration itr = loader.getResources(classFile);
                 itr.hasMoreElements();) {
                URL url = (URL) itr.nextElement();
                //if ("jar".equals(url.getProtocol())) { // hadoop run protocol jar
                if ("file".equals(url.getProtocol())) {
                    String toReturn = url.getPath();
                    if (toReturn.startsWith("file:")) {
                        toReturn = toReturn.substring("file:".length());
                    }
                    toReturn = URLDecoder.decode(toReturn, "UTF-8");
                    return toReturn.replaceAll("!.*$", "");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    //java.library.path
    public static void main(String[] args){
        //System.out.println(System.getProperty("java.library.path"));
        String javaLibPath = getProperty("java.class.path");
        setProperty("java.class.path", "/tmp/lib/jars/ch02-1.0-SNAPSHOT.jar" + ":" + javaLibPath);
        out.println(getProperty("java.class.path"));
//        out.println(findContainingJar(MaxTemperatureMain.class)); // it does work

        /*
        * Here's the code I used to print all the java system properties
        * */
          Properties p = System.getProperties();
        // 1st method
//        for( Map.Entry entry: p.entrySet())
//        {
//            System.out.println(entry.getKey() + " = " + entry.getValue());
//        }

        // 2nd method
//        Enumeration keys = p.keys();
//        while ( keys.hasMoreElements()){
//            String key = (String) keys.nextElement();
//            String value = (String) p.get(key);
//            out.println(key + " = " + value);
//        }

        /*
        * Here's the code to print all environment variable
        * */

        Map<String, String> envMap = System.getenv();
        for (Map.Entry entry : envMap.entrySet()) {
            out.println( entry.getKey() + " = " + entry.getValue());
        }
    }
}
