package model;

import service.Use;

public interface IUsable {

    default boolean use(Item item, Player player){
        return new Use(player).execute(item);
    }
    String getLocalUse();
}