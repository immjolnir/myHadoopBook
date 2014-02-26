package com.myhp.serDe.avro;

import javafx.util.Pair;
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
import org.codehaus.jackson.JsonNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2/25/14.
 */
public class PairAvroTest {
    private static Schema schema = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Pair\",\"doc\":\"A pair of strings.\",\"fields\":[{\"name\":\"left\",\"type\":\"string\"},{\"name\":\"right\",\"type\":\"string\"}]}");
    private  static File avroFile = new File("pairData.avro");

    @Before
    public void testWritePairAvroData() throws Exception {
        PairAvro pairAvro = new PairAvro();
        pairAvro.writePairAvroData(avroFile);
    }

    @Test
    public void testReadPairAvroDataWithoutSchema() throws IOException {
        DatumReader<GenericRecord> recordDatumReader = new GenericDatumReader<GenericRecord>();
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(avroFile, recordDatumReader);
        Schema schema = dataFileReader.getSchema();
        System.out.println(schema.toString());
        /* in Schema.java
        public enum Type {
            RECORD, ENUM, ARRAY, MAP, UNION, FIXED, STRING, BYTES,
            INT, LONG, FLOAT, DOUBLE, BOOLEAN, NULL;
            private String name;
            private Type() { this.name = this.name().toLowerCase(); }
            public String getName() { return name; }
        };*/
        // 1st part: schema info
        Assert.assertEquals(schema.getType(), Schema.Type.RECORD);
        System.out.println("Full Name: " + schema.getFullName() + " Name: " + schema.getName());
        System.out.println("doc: " + schema.getDoc());
        List<Schema.Field> fieldList = schema.getFields();
        System.out.println("Record size: " + fieldList.size()); //2

        Map<String, JsonNode> kv = new HashMap<>();

        for (Schema.Field field : fieldList) {
            System.out.println(field.toString()); //left type:STRING pos:0
            System.out.println(field.defaultValue()); // null
            System.out.println(field.doc()); // null
            System.out.println(field.name()); // key name: left
            System.out.println(field.pos()); // 0 &  1, start from 0,
            System.out.println(field.order()); // default order is Order.ASCENDING
            System.out.println(field.schema()); //"string" & "string"
//            System.out.println(field.getProp());
            kv.put(field.name(), field.defaultValue());
        }

        // 2nd part: data part
        System.out.println("===================");
        GenericRecord record = null;
        while (dataFileReader.hasNext()){
            record = dataFileReader.next(record);
            //System.out.println(record.toString());
            for(String key: kv.keySet()) {
                System.out.println(key + ":" + record.get(key));
            }
        }

        // write back to another avro file, and compare them.
        GenericRecord datum = new GenericData.Record(schema);
        datum.put("right", new Utf8("R"));
        datum.put("left", new Utf8("L"));

        File file = new File("new_pairData.avro");
        DatumWriter<GenericRecord> writer = new GenericDatumWriter<GenericRecord>(schema);
        DataFileWriter<GenericRecord> dataFileWriter =
                new DataFileWriter<GenericRecord>(writer);
        dataFileWriter.create(schema, file);
        dataFileWriter.append(datum);
//        dataFileWriter.append(datum1);
//        dataFileWriter.append(datum2);
        dataFileWriter.close();

    }
}
