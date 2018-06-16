import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.util.Scanner;
public class GameGridTest extends JFrame{
   public GameGridTest(){
      super.setTitle("Game Test");
      super.setSize(700,700);
      super.setDefaultCloseOperation(EXIT_ON_CLOSE);
      super.setLocationRelativeTo(null);
   }
   public static void main(String[] args){
      Scanner console = new Scanner(System.in);
      JFrame frame = new GameGridTest();
      frame.setVisable(true);
   }
}