package com.muaz.gym.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;


public class PersonDTO {

    @NotEmpty
    @Size(min = 5, max = 100, message = "Name should be between 5 and 100 symbols")
    private String name;

    private List<MembershipDTO> membershipDTOS;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MembershipDTO> getMembershipDTOS() {
        return membershipDTOS;
    }

    public void setMembershipDTOS(List<MembershipDTO> membershipDTOS) {
        this.membershipDTOS = membershipDTOS;
    }
}
