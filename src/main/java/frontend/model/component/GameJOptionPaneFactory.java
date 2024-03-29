package frontend.model.component;

import javax.swing.*;
import java.awt.*;

public final class GameJOptionPaneFactory {

    private GameJOptionPaneFactory() {
    }

    public static void openHistory(Container container) {
        JOptionPane.showMessageDialog(container,
                """
                        Bem-vindo ao Adventure of Treasures!
                                                
                        Você é um historiador, que adora se aventurar a procura de tesouros antigos, sua nova busca
                        são os antigos tesouros templários. 1 semana atrás, após muitas pesquisas surgiu uma pista
                        sobre o paradeiro do tesouro, ela aponta para a Vila Fantasma localizada na Ilha Voraste,
                        um antigo lugar esquecido após o fim da época das Cruzadas.
                                                
                        Neste momento você chegou á ilha e tem como único objetivo encontrar o tesouro.
                        """,
                "Historia",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void openCommand(Container container) {
        JOptionPane.showMessageDialog(container,
                """
                         ↑ - Mover para o Norte
                         ← - Mover para o Oeste
                         → - Mover para o Leste
                         ↓ - Mover para o Sul
                         1 - Interagir (item, porta, NPC)
                         0 - Abrir inventario
                        F5 - Carregar jogo salvo
                        F6 - Salvar jogo
                        """, "Comandos",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void openHelp(Container container) {
        JOptionPane.showMessageDialog(container,
                """
                        Você está na Ilha Voraste
                        Seu objetivo é encontrar o tesouro templário,\s
                        depois entrar no barco e interagir com o Capitao do Navio""",
                "Ajuda",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
