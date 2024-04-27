package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.infra.CacheService;
import backend.service.interfaces.ICommand;
import backend.service.model.NPC;
import backend.service.model.Player;

public final class InteractNPCCommand implements ICommand {

    private final Player player;
    private final NPC npc;

    public InteractNPCCommand(NPC npc, Player player) {
        this.npc = npc;
        this.player = player;
    }

    @Override
    public TypeMessage execute() {
        var typeMessage = this.npc.isAction(this.player.getInventory().getItems());
        if (typeMessage.isPresent() && typeMessage.get().isSuccess()) return typeMessage.get();

        var door = CacheService.getDoor(this.npc.getIdDoor()).orElse(null);

        assert door != null;
        var mapGame = CacheService.getMapGame(door.getIdMapOutside()).orElse(null);

        this.player.updateMove(this.player.getDirection(), door.getCoordinateOutside());
        this.player.setCurrentMap(mapGame);

        return TypeMessage.NPC_INTERACT;
    }

}
