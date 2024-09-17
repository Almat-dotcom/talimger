package kz.talimger.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "district")
public class District extends BaseEntity{
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = true)
    private City city; // Связь с таблицей City

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = true)
    private Country country; // Связь с таблицей City

    @Column(name = "location_id", nullable = false, unique = true)
    private Long locationId;
}
