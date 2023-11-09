package com.fullstackshopping.easyshopping.product.service;

import com.fullstackshopping.easyshopping.common.dto.request.ProductImageRequest;
import com.fullstackshopping.easyshopping.common.dto.request.ProductRequest;
import com.fullstackshopping.easyshopping.product.model.Product;
import com.fullstackshopping.easyshopping.product.repository.ProductRepository;
import com.fullstackshopping.easyshopping.productimage.service.ProductImageService;
import com.fullstackshopping.easyshopping.security.service.TokenService;
import com.fullstackshopping.easyshopping.user.model.User;
import com.fullstackshopping.easyshopping.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageService productImageService;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Autowired
    public ProductService(ProductRepository productRepository, TokenService tokenService, UserRepository userRepository, ProductImageService productImageService) {
        this.productRepository = productRepository;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.productImageService = productImageService;
    }

    public List<Product> getAllProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Error retrieving all products", e);
        }
    }

    public Product getProductById(int productId) {
        return  this.productRepository.findById(productId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, ("Product not found with ID: " + productId)));
    }

    public List<Product> getAllProductsBySellerId(int sellerID) {
        return this.productRepository.findAllBySellerId(sellerID)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Products not found for user with ID: " + sellerID ));
    }

    public Product createProduct(ProductRequest productRequest, String token) {
        String username = this.tokenService.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found"));

        Product newProduct = new Product(
                productRequest.getName(),
                productRequest.getDescription(),
                user,
                BigDecimal.valueOf(0),
                productRequest.getPrice()
        );


        try{
            return productRepository.save(newProduct);
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not create Product.");
        }

    }


    @Transactional
    public Product createProductWithImages(ProductRequest productRequest, List<MultipartFile> files, String token) {

        try{
            Product product = this.createProduct(productRequest, token);

            for (MultipartFile file : files) {
                ProductImageRequest imageRequest = new ProductImageRequest(product.getId(), file);
                productImageService.createProductImage(imageRequest);
            }
            return product;
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not create Product Listing with Images." + e.getMessage());
        }
    }

    public Product updateProduct(int productId, ProductRequest updatedProductRequest) {

        Product currentProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found for PUT request"));

        try{
            currentProduct.setName(updatedProductRequest.getName());
            currentProduct.setDescription(updatedProductRequest.getDescription());
            currentProduct.setPrice(updatedProductRequest.getPrice());

            return productRepository.save(currentProduct);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product update failed");
        }

    }

    public boolean deleteProduct(int productId) {

        try{
            productRepository.deleteById(productId);
            return true;
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product deletion failed for Product with id: " + productId);
        }

    }



}
