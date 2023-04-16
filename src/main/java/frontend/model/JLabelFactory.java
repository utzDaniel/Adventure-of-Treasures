package frontend.model;

import backend.model.Player;
import backend.model.builder.item.Item;
import backend.model.builder.map.MapGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class JLabelFactory {

    public static JLabel getInstance(Player player) {
        var rectangle = new Rectangle(player.getLocation().getX(), player.getLocation().getY(), 32, 47);
        return create(player.getIcon(), "player", rectangle);
    }

    public static JLabel getInstance(MapGame mapGame) {
        var rectangle = new Rectangle(0, 0, 800, 600);
        return create(mapGame.getIcon(), "mapa", rectangle);
    }

    public static JLabel getInstance(Item item) {
        var rectangle = new Rectangle(item.getLocation().getX(), item.getLocation().getY()-10, 100, 100);
        return create(item.getIcon(), "item", rectangle);
    }

    public static List<JLabel> getInstance(List<Item> itens) {
        var listJLabel = new ArrayList<JLabel>();
        itens.forEach(item -> listJLabel.add(getInstance(item)));
        return listJLabel;
    }

    private static JLabel create(ImageIcon icon, String name, Rectangle rectangle) {
        var jLabel = new JLabel();
        jLabel.setIcon(icon);
        jLabel.setName(name);
        jLabel.setBounds(rectangle);
        return jLabel;
    }
}
