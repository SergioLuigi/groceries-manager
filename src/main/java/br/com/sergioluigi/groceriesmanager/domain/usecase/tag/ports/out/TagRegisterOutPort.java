package br.com.sergioluigi.groceriesmanager.domain.usecase.tag.ports.out;

import br.com.sergioluigi.groceriesmanager.domain.model.Tag;

public interface TagRegisterOutPort {
    Tag tagRegister(Tag tag);
}
