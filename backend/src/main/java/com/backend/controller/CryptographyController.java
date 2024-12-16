package com.backend.controller;

import com.backend.utils.Algorithms;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/cryptography")
public class CryptographyController {

    private final Algorithms algorithms;

    public CryptographyController(Algorithms algorithms) {
        this.algorithms = algorithms;
    }

    @PostMapping("/process")
    public ResponseEntity<?> processFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("key") String key,
            @RequestParam("algo") String algorithm,
            @RequestParam("operation") String operation
    ) {
        try {
            // Save the uploaded file temporarily
            Path tempInputFile = Files.createTempFile("input-", ".tmp");
            Path tempOutputFile = Files.createTempFile("output-", ".tmp");
            Files.write(tempInputFile, file.getBytes());

            // Directly use the provided key without any modifications
            String processedKey = key; // No modifications, use the user's input key

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
                    return ResponseEntity.badRequest().body("Error: Unsupported algorithm");
            }

            // Return the processed file as a response
            byte[] resultFileBytes = Files.readAllBytes(tempOutputFile);

            // Clean up temporary files
            Files.delete(tempInputFile);
            Files.delete(tempOutputFile);

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=processed-file")
                    .body(resultFileBytes);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }



}
