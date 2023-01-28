package id.daimus.productservice.application.product.service;

import id.daimus.productservice.application.product.entity.Product;
import id.daimus.productservice.application.product.repository.ProductRepository;
import id.daimus.productservice.application.product.usecase.UpdateProductUseCase;
import id.daimus.productservice.shared.exception.DataNotFoundException;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UpdateProductService implements UpdateProductUseCase {
    private final ProductRepository productRepository;
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    @Override
    public Product updateProduct(Long id, Product product) throws DataNotFoundException {
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
        Optional<Product> p = productRepository.getProductById(id);
        if (p.isEmpty()){
            throw new DataNotFoundException(String.format("Data product with id %s not found", id));
        }
        product.setId(id);
        return productRepository.updateProduct(product);
    }
}
