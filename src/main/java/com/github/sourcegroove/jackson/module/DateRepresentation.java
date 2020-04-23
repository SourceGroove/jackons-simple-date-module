package com.github.sourcegroove.jackson.module;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalField;
import java.util.Date;

import static java.time.temporal.ChronoField.*;
import static java.time.temporal.ChronoField.NANO_OF_SECOND;

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
        } else {
            this.odt = OffsetDateTime.parse(string, getFormatter());
            return this;
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
                return this.odt.format(getFormatter());
            case UTC:
                return this.odt.withOffsetSameInstant(ZoneOffset.UTC).format(getFormatter());
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

    private DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    }
    

}
