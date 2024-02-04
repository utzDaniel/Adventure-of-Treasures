package backend.service.component.open;

import backend.controller.enums.TypeMessage;
import backend.service.infra.Cache;
import backend.service.model.MapGame;
import backend.service.model.Player;

import java.util.Objects;
import java.util.Optional;

public final class Open {

    private final Player player;

    public Open(Player player) {
        this.player = player;
    }

    public TypeMessage run() {

        var door = player.getCurrentMap().getDoor(player.getCoordinate()).orElse(null);

        if (Objects.isNull(door))
            return TypeMessage.DOOR_NOT_EXIT;

        var npc = this.player.getCurrentMap().getNPC();
        Optional<TypeMessage> msg = Optional.empty();
        if (npc.isPresent())
            msg = npc.get().isAction(door.getId(), this.player.getInventory().getItens());
        if (msg.isPresent() && msg.get() == TypeMessage.GAME_FINISH) return msg.get();

        if (!door.isOpen())
            return TypeMessage.DOOR_CLOSED;

        var mapGame = Cache.getMapGame(door.getIdMapGame());
        if (mapGame.isEmpty()) return TypeMessage.DOOR_NOT_EXIT;

        updateMove(mapGame.get());
        this.player.setCurrentMap(mapGame.get());

        return TypeMessage.DOOR_OPEN;
    }

    private void updateMove(MapGame mapGame) {
        var idMapGame = this.player.getCurrentMap().getId();
        var door = mapGame.getDoorByMap(idMapGame);
        if (door.isEmpty()) return;
        this.player.updateMove(this.player.getDirection(), door.get().getCoordinate());
    }

}
