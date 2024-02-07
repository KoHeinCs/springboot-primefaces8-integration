package com.ashwetaw.repositories.primary;

import com.ashwetaw.model.biller.Billers;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author heinhtet_aung
 * @created 10/5/2023
 **/
@Repository
public interface BillerRepository extends MongoRepository<Billers, String> {

    @Query(value = "{}", fields = "{ 'billerCategory': 1, '_id': 0 }")
    Set<String> findAllDistinctBillerCategories();

    List<Billers> findByBillerTemplateID(String billerTemplateId);
 }
