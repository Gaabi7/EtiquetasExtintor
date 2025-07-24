package org.example;

import java.io.InputStream;
import java.util.List;

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
    }
}