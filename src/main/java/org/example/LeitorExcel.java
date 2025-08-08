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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LeitorExcel {

    private static final DateTimeFormatter FORMATADOR_MES_ANO = DateTimeFormatter.ofPattern("MMM-yyyy", new Locale("pt", "BR"));
    private static final DateTimeFormatter FORMATADOR_APENAS_ANO = DateTimeFormatter.ofPattern("yyyy");

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
                if (row == null) continue;

                //Pegar os Valores das celulas
                String numeroDePosicionamento = getTextoSimples(row.getCell(0));
                String tipo = getTextoSimples(row.getCell(1));
                String capacidade = getTextoSimples(row.getCell(2));
                String numeroDeIdentificacao = getTextoSimples(row.getCell(3));
                String regiao = getTextoSimples(row.getCell(6));
                String endereco = getTextoSimples(row.getCell(7));

                // Leitura de datas e anos usando o método getDataFormatada com o formatador apropriado
                String dataRecargaCompleta = getDataFormatada(row.getCell(4), FORMATADOR_MES_ANO);
                String proximaRecargaCompleta = getDataFormatada(row.getCell(8), FORMATADOR_MES_ANO);
                
                // --- PONTO DA CORREÇÃO: Usando o formatador de ano ---
                String anoUltimoTeste = getDataFormatada(row.getCell(5), FORMATADOR_APENAS_ANO);
                String anoProximoTeste = getDataFormatada(row.getCell(9), FORMATADOR_APENAS_ANO);

                // --- Processamento dos dados lidos ---

                // Separando mês/ano para Data de Recarga
                String mesRecarga = "";
                String anoRecarga = "";
                if (!dataRecargaCompleta.isEmpty()) {
                    String[] partes = dataRecargaCompleta.split("-");
                    if (partes.length == 2) {
                        mesRecarga = partes[0];
                        anoRecarga = partes[1];
                    }
                }

                // Separando mês/ano para Próxima Recarga
                String mesProximaRecarga = "";
                String anoProximaRecarga = "";
                if (!proximaRecargaCompleta.isEmpty()) {
                    String[] partes = proximaRecargaCompleta.split("-");
                    if (partes.length == 2) {
                        mesProximaRecarga = partes[0];
                        anoProximaRecarga = partes[1];
                    }
                }
                
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
    private static String getTextoSimples(Cell cell) {
        if (cell == null) return "";
        return new DataFormatter().formatCellValue(cell).trim();
    }

    private static String getDataFormatada(Cell cell, DateTimeFormatter formatter) {
        if (cell == null) return "";
        DataFormatter df = new DataFormatter(new Locale("pt", "BR"));
        
        // Apenas células NUMÉRICAS podem ser datas. Isso evita erros com células de texto.
        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
        Date dataValor = cell.getDateCellValue();
        if (dataValor != null) {
            LocalDate data = dataValor.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return data.format(formatter);
        }
    }

    // Caso 2: É texto (tentar interpretar)
    String texto = df.formatCellValue(cell).trim();
    if (!texto.isEmpty()) {
        try {
            // tenta converter se for só ano
            if (formatter == FORMATADOR_APENAS_ANO && texto.matches("\\d{4}")) {
                return texto;
            }
            // tenta converter se for mês-ano
            if (formatter == FORMATADOR_MES_ANO) {
                LocalDate data = LocalDate.parse("01-" + texto, DateTimeFormatter.ofPattern("dd-MMM-yyyy", new Locale("pt", "BR")));
                return data.format(formatter);
            }
        } catch (Exception e) {
            // Se não conseguir converter, retorna o texto original
            return texto;
        }
    }

    return "";
}
}