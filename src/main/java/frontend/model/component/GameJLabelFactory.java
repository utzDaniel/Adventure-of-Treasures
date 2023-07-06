package frontend.model.component;

import backend.controller.interfaces.ICoordinateDTO;
import backend.controller.interfaces.IItemDTO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

final class GameJLabelFactory {

    private GameJLabelFactory() {
    }

    static JLabel getInstance(String icon, ICoordinateDTO coordinate) {
        var width = 32;
        var height = 47;
        var rectangle = new Rectangle(coordinate.x(), coordinate.y(), width, height);
        return create(new ImageIcon(icon), "player", rectangle);
    }

    static JLabel getInstance(String name, String icon) {
        var x = 0;
        var y = 0;
        var width = 800;
        var height = 600;
        var rectangle = new Rectangle(x, y, width, height);
        return create(new ImageIcon(icon), name, rectangle);
    }

    static JLabel getInstance(IItemDTO item) {
        var width = 100;
        var height = 100;
        var rectangle = new Rectangle(item.coordinate().x(), item.coordinate().y() - 10, width, height);
        return create(new ImageIcon(item.icon()), "item", rectangle);
    }

    static List<JLabel> getInstance(List<IItemDTO> itens) {
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
