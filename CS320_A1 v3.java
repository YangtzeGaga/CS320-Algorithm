//Done by Jma412, Jia Ma, 6290387.
import java.util.*;
 
public class CS320_A1 {
 
	public static void main(String[] args) 
    {
		/* // Preference order for 3 blue nodes       
        int blueNodes[][] = {
                {1,2},
                {1,2}
         };
		
        // Preference order for 3 pink nodes       
        int pinkNodes[][] = {
                {2,1},
                {2,1},
         }; 
        
        execute(blueNodes, pinkNodes);
    } */
    	
    	Scanner scanner = new Scanner (System.in);
		//Scanner scanner = new Scanner ("input.txt");
    	int [][] blueNodes = null;
    	int [][] pinkNodes = null;
    	int row = 0;
    	int col = 0;
    	int n = 0;
    	int instance = 1;
    	boolean isNewInstance = false;
    	boolean blueNodesFull = false;
    	try{
	        while (scanner.hasNextLine()){
	        	String input = scanner.nextLine();
	        	StringTokenizer st = new StringTokenizer (input);
	        	//System.out.println(st.countTokens());
	        	if(st.equals("0")){    //check if it ends or not
	        		break;
	        	}
	        	if(st.countTokens() == 1){   //check the index of instance
	        		//System.out.println(st.nextToken());
	        		n = Integer.parseInt(st.nextToken());
	        		//System.out.println(n);
	        		blueNodes = new int [n][n];
	        		pinkNodes = new int [n][n];
	        		isNewInstance = true;
	        	}else{                 // loop data into 2d arrays, blueNodes and pinkNodes
	        		if(isNewInstance == true){
	        			for (int i=0; i<=n-1; i++) {
	        				blueNodes [row][col] = Integer.parseInt(st.nextToken());
	        				col++;
	        			}
	        			//System.out.println(blueNodes[0][2]);
	        			row++;
	        			col = 0;
	        			isNewInstance = false;
	        		}else{
	        			if(row <= n-1 && blueNodesFull == false){
	        				//System.out.println(blueNodes[0][2]);
	        				for (int i=0; i<=n-1; i++){
	        					blueNodes[row][col] = Integer.parseInt(st.nextToken());
	        					col++;
	        				}
	        				//System.out.println(blueNodes[2][2]);
	        				row++;
	        				col = 0;
	        			}
	        			else if(blueNodesFull == false){
	        				blueNodesFull = true;
	        				row = 0;
	        				col = 0;
	        				for (int i=0; i<=n-1; i++){
	        					pinkNodes[row][col] = Integer.parseInt(st.nextToken());
	        					col++;
	        				}
	        				//System.out.println(pinkNodes[0][0]);
	        				row++;
	        				col = 0;
	        			}
	        			else if(row <= n-1 && blueNodesFull == true){
	        				//System.out.println(input.length());
	        				for (int i=0; i<=n-1; i++){
	        					pinkNodes[row][col] = Integer.parseInt(st.nextToken());
	        					col++;
	        				}
	        				row++;
	        				col = 0;
	        				if(row > n-1)  {
	 	        				System.out.println("Instance " + instance + ":");
	 	        				/* for (int i=0; i<blueNodes.length; i++)
	 	        					System.out.println(blueNodes[i][i]); */
	 	        				execute(blueNodes, pinkNodes);
	 	        				instance++;
	 	        				row = 0;
	 	        				col = 0;
	 	        				blueNodesFull = false;
	 	        				blueNodes = null;
	 	        				pinkNodes = null;
	 	        			}
	        			}
	        			    
	        		}
	        	} 
            } 
    	}catch (NullPointerException e){
    		e.printStackTrace();
    	}finally{
    	    scanner.close(); 
    	} 
}      
              
        public static void execute(int[][] blueNodes, int[][] pinkNodes) {
        	
        List<Integer> availableBlueNode = new LinkedList <Integer> ();
        Map<Integer, Integer> finalBlueNode = new HashMap <Integer, Integer> ();
        Map<Integer, Integer> availablePinkNode = new HashMap <Integer, Integer> ();
        
     // Add all available blue nodes to a HashSet
        for (int i=1; i<=blueNodes.length; i++)
            availableBlueNode.add(i);
        
        while (!availableBlueNode.isEmpty())  // Loop till there are no unmatched blue nodes available
        {
            int currentBlueNode = availableBlueNode.remove(0);
            System.out.println (currentBlueNode + " arrives:");

            for (int pinkNode : blueNodes[currentBlueNode-1]) // loop through preferences of this blue node
            {
                if (availablePinkNode.get(pinkNode) == null) // this pink node is not yet engaged
                {
                    availablePinkNode.put(pinkNode, currentBlueNode);
                    finalBlueNode.put(currentBlueNode, pinkNode);
                    System.out.println (currentBlueNode + " engaged to " + pinkNode);
                    break;
                } 
                else               // this pink node is currently engaged
                {
                	int matchedBlueNode = availablePinkNode.get(pinkNode);
                    int prefForMatchedBlueNode = -1;
                    int prefForCurrentBlueNode = -1;
                    for (int i=0; i<pinkNodes[pinkNode-1].length; i++)  
                        if (pinkNodes[pinkNode-1][i] == matchedBlueNode) // find preference order for current matched blue node
                            prefForMatchedBlueNode = i;
                    
                    for (int j=0; j<pinkNodes[pinkNode-1].length; j++) 
                        if (pinkNodes[pinkNode-1][j] == currentBlueNode) // find preference order for current proposer
                            prefForCurrentBlueNode = j;
                    
                    if (prefForCurrentBlueNode < prefForMatchedBlueNode) // next unmatched blue node has higher preference by this woman
                    {
                        availablePinkNode.put (pinkNode, currentBlueNode);
                        finalBlueNode.put (currentBlueNode, pinkNode);// accept current unmatched blue node
                        availableBlueNode.add(matchedBlueNode); // return previous matched blue node to unmatched blue node's pool
                        System.out.println (matchedBlueNode + " is dumped by " + pinkNode);
                        System.out.println (currentBlueNode + " engaged to " + pinkNode);
                        break;
                    }// else no change...keep looking for this blue node
                }
            }
        }
        //Print matched blue node number.
        //reverse(availablePinkNode);
        /* for (Integer value: availablePinkNode.values())
        	System.out.println(availablePinkNode.get(value)); */
        for (Map.Entry<Integer, Integer> matched: finalBlueNode.entrySet())
        	System.out.println(matched.getValue()); 
        
        /* for (Integer matched: finalBlueNode.keySet())
        	System.out.println(finalBlueNode.get(matched)); */
     } 
        /* public static <K,V> HashMap<V,K> reverse(Map<K,V> map) {
            HashMap<V,K> rev = new HashMap<V, K>();
            for(Map.Entry<K,V> entry : map.entrySet())
                rev.put(entry.getValue(), entry.getKey());
            return rev;
        } */
}
