package view;

import model.Item;

import javax.swing.*;

public class ButtonItem {

    private int index;
    private int positionX;
    private int positionY;
    private final JButton[] buttonItens;

    public ButtonItem(){
        buttonItens = new JButton[24];
        reset();
    }

    public void create(Item item){
        setButtonItens(item);
        update();
    }

    private void setButtonItens(Item item) {
        buttonItens[index] = new JButton();
        buttonItens[index].setActionCommand(item.getName());
        buttonItens[index].setName(item.getName());
        buttonItens[index].setIcon(item.getImagemIcon());
        buttonItens[index].setBackground(Colors.BROWN_2);
        buttonItens[index].setBounds(positionX, positionY, 37, 38);
    }

    private void update(){
        index++;
        positionX += 53;
        if(index % 6 == 0) updateLine();
    }

    private void updateLine(){
        positionY += index <= 5 ? 55 : 51;
        positionX = 17;
    }

    private void reset(){
        index = 0;
        positionX = 17;
        positionY = 14;
    }

    public void remove(JLabel labelSideEast) {
        for (int i = 0; i < index; i++) {
            labelSideEast.remove(buttonItens[i]);
            buttonItens[i] = null;
        }
        reset();
    }

    public JButton getLast() {
        if(index == 0)
            return null;
        return buttonItens[index-1];
    }

    public JButton[] getButtonItens() {
        JButton[] buttons = new JButton[index];
        System.arraycopy(buttonItens, 0, buttons, 0, index);
        return buttons;
    }
}
