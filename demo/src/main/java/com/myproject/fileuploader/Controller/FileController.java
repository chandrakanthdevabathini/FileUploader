package com.myproject.fileuploader.Controller;

import com.myproject.fileuploader.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

    @Autowired
    FileService fileService;


    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) {

        return fileService.uploadFile(file,name);

    }

    @GetMapping("/getFileData")
    public String getFileData(@RequestParam("fileName") String fileName, @RequestParam("fileLocation") String fileLocation){
        return fileService.downloadFile(fileName,fileLocation);
    }
}