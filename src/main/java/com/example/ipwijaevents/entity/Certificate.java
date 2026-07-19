package com.example.ipwijaevents.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "certificates")
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Satu pendaftaran menghasilkan satu sertifikat
    @OneToOne
    @JoinColumn(name = "registration_id", nullable = false, unique = true)
    private Registration registration;

    // Nomor sertifikat
    @Column(nullable = false, unique = true)
    private String nomorSertifikat;

    // Tanggal sertifikat diterbitkan
    @Column(nullable = false)
    private LocalDate tanggalTerbit;

    // Nama file PDF sertifikat
    private String fileCertificate;

    // Apakah sertifikat sudah tersedia
    @Column(nullable = false)
    private boolean tersedia = false;

    // =========================
    // Constructor
    // =========================
    public Certificate() {
    }

    // =========================
    // Getter & Setter
    // =========================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public String getNomorSertifikat() {
        return nomorSertifikat;
    }

    public void setNomorSertifikat(String nomorSertifikat) {
        this.nomorSertifikat = nomorSertifikat;
    }

    public LocalDate getTanggalTerbit() {
        return tanggalTerbit;
    }

    public void setTanggalTerbit(LocalDate tanggalTerbit) {
        this.tanggalTerbit = tanggalTerbit;
    }

    public String getFileCertificate() {
        return fileCertificate;
    }

    public void setFileCertificate(String fileCertificate) {
        this.fileCertificate = fileCertificate;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }

    // =========================
    // Helper Method
    // =========================
    public User getUser() {
        return registration != null ? registration.getUser() : null;
    }

    public Event getEvent() {
        return registration != null ? registration.getEvent() : null;
    }

}
