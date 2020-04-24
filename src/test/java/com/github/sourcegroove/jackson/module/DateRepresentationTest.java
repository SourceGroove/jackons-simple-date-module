package com.github.sourcegroove.jackson.module;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

import static org.junit.Assert.assertNotNull;


public class DateRepresentationTest {

    @Test()
    public void testOfIsoString(){
        assertOfString(DateRepresentationType.ISO, "2020-04-23");
        assertOfString(DateRepresentationType.ISO, "2020-04-23T11:56:29");
        assertOfString(DateRepresentationType.ISO, "2020-04-23T11:56:29.1-04:00");
        assertOfString(DateRepresentationType.ISO, "2020-04-23T11:56:29.532-04:00");
        assertOfString(DateRepresentationType.ISO, "2020-04-23T11:56:29-04:00");
        assertOfString(DateRepresentationType.ISO, "2020-04-23T11:56:29.3Z");
        assertOfString(DateRepresentationType.ISO, "2020-04-23T11:56:29.123Z");
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

    private void assertOfString(DateRepresentationType type, String str){
        DateRepresentation rep = new DateRepresentation(type).of(str);
        assertNotNull(rep.getFormatter().parse(str));
        assertNotNull(rep);
        assertNotNull(rep.toDate());
        assertNotNull(rep.toLocalDate());
        assertNotNull(rep.toLocalDateTime());
        assertNotNull(rep.toZonedDateTime());
        assertNotNull(rep.toOffsetDateTime());
        assertNotNull(rep.toEpoch());
        assertNotNull(rep.serialize());
    }

}
