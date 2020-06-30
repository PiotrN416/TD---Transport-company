package com.dq.trucks.controller.actions.job;


import com.dq.trucks.controller.Action;
import com.dq.trucks.helpers.ConstraintsUtil;
import com.dq.trucks.model.PersistenceManager;
import com.dq.trucks.model.entities.Job;
import com.dq.trucks.view.View;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FindAllJobAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        List<String> all = persistence.findAll(Job.class);
        view.showMessage(all);
        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "All Jobs";
    }
}
