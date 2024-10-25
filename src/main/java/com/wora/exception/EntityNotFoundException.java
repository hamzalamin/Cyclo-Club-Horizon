package com.wora.exception;

import com.wora.models.entities.embeddables.GeneralResultId;

import javax.swing.text.html.parser.Entity;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String entity, GeneralResultId id) {
        super(entity + " with the id " + id + " not found");
    }

}
