package backend.model.dto;

import rules.interfaces.ICoordinate;
import rules.interfaces.IItem;
import rules.interfaces.IMovePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MovePlayerDTO implements IMovePlayer {

    private String iconMap;
    private String iconPlayer;
    private CoordinateDTO coordinatePlayer;
    private String songMap;
    private List<ItemDTO> itens;
    private int indexItens;

    public MovePlayerDTO() {
    }

    public MovePlayerDTO(String iconMap, String iconPlayer, ICoordinate coordinatePlayer, String songMap, List<IItem> itens, int indexItens) {
        this.iconMap = iconMap;
        this.iconPlayer = iconPlayer;
        this.coordinatePlayer = new CoordinateDTO(coordinatePlayer.getX(), coordinatePlayer.getY());
        this.songMap = songMap;
        if (Objects.nonNull(itens))
            this.itens = new ArrayList<>(itens.stream()
                    .map(item -> new ItemDTO(item.getIcon(), item.getCoordinate(), item.getName(), item.getDescription(),
                            item.getEffect(), item.getWeight(), item.getSpecialization(), item.isEquipped()))
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
    public List<IItem> getItens() {
        if (Objects.isNull(itens)) return null;
        return new ArrayList<>(this.itens.stream()
                .map(item -> new ItemDTO(item.getIcon(), item.getCoordinate(), item.getName(), item.getDescription(),
                        item.getEffect(), item.getWeight(), item.getSpecialization(), item.isEquipped()))
                .toList());
    }

    @Override
    public int getIndexItens() {
        return this.indexItens;
    }
}
