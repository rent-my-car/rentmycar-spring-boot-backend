package com.rentmycar.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentmycar.entity.User;
import com.rentmycar.entity.UserRoleEnum;

import antlr.collections.List;
import java.util.*;
public interface HostDao extends JpaRepository<User, Long> {
     Optional<java.util.List<User>> findByRoleEnum(UserRoleEnum roleEnum);
}
