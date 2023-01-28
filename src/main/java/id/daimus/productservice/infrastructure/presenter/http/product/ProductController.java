package id.daimus.productservice.infrastructure.presenter.http.product;

import id.daimus.productservice.application.product.entity.Product;
import id.daimus.productservice.application.product.usecase.*;
import id.daimus.productservice.infrastructure.presenter.http.Response;
import id.daimus.productservice.shared.exception.DataNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final GetAllProductUseCase getAllProductUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    @Operation(summary = "Find all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping
    public ResponseEntity<Object> getAllProduct() throws DataNotFoundException {
        log.info("GET /products called");
        Response response = new Response();
        response.setPath("/products");
        List<Product> products = getAllProductUseCase.getAllProduct();
        response.setData(products);
        return response.getResponse();
    }
    @Operation(summary = "Find product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid product id supplied"),
            @ApiResponse(responseCode = "404", description = "Product with the id not found")
    })
    @Parameters(value = {
            @Parameter(in = ParameterIn.PATH, name = "id", description = "Product Id")
    })
    @GetMapping("{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id) throws DataNotFoundException {
        log.info("GET /products/{} called", id.toString());
        Response response = new Response();
        Product product = getProductByIdUseCase.getProductById(id);
        response.setData(product);
        return response.getResponse();
    }

    @Operation(summary = "Create new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid product id supplied"),
    })
    @PostMapping
    public ResponseEntity<Object> createProduct(@Valid @RequestBody Product productParam) {
        log.info("POST /products called");
        Response response = new Response();
        Product product = createProductUseCase.createProduct(productParam);
        response.setData(product);
        response.setHttpCode(201);
        return response.getResponse();
    }

    @Operation(summary = "Update product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid product id supplied"),
            @ApiResponse(responseCode = "404", description = "Product with id not found"),
    })
    @Parameters(value = {
            @Parameter(in = ParameterIn.PATH, name = "id", description = "Product Id")
    })
    @PatchMapping("{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Long id, @RequestBody Product productParam) throws DataNotFoundException {
        log.info("PATCH /products/{} called", id.toString());
        Response response = new Response();
        Product product = updateProductUseCase.updateProduct(id, productParam);
        response.setData(product);
        return response.getResponse();
    }

    @Operation(summary = "Delete product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid product id supplied"),
            @ApiResponse(responseCode = "404", description = "Product with the id not found"),
    })
    @Parameters(value = {
            @Parameter(in = ParameterIn.PATH, name = "id", description = "Product Id")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) throws DataNotFoundException {
        log.info("DELETE /products/{} called", id.toString());
        Response response = new Response();
        deleteProductUseCase.deleteProduct(id);
        response.setHttpCode(204);
        return response.getResponse();
    }
}
