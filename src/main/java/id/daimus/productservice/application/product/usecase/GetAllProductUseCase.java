package id.daimus.productservice.application.product.usecase;

import id.daimus.productservice.application.product.entity.Product;
import id.daimus.productservice.shared.exception.DataNotFoundException;

import java.util.List;

public interface GetAllProductUseCase {
    List<Product> getAllProduct() throws DataNotFoundException;
}
