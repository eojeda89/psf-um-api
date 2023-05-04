package com.psifiacos.um_api.services;

import com.psifiacos.um_api.dtos.UnitDto;
import com.psifiacos.um_api.model.documents.Category;
import com.psifiacos.um_api.model.documents.DataType;
import com.psifiacos.um_api.model.documents.Unit;
import com.psifiacos.um_api.model.repositories.UnitRepository;
import com.psifiacos.um_api.model.repositories.support.FilterCondition;
import com.psifiacos.um_api.model.repositories.support.GenericFilterCriteriaBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.psifiacos.um_api.model.repositories.support.FilterOperationEnum.IN;
import static com.psifiacos.um_api.model.repositories.support.FilterOperationEnum.IS;

@Service
@Slf4j
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;
    private final DataTypeService dataTypeService;
    private final MongoTemplate mongoTemplate;

    @Override
    public Unit save(Unit unit) {
        return unitRepository.save(unit);
    }

    @Override
    public List<Unit> saveAll(List<Unit> units) {
        return unitRepository.saveAll(units);
    }

    @Override
    public UnitDto findById(String id) {
        Unit unit = unitRepository.findById(id).orElseThrow();
        return UnitDto.builder()
                .name(unit.getName())
                .symbols(unit.getSymbols())
                .category(unit.getCategory().getName())
                .build();
    }

    @Override
    public List<UnitDto> findUnits(String name, String symbol, String category, String dataType) {
        GenericFilterCriteriaBuilder filterCriteriaBuilder = new GenericFilterCriteriaBuilder();
        List<FilterCondition> filterAnd = new ArrayList<>();
        if (name != null) {
            FilterCondition nameFilter = new FilterCondition("name", IS, name);
            filterAnd.add(nameFilter);
        }
        if (symbol != null) {
            FilterCondition symbolFilter = new FilterCondition("symbol", IN, symbol);
            filterAnd.add(symbolFilter);
        }
        if (category != null) {
            FilterCondition categoryFilter = new FilterCondition("category.name", IS, category);
            filterAnd.add(categoryFilter);
        }
        if (dataType != null) {
            DataType data = dataTypeService.findByNativeIdentifier(dataType);
            FilterCondition dataTypeFilter = new FilterCondition("category.name", IS, data.getCategory());
            filterAnd.add(dataTypeFilter);
        }
        Query query = filterCriteriaBuilder.addCondition(filterAnd, new ArrayList<>());
        List<Unit> units = findAll(query);
        List<UnitDto> responses = new ArrayList<>();
        for (Unit unit : units) {
            responses.add(UnitDto.builder()
                    .name(unit.getName())
                    .symbols(unit.getSymbols())
                    .category(unit.getCategory().getName())
                    .build());
        }
        return responses;
    }

    @Override
    public List<Unit> findAll(Query query) {
        return mongoTemplate.find(query, Unit.class);
    }

    @Override
    public UnitDto create(UnitDto unitRequest) {
        Unit unit = new Unit();
        BeanUtils.copyProperties(unitRequest, unit);
        unit.setCategory(new Category(unitRequest.getCategory()));
        save(unit);
        return unitRequest;
    }

    @Override
    public UnitDto addSymbol(String id, String symbol) {
        Unit unit = unitRepository.findById(id).orElseThrow();
        unit.addSymbol(symbol);
        save(unit);
        return UnitDto.builder()
                .name(unit.getName())
                .symbols(unit.getSymbols())
                .category(unit.getCategory().getName())
                .build();
    }

    @Override
    public UnitDto deleteSymbol(String id, String symbol) {
        Unit unit = unitRepository.findById(id).orElseThrow();
        unit.deleteSymbol(symbol);
        save(unit);
        return UnitDto.builder()
                .name(unit.getName())
                .symbols(unit.getSymbols())
                .category(unit.getCategory().getName())
                .build();
    }

    @Override
    public Unit findByName(String name) {
        return unitRepository.findByName(name).orElseThrow();
    }

    @Override
    public List<UnitDto> createMany(List<UnitDto> unitRequests) {
        List<UnitDto> result = new ArrayList<>();
        for (UnitDto unitDto : unitRequests) {
            try {
                create(unitDto);
                result.add(unitDto);
            }catch (Exception ignored) {}
        }
        return result;
    }
}
