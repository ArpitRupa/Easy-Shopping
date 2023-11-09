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

    private static void compressPng(MultipartFile image) throws IOException {
        System.out.println("Original Size: " + image.getSize());
        System.out.println("PNG");

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(image.getBytes())) {
            BufferedImage bufferedImage = ImageIO.read(inputStream);

            // Loop over possible compression quality range
            for (float quality = 0.0f; quality <= 1.0f; quality += 0.1f) {
                try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                    Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("png");
                    ImageWriter writer = writers.next();

                    ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
                    writer.setOutput(imageOutputStream);

                    ImageWriteParam params = writer.getDefaultWriteParam();
                    params.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                    params.setCompressionQuality(quality);

                    writer.write(null, new IIOImage(bufferedImage, null, null), params);

                    byte[] compressedImageData = outputStream.toByteArray();
                    System.out.println("New Size for " + quality + ": " + compressedImageData.length);

                    imageOutputStream.close();
                    writer.dispose();
                }
            }
        }
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
        }
    }

    private static void compressJpeg(MultipartFile image) throws IOException{
        System.out.println("Original Size: " + image.getSize());
        System.out.println("JPEG");

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(image.getBytes())) {
            BufferedImage bufferedImage = ImageIO.read(inputStream);

            // Loop over possible compression quality range
            for (float quality = 0.0f; quality <= 1.0f; quality += 0.1f) {
                try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                    Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
                    ImageWriter writer = writers.next();

                    ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
                    writer.setOutput(imageOutputStream);

                    ImageWriteParam params = writer.getDefaultWriteParam();
                    params.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                    params.setCompressionQuality(quality);

                    writer.write(null, new IIOImage(bufferedImage, null, null), params);

                    byte[] compressedImageData = outputStream.toByteArray();
                    System.out.println("New Size for " + quality + ": " + compressedImageData.length);

                    imageOutputStream.close();
                    writer.dispose();
                }
            }
        }
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(image.getBytes())) {
            BufferedImage bufferedImage = ImageIO.read(inputStream);

            // Loop over possible compression quality range using Thumbnails
            for (float quality = 0.0f; quality <= 1.0f; quality += 0.1f) {
                try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                    // Compress the image using Thumbnails library
                    Thumbnails.of(bufferedImage)
                            .scale(1)
                            .outputQuality(quality)
                            .outputFormat("jpg")
                            .toOutputStream(outputStream);

                    byte[] compressedImageData = outputStream.toByteArray();
                    System.out.println("New Size for " + quality + ": " + compressedImageData.length);
                }
            }
        }
    }

    public static void compressImage(MultipartFile image) throws IOException {
        if ("image/png".equals(image.getContentType())) {
//            return compressPng(image);
            compressPng(image);
        } else if ("image/jpeg".equals(image.getContentType())) {
//            return compressJpeg(image);
            compressJpeg(image);
        } else {
            // Throw an exception if not supported type
            throw new UnsupportedOperationException("Unsupported image type: " + image.getContentType());
        }
    }

}
