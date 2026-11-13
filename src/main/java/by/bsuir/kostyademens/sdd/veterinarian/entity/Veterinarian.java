package by.bsuir.kostyademens.sdd.veterinarian.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "veterinarians")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Veterinarian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String specialization;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    public Veterinarian(String fullName, String specialization, String phoneNumber, String email) {
        this.fullName = fullName;
        this.specialization = specialization;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void update(String fullName, String specialization, String phoneNumber, String email) {
        this.fullName = fullName;
        this.specialization = specialization;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
