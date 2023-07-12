package com.psifiacos.um_api.services;

import com.psifiacos.um_api.dtos.UnitDto;
import com.psifiacos.um_api.dtos.UnitDtoResponse;
import com.psifiacos.um_api.model.documents.Category;
import com.psifiacos.um_api.model.documents.DataType;
import com.psifiacos.um_api.model.documents.Unit;
import com.psifiacos.um_api.model.repositories.UnitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<UnitDtoResponse> findUnits(String name, String symbol, String category, String dataType) {
        Query query = new Query();
        if (name != null) query.addCriteria(Criteria.where("name").is(name));
        if (symbol != null) query.addCriteria(Criteria.where("symbol").is(symbol));
        if (category != null) query.addCriteria(Criteria.where("category").is(category));
        if (dataType != null) {
            DataType data = dataTypeService.findByNativeIdentifier(dataType);
            query.addCriteria(Criteria.where("category").is(data.getCategory()));
        }
        List<Unit> units = findAll(query);
        List<UnitDtoResponse> responses = new ArrayList<>();
        for (Unit unit : units) {
            responses.add(UnitDtoResponse.builder()
                    .id(unit.getId())
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
