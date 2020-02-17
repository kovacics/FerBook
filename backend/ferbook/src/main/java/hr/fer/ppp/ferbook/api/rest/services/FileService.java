package hr.fer.ppp.ferbook.api.rest.services;

import com.google.common.hash.Hashing;
import hr.fer.ppp.ferbook.api.model.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@Service
public class FileService {

    @Autowired
    private PicturesService picturesService;

    private final Path imageDir = Paths.get("./src/main/resources/static/images");

    public int saveFile(MultipartFile multipartFile) throws IOException {

        String fileName = multipartFile.getOriginalFilename();

        if (fileName == null) {
            throw new RuntimeException("File name error");
        }

        Picture picture = new Picture();
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
        String toHash = fileName + "ferbook" + new Random().nextInt();
        String hashed = Hashing.sha256().hashString(toHash, StandardCharsets.UTF_8).toString();

        Path destination;

        if (isImage(extension)) {
            destination = imageDir.resolve(hashed + "." + extension);
            picture.setPictureUrl("/images/" + hashed + "." + extension);
        } else {
            throw new RuntimeException("Invalid format");
        }

        if (destination.toFile().exists()) {
            throw new RuntimeException("File with the same name already exist.");
        }

        Files.copy(multipartFile.getInputStream(), destination);
        picturesService.savePicture(picture);
        return picture.getID();
    }

    private boolean isImage(String extension) {
        return extension.equals("png") || extension.equals("jpg") ||
                extension.equals("gif") || extension.equals("jpeg");
    }
}
