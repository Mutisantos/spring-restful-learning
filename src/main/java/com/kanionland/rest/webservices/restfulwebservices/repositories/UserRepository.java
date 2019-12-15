package com.kanionland.rest.webservices.restfulwebservices.repositories;

import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.kanionland.rest.webservices.restfulwebservices.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
   @Modifying
   @Transactional
   void deleteById(int id) throws EmptyResultDataAccessException;

}
