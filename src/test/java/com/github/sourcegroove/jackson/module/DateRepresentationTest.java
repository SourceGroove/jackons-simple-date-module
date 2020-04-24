package com.github.sourcegroove.jackson.module;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;


public class DateRepresentationTest {

    @Test()
    public void testFormatter(){
        DateTimeFormatter fmt = new DateRepresentation().getFormatter();
        
        assertNotNull(fmt.parse("2020-04-23T20:09:39.1-04:00"));
        assertNotNull(fmt.parse("2020-04-23"));
        assertNotNull(fmt.parse("2020-04-23T01:02:03"));
        assertNotNull(fmt.parse("2020-04-23T01:02:03.123"));
        assertNotNull(LocalDateTime.parse("2020-04-23T01:02", fmt));
        assertNotNull(OffsetDateTime.parse("2020-04-23", fmt));
    }
    
    @Test()
    public void testParsePartialIsoFormats(){
        assertNotNull(new DateRepresentation().of("2020-04-23"));
        assertNotNull(new DateRepresentation().of("2020-04-23T11:56:29"));
        assertNotNull(new DateRepresentation().of("2020-04-23T11:56:29.532-04:00"));
        assertNotNull(new DateRepresentation().of("2020-04-23T11:56:29-04:00"));
    }
    
    @Test
    public void testParsePartialUtcFormats(){
        assertNotNull(new DateRepresentation(DateRepresentationType.UTC).of("2020-04-23T15:58:29.053Z"));
        //assertNotNull(new DateRepresentation(DateRepresentationType.UTC).of("2020-04-23T11:56:29"));
    }
    @Test()
    public void testIsoSerialize() {
        System.out.println("Epoch: " + new DateRepresentation().of(new Date().getTime()).serialize());
        System.out.println("Date: " + new DateRepresentation().of(new Date()).serialize());
        System.out.println("LocalDate: " + new DateRepresentation().of(LocalDate.now()).serialize());
        System.out.println("LocalDateTime: " + new DateRepresentation().of(LocalDateTime.now()).serialize());
        System.out.println("ZonedDateTime: " + new DateRepresentation().of(ZonedDateTime.now()).serialize());
        System.out.println("OffsetDateTime: " + new DateRepresentation().of(OffsetDateTime.now()).serialize());
    }
    
    @Test()
    public void testIsoSerializeFromDate(){
        String str = new DateRepresentation().of(new Date()).serialize().toString();
        System.out.println(str);
        assertEquals("2020-04-23T19:47:25.991-04:00".length(), str.length());
    }

    @Test()
    public void testFromIsoString() {
        String str = "2020-04-23T11:56:29.532-04:00";
        System.out.println("Epoch: " + new DateRepresentation().of(str).toEpoch());
        System.out.println("Date: " + new DateRepresentation().of(str).toDate());
        System.out.println("LocalDate: " + new DateRepresentation().of(str).toLocalDate());
        System.out.println("LocalDateTime: " + new DateRepresentation().of(str).toLocalDateTime());
        System.out.println("ZonedDateTime: " + new DateRepresentation().of(str).toZonedDateTime());
        System.out.println("OffsetDateTime: " + new DateRepresentation().of(str).toOffsetDateTime());

        String partialStr = "2020-04-23";
        System.out.println("Epoch: " + new DateRepresentation().of(partialStr).toEpoch());
        System.out.println("Date: " + new DateRepresentation().of(partialStr).toDate());
        System.out.println("LocalDate: " + new DateRepresentation().of(partialStr).toLocalDate());
        System.out.println("LocalDateTime: " + new DateRepresentation().of(partialStr).toLocalDateTime());
        System.out.println("ZonedDateTime: " + new DateRepresentation().of(partialStr).toZonedDateTime());
        System.out.println("OffsetDateTime: " + new DateRepresentation().of(partialStr).toOffsetDateTime());
    }

    @Test()
    public void testUtcSerialize() {
        System.out.println("Epoch: " + new DateRepresentation(DateRepresentationType.UTC).of(new Date().getTime()).serialize());
        System.out.println("Date: " + new DateRepresentation(DateRepresentationType.UTC).of(new Date()).serialize());
        System.out.println("LocalDate: " + new DateRepresentation(DateRepresentationType.UTC).of(LocalDate.now()).serialize());
        System.out.println("LocalDateTime: " + new DateRepresentation(DateRepresentationType.UTC).of(LocalDateTime.now()).serialize());
        System.out.println("ZonedDateTime: " + new DateRepresentation(DateRepresentationType.UTC).of(ZonedDateTime.now()).serialize());
        System.out.println("OffsetDateTime: " + new DateRepresentation(DateRepresentationType.UTC).of(OffsetDateTime.now()).serialize());
    }

    @Test()
    public void testFromUtcString() {
        String str = "2020-04-23T15:58:29.053Z";
        System.out.println("Epoch: " + new DateRepresentation(DateRepresentationType.UTC).of(str).toEpoch());
        System.out.println("Date: " + new DateRepresentation(DateRepresentationType.UTC).of(str).toDate());
        System.out.println("LocalDate: " + new DateRepresentation(DateRepresentationType.UTC).of(str).toLocalDate());
        System.out.println("LocalDateTime: " + new DateRepresentation(DateRepresentationType.UTC).of(str).toLocalDateTime());
        System.out.println("ZonedDateTime: " + new DateRepresentation(DateRepresentationType.UTC).of(str).toZonedDateTime());
        System.out.println("OffsetDateTime: " + new DateRepresentation(DateRepresentationType.UTC).of(str).toOffsetDateTime());
    }

    @Test()
    public void testEpochSerialize() {
        System.out.println("Epoch: " + new DateRepresentation(DateRepresentationType.EPOCH).of(new Date().getTime()).serialize());
        System.out.println("Date: " + new DateRepresentation(DateRepresentationType.EPOCH).of(new Date()).toString());
        System.out.println("LocalDate: " + new DateRepresentation(DateRepresentationType.EPOCH).of(LocalDate.now()).serialize());
        System.out.println("LocalDateTime: " + new DateRepresentation(DateRepresentationType.EPOCH).of(LocalDateTime.now()).serialize());
        System.out.println("ZonedDateTime: " + new DateRepresentation(DateRepresentationType.EPOCH).of(ZonedDateTime.now()).serialize());
        System.out.println("OffsetDateTime: " + new DateRepresentation(DateRepresentationType.EPOCH).of(OffsetDateTime.now()).serialize());
        Assert.assertTrue(new DateRepresentation(DateRepresentationType.EPOCH).of(OffsetDateTime.now()).serialize() instanceof Long);
    }
    
    @Test()
    public void testFromEpochString() {
        String str = "1587614400000";
        System.out.println("Epoch: " + new DateRepresentation(DateRepresentationType.EPOCH).of(str).toEpoch());
        System.out.println("Date: " + new DateRepresentation(DateRepresentationType.EPOCH).of(str).toDate());
        System.out.println("LocalDate: " + new DateRepresentation(DateRepresentationType.EPOCH).of(str).toLocalDate());
        System.out.println("LocalDateTime: " + new DateRepresentation(DateRepresentationType.EPOCH).of(str).toLocalDateTime());
        System.out.println("ZonedDateTime: " + new DateRepresentation(DateRepresentationType.EPOCH).of(str).toZonedDateTime());
        System.out.println("OffsetDateTime: " + new DateRepresentation(DateRepresentationType.EPOCH).of(str).toOffsetDateTime());
    }

}
