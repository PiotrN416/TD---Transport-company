package com.dq.trucks.controller.actions.job;


import com.dq.trucks.controller.Action;
import com.dq.trucks.helpers.ConstraintsUtil;
import com.dq.trucks.model.PersistenceManager;
import com.dq.trucks.model.entities.Job;
import com.dq.trucks.view.View;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class FindJobAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        String result = persistence.findById(view.getValidNumberPropertyCancellable("id"), Job.class);
        view.showMessage(result);
        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Find Job";
    }
}
