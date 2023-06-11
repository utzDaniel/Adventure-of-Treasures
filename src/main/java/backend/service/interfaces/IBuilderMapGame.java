package backend.service.interfaces;

import backend.service.model.Door;
import backend.service.model.builder.Item;
import backend.service.model.builder.MapGame;

import java.util.List;

public interface IBuilderMapGame {

    IBuilderMapGame name(String name);

    IBuilderMapGame image(String filename);

    IBuilderMapGame limits(int[][] limits);

    IBuilderMapGame doors(List<Door> door);

    IBuilderMapGame itens(List<Item> item);
    public IBuilderMapGame song(String filename);

    MapGame build();

}
