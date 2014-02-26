package com.myhp.serDe.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.util.Utf8;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 2/24/14.
 */
public class PairAvro {
    static {
        System.out.println("Static block");

    }
    public void writePairAvroData(File avrofile) throws IOException {
        Schema schema = new Schema.Parser().parse(getClass().getResourceAsStream("/pair.avsc"));

        GenericRecord datum = new GenericData.Record(schema);
        datum.put("left", new Utf8("L"));
        datum.put("right", new Utf8("R"));

//        GenericRecord datum1 = new GenericData.Record(schema);
//        datum1.put("left", new Utf8("L1"));
//        datum1.put("right", new Utf8("R1"));
//
//        GenericRecord datum2 = new GenericData.Record(schema);
//        datum2.put("left", new Utf8("L2"));
//        datum2.put("right", new Utf8("R2"));

//        File file = new File("data.avro");
        File file = avrofile;
        DatumWriter<GenericRecord> writer = new GenericDatumWriter<GenericRecord>(schema);
        DataFileWriter<GenericRecord> dataFileWriter =
                new DataFileWriter<GenericRecord>(writer);
        dataFileWriter.create(schema, file);
        dataFileWriter.append(datum);
//        dataFileWriter.append(datum1);
//        dataFileWriter.append(datum2);
        dataFileWriter.close();
    }

    public void readPairAvroData(){
        Schema schema = null;
        File file = new File("data.avro");
        try {
            schema = new Schema.Parser().parse(getClass().getResourceAsStream("/pair.avsc"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DatumReader<GenericRecord> reader = new GenericDatumReader<GenericRecord>();
        DataFileReader<GenericRecord> dataFileReader =
                null;
        try {
            dataFileReader = new DataFileReader<GenericRecord>(file, reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat("Schema is the same", schema, is(dataFileReader.getSchema()));


        assertThat(dataFileReader.hasNext(), is(true));
        GenericRecord result = dataFileReader.next();
        assertThat(result.get("left").toString(), is("L"));
        assertThat(result.get("right").toString(), is("R"));
        assertThat(dataFileReader.hasNext(), is(false));
    }

    public void OverrideAvroWithDataFileWriter(File avroFile){
        Schema schema = null;
        try {
            schema = new Schema.Parser().parse(getClass().getResourceAsStream("/pair.avsc"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        File file = new File("data.avro");
        File file = avroFile;
        DatumWriter<GenericRecord> writer = new GenericDatumWriter<>(schema);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(writer);

        try {
            dataFileWriter.create(schema,file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        GenericRecord datum = new GenericData.Record(schema);
        datum.put("left", "NL");
        datum.put("right", "NR");
        try {
            dataFileWriter.append(datum);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
//    public static void main(String [] args) {
//        PairAvro pairAvro = new PairAvro();
//
//        try {
//            pairAvro.writePairAvroData();
//            pairAvro.appendAvroWithDataFileWriter();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        new PairAvro().readPairAvroData();
//        // after append, the readPairAvroData()
//        //Expected: is "L"
////        but: was "NL"
//
//        System.out.println("Main done");
//    }
}
