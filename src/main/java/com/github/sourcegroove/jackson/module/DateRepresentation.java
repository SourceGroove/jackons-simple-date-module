package com.github.sourcegroove.jackson.module;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

import static java.time.temporal.ChronoField.*;

@Slf4j
public class DateRepresentation {

    private DateRepresentationType type;
    private OffsetDateTime odt;

    public DateRepresentation() {
        this.type = DateRepresentationType.ISO;
    }

    public DateRepresentation(DateRepresentationType type) {
        this.type = type;
    }

    public DateRepresentation of(String string) {
        if (StringUtils.isBlank(string)) {
            return this;
        }
        if (this.type == DateRepresentationType.EPOCH) {
            return this.of(Long.valueOf(string));
        } else if (this.type == DateRepresentationType.UTC){
            return this.of(OffsetDateTime.parse(string, DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        } else {
            return this.of(OffsetDateTime.parse(string, getFormatter()));
        }
    }

    public DateRepresentation of(Long date) {
        Instant i = Instant.ofEpochMilli(date);
        this.odt = i.atOffset(ZoneOffset
                .systemDefault()
                .getRules()
                .getOffset(i));
        return this;
    }

    public DateRepresentation of(Date date) {
        this.odt = date.toInstant()
                .atOffset(ZoneOffset
                        .systemDefault()
                        .getRules()
                        .getOffset(date.toInstant()));
        return this;
    }

    public DateRepresentation of(LocalDate date) {
        return this.of(date.atStartOfDay());
    }

    public DateRepresentation of(LocalDateTime date) {
        this.odt = date.atOffset(ZoneOffset
                .systemDefault()
                .getRules()
                .getOffset(date));
        return this;
    }

    public DateRepresentation of(ZonedDateTime date) {
        this.odt = date.withZoneSameInstant(ZoneOffset.systemDefault())
                .toOffsetDateTime();
        return this;
    }

    public DateRepresentation of(OffsetDateTime date) {
        this.odt = date;
        return this;
    }

    public Date toDate() {
        return Date.from(this.odt.toInstant());
    }

    public LocalDate toLocalDate() {
        return this.odt.toLocalDate();
    }

    public LocalDateTime toLocalDateTime() {
        return this.odt.toLocalDateTime();
    }

    public ZonedDateTime toZonedDateTime() {
        return this.odt.toZonedDateTime();
    }

    public OffsetDateTime toOffsetDateTime() {
        return this.odt;
    }

    public Long toEpoch() {
        return this.odt.toInstant().toEpochMilli();
    }

    public Object serialize() {
        if (this.odt == null) {
            return null;
        }

        log.trace("Returning " + this.type + " formatted date string");
        switch (this.type) {
            case ISO:
                return this.odt.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            case UTC:
                return this.odt
                        .withOffsetSameInstant(ZoneOffset.UTC)
                        .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            case EPOCH:
                return this.toEpoch();
            default:
                log.error("Unsupported date representation type");
                throw new IllegalArgumentException("Unsupported date representation type");
        }
    }
    
    public String toString(){
        return this.serialize().toString();
    }
    
    protected DateTimeFormatter getFormatter(){
        return new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd['T'HH][:mm][:ss][.SSS][xxx]")
                .parseDefaulting(HOUR_OF_DAY, 1)
                .parseDefaulting(MINUTE_OF_HOUR, 40)
                .parseDefaulting(SECOND_OF_MINUTE, 55)
                .parseDefaulting(NANO_OF_SECOND, 1000000)
                .parseDefaulting(OFFSET_SECONDS, OffsetDateTime.now().getOffset().getTotalSeconds())
                .toFormatter();
    }
    

}
