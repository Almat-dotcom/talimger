package kz.talimger.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rubric")
public class Rubric extends BaseEntity{
    @Column(nullable = false, unique = true)
    private String locationId;

    @Column(nullable = false, unique = true)
    private String alias;

    @Column(nullable = false)
    private String name;
}
