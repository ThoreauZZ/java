package com.thoreau.bootspock.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * 2019/3/28 11:56 PM.
 *
 * @author zhaozhou
 */
public class ImageNameValidator {
    @Getter
    @Setter
    class CheckResult{
        private int code;
        private Boolean result;
        private String message;
    }

    public CheckResult check(String fileName) {
        CheckResult checkResult = new CheckResult();
        checkResult.setResult(false);
        if (fileName.endsWith(".JPG") || fileName.endsWith(".PNG")) {
            checkResult.setResult(true);
            return checkResult;
        }
        checkResult.setMessage("files are not supported");
        return checkResult;
    }

    public boolean isValidImageExtension(String fileName) {
        if (fileName.endsWith(".JPG") || fileName.endsWith(".PNG")) {
            return true;
        }
        return false;
    }
}
