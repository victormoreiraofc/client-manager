package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class CTCONTAB {

    static Connection conectado;

    private static Connection conectar() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conectado = DriverManager.getConnection("jdbc:mysql://junction.proxy.rlwy.net:45791/railway", "root", "TVJqYFGfMCfJLRXAOZNrYECLNsoZxGaR");
        return conectado;
    }

    public static Usuario fazerLoginU(String u, String s) throws ClassNotFoundException, SQLException {
        conectado = conectar();
        PreparedStatement st = conectado.prepareStatement("SELECT id, usuario, email FROM usuarios WHERE (usuario = ? OR email = ?) AND senha = ?");
        st.setString(1, u);
        st.setString(2, u);
        st.setString(3, s);
        ResultSet resultado = st.executeQuery();

        Usuario usuario = null;
        if (resultado.next()) {

            usuario = new Usuario();
            usuario.setId(resultado.getInt("id"));
            usuario.setUsuario(resultado.getString("usuario"));
            usuario.setEmail(resultado.getString("email"));
        }

        return usuario; // Retorna o objeto Usuario ou null se não encontrado
    }

    public static void registrarUsuario(String u, String e, String s) throws ClassNotFoundException, SQLException {
        conectado = conectar();
        PreparedStatement st = conectado.prepareStatement("INSERT INTO usuarios (usuario, email, senha) VALUES(?,?,?)");
        st.setString(1, u);
        st.setString(2, e);
        st.setString(3, s);
        st.executeUpdate();
    }

    public static void editarDados(String email, String senha, Usuario usuario) throws ClassNotFoundException, SQLException {
        conectado = conectar();
        PreparedStatement st = conectado.prepareStatement("UPDATE usuarios SET email = ?, senha = ? WHERE id = ?");
        st.setString(1, email);
        st.setString(2, senha);
        st.setInt(3, usuario.getId());  // Usa setInt para o id
        st.executeUpdate();
    }

    public static int clienteTotalRegis() throws ClassNotFoundException, SQLException {
        conectado = conectar();
        PreparedStatement st = conectado.prepareStatement("SELECT COUNT(*) AS total FROM cliente");
        ResultSet resultado = st.executeQuery();

        int total = 0;
        if (resultado.next()) {
            total = resultado.getInt("total");
        }

        return total;
    }

    public static int tarefaPendentes() throws ClassNotFoundException, SQLException {
        conectado = conectar();
        PreparedStatement st = conectado.prepareStatement("SELECT COUNT(*) AS pendentes FROM tarefa WHERE StatusTarefa = 'pendente'");
        ResultSet resultado = st.executeQuery();

        int pendente = 0;
        if (resultado.next()) {
            pendente = resultado.getInt("pendentes");
        }

        return pendente;
    }

    public static int serviçosNaoRealizados() throws ClassNotFoundException, SQLException {
        conectado = conectar();
        PreparedStatement st = conectado.prepareStatement("SELECT COUNT(*) AS andamento FROM tarefa WHERE StatusTarefa = 'em andamento'");
        ResultSet resultado = st.executeQuery();

        int andamento = 0;
        if (resultado.next()) {
            andamento = resultado.getInt("andamento");
        }

        return andamento;
    }

    public static int serviçosRealizados() throws ClassNotFoundException, SQLException {
        conectado = conectar();
        PreparedStatement st = conectado.prepareStatement("SELECT COUNT(*) AS concluido FROM tarefa WHERE StatusTarefa = 'concluido'");
        ResultSet resultado = st.executeQuery();

        int concluido = 0;
        if (resultado.next()) {
            concluido = resultado.getInt("concluido");
        }

        return concluido;
    }

    public static int totalRelatorios() throws ClassNotFoundException, SQLException {
        conectado = conectar();
        PreparedStatement st = conectado.prepareStatement("SELECT COUNT(*) AS total FROM relatorio");
        ResultSet resultado = st.executeQuery();

        int total = 0;
        if (resultado.next()) {
            total = resultado.getInt("total");
        }

        return total;
    }

    public static int novosclientesdomes() throws ClassNotFoundException, SQLException {
        conectado = conectar();
        PreparedStatement st = conectado.prepareStatement("SELECT COUNT(*) AS total FROM cliente");
        ResultSet resultado = st.executeQuery();

        int totalClientes = 0;
        if (resultado.next()) {
            totalClientes = resultado.getInt("total");

        }

        return totalClientes;
    }

    public static void registrarEvento(String evento, String dataInicio, String dataFinal, String horarioInicial, String horarioFinal, String nomeUsuario) throws ClassNotFoundException, SQLException {
        conectado = conectar();
        PreparedStatement st = conectado.prepareStatement(
                "INSERT INTO agenda (Evento, DataInicio, DataFinal, HorarioInicial, HorarioFinal, usuario) VALUES (?, ?, ?, ?, ?, ?)"
        );
        st.setString(1, evento);
        st.setString(2, dataInicio);
        st.setString(3, dataFinal);
        st.setString(4, horarioInicial);
        st.setString(5, horarioFinal);
        st.setString(6, nomeUsuario); // Associa o evento ao usuário logado
        st.executeUpdate();
    }

    public static void registrarCliente(String dataCadastro, String nome, String tipoPessoa, String email, String servico, String situacaoServico, String celular, String telefone, String observacoes, String nomeUsuario) throws ClassNotFoundException, SQLException {
        conectado = conectar();
        PreparedStatement st = conectado.prepareStatement(
                "INSERT INTO cliente (DataCadastro, Nome, TipoPessoa, Email, Servico, SituacaoServico, Celular, Telefone, Observacoes, usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
        );
        st.setString(1, dataCadastro);
        st.setString(2, nome);
        st.setString(3, tipoPessoa);
        st.setString(4, email);
        st.setString(5, servico);
        st.setString(6, situacaoServico);
        st.setString(7, celular);
        st.setString(8, telefone);
        st.setString(9, observacoes);
        st.setString(10, nomeUsuario); // Associa o evento ao usuário logado
        st.executeUpdate();
    }

}
