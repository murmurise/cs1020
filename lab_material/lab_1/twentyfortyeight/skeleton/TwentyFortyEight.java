/*
 * Name  : Zhang Yuan (Alex)
 * Matric No.  : A0157059X
 */

import java.util.*;

public class TwentyFortyEight {
  
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);
    Puzzle pz = new Puzzle(readPuzzle(sc));
    int direction = sc.nextInt();
    pz.slide(direction);
    pz.add(direction);
    pz.slide(direction);
    pz.print();
  }
  
  
  public static int[][] readPuzzle(Scanner sc){
    
    int[][] pz = new int[4][4];
    int len = 4;
    for (int r = 0; r < len; r++){
      for (int c = 0; c < len; c++){
        pz[r][c] = sc.nextInt();
      }
    }
    
    return pz;
    
  }
}



class Puzzle {
  
  private int[][] puzzle;
  private int len;
  
  public Puzzle(int[][] puzzleIn){
    
    this.puzzle = puzzleIn;
    this.len = puzzle.length;
    
  }
  
  
  public void slide (int direction){  //0, 1, 2, 3 - left, up, right, down 
    
    switch (direction){
      
      case 0: case 2:{    
        
        for (int r = 0; r < len; r++){ //collect all numbered tiles in a row into an array
          int[]numTiles = new int[4]; 
          int nTIdx = 0; //index of array "numbered tiles(in a row/column)"
          
          for(int c = 0; c < len; c++){
            if (this.puzzle[r][c] != 0){
              numTiles[nTIdx] = this.puzzle[r][c];
              //denbug
//              System.out.println(Arrays.toString(numTiles)+"\n ");
              nTIdx ++;
            }
          }
          
          changeRow(r, direction, numTiles);
//          print();
          
        }    
      }
      break;
      
      case 1: case 3:{
        
        for (int c = 0; c < len; c++){ //collect all numbered tiles in a column into an array
          int[]numTiles = new int[4]; 
          int nTIdx = 0; //index of array "numbered tiles(in a row/column)"
          
          for(int r = 0; r < len ; r++){   
            if (this.puzzle[r][c] != 0){
              numTiles[nTIdx] = this.puzzle[r][c];
              nTIdx ++;
            }    
          }
          
          changeCol(c, direction, numTiles);
//          print();
          
        }
      }
    }
  }
  public void changeRow(int rowNum, int direction, int[] tiles){
    
    int[] newTiles = new int[len];//create a new line, to be inserted into the puzzle
    
    if(direction == 0) //left
      newTiles = tiles;
    else if (direction == 2)//right
    {
      int diff = 0;
      for (int i = 0; i < len; i++){ 
        if(tiles[i] == 0){
          diff = len - i;
          break;
        }
      }
      for (int i = 0; i < len && tiles[i] != 0; i++){
        newTiles[diff+i] = tiles[i];
      }
    }
    
    this.puzzle[rowNum] = newTiles;
  }   
  
  
  public void changeCol(int colNum, int direction, int[] tiles){
    
    int[] newTiles = new int[len];//create a new line, to be inserted into the puzzle
    
    if(direction == 1) //up
      newTiles = tiles;
    else if (direction == 3)//down
    {
      int diff = 0;
      for (int i = 0; i < len; i++){ 
        if(tiles[i] == 0){
          diff = len - i;
          break;
        }
      }
      for (int i = 0; i < len && tiles[i] != 0; i++){
        newTiles[diff+i] = tiles[i];
      }
    } 
    
    
    //change the designated column
    for (int i = 0; i < len; i++){
      this.puzzle[i][colNum] = newTiles[i];
    }
    
  }
  
  public void add(int direction){
    
    //expand the puzzle
    
    int[][] pzExp = new int[len+2][len+2];
    for (int r = 0; r < len+2; r++){
      for (int c = 0; c < len+2; c++){
        if (r >= 1 && r < len + 1 && c >= 1 && c < len + 1 )
          pzExp[r][c] = puzzle[r-1][c-1];
        else
          pzExp[r][c] = -1; 
      }
    }
    
    //loop through the "inner" of the expanded puzzle to find adj identical items
    
    switch(direction){
      
      case 0:{ // left
        for(int r = 1; r < len+1; r ++){
          for (int c = 1; c < len+1; c++){
            if (pzExp[r][c] == pzExp[r][c+1]){
              pzExp[r][c]*= 2;
              pzExp[r][c+1] = 0;
            }
          }
        }
      }
      break;
      
      case 1:{ // up
        for(int c = 1; c < len+1; c++){
          for (int r = 1; r < len+1; r++){
            if (pzExp[r][c] == pzExp[r+1][c]){
              pzExp[r][c]*= 2;
              pzExp[r+1][c] = 0;
            }
          }
        }
      }
      break;
      
      case 2:{ // right
        for(int r = 1; r < len+1; r ++){
          for (int c = 1; c < len+1;){
            if (pzExp[r][c] == pzExp[r][c-1]){
              pzExp[r][c]*= 2;
              pzExp[r][c-1] = 0;
              c += 2; // case such as [2,2,4,4] should give[0,4,0,8] rather than [0,0,8,4]
            }
            else
              c ++;
          }
        }
      }
      break;
      
      case 3:{ // down
        for(int c = 1; c < len+1; c++){
          for (int r = 1; r < len+1; ){
            if (pzExp[r][c] == pzExp[r-1][c]){
              pzExp[r][c]*= 2;
              pzExp[r-1][c] = 0;
              r += 2; 
            }
            else
              r ++;
            
          }
          //debug
//          for (int i = 0; i < len + 2; i ++){
//            for (int j = 0; j < len + 2; j++){
//              System.out.print(pzExp[i][j]+" ");
//            }
//            System.out.println();
//          }
//          System.out.println();
        }
      }
      break;
    }
    
    
    //update the puzzle
    for (int r = 0; r < len; r++){
      for (int c = 0; c < len; c++){
        this.puzzle[r][c] = pzExp[r+1][c+1]; 
      }
    }
    
  }
  
  public void print(){
    
    for (int i = 0; i < len; i++){
      for (int j = 0; j < len; j++){
        System.out.print(puzzle[i][j] + ((j==len-1)?"\n":" "));
      }
      
    }
  }
}


