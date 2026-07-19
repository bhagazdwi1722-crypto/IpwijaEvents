package com.example.ipwijaevents.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mahasiswa penerima notifikasi
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Event yang berkaitan dengan notifikasi
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(nullable = false)
    private String judul;

    @Column(nullable = false, length = 500)
    private String pesan;

    @Column(nullable = false)
    private LocalDateTime waktu;

    @Column(nullable = false)
    private boolean dibaca;

    public Notification() {
        this.waktu = LocalDateTime.now();
        this.dibaca = false;
    }

    // ==========================
    // Getter & Setter
    // ==========================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public LocalDateTime getWaktu() {
        return waktu;
    }

    public void setWaktu(LocalDateTime waktu) {
        this.waktu = waktu;
    }

    public boolean isDibaca() {
        return dibaca;
    }

    public void setDibaca(boolean dibaca) {
        this.dibaca = dibaca;
    }
}
