package backend.service.component;

import backend.service.infra.Cache;
import backend.service.interfaces.ICoordinate;
import backend.service.model.Door;
import backend.service.model.builder.MapGame;

import javax.swing.*;

public final class ActivateMapGame {

    //TODO resolver isso depois - Servico
    public boolean run(String nameItem) {
        boolean activate = false;
        try {
            if (nameItem.equals("tocha")) {
                MapGame templo = Cache.getMapGame(8);
                Door openDoor = templo.getDoor(ICoordinate.getInstance(9, 24)).get();
                openDoor.setOpen(!openDoor.isOpen());
                activate = true;
            } else if (nameItem.equals("mapa")) {
                MapGame praia = Cache.getMapGame(4);
                praia.setImage("src/main/resources/map/praiaM.png");
            } else if (nameItem.equals("chave")) {
                MapGame praia = Cache.getMapGame(4);
                praia.setImage("src/main/resources/map/praia.png");
            } else if (nameItem.equals("escada")) {
                MapGame templo = Cache.getMapGame(8);
                templo.setImage("src/main/resources/map/temploF.png");
            }
        } catch (Exception e) {
            System.out.println("Direção invalida!");
        }
        return activate;
    }
}
