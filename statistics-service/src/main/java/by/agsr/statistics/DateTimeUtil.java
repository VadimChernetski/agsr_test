package by.agsr.statistics;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.ZoneId;

@UtilityClass
public class DateTimeUtil {

    private final static ZoneId ZONE_ID = ZoneId.of("Europe/Minsk");

    public static LocalDateTime getDateTime() {
        return LocalDateTime.now(ZONE_ID);
    }

}
