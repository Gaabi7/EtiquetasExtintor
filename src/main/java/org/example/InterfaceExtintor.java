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

        try {
            //carrega os entintores da planilha
            InputStream arquivo = App.class.getResourceAsStream("/extintores.xlsx");
            extintores = LeitorExcel.lerExtintoresDoExcel(arquivo);

        } catch (Exception e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Erro ao carregar a planilha de extintores.\nO programa não funcionará corretamente.",
                JOptionPane.ERROR_MESSAGE);

        } 
        

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

         if (extintores == null) { // uma verificação de segurança
        areaEtiqueta.setText("Erro: a lista de extintores não foi carregada.");
        return;
    }

        Optional<Extintor> extOpt = extintores.stream()
                .filter(ext -> ext.getNumeroDePosicionamento().equalsIgnoreCase(localidade))
                .findFirst();

        if(extOpt.isPresent()) {
            Extintor ext = extOpt.get();

            String zpl = GeradorZPL.gerarEtiqueta(ext);
                    


            areaEtiqueta.setText(zpl);

        } else {
            areaEtiqueta.setText("Extintor não encontrado");
        }
    }
}