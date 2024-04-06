package frontend.model.component;

import backend.controller.interfaces.IComponentInfo;
import frontend.enums.ComponentsProperties;

import javax.swing.*;
import java.awt.*;
import java.util.List;

final class GameJLabelFactory {

    private GameJLabelFactory() {
    }

    static List<JLabel> getInstance(List<IComponentInfo> infos) {
        return infos.stream()
                .map(GameJLabelFactory::create)
                .toList();
    }

    private static JLabel create(IComponentInfo info) {
        var jLabel = new JLabel();
        jLabel.setName(info.name());
        var icon = new ImageIcon(info.image());
        jLabel.setIcon(icon);
        //TODO resolver isso depois
        Dimension dimension;
        if (info.name().equals("DECORATION")) {
            dimension = new Dimension(icon.getIconWidth(), icon.getIconHeight());
        } else {
            dimension = Enum.valueOf(ComponentsProperties.class, info.name()).getDimension();
        }
        var rectangle = new Rectangle(info.point(), dimension);
        jLabel.setBounds(rectangle);
        return jLabel;
    }

}
