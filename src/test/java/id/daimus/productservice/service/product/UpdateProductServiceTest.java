package id.daimus.productservice.service.product;

import id.daimus.productservice.application.product.entity.Product;
import id.daimus.productservice.application.product.repository.ProductRepository;
import id.daimus.productservice.application.product.service.UpdateProductService;
import id.daimus.productservice.shared.exception.DataNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UpdateProductServiceTest {
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    UpdateProductService updateProductService;
    @Test
    void updateProduct_WhenProductExistAndValid_ShouldReturnProduct() throws DataNotFoundException {
        EasyRandom easyRandom = new EasyRandom();
        // Given
        Product expectedProduct = new Product(
                (long) easyRandom.nextInt(100),
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class),
                easyRandom.nextInt(100),
                easyRandom.nextInt(100000)
        );
        given(productRepository.getProductById(expectedProduct.getId())).willReturn(Optional.of(expectedProduct));
        given(productRepository.updateProduct(expectedProduct)).willReturn(expectedProduct);
        // When
        Product actualProduct = updateProductService.updateProduct(expectedProduct.getId(), expectedProduct);
        // Then
        assertEquals(expectedProduct, actualProduct);
    }
    @Test
    void updateProduct_WhenProductNotExistAndValid_ShouldThrowDataNotFoundException(){
        EasyRandom easyRandom = new EasyRandom();
        // Given
        Product expectedProduct = new Product(
                (long) easyRandom.nextInt(100),
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class),
                easyRandom.nextInt(100),
                easyRandom.nextInt(100000)
        );
        given(productRepository.getProductById(expectedProduct.getId())).willReturn(Optional.empty());
        given(productRepository.updateProduct(expectedProduct)).willReturn(expectedProduct);
        // When
        Exception exception = assertThrows(DataNotFoundException.class, () -> {
            updateProductService.updateProduct(expectedProduct.getId(), expectedProduct);
        });
        // Then
        assertEquals(exception.getClass(), DataNotFoundException.class);
    }
    @Test
    void updateProduct_WhenProductExistAndProductIsEmpty_ShouldThrowConstraintViolationException(){
        EasyRandom easyRandom = new EasyRandom();
        // Given
        Product expectedProduct = new Product(
                (long) easyRandom.nextInt(100),
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class),
                easyRandom.nextInt(100),
                easyRandom.nextInt(100000)
        );
        given(productRepository.getProductById(expectedProduct.getId())).willReturn(Optional.of(expectedProduct));
        given(productRepository.updateProduct(expectedProduct)).willReturn(expectedProduct);
        // When
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            updateProductService.updateProduct(expectedProduct.getId(), new Product());
        });
        // Then
        assertEquals(exception.getClass(), ConstraintViolationException.class);
    }
    @Test
    void updateProduct_WhenProductExistAndProductIsNull_ShouldThrowIllegalArgumentException(){
        EasyRandom easyRandom = new EasyRandom();
        // Given
        Product expectedProduct = new Product(
                (long) easyRandom.nextInt(100),
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class),
                easyRandom.nextInt(100),
                easyRandom.nextInt(100000)
        );
        given(productRepository.getProductById(expectedProduct.getId())).willReturn(Optional.of(expectedProduct));
        given(productRepository.updateProduct(expectedProduct)).willReturn(expectedProduct);
        // When
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            updateProductService.updateProduct(expectedProduct.getId(), null);
        });
        // Then
        assertEquals(exception.getClass(), IllegalArgumentException.class);
    }
    @Test
    void updateProduct_WhenProductIdIsNull_ShouldThrowDataNotFoundException(){
        EasyRandom easyRandom = new EasyRandom();
        // Given
        Product expectedProduct = new Product(
                (long) easyRandom.nextInt(100),
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class),
                easyRandom.nextInt(100),
                easyRandom.nextInt(100000)
        );
        given(productRepository.getProductById(expectedProduct.getId())).willReturn(Optional.of(expectedProduct));
        given(productRepository.updateProduct(expectedProduct)).willReturn(expectedProduct);
        // When
        Exception exception = assertThrows(DataNotFoundException.class, () -> {
            updateProductService.updateProduct(null, expectedProduct);
        });
        // Then
        assertEquals(exception.getClass(), DataNotFoundException.class);
    }
    @Test
    void updateProduct_WhenProductExistAndProductNameIsNull_ShouldThrowConstraintViolationException(){
        EasyRandom easyRandom = new EasyRandom();
        // Given
        String expectedMessage = "The product name is required";
        Product expectedProduct = new Product(
                (long) easyRandom.nextInt(100),
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class),
                easyRandom.nextInt(100),
                easyRandom.nextInt(100000)
        );
        Product expectedProductInput = expectedProduct;
        expectedProductInput.setName(null);
        given(productRepository.getProductById(expectedProduct.getId())).willReturn(Optional.of(expectedProduct));
        given(productRepository.updateProduct(expectedProductInput)).willReturn(expectedProductInput);
        // When
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            updateProductService.updateProduct(expectedProduct.getId(), expectedProductInput);
        });
        // Then
        assertEquals(exception.getClass(), ConstraintViolationException.class);
        assertTrue(exception.getMessage().contains(expectedMessage));

    }
    @Test
    void updateProduct_WhenProductExistAndProductDescriptionIsNull_ShouldThrowConstraintViolationException(){
        EasyRandom easyRandom = new EasyRandom();
        // Given
        String expectedMessage = "The product description is required";
        Product expectedProduct = new Product(
                (long) easyRandom.nextInt(100),
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class),
                easyRandom.nextInt(100),
                easyRandom.nextInt(100000)
        );
        Product expectedProductInput = expectedProduct;
        expectedProductInput.setDescription(null);
        given(productRepository.getProductById(expectedProduct.getId())).willReturn(Optional.of(expectedProduct));
        given(productRepository.updateProduct(expectedProductInput)).willReturn(expectedProductInput);
        // When
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            updateProductService.updateProduct(expectedProduct.getId(), expectedProductInput);
        });
        // Then
        assertEquals(exception.getClass(), ConstraintViolationException.class);
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
    @Test
    void updateProduct_WhenProductExistAndProductStockIsNull_ShouldThrowConstraintViolationException(){
        EasyRandom easyRandom = new EasyRandom();
        // Given
        String expectedMessage = "The minimum stock is 1";
        Product expectedProduct = new Product(
                (long) easyRandom.nextInt(100),
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class),
                easyRandom.nextInt(100),
                easyRandom.nextInt(100000)
        );
        Product expectedProductInput = new Product();
        expectedProductInput.setId(expectedProduct.getId());
        expectedProductInput.setName(expectedProduct.getName());
        expectedProductInput.setDescription(expectedProduct.getDescription());
        expectedProductInput.setPrice(expectedProduct.getPrice());
        given(productRepository.getProductById(expectedProduct.getId())).willReturn(Optional.of(expectedProduct));
        given(productRepository.updateProduct(expectedProductInput)).willReturn(expectedProductInput);
        // When
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            updateProductService.updateProduct(expectedProduct.getId(), expectedProductInput);
        });
        // Then
        assertEquals(exception.getClass(), ConstraintViolationException.class);
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
    @Test
    void updateProduct_WhenProductExistAndProductStockIsNegative_ShouldThrowConstraintViolationException(){
        EasyRandom easyRandom = new EasyRandom();
        // Given
        String expectedMessage = "The minimum stock is 1";
        Product expectedProduct = new Product(
                (long) easyRandom.nextInt(100),
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class),
                easyRandom.nextInt(100),
                easyRandom.nextInt(100000)
        );
        Product expectedProductInput = expectedProduct;
        expectedProductInput.setStock(-expectedProduct.getStock());
        given(productRepository.getProductById(expectedProduct.getId())).willReturn(Optional.of(expectedProduct));
        given(productRepository.updateProduct(expectedProductInput)).willReturn(expectedProductInput);
        // When
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            updateProductService.updateProduct(expectedProduct.getId(), expectedProductInput);
        });
        // Then
        assertEquals(exception.getClass(), ConstraintViolationException.class);
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
    @Test
    void updateProduct_WhenProductExistAndProductPriceIsNull_ShouldThrowConstraintViolationException(){
        EasyRandom easyRandom = new EasyRandom();
        // Given
        String expectedMessage = "The minimum price is 1";
        Product expectedProduct = new Product(
                (long) easyRandom.nextInt(100),
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class),
                easyRandom.nextInt(100),
                easyRandom.nextInt(100000)
        );
        Product expectedProductInput = new Product();
        expectedProductInput.setId(expectedProduct.getId());
        expectedProductInput.setName(expectedProduct.getName());
        expectedProductInput.setDescription(expectedProduct.getDescription());
        expectedProductInput.setStock(expectedProduct.getStock());
        given(productRepository.getProductById(expectedProduct.getId())).willReturn(Optional.of(expectedProduct));
        given(productRepository.updateProduct(expectedProductInput)).willReturn(expectedProductInput);
        // When
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            updateProductService.updateProduct(expectedProduct.getId(), expectedProductInput);
        });
        // Then
        assertEquals(exception.getClass(), ConstraintViolationException.class);
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
    @Test
    void updateProduct_WhenProductExistAndProductPriceIsNegative_ShouldThrowConstraintViolationException(){
        EasyRandom easyRandom = new EasyRandom();
        // Given
        String expectedMessage = "The minimum price is 1";
        Product expectedProduct = new Product(
                (long) easyRandom.nextInt(100),
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class),
                easyRandom.nextInt(100),
                easyRandom.nextInt(100000)
        );
        Product expectedProductInput = expectedProduct;
        expectedProductInput.setPrice(-expectedProduct.getPrice());
        given(productRepository.getProductById(expectedProduct.getId())).willReturn(Optional.of(expectedProduct));
        given(productRepository.updateProduct(expectedProductInput)).willReturn(expectedProductInput);
        // When
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            updateProductService.updateProduct(expectedProduct.getId(), expectedProductInput);
        });
        // Then
        assertEquals(exception.getClass(), ConstraintViolationException.class);
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
}
