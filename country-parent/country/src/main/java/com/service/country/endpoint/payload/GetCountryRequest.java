package com.service.country.endpoint.payload;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Setter
@Getter
@XmlType(name = "GetCountryRequest", namespace = "https://github.com/keramiozsoy/soap-101")
//@XmlRootElement(name = "GetCountryRequest", namespace = "https://github.com/keramiozsoy/soap-101")
public class GetCountryRequest {
    @XmlElement(name = "name", required = true)
    private String name;
}
