package repository;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class CreateItem {

    private final List<Item> item;
    private final List<Item> itemInvisiblePlayer;

    public CreateItem (){
        item = new ArrayList<>();
        itemInvisiblePlayer = new ArrayList<>();
        createItens();
    }

    private void createItens() {
        CreateImageItem createImageItem = new CreateImageItem();
        item.add(new ItemNotRemove("tesouro", "tesouro lendário dos templários",null, 3,620,240,createImageItem.selectImage("tesouro")));//0
        item.add(new ItemCombinable("madeira", "cabo de madeira velho", 5, 3,410,200,createImageItem.selectImage("madeira")));//1
        item.add(new ItemCombinable("pederneira", "item específico para fazer fogo", 2, 3,440,200,createImageItem.selectImage("pederneira")));//2
        item.add(new ItemCombinable("madeiras", "madeira para construir algo", 5, 2,640,80,createImageItem.selectImage("madeiras")));//3
        item.add(new ItemCombinable("faca", "serve para cortar algo", 3, 3,420,130,createImageItem.selectImage("faca")));//4
        item.add(new ItemCombinable("frasco", "contém algum líquido inflamável", 3, 3,280,310,createImageItem.selectImage("frasco")));//5
        item.add(new ItemCombinable("martelo", "utilzado para construir algo", 4, 2,160,320,createImageItem.selectImage("martelo")));//6
        item.add(new ItemCombinable("papel", "papel escrito em lingua antiga", 2, 1,510,320,createImageItem.selectImage("papel")));//7
        item.add(new ItemUsable("pa", "ferramenta usada para cavar", 3, "praia",200,280,createImageItem.selectImage("pa")));//8
        item.add(new ItemCombinable("pregos", "utilzado para construir algo", 3, 2,460,400,createImageItem.selectImage("pregos")));//9
        item.add(new ItemCombinable("livro", "livro antigo usado para decifrar escrita antiga", 1,1,490,390,createImageItem.selectImage("livro")));//10
        item.add(new ItemEquipable("mochila", "utilizada para carregar mais coisas", 0,650,220,createImageItem.selectImage("mochila")));//11
        item.add(new ItemNotRemove("mapa", "algo está enterrado na praia",null, 0,410,200,createImageItem.selectImage("mapa")));//12
        item.add(new ItemUsable("chave", "utilizada para abir algo", 3, "vila",580,300,createImageItem.selectImage("chave")));//13
        item.add(new ItemUsable("escada", "utilizada para subir em algum lugar", 5, "templo",410,200,createImageItem.selectImage("escada")));//14
        item.add(new ItemEquipable("tocha", "utilizado para iluminar", 5,410,200,createImageItem.selectImage("tocha")));//15

        //itens invisible
        item.get(13).setVisible(false);//praia
        itemInvisiblePlayer.add(item.get(12));
        itemInvisiblePlayer.add(item.get(14));
        itemInvisiblePlayer.add(item.get(15));
        item.get(12).setVisible(false);
        item.get(14).setVisible(false);
        item.get(15).setVisible(false);
    }

    public Item getItem(String nameItem){
        for (Item itens : item) {
            if (itens.getName().equals(nameItem)){
                return itens;
            }
        }
        return null;
    }

    public void setLocalMapa(MapGame mapGame){
        ((ItemNotRemove)item.get(12)).setMapGame(mapGame);
    }

    public List<Item> getItemInvisiblePlayer(){
        return itemInvisiblePlayer;
    }
}