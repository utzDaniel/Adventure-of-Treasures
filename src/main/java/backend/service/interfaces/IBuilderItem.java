package backend.service.interfaces;

import backend.service.model.builder.Item;
import backend.controller.interfaces.ICoordinate;

public interface IBuilderItem {

    IBuilderItem name(String name);

    IBuilderItem description(String description);

    IBuilderItem weight(int weight);

    IBuilderItem coordinate(ICoordinate coordinate);

    IBuilderItem image(String filename);

    IBuilderItem removable(boolean removable);

    IBuilderItem visible(boolean visible);

    Item build();
}
