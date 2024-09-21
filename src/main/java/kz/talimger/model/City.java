package kz.talimger.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "city")
@FieldNameConstants
public class City extends BaseEntity {
    @Column(nullable = false, unique = true)
    private Long locationId;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(name = "is_region_center")
    private Boolean isRegionCenter;
}
