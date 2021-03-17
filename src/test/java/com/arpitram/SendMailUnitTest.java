package com.arpitram;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SendMail.class)
class SendMailUnitTest {

    String absolutePath;
    String emailIdPath;
    String contentPath;

    @BeforeEach
    void setUp() {
        String path = "src/test/resources";

        File file = new File(path);
        absolutePath = file.getAbsolutePath();
        emailIdPath = absolutePath + File.separator + "emails.txt";
        contentPath = absolutePath + File.separator + "content.txt";
    }

    @Test
    void sendEmailTest() throws Exception {
        PowerMockito.spy(SendMail.class);
        PowerMockito.doReturn("success").when(SendMail.class, "sendMail", ArgumentMatchers.any(String.class), ArgumentMatchers.any(String.class), ArgumentMatchers.any(String.class), ArgumentMatchers.any(String.class));

        String result = SendMail.getEmailAddress("mail-config.properties", emailIdPath, contentPath, "", "Test Mail", "");
        Assertions.assertEquals("failure", result);
    }

    @Test
    void getEmailAddressThrowsTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> SendMail.getEmailAddress(emailIdPath, emailIdPath, contentPath, "", "Test Mail", ""));
    }
}