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
                String numeroDePosicionamento = getCellValueAsString(row.getCell(0));
                String tipo = getCellValueAsString(row.getCell(1));
                String capacidade = getCellValueAsString(row.getCell(2));
                String numeroDeIdentificacao = getCellValueAsString(row.getCell(3));
                String regiao = getCellValueAsString(row.getCell(6));
                String endereco = getCellValueAsString(row.getCell(7));

                //separa as datas
                String dataDeRecarga = formatarData(row.getCell(4), mesAnoFormatter);
                String mesRecarga = "", anoRecarga = "";
                if (dataDeRecarga.contains("/")) {
                    String[] partes = dataDeRecarga.split("/");
                    mesRecarga = partes[0];
                    anoRecarga = partes[1];
                }

                //ultimo Teste (somente ano)
                String anoUltimoTeste = formatarData(row.getCell(5), apenasAnoFormatter);

                // Proxima Recarga (mes-ano)
                String proximaRecarga = formatarData(row.getCell(8), mesAnoFormatter);
                String mesProximaRecarga = "", anoProximaRecarga = "";
                if (proximaRecarga.contains("/")) {
                    String[] partes = proximaRecarga.split("/");
                    mesProximaRecarga = partes[0];
                    anoProximaRecarga = partes[1];
                }

                // Proximo Teste (somente ano)
                String anoProximoTeste = formatarData(row.getCell(9), apenasAnoFormatter);

                Extintor extintor = new Extintor(
                    numeroDePosicionamento,     
                    tipo,                       
                    capacidade,                 
                    numeroDeIdentificacao,      
                    regiao,                     
                    endereco,                   
                    mesRecarga,                 
                    anoRecarga,                 
                    anoUltimoTeste,             
                    mesProximaRecarga,          
                    anoProximaRecarga,          
                    anoProximoTeste             
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
