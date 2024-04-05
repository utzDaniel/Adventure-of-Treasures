package service;

import backend.controller.enums.TypeMessage;
import backend.service.command.LoadCommand;
import backend.service.memento.BackupMemento;
import backend.service.memento.BackupMementoFactory;
import backend.util.FileUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadCommandTest {

    private List<String> extrinsics;
    private final String FILENAME = "src/test/resources/save/teste_load.txt";

    @Before
    public void create() {
        this.extrinsics = new ArrayList<String>();
        this.extrinsics.add("1;false;door;RIGHT_FOOT_TOGETHER;1;SOUTH;47;30;src/main/resources/image/player;0;10;false;[];player;1;src/main/resources/image/map/cais.png;[];mapGame;");
        this.extrinsics.add("10;22;65;true;false;item;1;false;door;14;true;door;15;false;door;RIGHT_FOOT_FORWARD;12;WEST;24;47;src/main/resources/image/player;0;15;false;[10,];player;1;src/main/resources/image/map/cais.png;[];mapGame;12;src/main/resources/image/map/barco.png;[];mapGame;");
        this.extrinsics.add("4;31;28;false;false;item;7;8;64;false;false;item;8;20;41;false;false;item;9;32;16;false;false;item;11;28;20;false;true;item;1;false;door;2;true;door;4;true;door;5;true;door;6;false;door;7;true;door;13;true;door;LEFT_FOOT_TOGETHER;4;EAST;15;5;src/main/resources/image/player;3;10;false;[8,11,];player;1;src/main/resources/image/map/cais.png;[];mapGame;2;src/main/resources/image/map/farol.png;[];mapGame;4;src/main/resources/image/map/praiaM.png;[];mapGame;5;src/main/resources/image/map/floresta.png;[7,];mapGame;6;src/main/resources/image/map/vila.png;[];mapGame;7;src/main/resources/image/map/alojamento.png;[9,];mapGame;11;src/main/resources/image/map/enfermaria.png;[4,];mapGame;");
        this.extrinsics.add("4;31;28;false;false;item;6;20;41;false;false;item;7;8;64;false;false;item;8;20;41;false;false;item;9;32;16;false;false;item;1;false;door;2;true;door;4;true;door;5;true;door;6;true;door;7;true;door;8;true;door;9;false;door;10;false;door;13;true;door;LEFT_FOOT_FORWARD;8;NORTH;53;38;src/main/resources/image/player;0;10;false;[8,];player;1;src/main/resources/image/map/cais.png;[];mapGame;2;src/main/resources/image/map/farol.png;[];mapGame;4;src/main/resources/image/map/praia.png;[];mapGame;5;src/main/resources/image/map/floresta.png;[7,];mapGame;6;src/main/resources/image/map/vila.png;[];mapGame;7;src/main/resources/image/map/alojamento.png;[9,];mapGame;8;src/main/resources/image/map/temploA.png;[6,];mapGame;11;src/main/resources/image/map/enfermaria.png;[4,];mapGame;");
        write();
    }

    @Test
    public void validErro() {
        var load = new LoadCommand("sdasda");
        var msg = load.execute();
        Assert.assertEquals(TypeMessage.LOAD_ERROR, msg);
    }

    @Test
    public void validTeste01() {
        var index = 0;
        var load = new LoadCommand(this.FILENAME, index);
        var msg = load.execute();
        Assert.assertEquals(TypeMessage.LOAD, msg);
        var memento = new BackupMementoFactory().create();
        Assert.assertEquals(this.extrinsics.get(index), memento.extrinsic());
    }

    @Test
    public void validTeste02() {
        var index = 1;
        var load = new LoadCommand(this.FILENAME, index);
        var msg = load.execute();
        Assert.assertEquals(TypeMessage.LOAD, msg);
        var memento = new BackupMementoFactory().create();
        Assert.assertEquals(this.extrinsics.get(index), memento.extrinsic());
    }

    @Test
    public void validTeste03() {
        var index = 2;
        var load = new LoadCommand(this.FILENAME, index);
        var msg = load.execute();
        Assert.assertEquals(TypeMessage.LOAD, msg);
        var memento = new BackupMementoFactory().create();
        Assert.assertEquals(this.extrinsics.get(index), memento.extrinsic());
    }

    @Test
    public void validTeste04() {
        var index = 3;
        var load = new LoadCommand(this.FILENAME, index);
        var msg = load.execute();
        Assert.assertEquals(TypeMessage.LOAD, msg);
        var memento = new BackupMementoFactory().create();
        Assert.assertEquals(this.extrinsics.get(index), memento.extrinsic());
    }

    @Test
    public void validTesteUndo() {
        var index = 3;
        var load = new LoadCommand(this.FILENAME, index);
        var msg = load.execute();
        Assert.assertEquals(TypeMessage.LOAD, msg);
        var memento = new BackupMementoFactory().create();
        Assert.assertEquals(this.extrinsics.get(index), memento.extrinsic());
        load.undo();
        Assert.assertNotEquals(this.extrinsics.get(index), memento.extrinsic());
    }

    private void write() {
        try {
            var fileUtil = new FileUtil<BackupMemento>(this.FILENAME);
            fileUtil.writeFile(this.extrinsics);
        } catch (IOException e) {
            return;
        }
    }

}
