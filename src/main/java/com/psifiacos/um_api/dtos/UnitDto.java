package com.psifiacos.um_api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UnitDto {
    @NotBlank
    private String name;
    @NotEmpty
    private Set<String> symbols;
    @NotBlank
    private String category;
}
