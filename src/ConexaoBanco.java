import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    private static final String URL = "jdbc:postgresql://localhost:5432/teste02";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "201918";

    public static Connection obterConexao() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC não encontrado.", e);
        }
    }

    public static void fecharConexao(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexão fechada com sucesso!");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar a conexão: " + ex.getMessage());
        }
    }
}
