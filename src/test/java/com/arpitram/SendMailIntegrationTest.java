package com.arpitram;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;

@ExtendWith(MockitoExtension.class)
class SendMailIntegrationTest {

    @Test
    void sendEmailTest() {
        String path = "src/test/resources";

        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        String emailIdPath = absolutePath + File.separator + "emails.txt";
        String contentPath = absolutePath + File.separator + "content.txt";

        System.out.println(absolutePath);
        String result = SendMail.getEmailAddress("mail-config.properties", emailIdPath, contentPath, "", "Test Mail", "");
        Assertions.assertEquals("failure", result, () -> "Test failed");
    }

    @Test
    void getEmailAddressThrowsTest() {
        String path = "src/test/resources";

        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        String emailIdPath = absolutePath + File.separator + "emails.txt";
        String contentPath = absolutePath + File.separator + "content.txt";

        System.out.println(absolutePath);

        Assertions.assertThrows(IllegalArgumentException.class, () -> SendMail.getEmailAddress(emailIdPath, emailIdPath, contentPath, "", "Test Mail", ""));
    }
}