package com.service.country.endpoint.payload;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Setter
@Getter
//@XmlRootElement(name = "ContinentsRequest", namespace = "https://github.com/keramiozsoy/soap-101")
@XmlType(name = "ContinentsRequest", namespace = "https://github.com/keramiozsoy/soap-101")
public class ContinentsRequest {
    @XmlElement(name = "name", required = true)
    private String name;
}
