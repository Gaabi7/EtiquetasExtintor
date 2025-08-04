package org.example;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class CelulaVazia {

    public static String lerCelulaComoString(Cell cell) {
        if (cell == null) {
            return ""; // célula nula, retorna string vazia
        }

        // Dependendo do tipo da célula, converte para string
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            // Se for número, converte para string (sem casas decimais, por exemplo)
            double valor = cell.getNumericCellValue();
            if (valor == (long) valor) {
                return String.valueOf((long) valor);
            } else {
                return String.valueOf(valor);
            }
        } else if (cell.getCellType() == CellType.BLANK) {
            return "";
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == CellType.FORMULA) {
            // Avalia a fórmula se precisar
            try {
                return cell.getStringCellValue().trim();
            } catch (Exception e) {
                return "";
            }
        } else {
            return "";
        }
    }
}