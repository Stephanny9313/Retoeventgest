package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.repository.AuditRepository;
import com.example.eventgest.domain.repository.EventRepository;
import com.example.eventgest.domain.repository.RolRepository;
import com.example.eventgest.domain.repository.UserRepository;

public class UserServiceImpl {

    private final UserRepository userRepository;
    private final AuditRepository auditRepository;
    private final RolRepository rolRepository;
    private final EventRepository eventRepository;


    public UserServiceImpl(UserRepository userRepository, AuditRepository auditRepository, RolRepository rolRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.auditRepository = auditRepository;
        this.rolRepository = rolRepository;
        this.eventRepository = eventRepository;
    }
}
