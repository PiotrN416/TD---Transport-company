package com.dq.trucks.controller.actions.job;


import com.dq.trucks.controller.Action;
import com.dq.trucks.helpers.ConstraintsUtil;
import com.dq.trucks.model.PersistenceManager;
import com.dq.trucks.model.entities.Job;
import com.dq.trucks.view.View;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class UpdateJobAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        Integer id = view.getValidNumberPropertyCancellable("id");
        String field = view.getPropertyCancellable("update what");
        String fieldVal = view.getPropertyCancellable("update to");
        persistence.update(id, field, fieldVal, Job.class);
        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Update Job";
    }
}
