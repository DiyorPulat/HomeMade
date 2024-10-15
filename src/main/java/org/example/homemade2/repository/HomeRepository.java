package org.example.homemade2.repository;

import org.example.homemade2.entity.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {

    Optional<Home> findHomeByClient_id(Long client_id);
    @Override
    Optional<Home> findById(Long id);
}
