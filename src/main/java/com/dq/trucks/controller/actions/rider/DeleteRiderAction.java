package com.dq.trucks.controller.actions.rider;


import com.dq.trucks.controller.Action;
import com.dq.trucks.helpers.ConstraintsUtil;
import com.dq.trucks.model.PersistenceManager;
import com.dq.trucks.model.entities.Rider;
import com.dq.trucks.view.View;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class DeleteRiderAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        String field = view.getPropertyCancellable("by field");
        String value = view.getPropertyCancellable("value");

        try {
            persistence.delete(field, Integer.valueOf(value), Rider.class);
        }
        catch(NumberFormatException e){
            persistence.delete(field, value, Rider.class);
        }

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Delete Rider";
    }
}
