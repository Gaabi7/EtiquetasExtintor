package org.example;

public class GeradorZPL {

    public static String gerarZplExtintores(Extintor extintor) {
        String modeloZpl = """
                CT~CD,~CC^~CT
                ^XA~TA000~JSN^LT0^MNW^MTD^PON^PMN^LH0,0^JMA^PR4,4~SD15^JUS^LRN^CI0^XZ
                ^XA
                ^MMT
                ^PW831
                ^LL1279
                ^LS0
                ^FO384,384^GFA,00512,00512,00008,:Z64:
                eJxjYCAMZNBoGyhdAaULoPQHKP0AQjFCaeYDULoBQrNDaX6ocj4oLTe4rIHRwxsAADTYDb8=:C594
                ^FO224,384^GFA,04352,04352,00068,:Z64:
                eJztzDENADAIADCc4N8kmwbCQ0IroBEAAC35JmrVAQAc8QFDvLmh:F305
                ^FO544,384^GFA,00512,00512,00008,:Z64:
                eJxjYCACsKPR/FBaDkrLQGkbKG0BpSug9Aco/QBCMUJp5gNQugFqPIweHNbA6GENACRDC+g=:F6B2
                ^FO384,320^GFA,00512,00512,00008,:Z64:
                eJxjYBg+gA+NloPSNlDaAkpXQOkCKP0BQjE+gNIHIDQzlGZvgNJQ5fyDyxoYTR4AAK/eC9s=:EF41
                ^FO288,352^GFA,01920,01920,00060,:Z64:
                eJxjYBgFo2AUjIJRMBhB/X9ywcDpHQXEAwCvwavT:3849
                ^FO544,320^GFA,00512,00512,00008,:Z64:
                eJxjYBgGgA+NloPSNlDaAkpXQOkCKP0BQjE+gNIHIDQzlGZvgNJQ5fyDyxoYTREAAALjC9s=:676C
                ^FO384,288^GFA,00512,00512,00008,:Z64:
                eJxjYBh8gA+NloPSNlDaAkpXQOkCKP0BQjE+gNIHIDQzlGZvgNJQ5fyDyxoYTV8AAEe7C9s=:1717
                ^FO256,320^GFA,02048,02048,00064,:Z64:
                eJxjYBgFo2AUDCCQ/08u+Dco9I+CUTAKhigAAEurra0=:C63D
                ^FO544,288^GFA,00512,00512,00008,:Z64:
                eJxjYBhEgB2N5ofSclBaBkrbQGkLKF0BpT9A6QcQihFKMx+A0g1Q42H04LAGRg8IAABOIAvo:BA89
                ^FO0,0^GFA,04608,04608,00024,:Z64:
                eJztkj9r20AYxt87bJ/RYJSh0EFEGg8PImO2eGiqpYMDDV1MkqXQ8QINdFDR4aGYDPkMIpPJJwiZzkGO1hra/fDkSVSU0qWg3umP20bQsXS4H/a9+NHj5967VwAGg8FgMPxTukWLkdattpXrxWnrTC/9tu7pxYbjsdpl9AS9VL/QGBDsaZ3CpXqOOYVr5cQzwErSYSgYwwiJEKUjOCMdIFU+hQGAxILClIPsAfQqvw2OjQRaMJQCEsrvKqn093XH3IOpqlgfqPTvqRN0RXfByFJVIiCo+vEq//oMz/U+XG3X5MOOQAkjZf2o+qnz1WPej2Ns6f/FTT6DEC4E5JIQVUcMnVT9e6UfpOypcwiP4m0+Q0jYK0Fc1b/NOtv+vbJ/bul8z+tbsF/1z4jyHYrdSReJt8zebe4zxjj2MLd0HXKv7ufpHyNB23nR9rxKKXr2mMMvWvdvWmy07rbfn9I/bPvnWj8oim/JabT8EQVpkIRRUPv9LJNy4POND/fAqY+zrMlffJq4TugcpTurd+HRr3w+n1tUfaY0ntNhnR8VRRKGp1F+EgYkzyevis9N/uzN5sqXA7oGuR7gjDf5SXgeHOROGKzy3F0Viyr/7obTGO/HOv827sd309u6/47yO6+fXzy8z5cvzouU1fkq+RrWH+AB5PQKZzNZ57vO96+LNEEpytOUFKsy3/rtYi6rb/y3+1T38RheTvS4RXuyBoPBYDAY/j9+ArLNNok=:648D
                ^FO17,174^GB798,1097,8^FS
                ^FT262,64^A0N,25,31^FB276,1,0,C^FH\\^FDTecidos e Armarinhos^FS
                ^FT262,95^A0N,25,31^FB276,1,0,C^FH\\^FD Miguel Bartolomeu^FS
                ^FT221,150^A0N,28,28^FH\\^FDControle de Vistoria de Extintor^FS
                ^FT42,233^A0N,28,28^FH\\^FDN\\A7 DE IDENTIFICA\\80AO:^FS
                ^FO326,229^GB424,0,3^FS
                ^FT119,566^A0N,21,21^FH\\^FDINSPE\\80AO MENSAL DE EQUIPAMENTO DE COMBATE A INCENDIO^FS
                ^FO39,637^GB757,581,4^FS
                ^FT76,662^A0N,20,19^FH\\^FDDIA^FS
                ^FT188,662^A0N,20,19^FH\\^FDMES^FS
                ^FT301,662^A0N,20,19^FH\\^FDANO^FS
                ^FT414,662^A0N,20,19^FH\\^FDOBSERVA\\80AO^FS
                ^FT652,662^A0N,20,19^FH\\^FDVISTO^FS
                ^FT649,159^BQN,2,6
                ^FH\\^FDLA,80087^FS
                ^FT322,227^A0N,28,28^FH\\^FDTeste do numero de identific 44444^FS
                ^FO39,714^GB754,0,3^FS
                ^FT42,300^A0N,28,28^FH\\^FDTIPO/CAPACIDADE:^FS
                ^FO277,295^GB475,0,3^FS
                ^FT42,338^A0N,28,28^FH\\^FDDATA DA RECARGA:^FS
                ^FT42,462^A0N,28,28^FH\\^FDLOCAL:^FS
                ^FO137,457^GB617,0,3^FS
                ^FT42,378^A0N,28,28^FH\\^FDPROXIMA RECARGA:^FS
                ^FT42,419^A0N,28,28^FH\\^FDPROXIMA TESTE:^FS
                ^FO137,495^GB614,0,3^FS
                ^FO39,675^GB754,0,3^FS
                ^FO41,752^GB754,0,3^FS
                ^FO39,791^GB754,0,3^FS
                ^FO39,830^GB754,0,3^FS
                ^FO41,907^GB754,0,3^FS
                ^FO41,868^GB754,0,3^FS
                ^FO39,984^GB754,0,3^FS
                ^FO39,945^GB754,0,3^FS
                ^FO41,1023^GB754,0,3^FS
                ^FO39,1061^GB754,0,3^FS
                ^FO39,1138^GB754,0,3^FS
                ^FO39,1100^GB754,0,3^FS
                ^FO39,1177^GB754,0,3^FS
                ^FO41,1216^GB754,0,3^FS
                ^FO151,640^GB0,577,3^FS
                ^FO262,640^GB0,577,3^FS
                ^FO569,639^GB0,577,3^FS
                ^FO373,640^GB0,577,3^FS
                ^FT277,294^A0N,28,28^FH\\^FDTeste do tipo e a capacidade 777^FS
                ^FT139,455^A0N,28,28^FH\\^FDTeste do local 44444^FS
                ^FT139,489^A0N,28,28^FH\\^FDlocal^FS
                ^FT323,330^A0N,28,28^FH\\^FDdia^FS
                ^FT463,333^A0N,28,28^FH\\^FDmes^FS
                ^FT635,330^A0N,28,28^FH\\^FDano^FS
                ^FT323,373^A0N,28,28^FH\\^FDdia^FS
                ^FT463,372^A0N,28,28^FH\\^FDmes^FS
                ^FT635,373^A0N,28,28^FH\\^FDano^FS
                ^FT301,412^A0N,28,28^FH\\^FDdia^FS
                ^FT463,412^A0N,28,28^FH\\^FDmes^FS
                ^FT635,412^A0N,28,28^FH\\^FDano^FS
                ^FT42,267^A0N,28,28^FH\\^FDN\\A7 DE LOCALIDADE:^FS
                ^FO285,263^GB466,0,3^FS
                ^FT293,261^A0N,28,28^FH\\^FDTeste do numero de locali 5555^FS
                ^PQ1,0,1,Y^XZ
            """;
    }
}
