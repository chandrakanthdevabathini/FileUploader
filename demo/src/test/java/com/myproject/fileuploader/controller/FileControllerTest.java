package com.myproject.fileuploader.controller;

import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import com.myproject.fileuploader.Controller.FileController;
import com.myproject.fileuploader.Service.FileService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

@RunWith(MockitoJUnitRunner.class)
public class FileControllerTest {

    @InjectMocks
    private FileController fileController;
    @Mock
    private FileService fileService;
    @Mock
    private MultipartFile file;

    private static final String FILE_NAME = "fileName";
    private static final String FILE_LOCATION = "fileLocation";

    @Test
    public void uploadFileReturnsSuccess() {
        when(fileService.uploadFile(eq(file), eq(FILE_NAME))).thenReturn("Successfully stored file");

        String uploadedFileState = fileController.uploadFile(file, FILE_NAME);

        assertThat(uploadedFileState, is(notNullValue()));
        assertThat(uploadedFileState, is("Successfully stored file"));
    }

    @Test
    public void getFileReturnsPath() {
        when(fileService.downloadFile(eq(FILE_NAME), eq(FILE_LOCATION))).thenReturn("downloaded file path");

        String downloadedFilePath = fileController.getFileData(FILE_NAME, FILE_LOCATION);

        assertThat(downloadedFilePath, is(notNullValue()));
        assertThat(downloadedFilePath, is("downloaded file path"));
    }
}