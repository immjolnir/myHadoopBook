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
    public void writePairAvroData() throws IOException {
        Schema schema = new Schema.Parser().parse(getClass().getResourceAsStream("/pair.avsc"));

        GenericRecord datum = new GenericData.Record(schema);
        datum.put("left", new Utf8("L"));
        datum.put("right", new Utf8("R"));

        File file = new File("data.avro");
        DatumWriter<GenericRecord> writer = new GenericDatumWriter<GenericRecord>(schema);
        DataFileWriter<GenericRecord> dataFileWriter =
                new DataFileWriter<GenericRecord>(writer);
        dataFileWriter.create(schema, file);
        dataFileWriter.append(datum);
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

    public static void main(String [] args) {
        try {
            new PairAvro().writePairAvroData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new PairAvro().readPairAvroData();

        System.out.println("Main done");
    }
}
