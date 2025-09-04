package org.example;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

public class InterfaceExtintor extends JFrame {

    private List<Extintor> extintores;
    private JTextField campoLocalidade;
    private JTextArea areaZPL;
    private JLabel labelImagemEtiqueta; // Label para mostrar a imagem da etiqueta
    private String zplAtual; // Guarda o ZPL gerado para ser usado pela impressão

    public InterfaceExtintor() {
        super("Controle de Etiquetas de Extintor");

        // --- Carregamento dos dados
        try {
            InputStream arquivo = App.class.getResourceAsStream("/extintores.xlsx");
            if (arquivo == null) {
                throw new IllegalStateException("Arquivo 'extintores.xlsx' não encontrado no classpath.");
            }
            extintores = LeitorExcel.lerExtintoresDoExcel(arquivo);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erro crítico ao carregar a planilha de extintores: " + e.getMessage(),
                    "Erro de Leitura",
                    JOptionPane.ERROR_MESSAGE);
            // Encerra a aplicação se não conseguir ler o arquivo, pois ela se torna inútil.
            System.exit(1);
        }
        
        // --- Painel Superior ---
        JPanel painelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelSuperior.add(new JLabel("Numero da Localidade:"));
        campoLocalidade = new JTextField(15);
        painelSuperior.add(campoLocalidade);

        //botao visualizar
        JButton botaoVisualizar = new JButton("Visualizar Etiqueta");
        painelSuperior.add(botaoVisualizar);

        //botao imprimir
        JButton botaoImprimir = new JButton("Imprimir");
        painelSuperior.add(botaoImprimir);

        //painel de alterar
        JTabbedPane painelComAbas = new JTabbedPane();

        // Aba 1: Pré-visualização da Imagem
        labelImagemEtiqueta = new JLabel("A pré-visualização da etiqueta aparecerá aqui.", SwingConstants.CENTER);
        labelImagemEtiqueta.setVerticalAlignment(SwingConstants.TOP); // Alinha a imagem no topo
        JScrollPane scrollImagem = new JScrollPane(labelImagemEtiqueta);
        painelComAbas.addTab("Pré-visualização", scrollImagem);

        // Aba 2: Código ZPL
        areaZPL = new JTextArea(20, 70);
        areaZPL.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaZPL.setEditable(false); // O usuário não deve editar o código gerado
        JScrollPane scrollZPL = new JScrollPane(areaZPL);
        painelComAbas.addTab("Código ZPL", scrollZPL);

        // --- Configuração do Layout Principal ---
        setLayout(new BorderLayout(50, 50));
        add(painelSuperior, BorderLayout.NORTH);
        add(painelComAbas, BorderLayout.CENTER);

        // --- Ações dos Botões ---
        botaoVisualizar.addActionListener(e -> buscarEVisualizarEtiqueta());
        botaoImprimir.addActionListener(e -> imprimirEtiqueta());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // 1. Define a janela para ser maximizada
        setUndecorated(false); // 2. Mantém as decorações (barra de título, botões de fechar/minimizar)
        setVisible(true);

