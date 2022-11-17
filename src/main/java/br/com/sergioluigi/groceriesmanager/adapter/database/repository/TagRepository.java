package br.com.sergioluigi.groceriesmanager.adapter.database.repository;

import br.com.sergioluigi.groceriesmanager.adapter.database.document.TagDocument;
import br.com.sergioluigi.groceriesmanager.core.domain.Tag;
import br.com.sergioluigi.groceriesmanager.core.usecase.tag.tagregister.port.out.TagExistsOutPort;
import br.com.sergioluigi.groceriesmanager.core.usecase.tag.tagregister.port.out.TagRegisterOutPort;
import org.bson.types.ObjectId;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

@JaversSpringDataAuditable
public interface TagRepository
        extends MongoRepository<TagDocument, ObjectId>,
                TagRegisterOutPort,
                TagExistsOutPort {

    @Override
    default TagDocument registerTag(Tag tag){
        var tagDocumentToSave = new TagDocument(tag.getDescription());
        return save(tagDocumentToSave);
    }

    @Override
    Optional<Boolean> existsByDescription(String description);
}
