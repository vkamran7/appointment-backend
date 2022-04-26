package com.appointment.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AppointmentDTO {

    @ApiModelProperty(position = 0)
    private int year;
    @ApiModelProperty(position = 1)
    private int month;
    @ApiModelProperty(position = 2)
    private int day;
    @ApiModelProperty(position = 3)
    private int hour;
    @ApiModelProperty(position = 4)
    private String service;
}
