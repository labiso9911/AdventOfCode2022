package dk.lbi.adventofcode.utils;

import java.io.File;
import java.net.URL;

public class GetResourceFile {
    public File getFile(String fileName) {
        URL resource = getClass().getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        } else {

            // failed if files have whitespaces or special characters
            return new File(resource.getFile());

            //return new File(resource.toURI());
        }
    }
}
