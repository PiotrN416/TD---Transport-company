package com.dq.trucks.view;

import com.dq.trucks.controller.SelectOption;
import com.dq.trucks.model.Entity;

import java.util.List;

public interface View {

    void showMessage(String message);

    void showMessage(List messages);

    void showSubMessage(String message);

    void showGroup(String name, List messages);

    String getProperty(String propertyName);

    String getPropertyCancellable(String propertyName);

    int getValidNumberPropertyCancellable(String propertyName);

    SelectOption selectFromOptions(String setName, List<SelectOption> options);

    boolean getConfirmation(String message);

    boolean isAppRunning();

    void setAppRunning(boolean flag);

    Class<? extends Entity> getValidEntityClass(String type1);
}
