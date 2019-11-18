package com.kanionland.rest.webservices.restfulwebservices.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

// This class will be treated as a Spring component and managed it its context
@Component
public class UserDAOService {
   private static List<User> users = new ArrayList<>();
   private static int usersCount = 3;
   static {
      users.add(User.builder().uid(1).name("Muti").birthDate(new Date()).build());
      users.add(User.builder().uid(2).name("Stone").birthDate(new Date()).build());
      users.add(User.builder().uid(3).name("Flam").birthDate(new Date()).build());
   }

   public List<User> findAll() {
      return users;
   }

   public User save(final User user) {
      if (user.getUid() == null) {
         user.setUid(++usersCount);
      }
      users.add(user);
      return user;
   }

   public User findOne(final int id) {
      return users.stream().filter(user -> id == user.getUid()).findFirst().orElse(null);
   }

   public boolean removeUser(final int id) {
      final Predicate<User> requiredUser = user -> user.getUid() == id;
      if (users.removeIf(requiredUser)) {
         usersCount = users.size();
         return true;
      } else {
         return false;
      }
   }

}
