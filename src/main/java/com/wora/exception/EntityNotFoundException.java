package com.wora.exception;

import com.wora.models.entities.embeddables.GeneralResultId;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String entity, GeneralResultId id) {
        super(entity + " with the id " + id + " not found");
    }

}
