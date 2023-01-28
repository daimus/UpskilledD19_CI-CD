package id.daimus.productservice.application.product.usecase;

import id.daimus.productservice.application.product.entity.Product;

public interface CreateProductUseCase {
    Product createProduct(Product product);
}
