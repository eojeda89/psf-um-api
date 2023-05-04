package com.psifiacos.um_api.services;

import com.psifiacos.um_api.dtos.ConversionDto;
import com.psifiacos.um_api.dtos.ConversionRequest;
import com.psifiacos.um_api.dtos.ConversionResponse;
import com.psifiacos.um_api.model.documents.Conversion;
import com.psifiacos.um_api.model.documents.Unit;
import com.psifiacos.um_api.model.repositories.ConversionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConversionServiceImpl implements ConversionService {
    private final ConversionRepository conversionRepository;
    private final UnitService unitService;

    @Override
    public Conversion save(Conversion conversion) {
        return conversionRepository.save(conversion);
    }

    @Override
    public Conversion delete(Conversion conversion) {
        conversionRepository.delete(conversion);
        return conversion;
    }

    @Override
    public Conversion findById(String id) {
        return conversionRepository.findById(id).orElseThrow();
    }

    @Override
    public Conversion findByFromAndTo(String from, String to) {
        return conversionRepository.findByFromAndTo(from, to).orElseThrow();
    }

    @Override
    public ConversionResponse convert(ConversionRequest conversionRequest) {
        Unit from = unitService.findByName(conversionRequest.getFrom());
        Unit to = unitService.findByName(conversionRequest.getTo());
        Conversion conversion = findByFromAndTo(from.getId(), to.getId());
        float convertedValue = 0.0f;
        switch (conversion.getOperation()) {
            case multiplication -> convertedValue = conversionRequest.getValue() * conversion.getFactor();
            case division -> convertedValue = conversionRequest.getValue() / conversion.getFactor();
            case sum -> convertedValue = conversionRequest.getValue() + conversion.getFactor();
            case rest -> convertedValue = conversionRequest.getValue() - conversion.getFactor();
        }
        return ConversionResponse.builder()
                .from(from.getName())
                .to(to.getName())
                .category(from.getCategory().getName())
                .value(conversionRequest.getValue())
                .convertedValue(convertedValue)
                .build();
    }

    @Override
    public ConversionDto createConversion(ConversionDto conversionDto) {
        Unit from = unitService.findByName(conversionDto.getFrom());
        Unit to = unitService.findByName(conversionDto.getTo());
        if (!conversionRepository.existsByFromAndTo(from.getId(), to.getId())) {
            Conversion conversion = Conversion.builder()
                    .from(from.getId())
                    .to(to.getId())
                    .operation(conversionDto.getOperation())
                    .factor(conversionDto.getFactor())
                    .build();
            save(conversion);
            return conversionDto;
        }
        throw new NoSuchElementException();
    }

    @Override
    public List<ConversionDto> createManyConversion(List<ConversionDto> conversions) {
        List<ConversionDto> result = new ArrayList<>();
        for (ConversionDto conversionDto : conversions) {
            try {
                createConversion(conversionDto);
                result.add(conversionDto);
            }catch (Exception ignored) {}
        }
        return result;
    }
}
