package Screen;

import Data.CTCONTAB;
import Data.Evento;
import Data.IconUtil;
import Data.PermissaoUtil;
import Data.Usuario;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TelaEventoTable extends javax.swing.JFrame {

    private final Usuario usuarioLogado;

    public TelaEventoTable(Usuario usuario) {
        this.usuarioLogado = usuario;
        initComponents();
        PermissaoUtil.aplicarPermissao(usuarioLogado, btnAdministracao);
        carregarEventosNoCalendario();
        IconUtil.setIcon(usuarioLogado, lblUserIcon);
    }

    private void carregarEventosNoCalendario() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        Map<Integer, List<String>> eventosPorDia = agruparEventosPorDia();

        for (int row = 0; row < model.getRowCount(); row++) {
            for (int col = 0; col < model.getColumnCount(); col++) {
                int dia = (row * 7) + col + 1;
                String eventosTexto = eventosPorDia.getOrDefault(dia, List.of())
                        .stream()
                        .collect(Collectors.joining("\n"));
                model.setValueAt(eventosTexto, row, col);
            }
        }
    }

    private Map<Integer, List<String>> agruparEventosPorDia() {
        try {
            return CTCONTAB.listarEventos().stream()
                    .collect(Collectors.groupingBy(
                            evento -> extrairDiaDoEvento(evento.getDataInicio()),
                            Collectors.mapping(Evento::getEvento, Collectors.toList())
                    ));
        } catch (Exception e) {
            e.printStackTrace();
            return Map.of(); // Retorna mapa vazio em caso de erro
        }
    }

    private int extrairDiaDoEvento(String dataInicio) {
        String[] partes = dataInicio.split("-");
        return Integer.parseInt(partes[2]);
    }

    private static class CalendarioCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            JTextArea textArea = new JTextArea();
            textArea.setText(value != null ? value.toString() : "");
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setBackground(new Color(0, 0, 0, 0));
            //textArea.setBorder(null);
            textArea.setAlignmentY(Component.BOTTOM_ALIGNMENT);
            textArea.setAlignmentX(SwingConstants.CENTER);
            textArea.setForeground(Color.WHITE);
            textArea.setFont(new Font("Arial", Font.BOLD, 12));
            textArea.setPreferredSize(new Dimension(40, 40));

            textArea.setAlignmentY(Component.BOTTOM_ALIGNMENT);
            textArea.setMargin(new Insets(50, 10, 5, 10));

            return textArea;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDataDeCadastro38 = new javax.swing.JLabel();
        lblDataDeCadastro39 = new javax.swing.JLabel();
        lblDataDeCadastro40 = new javax.swing.JLabel();
        lblDataDeCadastro41 = new javax.swing.JLabel();
        lblDataDeCadastro33 = new javax.swing.JLabel();
        lblDataDeCadastro34 = new javax.swing.JLabel();
        lblDataDeCadastro35 = new javax.swing.JLabel();
        lblDataDeCadastro36 = new javax.swing.JLabel();
        lblDataDeCadastro28 = new javax.swing.JLabel();
        lblDataDeCadastro29 = new javax.swing.JLabel();
        lblDataDeCadastro30 = new javax.swing.JLabel();
        lblDataDeCadastro31 = new javax.swing.JLabel();
        lblDataDeCadastro23 = new javax.swing.JLabel();
        lblDataDeCadastro24 = new javax.swing.JLabel();
        lblDataDeCadastro25 = new javax.swing.JLabel();
        lblDataDeCadastro26 = new javax.swing.JLabel();
        lblDataDeCadastro17 = new javax.swing.JLabel();
        lblDataDeCadastro18 = new javax.swing.JLabel();
        lblDataDeCadastro19 = new javax.swing.JLabel();
        lblDataDeCadastro20 = new javax.swing.JLabel();
        lblDataDeCadastro21 = new javax.swing.JLabel();
        lblDataDeCadastro12 = new javax.swing.JLabel();
        lblDataDeCadastro13 = new javax.swing.JLabel();
        lblDataDeCadastro14 = new javax.swing.JLabel();
        lblDataDeCadastro15 = new javax.swing.JLabel();
        lblDataDeCadastro16 = new javax.swing.JLabel();
        lblDataDeCadastro11 = new javax.swing.JLabel();
        lblDataDeCadastro10 = new javax.swing.JLabel();
        lblDataDeCadastro9 = new javax.swing.JLabel();
        lblDataDeCadastro8 = new javax.swing.JLabel();
        lblDataDeCadastro7 = new javax.swing.JLabel();
        btnCadastrarEvento32 = new javax.swing.JButton();
        lblCadastrarEvento32 = new javax.swing.JLabel();
        btnCadastrarEvento33 = new javax.swing.JButton();
        lblCadastrarEvento33 = new javax.swing.JLabel();
        btnCadastrarEvento34 = new javax.swing.JButton();
        lblCadastrarEvento34 = new javax.swing.JLabel();
        btnCadastrarEvento35 = new javax.swing.JButton();
        lblCadastrarEvento35 = new javax.swing.JLabel();
        btnCadastrarEvento27 = new javax.swing.JButton();
        lblCadastrarEvento27 = new javax.swing.JLabel();
        btnCadastrarEvento28 = new javax.swing.JButton();
        lblCadastrarEvento28 = new javax.swing.JLabel();
        btnCadastrarEvento29 = new javax.swing.JButton();
        lblCadastrarEvento29 = new javax.swing.JLabel();
        btnCadastrarEvento30 = new javax.swing.JButton();
        lblCadastrarEvento30 = new javax.swing.JLabel();
        btnCadastrarEvento22 = new javax.swing.JButton();
        lblCadastrarEvento22 = new javax.swing.JLabel();
        btnCadastrarEvento23 = new javax.swing.JButton();
        lblCadastrarEvento23 = new javax.swing.JLabel();
        btnCadastrarEvento24 = new javax.swing.JButton();
        lblCadastrarEvento24 = new javax.swing.JLabel();
        btnCadastrarEvento25 = new javax.swing.JButton();
        lblCadastrarEvento25 = new javax.swing.JLabel();
        btnCadastrarEvento17 = new javax.swing.JButton();
        lblCadastrarEvento17 = new javax.swing.JLabel();
        btnCadastrarEvento18 = new javax.swing.JButton();
        lblCadastrarEvento18 = new javax.swing.JLabel();
        btnCadastrarEvento19 = new javax.swing.JButton();
        lblCadastrarEvento19 = new javax.swing.JLabel();
        btnCadastrarEvento20 = new javax.swing.JButton();
        lblCadastrarEvento20 = new javax.swing.JLabel();
        btnCadastrarEvento11 = new javax.swing.JButton();
        lblCadastrarEvento11 = new javax.swing.JLabel();
        btnCadastrarEvento12 = new javax.swing.JButton();
        lblCadastrarEvento12 = new javax.swing.JLabel();
        btnCadastrarEvento13 = new javax.swing.JButton();
        lblCadastrarEvento13 = new javax.swing.JLabel();
        btnCadastrarEvento14 = new javax.swing.JButton();
        lblCadastrarEvento14 = new javax.swing.JLabel();
        btnCadastrarEvento15 = new javax.swing.JButton();
        lblCadastrarEvento15 = new javax.swing.JLabel();
        btnCadastrarEvento6 = new javax.swing.JButton();
        lblCadastrarEvento6 = new javax.swing.JLabel();
        lblCadastrarEvento16 = new javax.swing.JLabel();
        btnCadastrarEvento16 = new javax.swing.JButton();
        lblCadastrarEvento21 = new javax.swing.JLabel();
        btnCadastrarEvento21 = new javax.swing.JButton();
        lblCadastrarEvento26 = new javax.swing.JLabel();
        btnCadastrarEvento26 = new javax.swing.JButton();
        lblCadastrarEvento31 = new javax.swing.JLabel();
        btnCadastrarEvento31 = new javax.swing.JButton();
        btnCadastrarEvento7 = new javax.swing.JButton();
        btnCadastrarEvento8 = new javax.swing.JButton();
        btnCadastrarEvento9 = new javax.swing.JButton();
        btnCadastrarEvento10 = new javax.swing.JButton();
        btnCadastrarEvento5 = new javax.swing.JButton();
        lblCadastrarEvento5 = new javax.swing.JLabel();
        btnCadastrarEvento4 = new javax.swing.JButton();
        lblCadastrarEvento4 = new javax.swing.JLabel();
        btnCadastrarEvento3 = new javax.swing.JButton();
        lblCadastrarEvento3 = new javax.swing.JLabel();
        btnCadastrarEvento2 = new javax.swing.JButton();
        lblCadastrarEvento2 = new javax.swing.JLabel();
        btnCadastrarEvento = new javax.swing.JButton();
        lblCadastrarEvento = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblDataDeCadastro4 = new javax.swing.JLabel();
        lblDataDeCadastro5 = new javax.swing.JLabel();
        lblDataDeCadastro6 = new javax.swing.JLabel();
        lblDataDeCadastro3 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        lblDataDeCadastro2 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        lblDataDeCadastro1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lblDataDeCadastro = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jlibCadastrarNovo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnAdministracao = new javax.swing.JButton();
        btnConfiguracoes = new javax.swing.JButton();
        btnTarefas = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnRelatorios = new javax.swing.JButton();
        btnCalendario = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        JPanelTelaAcesso = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jlibLogo2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblUserIcon = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnNotificacoes = new javax.swing.JButton();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lblDataDeCadastro38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro38.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro38.setText("28");
        lblDataDeCadastro38.setToolTipText("");
        getContentPane().add(lblDataDeCadastro38);
        lblDataDeCadastro38.setBounds(1205, 440, 30, 20);

        lblDataDeCadastro39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro39.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro39.setText("21");
        lblDataDeCadastro39.setToolTipText("");
        getContentPane().add(lblDataDeCadastro39);
        lblDataDeCadastro39.setBounds(1205, 340, 30, 20);

        lblDataDeCadastro40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro40.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro40.setText("7");
        lblDataDeCadastro40.setToolTipText("");
        getContentPane().add(lblDataDeCadastro40);
        lblDataDeCadastro40.setBounds(1205, 140, 30, 20);

        lblDataDeCadastro41.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro41.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro41.setText("14");
        lblDataDeCadastro41.setToolTipText("");
        getContentPane().add(lblDataDeCadastro41);
        lblDataDeCadastro41.setBounds(1205, 240, 30, 20);

        lblDataDeCadastro33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro33.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro33.setText("27");
        lblDataDeCadastro33.setToolTipText("");
        getContentPane().add(lblDataDeCadastro33);
        lblDataDeCadastro33.setBounds(1045, 440, 30, 20);

        lblDataDeCadastro34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro34.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro34.setText("20");
        lblDataDeCadastro34.setToolTipText("");
        getContentPane().add(lblDataDeCadastro34);
        lblDataDeCadastro34.setBounds(1045, 340, 30, 20);

        lblDataDeCadastro35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro35.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro35.setText("6");
        lblDataDeCadastro35.setToolTipText("");
        getContentPane().add(lblDataDeCadastro35);
        lblDataDeCadastro35.setBounds(1045, 140, 30, 20);

        lblDataDeCadastro36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro36.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro36.setText("13");
        lblDataDeCadastro36.setToolTipText("");
        getContentPane().add(lblDataDeCadastro36);
        lblDataDeCadastro36.setBounds(1045, 240, 30, 20);

        lblDataDeCadastro28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro28.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro28.setText("26");
        lblDataDeCadastro28.setToolTipText("");
        getContentPane().add(lblDataDeCadastro28);
        lblDataDeCadastro28.setBounds(885, 440, 30, 20);

        lblDataDeCadastro29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro29.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro29.setText("19");
        lblDataDeCadastro29.setToolTipText("");
        getContentPane().add(lblDataDeCadastro29);
        lblDataDeCadastro29.setBounds(885, 340, 30, 20);

        lblDataDeCadastro30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro30.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro30.setText("5");
        lblDataDeCadastro30.setToolTipText("");
        getContentPane().add(lblDataDeCadastro30);
        lblDataDeCadastro30.setBounds(885, 140, 30, 20);

        lblDataDeCadastro31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro31.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro31.setText("12");
        lblDataDeCadastro31.setToolTipText("");
        getContentPane().add(lblDataDeCadastro31);
        lblDataDeCadastro31.setBounds(885, 240, 30, 20);

        lblDataDeCadastro23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro23.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro23.setText("25");
        lblDataDeCadastro23.setToolTipText("");
        getContentPane().add(lblDataDeCadastro23);
        lblDataDeCadastro23.setBounds(725, 440, 30, 20);

        lblDataDeCadastro24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro24.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro24.setText("18");
        lblDataDeCadastro24.setToolTipText("");
        getContentPane().add(lblDataDeCadastro24);
        lblDataDeCadastro24.setBounds(725, 340, 30, 20);

        lblDataDeCadastro25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro25.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro25.setText("11");
        lblDataDeCadastro25.setToolTipText("");
        getContentPane().add(lblDataDeCadastro25);
        lblDataDeCadastro25.setBounds(725, 240, 30, 20);

        lblDataDeCadastro26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro26.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro26.setText("4");
        lblDataDeCadastro26.setToolTipText("");
        getContentPane().add(lblDataDeCadastro26);
        lblDataDeCadastro26.setBounds(725, 140, 30, 20);

        lblDataDeCadastro17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro17.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro17.setText("31");
        lblDataDeCadastro17.setToolTipText("");
        getContentPane().add(lblDataDeCadastro17);
        lblDataDeCadastro17.setBounds(565, 540, 30, 20);

        lblDataDeCadastro18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro18.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro18.setText("24");
        lblDataDeCadastro18.setToolTipText("");
        getContentPane().add(lblDataDeCadastro18);
        lblDataDeCadastro18.setBounds(565, 440, 30, 20);

        lblDataDeCadastro19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro19.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro19.setText("17");
        lblDataDeCadastro19.setToolTipText("");
        getContentPane().add(lblDataDeCadastro19);
        lblDataDeCadastro19.setBounds(565, 340, 30, 20);

        lblDataDeCadastro20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro20.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro20.setText("10");
        lblDataDeCadastro20.setToolTipText("");
        getContentPane().add(lblDataDeCadastro20);
        lblDataDeCadastro20.setBounds(565, 240, 30, 20);

        lblDataDeCadastro21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro21.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro21.setText("3");
        lblDataDeCadastro21.setToolTipText("");
        getContentPane().add(lblDataDeCadastro21);
        lblDataDeCadastro21.setBounds(565, 140, 30, 20);

        lblDataDeCadastro12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro12.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro12.setText("30");
        lblDataDeCadastro12.setToolTipText("");
        getContentPane().add(lblDataDeCadastro12);
        lblDataDeCadastro12.setBounds(405, 540, 30, 20);

        lblDataDeCadastro13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro13.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro13.setText("23");
        lblDataDeCadastro13.setToolTipText("");
        getContentPane().add(lblDataDeCadastro13);
        lblDataDeCadastro13.setBounds(405, 440, 30, 20);

        lblDataDeCadastro14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro14.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro14.setText("16");
        lblDataDeCadastro14.setToolTipText("");
        getContentPane().add(lblDataDeCadastro14);
        lblDataDeCadastro14.setBounds(405, 340, 30, 20);

        lblDataDeCadastro15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro15.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro15.setText("9");
        lblDataDeCadastro15.setToolTipText("");
        getContentPane().add(lblDataDeCadastro15);
        lblDataDeCadastro15.setBounds(405, 240, 30, 20);

        lblDataDeCadastro16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro16.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro16.setText("2");
        lblDataDeCadastro16.setToolTipText("");
        getContentPane().add(lblDataDeCadastro16);
        lblDataDeCadastro16.setBounds(405, 140, 30, 20);

        lblDataDeCadastro11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro11.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro11.setText("29");
        lblDataDeCadastro11.setToolTipText("");
        getContentPane().add(lblDataDeCadastro11);
        lblDataDeCadastro11.setBounds(240, 540, 30, 20);

        lblDataDeCadastro10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro10.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro10.setText("22");
        lblDataDeCadastro10.setToolTipText("");
        getContentPane().add(lblDataDeCadastro10);
        lblDataDeCadastro10.setBounds(240, 440, 30, 20);

        lblDataDeCadastro9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro9.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro9.setText("15");
        lblDataDeCadastro9.setToolTipText("");
        getContentPane().add(lblDataDeCadastro9);
        lblDataDeCadastro9.setBounds(240, 340, 30, 20);

        lblDataDeCadastro8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro8.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro8.setText("8");
        lblDataDeCadastro8.setToolTipText("");
        getContentPane().add(lblDataDeCadastro8);
        lblDataDeCadastro8.setBounds(240, 240, 30, 20);

        lblDataDeCadastro7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro7.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro7.setText("1");
        lblDataDeCadastro7.setToolTipText("");
        getContentPane().add(lblDataDeCadastro7);
        lblDataDeCadastro7.setBounds(240, 140, 30, 20);

        btnCadastrarEvento32.setBorderPainted(false);
        btnCadastrarEvento32.setContentAreaFilled(false);
        btnCadastrarEvento32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento32ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento32);
        btnCadastrarEvento32.setBounds(1080, 440, 30, 20);

        lblCadastrarEvento32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento32.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento32.setToolTipText("");
        lblCadastrarEvento32.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento32KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento32);
        lblCadastrarEvento32.setBounds(1080, 430, 30, 50);

        btnCadastrarEvento33.setBorderPainted(false);
        btnCadastrarEvento33.setContentAreaFilled(false);
        btnCadastrarEvento33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento33ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento33);
        btnCadastrarEvento33.setBounds(1080, 340, 30, 20);

        lblCadastrarEvento33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento33.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento33.setToolTipText("");
        lblCadastrarEvento33.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento33KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento33);
        lblCadastrarEvento33.setBounds(1080, 330, 30, 50);

        btnCadastrarEvento34.setBorderPainted(false);
        btnCadastrarEvento34.setContentAreaFilled(false);
        btnCadastrarEvento34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento34ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento34);
        btnCadastrarEvento34.setBounds(1080, 240, 30, 20);

        lblCadastrarEvento34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento34.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento34.setToolTipText("");
        lblCadastrarEvento34.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento34KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento34);
        lblCadastrarEvento34.setBounds(1080, 230, 30, 50);

        btnCadastrarEvento35.setBorderPainted(false);
        btnCadastrarEvento35.setContentAreaFilled(false);
        btnCadastrarEvento35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento35ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento35);
        btnCadastrarEvento35.setBounds(1080, 140, 30, 20);

        lblCadastrarEvento35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento35.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento35.setToolTipText("");
        lblCadastrarEvento35.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento35KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento35);
        lblCadastrarEvento35.setBounds(1080, 130, 30, 50);

        btnCadastrarEvento27.setBorderPainted(false);
        btnCadastrarEvento27.setContentAreaFilled(false);
        btnCadastrarEvento27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento27ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento27);
        btnCadastrarEvento27.setBounds(915, 440, 30, 20);

        lblCadastrarEvento27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento27.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento27.setToolTipText("");
        lblCadastrarEvento27.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento27KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento27);
        lblCadastrarEvento27.setBounds(915, 430, 30, 50);

        btnCadastrarEvento28.setBorderPainted(false);
        btnCadastrarEvento28.setContentAreaFilled(false);
        btnCadastrarEvento28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento28ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento28);
        btnCadastrarEvento28.setBounds(915, 340, 30, 20);

        lblCadastrarEvento28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento28.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento28.setToolTipText("");
        lblCadastrarEvento28.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento28KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento28);
        lblCadastrarEvento28.setBounds(915, 330, 30, 50);

        btnCadastrarEvento29.setBorderPainted(false);
        btnCadastrarEvento29.setContentAreaFilled(false);
        btnCadastrarEvento29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento29ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento29);
        btnCadastrarEvento29.setBounds(915, 240, 30, 20);

        lblCadastrarEvento29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento29.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento29.setToolTipText("");
        lblCadastrarEvento29.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento29KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento29);
        lblCadastrarEvento29.setBounds(915, 230, 30, 50);

        btnCadastrarEvento30.setBorderPainted(false);
        btnCadastrarEvento30.setContentAreaFilled(false);
        btnCadastrarEvento30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento30ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento30);
        btnCadastrarEvento30.setBounds(915, 140, 30, 20);

        lblCadastrarEvento30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento30.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento30.setToolTipText("");
        lblCadastrarEvento30.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento30KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento30);
        lblCadastrarEvento30.setBounds(915, 130, 30, 50);

        btnCadastrarEvento22.setBorderPainted(false);
        btnCadastrarEvento22.setContentAreaFilled(false);
        btnCadastrarEvento22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento22ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento22);
        btnCadastrarEvento22.setBounds(755, 440, 30, 20);

        lblCadastrarEvento22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento22.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento22.setToolTipText("");
        lblCadastrarEvento22.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento22KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento22);
        lblCadastrarEvento22.setBounds(755, 430, 30, 50);

        btnCadastrarEvento23.setBorderPainted(false);
        btnCadastrarEvento23.setContentAreaFilled(false);
        btnCadastrarEvento23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento23ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento23);
        btnCadastrarEvento23.setBounds(755, 340, 30, 20);

        lblCadastrarEvento23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento23.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento23.setToolTipText("");
        lblCadastrarEvento23.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento23KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento23);
        lblCadastrarEvento23.setBounds(755, 330, 30, 50);

        btnCadastrarEvento24.setBorderPainted(false);
        btnCadastrarEvento24.setContentAreaFilled(false);
        btnCadastrarEvento24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento24ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento24);
        btnCadastrarEvento24.setBounds(755, 240, 30, 20);

        lblCadastrarEvento24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento24.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento24.setToolTipText("");
        lblCadastrarEvento24.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento24KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento24);
        lblCadastrarEvento24.setBounds(755, 230, 30, 50);

        btnCadastrarEvento25.setBorderPainted(false);
        btnCadastrarEvento25.setContentAreaFilled(false);
        btnCadastrarEvento25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento25ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento25);
        btnCadastrarEvento25.setBounds(755, 140, 30, 20);

        lblCadastrarEvento25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento25.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento25.setToolTipText("");
        lblCadastrarEvento25.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento25KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento25);
        lblCadastrarEvento25.setBounds(755, 130, 30, 50);

        btnCadastrarEvento17.setBorderPainted(false);
        btnCadastrarEvento17.setContentAreaFilled(false);
        btnCadastrarEvento17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento17ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento17);
        btnCadastrarEvento17.setBounds(595, 440, 30, 20);

        lblCadastrarEvento17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento17.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento17.setToolTipText("");
        lblCadastrarEvento17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento17KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento17);
        lblCadastrarEvento17.setBounds(595, 430, 30, 50);

        btnCadastrarEvento18.setBorderPainted(false);
        btnCadastrarEvento18.setContentAreaFilled(false);
        btnCadastrarEvento18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento18ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento18);
        btnCadastrarEvento18.setBounds(595, 340, 30, 20);

        lblCadastrarEvento18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento18.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento18.setToolTipText("");
        lblCadastrarEvento18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento18KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento18);
        lblCadastrarEvento18.setBounds(595, 330, 30, 50);

        btnCadastrarEvento19.setBorderPainted(false);
        btnCadastrarEvento19.setContentAreaFilled(false);
        btnCadastrarEvento19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento19ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento19);
        btnCadastrarEvento19.setBounds(595, 240, 30, 20);

        lblCadastrarEvento19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento19.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento19.setToolTipText("");
        lblCadastrarEvento19.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento19KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento19);
        lblCadastrarEvento19.setBounds(595, 230, 30, 50);

        btnCadastrarEvento20.setBorderPainted(false);
        btnCadastrarEvento20.setContentAreaFilled(false);
        btnCadastrarEvento20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento20ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento20);
        btnCadastrarEvento20.setBounds(595, 140, 30, 20);

        lblCadastrarEvento20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento20.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento20.setToolTipText("");
        lblCadastrarEvento20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento20KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento20);
        lblCadastrarEvento20.setBounds(595, 130, 30, 50);

        btnCadastrarEvento11.setBorderPainted(false);
        btnCadastrarEvento11.setContentAreaFilled(false);
        btnCadastrarEvento11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento11ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento11);
        btnCadastrarEvento11.setBounds(435, 540, 30, 20);

        lblCadastrarEvento11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento11.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento11.setToolTipText("");
        lblCadastrarEvento11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento11KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento11);
        lblCadastrarEvento11.setBounds(435, 530, 30, 50);

        btnCadastrarEvento12.setBorderPainted(false);
        btnCadastrarEvento12.setContentAreaFilled(false);
        btnCadastrarEvento12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento12ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento12);
        btnCadastrarEvento12.setBounds(435, 440, 30, 20);

        lblCadastrarEvento12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento12.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento12.setToolTipText("");
        lblCadastrarEvento12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento12KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento12);
        lblCadastrarEvento12.setBounds(435, 430, 30, 50);

        btnCadastrarEvento13.setBorderPainted(false);
        btnCadastrarEvento13.setContentAreaFilled(false);
        btnCadastrarEvento13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento13ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento13);
        btnCadastrarEvento13.setBounds(435, 340, 30, 20);

        lblCadastrarEvento13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento13.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento13.setToolTipText("");
        lblCadastrarEvento13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento13KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento13);
        lblCadastrarEvento13.setBounds(435, 330, 30, 50);

        btnCadastrarEvento14.setBorderPainted(false);
        btnCadastrarEvento14.setContentAreaFilled(false);
        btnCadastrarEvento14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento14ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento14);
        btnCadastrarEvento14.setBounds(435, 240, 30, 20);

        lblCadastrarEvento14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento14.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento14.setToolTipText("");
        lblCadastrarEvento14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento14KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento14);
        lblCadastrarEvento14.setBounds(435, 230, 30, 50);

        btnCadastrarEvento15.setBorderPainted(false);
        btnCadastrarEvento15.setContentAreaFilled(false);
        btnCadastrarEvento15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento15ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento15);
        btnCadastrarEvento15.setBounds(435, 140, 30, 20);

        lblCadastrarEvento15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento15.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento15.setToolTipText("");
        lblCadastrarEvento15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento15KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento15);
        lblCadastrarEvento15.setBounds(435, 130, 30, 50);

        btnCadastrarEvento6.setBorderPainted(false);
        btnCadastrarEvento6.setContentAreaFilled(false);
        btnCadastrarEvento6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento6ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento6);
        btnCadastrarEvento6.setBounds(270, 540, 30, 20);

        lblCadastrarEvento6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento6.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento6.setToolTipText("");
        lblCadastrarEvento6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento6KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento6);
        lblCadastrarEvento6.setBounds(270, 530, 30, 50);

        lblCadastrarEvento16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento16.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento16.setToolTipText("");
        lblCadastrarEvento16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento16KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento16);
        lblCadastrarEvento16.setBounds(270, 130, 30, 50);

        btnCadastrarEvento16.setBorderPainted(false);
        btnCadastrarEvento16.setContentAreaFilled(false);
        btnCadastrarEvento16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento16ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento16);
        btnCadastrarEvento16.setBounds(270, 140, 30, 20);

        lblCadastrarEvento21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento21.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento21.setToolTipText("");
        lblCadastrarEvento21.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento21KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento21);
        lblCadastrarEvento21.setBounds(270, 230, 30, 50);

        btnCadastrarEvento21.setBorderPainted(false);
        btnCadastrarEvento21.setContentAreaFilled(false);
        btnCadastrarEvento21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento21ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento21);
        btnCadastrarEvento21.setBounds(270, 240, 30, 20);

        lblCadastrarEvento26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento26.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento26.setToolTipText("");
        lblCadastrarEvento26.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento26KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento26);
        lblCadastrarEvento26.setBounds(270, 330, 30, 50);

        btnCadastrarEvento26.setBorderPainted(false);
        btnCadastrarEvento26.setContentAreaFilled(false);
        btnCadastrarEvento26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento26ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento26);
        btnCadastrarEvento26.setBounds(270, 340, 30, 20);

        lblCadastrarEvento31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento31.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento31.setToolTipText("");
        lblCadastrarEvento31.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento31KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento31);
        lblCadastrarEvento31.setBounds(270, 430, 30, 50);

        btnCadastrarEvento31.setBorderPainted(false);
        btnCadastrarEvento31.setContentAreaFilled(false);
        btnCadastrarEvento31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento31ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento31);
        btnCadastrarEvento31.setBounds(270, 440, 30, 20);

        btnCadastrarEvento7.setBorderPainted(false);
        btnCadastrarEvento7.setContentAreaFilled(false);
        btnCadastrarEvento7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento7ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento7);
        btnCadastrarEvento7.setBounds(282, 440, 30, 20);

        btnCadastrarEvento8.setBorderPainted(false);
        btnCadastrarEvento8.setContentAreaFilled(false);
        btnCadastrarEvento8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento8ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento8);
        btnCadastrarEvento8.setBounds(282, 340, 30, 20);

        btnCadastrarEvento9.setBorderPainted(false);
        btnCadastrarEvento9.setContentAreaFilled(false);
        btnCadastrarEvento9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento9ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento9);
        btnCadastrarEvento9.setBounds(282, 230, 30, 20);

        btnCadastrarEvento10.setBorderPainted(false);
        btnCadastrarEvento10.setContentAreaFilled(false);
        btnCadastrarEvento10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento10ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento10);
        btnCadastrarEvento10.setBounds(282, 140, 30, 20);

        btnCadastrarEvento5.setBorderPainted(false);
        btnCadastrarEvento5.setContentAreaFilled(false);
        btnCadastrarEvento5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento5ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento5);
        btnCadastrarEvento5.setBounds(110, 540, 30, 20);

        lblCadastrarEvento5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento5.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento5.setToolTipText("");
        lblCadastrarEvento5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento5KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento5);
        lblCadastrarEvento5.setBounds(110, 530, 30, 50);

        btnCadastrarEvento4.setBorderPainted(false);
        btnCadastrarEvento4.setContentAreaFilled(false);
        btnCadastrarEvento4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento4ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento4);
        btnCadastrarEvento4.setBounds(110, 440, 30, 20);

        lblCadastrarEvento4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento4.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento4.setToolTipText("");
        lblCadastrarEvento4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento4KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento4);
        lblCadastrarEvento4.setBounds(110, 430, 30, 50);

        btnCadastrarEvento3.setBorderPainted(false);
        btnCadastrarEvento3.setContentAreaFilled(false);
        btnCadastrarEvento3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento3ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento3);
        btnCadastrarEvento3.setBounds(110, 340, 30, 20);

        lblCadastrarEvento3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento3.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento3.setToolTipText("");
        lblCadastrarEvento3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento3KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento3);
        lblCadastrarEvento3.setBounds(110, 330, 30, 50);

        btnCadastrarEvento2.setBorderPainted(false);
        btnCadastrarEvento2.setContentAreaFilled(false);
        btnCadastrarEvento2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEvento2ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento2);
        btnCadastrarEvento2.setBounds(110, 240, 30, 20);

        lblCadastrarEvento2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento2.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento2.setToolTipText("");
        lblCadastrarEvento2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEvento2KeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento2);
        lblCadastrarEvento2.setBounds(110, 230, 30, 50);

        btnCadastrarEvento.setBorderPainted(false);
        btnCadastrarEvento.setContentAreaFilled(false);
        btnCadastrarEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEventoActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarEvento);
        btnCadastrarEvento.setBounds(110, 140, 30, 20);

        lblCadastrarEvento.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCadastrarEvento.setForeground(new java.awt.Color(186, 186, 186));
        lblCadastrarEvento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastrarEvento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus-button.png"))); // NOI18N
        lblCadastrarEvento.setToolTipText("");
        lblCadastrarEvento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCadastrarEventoKeyPressed(evt);
            }
        });
        getContentPane().add(lblCadastrarEvento);
        lblCadastrarEvento.setBounds(110, 130, 30, 50);

        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "DOMINGO", "SEGUNDA", "TERÇA", "QUARTA", "QUINTA", "SEXTA", "SABADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(115, 115, 115));
        jTable1.setRowHeight(100);
        jTable1.setShowGrid(false);
        jTable1.setDefaultRenderer(Object.class, new CalendarioCellRenderer());
        jTable1.setTableHeader(null);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(110, 130, 1130, 510);
        jTable1.setOpaque(false);
        ((DefaultTableCellRenderer) jTable1.getDefaultRenderer(Object.class)).setOpaque(false);
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);

        lblDataDeCadastro4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro4.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro4.setText("QUINTA");
        lblDataDeCadastro4.setToolTipText("");
        getContentPane().add(lblDataDeCadastro4);
        lblDataDeCadastro4.setBounds(760, 100, 160, 30);

        lblDataDeCadastro5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro5.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro5.setText("SEXTA");
        lblDataDeCadastro5.setToolTipText("");
        getContentPane().add(lblDataDeCadastro5);
        lblDataDeCadastro5.setBounds(920, 100, 160, 30);

        lblDataDeCadastro6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro6.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro6.setText("SABADO");
        lblDataDeCadastro6.setToolTipText("");
        getContentPane().add(lblDataDeCadastro6);
        lblDataDeCadastro6.setBounds(1080, 100, 160, 30);

        lblDataDeCadastro3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro3.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro3.setText("QUARTA");
        lblDataDeCadastro3.setToolTipText("");
        getContentPane().add(lblDataDeCadastro3);
        lblDataDeCadastro3.setBounds(600, 100, 160, 30);

        jSeparator7.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator7.setForeground(new java.awt.Color(115, 115, 115));
        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator7);
        jSeparator7.setBounds(1077, 100, 30, 30);

        jSeparator6.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator6.setForeground(new java.awt.Color(115, 115, 115));
        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator6);
        jSeparator6.setBounds(916, 100, 30, 30);

        jSeparator4.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator4.setForeground(new java.awt.Color(115, 115, 115));
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator4);
        jSeparator4.setBounds(434, 100, 30, 30);

        lblDataDeCadastro2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro2.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro2.setText("TERÇA");
        lblDataDeCadastro2.setToolTipText("");
        getContentPane().add(lblDataDeCadastro2);
        lblDataDeCadastro2.setBounds(440, 100, 150, 30);

        jSeparator5.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator5.setForeground(new java.awt.Color(115, 115, 115));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator5);
        jSeparator5.setBounds(594, 100, 50, 30);

        lblDataDeCadastro1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro1.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro1.setText("SEGUNDA");
        lblDataDeCadastro1.setToolTipText("");
        getContentPane().add(lblDataDeCadastro1);
        lblDataDeCadastro1.setBounds(280, 100, 150, 30);

        jSeparator3.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator3.setForeground(new java.awt.Color(115, 115, 115));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator3);
        jSeparator3.setBounds(756, 100, 30, 30);

        jSeparator2.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator2.setForeground(new java.awt.Color(115, 115, 115));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(273, 100, 30, 30);

        lblDataDeCadastro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro.setText("DOMINGO");
        lblDataDeCadastro.setToolTipText("");
        getContentPane().add(lblDataDeCadastro);
        lblDataDeCadastro.setBounds(108, 100, 170, 30);

        jSeparator8.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator8.setForeground(new java.awt.Color(115, 115, 115));
        getContentPane().add(jSeparator8);
        jSeparator8.setBounds(110, 130, 1130, 30);

        jlibCadastrarNovo.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jlibCadastrarNovo.setForeground(new java.awt.Color(205, 168, 16));
        jlibCadastrarNovo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlibCadastrarNovo.setText("NOVEMBRO 2024");
        getContentPane().add(jlibCadastrarNovo);
        jlibCadastrarNovo.setBounds(400, 20, 570, 50);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/seta-redondaE.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(400, 10, 120, 70);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/seta-redondaD.png"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(920, 10, 120, 70);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255, 15));
        getContentPane().add(jPanel1);
        jPanel1.setBounds(110, 100, 1130, 540);

        btnAdministracao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/admin-icon.png"))); // NOI18N
        btnAdministracao.setContentAreaFilled(false);
        btnAdministracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministracaoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdministracao);
        btnAdministracao.setBounds(0, 570, 80, 60);

        btnConfiguracoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/config-icon.png"))); // NOI18N
        btnConfiguracoes.setContentAreaFilled(false);
        btnConfiguracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracoesActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfiguracoes);
        btnConfiguracoes.setBounds(0, 500, 80, 50);

        btnTarefas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/task-icon.png"))); // NOI18N
        btnTarefas.setContentAreaFilled(false);
        btnTarefas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTarefasActionPerformed(evt);
            }
        });
        getContentPane().add(btnTarefas);
        btnTarefas.setBounds(0, 420, 80, 50);

        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/client-icon.png"))); // NOI18N
        btnClientes.setContentAreaFilled(false);
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        getContentPane().add(btnClientes);
        btnClientes.setBounds(5, 260, 70, 50);

        btnRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/relatory-icon.png"))); // NOI18N
        btnRelatorios.setContentAreaFilled(false);
        btnRelatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatoriosActionPerformed(evt);
            }
        });
        getContentPane().add(btnRelatorios);
        btnRelatorios.setBounds(5, 340, 70, 50);

        btnCalendario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/event-icon.png"))); // NOI18N
        btnCalendario.setContentAreaFilled(false);
        btnCalendario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalendarioActionPerformed(evt);
            }
        });
        getContentPane().add(btnCalendario);
        btnCalendario.setBounds(5, 184, 70, 50);

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home-icon.png"))); // NOI18N
        btnHome.setContentAreaFilled(false);
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        getContentPane().add(btnHome);
        btnHome.setBounds(0, 100, 80, 50);

        JPanelTelaAcesso.setBackground(new java.awt.Color(194, 166, 40));
        getContentPane().add(JPanelTelaAcesso);
        JPanelTelaAcesso.setBounds(0, 175, 80, 70);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/azul.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 640, 80, 60);

        jlibLogo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        getContentPane().add(jlibLogo2);
        jlibLogo2.setBounds(4, 10, 60, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/azul.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, -50, 80, 750);

        lblUserIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minhatura-de-perfil.png"))); // NOI18N
        getContentPane().add(lblUserIcon);
        lblUserIcon.setBounds(1210, 15, 50, 50);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo-semfundo.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 290, 70);

        btnNotificacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/alert-bell.png"))); // NOI18N
        btnNotificacoes.setContentAreaFilled(false);
        getContentPane().add(btnNotificacoes);
        btnNotificacoes.setBounds(1160, 10, 60, 60);

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background-contabil.png"))); // NOI18N
        getContentPane().add(Background);
        Background.setBounds(0, 0, 1280, 711);

        setSize(new java.awt.Dimension(1294, 708));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdministracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministracaoActionPerformed
        new TelaAdminTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAdministracaoActionPerformed

    private void btnConfiguracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracoesActionPerformed
        new TelaConfiguracao(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnConfiguracoesActionPerformed

    private void btnTarefasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTarefasActionPerformed
        new TelaTarefaTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTarefasActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        new TelaClienteTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatoriosActionPerformed
        new TelaRelatorioTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRelatoriosActionPerformed

    private void btnCalendarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalendarioActionPerformed
        new TelaEventoTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCalendarioActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        new TelaMenu(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeActionPerformed

    private void lblCadastrarEventoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEventoKeyPressed

    }//GEN-LAST:event_lblCadastrarEventoKeyPressed

    private void btnCadastrarEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEventoActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEventoActionPerformed

    private void btnCadastrarEvento2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento2ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento2ActionPerformed

    private void lblCadastrarEvento2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento2KeyPressed

    private void btnCadastrarEvento3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento3ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento3ActionPerformed

    private void lblCadastrarEvento3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento3KeyPressed

    }//GEN-LAST:event_lblCadastrarEvento3KeyPressed

    private void btnCadastrarEvento4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento4ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento4ActionPerformed

    private void lblCadastrarEvento4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento4KeyPressed

    private void btnCadastrarEvento5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento5ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento5ActionPerformed

    private void lblCadastrarEvento5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento5KeyPressed

    private void btnCadastrarEvento6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento6ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento6ActionPerformed

    private void lblCadastrarEvento6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento6KeyPressed

    private void btnCadastrarEvento7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento7ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento7ActionPerformed

    private void btnCadastrarEvento8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento8ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento8ActionPerformed

    private void btnCadastrarEvento9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento9ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento9ActionPerformed

    private void btnCadastrarEvento10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento10ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento10ActionPerformed

    private void btnCadastrarEvento11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento11ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento11ActionPerformed

    private void lblCadastrarEvento11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento11KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento11KeyPressed

    private void btnCadastrarEvento12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento12ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento12ActionPerformed

    private void lblCadastrarEvento12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento12KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento12KeyPressed

    private void btnCadastrarEvento13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento13ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento13ActionPerformed

    private void lblCadastrarEvento13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento13KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento13KeyPressed

    private void btnCadastrarEvento14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento14ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento14ActionPerformed

    private void lblCadastrarEvento14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento14KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento14KeyPressed

    private void btnCadastrarEvento15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento15ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento15ActionPerformed

    private void lblCadastrarEvento15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento15KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento15KeyPressed

    private void btnCadastrarEvento17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento17ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento17ActionPerformed

    private void lblCadastrarEvento17KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento17KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento17KeyPressed

    private void btnCadastrarEvento18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento18ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento18ActionPerformed

    private void lblCadastrarEvento18KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento18KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento18KeyPressed

    private void btnCadastrarEvento19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento19ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento19ActionPerformed

    private void lblCadastrarEvento19KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento19KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento19KeyPressed

    private void btnCadastrarEvento20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento20ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento20ActionPerformed

    private void lblCadastrarEvento20KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento20KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento20KeyPressed

    private void btnCadastrarEvento22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento22ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento22ActionPerformed

    private void lblCadastrarEvento22KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento22KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento22KeyPressed

    private void btnCadastrarEvento23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento23ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento23ActionPerformed

    private void lblCadastrarEvento23KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento23KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento23KeyPressed

    private void btnCadastrarEvento24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento24ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento24ActionPerformed

    private void lblCadastrarEvento24KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento24KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento24KeyPressed

    private void btnCadastrarEvento25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento25ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento25ActionPerformed

    private void lblCadastrarEvento25KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento25KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento25KeyPressed

    private void btnCadastrarEvento27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento27ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento27ActionPerformed

    private void lblCadastrarEvento27KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento27KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento27KeyPressed

    private void btnCadastrarEvento28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento28ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento28ActionPerformed

    private void lblCadastrarEvento28KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento28KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento28KeyPressed

    private void btnCadastrarEvento29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento29ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento29ActionPerformed

    private void lblCadastrarEvento29KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento29KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento29KeyPressed

    private void btnCadastrarEvento30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento30ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento30ActionPerformed

    private void lblCadastrarEvento30KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento30KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento30KeyPressed

    private void btnCadastrarEvento32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento32ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento32ActionPerformed

    private void lblCadastrarEvento32KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento32KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento32KeyPressed

    private void btnCadastrarEvento33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento33ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento33ActionPerformed

    private void lblCadastrarEvento33KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento33KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento33KeyPressed

    private void btnCadastrarEvento34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento34ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento34ActionPerformed

    private void lblCadastrarEvento34KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento34KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento34KeyPressed

    private void btnCadastrarEvento35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento35ActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarEvento35ActionPerformed

    private void lblCadastrarEvento35KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento35KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento35KeyPressed

    private void btnCadastrarEvento16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCadastrarEvento16ActionPerformed

    private void btnCadastrarEvento21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCadastrarEvento21ActionPerformed

    private void lblCadastrarEvento26KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento26KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento26KeyPressed

    private void btnCadastrarEvento26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCadastrarEvento26ActionPerformed

    private void lblCadastrarEvento31KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento31KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento31KeyPressed

    private void btnCadastrarEvento31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEvento31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCadastrarEvento31ActionPerformed

    private void lblCadastrarEvento21KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento21KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento21KeyPressed

    private void lblCadastrarEvento16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCadastrarEvento16KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCadastrarEvento16KeyPressed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaEventoTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaEventoTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaEventoTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEventoTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JPanel JPanelTelaAcesso;
    private javax.swing.JButton btnAdministracao;
    private javax.swing.JButton btnCadastrarEvento;
    private javax.swing.JButton btnCadastrarEvento10;
    private javax.swing.JButton btnCadastrarEvento11;
    private javax.swing.JButton btnCadastrarEvento12;
    private javax.swing.JButton btnCadastrarEvento13;
    private javax.swing.JButton btnCadastrarEvento14;
    private javax.swing.JButton btnCadastrarEvento15;
    private javax.swing.JButton btnCadastrarEvento16;
    private javax.swing.JButton btnCadastrarEvento17;
    private javax.swing.JButton btnCadastrarEvento18;
    private javax.swing.JButton btnCadastrarEvento19;
    private javax.swing.JButton btnCadastrarEvento2;
    private javax.swing.JButton btnCadastrarEvento20;
    private javax.swing.JButton btnCadastrarEvento21;
    private javax.swing.JButton btnCadastrarEvento22;
    private javax.swing.JButton btnCadastrarEvento23;
    private javax.swing.JButton btnCadastrarEvento24;
    private javax.swing.JButton btnCadastrarEvento25;
    private javax.swing.JButton btnCadastrarEvento26;
    private javax.swing.JButton btnCadastrarEvento27;
    private javax.swing.JButton btnCadastrarEvento28;
    private javax.swing.JButton btnCadastrarEvento29;
    private javax.swing.JButton btnCadastrarEvento3;
    private javax.swing.JButton btnCadastrarEvento30;
    private javax.swing.JButton btnCadastrarEvento31;
    private javax.swing.JButton btnCadastrarEvento32;
    private javax.swing.JButton btnCadastrarEvento33;
    private javax.swing.JButton btnCadastrarEvento34;
    private javax.swing.JButton btnCadastrarEvento35;
    private javax.swing.JButton btnCadastrarEvento4;
    private javax.swing.JButton btnCadastrarEvento5;
    private javax.swing.JButton btnCadastrarEvento6;
    private javax.swing.JButton btnCadastrarEvento7;
    private javax.swing.JButton btnCadastrarEvento8;
    private javax.swing.JButton btnCadastrarEvento9;
    private javax.swing.JButton btnCalendario;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracoes;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnNotificacoes;
    private javax.swing.JButton btnRelatorios;
    private javax.swing.JButton btnTarefas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jlibCadastrarNovo;
    private javax.swing.JLabel jlibLogo2;
    private javax.swing.JLabel lblCadastrarEvento;
    private javax.swing.JLabel lblCadastrarEvento11;
    private javax.swing.JLabel lblCadastrarEvento12;
    private javax.swing.JLabel lblCadastrarEvento13;
    private javax.swing.JLabel lblCadastrarEvento14;
    private javax.swing.JLabel lblCadastrarEvento15;
    private javax.swing.JLabel lblCadastrarEvento16;
    private javax.swing.JLabel lblCadastrarEvento17;
    private javax.swing.JLabel lblCadastrarEvento18;
    private javax.swing.JLabel lblCadastrarEvento19;
    private javax.swing.JLabel lblCadastrarEvento2;
    private javax.swing.JLabel lblCadastrarEvento20;
    private javax.swing.JLabel lblCadastrarEvento21;
    private javax.swing.JLabel lblCadastrarEvento22;
    private javax.swing.JLabel lblCadastrarEvento23;
    private javax.swing.JLabel lblCadastrarEvento24;
    private javax.swing.JLabel lblCadastrarEvento25;
    private javax.swing.JLabel lblCadastrarEvento26;
    private javax.swing.JLabel lblCadastrarEvento27;
    private javax.swing.JLabel lblCadastrarEvento28;
    private javax.swing.JLabel lblCadastrarEvento29;
    private javax.swing.JLabel lblCadastrarEvento3;
    private javax.swing.JLabel lblCadastrarEvento30;
    private javax.swing.JLabel lblCadastrarEvento31;
    private javax.swing.JLabel lblCadastrarEvento32;
    private javax.swing.JLabel lblCadastrarEvento33;
    private javax.swing.JLabel lblCadastrarEvento34;
    private javax.swing.JLabel lblCadastrarEvento35;
    private javax.swing.JLabel lblCadastrarEvento4;
    private javax.swing.JLabel lblCadastrarEvento5;
    private javax.swing.JLabel lblCadastrarEvento6;
    private javax.swing.JLabel lblDataDeCadastro;
    private javax.swing.JLabel lblDataDeCadastro1;
    private javax.swing.JLabel lblDataDeCadastro10;
    private javax.swing.JLabel lblDataDeCadastro11;
    private javax.swing.JLabel lblDataDeCadastro12;
    private javax.swing.JLabel lblDataDeCadastro13;
    private javax.swing.JLabel lblDataDeCadastro14;
    private javax.swing.JLabel lblDataDeCadastro15;
    private javax.swing.JLabel lblDataDeCadastro16;
    private javax.swing.JLabel lblDataDeCadastro17;
    private javax.swing.JLabel lblDataDeCadastro18;
    private javax.swing.JLabel lblDataDeCadastro19;
    private javax.swing.JLabel lblDataDeCadastro2;
    private javax.swing.JLabel lblDataDeCadastro20;
    private javax.swing.JLabel lblDataDeCadastro21;
    private javax.swing.JLabel lblDataDeCadastro23;
    private javax.swing.JLabel lblDataDeCadastro24;
    private javax.swing.JLabel lblDataDeCadastro25;
    private javax.swing.JLabel lblDataDeCadastro26;
    private javax.swing.JLabel lblDataDeCadastro28;
    private javax.swing.JLabel lblDataDeCadastro29;
    private javax.swing.JLabel lblDataDeCadastro3;
    private javax.swing.JLabel lblDataDeCadastro30;
    private javax.swing.JLabel lblDataDeCadastro31;
    private javax.swing.JLabel lblDataDeCadastro33;
    private javax.swing.JLabel lblDataDeCadastro34;
    private javax.swing.JLabel lblDataDeCadastro35;
    private javax.swing.JLabel lblDataDeCadastro36;
    private javax.swing.JLabel lblDataDeCadastro38;
    private javax.swing.JLabel lblDataDeCadastro39;
    private javax.swing.JLabel lblDataDeCadastro4;
    private javax.swing.JLabel lblDataDeCadastro40;
    private javax.swing.JLabel lblDataDeCadastro41;
    private javax.swing.JLabel lblDataDeCadastro5;
    private javax.swing.JLabel lblDataDeCadastro6;
    private javax.swing.JLabel lblDataDeCadastro7;
    private javax.swing.JLabel lblDataDeCadastro8;
    private javax.swing.JLabel lblDataDeCadastro9;
    private javax.swing.JLabel lblUserIcon;
    // End of variables declaration//GEN-END:variables
}
