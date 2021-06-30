import java.util.ArrayList;
import java.util.Collections;

public class Main {

public static void main(String[] args) {
		
		///Disk-Scheduling Algorithms: FCFS, SSTF, SCAN 
	    //The program services a disk drive with 5000 cylinders
	    //It will service 50 requests according to the algorithms and report the total amount of head movement by each algorithm
	
	
	
		////////////////////////////////////////////////FCFS
	
	    int diskhead =0;
	    if (args.length > 0)
	        diskhead = Integer.parseInt(args[0]);
	 
		
		ArrayList <Integer> diskdrive = new ArrayList <Integer>();
		
		int[] requests = {4078,153, 2819, 3294, 1433, 211, 1594, 2004, 2335, 2007, 771, 1043, 3950, 2784, 
				1881, 2931, 3599, 1245, 4086, 520, 3901, 2866, 947, 3794, 2353, 3970, 3948, 1815, 
				4621, 372, 2684, 3088, 827, 3126, 2083, 584, 4420, 1294, 917, 2881, 3659, 2868, 100, 1581, 
				4581, 1664, 1001, 1213, 3439, 4706 };
		if (args.length == 0)
		 diskhead =1000;
		else
		diskhead = Integer.parseInt(args[0]);
		
		int track =0;
		
		int sum=0;
		
        int MIN_CYLINDER = 0;
		
		int MAX_CYLINDER = 4999;
		
		/////add 0-4999 to arraylist
		
		for (int i= MIN_CYLINDER; i <= MAX_CYLINDER; i++ ) {
			     diskdrive.add(i);
		}
		/////////////////////////////////////////////////////////
		
		//////first loop to iterate through requests array
		
		for(int j =0; j < requests.length; j++) {
			
			
	    ////////second loop within first one, to iterate through diskdrive arraylist 
		for (int i= MIN_CYLINDER; i <= MAX_CYLINDER; i++ ) {
		   
			int disk = diskdrive.get(i);
			
			if( disk == requests[j]) {
		    	if(diskhead < disk) {
		    		track = disk-diskhead;
		    		sum = sum+track;
		    	    diskhead = disk;
		    	}
		    	else {
		    		track = diskhead - disk;
		    		sum=sum+track;
		    	    diskhead =disk;
		    	}
		    }
	}
		
		
		}
		
	   //sum contains number of total tracks
          System.out.println("FCSFS " + sum + " tracks of head movement");
          
          
		
		
		
        ///////////////////////////////////////////////////////////////SSTF  
		
		//reinitialize variables to default, diskhead must remain the same value on the first iteration of the second loop
          if (args.length == 0)
          diskhead =1000;
          else
          diskhead = Integer.parseInt(args[0]);
          
          track =0;
  		  sum=0;
  		  
  		//create arraylist with requests array values
          
       
  		ArrayList<Integer> list = new ArrayList <Integer>();
           for(int text:requests) {
              list.add(text);
           } 
  		 
      //diskhead2 will hold the value of next diskhead after the shortest time is found with the first diskhead
      //lastnum is the subtraction of the last value of the list with the last diskhead
           
          
          int diskhead2=0;
          int lastnum=0;
         
       
       //first loop for requests
          
      	for(int j =0; j < list.size(); j++) {
      	
      	//stops the loop when one value is left on the list	
    	if(list.size()==1)
      			break;
			
 
	   ///second loop the iterate through diskdrive finding values and shortest time
    		for (int i= MIN_CYLINDER; i <= MAX_CYLINDER; i++ ) {
    			
    	   
    		    int disk = diskdrive.get(i);
    			
    			if( disk == list.get(j)) {
    			
    		    	if(diskhead < disk) {
    		    		if(track ==0) {
    		    		track = disk-diskhead;
    		    		break;
    		    	
    		    		}
    		    		
    		    		else {
    		    			if(disk-diskhead < track) {
    		    				track = disk-diskhead;
    		    				diskhead2 =disk;
    		    				break;
    		    			}
    		    		}
    		    	
    		    	 
    		    	}
    		    	else {
    		    		if(track ==0) {
    		    		track = diskhead - disk;
    		    		break;
    		    	
    		    		}
    		    		else {
    		    		if(diskhead-disk < track) {
		    				track = diskhead-disk;
		    				diskhead2 =disk;
		    				break;
    		    		}
    		    		    
    		    		}
    		  
    		    	  
    		    	}
    		    }
    		
    	}//end of second loop
    		
    	
    		//after we searched through all values of requests with the second loop
    		//remove diskhead2 from the list, because we cannot compare it with itself
    		//get sum of tracks
    		//reinitialize everything to start again, j is -1 because the loop increments it at the start
    		//first loop officially ends when the list only has 1 value
    		
    		if(diskhead2 !=0 && j ==list.size()-1) {
    			diskhead=diskhead2;
    			list.remove(Integer.valueOf(diskhead2));
    		    sum = sum +track;
    		    lastnum= list.get(0)-diskhead;
    		    j=-1;
    			track=0;
    			diskhead2 =0;
    		
    		
    		   }
      	
      	
    		
    		
    		
    		
    		}//end of first loop
          
          
         //add sum to last subtraction of last value with diskhead
        
          sum = sum+lastnum;
    
         System.out.println("SSTF " + sum + " tracks of head movement");
          
          
          
          
         
         
          
        ////////////////////////////////////////////SCAN
          
          ////reinitialize all values
         if (args.length == 0)
          diskhead =1000;
         else
         diskhead = Integer.parseInt(args[0]);
         
          track =0;
  		  sum=0;
          
          
           ///create scan list with values of requests array
          
          ArrayList<Integer> scan = new ArrayList <Integer>();
          for(int text:requests) {
             scan.add(text);
          } 
          
          //create lists to hold values smaller and greater than diskhead
          
          ArrayList<Integer> less = new ArrayList <Integer>(); 
          ArrayList<Integer> greater = new ArrayList <Integer>(); 
          
          
          for(int j =0; j < scan.size(); j++) {
          
          
        	  
          for (int i= MIN_CYLINDER; i <= MAX_CYLINDER; i++) {
   		   
  			int disk = diskdrive.get(i);
  			
  			if( disk == scan.get(j)) {
  		    	if(diskhead < disk) 
  		    		greater.add(disk);
  		    	
  		    	else 
  		    		less.add(disk);
  		    	
  		    }
  			
  	     }//end of second loop
          
          
          
          
          }//end of first loop 
          
         
          ///sort the greater and less lists
          
          Collections.sort(greater);
          Collections.reverse(less);
          
          /////get sum of all tracks from less list
          
          int sumless =0;
          
          for(int l=0; l < less.size(); l++) {
            sumless = sumless + diskhead - less.get(l); 
        	 diskhead = less.get(l); 
          }
          
          sumless = sumless +diskhead;
    
          
          //////////////get sum of all tracks from greater list
          
          diskhead =0;
          
          int sumgreat =0;
          
          for(int g=0; g < greater.size(); g++) {
            sumgreat = sumgreat + greater.get(g)-diskhead; 
        	 diskhead = greater.get(g); 
          }
          
      
          sumgreat = sumgreat + diskdrive.get(diskdrive.size()-1) - diskhead;
          sum = sumgreat +sumless;
          System.out.println("Scan " + sum + " tracks of head movement");
          
         
         
	}

}