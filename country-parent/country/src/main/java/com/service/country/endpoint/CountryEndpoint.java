package com.service.country.endpoint;

import com.jcraft.jsch.JSchException;
import com.service.country.facade.CountryFacade;
import com.service.country.ws.generated.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@RequiredArgsConstructor
@Endpoint
public class CountryEndpoint {

    private final CountryFacade countryFacade;

    private static final String NAMESPACE_URI = "https://github.com/keramiozsoy/soap-101";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) throws JSchException {
        GetCountryResponse response = new GetCountryResponse();
        final Country country = new Country();
        country.setName("test");
        response.setCountry(country);
        countryFacade.generateXml();
        countryFacade.sendXml();
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ContinentsRequest")
    @ResponsePayload
    public ContinentsResponse getAllContinents(ContinentsRequest request){
        countryFacade.getAllContinents();
        return null;
    }

}
