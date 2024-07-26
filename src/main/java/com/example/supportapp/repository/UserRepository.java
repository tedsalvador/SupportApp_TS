/* package com.example.supportapp.repository;

import com.example.supportapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndUserPassword(String username, String userPassword);
}
 */
package com.example.supportapp.repository;

import com.example.supportapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndUserPassword(String username, String userPassword);
}

