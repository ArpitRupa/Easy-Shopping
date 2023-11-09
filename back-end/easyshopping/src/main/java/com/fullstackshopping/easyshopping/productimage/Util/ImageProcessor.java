package com.fullstackshopping.easyshopping.productimage.Util;


import com.googlecode.pngtastic.core.PngImage;
import com.googlecode.pngtastic.core.PngOptimizer;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

// Utilized to compress and decompress the image
// https://www.baeldung.com/java-image-compression-lossy-lossless
public class ImageProcessor{

    private static byte[] compressPng(MultipartFile image) throws IOException {
        System.out.println("Original Size: " + image.getSize());
        System.out.println("PNG");

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(image.getBytes())) {
            PngImage inputImage = new PngImage(inputStream);
            PngOptimizer imageOptimizer = new PngOptimizer();

            // Optimize the input PNG image
            PngImage optimizedImage = imageOptimizer.optimize(inputImage);

            // Get the compressed image data
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            optimizedImage.writeDataOutputStream(outputStream);

            byte[] compressedImageData = outputStream.toByteArray();
            System.out.println("New Size: " + compressedImageData.length);

            return compressedImageData;
        } catch (IOException pngCompressionException) {
            System.err.println("Error during image compression: " + pngCompressionException.getMessage());
        }

        return image.getBytes();
    }

    private static byte[] compressJpeg(MultipartFile image) throws IOException {
        System.out.println("Original Size: " + image.getSize());
        System.out.println("JPEG");

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(image.getBytes())) {
            BufferedImage bufferedImage = ImageIO.read(inputStream);

            // Define the target height for scaling
            int targetHeight = 500;

            // Calculate the scaling factor to achieve the target height
            float scalingFactor = (float) targetHeight / bufferedImage.getHeight();

            // Loop over possible compression quality range using Thumbnails
            for (float quality = 0.9f; quality >= 0.1f; quality -= 0.1f) {
                try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                    // Compress the image using Thumbnails library
                    Thumbnails.of(bufferedImage)
                            .scale(scalingFactor)
                            .outputQuality(quality)
                            .outputFormat("jpg")
                            .toOutputStream(outputStream);

                    byte[] compressedImageData = outputStream.toByteArray();
                    System.out.println("New Size for " + quality + ": " + compressedImageData.length);

                    // Check if compressed image size is below 5MB
                    if (compressedImageData.length < 5 * 1024 * 1024) {
                        System.out.println("Upload Size for " + quality + ": " + compressedImageData.length);

                        // Return Compressed Data
                        return compressedImageData;
                    }
                }
                catch (IOException innerIOException) {
                    System.err.println("Error during image compression: " + innerIOException.getMessage());
                }
            }
        } catch (IOException outerIOException) {
            System.err.println("Error reading the image file: " + outerIOException.getMessage());
        }

        return image.getBytes();
    }

    public static byte[] compressImage(MultipartFile image) throws IOException {
        if ("image/png".equals(image.getContentType())) {
            return compressPng(image);
        } else if ("image/jpeg".equals(image.getContentType())) {
            return compressJpeg(image);
        } else {
            // Throw an exception if not supported type
            throw new UnsupportedOperationException("Unsupported image type: " + image.getContentType());
        }
    }

}
