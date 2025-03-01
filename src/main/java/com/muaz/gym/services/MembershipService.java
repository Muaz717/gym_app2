package com.muaz.gym.services;

import com.muaz.gym.models.Membership;
import com.muaz.gym.repositories.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class MembershipService {

    private final MembershipRepository membershipRepository;
    private final PersonService personService;

    @Autowired
    public MembershipService(MembershipRepository membershipRepository, PersonService personService) {
        this.membershipRepository = membershipRepository;
        this.personService = personService;
    }

    @Transactional
    public List<Membership> findAll() {
        return membershipRepository.findAll();
    }

    @Transactional
    public Optional<Membership> findByNumber(int number) {
        Optional<Membership> membership = membershipRepository.findByNumber(number);

        return membership;
    }

    @Transactional
    public void add(Membership membership) {
        enrichMembership(membership);

        membershipRepository.save(membership);
    }

    private void enrichMembership(Membership membership) {
        membership.setOwner(personService.findByName(membership.getOwner().getName()).get());

        membership.setRecordingDay(LocalDateTime.now());
    }
}
