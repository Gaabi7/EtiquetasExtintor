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

        // Apenas para testar se leu os dados
        for (Extintor ext : extintores) {
            System.out.println(ext);
        }

        System.out.println("Iniciando aplicação...");

        SwingUtilities.invokeLater(() -> {
            // Esta linha cria uma nova instância da sua janela,
            // fazendo com que ela apareça na tela.
            new InterfaceExtintor();
        });

        
    }
}