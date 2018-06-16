import java.awt.Color;
import java.awt.Point;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
public class Vehicle{
   private static final Map<Character, Swatch> swatches = 
      new TreeMap<Character, Swatch>(){
         {
            put(new Character('A'), new Swatch(new Color(255, 77, 77), Color.RED));
            put(new Character('C'), new Swatch(new Color(255, 153, 0),new Color(255, 128, 0)));
            put(new Character('T'), new Swatch(Color.BLUE, new Color(0, 51, 204)));
         }};
   
   private Swatch type;
   private char typeName;
   private List<Point> positions = new ArrayList<Point>();
   
   public Vehicle(String summary){
      this.typeName = summary.charAt(0);
      this.type = swatches.get(Character.valueOf(typeName));
      List<Point> position = new ArrayList<Point>();
      for(int pointPos = 3; pointPos < summary.length(); pointPos += 5){
            int x = Character.getNumericValue(summary.charAt(pointPos));
            int y = Character.getNumericValue(summary.charAt(pointPos + 2));
            positions.add(new Point(x,y)); 
      }
   }
   
   public Swatch getType(){
      return type;
   }
   public char getTypeName(){
      return typeName;
   }
   public List<Point> getPosition(){
      return positions;
   }
   public void setPosition(List<Point> vPos){
      this.positions = vPos;
   }
   public String toString(){
      String summary = typeName + "";
      for(int pos = 0; pos < positions.size(); pos++){
         summary += positions.get(pos).toString();
      }
      return summary + " ";
   }
   public boolean isVerticle(){
      return positions.get(0).getX() == positions.get(1).getX();
   }
   
   private List<Point> sort(List<Point> position){
      Point lastPoint = position.get(0);
      int lastDistance = (int)(lastPoint.getX() + lastPoint.getY());
      for(int pos = 1; pos < position.size(); pos++){
         Point point = position.get(pos);
         int distance = (int)(point.getX() + point.getY());
         if(distance < lastDistance){
            Point pSave = position.get(pos - 1);
            position.set(pos, position.get(pos - 1));
            position.set(pos - 1, point);
            pos = 1;
         }else if(distance == lastDistance){
            position.remove(pos);
            pos = 1;
         }
      }
      return position;
   }
}