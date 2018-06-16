import java.awt.Color;
import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
public class GameGrid{
   private List<Vehicle> vehicleList = new ArrayList<Vehicle>();
   private boolean[][] positionMap = new boolean[6][6];
   
   public GameGrid(){
      setLevel("");
   }
   public GameGrid(String level){
      setLevel(level);
   }
   public void setLevel(String level){
      Scanner nextVehicle = new Scanner(level);
      while(nextVehicle.hasNext()){
         this.vehicleList.add(new Vehicle(nextVehicle.next()));
      }
      for(int x = 0; x < 6; x++){
         for(int y = 0; y < 6; y++){
            this.positionMap[x][y] = false;
         }
      }
      updateMap();
   }
   public boolean moveCarTo(int n, int pos){
      Vehicle vehicle = vehicleList.get(n); 
      List<Point> vehiclePos = vehicle.getPosition();
      if(checkMap(vehiclePos)){
         if(vehicle.isVerticle()){
            for(int vP = 0; vP < vehiclePos.size(); vP++){
              vehiclePos.get(pos).setLocation(vehiclePos.get(pos).getX(), n + pos); 
            }
         }else{
            for(int vP = 0; vP < vehiclePos.size(); vP++){
              vehiclePos.get(pos).setLocation(n + pos, vehiclePos.get(pos).getY()); 
            }
         }
         updateMap();
         return true;  
      }
      return false;
   }
   public JPanel drawBoard(int x, int y, int Dimension){
      JPanel board = new JPanel(null);
      board.setBounds(x,y,Dimension,Dimension);
      int boxDimension = Dimension / 6;
      for(int n = 0; n < vehicleList.size(); n++){
         Vehicle vehicle = vehicleList.get(n);
         Swatch vehicleSwatch = vehicle.getType();
         List<Point> vehiclePos = vehicle.getPosition();
         int carX = x + (int)vehiclePos.get(0).getX() * boxDimension;
         int carY = y + (int)vehiclePos.get(0).getY() * boxDimension;
         JLabel vehicleUI = new JLabel(n + "");
         if(vehicle.isVerticle()){
            vehicleUI.setBounds(carX, carY, boxDimension, boxDimension * vehiclePos.size());
         }else{
            vehicleUI.setBounds(carX, carY, boxDimension * vehiclePos.size(), boxDimension);
         }
         vehicleUI.setBackground(vehicleSwatch.getC1());
         vehicleUI.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(vehicleSwatch.getC2()),BorderFactory.createLineBorder(vehicleSwatch.getC1())));
         board.add(vehicleUI);
      }
      return board;
   }
   public String toString(){
      String summary = "";
      for(int n = 0; n < vehicleList.size(); n++){
         Vehicle vehicle = vehicleList.get(n);
         summary += vehicle.toString();
      }
      return summary;
   }
   
   private boolean checkMap(List<Point> vehicle){
      for(int vB = 0; vB < vehicle.size(); vB++){
         Point vehicleBox = vehicle.get(vB);
         if(positionMap[(int)vehicleBox.getX()][(int)vehicleBox.getY()]){
            return false;
         }
      }
      return true;
   }
   private void updateMap(){
      for(int vehicle = 0; vehicle < vehicleList.size(); vehicle++){
         List<Point> vehiclePos = vehicleList.get(vehicle).getPosition();
         for(int vB = 0; vB < vehiclePos.size(); vB++){
            Point vehicleBox = vehiclePos.get(vB);
            this.positionMap[(int)vehicleBox.getX()][(int)vehicleBox.getY()] = true;
         }
      }
   }
}