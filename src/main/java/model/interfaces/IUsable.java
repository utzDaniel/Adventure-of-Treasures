package model.interfaces;

import model.Item;
import service.Use;

public interface IUsable {

    default boolean use(Item item){
        return new Use(item).run();
    }
    String getLocalUse();
}