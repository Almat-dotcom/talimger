package kz.talimger.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kz.talimger.enums.CourseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student extends User {
    private CourseEnum course;

    @ManyToOne(fetch = FetchType.EAGER)
    private Specialization specialization;

    @ManyToOne(fetch = FetchType.EAGER)
    private Teacher teacher;
}
