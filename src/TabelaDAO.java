import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TabelaDAO {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/teste02";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "201918";



    public static void inserirObjeto(Objeto objeto) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "INSERT INTO objeto (nome) VALUES (?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, objeto.getNome());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Objeto inserido com sucesso!");
            } else {
                System.out.println("Nenhum objeto foi inserido. Verifique se os dados estão corretos.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir objeto: " + e.getMessage());
        }
    }

    public static void inserirCriterio(Criterio criterio) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

            String sql = "INSERT INTO criterio (qualidade, avaliacao, nota) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDouble(1, criterio.getQualidade());
            statement.setDouble(2, criterio.getAvaliacao());
            statement.setDouble(3, criterio.getNota());

            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Criterio Inserido com sucesso!");
            } else {
                System.out.println("Nenhum objeto foi atualizado e o ID é válido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Inserirpeso(Pesos pesos) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

            String sql = "INSERT INTO pesos (PesoQualidade, pesoAvaliacao, pesoNota) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDouble(1, pesos.getPesoQualidade());
            statement.setDouble(2, pesos.getPesoAvaliacao());
            statement.setDouble(3, pesos.getPesoNota());

            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Pesos inseridos com sucesso!");
            } else {
                System.out.println("Nenhum peso foi inserido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> mostrarTabelaObjeto() {
        List<String> objeto = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT * FROM objeto";
            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while ((resultSet.next())) {
                    int id = resultSet.getInt("id");
                    String nome = resultSet.getString("Nome");
                    objeto.add(String.valueOf(id));
                    objeto.add(nome);
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objeto;
    }

    public static List<Double> mostrarTabelaCriterio() {
        List<Double> criterio = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT * FROM criterio";
            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    double qualidade = resultSet.getDouble("qualidade");
                    double avaliacao = resultSet.getDouble("avaliacao");
                    double nota = resultSet.getDouble("nota");

                    criterio.add(Double.valueOf(id));
                    criterio.add(qualidade);
                    criterio.add(avaliacao);
                    criterio.add(nota);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return criterio;
    }

    public static List<Double> mostrarTabelaPesos() {
        List<Double> pesos = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT * FROM pesos";
            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    double pesoQualidade = resultSet.getDouble("pesoqualidade");
                    double pesoAvaliacao = resultSet.getDouble("pesoavaliacao");
                    double pesoNota = resultSet.getDouble("pesonota");
                    pesos.add(Double.valueOf(id));
                    pesos.add(pesoQualidade);
                    pesos.add(pesoAvaliacao);
                    pesos.add(pesoNota);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pesos;
    }

    public static List<String> mostrarTabelaComparacao() {
        List<String> comparacao = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT * FROM comparacao";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String nome_objeto = resultSet.getString("Nome_Objeto");
                        int id_objeto = (int) resultSet.getDouble("Id_Objeto");
                        int id_criterio = (int) resultSet.getDouble("Id_Criterio");
                        int id_Pesos = (int) resultSet.getDouble("id_pesos");
                        double resultado = resultSet.getDouble("Resultado");

                        comparacao.add(String.valueOf(id));
                        comparacao.add(nome_objeto);
                        comparacao.add(String.valueOf(id_objeto));
                        comparacao.add(String.valueOf(id_criterio));
                        comparacao.add(String.valueOf(id_Pesos));
                        comparacao.add(String.valueOf(resultado));
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comparacao;
    }


    public static boolean deletarObjeto(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

            boolean idVinculado = verificarIDNaTabelaComparacaoo(id);

            if (idVinculado) {
                System.out.println("O ID se refere a uma chave estrangeira na tabela 'comparacao'.");
                return false;
            }

            String sql = "DELETE FROM objeto WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            int linhasAfetadas = statement.executeUpdate();

            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean DeletarCriterio(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

            boolean idVinculado = verificarIDNaTabelaComparacaoc(id);

            if (idVinculado) {
                System.out.println("O ID se refere a uma chave estrangeira na tabela 'comparacao'.");
                return false;
            }

            String sql = "DELETE FROM criterio WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            int linhasAfetadas = statement.executeUpdate();

            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean DeletarPeso(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

            boolean idVinculado = verificarIDNaTabelaComparacaop(id);

            if (idVinculado) {
                System.out.println("O ID se refere a uma chave estrangeira na tabela 'comparacao'.");
                return false;
            }

            String sql = "DELETE FROM pesos WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            int linhasAfetadas = statement.executeUpdate();

            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean verificarIDNaTabelaComparacaop(int id) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "SELECT * FROM comparacao WHERE id_pesos = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            boolean idVinculado = resultSet.next();

            resultSet.close();
            statement.close();
            connection.close();

            return idVinculado;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }



    public static boolean verificarIDNaTabelaComparacaoc(int id) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "SELECT * FROM comparacao WHERE id_criterio = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            boolean idVinculado = resultSet.next();

            resultSet.close();
            statement.close();
            connection.close();

            return idVinculado;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public static boolean verificarIDNaTabelaComparacaoo(int id) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "SELECT * FROM comparacao WHERE id_objeto = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            boolean idVinculado = resultSet.next();

            resultSet.close();
            statement.close();
            connection.close();

            return idVinculado;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public static boolean verificarIDNaTabelaCritrio(int id) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "SELECT * FROM criterio WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            boolean idVinculado = resultSet.next();

            resultSet.close();
            statement.close();
            connection.close();

            return idVinculado;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public static boolean verificarIDNaTabelaPesos(int id) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "SELECT * FROM pesos WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            boolean idVinculado = resultSet.next();

            resultSet.close();
            statement.close();
            connection.close();

            return idVinculado;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public static boolean verificarIDNaTabelaObjeto(int id) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "SELECT * FROM objeto WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            boolean idVinculado = resultSet.next();

            resultSet.close();
            statement.close();
            connection.close();

            return idVinculado;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public static boolean verificarIDNaTabelaComparacao(int id) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "SELECT * FROM Comparacao WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            boolean idVinculado = resultSet.next();

            resultSet.close();
            statement.close();
            connection.close();

            return idVinculado;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean atualizarCriterio(int id, double novaQualidade, double novaAvaliacao, double novaNota) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

            boolean idVinculado = verificarIDNaTabelaComparacaoc(id);

            if (idVinculado) {
                System.out.println("O ID é uma chave estrangeira na tabela 'comparacao'. Não pode ser atualizado.");
                return false;
            } else {
                String sql = "UPDATE Criterio SET qualidade = ?, avaliacao = ?, nota = ? WHERE id = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setDouble(1, novaQualidade);
                statement.setDouble(2, novaAvaliacao);
                statement.setDouble(3, novaNota);
                statement.setInt(4, id);

                int linhasAfetadas = statement.executeUpdate();

                if (linhasAfetadas > 0) {
                    System.out.println("Critério atualizado com sucesso!");
                    return true;
                } else {
                    System.out.println("Nenhum critério foi atualizado. Verifique se o ID é válido.");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean AtualizarPeso(int id, double pesoQualidade, double pesoAvaliacao, double pesoNota) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

            boolean idVinculado = verificarIDNaTabelaComparacaoc(id);

            if (idVinculado) {
                System.out.println("O ID é uma chave estrangeira na tabela 'comparacao'. Não pode ser atualizado.");
                return false;
            } else {
                String sql = "UPDATE pesos SET pesoQualidade = ?, pesoAvaliacao = ?, pesoNota = ? WHERE id = ?";
                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    statement.setDouble(1, pesoQualidade);
                    statement.setDouble(2, pesoAvaliacao);
                    statement.setDouble(3, pesoNota);
                    statement.setInt(4, id);

                    int linhasAfetadas = statement.executeUpdate();

                    if (linhasAfetadas > 0) {
                        System.out.println("Pesos atualizados com sucesso!");
                        return true;
                    } else {
                        System.out.println("Nenhum peso foi atualizado. Verifique se o ID é válido.");
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean AtualizarObjeto(int id, String nome) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            boolean idVinculado = verificarIDNaTabelaComparacaoc(id);

            if (idVinculado) {
                System.out.println("O ID é uma chave estrangeira na tabela 'comparacao'. Não pode ser atualizado.");
                return false;
            } else {
                String sql = "UPDATE Objeto SET nome = ? WHERE id = ?";
                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    statement.setString(1, nome);
                    statement.setInt(2, id);

                    int linhasAfetadas = statement.executeUpdate();

                    if (linhasAfetadas > 0) {
                        System.out.println("Objeto atualizado com sucesso!");
                        return true;
                    } else {
                        System.out.println("Nenhum objeto foi atualizado. Verifique se o ID é válido.");
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean DeletarComparacao(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

            boolean idVinculado = verificarIDNaTabelaComparacao(id);

            if (!idVinculado) {
                System.out.println("O ID não existe na tabela 'comparacao'.");
                return false;
            }

            String sql = "DELETE FROM comparacao WHERE id = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, id);

                int linhasAfetadas = statement.executeUpdate();

                return linhasAfetadas > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static double compararObjetos(String nomeObjeto1, String nomeObjeto2, double qualidadeObjeto1, double qualidadeObjeto2, double avaliacaoObjeto1, double avaliacaoObjeto2, double resultadoObjeto1, double resultadoObjeto2) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static double calcularResultado(double qualidade, double pesoQualidade, double avaliacao, double pesoAvaliacao, double nota, double pesoNota) {
        return (qualidade * pesoQualidade) + (avaliacao * pesoAvaliacao) + (nota * pesoNota);
    }

    public static String compararResultados(String nomeObjeto1, String nomeObjeto2, double resultadoObjeto1, double resultadoObjeto2) {
        if (resultadoObjeto1 > resultadoObjeto2) {

            return "O(a): " + nomeObjeto1 + " tem um Resultado de :" + resultadoObjeto1 + ".\n" +
                    "O(a): " + nomeObjeto2 + " tem um Resultado de :" + resultadoObjeto2 + ".\n" +
                    "Portanto O(a):" + nomeObjeto1 + "E Melhor.";

        } else if (resultadoObjeto1 < resultadoObjeto2) {
            return "O(a): " + nomeObjeto2 + " tem um Resultado de :" + resultadoObjeto2 + ".\n" +
                    "O(a): " + nomeObjeto1 + " tem um Resultado de :" + resultadoObjeto1 + ".\n" +
                    "Portanto O(a):" + nomeObjeto2 + "E Melhor.";
        } else {
            return "O(a): " + nomeObjeto1 + " tem um Resultado de :" + resultadoObjeto1 + ".\n" +
                    "O(a): " + nomeObjeto2 + " tem um Resultado de :" + resultadoObjeto2 + ".\n" +
                    "Portanto O(a):" + nomeObjeto1 + "e" + nomeObjeto2 + ".São iguais.";
        }
    }



    public static int inserirObjetoNoBanco(Objeto objeto) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "INSERT INTO objeto (nome) VALUES (?)";
            PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, objeto.getNome());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        }

        return 1;
    }

    public static int inserirCriterioNoBanco(double qualidade, double avaliacao, double nota) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "INSERT INTO criterio (qualidade,avaliacao,nota) VALUES (?, ?,?)";
            PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setDouble(1, qualidade);
            statement.setDouble(2, avaliacao);
            statement.setDouble(3, nota);
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        }
        return 1;
    }

    public static int inserirPesosNoBanco(double pesoqualidade,double pesoavaliacao,double pesonota) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "INSERT INTO pesos (pesoqualidade,pesoavaliacao,pesonota) VALUES (?,?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDouble(1,pesoqualidade);
            statement.setDouble(2,pesoavaliacao);
            statement.setDouble(3, pesonota);
            statement.executeUpdate();

        }
        return 1;

    }

    public static void inserirComparacaoNoBanco(String nome_objeto, int id_objeto, int id_criterio,int id_pesos, double resultado) throws SQLException {


        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "INSERT INTO comparacao (nome_objeto,id_objeto, id_criterio,id_pesos, resultado) VALUES (?, ?, ?, ? ,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, nome_objeto);
            statement.setInt(2, id_objeto);
            statement.setInt(3, id_criterio);
            statement.setInt(4,id_pesos);
            statement.setDouble(5, resultado);
            statement.executeUpdate();
        }

    }
    public static int obterIdPeso(double pesoQualidade, double pesoAvaliacao, double pesoNota) {
        int idPeso = -1;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT id FROM pesos WHERE pesoqualidade = ? AND pesoavaliacao = ? AND pesonota = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setDouble(1, pesoQualidade);
                statement.setDouble(2, pesoAvaliacao);
                statement.setDouble(3, pesoNota);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        idPeso = resultSet.getInt("id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idPeso;
    }


    public static int obterIdObjetoPorNome(String nomeObjeto) {
        int idObjeto = -1;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT id FROM objeto WHERE nome = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, nomeObjeto);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        idObjeto = resultSet.getInt("id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idObjeto;

    }
}











