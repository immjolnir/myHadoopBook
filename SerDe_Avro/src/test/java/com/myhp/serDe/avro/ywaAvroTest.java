package com.myhp.serDe.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericArray;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.codehaus.jackson.JsonNode;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 2/26/14
 * Time: 5:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class ywaAvroTest {

    @Test
    public void testReadPairAvroDataWithoutSchema1() throws IOException {
        URL url = this.getClass().getResource("/201402251032/dhrb-edg-019.data.bf1.yahoo.com_1393023007870_sp_v2_201402251030_201402251030_a228d1045f30a54db2db95257e721a60.478.avro");
        System.out.println("URL: " + url.toString()); // start with file;/*
        File avroFile = new File(url.getFile()); // get the file name without protocol.

        DatumReader<GenericData.Array> recordDatumReader = new GenericDatumReader<GenericData.Array>();
        DataFileReader<GenericData.Array> dataFileReader = new DataFileReader<GenericData.Array>(avroFile, recordDatumReader);
        Schema schema = dataFileReader.getSchema();

        System.out.println(schema.toString());

        // 1st part: schema info
        Assert.assertEquals(schema.getType(), Schema.Type.ARRAY);
        System.out.println("Full Name: " + schema.getFullName() + " Name: " + schema.getName());
        System.out.println("doc: " + schema.getDoc());
        //System.out.println(dataFileReader.getMetaKeys());
        //System.out.println(dataFileReader.getBlockCount());


        // 2nd part: data part
        System.out.println("===================");
        //GenericData.Array array = null;
        Map<String, JsonNode> kv = new HashMap<>();

            while (dataFileReader.hasNext()) {
            //java.lang.ClassCastException: org.apache.avro.generic.GenericData$Array cannot be cast to org.apache.avro.generic.GenericRecord
             GenericArray array = dataFileReader.next();
            GenericRecord genericRecord = (GenericRecord) array.get(0);
            System.out.println(genericRecord.getSchema());
            readRecord(genericRecord, kv);
                for(String key: kv.keySet()){
                    Object value = genericRecord.get(key);
                    genericRecord.put(key, va);
                    System.out.println("key: " + key + ", value: " + value);
                }
            break;
        }

    }

    public Map readRecord(GenericRecord record, Map kv) {


        Schema schema = record.getSchema();
        List<Schema.Field> fieldList = schema.getFields();
        System.out.println(fieldList.size());
        for(Schema.Field field: fieldList){
//            System.out.println(field.toString());
//            System.out.println(field.schema());
//            System.out.println(field.name());
            kv.put(field.name(), field.defaultValue());
         }
        return kv;
    }
}
