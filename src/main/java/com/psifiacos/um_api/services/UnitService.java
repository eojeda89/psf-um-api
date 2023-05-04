package com.psifiacos.um_api.services;

import com.psifiacos.um_api.dtos.UnitDto;
import com.psifiacos.um_api.model.documents.Unit;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface UnitService {

    Unit save(Unit unit);

    List<Unit> saveAll(List<Unit> units);

    UnitDto findById(String id);

    List<UnitDto> findUnits(String name, String symbol, String category, String dataType);

    List<Unit> findAll(Query query);

    UnitDto create(UnitDto unitRequest);

    UnitDto addSymbol(String id, String symbol);

    UnitDto deleteSymbol(String id, String symbol);

    Unit findByName(String name);

    List<UnitDto> createMany(List<UnitDto> unitRequests);
}
