package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

public class FileService {

    @Autowired
    static
    RedirectAttributes redirectAttributes;

    public static String upload(MultipartFile file)
    {
        try {
            // Verifică dacă există fișier de încărcat
            if (!file.isEmpty()) {
                // Calea unde va fi salvată imaginea
                String fileName = file.getOriginalFilename();
                String destination = "";

                if(isImage(file))
                    destination = "/images";

                String uploadDir = "src/main/resources/static" + destination;

                // Creează directorul dacă nu există
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Salvează fișierul în directorul specificat
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Adaugă calea imaginii în obiectul DTO sau entitate
                return destination + "/" + fileName;
            }
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Eroare la salvarea imaginii: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private static boolean isImage(MultipartFile file)
    {
        String fileName = file.getOriginalFilename();

        // Verifică dacă fișierul are o extensie validă
        String[] allowedExtensions = {"jpg", "jpeg", "png", "gif"};
        if (fileName != null) {
            // Extrage extensia fișierului (partea după ultimul ".")
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            return Arrays.asList(allowedExtensions).contains(fileExtension);
        }
        return false;
    }

}
