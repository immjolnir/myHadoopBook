package com.myhp.basicUtil;

import org.junit.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created on 2/12/14.
 * It is used to learn the Date class in java.
 * Date, DateFormat, SimpleDateFormat
 * <p/>
 * Recommender link: http://tutorials.jenkov.com/java-date-time/parsing-formatting-dates.html
 *
 * @param date
 * @param format passed as parameter to the SimpleDateFormat class is a pattern that tells how the instance is to parse and format @date.
 *               Below is a list of the most common pattern letters you can use.
 *               y   = year   (yy or yyyy)
 *               M   = month  (MM)
 *               d   = day in month (dd)
 *               h   = hour (0-12)  (hh)
 *               H   = hour (0-23)  (HH)
 *               m   = minute in hour (mm)
 *               s   = seconds (ss)
 *               S   = milliseconds (SSS)
 *               z   = time zone  text        (e.g. Pacific Standard Time...)
 *               Z   = time zone, time offset (e.g. -0800)
 *               Here are a few pattern examples, with examples of how each pattern would format or expect to parse a date:
 *               yyyy-MM-dd           (2009-12-31)
 *               <p/>
 *               dd-MM-YYYY           (31-12-2009)
 *               <p/>
 *               yyyy-MM-dd HH:mm:ss  (2009-12-31 23:59:59)
 *               <p/>
 *               HH:mm:ss.SSS         (23:59.59.999)
 *               <p/>
 *               yyyy-MM-dd HH:mm:ss.SSS   (2009-12-31 23:59:59.999)
 *               <p/>
 *               yyyy-MM-dd HH:mm:ss.SSS Z   (2009-12-31 23:59:59.999 +0100)
 *
 *  @return the formated date string
 */
public class DateTimeFormat {
    static String simpleDateFormat(Date date, String format) {
        SimpleDateFormat fm = new SimpleDateFormat(format);
        Assert.assertEquals(fm.toLocalizedPattern(), format);
        Assert.assertEquals(fm.toPattern(), format);
        return fm.format(date);
    }

    static String simpleDateFormat(String date) {
        return new SimpleDateFormat(date).toPattern();
    }

    public static void main(String[] args) {
        Date date = new Date();
        System.out.print(date.toString());
        Assert.assertEquals(simpleDateFormat(date, "yyyy-MM-dd"), "2014-02-12");
    }
}
/*
* SimpleDateFormat class works on java.util.Date instances. Take the simpleDateFormat as example,
*
* */