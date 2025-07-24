package org.example;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LeitorExcel {
    private static final DateTimeFormatter mesAnoFormatter = DateTimeFormatter.ofPattern("MMM-yyyy", new Locale("pt", "BR"));
    private static final DateTimeFormatter apenasAnoFormatter = DateTimeFormatter.ofPattern("yyyy");
    public static List<Extintor> lerExtintoresDoExcel(InputStream arquivoExcel) {
        List<Extintor> extintores = new ArrayList<>();

        //Tentando Abrir e processar o Arquivo Excel
        try {
            Workbook workbook = new XSSFWorkbook(arquivoExcel);
            Sheet sheet = workbook.getSheetAt(0); //pega a primeira aba

            int primeiraLinha = sheet.getFirstRowNum() + 1;
            int ultimaLinha = sheet.getLastRowNum();

            for (int i = primeiraLinha; i <= ultimaLinha; i++) {
                Row row = sheet.getRow(i);

                //Pegar os Valores das celulas
                String numeroDePosicionamento = row.getCell(0).getStringCellValue();
                String tipo = row.getCell(1).getStringCellValue();
                String capacidade = row.getCell(2).getStringCellValue();
                String numeroDeIdentificacao = row.getCell(3).getStringCellValue();
                String dataDeRecarga = formatarData(row.getCell(4), mesAnoFormatter);
                String ultimoTeste = formatarData(row.getCell(5), apenasAnoFormatter);

                Extintor extintor = new Extintor(
                        numeroDePosicionamento, tipo, capacidade,
                        numeroDeIdentificacao, dataDeRecarga, ultimoTeste
                );

                extintores.add(extintor);
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo" + e.getMessage());
        }
        return extintores;

    }

    //pegar os valores e passar para texto independente do conteudo da celula
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        } else if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            // Caso seja data, formatar com dia, mês e ano
            LocalDate data = cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return data.toString(); // ou formatar como quiser
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf((long) cell.getNumericCellValue());
        }
        return "";
    }

    private static String formatarData(Cell cell, DateTimeFormatter formatter) {
        if (cell == null || !DateUtil.isCellDateFormatted(cell)) return "";
        LocalDate data = cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return data.format(formatter);
    }
}
