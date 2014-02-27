package com.myhp.serDe.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.io.DatumReader;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 2/27/14
 * Time: 3:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class GetAvroSchema {

    public Schema getSchema(File avroFile){
        DatumReader<GenericData> recordDatumReader = new GenericDatumReader<>();
        DataFileReader<GenericData> dataFileReader = null;
        try {
            dataFileReader = new DataFileReader<GenericData>(avroFile, recordDatumReader);
        } catch (IOException e) {
            System.err.printf("***Err in %s \n", "getSchema()");
            e.printStackTrace();
        }
        Schema schema = dataFileReader.getSchema();
        return schema;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.printf("Usage: %s <avroFile.avro> \n",
                    "com.myhp.serDe.avro.GetAvroSchema");
        }
        File avroFile = new File(args[0]);
        System.out.println("avro File Path: " + avroFile.getAbsolutePath());
        Schema schema = new GetAvroSchema().getSchema(avroFile);
        System.out.println(schema.toString());
    }
}
