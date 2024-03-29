package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.ICommand;
import backend.service.memento.BackupMementoMapper;
import backend.service.memento.BackupMemento;
import backend.service.memento.BackupMementoFactory;
import backend.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class SaveCommand implements ICommand {

    private final FileUtil<BackupMemento> fileUtil;
    private List<String> backup;

    public SaveCommand() {
        var filename = "src/main/resources/save/player.txt";
        this.fileUtil = new FileUtil<>(filename);
    }

    @Override
    public TypeMessage execute() {
        if (!backup()) return TypeMessage.BACKUP_ERRO;
        var memento = new BackupMementoFactory().create();
        var list = new ArrayList<>(this.backup);
        list.add(memento.extrinsic());
        return save(list);
    }

    @Override
    public void undo() {
        save(this.backup);
    }

    private TypeMessage save(List<String> list) {
        try {
            this.fileUtil.writeFile(list);
            return TypeMessage.SAVE;
        } catch (IOException e) {
            return TypeMessage.SAVE_ERRO;
        }
    }

    private boolean backup() {
        try {
            var mementos = this.fileUtil.readFile(new BackupMementoMapper(), 0);
            this.backup = mementos.stream()
                    .map(BackupMemento::extrinsic)
                    .toList();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
