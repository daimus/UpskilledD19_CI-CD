package id.daimus.productservice.infrastructure.data.jpa.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name", unique = true)
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="stock")
    private int stock;
    @Column(name="price")
    private int price;
}
