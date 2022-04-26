package com.appointment.app.controller;

import com.appointment.app.dto.AppointmentDTO;
import com.appointment.app.dto.DayDTO;
import com.appointment.app.service.AppointmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/appointments")
@Api(tags = "appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @PostMapping
    @ApiOperation(value = "${AppointmentController.appointments}")
    public ResponseEntity<?> saveAppointment(@RequestBody AppointmentDTO dto, HttpServletRequest request) {
        return new ResponseEntity<>(appointmentService.save(dto, request), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @PostMapping("/availableHours")
    @ApiOperation(value = "${AppointmentController.availableHours}")
    public ResponseEntity<?> getAvailableHours(@RequestBody DayDTO dayDTO) {
        dayDTO.checkValidity();
        return new ResponseEntity<>(appointmentService.getAvailable(dayDTO), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAppointments")
    @ApiParam(value = "${AppointmentController.getAll}")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
//        if ((page != null || size != null) && (page > 0 && size > 0)) {
//            return new ResponseEntity<>(appointmentService.getAppointmentPage(page, size), HttpStatus.OK);
//        }
        return new ResponseEntity<>(appointmentService.getAllAppointments(), HttpStatus.OK);
    }
}
