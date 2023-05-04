package com.psifiacos.um_api.model.documents;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document("data-types")
public class DataType {
    private String id;
    private String nativeIdentifier;
    private Category category;
    private String defaultUnit;
}
