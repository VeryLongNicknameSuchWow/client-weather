package pl.rynbou.weather;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class Datetime {
    private Timestamp timestamp;

    public void setSystemTime() {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.systemDefault());
        ZonedDateTime withTimezone = now.atZone(ZoneId.systemDefault());
        this.timestamp = Timestamp.valueOf(withTimezone.toLocalDateTime());
    }

    public Datetime() {
        setSystemTime();
    }

    public Datetime(Timestamp date, String timezone) {
        LocalDateTime withoutTimezone = date.toLocalDateTime();
        ZonedDateTime withTimezone = withoutTimezone.atZone(ZoneId.of(timezone));
        this.timestamp = Timestamp.valueOf(withTimezone.withZoneSameInstant(ZoneId.of("GMT")).toLocalDateTime());
    }

    public Datetime(Timestamp date, ZoneId id) {
        LocalDateTime withoutTimezone = date.toLocalDateTime();
        ZonedDateTime withTimezone = withoutTimezone.atZone(id);
        this.timestamp = Timestamp.valueOf(withTimezone.withZoneSameInstant(ZoneId.of("GMT")).toLocalDateTime());
    }

    public Datetime(LocalDateTime date, String timezone) {
        ZonedDateTime withTimezone = date.atZone(ZoneId.of(timezone));
        this.timestamp = Timestamp.valueOf(withTimezone.withZoneSameInstant(ZoneId.of("GMT")).toLocalDateTime());
    }

    public Datetime(LocalDateTime date, ZoneId id) {
        ZonedDateTime withTimezone = date.atZone(id);
        this.timestamp = Timestamp.valueOf(withTimezone.withZoneSameInstant(ZoneId.of("GMT")).toLocalDateTime());
    }

    public Timestamp getDate(String timezone) {
        LocalDateTime gmt = this.timestamp.toLocalDateTime();
        ZonedDateTime withTimezone = gmt.atZone(ZoneId.of("GMT"));
        return Timestamp.valueOf(withTimezone.withZoneSameInstant(ZoneId.of(timezone)).toLocalDateTime());
    }

    public Timestamp getDate(ZoneId id) {
        LocalDateTime gmt = this.timestamp.toLocalDateTime();
        ZonedDateTime withTimezone = gmt.atZone(ZoneId.of("GMT"));
        return Timestamp.valueOf(withTimezone.withZoneSameInstant(id).toLocalDateTime());
    }
}
