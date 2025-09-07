package org.example;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

public class InterfaceExtintor extends JFrame {

    private File arquivoPlanilhaSelecionado;
    private List<Extintor> extintores;
    private JTextField campoLocalidade;
    private JTextArea areaZPL;
    private JLabel labelImagemEtiqueta;
    private String zplAtual;
    private JComboBox<String> comboImpressoras;

    public InterfaceExtintor() {
        super("Controle de Etiquetas de Extintor");

        JPanel painelSuperior = new JPanel(null); // Define o layout como nulo
        painelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10)); // Borda ainda funciona

        // Botão "Selecione a planilha"
        JButton botaoSelecionarPlanilha = new JButton("Selecione a planilha");
        botaoSelecionarPlanilha.setBounds(20, 20, 150, 25); // x, y, width, height
        painelSuperior.add(botaoSelecionarPlanilha);

        // Label "Numero da Localidade:"
        JLabel labelLocalidade = new JLabel("Número da Localidade:");
        labelLocalidade.setBounds(180, 20, 150, 25);
        painelSuperior.add(labelLocalidade);

        // Campo de texto para localidade
        campoLocalidade = new JTextField(50);
        campoLocalidade.setBounds(320, 20, 100, 25);
        painelSuperior.add(campoLocalidade);

        // Botão "Visualizar Etiqueta"
        JButton botaoVisualizar = new JButton("Visualizar Etiqueta");
        botaoVisualizar.setBounds(450, 20, 150, 25);
        painelSuperior.add(botaoVisualizar);

        // Label "Selecione a Impressora:"
        JLabel labelImpressora = new JLabel("Selecione a Impressora:");
        labelImpressora.setBounds(20, 60, 150, 25); // Nova linha (y=60)
        painelSuperior.add(labelImpressora);

        // JComboBox para selecionar impressora
        comboImpressoras = new JComboBox<>();
        comboImpressoras.setBounds(180, 60, 250, 25); // Na mesma linha do label da impressora
        painelSuperior.add(comboImpressoras);

        // Botão "Imprimir"
        JButton botaoImprimir = new JButton("Imprimir");
        botaoImprimir.setBounds(450, 60, 150, 25); // Na mesma linha do combo box
        painelSuperior.add(botaoImprimir);
        
        painelSuperior.setPreferredSize(new java.awt.Dimension(620, 100)); // Exemplo de tamanho (ajuste conforme necessário)

        // --- Painel com Abas (Pré-visualização e Código ZPL) ---
        JTabbedPane painelComAbas = new JTabbedPane();

        // Aba 1: Pré-visualização da Imagem
        labelImagemEtiqueta = new JLabel("A pré-visualização da etiqueta aparecerá aqui.", SwingConstants.CENTER);
        labelImagemEtiqueta.setVerticalAlignment(SwingConstants.TOP);
        JScrollPane scrollImagem = new JScrollPane(labelImagemEtiqueta);
        painelComAbas.addTab("Pré-visualização", scrollImagem);

        // Aba 2: Código ZPL
        areaZPL = new JTextArea(20, 70);
        areaZPL.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaZPL.setEditable(false);
        JScrollPane scrollZPL = new JScrollPane(areaZPL);
        painelComAbas.addTab("Código ZPL", scrollZPL);

        // --- Configuração do Layout Principal ---
        setLayout(new BorderLayout(50, 50));
        add(painelSuperior, BorderLayout.NORTH);
        add(painelComAbas, BorderLayout.CENTER);

        // --- Ações dos Botões ---
        botaoSelecionarPlanilha.addActionListener(e -> selecionarPlanilha());
        botaoVisualizar.addActionListener(e -> buscarEVisualizarEtiqueta());
        botaoImprimir.addActionListener(e -> imprimirEtiqueta());

        // Carrega as impressoras ao iniciar
        carregarImpressoras();
        
        // --- Configurações da Janela ---
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setVisible(true);
        setSize(1100, 1000);
        setLocationRelativeTo(null);
    }
    
    private void carregarImpressoras() {
        // Limpa o combo box antes de carregar
        comboImpressoras.removeAllItems();
        
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        if (printServices.length > 0) {
            for (PrintService service : printServices) {
                comboImpressoras.addItem(service.getName());
            }
        } else {
            comboImpressoras.addItem("Nenhuma impressora encontrada.");
            JOptionPane.showMessageDialog(this, "Nenhuma impressora encontrada no sistema.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void selecionarPlanilha() {
        javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
        fileChooser.setDialogTitle("Selecione a planilha Excel");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Arquivos Excel (*.xlsx)", "xlsx"));

        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == javax.swing.JFileChooser.APPROVE_OPTION) {
            arquivoPlanilhaSelecionado = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Planilha selecionada: " + arquivoPlanilhaSelecionado.getName());

            try (InputStream arquivo = new java.io.FileInputStream(arquivoPlanilhaSelecionado)) {
                extintores = LeitorExcel.lerExtintoresDoExcel(arquivo);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao carregar a planilha selecionada: " + ex.getMessage(),
                        "Erro de Leitura", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void buscarEVisualizarEtiqueta() {
        String localidade = campoLocalidade.getText().trim();
        System.out.println("--- INICIANDO BUSCA ---");
        System.out.println("Buscando pela localidade: '" + localidade + "'");
        if (localidade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número de localidade.", "Entrada Inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (extintores == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione e carregue uma planilha primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Optional<Extintor> extOpt = extintores.stream()
                .filter(ext -> {
                    boolean encontrado = ext.getNumeroDePosicionamento().trim().equalsIgnoreCase(localidade);
                    if (encontrado) {
                        System.out.println(">>> CORRESPONDÊNCIA ENCONTRADA: " + ext.getNumeroDePosicionamento());
                    }
                    return encontrado;
                })
                .findFirst();

        if (extOpt.isPresent()) {
            System.out.println("Extintor encontrado! Gerando etiqueta...");
            Extintor ext = extOpt.get();
            zplAtual = GeradorZPL.gerarEtiqueta(ext);

            areaZPL.setText(zplAtual);
            areaZPL.setCaretPosition(0);

            atualizarPreviaDaEtiqueta(zplAtual);

        } else {
            System.out.println("!!! NENHUM EXTINTOR ENCONTRADO para a localidade: '" + localidade + "'");
            zplAtual = null;
            areaZPL.setText("Extintor com localidade '" + localidade + "' não encontrado.");
            labelImagemEtiqueta.setIcon(null);
            labelImagemEtiqueta.setText("Extintor não encontrado.");
        }
        System.out.println("--- FIM DA BUSCA ---");
    }

    private void atualizarPreviaDaEtiqueta(String zpl) {
        labelImagemEtiqueta.setIcon(null);
        labelImagemEtiqueta.setText("Carregando pré-visualização...");

        SwingWorker<ImageIcon, Void> worker = new SwingWorker<ImageIcon, Void>() {
            @Override
            protected ImageIcon doInBackground() throws Exception {
                String urlBase = "http://api.labelary.com/v1/printers/8dpmm/labels/4x6/0/";
                URL url = new URL(urlBase);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                con.setRequestProperty("Accept", "image/png");
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                con.getOutputStream().write(zpl.getBytes(StandardCharsets.UTF_8));

                if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = con.getInputStream();
                    BufferedImage bufferedImage = ImageIO.read(inputStream);
                    return new ImageIcon(bufferedImage);
                } else {
                    throw new RuntimeException("Falha na API do Labelary: " + con.getResponseMessage());
                }
            }

            @Override
            protected void done() {
                try {
                    ImageIcon imagem = get();
                    labelImagemEtiqueta.setIcon(imagem);
                    labelImagemEtiqueta.setText(null);
                } catch (Exception e) {
                    e.printStackTrace();
                    labelImagemEtiqueta.setIcon(null);
                    labelImagemEtiqueta.setText("Erro ao carregar pré-visualização. Verifique a conexão com a internet.");
                }
            }
        };
        worker.execute();
    }

    private void imprimirEtiqueta() {
        if (zplAtual == null || zplAtual.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há etiqueta para imprimir. Visualize uma etiqueta primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nomeDaImpressora = (String) comboImpressoras.getSelectedItem();

        if (nomeDaImpressora == null || nomeDaImpressora.trim().isEmpty() || nomeDaImpressora.equals("Nenhuma impressora encontrada.")) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma impressora válida.", "Erro de Impressão", JOptionPane.ERROR_MESSAGE);
            return;
        }

        PrintService impressoraZebra = null;
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService service : services) {
            if (service.getName().equalsIgnoreCase(nomeDaImpressora)) {
                impressoraZebra = service;
                break;
            }
        }

        if (impressoraZebra == null) {
            JOptionPane.showMessageDialog(this, "A impressora selecionada não foi encontrada.", "Erro de Impressão", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            DocPrintJob job = impressoraZebra.createPrintJob();
            // **CORREÇÃO APLICADA AQUI**: Envia os bytes como UTF-8
            byte[] bytesZPL = zplAtual.getBytes(StandardCharsets.UTF_8);
            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            Doc doc = new SimpleDoc(bytesZPL, flavor, null);

            job.print(doc, null);
            JOptionPane.showMessageDialog(this, "Etiqueta enviada para a impressora!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao imprimir: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}