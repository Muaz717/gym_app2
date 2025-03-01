package com.muaz.gym.controllers;

import com.muaz.gym.dto.MembershipDTO;


import com.muaz.gym.mappers.MembershipMapper;
import com.muaz.gym.models.Membership;
import com.muaz.gym.services.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/memberships")
public class MembershipController {

    private final MembershipMapper membershipMapper;
    private final MembershipService membershipService;

    @Autowired
    public MembershipController(MembershipMapper membershipMapper, MembershipService membershipService) {
        this.membershipMapper = membershipMapper;
        this.membershipService = membershipService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody MembershipDTO membershipDTO) {

        Membership membershipToAdd = membershipMapper.toEntity(membershipDTO);

        membershipService.add(membershipToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<MembershipDTO>> getAll() {
        List<MembershipDTO> membershipDTOS = membershipService.findAll().stream()
                .map(membershipMapper::toDto)
                .toList();

        membershipDTOS.forEach(m -> m.getOwner().setMembershipDTOS(null));
        membershipDTOS.forEach(this::enrichMembershipDTO);

        return ResponseEntity.ok(membershipDTOS);
    }

    @GetMapping("/{number}")
    public ResponseEntity<Optional<MembershipDTO>> getByNumber(@PathVariable int number) {
        Optional<MembershipDTO> membershipDTO = membershipService.findByNumber(number)
                .map(membershipMapper::toDto);

        membershipDTO.ifPresent(membershipDTO1 -> membershipDTO1.getOwner().setMembershipDTOS(null));
        enrichMembershipDTO(membershipDTO.get());

        return ResponseEntity.ok(membershipDTO);
    }

    private void enrichMembershipDTO(MembershipDTO membershipDTO) {
        int daysLeft = 30 - membershipDTO.getRecordingDay().plusDays(30).compareTo(LocalDateTime.now());
        membershipDTO.setDaysLeft(daysLeft);

        String firstDay = membershipDTO.getRecordingDay().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        membershipDTO.setFirstDay(firstDay);

        String lastDay = membershipDTO.getRecordingDay().plusDays(30).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        membershipDTO.setLastDay(lastDay);
    }

}
