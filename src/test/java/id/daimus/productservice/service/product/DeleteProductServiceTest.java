package id.daimus.productservice.service.product;

import id.daimus.productservice.application.product.entity.Product;
import id.daimus.productservice.application.product.repository.ProductRepository;
import id.daimus.productservice.application.product.service.DeleteProductService;
import id.daimus.productservice.shared.exception.DataNotFoundException;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DeleteProductServiceTest {
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    DeleteProductService deleteProductService;
    @Test
    void deleteProduct_WhenProductIsExist_ShouldReturnTrue() throws DataNotFoundException {
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
        // When
        boolean actualResult = deleteProductService.deleteProduct(expectedProduct.getId());
        // Then
        assertTrue(actualResult);
    }
    @Test
    void deleteProduct_WhenProductIsNotExist_ShouldThrowDataNotFoundException(){
        EasyRandom easyRandom = new EasyRandom();
        // Given
        Long productId = (long) easyRandom.nextInt(100);
        String expectedMessage = "No product with id " + productId;
        given(productRepository.getProductById(productId)).willReturn(Optional.empty());
        // When
        Exception exception = assertThrows(DataNotFoundException.class, () -> {
            deleteProductService.deleteProduct(productId);
        });
        // Then
        assertEquals(exception.getClass(), DataNotFoundException.class);
        assertEquals(exception.getMessage(),expectedMessage);
    }
}
