package com.psifiacos.um_api.model.repositories;

import com.psifiacos.um_api.model.documents.Conversion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConversionRepository extends MongoRepository<Conversion, String> {
    Optional<Conversion> findByFromAndTo(String from, String to);

    boolean existsByFromAndTo(String from, String to);
}
