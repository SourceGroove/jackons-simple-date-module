package com.github.sourcegroove.jackson.module;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Date;


@Slf4j
public class DateBuilderTest {

    @Test()
    public void testToIsoString(){
        log.info("Epoch: {}", new DateBuilder().from(new Date().getTime()).toString());
        log.info("Date: {}", new DateBuilder().from(new Date()).toString());
        log.info("LocalDate: {}", new DateBuilder().from(LocalDate.now()).toString());
        log.info("LocalDateTime: {}", new DateBuilder().from(LocalDateTime.now()).toString());
        log.info("ZonedDateTime: {}", new DateBuilder().from(ZonedDateTime.now()).toString());
        log.info("OffsetDateTime: {}", new DateBuilder().from(OffsetDateTime.now()).toString());
    }

    @Test()
    public void testFromIsoString(){
        String str = "2020-04-23T11:56:29.532-04:00";
        log.info("Epoch: {}", new DateBuilder().from(str).toEpoch());
        log.info("Date: {}", new DateBuilder().from(str).toDate());
        log.info("LocalDate: {}", new DateBuilder().from(str).toLocalDate());
        log.info("LocalDateTime: {}", new DateBuilder().from(str).toLocalDateTime());
        log.info("ZonedDateTime: {}", new DateBuilder().from(str).toZonedDateTime());
        log.info("OffsetDateTime: {}", new DateBuilder().from(str).toOffsetDateTime());
    }

    @Test()
    public void testToUtcString(){
        log.info("Epoch: {}", new DateBuilder(DateRepresentationType.UTC).from(new Date().getTime()).toString());
        log.info("Date: {}", new DateBuilder(DateRepresentationType.UTC).from(new Date()).toString());
        log.info("LocalDate: {}", new DateBuilder(DateRepresentationType.UTC).from(LocalDate.now()).toString());
        log.info("LocalDateTime: {}", new DateBuilder(DateRepresentationType.UTC).from(LocalDateTime.now()).toString());
        log.info("ZonedDateTime: {}", new DateBuilder(DateRepresentationType.UTC).from(ZonedDateTime.now()).toString());
        log.info("OffsetDateTime: {}", new DateBuilder(DateRepresentationType.UTC).from(OffsetDateTime.now()).toString());
    }
    
    @Test()
    public void testFromUtcString(){
        String str = "2020-04-23T15:58:29.053Z";
        log.info("Epoch: {}", new DateBuilder(DateRepresentationType.UTC).from(str).toEpoch());
        log.info("Date: {}", new DateBuilder(DateRepresentationType.UTC).from(str).toDate());
        log.info("LocalDate: {}", new DateBuilder(DateRepresentationType.UTC).from(str).toLocalDate());
        log.info("LocalDateTime: {}", new DateBuilder(DateRepresentationType.UTC).from(str).toLocalDateTime());
        log.info("ZonedDateTime: {}", new DateBuilder(DateRepresentationType.UTC).from(str).toZonedDateTime());
        log.info("OffsetDateTime: {}", new DateBuilder(DateRepresentationType.UTC).from(str).toOffsetDateTime());
    }

    @Test()
    public void testToEpochString(){
        log.info("Epoch: {}", new DateBuilder(DateRepresentationType.EPOCH).from(new Date().getTime()).toString());
        log.info("Date: {}", new DateBuilder(DateRepresentationType.EPOCH).from(new Date()).toString());
        log.info("LocalDate: {}", new DateBuilder(DateRepresentationType.EPOCH).from(LocalDate.now()).toString());
        log.info("LocalDateTime: {}", new DateBuilder(DateRepresentationType.EPOCH).from(LocalDateTime.now()).toString());
        log.info("ZonedDateTime: {}", new DateBuilder(DateRepresentationType.EPOCH).from(ZonedDateTime.now()).toString());
        log.info("OffsetDateTime: {}", new DateBuilder(DateRepresentationType.EPOCH).from(OffsetDateTime.now()).toString());
    }

    @Test()
    public void testFromEpochString(){
        String str = "1587614400000";
        log.info("Epoch: {}", new DateBuilder(DateRepresentationType.EPOCH).from(str).toEpoch());
        log.info("Date: {}", new DateBuilder(DateRepresentationType.EPOCH).from(str).toDate());
        log.info("LocalDate: {}", new DateBuilder(DateRepresentationType.EPOCH).from(str).toLocalDate());
        log.info("LocalDateTime: {}", new DateBuilder(DateRepresentationType.EPOCH).from(str).toLocalDateTime());
        log.info("ZonedDateTime: {}", new DateBuilder(DateRepresentationType.EPOCH).from(str).toZonedDateTime());
        log.info("OffsetDateTime: {}", new DateBuilder(DateRepresentationType.EPOCH).from(str).toOffsetDateTime());
    }

}
