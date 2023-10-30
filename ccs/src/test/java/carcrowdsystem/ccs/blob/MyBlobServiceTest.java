package carcrowdsystem.ccs.blob;
import carcrowdsystem.ccs.services.blob.MyBlobService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyBlobServiceTest {
    @Autowired
    MyBlobService blobService;

    @Test
    public void tryListFiles(){
        List<String> strings = blobService.listFiles();
        Assertions.assertNotNull(strings);
        strings.forEach(System.out::println);
    }
}
