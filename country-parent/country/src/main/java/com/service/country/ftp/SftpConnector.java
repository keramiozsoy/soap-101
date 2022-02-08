package com.service.country.ftp;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

@Slf4j
public class SftpConnector {
    private final JSch jSch = new JSch();
    private Session session = null;
    private ChannelSftp channel = null;

    public SftpConnector(String username, String password, String host, int port) throws JSchException {
        createSession(username, password, host, port);
        connect();
    }

    private void createSession(String username, String password, String host, int port) throws JSchException {
        Properties properties = new Properties();
        properties.put("StrictHostKeyChecking", "no");
        session = jSch.getSession(username, host, port);
        session.setTimeout(3000);
        session.setPassword(password);
        session.setConfig(properties);
    }

    private void connect() throws JSchException {
        session.connect();
        channel = (ChannelSftp) session.openChannel("sftp");
        channel.connect();
    }

    public void put(String ftpPath, String localPath, String filename) throws SftpException, FileNotFoundException {
        if (checkParams(ftpPath, filename) && checkChannelStatus()) {
            channel.cd(ftpPath);
            channel.put(new FileInputStream(localPath), filename);
            log.info("[{}]  -> was uploaded to FTP server", filename);
        }
    }

    private boolean checkParams(String path, String filename) {
        return StringUtils.isNotBlank(path) && StringUtils.isNotBlank(filename);
    }

    private boolean checkChannelStatus() {
        return channel.isConnected() && !channel.isClosed();
    }

    public void close() {
        if (channel != null) {
            channel.exit();
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }

}
