package model.interfaces;

import model.builder.item.Item;
import service.Use;

public interface IUsable {

    default boolean use(Item item){
        return new Use(item).run();
    }
    String getLocalUse();
}