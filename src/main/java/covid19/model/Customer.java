package covid19.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Customer")
@Data
@NoArgsConstructor
@Setter
@Getter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String customerName;
    private String customerAddress;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customer_product",joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;


}


/*
{
       "customerName": "John Doe",
        "customerAddress": "123 Main St",
        "products": [
        {
        "productName": "Book 1",
        "productType": "Fiction"
        },
        {
        "productName": "Book 2",
        "productType": "Non-Fiction"
        }
        ]
  }
*/
