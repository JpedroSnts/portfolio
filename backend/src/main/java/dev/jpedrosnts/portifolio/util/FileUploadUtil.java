package dev.jpedrosnts.portifolio.util;


import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLConnection;

@Component
public class FileUploadUtil {


    private final String connectionString;
    private final String containerName;

    @Autowired
    public FileUploadUtil(
            @Value("${azure.blob.container.name}") String containerName,
            @Value("${azure.blob.connection.string}") String connectionString
    ) {
        this.connectionString = connectionString;
        this.containerName = containerName;
    }

    public String upload(String fileName, byte[] image) {
        BlobContainerClient container = new BlobContainerClientBuilder()
                .containerName(containerName)
                .connectionString(connectionString)
                .buildClient();
        BlobClient blob = container.getBlobClient(fileName);
        BlobHttpHeaders headers = new BlobHttpHeaders();
        headers.setContentType(getContentType(fileName));
        blob.deleteIfExists();
        blob.upload(BinaryData.fromBytes(image));
        blob.setHttpHeaders(headers);
        return blob.getBlobUrl();
    }

    public boolean delete(String fileName) {
        BlobContainerClient container = new BlobContainerClientBuilder()
                .containerName(containerName)
                .connectionString(connectionString)
                .buildClient();
        BlobClient blob = container.getBlobClient(fileName);
        return blob.deleteIfExists();
    }

    private static String getContentType(String fileName) {
        return URLConnection.getFileNameMap().getContentTypeFor(fileName);
    }

    public static boolean isImage(String fileName) {
        try {
            return getContentType(fileName).startsWith("image/");
        } catch (Exception ex) {
            return false;
        }
    }

    public static String getExtension(String fileName) {
        int index = fileName.lastIndexOf(".");
        return fileName.substring(index);
    }
}
