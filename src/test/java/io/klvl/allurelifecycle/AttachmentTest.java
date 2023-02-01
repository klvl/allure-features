package io.klvl.allurelifecycle;

import io.qameta.allure.Allure;
import org.testng.annotations.Test;

public class AttachmentTest {

    @Test
    public void testSimpleAttach() {
        Allure.attachment("file-name", "file content");
    }

    @Test
    public void testAddAttachment() {
        Allure.addAttachment("file-name", "file-contant");
    }

    @Test
    public void testAddAttachmentWithType() {
        Allure.addAttachment("file-name", "application/json", "{}");
    }

    @Test
    public void testAddAttachmentWithExtension() {
        Allure.addAttachment("file-name", "application/json", "{}", ".json");
    }

}
