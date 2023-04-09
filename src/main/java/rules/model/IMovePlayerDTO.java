package rules.model;

import backend.model.builder.item.Item;
import rules.interfaces.ICoordinate;

import javax.swing.*;
import java.util.List;

public interface IMovePlayerDTO {

    ImageIcon getIconMap();
    ImageIcon getIconPlayer();
    ICoordinate getCoordinatePlayer();
    String getSongMap();

    List<Item> getItens();

    int getIndexItens();
}
