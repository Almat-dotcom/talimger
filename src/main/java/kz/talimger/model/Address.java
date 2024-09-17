package kz.talimger.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address extends BaseEntity{
    @Column(nullable = true, unique = true)
    private String buildingId;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = true)
    private Region region; // Область (если применимо)

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = true)
    private City city; // Город или поселок

    @ManyToOne
    @JoinColumn(name = "district_id", nullable = true)
    private District district; // Район города (если применимо)

    @ManyToOne
    @JoinColumn(name = "district_area_id", nullable = true)
    private DistrictArea districtArea; // Административная единица

    @ManyToOne
    @JoinColumn(name = "settlement_id", nullable = true)
    private Settlement settlement; // Поселок

    @Column(name = "street", nullable = true)
    private String street; // Улица

    @Column(name = "building_number", nullable = true)
    private String buildingNumber; // Номер здания

    @Column(nullable = true)
    private String addressName;
    
    private String addressComment;
}
