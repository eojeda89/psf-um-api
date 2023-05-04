package com.psifiacos.um_api.dtos;

import com.psifiacos.um_api.model.documents.Operation;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConversionDto {
    @NotNull
    private String from;
    @NotNull
    private String to;
    @NotNull
    private Operation operation;
    @NotNull
    private float factor;
}
