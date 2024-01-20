package backend.service.component;

import backend.service.enums.TypeItem;
import backend.service.infra.Cache;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IUsable;
import backend.service.model.Door;
import backend.service.model.MapGame;

public final class ActivateMapGame {

    //TODO resolver isso depois - Servico
    public boolean run(String nameItem) {
        boolean activate = false;
        try {
            if (nameItem.equals("mapa")) {
                var praia = Cache.getMapGame(4);
                if(praia.isEmpty()) return activate;
                praia.get().setImage("src/main/resources/image/map/praiaM.png");
                ((IUsable)Cache.getItem(11).get().getSpecialization(TypeItem.USABLE).get()).setEnabled(true);
            }
        } catch (Exception e) {
            System.out.println("Direção invalida!");
        }
        return activate;
    }
}
