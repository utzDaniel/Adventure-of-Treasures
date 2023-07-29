package backend.service.interfaces;

import backend.service.model.Door;
import backend.service.model.builder.Item;
import backend.service.model.builder.MapGame;

import java.util.List;

public interface IBuilderMapGame {

    IBuilderMapGame id(int id);

    IBuilderMapGame name(String name);

    IBuilderMapGame image(String filename);

    IBuilderMapGame limits(byte[][] limits);

    IBuilderMapGame doors(List<Door> door);

    IBuilderMapGame itens(List<Item> item);

    IBuilderMapGame song(String filename);

    MapGame build();

}
