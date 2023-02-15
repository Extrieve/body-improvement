package com.body.improvement.club.repository;

import com.body.improvement.club.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PageableUserRepo extends PagingAndSortingRepository<User, String> {

    Pageable findUserByUsername(String username, Pageable pageable);

    Pageable findUserByFirstNameAndLastName(String firstName, String lastName, Pageable pageable);
}
