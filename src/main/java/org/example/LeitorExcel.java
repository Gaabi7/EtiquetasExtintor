package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LeitorExcel {

    private static final Locale LOCALE_PTBR = new Locale("pt", "BR");
    private static final DateTimeFormatter FORMATADOR_MES_ANO = DateTimeFormatter.ofPattern("MMM-yyyy", LOCALE_PTBR);
    private static final DateTimeFormatter FORMATADOR_APENAS_ANO = DateTimeFormatter.ofPattern("yyyy");

    public static List<Extintor> lerExtintoresDoExcel(InputStream arquivoExcel) {
        List<Extintor> extintores = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(arquivoExcel)) {
            Sheet sheet = workbook.getSheetAt(0);
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

            int primeiraLinha = sheet.getFirstRowNum() + 2; // pula cabeçalho
            int ultimaLinha = sheet.getLastRowNum();

            for (int i = primeiraLinha; i <= ultimaLinha; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String numeroDePosicionamento = getTextoSimples(row.getCell(0), evaluator);
                String tipo = getTextoSimples(row.getCell(1), evaluator);
                String capacidade = getTextoSimples(row.getCell(2), evaluator);
                String numeroDeIdentificacao = getTextoSimples(row.getCell(3), evaluator);
                String regiao = getTextoSimples(row.getCell(6), evaluator);
                String endereco = getTextoSimples(row.getCell(7), evaluator);

                String dataRecargaCompleta = getDataFormatada(row.getCell(4), FORMATADOR_MES_ANO, evaluator);
                String proximaRecargaCompleta = getDataFormatada(row.getCell(8), FORMATADOR_MES_ANO, evaluator);

                String anoUltimoTeste = getDataFormatada(row.getCell(5), FORMATADOR_APENAS_ANO, evaluator);
                String anoProximoTeste = getDataFormatada(row.getCell(9), FORMATADOR_APENAS_ANO, evaluator);

                String mesRecarga = "";
                String anoRecarga = "";
                if (!dataRecargaCompleta.isEmpty() && dataRecargaCompleta.contains("-")) {
                    String[] partes = dataRecargaCompleta.split("-");
                    mesRecarga = partes[0];
                    anoRecarga = partes[1];
                }

                String mesProximaRecarga = "";
                String anoProximaRecarga = "";

                if (!proximaRecargaCompleta.isEmpty() && proximaRecargaCompleta.contains("-")) {
                    String[] partes = proximaRecargaCompleta.split("-");
                    mesProximaRecarga = partes[0];
                    anoProximaRecarga = partes[1];
                }

                extintores.add(new Extintor(
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
                ));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return extintores;
    }

    private static String getTextoSimples(Cell cell, FormulaEvaluator evaluator) {
        if (cell == null) return "";
        DataFormatter df = new DataFormatter(LOCALE_PTBR);
        return df.formatCellValue(cell, evaluator).trim();
    }

    private static String getDataFormatada(Cell cell, DateTimeFormatter formatter, FormulaEvaluator evaluator) {
        if (cell == null) return "";
        DataFormatter df = new DataFormatter(LOCALE_PTBR);

        CellType tipo = cell.getCellType();
        if (tipo == CellType.FORMULA) {
            tipo = evaluator.evaluateFormulaCell(cell);
        }

        if (tipo == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            Date dataValor = cell.getDateCellValue();
            if (dataValor != null) {
                LocalDate data = dataValor.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                return data.format(formatter);
            }
        }

        String texto = df.formatCellValue(cell, evaluator).trim();
        if (texto.isEmpty()) return "";

        try {
            if (formatter == FORMATADOR_APENAS_ANO && texto.matches("\\d{4}")) {
                return texto;
            }

            if (formatter == FORMATADOR_MES_ANO) {
                String[] padroes = {"MMM-yyyy", "MMM-yy", "MM-yyyy", "MM/yy", "MM/yyyy"};

                for (String padrao : padroes) {
                    try {
                        LocalDate data = LocalDate.parse("01-" + texto,
                            DateTimeFormatter.ofPattern("dd-" + padrao, LOCALE_PTBR));
                        return data.format(formatter);
                    } catch (Exception ignore) {
                    }
                }
            }
        } catch (Exception e) {
            return texto;
        }

        return "";
    }
}

