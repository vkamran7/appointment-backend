package com.appointment.app;

import com.appointment.app.controller.AppointmentController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import(com.appointment.app.controller.AppointmentController.class)
public class AppointmentApplicationTests {

    @Autowired
    private AppointmentController appointmentController;

    @Test
    public void contextLoads() {
        assertThat(appointmentController).isNotNull();
    }
}
