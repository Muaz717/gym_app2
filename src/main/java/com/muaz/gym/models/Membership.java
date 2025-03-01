package com.muaz.gym.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "Membership")
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    @Min(1)
    @Max(200)
    @NotNull(message = "Number should not be 0")
    private int number;

    @Column(name = "recording_day")
    private LocalDateTime recordingDay;

    @ManyToOne()
    @JoinColumn(name = "person", referencedColumnName = "name")
    private Person owner;

    public Membership() {}

    public Membership(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDateTime getRecordingDay() {
        return recordingDay;
    }

    public void setRecordingDay(LocalDateTime recordingDay) {
        this.recordingDay = recordingDay;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person person) {
        this.owner = person;
    }

    @Override
    public String toString() {
        return "Membership{" +
                "id=" + id +
                ", number=" + number +
                ", recordingDay='" + recordingDay + '\'' +
                ", owner=" + owner +
                '}';
    }
}
