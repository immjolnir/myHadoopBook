package com.myhp.serDe.avro;

import org.apache.avro.Schema;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 2/27/14
 * Time: 4:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class GetAvroSchemaTest {
    @Test
    public void testGetSchema() throws Exception {
        File schemaFile = new File(getClass().getResource("/expectedSchemaFile").getFile());
        Schema expectedSchema = new Schema.Parser().parse(schemaFile);

        File avroFile = new File(getClass().getResource("/part-00000.avro").getFile());
        Schema actuallySchema = new GetAvroSchema().getSchema(avroFile);
        Assert.assertEquals("[GetAvroSchemaTest] Avro File's Schema is same.", expectedSchema, actuallySchema);
    }
}
