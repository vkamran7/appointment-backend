package com.appointment.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DateTimeException;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DayDTO {
    @JsonIgnore
    private LocalDate date;

    @ApiModelProperty(position = 0)
    private int year;
    @ApiModelProperty(position = 1)
    private int month;
    @ApiModelProperty(position = 2)
    private int day;

    public void checkValidity() {
        try {
            this.date = LocalDate.of(this.year, this.month, this.day);
        } catch (DateTimeException ex) {
            throw new DateTimeException(ex.getMessage());
        }
    }
}
