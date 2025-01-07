package com.backend.service;

import com.backend.model.File;
import com.backend.repository.FileRepository;
import com.backend.utils.Algorithms;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileService {

    private final Algorithms algorithms;
    private final FileRepository fileRepository;

    public FileService(Algorithms algorithms, FileRepository fileRepository) {
        this.algorithms = algorithms;
        this.fileRepository = fileRepository;
    }


    public byte[] processFile(MultipartFile file, String key, String algorithm, String operation) throws Exception {
        // Save the uploaded file temporarily
        Path tempInputFile = Files.createTempFile("input-", ".tmp");
        Path tempOutputFile = Files.createTempFile("output-", ".tmp");
        Files.write(tempInputFile, file.getBytes());

        String processedKey = key;

        // Process the file based on the algorithm and operation
        switch (algorithm.toUpperCase()) {
            case "AES":
                if ("encrypt".equalsIgnoreCase(operation)) {
                    algorithms.encryptAES(tempInputFile, tempOutputFile, processedKey);
                } else {
                    algorithms.decryptAES(tempInputFile, tempOutputFile, processedKey);
                }
                break;

            case "DES":
                if ("encrypt".equalsIgnoreCase(operation)) {
                    algorithms.encryptDES(tempInputFile, tempOutputFile, processedKey);
                } else {
                    algorithms.decryptDES(tempInputFile, tempOutputFile, processedKey);
                }
                break;

            case "BLOWFISH":
                if ("encrypt".equalsIgnoreCase(operation)) {
                    algorithms.encryptBlowfish(tempInputFile, tempOutputFile, processedKey);
                } else {
                    algorithms.decryptBlowfish(tempInputFile, tempOutputFile, processedKey);
                }
                break;

            default:
                throw new IllegalArgumentException("Error: Unsupported algorithm");
        }

    // Save the encryption record
        File fileInput = new File();
        fileInput.setFileName(file.getOriginalFilename());
        fileInput.setKey(processedKey);
        fileInput.setAlgorithm(algorithm);
        fileInput.setOperation(operation);
        fileRepository.save(fileInput);

        // Return the processed file as a byte array
        byte[] resultFileBytes = Files.readAllBytes(tempOutputFile);

        // Clean up temporary files
        Files.delete(tempInputFile);
        Files.delete(tempOutputFile);

        return resultFileBytes;
    }

}
