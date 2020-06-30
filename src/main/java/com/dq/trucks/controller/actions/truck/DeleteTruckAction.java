package com.dq.trucks.controller.actions.truck;


import com.dq.trucks.controller.Action;
import com.dq.trucks.helpers.ConstraintsUtil;
import com.dq.trucks.model.PersistenceManager;
import com.dq.trucks.model.entities.Truck;
import com.dq.trucks.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteTruckAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        String field = view.getPropertyCancellable("by field");
        String value = view.getPropertyCancellable("value");

        try {
            persistence.delete(field, Integer.valueOf(value), Truck.class);
        }
        catch(NumberFormatException e){
            persistence.delete(field, value, Truck.class);
        }

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Delete Truck";
    }
}
