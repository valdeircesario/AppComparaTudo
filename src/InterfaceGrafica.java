import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;
import java.sql.SQLException;



public class InterfaceGrafica extends JFrame {
    private JButton btnInserirObjeto;


    private JButton btnInserirCriterio;
    private JButton btnInserirpeso;
    //INSERIR
    private JButton btnMostrarCriterio;
    private JButton btnmostrarTabelaObjeto;
    private JButton btnmostrarTabelaPesos;
    private JButton btnmostrarTabelaComparacao;
    //MOSTRAR

    private JButton btnDeletarObjeto;
    private JButton btnDeletarCriterio;


    private JButton btnDeletarPeso;

    //deletar

    private JButton btnAtualizarObjeto;
    private JButton btnAtualizarCriterio;
    private JButton btnAtualizarPeso;

    private JButton btnCompararObjeto;

    private JButton btnDeletarComparacao;



    private JButton btnSair;

    public InterfaceGrafica() {
        super("APP COMPARATUDO");





        btnInserirObjeto = new JButton("Inserir Objeto");
        btnInserirCriterio = new JButton("Inserir Critério");
        btnInserirpeso = new JButton("Inserir Pesos");
        btnmostrarTabelaObjeto = new JButton("Mostrar Objetos");
        btnMostrarCriterio = new JButton("Mostrar Critérios");
        btnmostrarTabelaPesos = new JButton("Mostrar Pesos");
        btnDeletarObjeto = new JButton("Deletar Objeto");
        btnDeletarCriterio = new JButton("Deletar Criterio");
        btnDeletarPeso = new JButton("Deletar Pesos");
        btnDeletarComparacao = new JButton("Deletar Comparações");
        btnAtualizarObjeto = new JButton("Alterar Objeto");
        btnAtualizarCriterio = new JButton("Alterar Criterio");
        btnAtualizarPeso = new JButton("Alterar Pesos");
        btnmostrarTabelaComparacao = new JButton("Mostrar Comparacao");
        btnCompararObjeto = new JButton("Comparar Objetos");

        btnSair = new JButton("Sair");


        add(btnInserirObjeto);
        add(btnInserirCriterio);
        add(btnInserirpeso);
        add(btnmostrarTabelaObjeto);
        add(btnMostrarCriterio);
        add(btnmostrarTabelaPesos);
        add(btnDeletarObjeto);
        add(btnDeletarCriterio);
        add(btnDeletarPeso);
        add(btnDeletarComparacao);
        add(btnAtualizarObjeto);
        add(btnAtualizarCriterio);
        add(btnAtualizarPeso);
        add(btnmostrarTabelaComparacao);
        add(btnCompararObjeto);

        add(btnSair);




        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 150);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());




        // METODOS DE INSERIR NA TABELA
        btnInserirObjeto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeObjeto = JOptionPane.showInputDialog(InterfaceGrafica.this, "Digite o nome do objeto:");
                Objeto objeto = new Objeto(nomeObjeto);
                TabelaDAO.inserirObjeto(objeto);
                JOptionPane.showMessageDialog(InterfaceGrafica.this, "Objeto inserido com sucesso!");

            }
        });

        btnInserirCriterio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double notaQualidade = Double.parseDouble(JOptionPane.showInputDialog(InterfaceGrafica.this, "Digite de 0 a 5 para Qualidade"));

                    double notaAvaliacao = Double.parseDouble(JOptionPane.showInputDialog(InterfaceGrafica.this, "Digite de 0 a 5 para Avaliação"));

                    double notaNota = Double.parseDouble(JOptionPane.showInputDialog(InterfaceGrafica.this, "Digite de 0 a 5 para a Nota"));
                    Criterio criterio = new Criterio(notaQualidade,notaAvaliacao,notaNota);

                    TabelaDAO.inserirCriterio(criterio);


                    JOptionPane.showMessageDialog(InterfaceGrafica.this, "Critérios inseridos com sucesso!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(InterfaceGrafica.this, "Valor inválido. Certifique-se de digitar um número válido de 0 a 5.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnInserirpeso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double pesoQualidade = Double.parseDouble(JOptionPane.showInputDialog(InterfaceGrafica.this, "Digite o Peso de, 0 a 3 para Qualidade:"));
                    double pesoAvaliacao = Double.parseDouble(JOptionPane.showInputDialog(InterfaceGrafica.this, "Digite o Peso de, 0 a 3 para Avaliação:"));
                    double pesoNota = Double.parseDouble(JOptionPane.showInputDialog(InterfaceGrafica.this, "Digite o Peso de, 0 a 3 para Nota:"));
                    Pesos pesos = new Pesos(pesoQualidade,pesoAvaliacao,pesoNota);
                    TabelaDAO.Inserirpeso(pesos);

                    JOptionPane.showMessageDialog(InterfaceGrafica.this, "Pesos inseridos com sucesso!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(InterfaceGrafica.this, "Valor inválido. Certifique-se de digitar um número válido de 0 a 5.", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        /// METODO DE MOSTRAR RABELA
        btnmostrarTabelaComparacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<String> comparacao = TabelaDAO.mostrarTabelaComparacao();

                    StringBuilder result = new StringBuilder("Tabela de comparações:\n");
                    result.append(String.format("%-8s %-15s %-15s %-15s %-15s %-15s\n", "id", "Nome Objeto", "id_objeto", "id_criterio","id_pesos", "resultado"));

                    if (!comparacao.isEmpty()) {
                        int i = 0;
                        while (i < comparacao.size()) {
                            String id = comparacao.get(i++);
                            String nomeObjeto = comparacao.get(i++);
                            String idObjeto = comparacao.get(i++);
                            String idCriterio = comparacao.get(i++);
                            String id_pesos = comparacao.get(i++);
                            String resultado = comparacao.get(i++);

                            result.append(String.format("%-9s %-23s %-19s %-19s %-19s %-20s\n", id, nomeObjeto, idObjeto, idCriterio,id_pesos, resultado));
                        }
                    } else {
                        result.append("Nenhum registro encontrado.\n");
                    }

                    JTextArea textArea = new JTextArea(result.toString());
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);

                    JOptionPane.showMessageDialog(InterfaceGrafica.this, scrollPane, "Tabela de comparações", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(InterfaceGrafica.this, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnMostrarCriterio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Double> criterio = TabelaDAO.mostrarTabelaCriterio();

                    StringBuilder result = new StringBuilder("Tabela de Critérios:\n");
                    result.append(String.format(" %-10s %-19s %-16s %-15s\n","id", "Qualidade", "Avaliação", "Nota"));

                    if (!criterio.isEmpty()) {
                        int i = 0;
                        while (i < criterio.size()) {
                            int id = criterio.get(i++).intValue();
                            double qualidade = criterio.get(i++);
                            double avaliacao = criterio.get(i++);
                            double nota = criterio.get(i++);

                            result.append(String.format("%-13d %-27.2f %-14.2f %-11.2f\n",id, qualidade, avaliacao, nota));
                        }
                    } else {
                        result.append("Nenhum registro encontrado.\n");
                    }

                    JTextArea textArea = new JTextArea(result.toString());
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);

                    JOptionPane.showMessageDialog(InterfaceGrafica.this, scrollPane, "Tabela de Critérios", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(InterfaceGrafica.this, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        btnmostrarTabelaPesos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Double> pesos = TabelaDAO.mostrarTabelaPesos();

                    StringBuilder result = new StringBuilder("Tabela de Pesos:\n");
                    result.append(String.format("%-10s %-20s %-20s %-10s\n", "id", "Peso Qualidade", "Peso Avaliação", "Peso Nota"));

                    if (!pesos.isEmpty()) {
                        int i = 0;
                        while (i < pesos.size()) {
                            int id = pesos.get(i++).intValue();
                            double pesoQualidade = pesos.get(i++);
                            double pesoAvaliacao = pesos.get(i++);
                            double pesoNota = pesos.get(i++);

                            result.append(String.format("%-15d %-25.2f %-27.2f %-18.2f\n", id, pesoQualidade, pesoAvaliacao, pesoNota));
                        }
                    } else {
                        result.append("Nenhum registro encontrado.\n");
                    }

                    JTextArea textArea = new JTextArea(result.toString());
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);

                    JOptionPane.showMessageDialog(InterfaceGrafica.this, scrollPane, "Tabela de Pesos", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(InterfaceGrafica.this, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnmostrarTabelaObjeto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<String> objetos = TabelaDAO.mostrarTabelaObjeto();

                    StringBuilder result = new StringBuilder("Tabela de Objeto:\n");
                    result.append(String.format("%-10s %-28s\n", "id", "Objetos"));

                    if (!objetos.isEmpty()) {
                        int i = 0;
                        while (i < objetos.size()) {
                            int id = Integer.parseInt(objetos.get(i++));
                            String nome = objetos.get(i++);

                            result.append(String.format("%-10d %-28s\n", id, nome));
                        }
                    } else {
                        result.append("Nenhum registro encontrado.\n");
                    }

                    JTextArea textArea = new JTextArea(result.toString());
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);

                    JOptionPane.showMessageDialog(InterfaceGrafica.this, scrollPane, "Tabela de objeto", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(InterfaceGrafica.this, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // METODO DE DELETA NA TABELA

        btnDeletarObjeto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idStr = JOptionPane.showInputDialog("Digite o ID do objeto a ser excluído:");

                try {
                    int id = Integer.parseInt(idStr);


                    boolean idVinculadoComparacao = TabelaDAO.verificarIDNaTabelaComparacaoo(id);

                    if (idVinculadoComparacao) {
                        JOptionPane.showMessageDialog(null, "O ID se refere a uma chave estrangeira na tabela 'comparacao'.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }


                    boolean idExisteCriterio = TabelaDAO.verificarIDNaTabelaObjeto(id);

                    if (!idExisteCriterio) {
                        JOptionPane.showMessageDialog(null, "ID inválido. Verifique o ID informado.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    boolean excluido = TabelaDAO.deletarObjeto(id);

                    if (excluido) {
                        JOptionPane.showMessageDialog(null, "Objeto excluído com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum Objeto foi excluído. Verifique o ID informado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido. Digite um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnDeletarCriterio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idStr = JOptionPane.showInputDialog("Digite o ID do Criterio a ser excluído:");

                try {
                    int id = Integer.parseInt(idStr);


                    boolean idVinculadoComparacao = TabelaDAO.verificarIDNaTabelaComparacaoc(id);

                    if (idVinculadoComparacao) {
                        JOptionPane.showMessageDialog(null, "O ID se refere a uma chave estrangeira na tabela 'comparacao'.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }


                    boolean idExisteCriterio = TabelaDAO.verificarIDNaTabelaCritrio(id);

                    if (!idExisteCriterio) {
                        JOptionPane.showMessageDialog(null, "ID inválido. Verifique o ID informado.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    boolean excluido = TabelaDAO.DeletarCriterio(id);

                    if (excluido) {
                        JOptionPane.showMessageDialog(null, "Criterio excluído com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum Criterio foi excluído. Verifique o ID informado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido. Digite um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });



        btnDeletarPeso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idStr = JOptionPane.showInputDialog("Digite o ID do Peso a ser excluído:");

                try {
                    int id = Integer.parseInt(idStr);


                    boolean idVinculadoComparacao = TabelaDAO.verificarIDNaTabelaComparacaop(id);

                    if (idVinculadoComparacao) {
                        JOptionPane.showMessageDialog(null, "O ID se refere a uma chave estrangeira na tabela 'comparacao'.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }


                    boolean idExistePesos = TabelaDAO.verificarIDNaTabelaPesos(id);

                    if (!idExistePesos) {
                        JOptionPane.showMessageDialog(null, "ID inválido. Verifique o ID informado.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    boolean excluido = TabelaDAO.DeletarPeso(id);

                    if (excluido) {
                        JOptionPane.showMessageDialog(null, "Peso excluído com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum peso foi excluído. Verifique o ID informado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido. Digite um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });





        btnDeletarComparacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idStr = JOptionPane.showInputDialog("Digite o ID da Comparacao a ser excluído:");

                try {
                    int id = Integer.parseInt(idStr);

                    boolean idExisteComparacao = TabelaDAO.verificarIDNaTabelaComparacao(id);

                    if (!idExisteComparacao) {
                        JOptionPane.showMessageDialog(null, "ID inválido. Verifique o ID informado.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    boolean excluido = TabelaDAO.DeletarComparacao(id);

                    if (excluido) {
                        JOptionPane.showMessageDialog(null, "Comparação excluída com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhuma comparação foi excluída. Verifique o ID informado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido. Digite um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });



        btnAtualizarPeso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idStr = JOptionPane.showInputDialog("Digite o ID do Peso a ser atualizado:");

                try {
                    int id = Integer.parseInt(idStr);


                    boolean idVinculadoComparacao = TabelaDAO.verificarIDNaTabelaComparacaop(id);

                    if (idVinculadoComparacao) {
                        JOptionPane.showMessageDialog(null, "O ID se refere a uma chave estrangeira na tabela 'comparacao'. Não é possível atualizar o peso.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }


                    boolean idExistePesos = TabelaDAO.verificarIDNaTabelaPesos(id);

                    if (!idExistePesos) {
                        JOptionPane.showMessageDialog(null, "ID inválido. Verifique o ID informado.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }


                    String pesoQualidadeStr = JOptionPane.showInputDialog("Digite o novo valor para o peso de qualidade:");
                    String pesoAvaliacaoStr = JOptionPane.showInputDialog("Digite o novo valor para o peso de avaliação:");
                    String pesoNotaStr = JOptionPane.showInputDialog("Digite o novo valor para o peso de nota:");

                    double pesoQualidade = Double.parseDouble(pesoQualidadeStr);
                    double pesoAvaliacao = Double.parseDouble(pesoAvaliacaoStr);
                    double pesoNota = Double.parseDouble(pesoNotaStr);

                    boolean atualizado = TabelaDAO.AtualizarPeso(id, pesoQualidade, pesoAvaliacao, pesoNota);

                    if (atualizado) {
                        JOptionPane.showMessageDialog(null, "Peso atualizado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum peso foi atualizado. Verifique o ID informado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido. Digite um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnAtualizarCriterio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idStr = JOptionPane.showInputDialog("Digite o ID do Criterio a ser atualizado:");

                try {
                    int id = Integer.parseInt(idStr);


                    boolean idVinculadoComparacao = TabelaDAO.verificarIDNaTabelaComparacaoc(id);

                    if (idVinculadoComparacao) {
                        JOptionPane.showMessageDialog(null, "O ID se refere a uma chave estrangeira na tabela 'comparacao'. Não é possível atualizar o peso.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }


                    boolean idExisteCriterio = TabelaDAO.verificarIDNaTabelaCritrio(id);

                    if (!idExisteCriterio) {
                        JOptionPane.showMessageDialog(null, "ID inválido. Verifique o ID informado.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }


                    String qualidadeStr = JOptionPane.showInputDialog("Digite o Novo Valor de 0 a 5 Para Qualidade:");
                    String avaliacaoStr = JOptionPane.showInputDialog("Digite o Novo Valor de 0 a 5 Para Avaliação:");
                    String notaStr = JOptionPane.showInputDialog("Digite o Novo Valor de 0 a 5 Para Nota:");

                    double qualidade = Double.parseDouble(qualidadeStr);
                    double avaliacao = Double.parseDouble(avaliacaoStr);
                    double nota = Double.parseDouble(notaStr);

                    boolean atualizado = TabelaDAO.atualizarCriterio(id, qualidade, avaliacao, nota);

                    if (atualizado) {
                        JOptionPane.showMessageDialog(null, "Criterio atualizado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum Criterio foi atualizado. Verifique o ID informado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido. Digite um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnAtualizarObjeto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idStr = JOptionPane.showInputDialog("Digite o ID do Objeto a ser atualizado:");

                try {
                    int id = Integer.parseInt(idStr);


                    boolean idVinculado = TabelaDAO.verificarIDNaTabelaComparacaoo(id);

                    if (idVinculado) {
                        JOptionPane.showMessageDialog(null, "O ID se refere a uma chave estrangeira na tabela 'comparacao'. Não é possível atualizar o peso.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }


                    boolean idExisteObjeto = TabelaDAO.verificarIDNaTabelaObjeto(id);

                    if (!idExisteObjeto) {
                        JOptionPane.showMessageDialog(null, "ID inválido. Verifique o ID informado.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }


                    String nome = JOptionPane.showInputDialog("Digite o Novo Nome do Objeto:");

                    boolean atualizado = TabelaDAO.AtualizarObjeto(id, nome);

                    if (atualizado) {
                        JOptionPane.showMessageDialog(null, "Objeto atualizado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum Objeto foi atualizado. Verifique o ID informado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido. Digite um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn = null;
                try {
                    conn = ConexaoBanco.obterConexao();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


                JOptionPane.showMessageDialog(InterfaceGrafica.this, "Saindo do banco de dados...");


                ConexaoBanco.fecharConexao(conn);


                JOptionPane.showMessageDialog(InterfaceGrafica.this, "Conexão encerrada com sucesso!");
            }
        });



        btnCompararObjeto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {


                    //INSERIR O OBJETO  NA TABELA( OBJETO1)
                    String nomeObjeto1 = JOptionPane.showInputDialog(null, "Digite o Nome do Primeiro Objeto:");
                    Objeto objeto = new Objeto(nomeObjeto1);
                    TabelaDAO.inserirObjetoNoBanco(objeto);



                    // INSERIR OS CRITERIOS NA TABELA DE CRITERIO( OBJETO1)

                    double qualidadeObjeto1 = Double.valueOf(JOptionPane.showInputDialog(null, "Digite de 0 a 5 Para Qualidade do(a): "+nomeObjeto1));
                    double avaliacaoObjeto1 = Double.valueOf(JOptionPane.showInputDialog(null, "Digite de 0 a 5 Para Avaliação do(a): "+nomeObjeto1));
                    double notaObjeto1 = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite de 0 a 5 Para a Nota do(a): "+nomeObjeto1));
                    TabelaDAO.inserirCriterioNoBanco(qualidadeObjeto1,avaliacaoObjeto1,notaObjeto1);

                    //INSERIR OS PESOS NA TABELA PESOS
                    double pesoqualidade = Double.valueOf(JOptionPane.showInputDialog(null, "Digite o Peso de 0 a 3 Para Qualidade"));
                    double pesoavaliacao = Double.valueOf(JOptionPane.showInputDialog(null, "Digite o Pesos de 0 a 3 Para Avaliação"));
                    double pesonota = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o Pesos de 0 a 3 Para a Nota:"));

                   TabelaDAO.inserirPesosNoBanco(pesoqualidade,pesoavaliacao,pesonota);


                    //INSERIR O OBJETO  NA TABELA( OBJETO2)
                    String nomeObjeto2 = JOptionPane.showInputDialog(null, "Digite o Nome do Segundo Objeto:");
                    objeto = new Objeto(nomeObjeto2);
                    TabelaDAO.inserirObjetoNoBanco(objeto);

                    // INSERIR OS CRITERIOS NA TABELA DE CRITERIO( OBJETO2)
                    double qualidadeObjeto2 = Double.valueOf(JOptionPane.showInputDialog(null, "Digite de 0 a 5 Para Qualidade do(a): "+nomeObjeto2));
                    double avaliacaoObjeto2 = Double.valueOf(JOptionPane.showInputDialog(null, "Digite de 0 a 5 Para Avaliação do(a): "+nomeObjeto2));
                    double notaObjeto2 = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite de 0 a 5 Para a Nota do(a): "+nomeObjeto2));

                    TabelaDAO.inserirCriterioNoBanco(qualidadeObjeto2,avaliacaoObjeto2,notaObjeto2);






                    //INSERIR PESOS NA TABELA PESOS (PESOS DE COMPARACÃO AMBOS CRITERIOS)

                    int idCriterio1 = TabelaDAO.inserirCriterioNoBanco(qualidadeObjeto1, avaliacaoObjeto1, notaObjeto1);
                    int idCriterio2 = TabelaDAO.inserirCriterioNoBanco(qualidadeObjeto2, avaliacaoObjeto2, notaObjeto2);
                    int idObjeto1 = TabelaDAO.obterIdObjetoPorNome(nomeObjeto1);
                    int idObjeto2 = TabelaDAO.obterIdObjetoPorNome(nomeObjeto2);
                    int idpesos =TabelaDAO.obterIdPeso(pesoqualidade,pesoavaliacao,pesonota);


                    double resultadoObjeto1 = TabelaDAO.calcularResultado(qualidadeObjeto1, avaliacaoObjeto1, notaObjeto1, pesoqualidade, pesoavaliacao, pesonota);
                    double resultadoObjeto2 = TabelaDAO.calcularResultado(qualidadeObjeto2, avaliacaoObjeto2, notaObjeto2, pesoqualidade, pesoavaliacao, pesonota);

                    TabelaDAO.inserirComparacaoNoBanco(nomeObjeto1,idObjeto1,idCriterio1,idpesos, resultadoObjeto1);
                    TabelaDAO.inserirComparacaoNoBanco(nomeObjeto2, idObjeto2, idCriterio2,idpesos, resultadoObjeto2);



                    String mensagemComparacao = TabelaDAO.compararResultados(nomeObjeto1, nomeObjeto2, resultadoObjeto1, resultadoObjeto2);
                    System.out.println(mensagemComparacao);











                    // Comparar os resultados e exibir mensagem
                    if (resultadoObjeto1 > resultadoObjeto2) {
                        JOptionPane.showMessageDialog(null, "O(a) " + nomeObjeto1 +", Obteve o Resultado de = "+ resultadoObjeto1+ "\nO(a) " + nomeObjeto2 + " ,Obteve o Resultado de = "+resultadoObjeto2+"\nPortanto: O(a) "  +nomeObjeto1+ " , e Melhor.");
                    } else if (resultadoObjeto2 > resultadoObjeto1) {
                        JOptionPane.showMessageDialog(null, "O(a) " + nomeObjeto2 + ", Obteve o Resulstdado de = "+resultadoObjeto2+ "\nO(a) "+ nomeObjeto1 + " ,Obteve o Resultado de = " + resultadoObjeto1+"\nPortanto: O(a) "  +nomeObjeto2+ " , e Melhor.");
                    } else {
                        JOptionPane.showMessageDialog(null, "O(a) " + nomeObjeto1 +", Obteve o Resulstdado de = "+ resultadoObjeto1+ "\nO(a) " + nomeObjeto2 + " ,Obteve o Resultado de = "+resultadoObjeto2+"\nPortanto: O(a)" + nomeObjeto1 + ", e " + nomeObjeto2 + " , São iguais.");
                    }


                    // INSERIR OS OBJETOS NA TABELA COMPARACAO COM SEUS RESULTADOS



                    TabelaDAO.compararObjetos(nomeObjeto1,nomeObjeto2,qualidadeObjeto1,qualidadeObjeto2,avaliacaoObjeto1,avaliacaoObjeto2,notaObjeto1,notaObjeto2);




                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valor inválido. Certifique-se de digitar um número válido de 0 a 5.", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
//
        });








    }


}
