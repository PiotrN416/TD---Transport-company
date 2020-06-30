package com.dq.trucks.controller.actions.job;

import com.dq.trucks.controller.Action;
import com.dq.trucks.helpers.ConstraintsUtil;
import com.dq.trucks.helpers.IdsService;
import com.dq.trucks.model.PersistenceManager;
import com.dq.trucks.model.entities.Job;
import com.dq.trucks.view.View;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class AddJobAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        Job job = new Job();

        job.setId(IdsService.getNext());
        job.setName(view.getPropertyCancellable("name"));
        job.setDesc(view.getPropertyCancellable("desc"));
        job.setStatus("UNRESOLVED");

        persistence.create(job);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Add Job";
    }
}
