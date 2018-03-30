package dbolas;
import javax.swing.JFrame;
public class DBolas {
    public static void main(String[] args) {
        Ventana ventana= new Ventana();
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
    }
}
