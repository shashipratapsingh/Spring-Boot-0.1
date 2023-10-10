package covid19.model;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Author")
@Data
@NoArgsConstructor
@Setter
@Getter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "author_address")
    private String authorAddress;
    @Column(name = "author_email")
    private String authorEmail;

    @OneToMany(targetEntity = Book.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk" ,referencedColumnName ="id")
    private List<Book> books;

/*    {
        "authorName": "John Doe",
            "authorAddress": "123 Main St",
            "authorEmail": "john.doe@example.com",
            "books": [
        {

            "bookName": "Book 1",
                "typeBook": "Fiction"

        },
        {

            "bookName": "Book 2",
                "typeBook": "Non-Fiction"
        }
    ]
    }*/




}

