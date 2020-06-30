package com.dq.trucks;

import com.dq.trucks.controller.SelectOption;
import com.dq.trucks.controller.actions.CreateRelationAction;
import com.dq.trucks.controller.actions.FindRelationAction;
import com.dq.trucks.controller.actions.cargo.*;
import com.dq.trucks.controller.actions.job.*;
import com.dq.trucks.controller.actions.rider.*;
import com.dq.trucks.controller.actions.truck.*;
import com.dq.trucks.controller.options.RunWithNeo4JPersistence;
import com.dq.trucks.helpers.CancellingOperationException;
import com.dq.trucks.helpers.ConstraintsUtil;
import com.dq.trucks.model.PersistenceManager;
import com.dq.trucks.view.ConsoleView;
import com.dq.trucks.view.View;

import java.util.ArrayList;
import java.util.List;

public class Application {

    private static List<SelectOption> registeredActions;
    private static PersistenceManager persistence;
    private static View view;

    public static void main(String[] args) {

        view = new ConsoleView();
        view.showMessage("Starting application");

        persistence = initializePersistence();
        registeredActions = initializeActions();

        while (view.isAppRunning()) {
            SelectOption action = view.selectFromOptions("action", registeredActions);
            String result = executeCancellableAction(action);
            view.showMessage(result);
        }

        view.showMessage("Good bye!");

    }

    private static String executeCancellableAction(SelectOption action) {
        try {
            return (String) action.execute();
        } catch (CancellingOperationException e) {
            return ConstraintsUtil.OPERATION_CANCELLED_MESSAGE;
        } catch (Exception e) {
            return e.getClass().getSimpleName() + " " + e.getMessage();
        }
    }

    private static PersistenceManager initializePersistence() {
        List<SelectOption> options = new ArrayList<>();

        options.add(new RunWithNeo4JPersistence());
//        options.add(new RunWithMapRPersistence());

        SelectOption option = view.selectFromOptions("persistence handlers", options);

        return (PersistenceManager) option.execute();
    }

    private static List<SelectOption> initializeActions() {
        ArrayList<SelectOption> actions = new ArrayList<>();

        actions.add(new AddCargoAction(persistence, view));
        actions.add(new DeleteCargoAction(persistence, view));
        actions.add(new FindAllCargoAction(persistence, view));
        actions.add(new FindCargoAction(persistence, view));
        actions.add(new UpdateCargoAction(persistence, view));

        actions.add(new AddJobAction(persistence, view));
        actions.add(new DeleteJobAction(persistence, view));
        actions.add(new FindAllJobAction(persistence, view));
        actions.add(new FindJobAction(persistence, view));
        actions.add(new UpdateJobAction(persistence, view));

        actions.add(new AddRiderAction(persistence, view));
        actions.add(new DeleteRiderAction(persistence, view));
        actions.add(new FindAllRiderAction(persistence, view));
        actions.add(new FindRiderAction(persistence, view));
        actions.add(new UpdateRiderAction(persistence, view));

        actions.add(new AddTruckAction(persistence, view));
        actions.add(new DeleteTruckAction(persistence, view));
        actions.add(new FindAllTruckAction(persistence, view));
        actions.add(new FindTruckAction(persistence, view));
        actions.add(new UpdateTruckAction(persistence, view));

        actions.add(new CreateRelationAction(persistence, view));
        actions.add(new FindRelationAction(persistence, view));

        return actions;
    }
}
