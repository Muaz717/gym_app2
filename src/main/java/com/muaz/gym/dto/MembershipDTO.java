package com.muaz.gym.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class MembershipDTO {

    @Min(1)
    @Max(100)
    @NotNull
    private int number;

//    @NotNull
    private PersonDTO owner;

    @JsonIgnore
    private LocalDateTime recordingDay;

    private String firstDay;

    private String lastDay;

    private int daysLeft;

    public String getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(String firstDay) {
        this.firstDay = firstDay;
    }

    public String getLastDay() {
        return lastDay;
    }

    public void setLastDay(String lastDay) {
        this.lastDay = lastDay;
    }

    public LocalDateTime getRecordingDay() {
        return recordingDay;
    }

    public void setRecordingDay(LocalDateTime recordingDay) {
        this.recordingDay = recordingDay;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public PersonDTO getOwner() {
        return owner;
    }

    public void setOwner(PersonDTO owner) {
        this.owner = owner;
    }

}
