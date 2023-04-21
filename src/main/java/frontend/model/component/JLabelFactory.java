package frontend.model.component;

import backend.model.Player;
import backend.model.builder.item.Item;
import backend.model.builder.map.MapGame;

import javax.swing.*;
import java.util.List;

public final class JLabelFactory {

    private JLabelFactory() {
    }

    public static JLabel getInstance(Player player) {
        return JLabelGameFactory.getInstance(player);
    }

    public static JLabel getInstance(MapGame mapGame) {
        return JLabelGameFactory.getInstance(mapGame);
    }

    public static JLabel getInstance(Item item) {
        return JLabelGameFactory.getInstance(item);
    }


    public static List<JLabel> getInstance(List<Item> itens) {
        return JLabelGameFactory.getInstance(itens);
    }

    public static JLabel getInstance(String name, int index) {
        return JLabelInformationFactory.getInstance(name, index);
    }

    public static JLabel getInstance(String name) {
        return JLabelInventoryFactory.getInstance(name);
    }
}
