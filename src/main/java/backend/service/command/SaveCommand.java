package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.ICommand;
import backend.service.memento.BackupMemento;
import backend.service.memento.BackupMementoMapper;
import backend.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class SaveCommand implements ICommand {

    private final FileUtil<BackupMemento> fileUtil;
    private final BackupMemento memento;

    public SaveCommand(String filename, BackupMemento memento) {
        this.fileUtil = new FileUtil<>(filename);
        this.memento = memento;
    }

    @Override
    public TypeMessage execute() {
        try {
            var list = new ArrayList<>(backup());
            list.add(this.memento.extrinsic());
            this.fileUtil.writeFile(list);
            return TypeMessage.SAVE;
        } catch (IOException e) {
            return TypeMessage.SAVE_ERROR;
        }
    }

    private List<String> backup() throws IOException {
        return this.fileUtil.readFile(new BackupMementoMapper(), 0)
                .stream()
                .map(BackupMemento::extrinsic)
                .toList();
    }

}
