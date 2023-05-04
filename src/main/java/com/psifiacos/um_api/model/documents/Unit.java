package com.psifiacos.um_api.model.documents;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document("units")
public class Unit {
    private String id;
    private String name;
    private Set<String> symbols;
    private Category category;

    public Set<String> addSymbol(String symbol) {
        this.symbols.add(symbol);
        return this.symbols;
    }

    public Set<String> deleteSymbol(String symbol) {
        this.symbols.remove(symbol);
        return this.symbols;
    }
}
