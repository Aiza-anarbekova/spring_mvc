package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_gen")
    @SequenceGenerator(name = "lesson_gen", sequenceName = "lesson_seq", allocationSize = 1)
    private Long id;
    @Column(name = "lesson_name")
    private String lessonName;

    public Lesson(String lessonName) {
        this.lessonName = lessonName;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Course course;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lesson", fetch = FetchType.LAZY)
    private List<Task> tasks;

    public void addTask(Task task1){
        if (tasks==null){
            tasks=new ArrayList<>();
        }
        tasks.add(task1);
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Video video;


}
