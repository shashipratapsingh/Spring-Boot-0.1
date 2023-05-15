package com.kisaan.jai.service;

import java.io.IOException;

public interface ImageService {

    byte[] getImage(String folderName, String fileName) throws IOException;

    byte[] putImage(String folderName, String fileName);
}
