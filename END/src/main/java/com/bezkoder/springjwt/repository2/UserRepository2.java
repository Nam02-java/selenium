package com.bezkoder.springjwt.repository2;

import com.bezkoder.springjwt.models2.User2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository2 extends JpaRepository<User2, Long> {

    Optional<User2> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
