import java.awt.Color;
public class Swatch{
   private Color c1;
   private Color c2;
   public Swatch(Color c1, Color c2){
      this.c1 = c1;
      this.c2 = c2;
   }
   public Color getC1(){
      return c1;
   }
   public Color getC2(){
      return c2;
   }
}