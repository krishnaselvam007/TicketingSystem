package com.ticket.system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticket.system.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

}
