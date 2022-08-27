package com.body.improvement.club.repository;

import com.body.improvement.club.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findUserByUserId(String userId);

    User findUserByUsername(String username);

    User findUserByFirstNameAndLastName(String firstName, String lastName);

    Collection<User> findUserByFirstNameContaining(String firstName);

    Collection<User> findUserByUsernameContaining(String username);
}
