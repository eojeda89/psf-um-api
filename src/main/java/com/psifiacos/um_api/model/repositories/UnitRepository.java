package com.psifiacos.um_api.model.repositories;

import com.psifiacos.um_api.model.documents.Unit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitRepository extends MongoRepository<Unit, String> {
    Optional<Unit> findByName(String name);
}
