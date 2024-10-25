package com.wora.exception;

import com.wora.models.entities.embeddables.GeneralResultId;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String entity, Object id) {
        super(entity + " with the id " + id + " not found");
    }

}
