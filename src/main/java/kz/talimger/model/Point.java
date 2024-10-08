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
@Table(name = "point")
public class Point extends BaseEntity{
    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;
}
