package org.example;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LeitorExcel {
    public static List<Extintor> lerExtintoresDoExcel(InputStream arquivoExcel) {
        List<Extintor> extintores = new ArrayList<>();

        //Tentando Abrir e processar o Arquivo Excel
        try {
            Workbook workbook = new XSSFWorkbook(arquivoExcel);
            Sheet sheet = workbook.getSheetAt(0); //pega a primeira aba

            int primeiraLinha = sheet.getFirstRowNum() + 1;
            int ultimaLinha = sheet.getLastRowNum();

            for (int i = primeiraLinha; i <= ultimaLinha; i++) {
                var row = sheet.getRow(i);
            }



        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo" + e.getMessage());
        }
        return extintores;

    }
}
