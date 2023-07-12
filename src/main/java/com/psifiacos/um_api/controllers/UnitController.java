package com.psifiacos.um_api.controllers;

import com.psifiacos.um_api.dtos.UnitDto;
import com.psifiacos.um_api.dtos.UnitDtoResponse;
import com.psifiacos.um_api.services.UnitService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/v1/units")
@Validated
@Slf4j
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<UnitDtoResponse>> findUnits(@RequestParam(required = false, value = "name") String name,
                                                           @RequestParam(required = false, value = "symbol") String symbol,
                                                           @RequestParam(required = false, value = "category") String category,
                                                           @RequestParam(required = false, value = "dataType") String dataType) {
        return new ResponseEntity<>(unitService.findUnits(name, symbol, category, dataType), OK);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<UnitDto> findUnit(@PathVariable String id) {
        return new ResponseEntity<>(unitService.findById(id), OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UnitDto> createUnit(@Valid @RequestBody UnitDto unitRequest) {
        return new ResponseEntity<>(unitService.create(unitRequest), OK);
    }

    @PostMapping(path = "/many", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<UnitDto>> createManyUnits(@Valid @RequestBody List<UnitDto> unitRequests) {
        return new ResponseEntity<>(unitService.createMany(unitRequests), OK);
    }

    @PatchMapping(path = "/{id}/add-symbol", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UnitDto> addSymbol(@NotBlank @NotNull @PathVariable String id,
                                             @NotBlank @NotNull @RequestBody String symbol) {
        return new ResponseEntity<>(unitService.addSymbol(id, symbol), OK);
    }

    @PatchMapping(path = "/{id}/delete-symbol", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UnitDto> deleteSymbol(@NotBlank @NotNull @PathVariable String id,
                                             @NotBlank @NotNull @RequestBody String symbol) {
        return new ResponseEntity<>(unitService.deleteSymbol(id, symbol), OK);
    }
}
