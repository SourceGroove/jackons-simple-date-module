package com.github.sourcegroove.jackson.module;

import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.util.Date;

public class DateBuilder {

    private DateRepresentationType type;
    private OffsetDateTime odt;

    public DateBuilder() {
        this.type = DateRepresentationType.ISO;
    }

    public DateBuilder(DateRepresentationType type) {
        this.type = type;

    }

    public DateBuilder from(String string) {
        if(StringUtils.isBlank(string)){
            return this;
        }
        if (this.type == DateRepresentationType.EPOCH) {
            return this.from(Long.valueOf(string));
        } else {
            this.odt = OffsetDateTime.parse(string, getFormatter());
            return this;
        }
    }

    public DateBuilder from(Long date) {
        Instant i = Instant.ofEpochMilli(date);
        this.odt = i.atOffset(ZoneOffset
                .systemDefault()
                .getRules()
                .getOffset(i));
        return this;
    }

    public DateBuilder from(Date date) {
        this.odt = date.toInstant()
                .atOffset(ZoneOffset
                        .systemDefault()
                        .getRules()
                        .getOffset(date.toInstant()));
        return this;
    }

    public DateBuilder from(LocalDate date) {
        return this.from(date.atStartOfDay());
    }

    public DateBuilder from(LocalDateTime date) {
        this.odt = date.atOffset(ZoneOffset
                .systemDefault()
                .getRules()
                .getOffset(date));
        return this;
    }

    public DateBuilder from(ZonedDateTime date) {
        this.odt = date.withZoneSameInstant(ZoneOffset.systemDefault())
                .toOffsetDateTime();
        return this;
    }

    public DateBuilder from(OffsetDateTime date) {
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

    public String toString() {
        if (this.odt == null) {
            return null;
        }

        switch (this.type) {
            case ISO:
                return toIsoString();
            case UTC:
                return toUtcString();
            case EPOCH:
                return toEpochString();
            default:
                throw new IllegalArgumentException("Unsupported date type");
        }
    }


    private String toIsoString() {
        return this.odt.format(getFormatter());
    }

    private String toUtcString() {
        return this.odt
                .withOffsetSameInstant(ZoneOffset.UTC)
                .format(getFormatter());
    }

    private String toEpochString() {
        return this.toEpoch().toString();
    }

    private DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    }

    private DateTimeFormatter getForgivingFormatter() {
        return new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .optionalStart()
                .appendPattern("HH:mm:ss.SSS'Z'")
                .optionalEnd()
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
                .parseDefaulting(ChronoField.OFFSET_SECONDS, 0)
                .toFormatter()
                .withResolverStyle(ResolverStyle.SMART)
                .withZone(ZoneId.systemDefault());
    }
}
