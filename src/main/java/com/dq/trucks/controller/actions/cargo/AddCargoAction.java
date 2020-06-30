package com.dq.trucks.controller.actions.cargo;

import com.dq.trucks.controller.Action;
import com.dq.trucks.helpers.ConstraintsUtil;
import com.dq.trucks.helpers.IdsService;
import com.dq.trucks.model.PersistenceManager;
import com.dq.trucks.model.entities.Cargo;
import com.dq.trucks.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddCargoAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        Cargo cargo = new Cargo();

        cargo.setId(IdsService.getNext());
        cargo.setName(view.getPropertyCancellable("name"));
        cargo.setType(view.getPropertyCancellable("type"));

        persistence.create(cargo);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Add Cargo";
    }
}
