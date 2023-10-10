package covid19.model;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "student_gfg_detail")
public class StudentGfgDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "college")
    private String college;

    @Column(name = "no_of_problems_solved")
    private String noOfProblemsSolved;

    @OneToOne(mappedBy = "studentGfgDetail",cascade = CascadeType.ALL)
    private Student student;

}