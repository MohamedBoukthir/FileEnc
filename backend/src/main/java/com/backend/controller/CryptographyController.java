package com.backend.controller;

import com.backend.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/cryptography")
public class CryptographyController {

    private final FileService fileService;

    public CryptographyController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/process")
    public ResponseEntity<?> processFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("key") String key,
            @RequestParam("algo") String algorithm,
            @RequestParam("operation") String operation
    ) {
        try {
            System.out.println("Received file: " + file.getOriginalFilename());
            System.out.println("Received key: " + key);
            System.out.println("Received algorithm: " + algorithm);
            System.out.println("Received operation: " + operation);

            byte[] resultFileBytes = fileService.processFile(file, key, algorithm, operation);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=processed-file")
                    .body(resultFileBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}