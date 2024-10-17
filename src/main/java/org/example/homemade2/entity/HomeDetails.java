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
@Table(name = "home_details")
public class HomeDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "home_id")
    private Home home;

    @Column(name = "homeImg")
    private String homeImg;

    @Column(name = "type")
    private String type;

    @Column(name = "code")
    private String code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HomeDetails that = (HomeDetails) o;
        return  Objects.equals(home, that.home) && Objects.equals(homeImg, that.homeImg) && Objects.equals(type, that.type) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, home, homeImg, type, code);
    }
}
