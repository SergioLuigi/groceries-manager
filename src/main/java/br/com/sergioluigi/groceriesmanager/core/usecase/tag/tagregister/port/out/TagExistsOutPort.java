package br.com.sergioluigi.groceriesmanager.core.usecase.tag.tagregister.port.out;

import java.util.Optional;

public interface TagExistsOutPort {
    Boolean existsByDescription(String description);
}
