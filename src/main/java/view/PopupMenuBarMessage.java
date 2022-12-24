package view;

import javax.swing.*;

public class PopupMenuBarMessage {

    private final JFrame frame;

    public PopupMenuBarMessage(JFrame frame){
        this.frame = frame;
    }

    public void history(){
        JOptionPane.showMessageDialog(frame,
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

    public void command(){
        JOptionPane.showMessageDialog(frame,
                """
                        ↑ - Mover para o Norte
                        ← - Mover para o Oeste
                        → - Mover para o Leste
                        ↓ - Mover para o Sul
                        1 - Abrir porta e Interagir com o Capitao do barco
                        2 - Pegar item
                        3 - Abrir inventario
                        ""","Comandos",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void help(){
        JOptionPane.showMessageDialog(frame,
                """
                        Você está na Ilha Voraste
                        Seu objetivo é encontrar o tesouro templário,\s
                        depois entrar no barco e interagir com o Capitao do Navio""",
                "Ajuda",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
