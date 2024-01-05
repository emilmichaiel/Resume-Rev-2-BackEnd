package com.emilmi.resume.common;

import org.springframework.stereotype.Component;

@Component
public class ToLowerCaseAndTrimTransformationMapper {
    @ToLowerCaseAndTrimTransformation
    public String transform(String inputString) {
        return inputString == null ?
                null :
                inputString.toLowerCase().trim();
    }
}
