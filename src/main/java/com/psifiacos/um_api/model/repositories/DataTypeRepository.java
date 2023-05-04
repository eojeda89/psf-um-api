package com.psifiacos.um_api.model.repositories;

import com.psifiacos.um_api.model.documents.DataType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataTypeRepository extends MongoRepository<DataType, String> {
    Optional<DataType> findByNativeIdentifier(String nativeIdentifier);
}
