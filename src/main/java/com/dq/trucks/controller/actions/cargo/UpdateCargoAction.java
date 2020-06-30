package com.dq.trucks.controller.actions.cargo;

import com.dq.trucks.controller.Action;
import com.dq.trucks.helpers.ConstraintsUtil;
import com.dq.trucks.model.PersistenceManager;
import com.dq.trucks.model.entities.Cargo;
import com.dq.trucks.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateCargoAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        Integer id = view.getValidNumberPropertyCancellable("id");
        String field = view.getPropertyCancellable("update what");
        String fieldVal = view.getPropertyCancellable("update to");
        persistence.update(id, field, fieldVal, Cargo.class);
        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Update Cargo";
    }
}
