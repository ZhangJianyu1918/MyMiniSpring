package org.spring.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassPathResource implements Resource{

    private final String classPath;

    public ClassPathResource(String classPath) {
        this.classPath = classPath;
    }


    @Override
    public InputStream getInputStream() throws IOException {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(this.classPath);
        if (resourceAsStream == null) {
            throw new FileNotFoundException(this.classPath + "cannot be opened because it doesn't exist.");
        }
        return resourceAsStream;
    }
}
