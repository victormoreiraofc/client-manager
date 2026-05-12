package screen;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ClienteItemPanel extends JPanel {

    private int id;
    private String nome;
    private String status;
    private String tipoPessoa;
    private String dataCadastro;
    private String statusTraduzido;
    private String tipoPessoaTraduzido;
    private boolean selecionado = false;
    private final Client parent;

    private String traduzirValor(String valor) {
        if (valor == null) {
            return "";
        }
        String lang = Data.I18nManager.getCurrentLocale().getLanguage();

        switch (valor) {
            case "Pendente":
                switch (lang) {
                    case "en":
                        return "Pending";
                    case "es":
                        return "Pendiente";
                    case "de":
                        return "Ausstehend";
                    case "fr":
                        return "En attente";
                    case "it":
                        return "In attesa";
                    case "ja":
                        return "保留中";
                    case "ko":
                        return "대기 중";
                    case "zh":
                        return "待处理";
                    default:
                        return "Pendente";
                }
            case "Em andamento":
                switch (lang) {
                    case "en":
                        return "In Progress";
                    case "es":
                        return "En progreso";
                    case "de":
                        return "In Bearbeitung";
                    case "fr":
                        return "En cours";
                    case "it":
                        return "In corso";
                    case "ja":
                        return "進行中";
                    case "ko":
                        return "진행 중";
                    case "zh":
                        return "进行中";
                    default:
                        return "Em andamento";
                }
            case "Concluido":
                switch (lang) {
                    case "en":
                        return "Completed";
                    case "es":
                        return "Completado";
                    case "de":
                        return "Abgeschlossen";
                    case "fr":
                        return "Terminé";
                    case "it":
                        return "Completato";
                    case "ja":
                        return "完了";
                    case "ko":
                        return "완료";
                    case "zh":
                        return "已完成";
                    default:
                        return "Concluído";
                }
            case "Fisica":
                switch (lang) {
                    case "en":
                        return "Natural";
                    case "es":
                        return "Física";
                    case "de":
                        return "Natürliche";
                    case "fr":
                        return "Physique";
                    case "it":
                        return "Fisica";
                    case "ja":
                        return "個人";
                    case "ko":
                        return "개인";
                    case "zh":
                        return "自然人";
                    default:
                        return "Física";
                }
            case "Juridica":
                switch (lang) {
                    case "en":
                        return "Legal Entity";
                    case "es":
                        return "Jurídica";
                    case "de":
                        return "Juristische Person";
                    case "fr":
                        return "Juridique";
                    case "it":
                        return "Giuridica";
                    case "ja":
                        return "法人";
                    case "ko":
                        return "법인";
                    case "zh":
                        return "法人";
                    default:
                        return "Jurídica";
                }
            case "NI":
                switch (lang) {
                    case "en":
                        return "Not Informed";
                    case "es":
                        return "No Informado";
                    case "de":
                        return "Nicht angegeben";
                    case "fr":
                        return "Non renseigné";
                    case "it":
                        return "Giuridica";
                    case "ja":
                        return "未指定";
                    case "ko":
                        return "미지정";
                    case "zh":
                        return "未告知";
                    default:
                        return "Jurídica";
                }
            default:
                return valor;
        }
    }

    public ClienteItemPanel(Data.Cliente c, Client parent) {
        this.id = c.getId();
        this.nome = c.getNome();
        this.status = c.getSituacaoServico();
        this.tipoPessoa = c.getTipoPessoa();
        this.dataCadastro = c.getDataCadastro();
        this.parent = parent;

        this.statusTraduzido = traduzirValor(this.status);
        this.tipoPessoaTraduzido = traduzirValor(this.tipoPessoa);

        setPreferredSize(new Dimension(976, 50));
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parent.selecionarItem(ClienteItemPanel.this);
                System.out.println("botão de ID " + id);
            }
        });
    }

    public void setSelecionado(boolean sel) {
        this.selecionado = sel;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 1. Desenhar o Fundo do Retângulo (#1C2536)
        g2.setColor(new Color(28, 37, 54));
        g2.fillRoundRect(0, 0, 976, 50, 15, 15);

        // 2. Borda de Seleção (#CDA80C)
        if (selecionado) {
            g2.setColor(new Color(205, 168, 12));
            g2.setStroke(new BasicStroke(1));
            g2.drawRoundRect(1, 1, 974, 48, 15, 15);
        }

        // 3. Configuração da Fonte (Roboto 12px)
        // Nota: Certifique-se que o método carregarRoboto existe em FonteUtils
        g2.setFont(FonteUtils.carregarRobotoRegular(12f));
        g2.setColor(new Color(209, 213, 219));
        FontMetrics fm = g2.getFontMetrics();
        int centerY = (getHeight() + fm.getAscent()) / 2 - 2;

        // Coordenadas relativas (X informado - X do ScrollPane [276])
        int relX_ID = 288 - 276;
        int relX_Nome = 362 - 276;
        int relX_Status = 657 - 276;
        int relX_Tipo = 827 - 276;
        int relX_Data = 1040 - 276;

        // ID e Nome
        g2.drawString(String.valueOf(id), relX_ID, centerY);
        g2.drawString(nome, relX_Nome, centerY);

        // Badge de Status
        desenharBadgeStatus(g2, fm, relX_Status);

        // Tipo e Data
        g2.drawString(tipoPessoaTraduzido, relX_Tipo, centerY);
        g2.drawString(dataCadastro, relX_Data, centerY);

        g2.dispose();
    }

    private void desenharBadgeStatus(Graphics2D g2, FontMetrics fm, int x) {
        String texto = statusTraduzido; // Usa o texto já traduzido
        Color corFundo;

        // A lógica de decisão de cor continua baseada no valor original do BD (em PT)
        if ("Concluido".equalsIgnoreCase(status) || "Finalizado".equalsIgnoreCase(status)) {
            corFundo = new Color(74, 222, 128, 76); // Verde
            // Se quiser garantir que "Finalizado" também seja traduzido como "Concluido":
            texto = traduzirValor("Concluido");
        } else if ("Em andamento".equalsIgnoreCase(status)) {
            corFundo = new Color(250, 204, 21, 76); // Amarelo
        } else {
            corFundo = new Color(248, 113, 113, 76); // Vermelho/Pendente
        }

        int textWidth = fm.stringWidth(texto);
        int badgeW = textWidth + 10;
        int badgeH = 20;
        int badgeY = (getHeight() - badgeH) / 2;

        g2.setColor(corFundo);
        g2.fillRoundRect(x, badgeY, badgeW, badgeH, 8, 8);
        g2.setColor(new Color(209, 213, 219));
        g2.drawString(texto, x + 5, (getHeight() + fm.getAscent()) / 2 - 2);
    }
}
