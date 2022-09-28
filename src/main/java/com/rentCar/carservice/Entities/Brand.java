package com.rentCar.carservice.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "brands")
public class Brand implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(max = 30)
    private String name;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(
            targetEntity = Car.class,
            mappedBy = "brand",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Car> cars;
}
