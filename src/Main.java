import javax.swing.*;


public class Main {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/teste02";
        String user = "postgres";
        String password = "201918";

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                InterfaceGrafica interfaceGrafica = new InterfaceGrafica();
                interfaceGrafica.setVisible(true);



            }
        });
    }
}
















