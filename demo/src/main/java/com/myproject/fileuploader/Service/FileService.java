package com.myproject.fileuploader.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {
    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    public String uploadFile(MultipartFile file, String name) {

        String response = "Failed to store file";

        try {
            Path destinstionLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(name));

            Files.copy(file.getInputStream(), destinstionLocation, StandardCopyOption.REPLACE_EXISTING);
            response = "Successfully stored file";
            
        } catch (Exception e) {
            e.printStackTrace();

        }

        return response;

    }

    public String downloadFile(String fileName, String fileLocation) {

        try {

                File file = ResourceUtils.getFile(fileLocation + fileName);

                return new String(Files.readAllBytes(file.toPath()));
        }

        catch (Exception e) {
            return "No file found at given location";

        }
    }

}
