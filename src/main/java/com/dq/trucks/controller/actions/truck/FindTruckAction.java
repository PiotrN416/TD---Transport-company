package com.dq.trucks.controller.actions.truck;


import com.dq.trucks.controller.Action;
import com.dq.trucks.helpers.ConstraintsUtil;
import com.dq.trucks.model.PersistenceManager;
import com.dq.trucks.model.entities.Truck;
import com.dq.trucks.view.View;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class FindTruckAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        String result = persistence.findById(view.getValidNumberPropertyCancellable("id"), Truck.class);
        view.showMessage(result);
        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Find Truck";
    }
}
