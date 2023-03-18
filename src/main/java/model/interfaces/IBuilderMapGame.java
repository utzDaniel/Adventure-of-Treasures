package model.interfaces;

import model.Door;
import model.builder.map.MapGame;

public interface IBuilderMapGame {

    IBuilderMapGame name(String name);

    IBuilderMapGame image(String filename);

    IBuilderMapGame limits(int[][] limits);

    IBuilderMapGame doors(Door door);

    IBuilderMapGame itens(String item);
    public IBuilderMapGame song(String filename);

    MapGame build();

}