        // --- Configurações da Janela ---
        setSize(1100, 1000); // Um tamanho inicial maior para acomodar a etiqueta
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void buscarEVisualizarEtiqueta() {
        String localidade = campoLocalidade.getText().trim();
        System.out.println("--- INICIANDO BUSCA ---");
        System.out.println("Buscando pela localidade: '" + localidade + "'");
        if (localidade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número de localidade.", "Entrada Inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Optional<Extintor> extOpt = extintores.stream()
        .filter(ext -> {
            // Log para cada comparação feita
            boolean encontrado = ext.getNumeroDePosicionamento().trim().equalsIgnoreCase(localidade);
            if (encontrado) {
                System.out.println(">>> CORRESPONDÊNCIA ENCONTRADA: " + ext.getNumeroDePosicionamento());
            }
            return encontrado;
        })
        .findFirst();

        if (extOpt.isPresent()) {
        System.out.println("Extintor encontrado! Gerando etiqueta..."); //Sucesso :)
        Extintor ext = extOpt.get();
        zplAtual = GeradorZPL.gerarEtiqueta(ext);

        areaZPL.setText(zplAtual);
        areaZPL.setCaretPosition(0);

        atualizarPreviaDaEtiqueta(zplAtual);

        } else {
            System.out.println("!!! NENHUM EXTINTOR ENCONTRADO para a localidade: '" + localidade + "'"); // Falha!
            zplAtual = null;
            areaZPL.setText("Extintor com localidade '" + localidade + "' não encontrado.");
            labelImagemEtiqueta.setIcon(null);
            labelImagemEtiqueta.setText("Extintor não encontrado.");
        }
        System.out.println("--- FIM DA BUSCA ---");
    }

    // Metodo para buscar a imagem da etiqueta no Labelary
    private void atualizarPreviaDaEtiqueta(String zpl) {

        // Mostra uma mensagem de carregamento
        labelImagemEtiqueta.setIcon(null);
        labelImagemEtiqueta.setText("Carregando pré-visualização...");

        // Executa a requisição de rede em uma thread separada para não travar a interface
        SwingWorker<ImageIcon, Void> worker = new SwingWorker<ImageIcon, Void>() {
            @Override
            protected ImageIcon doInBackground() throws Exception {
                // URL da API do Labelary para gerar uma imagem PNG de 4x6 polegadas (aprox. 800x1200 pixels)
                String urlBase = "https://www.labelzoom.net/app/converter/from-zpl?to=png";
                
                // Prepara a conexão HTTP
                URL url = new URL(urlBase );
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                con.setRequestProperty("Accept", "image/png"); // Queremos um PNG de volta

                // Envia o código ZPL no corpo da requisição
                con.getOutputStream().write(zpl.getBytes(StandardCharsets.UTF_8));

                // Verifica se a requisição foi bem-sucedida
                if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = con.getInputStream();
                    BufferedImage bufferedImage = ImageIO.read(inputStream);
                    return new ImageIcon(bufferedImage);
                } else {
                    // Se deu erro, lança uma exceção com a mensagem do servidor
                    throw new RuntimeException("Falha na API do Labelary: " + con.getResponseMessage());
                }
            }

            @Override
            protected void done() {
                try {
                    ImageIcon imagem = get(); // Pega o resultado da thread
                    labelImagemEtiqueta.setIcon(imagem);
                    labelImagemEtiqueta.setText(null); // Remove o texto "Carregando..."
                } catch (Exception e) {
                    e.printStackTrace();
                    labelImagemEtiqueta.setIcon(null);
                    labelImagemEtiqueta.setText("Erro ao carregar pré-visualização. Verifique a conexão com a internet.");
                }
            }
        };
        worker.execute(); // Inicia a thread
    }

    private void imprimirEtiqueta() {
        if (zplAtual == null || zplAtual.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há etiqueta para imprimir. Visualize uma etiqueta primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            
            //*****ATENÇÃO***** --> AQUI MUDA O NOME DA IMPRESSORA, DIGITE EXTAMENTE IGUAL
            String nomeDaImpressora = "Digita O Nome";

            //prucurando a impressora pelo nome
            javax.print.PrintService[] services = javax.print.PrintServiceLookup.lookupPrintServices(null, null);
            javax.print.PrintService impressoraZebra = null;
            for (javax.print.PrintService service : services) {
                if (service.getName().equalsIgnoreCase(nomeDaImpressora)) {
                    impressoraZebra = service;
                    break;
                }
            }

            if (impressoraZebra == null) {
                JOptionPane.showMessageDialog(this, "Impressora nao encontrada: " + nomeDaImpressora + "Erro" + JOptionPane.ERROR_MESSAGE);
                return;
            }

            //cria o trabalho de impressão
            javax.print.DocPrintJob job = impressoraZebra.createPrintJob();

            //cria um documentos com os bytes do ZPL
            byte[] bytesZPL = zplAtual.getBytes();
            javax.print.DocFlavor flavor = javax.print.DocFlavor.BYTE_ARRAY.AUTOSENSE;
            javax.print.Doc doc = new javax.print.SimpleDoc(bytesZPL, flavor, null);

            //Envia para a impressora
            job.print(doc, null);
            JOptionPane.showMessageDialog(this, "Etiqueta enviada para a impressora!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao imprimir: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}