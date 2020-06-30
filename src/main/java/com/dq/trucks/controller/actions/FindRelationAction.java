package com.dq.trucks.controller.actions;

import com.dq.trucks.controller.Action;
import com.dq.trucks.helpers.ConstraintsUtil;
import com.dq.trucks.model.Entity;
import com.dq.trucks.model.PersistenceManager;
import com.dq.trucks.view.View;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FindRelationAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        Class<? extends Entity> type1 = view.getValidEntityClass("Type");
        int id1 = view.getValidNumberPropertyCancellable("id");

        List<String> related = persistence.findRelated(id1, type1);
        view.showMessage(related);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Find Relation";
    }
}
