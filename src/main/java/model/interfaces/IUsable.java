package model.interfaces;

import model.builder.item.Item;
import service.Use;

public interface IUsable {

    boolean use(Item item);
    String getLocalUse();
    String getName();
}