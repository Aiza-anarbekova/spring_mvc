package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(generator = "gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "company_gen",
    sequenceName = "company_seq",allocationSize = 1)
    private Long id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "located_country")
    private String locatedCountry;

    @OneToMany(fetch = FetchType.EAGER,cascade =
            {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST},mappedBy = "company")
    private List< Course> course;
}