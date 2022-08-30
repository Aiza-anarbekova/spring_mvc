package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "videos")
@Getter@Setter

public class Video {
    @Id
    @GeneratedValue(generator = "video_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "video_gen",sequenceName = "video_seq",allocationSize = 1)

    private Long id;
    @Column(name = "video_Name")
    private String videoName;
    private String link;

    public Video() {
    }

    public Video(String videoName, String link) {
        this.videoName = videoName;
        this.link = link;
    }
    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST}
            ,fetch = FetchType.EAGER)
    private Lesson lesson;
}
