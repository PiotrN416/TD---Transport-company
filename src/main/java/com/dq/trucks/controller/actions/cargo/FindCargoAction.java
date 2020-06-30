package com.dq.trucks.controller.actions.cargo;


import com.dq.trucks.controller.Action;
import com.dq.trucks.helpers.ConstraintsUtil;
import com.dq.trucks.model.PersistenceManager;
import com.dq.trucks.model.entities.Cargo;
import com.dq.trucks.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindCargoAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        String result = persistence.findById(view.getValidNumberPropertyCancellable("id"), Cargo.class);
        view.showMessage(result);
        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Find Cargo";
    }
}
