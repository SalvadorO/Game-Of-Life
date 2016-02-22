package TEMP;

public class oppg4main {

	public static void main(String[] args) {
		
		int[][] table = 
				new int [][]	{{0,0,0,1},
								 {1,0,1,1},
			 					 {1,0,0,0},
			 					 {1,0,0,0}
								};

		gameboard gb = new gameboard(table);
		nextGeneration ng = new nextGeneration(gb);
								
		int x = 1;
		int y = 3;
		
		System.out.println("\nCurrent board: "+gb.toString());
		System.out.println("Current gen");
		System.out.println("Cell is alive: "+ng.isAlive(x,y));
		System.out.println("Nbours: "+ng.countNeighbours(x, y));
			
		System.out.println("\nNext gen:");
		System.out.println("Cell will be alive: "+ng.cellstatusNextgeneration(x, y));
		
		
		System.out.println("\nNew current board: "+gb.toString());
		
	}

}
