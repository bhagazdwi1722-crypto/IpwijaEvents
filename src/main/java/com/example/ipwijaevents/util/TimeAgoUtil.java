package com.example.ipwijaevents.util;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeAgoUtil {

    public static String timeAgo(LocalDateTime waktu) {

        if (waktu == null) {
            return "-";
        }

        Duration duration = Duration.between(waktu, LocalDateTime.now());

        long seconds = duration.getSeconds();

        if (seconds < 60) {
            return "Baru saja";
        }

        long minutes = seconds / 60;

        if (minutes < 60) {
            return minutes + " menit yang lalu";
        }

        long hours = minutes / 60;

        if (hours < 24) {
            return hours + " jam yang lalu";
        }

        long days = hours / 24;

        if (days == 1) {
            return "Kemarin";
        }

        if (days < 30) {
            return days + " hari yang lalu";
        }

        long months = days / 30;

        if (months < 12) {
            return months + " bulan yang lalu";
        }

        long years = months / 12;

        return years + " tahun yang lalu";
    }

}
