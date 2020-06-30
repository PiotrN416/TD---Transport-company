package com.dq.trucks.controller;

import com.dq.trucks.helpers.IdsService;
import lombok.Getter;

public abstract class SelectOption<T> {

    @Getter
    private long optionId;

    public SelectOption() {
        optionId = IdsService.getNext();
    }

    public abstract T execute();

    public abstract String getOptionLabel();

}
