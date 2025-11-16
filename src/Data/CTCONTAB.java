package Data;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class CTCONTAB {

    static Connection conectado;

    private static Connection conectar() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conectado = DriverManager.getConnection("jdbc:mysql://localhost:3306/ctcontab", "root", "1234");
        return conectado;
    }

    public static String sanitizeInput(String input) {
        return input.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }

    public static Usuario fazerLoginU(String u, String s) throws ClassNotFoundException, SQLException {
        conectado = conectar();
        PreparedStatement st = conectado.prepareStatement("SELECT id, usuario, email, Permissao, Imagem FROM usuarios WHERE (usuario = ? OR email = ?) AND senha = ?");
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
            usuario.setPermissao(resultado.getString("Permissao"));
            byte[] imagemBytes = resultado.getBytes("Imagem");
            usuario.setImagem(imagemBytes);
        }

        return usuario;
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
        st.setInt(3, usuario.getId());
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
        PreparedStatement st = conectado.prepareStatement("SELECT COUNT(*) AS total FROM cliente WHERE MONTH(DataCadastro) = MONTH(CURRENT_DATE()) AND YEAR(DataCadastro) = YEAR(CURRENT_DATE())");
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
        st.setString(6, nomeUsuario);
        st.executeUpdate();
    }

    public static void registrarCliente(Cliente cliente) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO cliente (Nome, TipoPessoa, SituacaoServico, Servico, Telefone, Email, Celular, Observacoes, DataCadastro, Usuario) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        conectado = conectar();
        PreparedStatement st = conectado.prepareStatement(sql);
        st.setString(1, cliente.getNome());
        st.setString(2, cliente.getTipoPessoa());
        st.setString(3, cliente.getSituacaoServico());
        st.setString(4, cliente.getServico());
        st.setString(5, cliente.getTelefone());
        st.setString(6, cliente.getEmail());
        st.setString(7, cliente.getCelular());
        st.setString(8, cliente.getObservacoes());
        st.setString(9, cliente.getDataCadastro());
        st.setString(10, cliente.getUsuario());

        st.executeUpdate();
    }

    public static void registrarTarefa(Tarefa tarefa) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO tarefa (NomeTarefa, Descrição, StatusTarefa, DataVencimento, Prioridade, responsavel) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        conectado = conectar();
        PreparedStatement st = conectado.prepareStatement(sql);
        st.setString(1, tarefa.getNomeTarefa());
        st.setString(2, tarefa.getDescricao());
        st.setString(3, tarefa.getStatusTarefa());
        st.setString(4, tarefa.getDataVencimento());
        st.setString(5, tarefa.getPrioridade());
        st.setString(6, tarefa.getResponsavel());

        st.executeUpdate();
    }

    public static void registrarRelatorio(Relatorio relatorio) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO relatorio (NomeRelatorio, Descrição, StatusRelatorio, DataCadastro) "
                + "VALUES (?, ?, ?, ?)";

        conectado = conectar();
        PreparedStatement st = conectado.prepareStatement(sql);
        st.setString(1, relatorio.getNomeRelatorio());
        st.setString(2, relatorio.getDescricao());
        st.setString(3, relatorio.getStatusRelatorio());
        st.setString(4, relatorio.getDataCadastro());

        st.executeUpdate();
    }

    public static List<Cliente> listarClientes() throws ClassNotFoundException, SQLException {
        List<Cliente> clientes = new ArrayList<>();
        conectado = conectar();
        String query = "SELECT ID, Nome, TipoPessoa, Email, Servico, SituacaoServico, Celular, Telefone, Observacoes, DataCadastro, Usuario FROM cliente";
        PreparedStatement st = conectado.prepareStatement(query);
        ResultSet resultado = st.executeQuery();

        while (resultado.next()) {
            Cliente cliente = new Cliente(
                    resultado.getInt("ID"),
                    resultado.getString("Nome"),
                    resultado.getString("TipoPessoa"),
                    resultado.getString("Email"),
                    resultado.getString("Servico"),
                    resultado.getString("SituacaoServico"),
                    resultado.getString("Celular"),
                    resultado.getString("Telefone"),
                    resultado.getString("Observacoes"),
                    resultado.getString("DataCadastro"),
                    resultado.getString("Usuario")
            );
            clientes.add(cliente);
        }

        return clientes;
    }

    public static List<Relatorio> listarRelatorios() throws ClassNotFoundException, SQLException {
        List<Relatorio> relatorios = new ArrayList<>();
        conectado = conectar();
        String query = "SELECT ID, NomeRelatorio, Descrição, StatusRelatorio, DataCadastro FROM relatorio";
        PreparedStatement st = conectado.prepareStatement(query);
        ResultSet resultado = st.executeQuery();

        while (resultado.next()) {
            Relatorio relatorio = new Relatorio(
                    resultado.getInt("ID"),
                    resultado.getString("NomeRelatorio"),
                    resultado.getString("Descrição"),
                    resultado.getString("StatusRelatorio"),
                    resultado.getString("DataCadastro")
            );
            relatorios.add(relatorio);
        }

        return relatorios;
    }

    public static List<Tarefa> listarTarefas() throws ClassNotFoundException, SQLException {
        List<Tarefa> tarefas = new ArrayList<>();
        conectado = conectar();
        String query = "SELECT ID, NomeTarefa, Descrição, StatusTarefa, DataVencimento, Prioridade, responsavel FROM tarefa";
        PreparedStatement st = conectado.prepareStatement(query);
        ResultSet resultado = st.executeQuery();

        while (resultado.next()) {
            Tarefa tarefa = new Tarefa(
                    resultado.getInt("ID"),
                    resultado.getString("NomeTarefa"),
                    resultado.getString("Descrição"),
                    resultado.getString("StatusTarefa"),
                    resultado.getString("DataVencimento"),
                    resultado.getString("Prioridade"),
                    resultado.getString("responsavel")
            );
            tarefas.add(tarefa);
        }

        return tarefas;
    }

    public static List<Funcionario> listarFuncionarios() throws ClassNotFoundException, SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        conectado = conectar();
        String query = "SELECT id, usuario, email, senha, Imagem, Permissao, created_at FROM usuarios";
        PreparedStatement st = conectado.prepareStatement(query);
        ResultSet resultado = st.executeQuery();

        while (resultado.next()) {
            Funcionario funcionario = new Funcionario(
                    resultado.getInt("id"),
                    resultado.getString("usuario"),
                    resultado.getString("email"),
                    resultado.getString("senha"),
                    resultado.getBytes("Imagem"),
                    resultado.getString("Permissao"),
                    resultado.getTimestamp("created_at")
            );
            funcionarios.add(funcionario);
        }

        return funcionarios;
    }

    public static void excluirRegistro(String tabela, String campoIdentificador, int id) throws ClassNotFoundException, SQLException {
        conectado = conectar();
        String sql = "DELETE FROM " + tabela + " WHERE " + campoIdentificador + " = ?";

        try (PreparedStatement st = conectado.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao excluir registro: " + e.getMessage(), e);
        }
    }

    public static void alterarPermissao(String tabela, String campoIdentificador, int id, String novaPermissao) throws ClassNotFoundException, SQLException {
        conectado = conectar();
        String sql = "UPDATE " + tabela + " SET permissao = ? WHERE " + campoIdentificador + " = ?";

        try (PreparedStatement st = conectado.prepareStatement(sql)) {
            st.setString(1, novaPermissao);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar permissão: " + e.getMessage(), e);
        }
    }

    public static List<Evento> listarEventos() throws ClassNotFoundException, SQLException {
        List<Evento> eventos = new ArrayList<>();
        conectado = conectar();

        String query = "SELECT Evento, DataInicio FROM agenda";
        PreparedStatement st = conectado.prepareStatement(query);
        ResultSet resultado = st.executeQuery();

        while (resultado.next()) {
            Evento evento = new Evento(
                    resultado.getString("Evento"),
                    resultado.getString("DataInicio")
            );
            eventos.add(evento);
        }

        return eventos;
    }

    public static void registrarImagemUsuario(InputStream imagem, Usuario usuario) throws ClassNotFoundException, SQLException {
        conectado = conectar();
        PreparedStatement st = conectado.prepareStatement("UPDATE usuarios SET Imagem = ? WHERE id = ?");
        st.setBinaryStream(1, imagem);
        st.setInt(2, usuario.getId());
        st.executeUpdate();
    }

    public static byte[] getImagemUsuario(int id) throws SQLException, ClassNotFoundException {
        conectado = conectar();
        PreparedStatement st = conectado.prepareStatement("SELECT Imagem FROM usuarios WHERE id = ?");
        st.setInt(1, id);
        ResultSet resultado = st.executeQuery();

        byte[] imagem = null;
        if (resultado.next()) {
            imagem = resultado.getBytes("Imagem");
        }
        return imagem;
    }

    public static void atualizarCliente(Cliente cliente) throws ClassNotFoundException, SQLException {
        conectado = conectar();

        String sql = "UPDATE cliente SET Nome = ?, TipoPessoa = ?, Email = ?, Servico = ?, SituacaoServico = ?, Celular = ?, Telefone = ?, Observacoes = ? WHERE ID = ?";

        try (PreparedStatement st = conectado.prepareStatement(sql)) {
            st.setString(1, cliente.getNome());
            st.setString(2, cliente.getTipoPessoa());
            st.setString(3, cliente.getEmail());
            st.setString(4, cliente.getServico());
            st.setString(5, cliente.getSituacaoServico());
            st.setString(6, cliente.getCelular());
            st.setString(7, cliente.getTelefone());
            st.setString(8, cliente.getObservacoes());
            st.setInt(9, cliente.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar cliente: " + e.getMessage(), e);
        }
    }

    public static void atualizarRelatorio(Relatorio relatorio) throws ClassNotFoundException, SQLException {
        conectado = conectar();

        String sql = "UPDATE relatorio SET NomeRelatorio = ?, Descrição = ?, StatusRelatorio = ?, DataCadastro = ? WHERE ID = ?";

        try (PreparedStatement st = conectado.prepareStatement(sql)) {
            st.setString(1, relatorio.getNomeRelatorio());
            st.setString(2, relatorio.getDescricao());
            st.setString(3, relatorio.getStatusRelatorio());
            st.setString(4, relatorio.getDataCadastro());
            st.setInt(5, relatorio.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar relatorio: " + e.getMessage(), e);
        }
    }

    public static void atualizarTarefa(Tarefa tarefa) throws ClassNotFoundException, SQLException {
        conectado = conectar();

        String sql = "UPDATE tarefa SET NomeTarefa = ?, Descrição = ?, StatusTarefa = ?, DataVencimento = ?, Prioridade = ? WHERE ID = ?";

        try (PreparedStatement st = conectado.prepareStatement(sql)) {
            st.setString(1, tarefa.getNomeTarefa());
            st.setString(2, tarefa.getDescricao());
            st.setString(3, tarefa.getStatusTarefa());
            st.setString(4, tarefa.getDataVencimento());
            st.setString(5, tarefa.getPrioridade());
            st.setInt(6, tarefa.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar relatorio: " + e.getMessage(), e);
        }
    }

    public static Usuario buscarUsuarioPorNome(String nomeUsuario) throws SQLException, ClassNotFoundException {
        Usuario usuario = null;
        String sql = "SELECT id, usuario, email, senha, Permissao, Imagem FROM usuarios WHERE usuario = ?";

        conectado = conectar();

        try (PreparedStatement stmt = conectado.prepareStatement(sql)) {
            stmt.setString(1, nomeUsuario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setUsuario(rs.getString("usuario"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setPermissao(rs.getString("Permissao"));
                    usuario.setImagem(rs.getBytes("Imagem"));
                }
            }
        }

        return usuario;
    }

    public static void atualizarEmailUsuario(String emailAtual, String emailNovo) throws ClassNotFoundException, SQLException {
        conectado = conectar();

        String checkSql = "SELECT COUNT(*) FROM usuarios WHERE email = ?";
        try (PreparedStatement checkSt = conectado.prepareStatement(checkSql)) {
            checkSt.setString(1, emailNovo);
            ResultSet rs = checkSt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                throw new SQLException("O e-mail já está sendo usado por outro usuário.");
            }
        }

        String sql = "UPDATE usuarios SET email = ? WHERE email = ?";
        try (PreparedStatement st = conectado.prepareStatement(sql)) {
            st.setString(1, emailNovo);
            st.setString(2, emailAtual);
            int rowsUpdated = st.executeUpdate();

            if (rowsUpdated == 0) {
                throw new SQLException("Nenhum usuário encontrado com esse e-mail.");
            }
        }
    }

    public static void atualizarNomeUsuario(String nomeAtual, String nomeNovo) throws ClassNotFoundException, SQLException {
        conectado = conectar();

        String checkSql = "SELECT COUNT(*) FROM usuarios WHERE usuario = ?";
        try (PreparedStatement checkSt = conectado.prepareStatement(checkSql)) {
            checkSt.setString(1, nomeNovo);
            ResultSet rs = checkSt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                throw new SQLException("O nome de usuário já está em uso.");
            }
        }

        String sql = "UPDATE usuarios SET usuario = ? WHERE usuario = ?";
        try (PreparedStatement st = conectado.prepareStatement(sql)) {
            st.setString(1, nomeNovo);
            st.setString(2, nomeAtual);
            int rowsUpdated = st.executeUpdate();

            if (rowsUpdated == 0) {
                throw new SQLException("Nenhum usuário encontrado com esse nome.");
            }
        }
    }

    public static void atualizarSenhaUsuario(String email, String senhaNova) throws ClassNotFoundException, SQLException {
        conectado = conectar();

        String sql = "UPDATE usuarios SET senha = ? WHERE email = ?";
        try (PreparedStatement st = conectado.prepareStatement(sql)) {
            st.setString(1, senhaNova);
            st.setString(2, email);
            int rowsUpdated = st.executeUpdate();

            if (rowsUpdated == 0) {
                throw new SQLException("Nenhum usuário encontrado com esse e-mail.");
            }
        }
    }
    public static String buscarSenhaPorEmail(String email) throws ClassNotFoundException, SQLException {
    conectado = conectar();
    String sql = "SELECT senha FROM usuarios WHERE email = ?";
    try (PreparedStatement st = conectado.prepareStatement(sql)) {
        st.setString(1, email);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return rs.getString("senha");
        } else {
            throw new SQLException("Usuário não encontrado.");
        }
    }
}
}
