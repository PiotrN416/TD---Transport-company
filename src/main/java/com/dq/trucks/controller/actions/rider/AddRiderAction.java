package com.dq.trucks.controller.actions.rider;

import com.dq.trucks.controller.Action;
import com.dq.trucks.helpers.ConstraintsUtil;
import com.dq.trucks.helpers.IdsService;
import com.dq.trucks.model.PersistenceManager;
import com.dq.trucks.model.entities.Rider;
import com.dq.trucks.view.View;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class AddRiderAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        Rider rider = new Rider();

        rider.setId(IdsService.getNext());
        rider.setFirstName(view.getPropertyCancellable("first name"));
        rider.setLastName(view.getPropertyCancellable("last name"));

        persistence.create(rider);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Add Rider";
    }
}
