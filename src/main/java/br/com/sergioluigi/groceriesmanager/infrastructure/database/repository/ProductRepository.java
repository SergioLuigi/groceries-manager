package br.com.sergioluigi.groceriesmanager.infrastructure.database.repository;

import br.com.sergioluigi.groceriesmanager.infrastructure.database.document.ProductDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductDocument, ObjectId> {
    public Boolean existsByName(String name);
}
