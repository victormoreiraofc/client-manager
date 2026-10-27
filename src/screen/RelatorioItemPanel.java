package screen;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RelatorioItemPanel extends JPanel {

    private int id;
    private String nome;
    private String status;
    private String dataCadastro;
    private String statusTraduzido;
    private boolean selecionado = false;
    private final Report parent;
    private final Data.Relatorio relatorio;

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
            default:
                return valor;
        }
    }

    private String formatarData(String data) {
        if (data == null) {
            return "";
        }
        String d = data.trim();
        if (d.matches("\\d{4}-\\d{2}-\\d{2}.*")) {
            return d.substring(8, 10) + "/" + d.substring(5, 7) + "/" + d.substring(0, 4);
        }
        return d.replace('-', '/');
    }

    public RelatorioItemPanel(Data.Relatorio r, Report parent) {
        this.relatorio = r;
        this.id = r.getId();
        this.nome = r.getNomeRelatorio();
        this.status = r.getStatusRelatorio();
        this.dataCadastro = formatarData(r.getDataCadastro());
        this.parent = parent;

        this.statusTraduzido = traduzirValor(this.status);

        setPreferredSize(new Dimension(976, 50));
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parent.selecionarItem(RelatorioItemPanel.this);
                parent.abrirRelatorio(relatorio);
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

        g2.setColor(new Color(28, 37, 54));
        g2.fillRoundRect(0, 0, 976, 50, 15, 15);

        if (selecionado) {
            g2.setColor(new Color(205, 168, 12));
            g2.setStroke(new BasicStroke(1));
            g2.drawRoundRect(1, 1, 974, 48, 15, 15);
        }

        g2.setFont(FonteUtils.carregarRobotoRegular(12f));
        g2.setColor(new Color(209, 213, 219));
        FontMetrics fm = g2.getFontMetrics();
        int centerY = (getHeight() + fm.getAscent()) / 2 - 2;

        int relX_ID = 288 - 276;
        int relX_Nome = 362 - 276;
        int relX_Status = 820 - 276 - 5;
        int relX_Data = 1040 - 276;

        g2.drawString(String.valueOf(id), relX_ID, centerY);
        g2.drawString(nome, relX_Nome, centerY);

        desenharBadgeStatus(g2, fm, relX_Status);

        g2.drawString(dataCadastro, relX_Data, centerY);

        g2.dispose();
    }

    private void desenharBadgeStatus(Graphics2D g2, FontMetrics fm, int x) {
        String texto = statusTraduzido;
        Color corFundo;

        if ("Concluido".equalsIgnoreCase(status) || "Finalizado".equalsIgnoreCase(status)) {
            corFundo = new Color(74, 222, 128, 76);
            texto = traduzirValor("Concluido");
        } else if ("Em andamento".equalsIgnoreCase(status)) {
            corFundo = new Color(250, 204, 21, 76);
        } else {
            corFundo = new Color(248, 113, 113, 76);
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
