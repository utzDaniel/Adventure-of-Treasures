package backend.service.command;

import backend.Game;
import backend.controller.enums.TypeMessage;
import backend.service.infra.CacheService;
import backend.service.interfaces.ICommand;
import backend.service.memento.BackupMemento;
import backend.service.memento.BackupMementoFactory;
import backend.service.memento.BackupMementoMapper;
import backend.util.FileUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class LoadCommand implements ICommand {

    private final BackupMemento backup;
    private int index;
    private final boolean last;
    private final FileUtil<BackupMemento> fileUtil;

    public LoadCommand(String filename) {
        this.fileUtil = new FileUtil<>(filename);
        this.backup = new BackupMementoFactory().create();
        this.last = true;
    }

    public LoadCommand(String filename, int index) {
        this.fileUtil = new FileUtil<>(filename);
        this.backup = new BackupMementoFactory().create();
        this.last = false;
        this.index = index;
    }

    @Override
    public TypeMessage execute() {
        var mementos = read();
        if (mementos.isEmpty()) return TypeMessage.LOAD_ERROR;
        if (this.last) this.index = mementos.keySet().size() - 1;
        restore(mementos.get(this.index));
        return TypeMessage.LOAD;
    }

    @Override
    public void undo() {
        restore(this.backup);
    }

    private void restore(BackupMemento memento) {
        CacheService.clearAll();

        Game.player.restore(memento.playerMemento());

        memento.itemMementos()
                .forEach(m -> {
                    var item = CacheService.getItem(m.id());
                    item.ifPresent(v -> v.restore(m));
                });
        memento.doorMemento()
                .forEach(m -> {
                    var door = CacheService.getDoor(m.id());
                    door.ifPresent(v -> v.restore(m));
                });
        memento.mapGameMemento()
                .forEach(m -> {
                    var map = CacheService.getMapGame(m.id());
                    map.ifPresent(v -> v.restore(m));
                });
    }

    private Map<Integer, BackupMemento> read() {
        try {
            return this.fileUtil.readFile(new BackupMementoMapper());
        } catch (IOException e) {
            return new HashMap<>();
        }
    }
}
