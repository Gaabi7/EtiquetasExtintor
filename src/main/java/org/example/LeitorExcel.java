package org.example;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LeitorExcel {

    private static final DateTimeFormatter mesAnoFormatter = DateTimeFormatter.ofPattern("MMM-yyyy", new Locale("pt", "BR"));
    private static final DateTimeFormatter apenasAnoFormatter = DateTimeFormatter.ofPattern("yyyy");

    public static List<Extintor> lerExtintoresDoExcel(InputStream arquivoExcel) {
        List<Extintor> extintores = new ArrayList<>();

        //Tentando Abrir e processar o Arquivo Excel
        try {
            Workbook workbook = new XSSFWorkbook(arquivoExcel);
            Sheet sheet = workbook.getSheetAt(0); //pega a primeira aba

            int primeiraLinha = sheet.getFirstRowNum() + 2;
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
                String dataRecargaFormatada = formatarData(row.getCell(4), mesAnoFormatter); // Retorna "mai/2024"
                String mesRecarga = "";
                String anoRecarga = "";
                if (dataRecargaFormatada != null && !dataRecargaFormatada.isEmpty()) {
                    String[] partes = dataRecargaFormatada.split("/");
                    if (partes.length == 2) {
                        mesRecarga = partes[0]; // "mai"
                        anoRecarga = partes[1]; // "2024"
                    }
                }

                //ultimo Teste (somente ano)
                String anoUltimoTeste = formatarData(row.getCell(5), apenasAnoFormatter);

                // Proxima Recarga (mes-ano)
                String proximaRecargaFormatada = formatarData(row.getCell(8), mesAnoFormatter);
                String mesProximaRecarga = "";
                String anoProximaRecarga = "";
                if (proximaRecargaFormatada != null && !proximaRecargaFormatada.isEmpty()) {
                    String[] partes = proximaRecargaFormatada.split("/");
                    if (partes.length == 2) {
                        mesProximaRecarga = partes[0];
                        anoProximaRecarga = partes[1];
                    }
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
            e.printStackTrace();
        }
        return extintores;
    }

        

    //pegar os valores e passar para texto independente do conteudo da celula
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return ""; // Célula nula, retorna string vazia
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
        
            case NUMERIC:
                // Verifica se a célula é uma data antes de tratar como número
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString(); 
                } else {
                    // Trata números puros, preservando casas decimais se existirem
                    double valor = cell.getNumericCellValue();
                    if (valor == (long) valor) {
                        // Se o número for inteiro (ex: 123.0), converte para "123"
                        return String.valueOf((long) valor);
                    } else {
                        // Se tiver casas decimais (ex: 2.5), converte para "2.5"
                        return String.valueOf(valor);
                    }
                }
            case BLANK:
                return "";

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case FORMULA:
                try {
                    return cell.getStringCellValue().trim();
                }catch (Exception e) {
                    // Se a fórmula resultar em erro ou não for string, pode tentar como número
                    try {
                        return String.valueOf(cell.getNumericCellValue());
                    } catch (Exception e2) {
                        return ""; // Retorna vazio se tudo falhar
                    }
                }

            default:
            return "";
        
        }
    }

    private static String formatarData(Cell cell, DateTimeFormatter formatter) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return "";
        }
        
        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            LocalDate data = cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return data.format(formatter);
        }
        // Retorna vazio se a célula não for uma data válida
        return "";
    }
}
