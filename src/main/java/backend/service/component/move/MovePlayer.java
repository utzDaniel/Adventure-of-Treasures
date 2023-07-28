package backend.service.component.move;

import backend.controller.enums.TypeMessage;
import backend.service.enums.Move;
import backend.service.model.Player;

public final class MovePlayer {
    private final Player player;
    private final Move move;

    public MovePlayer(Move move, Player player) {
        this.move = move;
        this.player = player;
    }

    public TypeMessage run() {
        if (this.move.isNextScenery(this.player.getLocation()))
            return new MovePlayerNextScenery(this.player, this.move).run();
        else
            return new MovePlayerScenery(this.player, this.move).run();
    }
}
