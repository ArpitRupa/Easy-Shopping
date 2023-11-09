package com.fullstackshopping.easyshopping.productimage.model;

import com.fullstackshopping.easyshopping.product.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Arrays;

@Entity
@Table(name = "product_images")
public class ProductImage implements Serializable {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto to work with auto increment or sequence
    @Column(name = "image_id", nullable = false, updatable = false) // cannot update ID once set
    private int imageId;

    // Multiple images can belong to one Product
    // Lazy Fetch since we don't want to reload the product for every image
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private Product product;

    @Lob // Annotation for Binary Large OBject
    @Column(name = "image_data", nullable = false, updatable = false, length = 5242880)
    private byte[] imageData;

    @Column(name = "file_name", nullable = false, updatable = false)
    private String fileName;

    @Column(name = "file_type", nullable = false, updatable = false)
    private String  fileType;



    // Constructors, getters, setters

    // Default constructor
    public ProductImage() {
    }

    public ProductImage(Product product, byte[] imageData, String fileName, String fileType) {
        this.product = product;
        this.imageData = imageData;
        this.fileName = fileName;
        this.fileType = fileType;
    }

    // Getters and setters
    public int getImageId() {
        return this.imageId;
    }

    public byte[] getImageData() {
        return this.imageData;
    }
    public Product getProduct() {
        return this.product;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    //Override toString() method

    @Override
    public String toString() {
        return "ProductImage{" +
                "imageId=" + imageId +
                ", product=" + product +
                ", imageData=" + Arrays.toString(imageData) +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}
