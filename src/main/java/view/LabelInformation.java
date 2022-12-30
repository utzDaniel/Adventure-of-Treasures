package view;

import exception.ButtonException;

import javax.swing.*;
import java.awt.*;

public class LabelInformation {

    private int positionY;

    public LabelInformation(){
        positionY = 190;
    }

    public JLabel create(String info){
        JLabel label = new JLabel(info);
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        label.setForeground(Colors.WHITE);
        label.setAlignmentY(0.0f);
        label.setBounds(17, positionY, 300, 100);
        style(info, label);
        positionY += 20;
        return label;
    }

    private void style(String name, JLabel label) {
        if(name.contains("Capacidade do inventario")){
            label.setHorizontalAlignment(SwingConstants.RIGHT);
        }else{
            label.setHorizontalAlignment(SwingConstants.LEFT);
        }
    }

}
