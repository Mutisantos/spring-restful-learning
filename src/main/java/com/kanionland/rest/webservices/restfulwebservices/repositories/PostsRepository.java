package com.kanionland.rest.webservices.restfulwebservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kanionland.rest.webservices.restfulwebservices.entities.Post;

@Repository
public interface PostsRepository extends JpaRepository<Post, Integer> {

}
