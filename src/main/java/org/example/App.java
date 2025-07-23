package org.example;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        InputStream arquivo = App.class.getClassLoader().getResourceAsStream("extintores.xlsx");

        if (arquivo == null) {
            System.out.println("Arquivo não encontrado");
        } else {
            System.out.println("Arquivo encontrado");
        }

        List<Extintor> extintores = LeitorExcel.lerExtintoresDoExcel(arquivo);

    }
}
