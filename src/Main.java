import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/teste02";
        String user = "postgres";
        String password = "201918";
//        JFrame frame = new JFrame();
//
//        JButton btnInserirObjeto = new JButton("Inserir Objeto");
//        frame.add(btnInserirObjeto);
//        frame.setSize(500,500);
//        frame.setVisible(true);
//
//        btnInserirObjeto.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String nomeObjeto = JOptionPane.showInputDialog(frame, "Digite o nome do objeto:");
//                Objeto objeto = new Objeto(nomeObjeto);
//                TabelaDAO.inserirObjeto(objeto);
//                JOptionPane.showMessageDialog(frame, "Objeto inserido com sucesso!");
//
//            }
//        });

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                InterfaceGrafica interfaceGrafica = new InterfaceGrafica();
                interfaceGrafica.setVisible(true);


//                try {
//                    Connection conn = DriverManager.getConnection(url, user, password);
//                    Scanner scanner = new Scanner(System.in);
//                    int opcao;
//                    int opcao1;
//
//                    do {
//                        System.out.println("Selecione uma opção:");
//                        System.out.println("1. Inserir");
//                        System.out.println("2. Alterar");
//                        System.out.println("3. Deletar");
//                        System.out.println("4. Mostrar");
//                        System.out.println("5. Comparar objetos");
//                        System.out.println("6. Sair");
//
//                        opcao = scanner.nextInt();
//
//                        switch (opcao) {
//                            case 1:
//                                System.out.println("Digite uma opção:");
//                                System.out.println("1. Inserir objeto");
//                                System.out.println("2. Inserir critério");
//                                System.out.println("3. Inserir pesos");
//                                System.out.println("4. Sair");
//                                opcao1 = scanner.nextInt();
//                                switch (opcao1) {
//                                    case 1:
//
//
//                                        break;
//                                    case 2:
//                                        TabelaDAO.inserirCriterio();
//                                        break;
//                                    case 3:
//                                        TabelaDAO.inserirPesos();
//                                        break;
//                                    case 4:
//                                        System.out.println("Saindo...");
//                                        break;
//                                    default:
//                                        System.out.println("Opção inválida. Tente novamente.");
//                                }
//                                break;
//
//                            case 2:
//                                System.out.println("Digite uma opção:");
//                                System.out.println("1. Alterar objeto");
//                                System.out.println("2. Alterar critério");
//                                System.out.println("3. Alterar pesos");
//                                System.out.println("4. Sair");
//                                opcao1 = scanner.nextInt();
//                                switch (opcao1) {
//                                    case 1:
//                                        TabelaDAO.atualizarobjeto();
//                                        break;
//                                    case 2:
//                                        TabelaDAO.atualizarTabelaCriterio();
//                                        break;
//                                    case 3:
//                                        TabelaDAO.atualizarPesosUsuario();
//                                        break;
//                                    case 4:
//                                        System.out.println("Saindo...");
//                                        break;
//                                    default:
//                                        System.out.println("Opção inválida. Tente novamente.");
//                                }
//                                break;
//
//                            case 3:
//                                System.out.println("Digite uma opção:");
//                                System.out.println("1. Deletar objeto");
//                                System.out.println("2. Deletar critério");
//                                System.out.println("3. Deletar pesos");
//                                System.out.println("4. Sair");
//                                opcao1 = scanner.nextInt();
//                                switch (opcao1) {
//                                    case 1:
//                                        TabelaDAO.deletarObjeto();
//                                        break;
//                                    case 2:
//                                        TabelaDAO.deletarCriterio();
//                                        break;
//                                    case 3:
//                                        TabelaDAO.deletarPeso();
//                                        break;
//                                    case 4:
//                                        System.out.println("Saindo...");
//                                        break;
//                                    default:
//                                        System.out.println("Opção inválida. Tente novamente.");
//                                }
//                                break;
//
//                            case 4:
//                                System.out.println("Digite uma opção:");
//                                System.out.println("1. Mostrar objeto");
//                                System.out.println("2. Mostrar critério");
//                                System.out.println("3. Mostrar pesos");
//                                System.out.println("4. Mostrar comparação");
//                                System.out.println("5. Sair");
//                                opcao1 = scanner.nextInt();
//                                switch (opcao1) {
//                                    case 1:
//                                        TabelaDAO.mostrarTabelaObjeto();
//                                        break;
//                                    case 2:
//                                        TabelaDAO.mostrarTabelaCriterio();
//                                        break;
//                                    case 3:
//                                        TabelaDAO.mostrarTabelaPesos();
//                                        break;
//                                    case 4:
//                                        TabelaDAO.mostrarTabelaComparacao();
//                                        break;
//                                    case 5:
//                                        System.out.println("Saindo...");
//                                        break;
//                                    default:
//                                        System.out.println("Opção inválida. Tente novamente.");
//                                }
//                                break;
//
//                            case 5:
//                                TabelaDAO.compararObjetos();
//                                break;
//
//                            case 6:
//                                System.out.println("Saindo...");
//                                break;
//
//                            default:
//                                System.out.println("Opção inválida. Tente novamente.");
//                        }
//                    } while (opcao != 6);
//
//                    TabelaDAO.fecharConexao();
//                } catch (SQLException e) {
//                    System.out.println("Ocorreu um erro ao conectar ao banco de dados: " + e.getMessage());
//                }
            }
        });
    }
}
















