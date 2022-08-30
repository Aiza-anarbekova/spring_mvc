package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(generator = "company_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "company_seq",
            sequenceName = "company_seq", allocationSize = 1)
    private Long id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "located_country")
    private String locatedCountry;

    @OneToMany(cascade =
            {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "company")
    private List<Course> course;

    public void addCourse(Course course1) {
        if (course == null) {
            course = new ArrayList<>();
        }
        course.add(course1);
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
    private List<Student> students;

    public void addStudent(Student student1) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student1);
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<Instructor> instructors;

    public void addInstructor(Instructor instructor1) {
        if (instructors == null) {
            instructors = new ArrayList<>();
        }
        instructors.add(instructor1);
    }
}
