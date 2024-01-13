package backend.service.component;

import backend.service.infra.Cache;
import backend.service.interfaces.ICoordinate;
import backend.service.model.Door;
import backend.service.model.MapGame;

public final class ActivateMapGame {

    //TODO resolver isso depois - Servico
    public boolean run(String nameItem) {
        boolean activate = false;
        try {
            if (nameItem.equals("tocha")) {
                var templo = Cache.getMapGame(8);
                if(templo.isEmpty()) return activate;
                Door openDoor = templo.get().getDoor(ICoordinate.getInstance(24, 9)).get();
                openDoor.setOpen(!openDoor.isOpen());
                activate = true;
            } else if (nameItem.equals("mapa")) {
                var praia = Cache.getMapGame(4);
                if(praia.isEmpty()) return activate;
                praia.get().setImage("src/main/resources/image/map/praiaM.png");
            } else if (nameItem.equals("chave")) {
                var praia = Cache.getMapGame(4);
                if(praia.isEmpty()) return activate;
                praia.get().setImage("src/main/resources/image/map/praia.png");
            } else if (nameItem.equals("escada")) {
                var templo = Cache.getMapGame(8);
                if(templo.isEmpty()) return activate;
                templo.get().setImage("src/main/resources/image/map/temploF.png");
            }
        } catch (Exception e) {
            System.out.println("Direção invalida!");
        }
        return activate;
    }
}
