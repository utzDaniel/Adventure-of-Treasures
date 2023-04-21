package frontend.model.component;

import backend.model.Player;
import backend.model.builder.item.Item;
import backend.model.builder.map.MapGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

final class JLabelGameFactory {

    private JLabelGameFactory() {
    }

    static JLabel getInstance(Player player) {
        var width = 32;
        var height = 47;
        var rectangle = new Rectangle(player.getLocation().getX(), player.getLocation().getY(), width, height);
        return create(player.getIcon(), "player", rectangle);
    }

    static JLabel getInstance(MapGame mapGame) {
        var x = 0;
        var y = 0;
        var width = 800;
        var height = 600;
        var rectangle = new Rectangle(x, y, width, height);
        return create(mapGame.getIcon(), "mapa", rectangle);
    }

    static JLabel getInstance(Item item) {
        var width = 100;
        var height = 100;
        var rectangle = new Rectangle(item.getLocation().getX(), item.getLocation().getY() - 10, width, height);
        return create(item.getIcon(), "item", rectangle);
    }

    static List<JLabel> getInstance(List<Item> itens) {
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
