package com.tata.customerservice.model;

import com.tata.customerservice.types.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * Entity class for Person.
 *
 * <br>Requirement:</br>
 * nombre, genero, edad, identificación, dirección, teléfono
 * Debe manera su clave primaria (PK)
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "person")
public class PersonEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 5514356794362801434L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long personId;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer age;

    @Column(unique = true)
    private String identification;

    private String address;

    private String phone;
}
