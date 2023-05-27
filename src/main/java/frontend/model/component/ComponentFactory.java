package frontend.model.component;

import backend.model.Player;
import backend.model.builder.item.Item;
import backend.model.builder.map.MapGame;
import rules.interfaces.IItem;

import javax.swing.*;
import java.util.List;

public final class ComponentFactory {

    private ComponentFactory() {
    }

    public static JFrame getJFrame() {
        var title = "Adventure of Treasures - Version 1.0";
        var closeOperation = 3;
        var width = 810;
        var height = 662;
        var frame = new JFrame();
        frame.setName("frame");
        frame.setTitle(title);
        frame.setDefaultCloseOperation(closeOperation);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);//Deixar a tela no meio automatizamente
        frame.setLayout(null);
        frame.setResizable(false);//Disabilitar o maximizar e aumentar as lateral
        frame.getContentPane().setLayout(null);
        return frame;
    }

    public static JMenuBar getJMenuBar() {
        var menuBar = new JMenuBar();
        menuBar.setName("menubar");
        return menuBar;
    }

    public static JMenu getJMenu(String name) {
        var menu = new JMenu();
        menu.setName("menu");
        menu.setText(name);
        return menu;
    }

    public static JMenuItem getJMenuItem(String name) {
        var nameItem = name.equals("Musica") || name.equals("Efeitos") ? "On/Off" : name;
        var jMenuItem = new JMenuItem();
        jMenuItem.setName("menuitem");
        jMenuItem.setText(nameItem);
        return jMenuItem;
    }

    public static JPanel getJPanel(String name) {
        return InventoryJPanelFactory.getInstance(name);
    }

    public static JLabel getJLabel(MapGame mapGame) {
        return GameJLabelFactory.getInstance(mapGame);
    }

    public static JLabel getJLabel(Player player) {
        return GameJLabelFactory.getInstance(player);
    }

    public static JLabel getJLabel(IItem item) {
        return GameJLabelFactory.getInstance(item);
    }

    public static List<JLabel> getJLabel(List<IItem> item) {
        return GameJLabelFactory.getInstance(item);
    }

    public static JLabel getJLabel(String name) {
        return InventoryJLabelFactory.getInstance(name);
    }

    public static JLabel getJLabel(String name, int index) {
        return InventoryInformationJLabelFactory.getInstance(name, index);
    }

    public static JButton getJButton(String name) {
        return InventoryJButtonActionFactory.getInstance(name);
    }

    public static JButton getJButton(Item item, int index) {
        return InventoryJButtonItemFactory.getInstance(item, index);
    }
}
