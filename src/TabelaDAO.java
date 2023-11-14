import jdk.internal.icu.impl.CharacterIteratorWrapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TabelaDAO {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/teste02";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "201918";
    private static CharacterIteratorWrapper resultSet;


    public static void inserirObjeto(Objeto objeto) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

            String sql = "INSERT INTO objeto (nome) VALUES ( ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, objeto.getNome());
            statement.executeUpdate();
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Objeto atualizado com sucesso!");
            } else {
                System.out.println("Nenhum objeto foi atualizado. Verifique se o ID é válido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Objeto> mostrarTabelaObjeto() {
        List<Objeto> objetos = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT * FROM objeto";
            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    System.out.println("ID: " + resultSet.getInt("id") + ", Nome: " + resultSet.getString("nome"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Object[]> mostrarTabelaCriterio() {
        List<Double> Criterio = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT * FROM criterio";
            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    System.out.println("ID: " + resultSet.getInt("id") +
                            ", Qualidade: " + resultSet.getDouble("qualidade") +
                            ", Avaliacao: " + resultSet.getDouble("avaliacao") +
                            ", Nota: " + resultSet.getDouble("nota") +
                            ", ID_Peso: " + resultSet.getInt("id_peso"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Double> mostrarTabelaPesos() {
        List<Double> pesos = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT * FROM pesos";
            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    double pesoQualidade = resultSet.getDouble("pesoqualidade");
                    double pesoAvaliacao = resultSet.getDouble("pesoavaliacao");
                    double pesoNota = resultSet.getDouble("pesonota");

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


    public static void inserirCriterio() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("de 0 a 5 para qualidade ");
            double Qualidade = scanner.nextDouble();
            System.out.print("avalie de 0 a 5:");
            double Avaliacao = scanner.nextDouble();
            System.out.print("de a nota de 0 a 5 ");
            double Nota = scanner.nextDouble();

            String sql = "INSERT INTO criterio ( qualidade,avaliacao,nota) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDouble(1, Qualidade);
            statement.setDouble(2, Avaliacao);
            statement.setDouble(3, Nota);
            statement.executeUpdate();
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Objeto com sucwaao com sucesso!");
            } else {
                System.out.println("Nenhum objeto foi atualizadose o ID é válido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deletarObjeto() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Digite o ID do objeto a ser deletado: ");
            String Nome = scanner.nextLine();

            String sql = "DELETE FROM objeto WHERE nome = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, Nome);
            statement.executeUpdate();
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Objeto atualizado com sucesso!");
            } else {
                System.out.println("Nenhum objeto foi atualizado. Verifique se o ID é válido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void atualizarTabelaCriterio() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Digite o id criterio: ");
            int id = scanner.nextInt();
            System.out.print("Digite A novaQualidade: ");
            double novaQualidade = scanner.nextDouble();
            System.out.print("Digite a novaAvaliacao: ");
            double novaAvaliacao = scanner.nextDouble();
            System.out.print("Digite a novaNota: ");
            double novaNota = scanner.nextDouble();

            String sql = "UPDATE criterio SET Qualidade,Avaliacao,Nota = novaQhalidade,novaAvaliacao,novaNota WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setDouble(2, novaQualidade);
            statement.setDouble(3, novaAvaliacao);
            statement.setDouble(4, novaNota);
            statement.executeUpdate();
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Objeto atualizado com sucesso!");
            } else {
                System.out.println("Nenhum objeto foi atualizado. Verifique se o ID é válido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void atualizarPesosUsuario() throws SQLException {
        try (Scanner scanner = new Scanner(System.in);
             Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

            System.out.print("Digite o ID do registro que deseja atualizar: ");
            int id = scanner.nextInt();

            System.out.print("Digite o novo valor para 'Peso Qualidade': ");
            double pesoQualidade = scanner.nextDouble();

            System.out.print("Digite o novo valor para 'Peso Avaliação': ");
            double pesoAvaliacao = scanner.nextDouble();

            System.out.print("Digite o novo valor para 'Peso Nota': ");
            double pesoNota = scanner.nextDouble();

            String sql = "UPDATE pesos SET pesoqualidade = ?, pesoavaliacao = ?, pesonota = ? WHERE id = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setDouble(1, pesoQualidade);
                statement.setDouble(2, pesoAvaliacao);
                statement.setDouble(3, pesoNota);
                statement.setInt(4, id);

                int linhasAfetadas = statement.executeUpdate();

                if (linhasAfetadas > 0) {
                    System.out.println("Pesos atualizados com sucesso!");
                } else {
                    System.out.println("Nenhum peso foi atualizado. Verifique se o ID é válido.");
                }
            }
        }
    }

    public static void atualizarobjeto() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite o novo nome do ojeto: ");
            String nome = scanner.nextLine();


            String sql = "UPDATE objeto SET nome WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, nome);
            statement.executeUpdate();
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Objeto atualizado com sucesso!");
            } else {
                System.out.println("Nenhum objeto foi atualizado. Verifique se o ID é válido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deletarCriterio() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Digite o ID do criterio a ser deletado: ");
            int id = scanner.nextInt();

            String sql = "DELETE FROM criterio WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Objeto atualizado com sucesso!");
            } else {
                System.out.println("Nenhum objeto foi atualizado. Verifique se o ID é válido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void fecharConexao() {
    }

    public static void exibirComparacoes() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT * FROM comparacao";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            System.out.println("Tabela Comparacao:");
            System.out.println("ID\tNome Objeto\tResultado");

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nomeObjeto = resultSet.getString("nome_objeto");
                double resultado = resultSet.getDouble("resultado");

                System.out.println(id + "\t" + nomeObjeto + "\t\t" + resultado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static double compararObjetos() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Digite o nome do primeiro objeto: ");
            String nomeObjeto1 = scanner.nextLine();
            double qualidadeObjeto1 = obterNota("Qualidade", scanner);
            double avaliacaoObjeto1 = obterNota("Avaliação", scanner);
            double notaObjeto1 = obterNota("Nota", scanner);
            double pesoQualidade = obterPeso("Qualidade", scanner);
            double pesoAvaliacao = obterPeso("Avaliação", scanner);
            double pesoNota = obterPeso("Nota", scanner);

            // Limpar o buffer do scanner antes de solicitar o nome do segundo objeto
            scanner.nextLine();

            System.out.print("Digite o nome do segundo objeto: ");
            String nomeObjeto2 = scanner.nextLine();
            double qualidadeObjeto2 = obterNota("Qualidade", scanner);
            double avaliacaoObjeto2 = obterNota("Avaliação", scanner);
            double notaObjeto2 = obterNota("Nota", scanner);

            int idObjeto1 = inserirObjetoNoBanco(nomeObjeto1);
            int idObjeto2 = inserirObjetoNoBanco(nomeObjeto2);

            int idPeso = (int) inserirPesosNoBanco(pesoQualidade, pesoAvaliacao, pesoNota);

            int idCriterio1 = inserirCriterioNoBanco(qualidadeObjeto1, avaliacaoObjeto1, notaObjeto1);
            int idCriterio2 = inserirCriterioNoBanco(qualidadeObjeto2, avaliacaoObjeto2, notaObjeto2);

            double resultadoObjeto1 = calcularResultado(qualidadeObjeto1, avaliacaoObjeto1, notaObjeto1, pesoQualidade, pesoAvaliacao, pesoNota);
            double resultadoObjeto2 = calcularResultado(qualidadeObjeto2, avaliacaoObjeto2, notaObjeto2, pesoQualidade, pesoAvaliacao, pesoNota);

            inserirComparacaoNoBanco(nomeObjeto1, idObjeto1, idCriterio1, resultadoObjeto1);
            inserirComparacaoNoBanco(nomeObjeto2, idObjeto2, idCriterio2, resultadoObjeto2);

            String mensagemComparacao = compararResultados(nomeObjeto1, nomeObjeto2, resultadoObjeto1, resultadoObjeto2);
            System.out.println(mensagemComparacao);


            return Math.max(resultadoObjeto1, resultadoObjeto2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static double calcularResultado(double qualidade, double avaliacao, double nota, double pesoQualidade, double pesoAvaliacao, double pesoNota) {
        return (qualidade * pesoQualidade) + (avaliacao * pesoAvaliacao) + (nota * pesoNota);
    }


    private static double obterNota(String tipo, Scanner scanner) {
        System.out.print("Qual a nota para " + tipo + "? De 0 a 5: ");
        return scanner.nextDouble();
    }

    private static double obterPeso(String tipo, Scanner scanner) {
        System.out.print("Peso de " + tipo + " (de 0 a 3): ");
        return scanner.nextDouble();
    }

    private static int inserirObjetoNoBanco(String nomeObjeto) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "INSERT INTO objeto (nome) VALUES (?)";
            PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, nomeObjeto);
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        }
        return -1;
    }

    private static int inserirCriterioNoBanco(double qualidade, double avaliacao, double nota) throws SQLException {
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
        return -1;
    }

    private static double inserirPesosNoBanco(double pesoqualidade, double pesoavaliacao, double pesonota) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "INSERT INTO pesos (pesoQualidade,pesoAvaliacao,pesoNota) VALUES (?,?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDouble(1, pesoqualidade);
            statement.setDouble(2, pesoavaliacao);
            statement.setDouble(3, pesonota);
            statement.executeUpdate();
        }
        return pesoqualidade;
    }

    private static void inserirComparacaoNoBanco(String nomeObjeto, int idObjeto, int idCriterio, double resultado) throws SQLException {
        // Inicializa a variável com um valor padrão

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "INSERT INTO comparacao (nome_objeto,id_objeto, id_criterio, resultado) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, nomeObjeto);
            statement.setInt(2, idObjeto);
            statement.setInt(3, idCriterio);
            statement.setDouble(4, resultado);
            statement.executeUpdate();
        }
    }

    private static String compararResultados(String nomeObjeto1, String nomeObjeto2, double resultadoObjeto1, double resultadoObjeto2) {
        if (resultadoObjeto1 > resultadoObjeto2) {

            return "O(a): " + nomeObjeto1 + " tem um resultado maior do que O(a):" + nomeObjeto2 + ".";

        } else if (resultadoObjeto1 < resultadoObjeto2) {
            return "O(a):" + nomeObjeto2 + " tem um resultado maior do que O(a): " + nomeObjeto1 + ".";
        } else {
            return "Os objetos " + nomeObjeto1 + " e " + nomeObjeto2 + " têm resultados iguais.";
        }
    }

    public static void inserirPesos() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Peso de Qualidade (de 0 a 3): ");
            double pesoQualidade = scanner.nextDouble();

            System.out.print("Peso de Avaliação (de 0 a 3): ");
            double pesoAvaliacao = scanner.nextDouble();

            System.out.print("Peso de Nota (de 0 a 3): ");
            double pesoNota = scanner.nextDouble();

            inserirPesosNoBanco(pesoQualidade, pesoAvaliacao, pesoNota);
            System.out.println("Pesos inseridos com sucesso.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deletarPeso() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Digite o ID do peso que deseja excluir: ");
            int idPeso = scanner.nextInt();

            if (idPeso > 0) {
                if (existePeso(conn, idPeso)) {
                    deletarPesoNoBanco(conn, idPeso);
                    System.out.println("Peso excluído com sucesso.");
                } else {
                    System.out.println("O ID do peso não existe na tabela.");
                }
            } else {
                System.out.println("O ID do peso deve ser um número positivo.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean existePeso(Connection conn, int idPeso) throws SQLException {
        String sql = "SELECT id FROM pesos WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, idPeso);
            return statement.executeQuery().next();
        }
    }

    private static void deletarPesoNoBanco(Connection conn, int idPeso) throws SQLException {
        String sql = "DELETE FROM pesos WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, idPeso);
            statement.executeUpdate();
        }
    }

    public static List<Object[]> mostrarTabelaComparacao() {
        List<Object> Comparacoes = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT * FROM comparacao";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    System.out.printf("%-5s %-20s %-10s %-10s %-10s%n", "ID", "Nome Objeto", "ID Objeto", "ID Criterio", "Resultado");
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String nomeObjeto = resultSet.getString("nome_objeto");
                        int idObjeto = resultSet.getInt("id_objeto");
                        int idCriterio = resultSet.getInt("id_criterio");
                        double resultado = resultSet.getDouble("resultado");

                        System.out.printf("%-5d %-20s %-10d %-10d %-10.2f%n", id, nomeObjeto, idObjeto, idCriterio, resultado);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}








