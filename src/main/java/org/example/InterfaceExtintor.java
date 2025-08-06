package org.example;

import java.util.Optional;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

public class InterfaceExtintor extends JFrame {

    private List<Extintor> extintores;
    private JTextField campoLocalidade;
    private JTextArea areaEtiqueta;
    private JButton botaoVisualizar;
    private JButton botaoImprimir;

    public InterfaceExtintor() {
        super("Impressão de Etiqueta");

        // Layout principal
        setLayout(new BorderLayout());

        //carrega os entintores da planilha
        InputStream arquivo = App.class.getResourceAsStream("/extintores.xlsx");
        extintores = LeitorExcel.lerExtintoresDoExcel(arquivo);

        // Painel superior com campo e botões
        JPanel painelSuperior = new JPanel(new FlowLayout());
        campoLocalidade = new JTextField(10);
        JButton botaoBuscar = new JButton("Visualizar");
        painelSuperior.add(new JLabel("Numero da Localidade:"));
        painelSuperior.add(campoLocalidade);
        painelSuperior.add(botaoBuscar);

        //area da etiqueta
        areaEtiqueta = new JTextArea(15, 40);
        areaEtiqueta.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(areaEtiqueta);

        add(painelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        //açoes do botao
        botaoBuscar.addActionListener((e -> buscarPorLocalidade()));

        //Janela
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void buscarPorLocalidade() {
        String localidade = campoLocalidade.getText().trim();

        Optional<Extintor> extOpt = extintores.stream()
                .filter(ext -> ext.getNumeroDePosicionamento().equalsIgnoreCase(localidade))
                .findFirst();

        if(extOpt.isPresent()) {
            Extintor ext = extOpt.get();

            String zpl = String.format("""
                    CT~CD,~CC^~CT
                    ^XA~TA000~JSN^LT0^MNW^MTD^PON^PMN^LH0,0^JMA^PR4,4~SD15^JUS^LRN^CI0^XZ
                    ^XA
                    ^MMT
                    ^PW831
                    ^LL1279
                    ^LS0
                    ^FO384,480^GFA,00512,00512,00008,:Z64:
                    eJxjYKACKECjP0AoxgdQ+gCEZobRDRCaHUrzQ7XxQWk5KG0DpS2gdMWgsgZGD20AACEMECA=:469B
                    ^FO256,512^GFA,02048,02048,00064,:Z64:
                    eJxjYBjaoP4/JeDHgOsfBaNgFIyCUTAKBgIAAE41uq8=:0E63
                    ^FO576,480^GFA,00256,00256,00004,:Z64:
                    eJxjYCAE+KBYDohtgNgCiCuAuACIPzAwMD4A4gMMDMxAzN4AxEBhfuposSDoNsoBAIG7C9s=:FF86
                    ^FO384,384^GFA,00512,00512,00008,:Z64:
                    eJxjYBgCwAKNroDSH6D0AwjFCKMPQGhmKM3eAKWhyvmhtByUloHSNoPKGjhNUwAAjdMPiQ==:4C9B
                    ^FO288,416^GFA,01920,01920,00060,:Z64:
                    eJxjYBgFo2AUjAL6Acb/5II/A6Z3FIwCWgAAkbStTQ==:137D
                    ^FO544,384^GFA,00512,00512,00008,:Z64:
                    eJxjYBjMwAKNroDSH6D0AwjFCKMPQGhmKM3eAKWhyvmhtByUloHSNoPKGjhNDwAA+osPiQ==:F1B5
                    ^FO384,352^GFA,00512,00512,00008,:Z64:
                    eJxjYKAjsECjK6D0Byj9AEIxwugDEJoZSrM3QGmocn4oLQelZaC0zaCyBk4PSgAA88APiQ==:ADA5
                    ^FO288,384^GFA,01920,01920,00060,:Z64:
                    eJxjYBgFowA7qP9PLvgxYHpHwSgYBaNgqAAANf6uuw==:0F31
                    ^FO576,352^GFA,00256,00256,00004,:Z64:
                    eJxjYCAV8EGxHBDbALEFEFcAcQEQf2BgYHwAxAcYGJiBmL0BiIHC/NTRYkGyWwkDAMQMC9s=:4C22
                    ^FO384,416^GFA,00512,00512,00008,:Z64:
                    eJxjYBjBgA+NloPSNlDaAkpXQOkCKP0BQjE+gNIHIDQzlGZvgNJQ5fyDyxoYDQYAawYL2w==:F8D2
                    ^FO224,448^GFA,02176,02176,00068,:Z64:
                    eJxjYBgFo2AUjIJRMAqGM7D/Txn4M4jMGD4AAECzv/U=:94D9
                    ^FO576,416^GFA,00256,00256,00004,:Z64:
                    eJxjYBhIwHgAgpmBmL0BiIFi/EDMB8RyQGwDxBZAXAHEH4D4AVD5A6poYYe6AADpWw6v:8CF5
                    ^FO0,0^GFA,04608,04608,00024,:Z64:
                    eJztk7+L02AYx583P0DuJCaQQocMRffSMajUN3DdHFJItgqCm7uzwUMsJ7xdnZ2OO6hrOeTuFSwcOtg/wMALWaSFq+AS6pH4NmnaXIKrILyfQHn49ptPHvK2AAKBQCAQ/J9Id6vkuepXyfP7x8Ob1wnLcrdmxtln60bW2iYYrYU6AqO4W89yJk0CCR7uBzaDpkxAAjvvKzooYKnQxOu7+Jz3n/LvNWjvA+/b8is+dzZP18EEswE6BrfUZxBDG0g7mDCYyJTPGz/f4QAeK9Dn2yBa6jMgoGnwHKQOn3d+zDuKAs56e7fcl8ZACDC6F3P3eOuncBtUCxzK21Zp/7VTa0LEWh2wpa0fcT9STOTwzXR955fpLQ2GNvoa0Nie8Nku+rqJwEMeYNfEurI5EVY7l85fzitPVsMKR3GWP6n9HmZZ/jKt8jPLa3U/9/MCCaJ4NKIxY+TiME03/jBUwZmpr6lBsWWhMJwV/TcR+/XhkNikm3QflPxqD/d91DOQ55mNnT+5t7yM0tNv7+h1TN6mi8L/HU0/O6ExNejAVVEIxf6JHAXPruSRfdaNtR/p+8LvoR70D7hfMV3F8KHwX8fLsU3iL5Ojj/Or4/Rk63enlm4NcJ8OnCkKP+X+R+lqtbw4P0/mv+fJYnGaLvITabzwKxfO8ju19xlkee3/6NUOUCAQCAQCwT/gD+fRCoA=:C48E
                    ^FO17,174^GB798,1097,8^FS
                    ^FT262,64^A0N,25,31^FB276,1,0,C^FH\^FDTecidos e Armarinhos^FS
                    ^FT262,95^A0N,25,31^FB276,1,0,C^FH\^FD Miguel Bartolomeu^FS
                    ^FT221,150^A0N,28,28^FH\^FDControle de Vistoria de Extintor^FS
                    ^FT30,233^A0N,28,28^FH\^FDN\A7 DE IDENTIFI:^FS
                    ^FO228,234^GB153,0,3^FS
                    ^FT104,591^A0N,21,24^FH\^FDINSPE\80AO MENSAL DE EQUIPAMENTO DE COMBATE A INCENDIO^FS
                    ^FO39,637^GB757,581,4^FS
                    ^FT76,662^A0N,20,19^FH\^FDDIA^FS
                    ^FT188,662^A0N,20,19^FH\^FDMES^FS
                    ^FT301,662^A0N,20,19^FH\^FDANO^FS
                    ^FT414,662^A0N,20,19^FH\^FDOBSERVA\80AO^FS
                    ^FT652,662^A0N,20,19^FH\^FDVISTO^FS
                    ^FT649,159^BQN,2,6
                    ^FH\^FDLA,80087^FS
                    ^FT262,233^A0N,28,28^FH\^FD%s^FS
                    ^FO39,714^GB754,0,3^FS
                    ^FT49,359^A0N,28,28^FH\^FDTIPO / CAPACIDADE:^FS
                    ^FO283,354^GB475,0,3^FS
                    ^FT49,397^A0N,28,28^FH\^FDDATA DA RECARGA:^FS
                    ^FT49,279^A0N,28,28^FH\^FDLOCAL:^FS
                    ^FO143,274^GB614,0,3^FS
                    ^FT49,438^A0N,28,28^FH\^FDPROXIMA RECARGA:^FS
                    ^FT49,519^A0N,28,28^FH\^FDPROXIMA TESTE:^FS
                    ^FO49,315^GB709,0,3^FS
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
                    ^FT292,350^A0N,28,28^FH\^FD%s / %s^FS
                    ^FT329,389^A0N,28,28^FH\^FD ^FS
                    ^FT469,392^A0N,28,28^FH\^FD%s^FS
                    ^FT642,389^A0N,28,28^FH\^FD%s^FS
                    ^FT329,432^A0N,28,28^FH\^FD ^FS
                    ^FT469,431^A0N,28,28^FH\^FD%s^FS
                    ^FT642,432^A0N,28,28^FH\^FD%s^FS
                    ^FT307,512^A0N,28,28^FH\^FD ^FS
                    ^FT469,512^A0N,28,28^FH\^FD ^FS
                    ^FT642,512^A0N,28,28^FH\^FD%s^FS
                    ^FT392,233^A0N,28,28^FH\^FDN\A7 DE LOCALIDADE:^FS
                    ^FO637,234^GB153,0,3^FS
                    ^FT686,233^A0N,28,28^FH\^FD%s^FS
                    ^FT143,274^A0N,28,28^FH\^FD%s^FS
                    ^FT49,316^A0N,28,28^FH\^FD%s^FS
                    ^FT49,477^A0N,28,28^FH\^FD\E9LTIMO TESTE:^FS
                    ^FT292,472^A0N,28,28^FH\^FD ^FS
                    ^FT469,472^A0N,28,28^FH\^FD ^FS
                    ^FT642,472^A0N,28,28^FH\^FD%s^FS
                    ^PQ1,0,1,Y^XZ
                    """);

                    ext.getNumeroDeIdentificacao(),
                    ext.getTipo(), ext.getCapacidade(),
                    ext.getMesRecarga(),
                    ext.getAnoRecarga(),
                    ext.getMesProximaRecarga(),
                    ext.getAnoProximaRecarga(),
                    ext.getAnoProximoTeste(),
                    ext.getNumeroDePosicionamento(),
                    ext.getRegiao(),
                    ext.getEndereco(),
                    ext.getAnoUltimoTeste()


            areaEtiqueta.setText(zpl);

        } else {
            areaEtiqueta.setText("Extintor não encontrado");
        }
    }
}