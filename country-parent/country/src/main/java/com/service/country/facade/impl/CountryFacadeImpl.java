package com.service.country.facade.impl;

import com.jcraft.jsch.JSchException;
import com.service.country.facade.CountryFacade;
import com.service.country.service.GenerateXmlService;
import com.service.country.service.SendXmlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CountryFacadeImpl implements CountryFacade {

    private final GenerateXmlService generateXmlService;
    private final SendXmlService sendXmlService;

    @Override
    public List<String> getAllContinents() {
      return null;
    }

    @Override
    public void generateXml() {
        generateXmlService.generateXml();
    }

    @Override
    public List<String> sendXml() throws JSchException {
        return sendXmlService.sendXml();
    }
}
