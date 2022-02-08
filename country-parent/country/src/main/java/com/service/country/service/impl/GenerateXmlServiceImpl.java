package com.service.country.service.impl;

import com.service.country.file.XMLFileOperation;
import com.service.country.propeties.PropertyReader;
import com.service.country.service.GenerateXmlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;

@RequiredArgsConstructor
@Service
public class GenerateXmlServiceImpl implements GenerateXmlService {

    private final PropertyReader propertyReader;

    @Override
    public void generateXml() {
        String path = System.getProperty("user.home") + File.separator + propertyReader.getGeneratedFolder();
        String filename = propertyReader.getFilename();

        XMLFileOperation.createDirectoryIfNotExist(path);
        XMLFileOperation.deleteOldXmlFiles(path);
        XMLFileOperation.writeXmlToPath(XMLFileOperation.prepareXml(), filename, path);
    }
}
