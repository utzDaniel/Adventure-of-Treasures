package backend.service;

import backend.Game;
import backend.controller.enums.TypeMessage;
import backend.controller.interfaces.IActionService;
import backend.controller.interfaces.IServiceResponse;
import backend.service.command.*;
import backend.service.component.inventory.quit.InventoryQuit;
import backend.service.dto.response.ServiceResponse;
import backend.service.enums.Direction;
import backend.service.mapper.ActionResponseMapper;
import backend.service.mapper.PlayerSaveReadMapper;
import backend.service.model.Player;
import backend.service.util.FileUtil;

import java.io.IOException;
import java.util.Objects;

public final class ActionService implements IActionService {

    /**
     * O service é responsável por conter a lógica de negócio da aplicação.
     * Ele encapsula as operações e regras de negócio que são necessárias para realizar as tarefas desejadas.
     * O service recebe os dados do controller, executa a lógica necessária e se comunica com o repository,
     * se for necessário acessar dados persistentes.
     * O service também pode realizar validações adicionais,
     * orquestrar várias operações do repository e aplicar regras de negócio mais complexas.
     */
    @Override
    public IServiceResponse refresh() {
        var inventory = Game.player.getInventory();
        var typeMessage = new InventoryQuit(inventory).run();

        if (!typeMessage.isSucess())
            new ServiceResponse(typeMessage, null);

        var obj = new ActionResponseMapper().apply(Game.player);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse interact() {
        var typeMessage = new InteractCommand(Game.player).execute();

        if (!typeMessage.isSucess())
            new ServiceResponse(typeMessage, null);

        var obj = new ActionResponseMapper().apply(Game.player);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse move(String direction) {
        var direction1 = Direction.getInstance(direction);

        if (Objects.isNull(direction1)) return new ServiceResponse(TypeMessage.DIRECTION_ERRO_INVALID, null);

        var cmd = new MoveCommand(Game.player, direction1);
        var typeMessage = cmd.execute();

        if (!typeMessage.isSucess())
            new ServiceResponse(typeMessage, null);

        var obj = new ActionResponseMapper().apply(Game.player);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse load() {
        var filename = "src/main/resources/save/player.txt";
        var fileUtil = new FileUtil<Player>(filename);
        try {
            Game.player = fileUtil.readFile(new PlayerSaveReadMapper());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return move(Game.player.getDirection().name());
    }

    @Override
    public IServiceResponse save() {
        var filename = "src/main/resources/save/player.txt";
        var fileUtil = new FileUtil<Player>(filename);
        try {
            fileUtil.writeFile(Game.player);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return refresh();
    }
}
