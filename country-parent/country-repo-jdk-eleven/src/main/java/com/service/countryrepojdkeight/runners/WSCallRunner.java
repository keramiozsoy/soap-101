package com.service.countryrepojdkeight.runners;

import org.oorsprong.websamples.*;
import org.oorsprong.websamples_countryinfo.CountryInfoService;
import org.oorsprong.websamples_countryinfo.CountryInfoServiceSoapType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URL;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class WSCallRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        final String endpoint = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso";

        final URL url = URI.create(endpoint).toURL();

        CountryInfoService service = new CountryInfoService(url);

        CountryInfoServiceSoapType port = service.getPort(CountryInfoServiceSoapType.class);

        final ArrayOftContinent listOfContinentsByCode = port.listOfContinentsByCode();

        listOfContinentsByCode.getTContinent().stream()
                .map(TContinent::getSName)
                .collect(Collectors.toUnmodifiableList())
                .forEach(System.out::println);

        listOfContinentsByCode.getTContinent().stream()
                .map(TContinent::getSCode)
                .collect(Collectors.toUnmodifiableList())
                .forEach(System.out::println);


        // ---

        final ArrayOftContinent listOfContinentsByName = port.listOfContinentsByName();

        listOfContinentsByName.getTContinent().stream()
                .map(TContinent::getSName)
                .collect(Collectors.toUnmodifiableList())
                .forEach(System.out::println);

        listOfContinentsByName.getTContinent().stream()
                .map(TContinent::getSCode)
                .collect(Collectors.toUnmodifiableList())
                .forEach(System.out::println);


        // ---

        final ArrayOftCountryCodeAndName listOfCountryNamesByCode = port.listOfCountryNamesByCode();

        listOfCountryNamesByCode.getTCountryCodeAndName().stream()
                .map(TCountryCodeAndName::getSISOCode)
                .collect(Collectors.toUnmodifiableList())
                .forEach(System.out::println);

        listOfCountryNamesByCode.getTCountryCodeAndName().stream()
                .map(TCountryCodeAndName::getSName)
                .collect(Collectors.toUnmodifiableList())
                .forEach(System.out::println);

        // ---
        final ArrayOftCountryCodeAndName listOfCountryNamesByName = port.listOfCountryNamesByName();

        listOfCountryNamesByName.getTCountryCodeAndName().stream()
                .map(TCountryCodeAndName::getSISOCode)
                .collect(Collectors.toUnmodifiableList())
                .forEach(System.out::println);

        listOfCountryNamesByName.getTCountryCodeAndName().stream()
                .map(TCountryCodeAndName::getSName)
                .collect(Collectors.toUnmodifiableList())
                .forEach(System.out::println);

        // ---

        final ArrayOftCountryCodeAndNameGroupedByContinent listOfCountryNamesGroupedByContinent = port.listOfCountryNamesGroupedByContinent();

        listOfCountryNamesGroupedByContinent.getTCountryCodeAndNameGroupedByContinent().stream()
                .map(TCountryCodeAndNameGroupedByContinent::getContinent)
                .collect(Collectors.toUnmodifiableList())
                .forEach(System.out::println);

        listOfCountryNamesGroupedByContinent.getTCountryCodeAndNameGroupedByContinent().stream()
                .map(TCountryCodeAndNameGroupedByContinent::getCountryCodeAndNames)
                .collect(Collectors.toUnmodifiableList())
                .forEach(System.out::println);

        // ---

        final ArrayOftCurrency listOfCurrenciesByCode = port.listOfCurrenciesByCode();

        listOfContinentsByCode.getTContinent().stream()
                .map(TContinent::getSCode)
                .collect(Collectors.toUnmodifiableList())
                .forEach(System.out::println);

        listOfContinentsByCode.getTContinent().stream()
                .map(TContinent::getSName)
                .collect(Collectors.toUnmodifiableList())
                .forEach(System.out::println);

        // ---

        final ArrayOftCurrency listOfCurrenciesByName = port.listOfCurrenciesByName();

        listOfCurrenciesByName.getTCurrency().stream()
                .map(TCurrency::getSISOCode)
                .collect(Collectors.toUnmodifiableList())
                .forEach(System.out::println);

        listOfCurrenciesByName.getTCurrency().stream()
                .map(TCurrency::getSName)
                .collect(Collectors.toUnmodifiableList())
                .forEach(System.out::println);

        // ---

        final ArrayOftLanguage listOfLanguagesByCode = port.listOfLanguagesByCode();

        listOfLanguagesByCode.getTLanguage().stream()
                .map(TLanguage::getSISOCode)
                .collect(Collectors.toUnmodifiableList())
                .forEach(System.out::println);

        listOfLanguagesByCode.getTLanguage().stream()
                .map(TLanguage::getSName)
                .collect(Collectors.toUnmodifiableList())
                .forEach(System.out::println);


        // ---

        final ArrayOftLanguage listOfLanguagesByName = port.listOfLanguagesByName();

        listOfLanguagesByName.getTLanguage().stream()
                .map(TLanguage::getSISOCode)
                .collect(Collectors.toUnmodifiableList())
                .forEach(System.out::println);

        listOfLanguagesByName.getTLanguage().stream()
                .map(TLanguage::getSName)
                .collect(Collectors.toUnmodifiableList())
                .forEach(System.out::println);

        // ---


        final TCountryInfo tCountryInfo = port.fullCountryInfo(
                listOfCountryNamesByCode.getTCountryCodeAndName().stream()
                        .findAny().orElseThrow().getSISOCode()
        );

        System.out.println(tCountryInfo.getSCountryFlag());


        // ---

        System.out.println(
                port.countryFlag(
                    listOfCountryNamesByCode.getTCountryCodeAndName().stream()
                            .findAny().orElseThrow().getSISOCode()
                )
        );


    }
}
