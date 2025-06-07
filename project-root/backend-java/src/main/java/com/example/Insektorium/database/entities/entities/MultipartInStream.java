package com.example.Insektorium.database.entities.entities;

import org.springframework.core.io.InputStreamResource;

import java.io.InputStream;

public class MultipartInStream extends InputStreamResource {
    private final String filename;

    public MultipartInStream(InputStream inputStream, String filename) {
        super(inputStream);
        this.filename = filename;
    }

    @Override
    public String getFilename() {
        return filename;
    }

    @Override
    public long contentLength() {
        return -1;
    }
}
