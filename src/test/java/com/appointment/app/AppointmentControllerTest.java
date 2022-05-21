package com.appointment.app;

import com.appointment.app.controller.AppointmentController;
import com.appointment.app.domain.Appointment;
import com.appointment.app.service.AppointmentService;
import org.assertj.core.util.Lists;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AppointmentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles(value = "test")
public class AppointmentControllerTest {

    @MockBean
    private AppointmentService appointmentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindAll() throws Exception {
        List<Appointment> appointmentList = createAppointmentList();

        Mockito.when(appointmentService.getAllAppointments()).thenReturn(appointmentList);

        mockMvc.perform(get("/api/appointments/getAppointments").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].username", is("Kamran")))
                .andExpect(jsonPath("$.[1].username", is("John")));

    }

    private static List<Appointment> createAppointmentList() {
        Appointment appointment1 = new Appointment();
        appointment1.setId(1L);
        appointment1.setUsername("Kamran");
        appointment1.setService("Service A");
        appointment1.setDay(LocalDate.of(2020, 10, 5));
        appointment1.setHour(LocalTime.of(15, 0));

        Appointment appointment2 = new Appointment();
        appointment2.setId(2L);
        appointment2.setUsername("John");
        appointment2.setService("Service B");
        appointment2.setDay(LocalDate.of(2021, 5, 3));
        appointment2.setHour(LocalTime.of(11,0));

        return Lists.list(appointment1, appointment2);
    }
}
