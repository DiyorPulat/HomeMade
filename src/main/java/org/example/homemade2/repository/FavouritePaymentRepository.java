package org.example.homemade2.repository;

import org.example.homemade2.entity.FavouritePayment;
import org.example.homemade2.entity.HomeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouritePaymentRepository extends JpaRepository<FavouritePayment,Long> {
    List<FavouritePayment> findFavouritePaymentByHomeDetails(HomeDetails homeDetails);
}
