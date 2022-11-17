package br.com.sergioluigi.groceriesmanager.core.usecase.tag.tagregister.port.out;

import br.com.sergioluigi.groceriesmanager.adapter.database.document.TagDocument;
import br.com.sergioluigi.groceriesmanager.core.domain.Tag;

public interface TagRegisterOutPort {
    TagDocument registerTag(Tag tag);
}
