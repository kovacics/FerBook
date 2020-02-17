package hr.fer.ppp.ferbook.api.rest.controllers;

import com.microsoft.azure.storage.StorageException;
import hr.fer.ppp.ferbook.api.rest.services.FileServiceAzureBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private FileServiceAzureBlob fileService;

    @PostMapping("/upload")
    public Integer uploadFile(@RequestParam MultipartFile file) throws IOException, StorageException, InvalidKeyException, URISyntaxException {
        return fileService.saveFile(file);
    }

    @PostMapping("/uploadMulti")
    public List<Integer> uploadFile(@RequestParam MultipartFile[] files) throws IOException, StorageException, InvalidKeyException, URISyntaxException {
        List<Integer> ids = new ArrayList<>();
        for (MultipartFile file : files) {
            ids.add(fileService.saveFile(file));
        }
        return ids;
    }
}
