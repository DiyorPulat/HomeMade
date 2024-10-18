package org.example.homemade2.repository;

import org.example.homemade2.entity.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {

    default Optional<Home> findHomeByClient_id(Long client_id){
        List<Home> homes = findAll();
        return homes.stream().filter(h -> h.getClient_id().equals(client_id)).findFirst();
    }


}
