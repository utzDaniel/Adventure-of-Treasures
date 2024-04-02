package service;

import backend.Game;
import backend.repository.singleton.DoorRepository;
import backend.repository.singleton.ItemRepository;
import backend.repository.singleton.MapGameRepository;
import backend.service.enums.TypeItem;
import backend.service.infra.CacheService;
import backend.service.model.ItemFactory;
import backend.service.interfaces.IUsable;
import backend.service.model.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runners.MethodSorters;

import java.util.*;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventsTest {

    private final Map<Integer, MapGame> mapGame;
    private final Map<Integer, Item> itens;
    private final Map<Integer, Door> doors;

    public EventsTest() {
        this.mapGame = new HashMap<>();
        this.itens = new HashMap<>();
        this.doors = new HashMap<>();
        try {
            new Player(null, new Inventory(0, 10));
        } catch (Exception e) {
            System.out.println();
        }
    }

    @Before
    public void create() throws Exception {
        MapGameRepository.getInstance().getAll()
                .forEach(v -> this.mapGame.put(v.id(), new MapGameFactory().create(v)));
        ItemRepository.getInstance().getAll()
                .forEach(v -> this.itens.put(v.id(), new ItemFactory().create(v)));
        DoorRepository.getInstance().getAll()
                .forEach(v -> this.doors.put(v.id(), new Door(v)));
        this.mapGame.values().forEach(v -> CacheService.getMapGame(v.id()));
        this.doors.values().forEach(v -> CacheService.getDoor(v.getId()));
        this.itens.values().forEach(v -> CacheService.getItem(v.getId()));
    }


    @Test
    public void validItem1() {
        this.itens.get(1).warn();
        var door = this.doors.get(6);
        door.setOpen(true);
        assertEquals(Optional.of(door).toString(),CacheService.getDoor(6).toString());
        door.setOpen(false);
    }

    @Test
    public void validItem2() {
        this.itens.get(2).warn();
        var door = this.doors.get(9);
        door.setOpen(true);
        assertEquals(Optional.of(door).toString(), CacheService.getDoor(9).toString());
        var map = this.mapGame.get(8);
        map.getDoor(9).ifPresent(v -> v.setOpen(true));
        map.setImage("src/main/resources/image/map/temploF.png");
        assertEquals(map.getImage(), Objects.requireNonNull(CacheService.getMapGame(8).orElse(null)).getImage());
        door.setOpen(false);
        map.getDoor(9).ifPresent(v -> v.setOpen(false));
    }

    @Test
    public void validItem_3_4_6_13() {
        var ids = new int[]{3, 4, 6, 13};
        for (int id : ids) {
            this.itens.get(id).warn();
            var item = this.itens.get(16);
            assertEquals(item.toString(), Game.player.getInventory().getItem(16).toString());
            Game.player.getInventory().removeItem(item);
        }
    }

    @Test
    public void validItem_5_12() {
        var ids = new int[]{5, 12};
        for (int id : ids) {
            this.itens.get(id).warn();
            var item = this.itens.get(8);
            assertEquals(item.toString(), Game.player.getInventory().getItem(8).toString());
            Game.player.getInventory().removeItem(item);
            var map = this.mapGame.get(4);
            map.setImage("src/main/resources/image/map/praiaM.png");
            assertEquals(map.getImage(), Objects.requireNonNull(CacheService.getMapGame(4).orElse(null)).getImage());
            map.setImage("src/main/resources/image/map/praia.png");
            var itens = new ArrayList<IUsable>();
            this.itens.get(11)
                    .getSpecialization(TypeItem.USABLE)
                    .ifPresent(v -> itens.add(((IUsable) v)));
            CacheService.getItem(11)
                    .flatMap(i -> i.getSpecialization(TypeItem.USABLE))
                    .ifPresent(v -> itens.add(((IUsable) v)));
            itens.get(0).setEnabled(true);
            assertEquals(itens.get(0).isEnabled(), itens.get(1).isEnabled());
            itens.get(0).setEnabled(false);
        }
    }

    @Test
    public void validItem_7_9_14() {
        var ids = new int[]{7, 9, 14};
        for (int id : ids) {
            this.itens.get(id).warn();
            var item = this.itens.get(2);
            assertEquals(item.toString(), Game.player.getInventory().getItem(2).toString());
            Game.player.getInventory().removeItem(item);
        }
    }


    @Test
    public void validItem11() {
        this.itens.get(11).warn();
        var map = this.mapGame.get(4);
        map.setImage("src/main/resources/image/map/praia.png");
        assertEquals(map.getImage(), Objects.requireNonNull(CacheService.getMapGame(4).orElse(null)).getImage());
        map.setImage("src/main/resources/image/map/praiaM.png");
    }

    @Test
    @DisplayName("falta isso > tocha em vez de abrir/bloquear a porta, alterar a image do mapa")
    public void validItem16() {
        this.itens.get(16).warn();
        var door = this.doors.get(10);
        door.setOpen(true);
        assertEquals(Optional.of(door).toString(), CacheService.getDoor(10).toString());
        door.setOpen(false);
    }

}
