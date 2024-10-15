package org.example.homemade2.repository;

import org.example.homemade2.entity.FavouritePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouritePaymentRepository extends JpaRepository<FavouritePayment,Long> {


}
