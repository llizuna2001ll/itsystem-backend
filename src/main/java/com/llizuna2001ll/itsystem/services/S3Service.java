package com.llizuna2001ll.itsystem.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.llizuna2001ll.itsystem.entities.Product;
import com.llizuna2001ll.itsystem.exceptions.ProductNotFoundException;
import com.llizuna2001ll.itsystem.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class S3Service {

    @Value("${application.bucket.name}")
    private String bucketName;
    private final AmazonS3 s3Client;
    private final ProductRepository productRepository;

    public S3Service(AmazonS3 s3Client,
                     ProductRepository productRepository) {
        this.s3Client = s3Client;
        this.productRepository = productRepository;
    }

    public Product uploadPicture(MultipartFile file, Long id) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = "products/" + System.currentTimeMillis() + "_" + product.getName().replace(" ","_");
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        product.setImagePath(fileName);

        return productRepository.save(product);
    }

    public String deletePicture(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }


    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            System.out.println("Error converting multipartFile to file");
        }
        return convertedFile;
    }
}
