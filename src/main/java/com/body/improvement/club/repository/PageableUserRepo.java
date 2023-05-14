package com.body.improvement.club.repository;

import com.body.improvement.club.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PageableUserRepo extends PagingAndSortingRepository<User, String> {

    Page<User> findByUsernameContaining(String username, Pageable pageable);

    Page<User> findByFirstNameContaining(String firstName, Pageable pageable);

    Page<User> findByLastNameContaining(String lastName, Pageable pageable);

}
