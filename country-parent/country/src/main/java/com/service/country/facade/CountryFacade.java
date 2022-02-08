package com.service.country.facade;

import com.jcraft.jsch.JSchException;

import java.util.List;

public interface CountryFacade {
    List<String> getAllContinents();
    void generateXml();
    List<String> sendXml() throws JSchException;
}
