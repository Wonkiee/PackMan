
import java.util.ArrayList;
import java.util.Random;


public class DisplayGame {
   
    ArrayList<Integer[]> cordinates = new ArrayList<>();
    public Random rn = new Random();
    public char[] foods = new char[12];
    
    public void InitialConfig(){
        foods[0]='B';
        foods[1]='B';
        foods[2]='B';
        foods[3]='B';
        foods[4]='G';
        foods[5]='G';
        foods[6]='G';
        foods[7]='G';
        foods[8]='R';
        foods[9]='R';
        foods[10]='R';
        foods[11]='R';
        
        Integer[] randomCordinates; 
        
        for(int i=0;i<12;i++){
            randomCordinates = new Integer[2];
            randomCordinates[0]= rn.nextInt(42)+1;
            randomCordinates[1]= rn.nextInt(42)+1;
            cordinates.add(randomCordinates);
           
        }
    
    }
    
    public String DisplayBrd(){
        StringBuilder sb = new StringBuilder("\"DOTS\":   [");
        for(int i=0;i<12;i++){
            sb.append("[\"").append(foods[i]).append("\", ").append(cordinates.get(i)[0]).append(", ").append(cordinates.get(i)[1]).append("]");
            if(i==11){
                break;
            }
            else{
                sb.append(",");
            }
       }
       sb.append("]");
        
       return sb.toString();
    }
    
    
    public void Winner(){
    
       
    }
    
}
