package com.dq.trucks.controller.actions.rider;


import com.dq.trucks.controller.Action;
import com.dq.trucks.helpers.ConstraintsUtil;
import com.dq.trucks.model.PersistenceManager;
import com.dq.trucks.model.entities.Rider;
import com.dq.trucks.view.View;
import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor
public class FindAllRiderAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        List<String> all = persistence.findAll(Rider.class);
        view.showMessage(all);
        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "All Riders";
    }
}
