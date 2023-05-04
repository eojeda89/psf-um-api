package com.psifiacos.um_api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConversionRequest {
    @NotNull
    @NotBlank
    private String from;
    @NotNull
    @NotBlank
    private String to;
    @NotNull
    private float value;
}
