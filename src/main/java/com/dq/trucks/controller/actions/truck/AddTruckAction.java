package com.dq.trucks.controller.actions.truck;

import com.dq.trucks.controller.Action;
import com.dq.trucks.helpers.ConstraintsUtil;
import com.dq.trucks.helpers.IdsService;
import com.dq.trucks.model.PersistenceManager;
import com.dq.trucks.model.entities.Truck;
import com.dq.trucks.view.View;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class AddTruckAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        Truck truck = new Truck();

        truck.setId(IdsService.getNext());
        truck.setManufacturer(view.getPropertyCancellable("manufacture"));
        truck.setModel(view.getPropertyCancellable("model"));
        truck.setSerialNumber(view.getPropertyCancellable("S/N"));

        persistence.create(truck);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Add Truck";
    }
}
