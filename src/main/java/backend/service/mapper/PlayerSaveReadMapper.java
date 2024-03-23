package backend.service.mapper;

import backend.service.enums.Direction;
import backend.service.enums.MovementImage;
import backend.service.infra.CacheService;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IMove;
import backend.service.model.Inventory;
import backend.service.model.Item;
import backend.service.model.Move;
import backend.service.model.Player;

import java.util.ArrayList;
import java.util.function.Function;

public class PlayerSaveReadMapper implements Function<String, Player> {

    @Override
    public Player apply(String extrinsic) {
        var dados = extrinsic.split(";");
        var move = createMove(dados);
        var inventory = createInventory(dados);
        return new Player(move, inventory);
    }
    private IMove createMove(String[] dados) {
        var movementImage = Enum.valueOf(MovementImage.class, dados[0]);
        var currentMapGame = CacheService.getMapGame(Integer.parseInt(dados[1])).orElse(null);
        var direction = Enum.valueOf(Direction.class, dados[2]);
        var coordinate = ICoordinate.getInstance(Integer.parseInt(dados[3]), Integer.parseInt(dados[4]));
        var path = dados[5];
        return new Move(path, coordinate, currentMapGame, movementImage, direction);
    }

    private Inventory createInventory(String[] dados) {
        var capacity = Integer.parseInt(dados[6]);
        var maxCapacity = Integer.parseInt(dados[7]);
        var isInventory = Boolean.parseBoolean(dados[8]);
        var itens = new ArrayList<Item>();
        for (int i = 9; i < dados.length; i++) {
            if(!dados[i].isEmpty())
                itens.add(CacheService.getItem(Integer.parseInt(dados[i])).orElse(null));
        }
        return new Inventory(capacity, maxCapacity, isInventory, itens);
    }

}
