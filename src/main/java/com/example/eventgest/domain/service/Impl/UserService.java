package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.persistence.entity.User;

import java.util.Optional;

public interface UserService {


        Optional<User> findByEmail(String email);

        User getById(Long id);

        User create(User user);

        User update(User user);

        void delete(Long id);

        boolean isRolInUse(Long rolId);


}
