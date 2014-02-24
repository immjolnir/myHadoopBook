package com.myhp.ch03.Configuration;// == MultipleResourceConfigurationTest
// == MultipleResourceConfigurationTest-Override
// == MultipleResourceConfigurationTest-Final
// == MultipleResourceConfigurationTest-Expansion
// == MultipleResourceConfigurationTest-SystemExpansion
// == MultipleResourceConfigurationTest-NoSystemByDefault

import org.apache.hadoop.conf.Configuration;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MultipleResourceConfigurationTest {

    @Test
    public void get() throws IOException {
        // Single test as an expedient for inclusion in the book

        // vv MultipleResourceConfigurationTest
        Configuration conf = new Configuration();
        conf.addResource("configuration-1.xml");
        conf.addResource("configuration-2.xml");
        // ^^ MultipleResourceConfigurationTest

        assertThat(conf.get("color"), is("yellow"));

        // override
        // vv MultipleResourceConfigurationTest-Override
        assertThat(conf.getInt("size", 0), is(12));
        // ^^ MultipleResourceConfigurationTest-Override

        // final properties cannot be overridden
        // vv MultipleResourceConfigurationTest-Final
        assertThat(conf.get("weight"), is("heavy"));


        // variable expansion
        // vv MultipleResourceConfigurationTest-Expansion
        assertThat(conf.get("size-weight"), is("12,heavy"));
        // ^^ MultipleResourceConfigurationTest-Expansion

        // variable expansion with system properties
        // vv MultipleResourceConfigurationTest-SystemExpansion
        System.setProperty("size", "14");
        assertThat(conf.get("size-weight"), is("14,heavy"));
        // ^^ MultipleResourceConfigurationTest-SystemExpansion

        // system properties are not picked up
        // vv MultipleResourceConfigurationTest-NoSystemByDefault
        System.setProperty("length", "2");
        assertThat(conf.get("length"), is((String) null));
        // ^^ MultipleResourceConfigurationTest-NoSystemByDefault

    }
    /* 3 ways to reset the final configuratio attribute.
        * 1st, using Configuration instance as above, it is ok.
        * 2nd, appending "-Dproperty=value" to the JVM cmd line, "Warning Ignore" not error
        * 3rd, redefine it in its following configure file, like override_final.xml "Warning Ignore" not error.
        * */
    @Test
    public void resetConfValueWithFinalAttrTest() {
        Configuration conf = new Configuration();
        conf.addResource("configuration-1.xml");
        assertThat(conf.get("weight"), is("heavy"));
        conf.set("weight", "reset-it-to-final");
        // 1st way, OK
        assertThat(conf.get("weight"), is("reset-it-to-final"));

        // 3rd way, fail, but it is not work here.
        // Warning got:
        //14/02/23 21:29:02 WARN conf.Configuration: override_final.xml:a attempt to override final parameter: weight;  Ignoring.
        //
        conf.addResource("override_final.xml");
        assertThat(conf.get("override_final"), is("test_its_ok"));
        assertThat(conf.get("weight"), is("set-it-in-override_final_xml"));
    }


}
