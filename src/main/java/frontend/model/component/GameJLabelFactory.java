package frontend.model.component;

import backend.controller.interfaces.IComponentInfo;
import backend.service.enums.Move;
import frontend.enums.ComponentsProperties;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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
        jLabel.setIcon(new ImageIcon(info.image()));
        var properties = Enum.valueOf(ComponentsProperties.class, info.name());
        var rectangle = new Rectangle(info.point(), properties.getDimension());
        jLabel.setBounds(rectangle);
        return jLabel;
    }

}
