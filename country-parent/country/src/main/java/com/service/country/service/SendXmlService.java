package com.service.country.service;

import com.jcraft.jsch.JSchException;

import java.util.List;

public interface SendXmlService {
    List<String> sendXml() throws JSchException;
}
