package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.infra.CacheService;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.IHandler;
import backend.service.model.NPC;
import backend.service.model.Player;

public final class InteractNPCCommand implements ICommand {

    private final Player player;
    private final IHandler<NPC> handler;

    public InteractNPCCommand(Player player, IHandler<NPC> handler) {
        this.player = player;
        this.handler = handler;
    }

    @Override
    public TypeMessage execute() {
        var coordinate = this.player.getCoordinate();
        coordinate.move(this.player.getDirection().getMove());
        var npc = this.player.getCurrentMap().getNPC(coordinate).orElse(null);

        var msg = this.handler.handle(npc);
        if (msg.isPresent()) return msg.get();

        assert npc != null;
        var typeMessage = npc.isAction(this.player.getInventory().getItems());
        if (typeMessage.isPresent() && typeMessage.get().isSuccess()) return typeMessage.get();

        var door = CacheService.getDoor(npc.getIdDoor()).orElse(null);

        assert door != null;
        var mapGame = CacheService.getMapGame(door.getIdMapOutside()).orElse(null);

        this.player.updateMove(this.player.getDirection(), door.getCoordinateOutside());
        this.player.setCurrentMap(mapGame);

        return TypeMessage.NPC_INTERACT;
    }

}
