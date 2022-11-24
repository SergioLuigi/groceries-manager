package br.com.sergioluigi.groceriesmanager.infrastructure.database.repository;

import br.com.sergioluigi.groceriesmanager.infrastructure.database.document.TagDocument;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TagRepository extends MongoRepository<TagDocument, ObjectId>{

    Boolean existsByDescription(String description);

    Boolean existsByDescriptionIn(List<String> descriptions);

    Page<TagDocument> findByDescriptionStartingWithIgnoreCase(String description, Pageable pageable);



}
