package model.interfaces;

import model.builder.item.Item;

import java.awt.*;

public interface IBuilderItem {

    IBuilderItem name(String name);

    IBuilderItem description(String description);

    IBuilderItem weight(int weight);

    IBuilderItem point(Point point);

    IBuilderItem image(String filename);

    IBuilderItem removable(boolean removable);

    IBuilderItem visible(boolean visible);

    Item build();
}
