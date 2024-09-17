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
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class EducationalInstitution extends BaseEntity {

    private String name;

    private String legalName;

    private String shortName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "institution_rubric",
            joinColumns = @JoinColumn(name = "institution_id"),
            inverseJoinColumns = @JoinColumn(name = "rubric_id")
    )
    private List<Rubric> rubrics;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = true)
    private City city;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = true)
    private Region region;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "point_id", referencedColumnName = "id")
    private Point point;

}
