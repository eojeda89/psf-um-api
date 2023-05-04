package com.psifiacos.um_api.controllers;

import com.psifiacos.um_api.dtos.ConversionDto;
import com.psifiacos.um_api.dtos.ConversionRequest;
import com.psifiacos.um_api.dtos.ConversionResponse;
import com.psifiacos.um_api.services.ConversionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/convert")
@Validated
@Slf4j
@RequiredArgsConstructor
public class ConversionController {

    private final ConversionService conversionService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ConversionResponse> convert(@Valid @RequestBody ConversionRequest conversionRequest) {
        return new ResponseEntity<>(conversionService.convert(conversionRequest), OK);
    }

    @PostMapping(path = "/addOne", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ConversionDto> createConversion(@Valid @RequestBody ConversionDto conversionDto) {
        return new ResponseEntity<>(conversionService.createConversion(conversionDto), OK);
    }

    @PostMapping(path = "/addMany", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<ConversionDto>> createManyConversion(@Valid @RequestBody List<ConversionDto> conversions) {
        return new ResponseEntity<>(conversionService.createManyConversion(conversions), OK);
    }
}
