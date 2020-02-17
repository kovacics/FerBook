package hr.fer.ppp.ferbook.api.rest.services;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.google.common.hash.Hashing;
import com.microsoft.azure.storage.StorageException;
import hr.fer.ppp.ferbook.api.model.Picture;
import hr.fer.ppp.ferbook.config.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.util.Random;

@Service
public class FileServiceAzureBlob {

    @Autowired
    private PicturesService picturesService;

    private final Path imageDir = Paths.get("./src/main/resources/static/images");

    public static final String CONNECT_STRING =
            "DefaultEndpointsProtocol=http;" +
                    "AccountName=ferbook;" +
                    "AccountKey=+auJiQaqulkMR8/KPmEl+q53n7+mkTQDWCpfibDi0shflw2gOIXGxpRDp/zaZAq1DGJ07pUxk0HWi/bi8xx/xA==";

    public static final String CONTAINER_NAME = "images";


    public Integer saveFile(MultipartFile multipartFile) throws IOException, URISyntaxException, StorageException, InvalidKeyException {

        String originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename == null) {
            throw new RuntimeException("File name error");
        }

        String extension = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);
        String fileName = generateHashedFilename(originalFilename, extension);

        Path destination;

        if (isImage(extension)) {
            destination = imageDir.resolve(fileName);
        } else {
            throw new BadRequestException("Invalid image format");
        }

        if (destination.toFile().exists()) {
            throw new RuntimeException("File with the same name already exist.");
        }

        //save file locally
        Files.copy(multipartFile.getInputStream(), destination);

        //upload to azure
        String azureUrl = uploadFileToAzure(fileName, destination);

        //delete local file, not needed anymore
        Files.delete(destination);

        //save azure url to picture object
        Picture picture = new Picture();
        picture.setPictureUrl(azureUrl);
        picturesService.savePicture(picture);

        return picture.getID();
    }

    private String uploadFileToAzure(String fileName, Path destination) throws URISyntaxException, InvalidKeyException, StorageException, IOException {

        // Create a BlobServiceClient object which will be used to create a container client
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(CONNECT_STRING).buildClient();

        // Get blob container client by container name
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(CONTAINER_NAME);

        // Get a reference to a blob
        BlobClient blobClient = containerClient.getBlobClient(fileName);

        // Upload the blob
        blobClient.uploadFromFile(destination.toAbsolutePath().toString());

        //return blob url
        return blobClient.getBlobUrl();
    }

    private String generateHashedFilename(String originalFilename, String extension) {
        String toHash = originalFilename + "ferbook" + new Random().nextInt();
        String hashed = Hashing.sha256().hashString(toHash, StandardCharsets.UTF_8).toString();
        return hashed + "." + extension;
    }

    private boolean isImage(String extension) {
        return extension.equals("png") || extension.equals("jpg") ||
                extension.equals("gif") || extension.equals("jpeg");
    }
}
