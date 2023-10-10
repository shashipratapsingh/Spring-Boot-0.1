package covid19.model;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Book")
@Data
@NoArgsConstructor
@Setter
@Getter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "type_book")
    private String typeBook;


}
