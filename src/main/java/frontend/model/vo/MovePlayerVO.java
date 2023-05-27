package frontend.model.vo;

import rules.interfaces.ICoordinate;
import rules.interfaces.IItem;
import rules.interfaces.IMovePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MovePlayerVO implements IMovePlayer {

    private String iconMap;
    private String iconPlayer;
    private CoordinateVO coordinatePlayer;
    private String songMap;
    private List<ItemVO> itens;
    private int indexItens;

    public MovePlayerVO() {
    }

    public MovePlayerVO(String iconMap, String iconPlayer, ICoordinate coordinatePlayer, String songMap, List<IItem> itens, int indexItens) {
        this.iconMap = iconMap;
        this.iconPlayer = iconPlayer;
        this.coordinatePlayer = new CoordinateVO(coordinatePlayer.getX(),coordinatePlayer.getY());
        this.songMap = songMap;
        if (Objects.nonNull(itens))
            this.itens = new ArrayList<>(itens.stream()
                    .map(item -> new ItemVO(item.getIcon(), item.getCoordinate()))
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
                .map(item -> new ItemVO(item.getIcon(), item.getCoordinate()))
                .toList());
    }

    @Override
    public int getIndexItens() {
        return this.indexItens;
    }
}
