package carcrowdsystem.ccs.services.blob;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyBlobService {
    private final AzureBlobProperties azureBlobProperties;

    private BlobContainerClient containerClient() {
        BlobServiceClient serviceClient = new BlobServiceClientBuilder()
                .connectionString(azureBlobProperties.getConnectionstring()).buildClient();
        BlobContainerClient container = serviceClient.getBlobContainerClient(azureBlobProperties.getContainer());
        return container;
    }

    public List<String> listFiles() {
        log.info("Início da lista de itens BLOB:");
        BlobContainerClient container = containerClient();
        val list = new ArrayList<String>();
        for (BlobItem blobItem : container.listBlobs()) {
            log.info("Blob {}", blobItem.getName());
            list.add(blobItem.getName());
        }
        log.info("Fim da lista de itens BLOB!");
        return list;
    }

    public ByteArrayOutputStream downloadFile(String blobitem) {
        log.info("Início do download {}", blobitem);
        BlobContainerClient containerClient = containerClient();
        BlobClient blobClient = containerClient.getBlobClient(blobitem);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        blobClient.download(os);
        log.info("Download Finalizado");
        return os;
    }

    public String storeFile(String filename, InputStream content, long length) {
        log.info("Início do ficheiro de armazenamento do Azure {}", filename);
        BlobClient client = containerClient().getBlobClient(filename);
        if (client.exists()) {
            log.warn("O arquivo já está localizado no Azure");
        } else {
            client.upload(content, length);
        }

        log.info("Azure store file END");
        return "Arquivo carregado com sucesso!";
    }
}
