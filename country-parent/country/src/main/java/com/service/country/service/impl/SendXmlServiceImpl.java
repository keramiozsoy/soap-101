package com.service.country.service.impl;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.service.country.file.XMLFileOperation;
import com.service.country.ftp.SftpConnector;
import com.service.country.propeties.PropertyReader;
import com.service.country.service.SendXmlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class SendXmlServiceImpl implements SendXmlService {

    private final PropertyReader propertyReader;

    @Override
    public List<String> sendXml() throws JSchException {
        List<String> errorFileList = new ArrayList<>();
        SftpConnector connector = null;
        try {
            connector = new SftpConnector(
                    propertyReader.getUsername(), propertyReader.getPassword(),
                    propertyReader.getHostname(), propertyReader.getPort()
            );
            log.info("FTP connector connected.");
            final Optional<List<File>> files = XMLFileOperation.readFiles(System.getProperty("user.home") + File.separator + propertyReader.getGeneratedFolder());
            if (files.isPresent()) {
                for (File file : files.get()) {
                    try {
                        connector.put(propertyReader.getUploadFtpFolder(), file.toString(), file.getName());
                        log.info("FTP connector put success. Filename -> [{}]", file.getName());
                    } catch (IOException | SftpException e) {
                        if (e instanceof IOException) {
                            errorFileList.add(String.format("File not found in path: %s ", file.getName()));
                        }
                        if (e instanceof SftpException) {
                            errorFileList.add(String.format("File transfer failed %s ", file.getName()));
                        }
                    }
                }

            }
        } finally {
            if (connector != null) {
                connector.close();
                log.info("FTP connector disconnected.");
            }
        }
        return errorFileList;
    }
}
