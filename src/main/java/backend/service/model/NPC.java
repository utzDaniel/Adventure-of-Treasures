package backend.service.model;

import backend.controller.enums.TypeMessage;
import backend.repository.interfaces.INPCEntity;
import backend.service.interfaces.ICoordinate;

import java.util.List;
import java.util.Optional;

public final class NPC {

    private final INPCEntity entity;

    public NPC(INPCEntity entity) {
        this.entity = entity;
    }

    public ICoordinate getCoordinate() {
        return ICoordinate.getInstance(entity.positionX(), entity.positionY());
    }

    public int getIdDoor() {
        return this.entity.idDoor();
    }

    public Optional<TypeMessage> isAction(List<Item> itens) {
        Optional<TypeMessage> msg = this.entity.idItem() == 15 ? Optional.of(TypeMessage.GAME_FINISH) : Optional.empty();
        return isRequerid(itens) ? msg : Optional.empty();
    }
    private boolean isRequerid(List<Item> itens) {
        var requerid = this.entity.idItem() == -1;
        if (!requerid) {
            requerid = itens.stream().anyMatch(v -> v.getId() == this.entity.idItem());
        }
        return requerid;
    }

    @Override
    public String toString() {
        return """
                {
                    "id": "%d",
                    "idMap": "%d",
                    "idDoor": "%d",
                    "idItem": "%d"
                }
                """.formatted(this.entity.id(), this.entity.idMap(), this.entity.idDoor(), this.entity.idItem());
    }
}
