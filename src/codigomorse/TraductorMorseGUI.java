/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package codigomorse;

/**
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TraductorMorseGUI {
    private final String[] letras = {
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
        "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };
    private final String[] morse = {
        ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
        "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."
    };
    private final String[][] matrizLetrasMorse = new String[26][2];
    private JFrame frame;
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JButton translateToMorseButton;
    private JButton translateToTextButton;

    public TraductorMorseGUI() {
        for (int i = 0; i < 26; i++) {
            matrizLetrasMorse[i][0] = letras[i];
            matrizLetrasMorse[i][1] = morse[i];
        }

        frame = new JFrame("Morse Translator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        inputTextArea = new JTextArea(5, 30);
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        JScrollPane inputScrollPane = new JScrollPane(inputTextArea);

        outputTextArea = new JTextArea(5, 30);
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
        outputTextArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);

        translateToMorseButton = new JButton("Traducir a Morse");
        translateToTextButton = new JButton("Traducir a Texto");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(translateToMorseButton);
        buttonPanel.add(translateToTextButton);

        frame.add(inputScrollPane, BorderLayout.NORTH);
        frame.add(outputScrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        translateToMorseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = inputTextArea.getText();
                String morseTexto = traducirAMorse(texto);
                outputTextArea.setText(morseTexto);
            }
        });

        translateToTextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String morseTexto = inputTextArea.getText();
                String textoTraducido = traducirALetras(morseTexto);
                outputTextArea.setText(textoTraducido);
            }
        });
        frame.pack();
        frame.setVisible(true);
    }

    public String traducirAMorse(String texto) {
        StringBuilder morseTraducido = new StringBuilder();
        for (char letra : texto.toUpperCase().toCharArray()) {
            if (Character.isLetter(letra)) {
                for (int i = 0; i < 26; i++) {
                    if (letras[i].equals(String.valueOf(letra))) {
                        morseTraducido.append(matrizLetrasMorse[i][1]).append(" ");
                        break;
                    }
                }
            } else if (letra == ' ') {
                morseTraducido.append(" ");
            }
        }
        return morseTraducido.toString();
    }

    public String traducirALetras(String morseTexto) {
        StringBuilder textoTraducido = new StringBuilder();
        String[] palabras = morseTexto.split("   ");
        for (String palabra : palabras) {
            String[] letrasMorse = palabra.split(" ");
            for (String letraMorse : letrasMorse) {
                for (int i = 0; i < 26; i++) {
                    if (morse[i].equals(letraMorse)) {
                        textoTraducido.append(letras[i]);
                        break;
                    }
                }
            }
            textoTraducido.append(" ");
        }
        return textoTraducido.toString().trim();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TraductorMorseGUI();
            }
        });
    }
}
