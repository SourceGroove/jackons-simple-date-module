package com.github.sourcegroove.jackson.module;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Date;


public class DateBuilderTest {

    @Test()
    public void testToIsoString() {
        System.out.println("Epoch: " + new DateBuilder().from(new Date().getTime()).toString());
        System.out.println("Date: " + new DateBuilder().from(new Date()).toString());
        System.out.println("LocalDate: " + new DateBuilder().from(LocalDate.now()).toString());
        System.out.println("LocalDateTime: " + new DateBuilder().from(LocalDateTime.now()).toString());
        System.out.println("ZonedDateTime: " + new DateBuilder().from(ZonedDateTime.now()).toString());
        System.out.println("OffsetDateTime: " + new DateBuilder().from(OffsetDateTime.now()).toString());
    }

    @Test()
    public void testFromIsoString() {
        String str = "2020-04-23T11:56:29.532-04:00";
        System.out.println("Epoch: " + new DateBuilder().from(str).toEpoch());
        System.out.println("Date: " + new DateBuilder().from(str).toDate());
        System.out.println("LocalDate: " + new DateBuilder().from(str).toLocalDate());
        System.out.println("LocalDateTime: " + new DateBuilder().from(str).toLocalDateTime());
        System.out.println("ZonedDateTime: " + new DateBuilder().from(str).toZonedDateTime());
        System.out.println("OffsetDateTime: " + new DateBuilder().from(str).toOffsetDateTime());
    }

    @Test()
    public void testToUtcString() {
        System.out.println("Epoch: " + new DateBuilder(DateRepresentationType.UTC).from(new Date().getTime()).toString());
        System.out.println("Date: " + new DateBuilder(DateRepresentationType.UTC).from(new Date()).toString());
        System.out.println("LocalDate: " + new DateBuilder(DateRepresentationType.UTC).from(LocalDate.now()).toString());
        System.out.println("LocalDateTime: " + new DateBuilder(DateRepresentationType.UTC).from(LocalDateTime.now()).toString());
        System.out.println("ZonedDateTime: " + new DateBuilder(DateRepresentationType.UTC).from(ZonedDateTime.now()).toString());
        System.out.println("OffsetDateTime: " + new DateBuilder(DateRepresentationType.UTC).from(OffsetDateTime.now()).toString());
    }

    @Test()
    public void testFromUtcString() {
        String str = "2020-04-23T15:58:29.053Z";
        System.out.println("Epoch: " + new DateBuilder(DateRepresentationType.UTC).from(str).toEpoch());
        System.out.println("Date: " + new DateBuilder(DateRepresentationType.UTC).from(str).toDate());
        System.out.println("LocalDate: " + new DateBuilder(DateRepresentationType.UTC).from(str).toLocalDate());
        System.out.println("LocalDateTime: " + new DateBuilder(DateRepresentationType.UTC).from(str).toLocalDateTime());
        System.out.println("ZonedDateTime: " + new DateBuilder(DateRepresentationType.UTC).from(str).toZonedDateTime());
        System.out.println("OffsetDateTime: " + new DateBuilder(DateRepresentationType.UTC).from(str).toOffsetDateTime());
    }

    @Test()
    public void testToEpochString() {
        System.out.println("Epoch: " + new DateBuilder(DateRepresentationType.EPOCH).from(new Date().getTime()).toString());
        System.out.println("Date: " + new DateBuilder(DateRepresentationType.EPOCH).from(new Date()).toString());
        System.out.println("LocalDate: " + new DateBuilder(DateRepresentationType.EPOCH).from(LocalDate.now()).toString());
        System.out.println("LocalDateTime: " + new DateBuilder(DateRepresentationType.EPOCH).from(LocalDateTime.now()).toString());
        System.out.println("ZonedDateTime: " + new DateBuilder(DateRepresentationType.EPOCH).from(ZonedDateTime.now()).toString());
        System.out.println("OffsetDateTime: " + new DateBuilder(DateRepresentationType.EPOCH).from(OffsetDateTime.now()).toString());
    }

    @Test()
    public void testFromEpochString() {
        String str = "1587614400000";
        System.out.println("Epoch: " + new DateBuilder(DateRepresentationType.EPOCH).from(str).toEpoch());
        System.out.println("Date: " + new DateBuilder(DateRepresentationType.EPOCH).from(str).toDate());
        System.out.println("LocalDate: " + new DateBuilder(DateRepresentationType.EPOCH).from(str).toLocalDate());
        System.out.println("LocalDateTime: " + new DateBuilder(DateRepresentationType.EPOCH).from(str).toLocalDateTime());
        System.out.println("ZonedDateTime: " + new DateBuilder(DateRepresentationType.EPOCH).from(str).toZonedDateTime());
        System.out.println("OffsetDateTime: " + new DateBuilder(DateRepresentationType.EPOCH).from(str).toOffsetDateTime());
    }

}
