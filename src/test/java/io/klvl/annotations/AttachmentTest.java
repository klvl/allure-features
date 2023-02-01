package io.klvl.annotations;

import io.qameta.allure.Attachment;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;

public class AttachmentTest {

    @Test
    public void testStringAttachment() {
        attachTextAsString("ABC");
    }

    @Test
    public void testJsonAttachment() {
        attachJsonAsByte("{ " +
                "\"user\": \"klvl\", " +
                "\"type\": \"attached\" " +
                "}");
    }

    // See types https://mimetype.io/all-types/
    @Attachment(value = "text-attachment", type = "text/plain", fileExtension = ".txt")
    public String attachTextAsString(String fileContent) {
        return fileContent;
    }

    @Attachment(value = "json-attachment", type = "application/json", fileExtension = ".json")
    public byte[] attachJsonAsByte(String content) {
        return content.getBytes(StandardCharsets.UTF_8);
    }
}
