package org.example;

public class GeradorZPL {
    private static String modelo_ZPL;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("~~CD,~CC^~CT~");
        sb.append("^XA~TA000~JSN^LT0^MNW^MTD^PON^PMN^LH0,0^JMA^PR4,4~SD15^JUS^LRN^CI0^XZ");
        sb.append("^XA");
        sb.append("^MMT");
        sb.append("^PW831");
        sb.append("^LL1279");
        sb.append("^LS0");
        sb.append("^FO384,416^GFA,00512,00512,00008,:Z64:");
        sb.append("eJxjYBjBoACN/gChGB9A6QMQmhlGN0BodijND9XGB6XloLQNlLaA0hWDyhoYDQEAihAQIA==:2B9F");
        sb.append("^FO256,448^GFA,02048,02048,00064,:Z64:");
        sb.append("eJxjYBgFo2AUjIJRMAroD+r/UwJ+DLj+oQ4Apse6rw==:F18F");
        sb.append("^FO576,416^GFA,00256,00256,00004,:Z64:");
        sb.append("eJxjYBhIwAfFckBsA8QWQFwBxAVA/IGBgfEBEB9gYGAGYvYGIAYK81NHiwXUBQA/Jwvb:7960");
        sb.append("^FO384,384^GFA,00512,00512,00008,:Z64:");
        sb.append("eJxjYBgCwAKNroDSH6D0AwjFCKMPQGhmKM3eAKWhyvmhtByUloHSNoPKGjhNUwAAjdMPiQ==:4C9B");
        sb.append("^FO288,416^GFA,01920,01920,00060,:Z64:");
        sb.append("eJxjYBgFo2AUjAL6Acb/5II/A6Z3FIwCWgAAkbStTQ==:137D");
        sb.append("^FO544,384^GFA,00512,00512,00008,:Z64:");
        sb.append("eJxjYBjMwAKNroDSH6D0AwjFCKMPQGhmKM3eAKWhyvmhtByUloHSNoPKGjhNDwAA+osPiQ==:F1B5");
        sb.append("^FO384,352^GFA,00512,00512,00008,:Z64:");
        sb.append("eJxjYKAjsECjK6D0Byj9AEIxwugDEJoZSrM3QGmocn4oLQelZaC0zaCyBk4PSgAA88APiQ==:ADA5");
        sb.append("^FO288,384^GFA,01920,01920,00060,:Z64:");
        sb.append("eJxjYBgFowA7qP9PLvgxYHpHwSgYBaNgqAAANf6uuw==:0F31");
        sb.append("^FO576,352^GFA,00256,00256,00004,:Z64:");
        sb.append("eJxjYCAV8EGxHBDbALEFEFcAcQEQf2BgYHwAxAcYGJiBmL0BiIHC/NTRYkGyWwkDAMQMC9s=:4C22");
        sb.append("^FO0,0^GFA,04608,04608,00024,:Z64:");
        sb.append("eJztkj9r20AYxt87bJ/RYJSh0EFEGg8PImO2eGiqpYMDDV1MkqXQ8QINdFDR4aGYDPkMIpPJJwiZzkGO1hra/fDkSVSU0qWg3umP20bQsXS4H/a9+NHj5967VwAGg8FgMPxTukWLkdattpXrxWnrTC/9tu7pxYbjsdpl9AS9VL/QGBDsaZ3CpXqOOYVr5cQzwErSYSgYwwiJEKUjOCMdIFU+hQGAxILClIPsAfQqvw2OjQRaMJQCEsrvKqn093XH3IOpqlgfqPTvqRN0RXfByFJVIiCo+vEq//oMz/U+XG3X5MOOQAkjZf2o+qnz1WPej2Ns6f/FTT6DEC4E5JIQVUcMnVT9e6UfpOypcwiP4m0+Q0jYK0Fc1b/NOtv+vbJ/bul8z+tbsF/1z4jyHYrdSReJt8zebe4zxjj2MLd0HXKv7ufpHyNB23nR9rxKKXr2mMMvWvdvWmy07rbfn9I/bPvnWj8oim/JabT8EQVpkIRRUPv9LJNy4POND/fAqY+zrMlffJq4TugcpTurd+HRr3w+n1tUfaY0ntNhnR8VRRKGp1F+EgYkzyevis9N/uzN5sqXA7oGuR7gjDf5SXgeHOROGKzy3F0Viyr/7obTGO/HOv827sd309u6/47yO6+fXzy8z5cvzouU1fkq+RrWH+AB5PQKZzNZ57vO96+LNEEpytOUFKsy3/rtYi6rb/y3+1T38RheTvS4RXuyBoPBYDAY/j9+ArLNNok=:648D");
        sb.append("^FO17,174^GB798,1097,8^FS");
        sb.append("^FT262,64^A0N,25,31^FB276,1,0,C^FH\\\\^FDTecidos e Armarinhos^FS");
        sb.append("^FT262,95^A0N,25,31^FB276,1,0,C^FH\\\\^FD Miguel Bartolomeu^FS");
        sb.append("^FT221,150^A0N,28,28^FH\\\\^FDControle de Vistoria de Extintor^FS");
        sb.append("^FT30,233^A0N,28,28^FH\\\\^FDN\\\\A7 DE IDENTIFI:^FS");
        sb.append("^FO228,234^GB153,0,3^FS");
        sb.append("^FT123,559^A0N,21,21^FH\\\\^FDINSPE\\\\80AO MENSAL DE EQUIPAMENTO DE COMBATE A INCENDIO^FS");
        sb.append("^FO39,637^GB757,581,4^FS");
        sb.append("^FT76,662^A0N,20,19^FH\\\\^FDDIA^FS");
        sb.append("^FT188,662^A0N,20,19^FH\\\\^FDMES^FS");
        sb.append("^FT301,662^A0N,20,19^FH\\\\^FDANO^FS");
        sb.append("^FT414,662^A0N,20,19^FH\\\\^FDOBSERVA\\\\80AO^FS");
        sb.append("^FT652,662^A0N,20,19^FH\\\\^FDVISTO^FS");
        sb.append("^FT649,159^BQN,2,6");
        sb.append("^FH\\\\^FDLA,80087^FS");
        sb.append("^FT225,233^A0N,28,28^FH\\\\^FD{NUMERO_IDENTIFICACAO}\\\\87\\\\C6o^FS");
        sb.append("^FO39,714^GB754,0,3^FS");
        sb.append("^FT49,359^A0N,28,28^FH\\\\^FDTIPO/CAPACIDADE:^FS");
        sb.append("^FO283,354^GB475,0,3^FS");
        sb.append("^FT49,397^A0N,28,28^FH\\\\^FDDATA DA RECARGA:^FS");
        sb.append("^FT49,282^A0N,28,28^FH\\\\^FDLOCAL:^FS");
        sb.append("^FO143,277^GB617,0,3^FS");
        sb.append("^FT49,438^A0N,28,28^FH\\\\^FDPROXIMA RECARGA:^FS");
        sb.append("^FT49,478^A0N,28,28^FH\\\\^FDPROXIMA TESTE:^FS");
        sb.append("^FO143,315^GB614,0,3^FS");
        sb.append("^FO39,675^GB754,0,3^FS");
        sb.append("^FO41,752^GB754,0,3^FS");
        sb.append("^FO39,791^GB754,0,3^FS");
        sb.append("^FO39,830^GB754,0,3^FS");
        sb.append("^FO41,907^GB754,0,3^FS");
        sb.append("^FO41,868^GB754,0,3^FS");
        sb.append("^FO39,984^GB754,0,3^FS");
        sb.append("^FO39,945^GB754,0,3^FS");
        sb.append("^FO41,1023^GB754,0,3^FS");
        sb.append("^FO39,1061^GB754,0,3^FS");
        sb.append("^FO39,1138^GB754,0,3^FS");
        sb.append("^FO39,1100^GB754,0,3^FS");
        sb.append("^FO39,1177^GB754,0,3^FS");
        sb.append("^FO41,1216^GB754,0,3^FS");
        sb.append("^FO151,640^GB0,577,3^FS");
        sb.append("^FO262,640^GB0,577,3^FS");
        sb.append("^FO569,639^GB0,577,3^FS");
        sb.append("^FO373,640^GB0,577,3^FS");
        sb.append("^FT283,353^A0N,28,28^FH\\\\^FD{TIPO_CAPACIDADE}^FS");
        sb.append("^FT146,275^A0N,28,72^FH\\\\^FD{LOCAL}^FS");
        sb.append("^FT146,309^A0N,28,72^FH\\\\^FDlocal^FS");
        sb.append("^FT329,389^A0N,28,28^FH\\\\^FD{RECARGA_DIA}^FS");
        sb.append("^FT469,392^A0N,28,28^FH\\\\^FD{RECARGA_MES}^FS");
        sb.append("^FT642,389^A0N,28,28^FH\\\\^FD{RECARGA_ANO}^FS");
        sb.append("^FT329,432^A0N,28,28^FH\\\\^FD{PROX_RECARGA_DIA}^FS");
        sb.append("^FT469,431^A0N,28,28^FH\\\\^FD{PROX_RECARGA_MES}^FS");
        sb.append("^FT642,432^A0N,28,28^FH\\\\^FD{PROX_RECARGA_ANO}^FS");
        sb.append("^FT307,472^A0N,28,28^FH\\\\^FD{PROX_TESTE_DIA}^FS");
        sb.append("^FT469,472^A0N,28,28^FH\\\\^FD{PROX_TESTE_MES}^FS");
        sb.append("^FT642,472^A0N,28,28^FH\\\\^FD{PROX_TESTE_ANO}^FS");
        sb.append("^FT392,233^A0N,28,28^FH\\\\^FDN\\\\A7 DE LOCALIDADE:^FS");
        sb.append("^FO637,234^GB153,0,3^FS");
        sb.append("^FT634,233^A0N,28,28^FH\\\\^FD{NUMERO_POSICIONAMENTO}^FS");
        sb.append("^PQ1,0,1,Y^XZ");

}

    public static String gerarEtiquetas(Extintor extintor) {
        String zpl = modelo_ZPL;

        zpl = zpl.replace("{NUMERO_IDENTIFICACAO}", extintor.getNumeroDeIdentificacao());
        zpl = zpl.replace("{NUMERO_POSICIONAMENTO}", extintor.getNumeroDePosicionamento());
        zpl = zpl.replace("{TIPO_CAPACIDADE}", extintor.getTipo() + " / " + extintor.getCapacidade());
        zpl = zpl.replace("{LOCAL}", extintor.getRegiao()+ "\n" + extintor.getEndereco());

        zpl = substituirData(zpl, "{RECARGA}", extintor.getDataDeRecarga());
        zpl = substituirData(zpl, "{PROX_RECARGA}", extintor.getProximaRecarga());
        zpl = substituirData(zpl, "{PROX_TESTE}", extintor.getProximoTeste());

        return zpl;
    }

    private static String substituirData(String zpl, String tipo, String data) {
        if (data == null || data.trim().isEmpty()) {
            zpl = zpl.replace("{" + tipo + "_DIA}", "");
            zpl = zpl.replace("{" + tipo + "_MES}", "");
            zpl = zpl.replace("{" + tipo + "_ANO}", "");
            return zpl;
        }

        String[] partes = data.split("/");

        if(partes.length == 3) {
            zpl = zpl.replace("{" + tipo + "_DIA}", partes[0]);
            zpl = zpl.replace("{" + tipo + "_MES}", partes[1]);
            zpl = zpl.replace("{" + tipo + "_ANO}", partes[2]);
        }

        return zpl;
    }
}

