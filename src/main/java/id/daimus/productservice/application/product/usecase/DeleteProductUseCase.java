package id.daimus.productservice.application.product.usecase;

import id.daimus.productservice.shared.exception.DataNotFoundException;

public interface DeleteProductUseCase {
    boolean deleteProduct(Long id) throws DataNotFoundException;
}
