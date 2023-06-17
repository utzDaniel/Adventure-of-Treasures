package frontend.model.vo;

import backend.service.interfaces.ICoordinate;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IOpenResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OpenVO implements IOpenResponse {

    private String iconMap;
    private String iconPlayer;
    private CoordinateVO coordinatePlayer;
    private String songMap;
    private List<ItemVO> itens;

    public OpenVO() {
    }

    public OpenVO(String iconMap, String iconPlayer, ICoordinate coordinatePlayer, String songMap, List<IItemDTO> itens) {
        this.iconMap = iconMap;
        this.iconPlayer = iconPlayer;
        this.coordinatePlayer = new CoordinateVO(coordinatePlayer.x(), coordinatePlayer.y());
        this.songMap = songMap;
        if (Objects.nonNull(itens))
            this.itens = new ArrayList<>(itens.stream()
                    .map(item -> new ItemVO(item.icon(), item.coordinate(), item.name(), item.description(),
                            item.effect(), item.weight(), item.specialization(), item.isEquipped()))
                    .toList());
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
                .map(item -> new ItemVO(item.icon(), item.coordinate(), item.name(), item.description(),
                        item.effect(), item.weight(), item.specialization(), item.isEquipped()))
                .toList());
    }

}
