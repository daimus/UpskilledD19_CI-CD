package id.daimus.productservice.application.product.usecase;

import id.daimus.productservice.application.product.entity.Product;
import id.daimus.productservice.shared.exception.DataNotFoundException;

public interface GetProductByIdUseCase {
    Product getProductById(Long id) throws DataNotFoundException;
}
