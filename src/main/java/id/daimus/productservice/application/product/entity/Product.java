package id.daimus.productservice.application.product.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    @NotEmpty(message = "The product name is required")
    private String name;
    @NotEmpty(message = "The product description is required")
    private String description;
    @Min(value = 1, message = "The minimum stock is 1")
    private int stock;
    @Min(value = 1, message = "The minimum price is 1")
    private int price;
}
