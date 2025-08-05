package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

public class InterfaceExtintor extends JFrame {

    private JTextField campoLocalidade;
    private JTextArea areaEtiqueta;
    private JButton botaoVisualizar;
    private JButton botaoImprimir;

    public InterfaceExtintor() {
        super("Impressão de Etiqueta");

        // Layout principal
        setLayout(new BorderLayout());

        //carrega os entintores da planilha
        InputStream arquivo = App.class.getResourceAsStream("/arquivo.xlsx");
        extintores = LeitorExcel.lerExtintoresDoExcel(arquivo);

        // Painel superior com campo e botões
        JPanel painelSuperior = new JPanel(new FlowLayout());
        campoLocalidade = new JTextField(10);
        JButton botaoBuscar = new JButton("Visualizar");
        painelSuperior.add(new JLabel("Localidade:"));
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

    private void BuscarPorLocalidade() {
        String localidade = campoLocalidade.getText().trim();

        Optional<Extintor> extOpt = extintores.stream()
                .filter(ext -> ext.getNumeroDePosicionamento().equalsIgnoreCase(localidade))
                .findFirst();

        if(extOpt.isPresent()) {
            extintores ext = extOpt.get();

            String zpl = String.format(""" ss   """,);





            areaEtiqueta.setText(zpl);

        } else {
            areaEtiqueta.setText("Extintor não encontrado");
        }
    }
}