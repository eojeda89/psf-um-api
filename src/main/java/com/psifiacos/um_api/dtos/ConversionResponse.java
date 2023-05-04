package com.psifiacos.um_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConversionResponse {
    private String from;
    private String to;
    private String category;
    private float value;
    private float convertedValue;
}
