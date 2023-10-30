package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.services.blob.MyBlobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/blob")
@RequiredArgsConstructor
@Slf4j
public class MyBlobRestController {
    private final MyBlobService myBlobService;

    @GetMapping("/")
    public List<String> blobitems() {
        return myBlobService.listFiles();
    }

    @GetMapping("/download/{filename}")
    public byte[] download(@PathVariable String filename) {
        log.info("download item BLOB: {}", filename);
        return myBlobService.downloadFile(filename).toByteArray();
    }
    @PostMapping("/upload")
    public String uploadFile(MultipartFile file) throws IOException {
        log.info("Filename :" + file.getOriginalFilename());
        log.info("Size:" + file.getSize());
        log.info("Contenttype:" + file.getContentType());
        myBlobService.storeFile(file.getOriginalFilename(),file.getInputStream(), file.getSize());
        return file.getOriginalFilename() + " Item BLOB salvo com sucesso!!!";
    }
}
