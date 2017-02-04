/*
 * Name  : Zhang Yuan (Alex)
 * Matric No. : A0157059X
 */

import java.util.*;

public class Bombs {
  
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);
    int row = sc.nextInt();
    int col = sc.nextInt();
    Grid grid = new Grid(createGrid(row, col, sc));
    
//    grid.print();
    
    int bombNumber = sc.nextInt();
    
    q1(bombNumber, grid, sc); //query1
    q2(grid);
  }
  
  
  public static int[][] createGrid(int row, int col, Scanner sc){
    
    int[][] grid = new int[row][col];
    
    for (int i = 0; i < row*col; i++){
      grid[i/col][i%col] = sc.nextInt();
    }
    return grid;
  }
  
  
  public static void q1(int bbNo, Grid grid, Scanner sc){
    
    int row = grid.getRow();
    int col = grid.getCol();
    
    //input all bombs into an array
    int[] bbs = new int[bbNo];
    for (int i = 0; i < bbNo; i++)
      bbs[i] = sc.nextInt(); 
    
    //output best strategies one by one
    for (int i = 0; i < bbNo; i++){
      int bbSz = bbs[i];
      grid.cmprNotgtD(bbSz);//find best strategy with the given bomb size
      System.out.println(grid.getBstStgyQ1()[1] +" "+ grid.getBstStgyQ1()[2]);
//      grid.print();
      grid.resetBstStgyQ1();
//      System.out.println("reset Q1, "+ Arrays.toString(grid.getBstStgyQ1()));
    }
  }
  
  public static void q2(Grid grid){
    int maxBbSz = (grid.getRow()>grid.getCol())?grid.getRow():grid.getCol();
    if (maxBbSz%2==0)
      maxBbSz++;
    //loop for different bbSzs
    for (int bbSz = 1; bbSz <= maxBbSz; bbSz+=2){
      grid.cmprScr(bbSz);
      
    }
    
    System.out.println(grid.getBstStgyQ2()[1] +" "+ grid.getBstStgyQ2()[2] +" "+ grid.getBstStgyQ2()[0]);
  }
}

class Grid {
  
  private int[][] grid;
  private int row;
  private int col;
  private int[] bstStgyQ1 = new int[4];  //an array of size 4 denoting {bombSize, xCo, yCo, no. of target destroyed}. 
  
  private int[] bstStgyQ2 = new int[4];  // an array of size 4 denoting {bombSize, xCo, yCo, score}. 
  
  
  public int getRow (){
    
    return this.row;
    
  }
  
  public int getCol(){
    
    return this.col;
    
  }
  
  
  public int[] getBstStgyQ1(){
    
    return this.bstStgyQ1;
    
  }
  
  
  public int[] getBstStgyQ2(){
    
    return this.bstStgyQ2;
    
  }
  
  
  public void resetBstStgyQ1(){
    
    this.bstStgyQ1 = new int[4];
    
  }
  
  
  public Grid(int[][] gridIn){
    
    this.grid = gridIn;
    this.row = grid.length;
    this.col = grid[0].length;
    bstStgyQ1[3] = 0;
    bstStgyQ2[3] = row*col*-1;
    
  }
  
  
  //compute no. of target that can be destroyed with a bomb of given size deployed at given coordinates of deploying position
  public int cmptgtD(int bbSz, int xCo, int yCo){
    
    //expand the grid, expended cells denoted by -1
    int rowExp = row + bbSz - 1;
    int colExp = col + bbSz - 1;
    int[][] gridExp = new int[rowExp][colExp];
    
    for (int r = 0; r < rowExp; r++){
      for (int c = 0; c < colExp; c++){
        if (r >= bbSz/2 && r < row + bbSz/2 && c >= bbSz/2 && c < col + bbSz/2 )
          gridExp[r][c] = grid[r-bbSz/2][c-bbSz/2];
        else
          gridExp[r][c] = -1; 
      }
    }
    
    int tgtD = 0;
    for (int r = xCo; r < xCo + bbSz; r++){ //loop through the exploded area to look for targets
      for (int c = yCo; c < yCo + bbSz; c++){
        if(gridExp[r][c] == 1)
          tgtD++;
      }
    }
//    System.out.println("land a bomb of size "+bbSz+" at "+xCo+" "+yCo+" destroys "+tgtD+" targets.");
    return tgtD;
  }
  
  
  //compute score at given coordinates of deploying position with a bomb of given size
  public int cmptScr(int bbSz, int xCo, int yCo){
    
    //expand the grid, expended cells denoted by -1
    int rowExp = row + bbSz - 1;
    int colExp = col + bbSz - 1;
    int[][] gridExp = new int[rowExp][colExp];
    
    for (int r = 0; r < rowExp; r++){
      for (int c = 0; c < colExp; c++){
        if (r >= bbSz/2 && r < row + bbSz/2 && c >= bbSz/2 && c < col + bbSz/2 )
          gridExp[r][c] = grid[r-bbSz/2][c-bbSz/2];
        else
          gridExp[r][c] = -1; 
      }
    }
    
    //loop through the exploded area to compute score based on cell content
    int score = 0;
    for (int r = xCo; r <= xCo + bbSz -1 ; r++){
      for (int c = yCo; c <= yCo + bbSz - 1; c++){
        switch(gridExp[r][c]){
          case -1:
            break;
          case 0:
            score -= 1; 
            break;
          case 1:
            score += 3; 
            break;
        }
      }
    }
//    System.out.println("land a bomb of size "+bbSz+" at "+xCo+" "+yCo+" scores "+score);
    return score;
  }
  
  
  //with a bomb of given size, compare no. of tgt destroyed with DIFFERENT deploying coordinates to update best strategy for Q1                 
  public void cmprNotgtD (int bbSz){
    
    for(int r = 0; r < this.row; r++){
      for (int c = 0; c < this.col; c++){ //loops the deployed position through the grid
        int noTgtD = cmptgtD(bbSz, r, c);
        if(noTgtD > bstStgyQ1[3]){
          this.bstStgyQ1[0] = bbSz;
          this.bstStgyQ1[1] = r;
          this.bstStgyQ1[2] = c;
          this.bstStgyQ1[3] = noTgtD;
//          System.out.println("with a bomb of size "+bbSz+", best to land it at "+r+" "+c+" to destroys "+noTgtD+" targets");
        }
        
      }
    }
  }
  
  
  //with a bomb of given size, compare score at DIFFERENT deploying coordinates to update best strategy for Q2                   
  public void cmprScr (int bbSz){
    
    for(int r = 0; r < this.row; r++){
      for (int c = 0; c < this.col; c++){
        int score = cmptScr(bbSz, r, c);
        if(score > bstStgyQ2[3]){
          this.bstStgyQ2[0] = bbSz;
          this.bstStgyQ2[1] = r;
          this.bstStgyQ2[2] = c;
          this.bstStgyQ2[3] = score;
        }
//        System.out.println("land a bomb of size "+bbSz+" at "+r+" "+c+" scores "+score);
      }
    }
  }
  
  
  //print the current grid
  public void print(){ 
    
    for (int i = 0; i < row; i++){
      for (int j = 0; j < col; j++){
        System.out.print(grid[i][j]+" ");
      }
      System.out.println();
    }
    
  }
}       






