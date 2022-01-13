package com.service.countryrepojdkeight.runners;

import org.oorsprong.websamples.ArrayOftContinent;
import org.oorsprong.websamples.TContinent;
import org.oorsprong.websamples_countryinfo.CountryInfoService;
import org.oorsprong.websamples_countryinfo.CountryInfoServiceSoapType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URL;
import java.util.stream.Collectors;

@Component
public class WSCallRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        final String endpoint = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso";

        final URL url = URI.create(endpoint).toURL();

        CountryInfoService service = new CountryInfoService(url);

        CountryInfoServiceSoapType port = service.getPort(CountryInfoServiceSoapType.class);

        final ArrayOftContinent arrayOftContinent = port.listOfContinentsByCode();

        arrayOftContinent.getTContinent().stream()
                .map(TContinent::getSName)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
}
