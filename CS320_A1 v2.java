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
		//Scanner scanner = new Scanner ("2015A1 sample input.doc");
    	int [][] blueNodes = null;
    	int [][] pinkNodes = null;
    	int row = 0;
    	int col = 0;
    	int n = 0;
    	int instance = 1;
    	boolean isNewInstance = false;
    	boolean isBlueNodesFull = false;
    	try{
	        while (scanner.hasNext()){
	        	int input = scanner.nextInt();
	        	if (input == 0){    //check if it ends or not
	        		break;
	        	}
	        	if (isNewInstance == false){   // loop data into 2d arrays, blueNodes and pinkNodes
	        		//System.out.println(n);
		        	n = input;
		        	blueNodes = new int [input][input];
		        	pinkNodes = new int [input][input];
	        		isNewInstance = true;
	        	}else{
	        		if (isBlueNodesFull == true){
	        			if (col == n - 1 && row < n){
		        			pinkNodes [row][col] = input;
		        			row++;
		        			col = 0;
		        			if (row == n){
		        				System.out.println("Instance " + instance + ":");
		        				execute(blueNodes, pinkNodes);
			        			row = 0;
			        			col = 0;
			        			instance++;
			        			isNewInstance = false;
			        			isBlueNodesFull = false;
			        			blueNodes = null;
			        			pinkNodes = null;
			        		}
		        		}else if (col < n - 1 && row < n){
		        			//System.out.println(pinkNodes[0][0]);
		        		    pinkNodes [row][col] = input;
		        		    col++;
		        		}
	        		}else if (col == n - 1 && row < n){	        			
	        			blueNodes [row][col] = input;
	        			//System.out.println(blueNodes[2][2]);
	        			row++;
	        			col = 0;
	        			if(row == n){
	        				isBlueNodesFull = true;
	        				row = 0;
	        			}
	        		}else if (col < n - 1 && row < n){
	        			//System.out.println(blueNodes[0][0]);
	        		    blueNodes [row][col] = input;
	        		    col++;
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

	            for (int w : blueNodes[currentBlueNode - 1]) // loop through preferences of this blue node
	            {
	            
	                Integer matchedBlueNode = availablePinkNode.get(w);
		                if (matchedBlueNode == null) // this pink node is not yet engaged
		                {
		                    availablePinkNode.put(w, currentBlueNode);
		                    finalBlueNode.put(currentBlueNode, w);
		                    availableBlueNode.remove(currentBlueNode);
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
		                    
		                    if (prefForCurrentBlueNode <= prefForMatchedBlueNode) // next unmatched blue node has higher preference by this woman
		                    {
		                        availablePinkNode.put (w, currentBlueNode);
		                        finalBlueNode.put (currentBlueNode, w);// accept current unmatched blue node
		                        availableBlueNode.remove(currentBlueNode);
		                        availableBlueNode.add(matchedBlueNode); // return previous matched blue node to unmatched blue node's pool
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
