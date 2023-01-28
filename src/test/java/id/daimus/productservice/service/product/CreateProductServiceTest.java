package id.daimus.productservice.service.product;

import id.daimus.productservice.application.product.entity.Product;
import id.daimus.productservice.application.product.repository.ProductRepository;
import id.daimus.productservice.application.product.service.CreateProductService;
import jakarta.validation.ConstraintViolationException;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CreateProductServiceTest {
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    CreateProductService createProductService;

    @Test
    void createProduct_WhenProductValid_ShouldReturnProduct(){
        EasyRandom easyRandom = new EasyRandom();
        // Given
        Product expectedProduct = new Product(
                (long) easyRandom.nextInt(100),
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class),
                easyRandom.nextInt(100),
                easyRandom.nextInt(100000)
        );
        given(productRepository.createProduct(expectedProduct)).willReturn(expectedProduct);
        // When
        Product actualProduct = createProductService.createProduct(expectedProduct);
        // Then
        assertEquals(actualProduct, expectedProduct);
    }

    @Test
    void createProduct_WhenProductIsEmpty_ShouldThrowConstraintViolationException(){
        // Given
        Product product = new Product();
        // When
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            createProductService.createProduct(product);
        });
        // Then
        assertEquals(exception.getClass(), ConstraintViolationException.class);
    }

    @Test
    void createProduct_WhenProductNameIsNull_ShouldThrowConstraintViolationException(){
        EasyRandom easyRandom = new EasyRandom();
        // Given
        String expectedMessage = "The product name is required";
        Product product = new Product();
        product.setDescription(easyRandom.nextObject(String.class));
        product.setStock(easyRandom.nextInt(100));
        product.setPrice(easyRandom.nextInt(100000));
        // When
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            createProductService.createProduct(product);
        });
        // Then
        assertEquals(exception.getClass(), ConstraintViolationException.class);
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void createProduct_WhenProductDescriptionIsNull_ShouldThrowConstraintViolationException(){
        EasyRandom easyRandom = new EasyRandom();
        // Given
        String expectedMessage = "The product description is required";
        Product product = new Product();
        product.setName(easyRandom.nextObject(String.class));
        product.setStock(easyRandom.nextInt(100));
        product.setPrice(easyRandom.nextInt(100000));
        // When
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            createProductService.createProduct(product);
        });
        // Then
        assertEquals(exception.getClass(), ConstraintViolationException.class);
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void createProduct_WhenProductStockIsNull_ShouldThrowConstraintViolationException(){
        EasyRandom easyRandom = new EasyRandom();
        // Given
        String expectedMessage = "The minimum stock is 1";
        Product product = new Product();
        product.setName(easyRandom.nextObject(String.class));
        product.setDescription(easyRandom.nextObject(String.class));
        product.setPrice(easyRandom.nextInt(100000));
        // When
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            createProductService.createProduct(product);
        });
        // Then
        assertEquals(exception.getClass(), ConstraintViolationException.class);
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void createProduct_WhenProductStockIsNegative_ShouldThrowConstraintViolationException(){
        EasyRandom easyRandom = new EasyRandom();
        // Given
        String expectedMessage = "The minimum stock is 1";
        Product product = new Product();
        product.setName(easyRandom.nextObject(String.class));
        product.setDescription(easyRandom.nextObject(String.class));
        product.setStock(-easyRandom.nextInt(100));
        product.setPrice(easyRandom.nextInt(100000));
        // When
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            createProductService.createProduct(product);
        });
        // Then
        assertEquals(exception.getClass(), ConstraintViolationException.class);
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void createProduct_WhenProductPriceIsNull_ShouldThrowConstraintViolationException(){
        EasyRandom easyRandom = new EasyRandom();
        // Given
        String expectedMessage = "The minimum price is 1";
        Product product = new Product();
        product.setName(easyRandom.nextObject(String.class));
        product.setDescription(easyRandom.nextObject(String.class));
        product.setStock(easyRandom.nextInt(100));
        // When
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            createProductService.createProduct(product);
        });
        // Then
        assertEquals(exception.getClass(), ConstraintViolationException.class);
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void createProduct_WhenProductPriceIsNegative_ShouldThrowConstraintViolationException(){
        EasyRandom easyRandom = new EasyRandom();
        // Given
        String expectedMessage = "The minimum price is 1";
        Product product = new Product();
        product.setName(easyRandom.nextObject(String.class));
        product.setDescription(easyRandom.nextObject(String.class));
        product.setStock(easyRandom.nextInt(100));
        product.setPrice(-easyRandom.nextInt(100000));
        // When
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            createProductService.createProduct(product);
        });
        // Then
        assertEquals(exception.getClass(), ConstraintViolationException.class);
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void createProduct_WhenProductIsNull_ShouldThrowIllegalArgumentException(){
        // Given
        Product product = null;
        // When
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            createProductService.createProduct(product);
        });
        // Then
        assertEquals(exception.getClass(), IllegalArgumentException.class);
    }
}
