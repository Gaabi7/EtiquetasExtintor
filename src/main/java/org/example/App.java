package org.example;

import java.io.InputStream;
import java.util.List;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        InputStream arquivo = App.class.getResourceAsStream("/extintores.xlsx");

        if (arquivo == null) {
            System.out.println("Arquivo não encontrado");
            return;
        } else {
            System.out.println("Arquivo encontrado");
        }

        List<Extintor> extintores = LeitorExcel.lerExtintoresDoExcel(arquivo);

        

        System.out.println("Iniciando aplicação...");

        SwingUtilities.invokeLater(() -> {
            // fazendo com que ela apareça na tela.
            new InterfaceExtintor();
        });

        
    }
}