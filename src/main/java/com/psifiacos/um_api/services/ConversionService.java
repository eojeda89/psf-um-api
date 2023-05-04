package com.psifiacos.um_api.services;

import com.psifiacos.um_api.dtos.ConversionDto;
import com.psifiacos.um_api.dtos.ConversionRequest;
import com.psifiacos.um_api.dtos.ConversionResponse;
import com.psifiacos.um_api.model.documents.Conversion;

import java.util.List;

public interface ConversionService {
    Conversion save(Conversion conversion);
    Conversion delete(Conversion conversion);
    Conversion findById(String id);
    Conversion findByFromAndTo(String from, String to);

    ConversionResponse convert(ConversionRequest conversionRequest);

    ConversionDto createConversion(ConversionDto conversionDto);

    List<ConversionDto> createManyConversion(List<ConversionDto> conversionDtos);
}
