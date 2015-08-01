import java.util.*;
import java.util.Map.Entry;
 
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
	        				//System.out.println(pinkNodes[0][2]);
	        				row++;
	        				col = 0;
	        			}
	        			else if(row <= n-1 && blueNodesFull == true){
	        				//System.out.println(input.length());
	        				for (int i=0; i<=n-1; i++){
	        					pinkNodes[row][col] = Integer.parseInt(st.nextToken());
	        					col++;
	        				}
	        				//System.out.println(pinkNodes[2][2]);
	        				row++;
	        				col = 0;
	        				if(row > n-1)  {
	 	        				System.out.println("Instance " + instance + ":");
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
        	
        Set<Integer> availableBlueNode = new HashSet <Integer> ();
        Map<Integer, Integer> finalBlueNode = new HashMap <Integer, Integer> ();
        Map<Integer, Integer> availablePinkNode = new HashMap <Integer, Integer> ();
        
     // Add all available blue nodes to a HashSet
        for (int i=1; i<=blueNodes.length; i++)
            availableBlueNode.add(i);
        
     // Store final states of blue nodes   
        for (int i=1; i<=blueNodes.length; i++)
            finalBlueNode.put(i, null);
        
     // Store alliance of a pink nodes in a HashMap.
        for (int i=1; i<=pinkNodes.length; i++)
            availablePinkNode.put(i, null);
        
        int size = availableBlueNode.size();
        while (size > 0)  // Loop till there are no unmatched blue nodes available
        {
            int currentBlueNode = availableBlueNode.iterator().next();
            //System.out.println ("\nMan " + currentBlueNode + " arrives:");

	            for (int w : blueNodes[currentBlueNode - 1]) // loop through preferences of this blue node
	            {
	            
	                Integer matchedBlueNode = availablePinkNode.get(w);
		                if (matchedBlueNode == null) // this pink node is not yet engaged
		                {
		                    availablePinkNode.put(w, currentBlueNode);
		                    finalBlueNode.put(currentBlueNode, w);
		                    availableBlueNode.remove(currentBlueNode);
		                    //System.out.println ("Man " + currentBlueNode + " engaged to woman " + w);
		                    break;
		                } 
		                else               // this pink node is currently engaged
		                {
		                    int prefForMatchedBlueNode = -1;
		                    int prefForCurrentBlueNode = -1;
		                    for (int k=1; k<=pinkNodes[w - 1].length; k++)
		                    {
		                        if (pinkNodes[w - 1][k - 1] == matchedBlueNode) // find preference order for current matched blue node
		                            prefForMatchedBlueNode = k;
		                        
		                        if (pinkNodes[w - 1][k - 1] == currentBlueNode) // find preference order for current proposer
		                            prefForCurrentBlueNode = k;
		                     }
		                    
		                    if (prefForCurrentBlueNode < prefForMatchedBlueNode) // next unmatched blue node has higher preference by this woman
		                    {
		                        availablePinkNode.put (w, currentBlueNode);
		                        finalBlueNode.put (currentBlueNode, w);// accept current unmatched blue node
		                        availableBlueNode.remove(currentBlueNode);
		                        availableBlueNode.add(matchedBlueNode); // return previous matched blue node to unmatched blue node's pool
		                        //System.out.println ("Man " + matchedBlueNode + " is dumped by woman " + w);
		                        //System.out.println ("Man " + currentBlueNode + " engaged to woman " + w);
		                        break;
		                    }
		                }
		            }
	           size = availableBlueNode.size();
        }
        //Print matched blue node number.
        //reverse(availablePinkNode);
        /* for (Integer value: availablePinkNode.values())
        	System.out.println(availablePinkNode.get(value)); */
        Iterator<Entry<Integer, Integer>> itr = finalBlueNode.entrySet().iterator();
        while (itr.hasNext())
        {
            Entry<Integer, Integer> entry = itr.next();
            System.out.println (entry.getValue());
        } 
     } 
        /* public static <K,V> HashMap<V,K> reverse(Map<K,V> map) {
            HashMap<V,K> rev = new HashMap<V, K>();
            for(Map.Entry<K,V> entry : map.entrySet())
                rev.put(entry.getValue(), entry.getKey());
            return rev;
        } */
}