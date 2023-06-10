package backend.service.interfaces;

import backend.service.model.Door;
import backend.service.model.builder.MapGame;

public interface IBuilderMapGame {

    IBuilderMapGame name(String name);

    IBuilderMapGame image(String filename);

    IBuilderMapGame limits(int[][] limits);

    IBuilderMapGame doors(Door door);

    IBuilderMapGame itens(String item);
    public IBuilderMapGame song(String filename);

    MapGame build();

}
