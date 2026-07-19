package com.example.ipwijaevents.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ipwijaevents.entity.Certificate;
import com.example.ipwijaevents.entity.User;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

    List<Certificate> findByRegistration_User(User user);

    boolean existsByRegistrationId(Long registrationId);

}
