package org.example.homemade2.repository;

import org.example.homemade2.entity.Home;
import org.example.homemade2.entity.HomeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.Optional;

@Repository
public interface HomeDetailsRepository extends JpaRepository<HomeDetails, Long> {
    Optional<HomeDetails> findHomeDetailsById(long id);
    List<HomeDetails> findHomeDetailsByHome(Home home);
}
