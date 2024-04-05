package backend.service.memento;

import backend.Game;
import backend.service.infra.CacheService;
import backend.service.model.Door;
import backend.service.model.Item;
import backend.service.model.MapGame;

import java.util.HashSet;
import java.util.Objects;

public final class BackupMementoFactory {

    public BackupMemento create() {

        var idItems = new HashSet<Integer>();

        Game.player.getInventory().getItems().stream()
                .map(Item::getId)
                .forEach(idItems::add);

        CacheService.getAllMapGame().stream()
                .flatMap(map -> map.getItems().stream().map(Item::getId))
                .forEach(idItems::add);

        var listItem = idItems.stream()
                .map(v -> CacheService.getItem(v).orElse(null))
                .filter(Objects::nonNull)
                .map(Item::save)
                .toList();

        var listDoor = CacheService.getAllDoor().stream()
                .map(Door::save)
                .toList();

        var listMap = CacheService.getAllMapGame().stream()
                .map(MapGame::save)
                .toList();

        return new BackupMemento(listItem, listDoor, Game.player.save(), listMap);
    }
}
