package com.service.country.file;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
class DataExampleObject {
    private final String id;
    private final String name;
    private final String currency;
    private final String capitalCity;
}
