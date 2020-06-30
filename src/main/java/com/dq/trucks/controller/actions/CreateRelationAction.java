package com.dq.trucks.controller.actions;

import com.dq.trucks.controller.Action;
import com.dq.trucks.helpers.ConstraintsUtil;
import com.dq.trucks.model.Entity;
import com.dq.trucks.model.PersistenceManager;
import com.dq.trucks.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateRelationAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {

        Class<? extends Entity> type1 = view.getValidEntityClass("Type1");
        int id1 = view.getValidNumberPropertyCancellable("id1");

        Class<? extends Entity> type2 = view.getValidEntityClass("Type2");
        int id2 = view.getValidNumberPropertyCancellable("id2");

        persistence.createRelation(id1, type1, id2, type2);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Create Relation";
    }
}
