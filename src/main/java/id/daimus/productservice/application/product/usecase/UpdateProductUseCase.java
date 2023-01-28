package id.daimus.productservice.application.product.usecase;

import id.daimus.productservice.application.product.entity.Product;
import id.daimus.productservice.shared.exception.DataNotFoundException;

public interface UpdateProductUseCase {
    Product updateProduct(Long id, Product product) throws DataNotFoundException;
}
