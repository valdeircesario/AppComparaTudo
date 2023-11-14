import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    private static final String URL = "jdbc:postgres://localhost:5432/teste02";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "201918";

    public static Connection obterConexao() throws SQLException {
        try {
            Class.forName("com.postgres.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC não encontrado.", e);
        }
    }
}
