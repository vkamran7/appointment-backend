package com.appointment.app.repository;

import com.appointment.app.domain.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByDayAndHour(LocalDate day, LocalTime hour);

    List<Appointment> findAllByDay(LocalDate day);

    @Override
    Page<Appointment> findAll(Pageable pageable);
}
