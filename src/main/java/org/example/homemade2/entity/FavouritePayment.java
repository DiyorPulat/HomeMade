package org.example.homemade2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "favouritePayment")
public class FavouritePayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private HomeDetails homeDetails;
    @Column(name = "amount")
    private Long amount;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavouritePayment that = (FavouritePayment) o;
        return  Objects.equals(homeDetails, that.homeDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, homeDetails, amount);
    }
}
