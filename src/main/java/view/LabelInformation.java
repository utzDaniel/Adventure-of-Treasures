package view;

import model.builder.item.Item;

import javax.swing.*;
import java.awt.*;

public class LabelInformation {

    private int index;
    private int positionY;
    private final int height = 14;
    private final JLabel[] infoLabel;

    public LabelInformation(){
        this.positionY = 222;
        this.infoLabel = new JLabel[4];
        this.index = 0;
    }

    public void create(String info){
        setInfoLabel(info);
        style(info);
        update();
    }

    private void setInfoLabel(String info) {
        infoLabel[index] = new JLabel(info);
        infoLabel[index].setFont(new Font("Arial", Font.PLAIN, 12));
        infoLabel[index].setForeground(Colors.WHITE);
        infoLabel[index].setVerticalAlignment(SwingConstants.TOP);
        infoLabel[index].setAlignmentY(0.0f);
    }

    private void style(String name) {
        if (name.contains("Descrição")) {
            infoLabel[index].setBounds(17, positionY, 305, height*2);// 2 Linha
        }else{
            infoLabel[index].setBounds(17, positionY, 305, height);
        }
        if(name.contains("Capacidade do inventario")){
            infoLabel[index].setHorizontalAlignment(SwingConstants.RIGHT);
        }else{
            infoLabel[index].setHorizontalAlignment(SwingConstants.LEFT);
        }
    }

    private void update(){
        index++;
        positionY += 20;
    }

    public void updateText(Item item){
        infoLabel[1].setText("Nome: " + item.getName());
        infoLabel[2].setText("Peso: " + item.getWeight());
        infoLabel[3].setText("<html>Descrição: " + item.getDescription()+"</html>");
    }

    public void resetText(int capacity,int maxCapacity ){
        infoLabel[0].setText(String.format("Capacidade do inventario %d/%d",capacity, maxCapacity));
        infoLabel[1].setText("Nome: ");
        infoLabel[2].setText("Peso: ");
        infoLabel[3].setText("Descrição: ");
    }

    public JLabel[] getInfoLabel() {
        return infoLabel;
    }

}
