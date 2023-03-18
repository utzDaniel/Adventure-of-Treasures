package model.interfaces;

import model.Coordinate;
import model.builder.item.Item;

public interface IBuilderItem {

    IBuilderItem name(String name);

    IBuilderItem description(String description);

    IBuilderItem weight(int weight);

    IBuilderItem coordinate(Coordinate coordinate);

    IBuilderItem image(String filename);

    IBuilderItem removable(boolean removable);

    IBuilderItem visible(boolean visible);

    Item build();
}