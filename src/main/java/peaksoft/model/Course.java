package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "coursess")
@Getter
@Setter
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(generator = "course_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "course_gen",sequenceName = "course_seq",allocationSize = 1)
    private Long id;
    @Column(name = "course_name")
    private String CourseName;
    private String duration;

    public Course(String courseName, String duration) {
        CourseName = courseName;
        this.duration = duration;
    }
    @ManyToOne
    private Company company;


}
