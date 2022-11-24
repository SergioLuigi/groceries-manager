package br.com.sergioluigi.groceriesmanager.domain.usecase.ports.out;

import br.com.sergioluigi.groceriesmanager.domain.model.Tag;

public interface TagRegisterOutPort {
    Tag tagRegister(Tag tag);
}
