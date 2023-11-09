package com.fullstackshopping.easyshopping.productimage.Util;

import com.aspose.imaging.Image;
import com.aspose.imaging.ImageOptionsBase;
import com.aspose.imaging.imageoptions.JpegOptions;
import com.aspose.imaging.imageoptions.PngOptions;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

// Utilized to compress and decompress the image
// https://www.baeldung.com/java-image-compression-lossy-lossless
public class ImageProcessor{

    private static void compressPng(MultipartFile image){
        System.out.println("PNG");
    }

    private static void compressJpeg(MultipartFile image){
        System.out.println("JPEG");
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
