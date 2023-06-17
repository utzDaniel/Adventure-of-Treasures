package frontend.model.vo;

import backend.service.interfaces.ICoordinate;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IMoveResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MovePlayerVO implements IMoveResponse {

    private String iconMap;
    private String iconPlayer;
    private CoordinateVO coordinatePlayer;
    private String songMap;
    private List<ItemVO> itens;
    private int indexItens;

    public MovePlayerVO() {
    }

    public MovePlayerVO(String iconMap, String iconPlayer, ICoordinate coordinatePlayer, String songMap, List<IItemDTO> itens, int indexItens) {
        this.iconMap = iconMap;
        this.iconPlayer = iconPlayer;
        this.coordinatePlayer = new CoordinateVO(coordinatePlayer.x(), coordinatePlayer.y());
        this.songMap = songMap;
        if (Objects.nonNull(itens))
            this.itens = new ArrayList<>(itens.stream()
                    .map(item -> new ItemVO(item.icon(), item.coordinate(), item.name(), item.description(),
                            item.effect(), item.weight(), item.specialization(), item.isEquipped()))
                    .toList());
        this.indexItens = indexItens;
    }

    @Override
    public String iconMap() {
        return this.iconMap;
    }

    @Override
    public String iconPlayer() {
        return this.iconPlayer;
    }

    @Override
    public ICoordinate coordinatePlayer() {
        return this.coordinatePlayer;
    }

    @Override
    public String songMap() {
        return this.songMap;
    }

    @Override
    public List<IItemDTO> itens() {
        if (Objects.isNull(itens)) return null;
        return new ArrayList<>(this.itens.stream()
                .map(item -> new ItemVO(item.icon(), item.coordinate(), item.name(), item.description(),
                        item.effect(), item.weight(), item.specialization(), item.isEquipped()))
                .toList());
    }

    @Override
    public int indexItens() {
        return this.indexItens;
    }
}
