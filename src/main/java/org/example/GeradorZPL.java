package org.example;

public class GeradorZPL {
    private static String modelo_ZPL;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("CT~CD,~CC^~CT");
        sb.append("^XA~TA000~JSN^LT0^MNW^MTD^PON^PMN^LH0,0^JMA^PR4,4~SD15^JUS^LRN^CI0^XZ");
        sb.append("^XA");
        sb.append("^MMT");
        sb.append("^PW831");
        sb.append("^LL1279");
        sb.append("^LS0");
        sb.append("^FO384,480^GFA,00512,00512,00008,:Z64:");
        sb.append("eJxjYKACKECjP0AoxgdQ+gCEZobRDRCaHUrzQ7XxQWk5KG0DpS2gdMWgsgZGD20AACEMECA=:469B");
        sb.append("^FO256,512^GFA,02048,02048,00064,:Z64:");
        sb.append("eJxjYBjaoP4/JeDHgOsfBaNgFIyCUTAKBgIAAE41uq8=:0E63");
        sb.append("^FO576,480^GFA,00256,00256,00004,:Z64:");
        sb.append("eJxjYCAE+KBYDohtgNgCiCuAuACIPzAwMD4A4gMMDMxAzN4AxEBhfuposSDoNsoBAIG7C9s=:FF86");
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
        sb.append("^FO384,416^GFA,00512,00512,00008,:Z64:");
        sb.append("eJxjYBjBgA+NloPSNlDaAkpXQOkCKP0BQjE+gNIHIDQzlGZvgNJQ5fyDyxoYDQYAawYL2w==:F8D2");
        sb.append("^FO224,448^GFA,02176,02176,00068,:Z64:");
        sb.append("eJxjYBgFo2AUjIJRMAqGM7D/Txn4M4jMGD4AAECzv/U=:94D9");
        sb.append("^FO576,416^GFA,00256,00256,00004,:Z64:");
        sb.append("eJxjYBhIwHgAgpmBmL0BiIFi/EDMB8RyQGwDxBZAXAHEH4D4AVD5A6poYYe6AADpWw6v:8CF5");
        sb.append("^FO0,0^GFA,04608,04608,00024,:Z64:");
        sb.append("eJztk7+L02AYx583P0DuJCaQQocMRffSMajUN3DdHFJItgqCm7uzwUMsJ7xdnZ2OO6hrOeTuFSwcOtg/wMALWaSFq+AS6pH4NmnaXIKrILyfQHn49ptPHvK2AAKBQCAQ/J9Id6vkuepXyfP7x8Ob1wnLcrdmxtln60bW2iYYrYU6AqO4W89yJk0CCR7uBzaDpkxAAjvvKzooYKnQxOu7+Jz3n/LvNWjvA+/b8is+dzZP18EEswE6BrfUZxBDG0g7mDCYyJTPGz/f4QAeK9Dn2yBa6jMgoGnwHKQOn3d+zDuKAs56e7fcl8ZACDC6F3P3eOuncBtUCxzK21Zp/7VTa0LEWh2wpa0fcT9STOTwzXR955fpLQ2GNvoa0Nie8Nku+rqJwEMeYNfEurI5EVY7l85fzitPVsMKR3GWP6n9HmZZ/jKt8jPLa3U/9/MCCaJ4NKIxY+TiME03/jBUwZmpr6lBsWWhMJwV/TcR+/XhkNikm3QflPxqD/d91DOQ55mNnT+5t7yM0tNv7+h1TN6mi8L/HU0/O6ExNejAVVEIxf6JHAXPruSRfdaNtR/p+8LvoR70D7hfMV3F8KHwX8fLsU3iL5Ojj/Or4/Rk63enlm4NcJ8OnCkKP+X+R+lqtbw4P0/mv+fJYnGaLvITabzwKxfO8ju19xlkee3/6NUOUCAQCAQCwT/gD+fRCoA=:C48E");
        sb.append("^FO17,174^GB798,1097,8^FS");
        sb.append("^FT262,64^A0N,25,31^FB276,1,0,C^FH\\^FDTecidos e Armarinhos^FS");
        sb.append("^FT262,95^A0N,25,31^FB276,1,0,C^FH\\^FD Miguel Bartolomeu^FS");
        sb.append("^FT221,150^A0N,28,28^FH\\^FDControle de Vistoria de Extintor^FS");
        sb.append("^FT30,233^A0N,28,28^FH\\^FDN\\A7 DE IDENTIFI:^FS");
        sb.append("^FO228,234^GB153,0,3^FS");
        sb.append("^FT104,591^A0N,21,24^FH\\^FDINSPE\\80AO MENSAL DE EQUIPAMENTO DE COMBATE A INCENDIO^FS");
        sb.append("^FO39,637^GB757,581,4^FS");
        sb.append("^FT76,662^A0N,20,19^FH\\^FDDIA^FS");
        sb.append("^FT188,662^A0N,20,19^FH\\^FDMES^FS");
        sb.append("^FT301,662^A0N,20,19^FH\\^FDANO^FS");
        sb.append("^FT414,662^A0N,20,19^FH\\^FDOBSERVA\\80AO^FS");
        sb.append("^FT652,662^A0N,20,19^FH\\^FDVISTO^FS");
        sb.append("^FT649,159^BQN,2,6");
        sb.append("^FH\\^FDLA,{QRCODE}^FS");
        sb.append("^FT262,233^A0N,28,28^FH\\^FD{NUM_IDENTIFICACAO}^FS");
        sb.append("^FO39,714^GB754,0,3^FS");
        sb.append("^FT49,359^A0N,28,28^FH\\^FDTIPO/CAPACIDADE:^FS");
        sb.append("^FO283,354^GB475,0,3^FS");
        sb.append("^FT49,397^A0N,28,28^FH\\^FDDATA DA RECARGA:^FS");
        sb.append("^FT49,279^A0N,28,28^FH\\^FDLOCAL:^FS");
        sb.append("^FO143,274^GB614,0,3^FS");
        sb.append("^FT49,438^A0N,28,28^FH\\^FDPROXIMA RECARGA:^FS");
        sb.append("^FT49,519^A0N,28,28^FH\\^FDPROXIMA TESTE:^FS");
        sb.append("^FO49,315^GB709,0,3^FS");
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
        sb.append("^FT292,350^A0N,28,28^FH\\^FD{TIPO} / {CAPACIDADE}^FS");
        sb.append("^FT329,389^A0N,28,28^FH\\^FD     ^FS");
        sb.append("^FT469,392^A0N,28,28^FH\\^FD{DATA_RECARGA_MES}^FS");
        sb.append("^FT642,389^A0N,28,28^FH\\^FD{DATA_RECARGA_ANO}^FS");
        sb.append("^FT329,432^A0N,28,28^FH\\^FD ^FS");
        sb.append("^FT469,431^A0N,28,28^FH\\^FD{PROXIMA_RECARGA_MES}^FS");
        sb.append("^FT642,432^A0N,28,28^FH\\^FD{PROXIMA_RECARGA_ANO}^FS");
        sb.append("^FT307,512^A0N,28,28^FH\\^FD ^FS");
        sb.append("^FT469,512^A0N,28,28^FH\\^FD ^FS");
        sb.append("^FT642,512^A0N,28,28^FH\\^FD{PROXIMO_TESTE_ANO}^FS");
        sb.append("^FT392,233^A0N,28,28^FH\\^FDN\\A7 DE LOCALIDADE:^FS");
        sb.append("^FO637,234^GB153,0,3^FS");
        sb.append("^FT686,233^A0N,28,28^FH\\^FD{NUM_POSICIONAMENTO}^FS");
        sb.append("^FT143,274^A0N,28,28^FH\\^FD{REGIAO}^FS");
        sb.append("^FT49,316^A0N,28,28^FH\\^FD{ENDERECO}^FS");
        sb.append("^FT49,477^A0N,28,28^FH\\^FD\\E9LTIMO TESTE:^FS");
        sb.append("^FT292,472^A0N,28,28^FH\\^FD ^FS");
        sb.append("^FT469,472^A0N,28,28^FH\\^FD ^FS");
        sb.append("^FT642,472^A0N,28,28^FH\\^FD{ULTIMO_TESTE_ANO}^FS");
        sb.append("^PQ1,0,1,Y^XZ");

}

    public static String gerarEtiqueta(Extintor extintor) { // Renomeado para singular
        String zpl = modelo_ZPL; // Começa com o modelo original

        // Substituições diretas
        zpl = zpl.replace("{NUM_IDENTIFICACAO}", extintor.getNumeroDeIdentificacao());
        zpl = zpl.replace("{NUM_POSICIONAMENTO}", extintor.getNumeroDePosicionamento());
        zpl = zpl.replace("{TIPO}", extintor.getTipo());
        zpl = zpl.replace("{CAPACIDADE}", extintor.getCapacidade());
        zpl = zpl.replace("{REGIAO}", extintor.getRegiao());
        zpl = zpl.replace("{ENDERECO}", extintor.getEndereco());

        // Substituição de datas
        zpl = zpl.replace("{DATA_RECARGA_MES}", extintor.getMesRecarga());
        zpl = zpl.replace("{DATA_RECARGA_ANO}", extintor.getAnoRecarga());
        zpl = zpl.replace("{ULTIMO_TESTE_ANO}", extintor.getAnoUltimoTeste());
        zpl = zpl.replace("{PROXIMA_RECARGA_MES}", extintor.getMesProximaRecarga());
        zpl = zpl.replace("{PROXIMA_RECARGA_ANO}", extintor.getAnoProximaRecarga());
        zpl = zpl.replace("{PROXIMO_TESTE_ANO}", extintor.getAnoProximoTeste());

        // QR Code
        zpl = zpl.replace("{QRCODE}", extintor.getNumeroDeIdentificacao());

        return zpl;
    }

    private static String substituirData(String zpl, String tipo, String data) {
        if (data == null || data.trim().isEmpty()) {
            zpl = zpl.replace("{" + tipo + "_MES}", "");
            zpl = zpl.replace("{" + tipo + "_ANO}", "");
            return zpl;
        }

        String[] partes = data.split("/");

        if(partes.length == 3) {
            zpl = zpl.replace("{" + tipo + "_MES}", partes[1]);
            zpl = zpl.replace("{" + tipo + "_ANO}", partes[2]);
        }

        return zpl;
    }
}

