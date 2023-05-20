package backend.model.dto;

import backend.model.Coordinate;
import rules.interfaces.ICoordinate;
import rules.interfaces.IItemDTO;
import rules.interfaces.IMovePlayerDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MovePlayerDTO implements IMovePlayerDTO {

    private String iconMap;
    private String iconPlayer;
    private Coordinate coordinatePlayer;
    private String songMap;
    private List<ItemDTO> itens;
    private int indexItens;

    public MovePlayerDTO() {
    }

    public MovePlayerDTO(String iconMap, String iconPlayer, ICoordinate coordinatePlayer, String songMap, List<IItemDTO> itens, int indexItens) {
        this.iconMap = iconMap;
        this.iconPlayer = iconPlayer;
        this.coordinatePlayer = (Coordinate) coordinatePlayer;
        this.songMap = songMap;
        if (Objects.nonNull(itens))
            this.itens = new ArrayList<>(itens.stream()
                    .map(item -> new ItemDTO(item.getIcon(), item.getCoordinate()))
                    .toList());
        this.indexItens = indexItens;
    }

    @Override
    public String getIconMap() {
        return this.iconMap;
    }

    @Override
    public String getIconPlayer() {
        return this.iconPlayer;
    }

    @Override
    public ICoordinate getCoordinatePlayer() {
        return this.coordinatePlayer;
    }

    @Override
    public String getSongMap() {
        return this.songMap;
    }

    @Override
    public List<IItemDTO> getItens() {
        if (Objects.isNull(itens)) return null;
        return new ArrayList<>(this.itens.stream()
                .map(item -> new ItemDTO(item.getIcon(), item.getCoordinate()))
                .toList());
    }

    @Override
    public int getIndexItens() {
        return this.indexItens;
    }
}
