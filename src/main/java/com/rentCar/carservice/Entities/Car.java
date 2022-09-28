package com.rentCar.carservice.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "cars")
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String model;

    @NotNull
    @Size(max = 12)
    private String plateNumber;

    @NotNull
    private Double dayCost;

    private Double mileage;

    @NotNull
    private String image;

    @NotNull
    @Size(max = 50)
    private String type;

    @NotNull
    private String description;

    @NotNull
    private Integer state;

    private Double rating;

    private Long ownerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "brand_id",
            referencedColumnName = "id",
            nullable = false
    )
    @JsonIgnore
    private Brand brand;

}
