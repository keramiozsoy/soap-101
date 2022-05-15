package com.service.country.endpoint.payload;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@Setter
@Getter

//@XmlRootElement(name = "ContinentsRequest", namespace = "https://github.com/keramiozsoy/soap-101")
@XmlType(name = "ContinentsResponse", namespace = "https://github.com/keramiozsoy/soap-101")
public class ContinentsResponse {
    @XmlElement(name = "nameList", required = true)
    private List<String> nameList;
}
