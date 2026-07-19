package com.example.ipwijaevents.controller.api;

public class EventCalendarDTO {

    private Long id;
    private String title;
    private String start;
    private String lokasi;

    public EventCalendarDTO(Long id, String title, String start, String lokasi) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.lokasi = lokasi;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStart() {
        return start;
    }

    public String getLokasi() {
        return lokasi;
    }
}
