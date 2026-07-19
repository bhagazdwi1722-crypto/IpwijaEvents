package com.example.ipwijaevents.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ipwijaevents.entity.Notification;
import com.example.ipwijaevents.entity.User;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserOrderByWaktuDesc(User user);

    long countByUserAndDibacaFalse(User user);

}
