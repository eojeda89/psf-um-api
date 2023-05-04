package com.psifiacos.um_api.services;

import com.psifiacos.um_api.model.documents.DataType;

import java.util.List;

public interface DataTypeService {

    DataType save(DataType dataType);

    List<DataType> save(List<DataType> dataTypes);

    DataType findById(String id);

    DataType findByNativeIdentifier(String nativeIdentifier);
}
