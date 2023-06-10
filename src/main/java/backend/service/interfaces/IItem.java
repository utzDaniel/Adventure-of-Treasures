package backend.service.interfaces;

import backend.controller.interfaces.ICoordinate;

import javax.swing.*;

public interface IItem {

    ICoordinate getLocation();

    ImageIcon getIcon();

    String getName();

    String getDescription();

    int getWeight();

    boolean isVisible();

}
