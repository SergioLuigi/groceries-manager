package br.com.sergioluigi.groceriesmanager.infrastructure.database.repository;

import br.com.sergioluigi.groceriesmanager.infrastructure.database.document.ProductDocument;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<ProductDocument, ObjectId> {
    public Boolean existsByName(String name);

    Optional<ProductDocument> findByName(String name);

    Page<ProductDocument> findByNameStartingWithIgnoreCase(String name, Pageable pageable);
}
