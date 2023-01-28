package id.daimus.productservice.infrastructure.data.jpa.product;

import id.daimus.productservice.application.product.entity.Product;
import id.daimus.productservice.application.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final JpaProductRepository jpaProductRepository;

    @Override
    public List<Product> getAllProducts() {
        List<ProductEntity> productEntities = jpaProductRepository.findAll();
        List<Product> products = new ArrayList<>();
        for (ProductEntity productEntity: productEntities){
            Product product = new Product();
            BeanUtils.copyProperties(productEntity, product);
            products.add(product);
        }
        return products;
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        Optional<ProductEntity> productEntity = jpaProductRepository.findById(id);
        if (productEntity.isPresent()){
            Product product = new Product();
            BeanUtils.copyProperties(productEntity.get(), product);
            return Optional.of(product);
        }
        return Optional.empty();
    }

    @Override
    public Product createProduct(Product product) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(product, productEntity);
        ProductEntity createdProductEntity = jpaProductRepository.save(productEntity);
        BeanUtils.copyProperties(createdProductEntity, product);
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(product, productEntity);
        ProductEntity createdProductEntity = jpaProductRepository.save(productEntity);
        BeanUtils.copyProperties(createdProductEntity, product);
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        jpaProductRepository.deleteById(id);
    }
}
