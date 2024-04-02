package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.infra.CacheService;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.ICoordinate;
import backend.service.model.MapGame;
import backend.service.model.Player;

public final class InteractNPCCommand implements ICommand {

    private final Player player;
    private final MapGame oldMapGame;
    private final ICoordinate oldCoordinate;

    public InteractNPCCommand(Player player) {
        this.player = player;
        this.oldMapGame = player.getCurrentMap();
        this.oldCoordinate = ICoordinate.getInstance(player.getCoordinate());
    }

    @Override
    public TypeMessage execute() {
        var coordinate = this.player.getCoordinate();
        coordinate.move(this.player.getDirection().getMove());
        var npcOptional = this.player.getCurrentMap().getNPC(coordinate);
        if (npcOptional.isEmpty()) {
            return TypeMessage.NPC_ERRO_FOUND;
        }
        var npc = npcOptional.get();

        var msg = npc.isAction(this.player.getInventory().getItens());

        if (msg.isPresent() && msg.get().isSuccess()) return msg.get();

        if (npc.getIdDoor() == -1) return TypeMessage.NPC_ERRO_INCOMPLETE;

        var door = CacheService.getDoor(npc.getIdDoor());
        if (door.isEmpty()) return TypeMessage.MAP_NOT_FOUND;

        var mapGame = CacheService.getMapGame(door.get().getIdMapGame());
        if (mapGame.isEmpty()) return TypeMessage.MAP_NOT_FOUND;

        updateMove(mapGame.get());
        this.player.setCurrentMap(mapGame.get());

        return TypeMessage.NPC_INTERACT;
    }

    @Override
    public void undo() {
        this.player.setCurrentMap(this.oldMapGame);
        this.player.updateMove(this.player.getDirection(), this.oldCoordinate);
    }

    private void updateMove(MapGame mapGame) {
        var idMapGame = this.player.getCurrentMap().id();
        var door = mapGame.getDoorByMap(idMapGame);
        if (door.isEmpty()) return;
        this.player.updateMove(this.player.getDirection(), door.get().getCoordinate());
    }
}
