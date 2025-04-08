package pck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static JFrame frame;
    private static JButton miniMaxButton;
    private static JButton heuristicButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Jogo Dots");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 1));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        miniMaxButton = new JButton("MiniMax");
        miniMaxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(1);
            }
        });
        panel.add(miniMaxButton);

        heuristicButton = new JButton("Algoritmo Heurístico");
        heuristicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(2);
            }
        });
        panel.add(heuristicButton);

        frame.add(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void startGame(int choice) {
        frame.dispose(); // Fecha a janela de seleção de jogo

        // Inicia o jogo escolhido
        if (choice == 1) {
            dots dots = new dots();
            dots.jogo();
        } else if (choice == 2) {
            dotsEuristico dots = new dotsEuristico();
            dots.jogo();
        }
    }
}
