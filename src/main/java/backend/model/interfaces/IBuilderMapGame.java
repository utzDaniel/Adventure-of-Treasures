package backend.model.interfaces;

import backend.model.Door;
import backend.model.builder.map.MapGame;

public interface IBuilderMapGame {

    IBuilderMapGame name(String name);

    IBuilderMapGame image(String filename);

    IBuilderMapGame limits(int[][] limits);

    IBuilderMapGame doors(Door door);

    IBuilderMapGame itens(String item);
    public IBuilderMapGame song(String filename);

    MapGame build();

}
