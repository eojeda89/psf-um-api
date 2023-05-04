package com.psifiacos.um_api.services;

import com.psifiacos.um_api.model.documents.DataType;
import com.psifiacos.um_api.model.repositories.DataTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataTypeServiceImpl implements DataTypeService {

    private final DataTypeRepository dataTypeRepository;

    @Override
    public DataType save(DataType dataType) {
        return dataTypeRepository.save(dataType);
    }

    @Override
    public List<DataType> save(List<DataType> dataTypes) {
        return dataTypeRepository.saveAll(dataTypes);
    }

    @Override
    public DataType findById(String id) {
        return dataTypeRepository.findById(id).orElseThrow();
    }

    @Override
    public DataType findByNativeIdentifier(String nativeIdentifier) {
        return dataTypeRepository.findByNativeIdentifier(nativeIdentifier).orElseThrow();
    }
}
