package view;

import exception.ButtonException;

import javax.swing.*;
import java.awt.*;

public class ButtonAction {

    private static int positionX;

    public ButtonAction(){
        positionX = 15;
    }

    public JButton create(String name){
        JButton button = new JButton(name.toUpperCase());
        button.setActionCommand(name);
        button.setAlignmentY(0.0f);
        button.setFont(new Font("Arial", Font.PLAIN, 10));
        button.setMargin(new Insets(0,0,0,0));
        button.setForeground(Colors.WHITE);
        button.setVisible(false);
        style(name, button);
        return button;
    }

    private void style(String name, JButton button) {
        switch (name) {
            case "usar" -> action(button);
            case "equipar" -> action(button);
            case "combinar" -> action(button);
            case "remover" -> action(button);
            case "cancelar" -> actionCancel(button);
            case "confirmar" -> actionConfirm(button);
            default -> throw new ButtonException("Nome do botão não encontrado");
        }
    }

    private void action(JButton button){
        button.setBackground(Colors.BLUE);
        button.setBounds(positionX, 320, 70, 30);
        positionX += 80;
    }

    private void actionCancel(JButton button){
        button.setBackground(Colors.RED);
        button.setBounds(30, 360, 130, 30);
    }

    private void actionConfirm(JButton button){
        button.setBackground(Colors.GREEN);
        button.setBounds(180, 360, 130, 30);
    }
}
