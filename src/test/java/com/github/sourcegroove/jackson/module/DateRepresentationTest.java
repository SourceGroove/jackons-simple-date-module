package com.github.sourcegroove.jackson.module;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@Slf4j
public class DateRepresentationTest {

    @Test
    public void givenUtcStringWhenParseThenParsed(){
        LocalDate localDate = new DateRepresentation(DateRepresentationType.UTC)
                .of("2020-05-08T16:00:00Z")
                .toLocalDate();
        assertEquals(LocalDate.of(2020,5,8), localDate);
    }
    @Test
    public void givenLocalDateWhenSerializeThenUtc(){
        Object str = new DateRepresentation(DateRepresentationType.UTC)
                .of(LocalDate.of(2020,5,8))
                .serialize();
        assertEquals("2020-05-08T00:00:00Z", str.toString());

    }
    @Test()
    public void testNullPointers(){
        new DateRepresentation(DateRepresentationType.ISO).of("").toDate();
        new DateRepresentation(DateRepresentationType.ISO).of("").toLocalDate();
        new DateRepresentation(DateRepresentationType.ISO).of("").toLocalDateTime();
        new DateRepresentation(DateRepresentationType.ISO).of("").toOffsetDateTime();
        new DateRepresentation(DateRepresentationType.UTC).of("").toDate();
        new DateRepresentation(DateRepresentationType.UTC).of("").toLocalDate();
        new DateRepresentation(DateRepresentationType.UTC).of("").toLocalDateTime();
        new DateRepresentation(DateRepresentationType.UTC).of("").toOffsetDateTime();
        new DateRepresentation(DateRepresentationType.EPOCH).of("").toDate();
        new DateRepresentation(DateRepresentationType.EPOCH).of("").toLocalDate();
        new DateRepresentation(DateRepresentationType.EPOCH).of("").toLocalDateTime();
        new DateRepresentation(DateRepresentationType.EPOCH).of("").toOffsetDateTime();
    }

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
        assertNotNull(new DateRepresentation(DateRepresentationType.ISO).of("2020-04-23"));
        assertNotNull(new DateRepresentation(DateRepresentationType.ISO).of("2020-04-23T11:56:29"));
        assertNotNull(new DateRepresentation(DateRepresentationType.ISO).of("2020-04-23T11:56:29.532-04:00"));
        assertNotNull(new DateRepresentation(DateRepresentationType.ISO).of("2020-04-23T11:56:29-04:00"));
    }

    @Test
    public void testParsePartialUtcFormats(){
        assertNotNull(new DateRepresentation(DateRepresentationType.UTC).of("2020-04-23T15:58:29.053Z"));
        //assertNotNull(new DateRepresentation(DateRepresentationType.UTC).of("2020-04-23T11:56:29"));
    }
    @Test()
    public void testIsoSerialize() {
        System.out.println("Epoch: " + new DateRepresentation(DateRepresentationType.ISO).of(new Date().getTime()).serialize());
        System.out.println("Date: " + new DateRepresentation(DateRepresentationType.ISO).of(new Date()).serialize());
        System.out.println("LocalDate: " + new DateRepresentation(DateRepresentationType.ISO).of(LocalDate.now()).serialize());
        System.out.println("LocalDateTime: " + new DateRepresentation(DateRepresentationType.ISO).of(LocalDateTime.now()).serialize());
        System.out.println("ZonedDateTime: " + new DateRepresentation(DateRepresentationType.ISO).of(ZonedDateTime.now()).serialize());
        System.out.println("OffsetDateTime: " + new DateRepresentation(DateRepresentationType.ISO).of(OffsetDateTime.now()).serialize());
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

        String utcString = "2020-04-23T15:58:29.053Z";
        System.out.println("Epoch: " + new DateRepresentation(DateRepresentationType.EPOCH).of(utcString).toEpoch());
        System.out.println("Date: " + new DateRepresentation(DateRepresentationType.EPOCH).of(utcString).toDate());
        System.out.println("LocalDate: " + new DateRepresentation(DateRepresentationType.EPOCH).of(utcString).toLocalDate());
        System.out.println("LocalDateTime: " + new DateRepresentation(DateRepresentationType.EPOCH).of(utcString).toLocalDateTime());
        System.out.println("ZonedDateTime: " + new DateRepresentation(DateRepresentationType.EPOCH).of(utcString).toZonedDateTime());
        System.out.println("OffsetDateTime: " + new DateRepresentation(DateRepresentationType.EPOCH).of(utcString).toOffsetDateTime());
    }

    private void assertOfString(DateRepresentationType type, String str){
        DateRepresentation rep = new DateRepresentation(type).of(str);
        assertNotNull(rep.getIsoFormatter().parse(str));
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
