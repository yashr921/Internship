
public class Solver {
  public static int[][] GRID = {
      {9,0,0,1,0,0,0,0,5},
      {0,0,5,0,9,0,2,0,1},
      {8,0,0,0,4,0,0,0,0},
      {0,0,0,0,8,0,0,0,0},
      {0,0,0,7,0,0,0,0,0},
      {0,0,0,0,2,6,0,0,9},
      {2,0,0,3,0,0,0,0,6},
      {0,0,0,2,0,0,9,0,0},
      {0,0,1,9,0,4,5,7,0},
    };
  
  private int[][] board;
  public static final int EMPTY = 0;
  public static final int SIZE = 9;
  
  public Solver(int[][] board) {
    this.board = new int[SIZE][SIZE];
    
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE;j++) {
        this.board[i][j] = board[i][j];
      }
    }
  }
  
  private boolean isValid(int row, int column, int number,int[][] board) {
    //checks if the number is in the same row
    for (int i = 0; i < SIZE; i++) {
      if (board[row][i] == number) {
        return false;
      }
    }
    
    //checks if the number is in the same column
    for (int i = 0; i < SIZE; i++) {
      if (board[i][column] == number) {
        return false;
      }
    }
    
    //checks to see if the number is in the same 3x3 grid
    int rowBox = row - (row % 3);
    int columnBox = column - (column % 3);
    
    for (int i = rowBox; i < rowBox + 3; i++) {
      for (int j = columnBox; j < columnBox + 3;j++) {
        if (board[i][j] == number) {
          return false;
        }
      }
    }
    return true;
  }
  
  public int[] findEmpty(int[][] board) {
    for (int row = 0; row < SIZE; row++) {
      for (int col = 0; col < SIZE; col++) {
        if (board[row][col] == EMPTY) {
          int [] position = {row,col};
          return position;
        }
      }
    }
    return null;
  }
  
  private boolean solve(int[][] board) { 
    
    for (int row = 0; row < SIZE;row++) {
      for (int col = 0; col < SIZE;col++) {
        if (board[row][col] == EMPTY) {
          for (int num = 1; num < 10;num++) {
            if (isValid(row, col, num, board)) {
              board[row][col] = num;
            
              if (solve(board)) {
                return true;
              } else {
                board[row][col] = EMPTY;
              }
            }
          }
          return false;
        }
      }
    }
    return true;
  }
  public void printBoard() {
    for (int i = 0; i < SIZE;i++) {
      for (int j = 0;j < SIZE;j++) {
        System.out.print(board[i][j] + " ");
        if ((j + 1) % 3 == 0) {
          System.out.print("| ");
        }
      }
      if ((i + 1) % 3 == 0) {
        System.out.println();
        System.out.print("--------------------------");
      }
      System.out.println();
    }
  }
  
  public void solveBoard() {
    solve(board);
  }
  public static void main(String[] args) {
    Solver soduku = new Solver(GRID);
    soduku.printBoard();
    soduku.solveBoard();
    soduku.printBoard();
  }

}
