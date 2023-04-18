package fr.jehann.app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The LogController class is a Spring RestController that exposes an API endpoint for downloading an application log file.
 * The base path for all endpoints of this class is "/api/logs".
 */
@RestController
@RequestMapping("/api/logs")
public class LogController {

    /**
     * The storageFolder variable stores the path to the folder where the application log file is stored.
     */

    @Value("${app.storagefolder}")
    private String storageFolder;

    /**
     * The getStorageFolder method returns the path to the folder where the application log file is stored.
     * @return the path to the folder where the application log file is stored
     */

    public String getStorageFolder() {
        return storageFolder;
    }

    /**
     * The setStorageFolder method sets the path to the folder where the application log file is stored.
     * @param storageFolder the path to the folder where the application log file is stored
     */

    public void setStorageFolder(String storageFolder) {
        this.storageFolder = storageFolder;
    }

    /**
     * The getLogFile method is a GET endpoint that allows clients to download the application log file.
     * @return a ResponseEntity containing the application log file as a resource
     * @throws IOException if the application log file cannot be read
     */

    @GetMapping(produces = "application/octet-stream")
    public ResponseEntity<Resource> getLogFile() throws IOException {
        // Construct a File object representing the application log file
        File file = new File(storageFolder + "/app.log");

        // Set the response headers
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=app.log");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        // Read the application log file into a ByteArrayResource
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        // Construct a ResponseEntity containing the response headers, content length, content type, and resource
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}