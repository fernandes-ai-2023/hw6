import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

/**
 * Incomplete class that models a maze of caves for the "Hunt the Wumpus" game.
 *   @author Catie Baker
 *   @version 4/10/18
 */
public class CaveMaze {
  private Cave currentCave;
  private ArrayList<Cave> caves;
  
  /**
   * Constructs a CaveMaze from the data found in a file.
   *   @param filename the name of the cave data file
   */
  public CaveMaze(String filename) throws java.io.FileNotFoundException {
      Scanner infile = new Scanner(new File(filename));
      
      int numCaves = infile.nextInt();
      this.caves = new ArrayList<Cave>();
      for (int i = 0; i < numCaves; i++) {
          this.caves.add(null);
      }
      
      for (int i = 0; i < numCaves; i++) {
          int num = infile.nextInt(); 
          int numAdj = infile.nextInt(); 
          ArrayList<Integer> adj = new ArrayList<Integer>();
          for (int a = 0; a < numAdj; a++) {
              adj.add(infile.nextInt());
          }
          String name = infile.nextLine().trim();          
          this.caves.set(num, new Cave(name, num, adj));
      }
      
      this.currentCave = this.caves.get(0);
      this.currentCave.markAsVisited();
  }
  
  /**
   * Moves the player from the current cave along the specified tunnel, marking the new cave as visited.
   *   @param tunnel the number of the tunnel to be traversed (1-3)
   *   @return Description of what tunnel the player moved down
   */
  public String move(int tunnel) {
      if (tunnel < 1 || tunnel > this.currentCave.getNumAdjCaves()) {
          return "There is no tunnel number " + tunnel;
      }
      
      int caveNum = this.currentCave.getNeighborDownTunnel(tunnel);
      this.currentCave = this.caves.get(caveNum);
      this.currentCave.markAsVisited();
      
      return "Moving down tunnel " + tunnel + "...";
  }
  
  /**
   * Attempts to toss a stun grenade into the specified tunnel, but currently no grenades.
   *   @param tunnel the number of the tunnel to be bombed (1-3)
   *   @return a message that you are out of grenades
   */
  public String toss(int tunnel) {
	  return "You have no stun grenades to throw!";
  }  
  
  /**
   * Displays the current cave name and the names of adjacent caves. Caves that have not yet been 
   * visited are displayed as "unknown". 
   *   @return the list of neighbor caves
   */
  public String showLocation() {
    String message = "You are currently in " + this.currentCave.getName();
    
	for (int i = 1; i <= this.currentCave.getNumAdjCaves(); i++) {
	    int caveNum = this.currentCave.getNeighborDownTunnel(i);
	    Cave adjCave = this.caves.get(caveNum);
	    message += "\n    (" + i + ") " + adjCave.getName();
	}
	
	return message;
  }
  
  /**
   * Reports whether the player is still able (healthy and mobile).
   *   @return true
   */
  public boolean stillAble() {
      return true;
  }

  /**
   * Reports whether there are any wumpi remaining.
   *   @return true 
   */
  public boolean stillWumpi() {
      return true;
  }
}