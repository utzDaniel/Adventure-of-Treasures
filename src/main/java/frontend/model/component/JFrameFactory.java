package frontend.model.component;

import javax.swing.*;
import java.awt.*;

public final class JFrameFactory {

    private JFrameFactory() {
    }

    public static JFrame getInstance() {
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

}
