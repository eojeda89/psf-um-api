package com.psifiacos.um_api.model.documents;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document("conversions")
public class Conversion {
    private String id;
    @NotNull
    private String from;
    @NotNull
    private String to;
    @NotNull
    private Operation operation;
    @NotNull
    private float factor;
}
