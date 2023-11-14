import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class InterfaceGrafica extends JFrame {
    private JButton btnInserirObjeto;


    private JButton btnInserirCriterio;
    private JButton btnInserirPesos;
    //INSERIR
    private JButton btnMostrarCriterio;
    private JButton btnMostrarObjetos;
    private JButton btnmostrarTabelaPesos;
    //MOSTRAR

    private JButton btnDeletarObjeto;
    private JButton btnDeletarCriterio;


    private JButton btnDeletarPeso;

    //deletar

    private JButton btnAlterarObjeto;
    private JButton btnAlterarCriterio;
    private JButton btnAlterarPeso;

    private JButton btnCompararObjeto;
    private JTable tblComparacao;
    private JButton btnMostraComparacao;
    private JTextField textFieldNomeObjeto;
    private JTextField textFieldQualidade;
    private JTextField textFieldAvaliacao;
    private JTextField textFieldNota;
    private JTextField textFieldPesoQualidade;
    private JTextField textFieldPesoAvaliacao;
    private JTextField textFieldPesoNota;
    private JTextArea textAreaComparacao;


    private JButton btnSair;

    public InterfaceGrafica() {
        super("APP COMPARATUDO");



        btnInserirObjeto = new JButton("Inserir Objeto");
        btnInserirCriterio = new JButton("Inserir Critério");
        btnInserirPesos = new JButton("Inserir Pesos");
        btnMostrarObjetos = new JButton("Mostrar Objetos");
        btnMostrarCriterio = new JButton("Mostrar Critérios");
        btnmostrarTabelaPesos = new JButton("Mostrar Pesos");
        btnDeletarObjeto = new JButton("Deletar Objeto");
        btnDeletarCriterio = new JButton("Deletar Criterio");
        btnDeletarPeso = new JButton("Deletar Pesos");
        btnAlterarObjeto = new JButton("Alterar Objeto");
        btnAlterarCriterio = new JButton("Alterar Criterio");
        btnAlterarPeso = new JButton("Alterar Pesos");
        btnMostraComparacao = new JButton("Mostrar Comparacao");
        btnCompararObjeto = new JButton("Comparar Objetos");
        btnSair = new JButton("Sair");


        add(btnInserirObjeto);
        add(btnInserirCriterio);
        add(btnInserirPesos);
        add(btnMostrarObjetos);
        add(btnMostrarCriterio);
        add(btnmostrarTabelaPesos);
        add(btnDeletarObjeto);
        add(btnDeletarCriterio);
        add(btnDeletarPeso);
        add(btnAlterarObjeto);
        add(btnAlterarCriterio);
        add(btnAlterarPeso);
        add(btnMostraComparacao);
        add(btnCompararObjeto);
        add(btnSair);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        btnInserirObjeto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeObjeto = JOptionPane.showInputDialog(InterfaceGrafica.this, "Digite o nome do objeto:");
                Objeto objeto = new Objeto(nomeObjeto);
                TabelaDAO.inserirObjeto(objeto);
                JOptionPane.showMessageDialog(InterfaceGrafica.this, "Objeto inserido com sucesso!");
            }
        });
        // Dentro da classe da sua interface gráfica
        btnDeletarObjeto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idStr = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do objeto a ser excluído:"));
                int id=0;

                try {
                    id = Integer.parseInt(String.valueOf(idStr));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido. Digite um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                TabelaDAO.deletarObjeto();
            }
        });
        btnInserirCriterio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double qualidade = Double.parseDouble(JOptionPane.showInputDialog(InterfaceGrafica.this, "Digite a qualidade:"));
                double avaliacao = Double.parseDouble(JOptionPane.showInputDialog(InterfaceGrafica.this, "Digite a avaliação:"));
                double nota = Double.parseDouble(JOptionPane.showInputDialog(InterfaceGrafica.this, "Digite a nota:"));

                TabelaDAO.inserirCriterio();
                JOptionPane.showMessageDialog(InterfaceGrafica.this, "Critério inserido com sucesso!");
            }
        });
        btnInserirPesos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double pesoQualidade = Double.parseDouble(JOptionPane.showInputDialog(InterfaceGrafica.this, "Digite o valor do Peso de Qualidade:"));
                double pesoAvaliacao = Double.parseDouble(JOptionPane.showInputDialog(InterfaceGrafica.this, "Digite o valor do Peso de Avaliação:"));
                double pesoNota = Double.parseDouble(JOptionPane.showInputDialog(InterfaceGrafica.this, "Digite o valor do Peso de Nota:"));

                TabelaDAO.inserirPesos();
                JOptionPane.showMessageDialog(InterfaceGrafica.this, "Pesos inseridos com sucesso!");
            }
        });
        btnmostrarTabelaPesos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Double> pesos = TabelaDAO.mostrarTabelaPesos();

                StringBuilder result = new StringBuilder("Tabela de Pesos:\n");
                result.append(String.format("%-15s %-15s %-15s\n", "Peso Qualidade", "Peso Avaliação", "Peso Nota"));

                if (!pesos.isEmpty()) {
                    int i = 0;
                    while (i < pesos.size()) {
                        double pesoQualidade = pesos.get(i++);
                        double pesoAvaliacao = pesos.get(i++);
                        double pesoNota = pesos.get(i++);

                        result.append(String.format("%-15.2f %-15.2f %-15.2f\n", pesoQualidade, pesoAvaliacao, pesoNota));
                    }
                } else {
                    result.append("Nenhum registro encontrado.\n");
                }

                JTextArea textArea = new JTextArea(result.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);

                JOptionPane.showMessageDialog(InterfaceGrafica.this, scrollPane, "Tabela de Pesos", JOptionPane.PLAIN_MESSAGE);
            }
        });


        btnDeletarPeso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TabelaDAO.deletarPeso();
                // Chame o método sem parâmetros
                JOptionPane.showMessageDialog(InterfaceGrafica.this, "Peso excluído com sucesso!");
            }
        });


        btnMostrarObjetos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Objeto> objetos = TabelaDAO.mostrarTabelaObjeto();
                StringBuilder result = new StringBuilder("Tabela de Objetos:\n");
                result.append(String.format("%-15s %-15s\n", "ID", "Nome"));

                if (!objetos.isEmpty()) {
                    for (Objeto objeto : objetos) {
                        result.append(String.format("%-15d %-15s\n", objeto.getId(), objeto.getNome()));
                    }
                } else {
                    result.append("Nenhum registro encontrado.\n");
                }

                JTextArea textArea = new JTextArea(result.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);

                JOptionPane.showMessageDialog(InterfaceGrafica.this, scrollPane, "Tabela de Objetos", JOptionPane.PLAIN_MESSAGE);


            }
        });

        btnMostraComparacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Object[]> comparacao = TabelaDAO.mostrarTabelaComparacao();
                if (!comparacao.isEmpty()) {
                    String[] columnNames = {"nome objeto", "id objeto", "id criterio", "resultado"};
                    Object[][] data = new Object[comparacao.size()][4];

                    for (int i = 0; i < comparacao.size(); i++) {
                        Object[] row = comparacao.get(i);
                        data[i][0] = row[0];
                        data[i][1] = row[1];
                        data[i][2] = row[2];
                        data[i][3] = row[3];
                    }

                    JFrame frame = new JFrame("Tabela de comparações");
                    JTable table = new JTable(data, columnNames);
                    JScrollPane scrollPane = new JScrollPane(table);
                    frame.add(scrollPane);
                    frame.setSize(600, 400);
                    frame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(InterfaceGrafica.this, "Nenhum registro foi encontrado.", "Tabela de comparações", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        btnMostrarCriterio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Object[]> criterios = TabelaDAO.mostrarTabelaCriterio();

                StringBuilder result = new StringBuilder("Tabela de Critérios:\n");
                result.append(String.format("%-5s %-10s %-10s %-5s %-8s\n", "ID", "Qualidade", "Avaliação", "Nota", "ID_Peso"));

                if (!criterios.isEmpty()) {
                    for (Object[] criterio : criterios) {
                        int id = (int) criterio[0];
                        double qualidade = (double) criterio[1];
                        double avaliacao = (double) criterio[2];
                        double nota = (double) criterio[3];
                        int idPeso = (int) criterio[4];

                        result.append(String.format("%-5d %-10.2f %-10.2f %-5.2f %-8d\n", id, qualidade, avaliacao, nota, idPeso));
                    }
                } else {
                    result.append("Nenhum registro encontrado.\n");
                }

                JTextArea textArea = new JTextArea(result.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);

                JOptionPane.showMessageDialog(InterfaceGrafica.this, scrollPane, "Tabela de Critérios", JOptionPane.PLAIN_MESSAGE);
            }
        });




    }


}
