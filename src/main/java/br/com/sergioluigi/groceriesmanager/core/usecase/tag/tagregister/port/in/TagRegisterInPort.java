package br.com.sergioluigi.groceriesmanager.core.usecase.tag.tagregister.port.in;

import br.com.sergioluigi.groceriesmanager.core.domain.Tag;

public interface TagRegisterInPort {

    void execute(Tag tag);
    
}