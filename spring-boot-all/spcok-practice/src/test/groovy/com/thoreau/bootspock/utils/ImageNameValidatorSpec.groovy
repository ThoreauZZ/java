package com.thoreau.bootspock.utils

import spock.lang.Specification
import spock.lang.Unroll

/**
 * 2019/3/29 12:01 AM.
 * @author zhaozhou
 */
class ImageNameValidatorSpec extends Specification {
    def "Valid images are PNG and JPG files (enterprise version)"() {
        given: "an image extension checker"
        ImageNameValidator validator = new ImageNameValidator()

        when: "an image is checked"
        ImageNameValidator.CheckResult result = validator.check(pictureFile)

        then: "expect that only valid filenames are accepted"
        result.result == checkBoolean
        result.message == errMsg

        where: "sample image names are"
        pictureFile       || checkBoolean | errMsg
        "scenery.JPG"     || true         | null
        "house.PNG"       || true         | null
        "sky.tiff"        || false        | "files are not supported"
        "dance_bunny.gif" || false        | "files are not supported"
    }

    @Unroll
    def "Valid images are PNG and JPG files only"() {
        given: "an image extension checker"
        ImageNameValidator validator = new ImageNameValidator()

        expect: "that only valid filenames are accepted"
        validator.isValidImageExtension(pictureFile) == validPicture

        where: "sample image names are"
        pictureFile << ["scenery.JPG", "house.PNG", "sky.tiff", "dance_bunny.gif"]
        validPicture << [true, true, false, false]
    }
    @Unroll
    def "Valid2 images are #pictureFile --> #validPicture "() {
        given: "an image extension checker"
        ImageNameValidator validator = new ImageNameValidator()

        expect: "that only valid filenames are accepted"
        validator.isValidImageExtension(pictureFile) == validPicture

        where: "sample image names are"
        [pictureFile,validPicture] << [["scenery.JPG",true],["sky.tiff",false]]
    }
}
