package Data;

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
        st.setString(10, nomeUsuario);
        st.executeUpdate();
    }

    public static void registrarTarefa(String nomeTarefa, String descricao, String statusTarefa, String dataVencimento, String prioridade) throws ClassNotFoundException, SQLException {
        conectado = conectar();
        PreparedStatement st = conectado.prepareStatement(
                "INSERT INTO tarefa (NomeTarefa, Descrição, StatusTarefa, DataVencimento, Prioridade) VALUES (?, ?, ?, ?, ?)"
        );
        st.setString(1, nomeTarefa);
        st.setString(2, descricao);
        st.setString(3, statusTarefa);
        st.setString(4, dataVencimento);
        st.setString(5, prioridade);
        st.executeUpdate();
    }

    public static void registrarRelatorio(String nomeRelatorio, String descricao, String statusRelatorio, String dataCadastro) throws ClassNotFoundException, SQLException {
        conectado = conectar();
        PreparedStatement st = conectado.prepareStatement(
                "INSERT INTO relatorio (NomeRelatorio, Descrição, StatusRelatorio, DataCadastro) VALUES (?, ?, ?, ?)"
        );
        st.setString(1, nomeRelatorio);
        st.setString(2, descricao);
        st.setString(3, statusRelatorio);
        st.setString(4, dataCadastro);
        st.executeUpdate();
    }

    public static List<Cliente> listarClientes() throws ClassNotFoundException, SQLException {
        List<Cliente> clientes = new ArrayList<>();
        conectado = conectar();
        String query = "SELECT ID, Nome, TipoPessoa, SituacaoServico, Servico, DataCadastro FROM cliente";
        PreparedStatement st = conectado.prepareStatement(query);
        ResultSet resultado = st.executeQuery();

        while (resultado.next()) {
            Cliente cliente = new Cliente(
                    resultado.getInt("ID"),
                    resultado.getString("Nome"),
                    resultado.getString("TipoPessoa"),
                    resultado.getString("SituacaoServico"),
                    resultado.getString("Servico"),
                    resultado.getString("DataCadastro")
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
}
