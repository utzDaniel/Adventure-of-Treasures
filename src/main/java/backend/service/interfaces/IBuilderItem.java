package backend.service.interfaces;

import backend.service.enums.TypeItem;
import backend.service.model.builder.Item;

import java.util.List;

public interface IBuilderItem {

    IBuilderItem id(int id);

    IBuilderItem name(String name);

    IBuilderItem description(String description);

    IBuilderItem weight(int weight);

    IBuilderItem coordinate(ICoordinate coordinate);

    IBuilderItem image(String filename);

    IBuilderItem type(List<TypeItem> listType);

    IBuilderItem visible(boolean visible);

    Item build();
}
