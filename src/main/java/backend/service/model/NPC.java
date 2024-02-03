package backend.service.model;

import backend.controller.enums.TypeMessage;
import backend.repository.interfaces.INPCEntity;

import java.util.List;
import java.util.Optional;

public final class NPC {

    private final INPCEntity entity;

    public NPC(INPCEntity entity) {
        this.entity = entity;
    }

    public Optional<TypeMessage> isAction(int idDoor, List<Item> itens) {
        Optional<TypeMessage> msg = this.entity.idItem() == 15 ? Optional.of(TypeMessage.GAME_FINISH) : Optional.empty();
        return isValid(idDoor, itens) ? msg : Optional.empty();
    }

    private boolean isValid(int idDoor, List<Item> itens) {
        return this.entity.idDoor() == idDoor && isRequerid(itens);
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
