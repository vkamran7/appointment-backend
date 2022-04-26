package com.appointment.app.service;

import com.appointment.app.domain.Appointment;
import com.appointment.app.domain.WorkingHours;
import com.appointment.app.dto.AppointmentDTO;
import com.appointment.app.dto.DayDTO;
import com.appointment.app.exception.CustomException;
import com.appointment.app.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public Appointment save(AppointmentDTO dto, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Appointment appointment = new Appointment();

        LocalDate localDate = LocalDate.of(dto.getYear(), dto.getMonth(), dto.getDay());
        LocalTime localTime = LocalTime.of(dto.getHour(), 0);

        if (appointmentRepository.existsByDayAndHour(localDate, localTime)) {
            throw new CustomException("Appointment already exists with the same date", HttpStatus.BAD_REQUEST);
        }

        // get username of a currently logged in user from Security Context
        appointment.setUsername(request.getUserPrincipal().getName());
        appointment.setDay(localDate);
        appointment.setHour(localTime);
        appointment.setService(dto.getService());
        return appointmentRepository.save(appointment);
    }

    public List<LocalTime> getAvailable(DayDTO dayDTO) {
        LocalDate localDate = LocalDate.of(dayDTO.getYear(), dayDTO.getMonth(), dayDTO.getDay());
        List<Appointment> list = appointmentRepository.findAllByDay(localDate);
        List<LocalTime> hourList = new ArrayList<>();
        for (Appointment appointment : list) {
            hourList.add(appointment.getHour());
        }

        List<LocalTime> availableHours = WorkingHours.getHours();
        availableHours.removeAll(hourList);
        return availableHours;
    }

    public List<Appointment> getAppointmentPage(int page, int size) {
        Page<Appointment> appointmentPage = appointmentRepository.findAll(PageRequest.of(page, size));
        return appointmentPage.get().collect(Collectors.toList());
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}
