package com.github.sourcegroove.jackson.module;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.TimeZone;

import static java.time.temporal.ChronoField.*;

@Slf4j
public class DateRepresentation {
    private DateRepresentationType type;
    private Instant instant;
    private TimeZone instantTimeZone;
    private TimeZone configuredTimeZone;

    public DateRepresentation(DateRepresentationType type) {
        this.type = type;
        this.configuredTimeZone = type == DateRepresentationType.UTC ? TimeZone.getTimeZone("UTC") : null;
    }

    public DateRepresentation of(String string) {
        if (StringUtils.isBlank(string)) {
            return this;
        } else if (this.type == DateRepresentationType.EPOCH) {
            return ofEpochString(string);
        } else if (this.type == DateRepresentationType.UTC) {
            return of(OffsetDateTime.parse(string, getIsoFormatter()));
        } else {
            return of(OffsetDateTime.parse(string, getIsoFormatter()));
        }
    }
    private DateRepresentation ofEpochString(String string){
        if(StringUtils.isNumeric(string)){
            return of(Long.valueOf(string));
        } else {
            return of(OffsetDateTime.parse(string, getIsoFormatter()));
        }
    }
    public DateRepresentation of(Long date) {
        this.instant = Instant.ofEpochMilli(date);
        return this;
    }
    public DateRepresentation of(Date date) {
        this.instant = date.toInstant();
        return this;
    }
    public DateRepresentation of(LocalDate date) {
        return of(date.atStartOfDay());
    }
    public DateRepresentation of(LocalDateTime date) {
        ZoneOffset offset = getTimeZone().toZoneId().getRules().getOffset(date);
        this.instant = date.toInstant(offset);
        return this;
    }


    public DateRepresentation of(ZonedDateTime date) {
        this.instantTimeZone = TimeZone.getTimeZone(date.getZone());
        this.instant = Instant.from(date);
        return this;
    }
    public DateRepresentation of(OffsetDateTime date) {
        this.instantTimeZone = TimeZone.getTimeZone(date.getOffset().normalized());
        this.instant = Instant.from(date);
        return this;
    }

    public LocalDate toLocalDate() {
        return getInstant() == null ? null : toLocalDateTime().toLocalDate();
    }

    public LocalDateTime toLocalDateTime() {
        return getInstant() == null ? null : LocalDateTime.ofInstant(getInstant(), getTimeZone().toZoneId());
    }

    public ZonedDateTime toZonedDateTime() {
        return getInstant() == null ? null : ZonedDateTime.ofInstant(getInstant(), getTimeZone().toZoneId());
    }

    public OffsetDateTime toOffsetDateTime() {
        return getInstant() == null ? null : OffsetDateTime.ofInstant(getInstant(), getTimeZone().toZoneId());
    }

    public Date toDate() {
        return getInstant() != null ? Date.from(getInstant()) : null;
    }

    public Long toEpoch() {
        return getInstant() != null ? getInstant().toEpochMilli() : null;
    }

    public String toString() {
        return this.serialize().toString();
    }

    public Object serialize() {
        if (this.instant == null) {
            return null;
        }

        switch (this.type) {
            case ISO:
                return toOffsetDateTime().format(getIsoFormatter());
            case UTC:
                return toOffsetDateTime().format(getUtcFormatter());
            case EPOCH:
                return this.toEpoch();
            default:
                log.error("Unsupported date representation type");
                return new IllegalArgumentException("Unsupported date representation type");
        }
    }

    protected DateTimeFormatter getUtcFormatter(){
        return  DateTimeFormatter.ISO_INSTANT;
    }
    protected DateTimeFormatter getIsoFormatter(){
        int defaultOffset = getTimeZone().toZoneId().getRules().getOffset(Instant.now()).getTotalSeconds();
        return new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd['T'HH][:mm][:ss]")
                .optionalStart()
                .appendFraction(ChronoField.NANO_OF_SECOND, 1, 9, true)//[.SSS]
                .optionalEnd()
                .optionalStart()
                .appendOffset("+HH:MM", "Z")
                .optionalEnd()
                .parseDefaulting(HOUR_OF_DAY, 0)
                .parseDefaulting(MINUTE_OF_HOUR, 0)
                .parseDefaulting(SECOND_OF_MINUTE, 0)
                .parseDefaulting(NANO_OF_SECOND, 0)
                .parseDefaulting(OFFSET_SECONDS, defaultOffset)
                .toFormatter();
    }

    private TimeZone getTimeZone(){

        if(this.configuredTimeZone != null){
            return this.configuredTimeZone;

        } else if(this.instantTimeZone != null){
            return this.instantTimeZone;

        } else if(this.type == DateRepresentationType.UTC){
            return TimeZone.getTimeZone("UTC");

        } else {
            return TimeZone.getDefault();
        }

    }
    private Instant getInstant(){
        return this.instant;
    }

}
