package org.example;

import java.io.InputStream;
import java.util.List;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {

        System.out.println("Iniciando aplicação...");

        SwingUtilities.invokeLater(() -> {
            // fazendo com que ela apareça na tela.
            new InterfaceExtintor();
        });
    }
}