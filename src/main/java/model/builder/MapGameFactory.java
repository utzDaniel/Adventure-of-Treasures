package model.builder;

import model.Coordinate;
import model.Door;
import model.builder.map.MapGame;
import model.builder.map.RoomBuilder;
import model.builder.map.SceneryBuilder;
import model.interfaces.IBuilderMapGame;

import java.util.Objects;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

public class MapGameFactory {
    private String[] dados;

    public MapGame create(String[] dados) {
        this.dados = dados;
        return type();
    }

    private MapGame type() {
        return dados[0].equals("0000") ? createRoom() : createScenery();
    }

    private IBuilderMapGame inicial(IBuilderMapGame mapGame) {
        return mapGame.name(dados[1])
                .image(dados[2])
                .doors(createDoor(dados[7]))
                .doors(createDoor(dados[8]))
                .doors(createDoor(dados[9]))
                .doors(createDoor(dados[10]))
                .itens(dados[11])
                .itens(dados[12])
                .itens(dados[13])
                .itens(dados[14])
                .song(dados[15])
                .limits(createLimits(dados[16]));
    }

    private MapGame createRoom() {
        return inicial(RoomBuilder
                .builder())
                .build();
    }

    private MapGame createScenery() {
        return inicial(SceneryBuilder
                .builder()
                .exits("norte", dados[3])
                .exits("sul", dados[4])
                .exits("oeste", dados[5])
                .exits("leste", dados[6]))
                .build();
    }

    private Door createDoor(String dados) {
        if (Objects.isNull(dados)) return null;
        var dadosDoor = dados.split(",");
        var mapGame = dadosDoor[0].trim();
        var x = parseInt(dadosDoor[1].trim());
        var y = parseInt(dadosDoor[2].trim());
        var open = parseBoolean(dadosDoor[3].trim());
        return new Door(mapGame, new Coordinate(x, y), open);
    }

    private int[][] createLimits(String dados) {
        int[][] limits = new int[56][78];
        int j = 0, k = 0;
        for (int i = 0; i < dados.length(); i++) {
            if (i != 0 && i % 78 == 0) {
                j++;
                k = 0;
            }
            limits[j][k] = parseInt(String.valueOf(dados.charAt(i)));
            k++;
        }
        return limits;
    }
}
