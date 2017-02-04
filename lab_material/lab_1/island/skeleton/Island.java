/*
 Name: Zhang Yuan(Alex)
 Matric No.:A0157059X
 */

import java.util.*;

public class Island {
  public static void main(String[] args){
    
    Scanner sc = new Scanner(System.in);
    Map map = new Map(readMap(sc));
    
    System.out.println(map.countIsland());
  }
  
  public static int[][] readMap(Scanner sc){
    
    int rowNum = sc.nextInt();
    int colNum = sc.nextInt();
    int[][] map = new int[rowNum][colNum];
    
    for (int r = 0; r < rowNum; r++){
      for (int c = 0; c < colNum; c++){
        map[r][c] = sc.nextInt();
      }
    }
    
    return map;
    
  }
  
  
}

class Map{
  
  int[][] map;
  int[][] mapExp;
  int len;
  int wid;
  
  public Map(int[][] mapIn){
    map = mapIn;
    len = map.length;
    wid = map[0].length;
    mapExp = new int[len+2][wid+2]; //expand the map by adding water tiles around it 
    
    for (int r = 1; r < len+1; r ++){
      for (int c = 1; c < wid+1; c ++){
        mapExp[r][c] = map[r-1][c-1];
      }
    }
  }
  
  
  public int countIsland(){
    
    int count = 0; 
    for(int r = 0; r < len+2; r++){ //loop row by row to find a land tile
      for (int c = 0; c < wid+2; c++){
        if (mapExp[r][c]==1){
          //debug
//          System.out.println("found a land tile at "+r+" "+c);
          markOutIsland(r,c);
          count++;
        }
      }
    }
    
    return count; 
  }
  
  
  // mark out an island by flipping its "1" to "0" on the map
  public void markOutIsland(int row, int col){  //takes in the coordinates of a land tile
    
    int r = row, c = col;
    
    while (mapExp[r][c] != 0){
      while (mapExp[r][c] != 0){
        mapExp[r][c] = 0; 
        //debug
//        System.out.println("one tile flipped");
        c++;
      }
      c = col; 
      r++;
    }
    //debug
//    print();
//    System.out.println();
    
  }
  
  public void print(){
  
    for (int i = 0; i < len+2; i++){
      for (int j = 0; j < wid+2; j++){
        System.out.print(mapExp[i][j] + ((j==wid+1)?"\n":" "));
      }
    }
  }
}
