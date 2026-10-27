package screen;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdminItemPanel extends JPanel {

    private int id;
    private String nome;
    private String email;
    private String permissao;
    private String dataCadastro;
    private boolean selecionado = false;
    private final Admin parent;
    private final Data.Usuario usuario;

    private String traduzirPermissao(String valor) {
        if (valor == null) {
            return "";
        }
        String lang = Data.I18nManager.getCurrentLocale().getLanguage();

        switch (valor.trim().toUpperCase()) {
            case "SUPER_ADM":
                switch (lang) {
                    case "en":
                        return "Super Administrator";
                    case "es":
                        return "Superadministrador";
                    case "de":
                        return "Super-Administrator";
                    case "fr":
                        return "Super administrateur";
                    case "it":
                        return "Super amministratore";
                    case "ja":
                        return "スーパー管理者";
                    case "ko":
                        return "최고 관리자";
                    case "zh":
                        return "超级管理员";
                    default:
                        return "Super Administrador";
                }
            case "ADM":
                switch (lang) {
                    case "en":
                        return "Administrator";
                    case "es":
                        return "Administrador";
                    case "de":
                        return "Administrator";
                    case "fr":
                        return "Administrateur";
                    case "it":
                        return "Amministratore";
                    case "ja":
                        return "管理者";
                    case "ko":
                        return "관리자";
                    case "zh":
                        return "管理员";
                    default:
                        return "Administrador";
                }
            case "NULL":
                switch (lang) {
                    case "en":
                        return "User";
                    case "es":
                        return "Usuario";
                    case "de":
                        return "Benutzer";
                    case "fr":
                        return "Utilisateur";
                    case "it":
                        return "Utente";
                    case "ja":
                        return "ユーザー";
                    case "ko":
                        return "사용자";
                    case "zh":
                        return "用户";
                    default:
                        return "Usuário";
                }
            default:
                return valor;
        }
    }

    private String formatarData(Object data) {
        if (data == null) {
            return "";
        }
        if (data instanceof java.util.Date) {
            return new java.text.SimpleDateFormat("dd/MM/yyyy").format((java.util.Date) data);
        }
        String d = data.toString().trim();
        if (d.matches("\\d{4}-\\d{2}-\\d{2}.*")) {
            return d.substring(8, 10) + "/" + d.substring(5, 7) + "/" + d.substring(0, 4);
        }
        return d.replace('-', '/');
    }

    private String ajustarTexto(String texto, FontMetrics fm, int larguraMax) {
        if (texto == null) {
            return "";
        }
        if (fm.stringWidth(texto) <= larguraMax) {
            return texto;
        }
        String reticencias = "...";
        int fim = texto.length();
        while (fim > 0 && fm.stringWidth(texto.substring(0, fim) + reticencias) > larguraMax) {
            fim--;
        }
        return texto.substring(0, fim) + reticencias;
    }

    public AdminItemPanel(Data.Usuario u, Admin parent) {
        this.usuario = u;
        this.id = u.getId();
        this.nome = u.getUsuario();
        this.email = u.getEmail();
        this.permissao = traduzirPermissao(u.getPermissao());
        this.dataCadastro = formatarData(u.getCreatedAt());
        this.parent = parent;

        setPreferredSize(new Dimension(976, 50));
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parent.selecionarItem(AdminItemPanel.this);
                parent.abrirUsuario(usuario);
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
        int relX_Email = 607 - 276;
        int relX_Permissao = 827 - 276;
        int relX_Data = 1040 - 276;

        g2.drawString(String.valueOf(id), relX_ID, centerY);
        g2.drawString(ajustarTexto(nome, fm, 607 - 362 - 15), relX_Nome, centerY);
        g2.drawString(ajustarTexto(email, fm, 827 - 607 - 15), relX_Email, centerY);
        g2.drawString(ajustarTexto(permissao, fm, 1040 - 827 - 15), relX_Permissao, centerY);
        g2.drawString(dataCadastro, relX_Data, centerY);

        g2.dispose();
    }
}
