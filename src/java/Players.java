import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * @author Rajit
 */
public class Players {
    
    private String[] players = new String[4];
   
    
    public int foods = 0;
    
    boolean go;
    ArrayList<PlayerData> list = new ArrayList<>();
    public boolean Move=false;
    public void InitialConfig(){
        
        players[0]="Player 11";
        players[1]="Player 2";
        players[2]="Player 3";
        players[3]="Player 4";
        
        
        int[] initialpos = new int[2];
        initialpos[0] = 45;
        initialpos[1] = 45;  
        PlayerData data1 = new PlayerData(0,0,initialpos);
        list.add(data1);
        
        initialpos[0] =  45;
        initialpos[1] = 0;
           
        PlayerData data2 = new PlayerData(0,0,initialpos);
        list.add(data2);
        
        initialpos[0] = 45;
        initialpos[1] = 45;
           
        PlayerData data3 = new PlayerData(0,0,initialpos);
        list.add(data3);
        
        initialpos[0] = 45;
        initialpos[1] = 45;
           
        PlayerData data4 = new PlayerData(0,0,initialpos);
        list.add(data4);
    
    }
    
    public void MovePosition(int player, int movement, DisplayGame game){
        
        if(Move){
        
        if(movement == 37){
          if(list.get(player-1).position[0] == 0)    
            list.get(player-1).position[0] += 44;
          else
            list.get(player-1).position[0]--;
        }
        if(movement == 38){
            if(list.get(player-1).position[1] == 0)    
            list.get(player-1).position[1] += 44;
            else
        list.get(player-1).position[1]--;
        }
        if(movement == 39){
            if(list.get(player-1).position[0] == 44)    
            list.get(player-1).position[0] = 0;
            else
        list.get(player-1).position[0]++;
        }
        if(movement == 40){
            if(list.get(player-1).position[1] == 44)    
            list.get(player-1).position[1] = 0;
            else
        list.get(player-1).position[1]++;
        }

        }
    }
    
    public void Scoring(DisplayGame a, int player){
        if(Move){
        
            for( int i=0 ;i<12; i++ )
            {
            
            
            if( a.cordinates.get(i)[0]==list.get(player-1).position[0] && a.cordinates.get(i)[1] == list.get(player-1).position[1] ){

               if( a.foods[i] == 'B' ){
                   list.get(player-1).score+=4; 
               }
               else if( a.foods[i] == 'G' ){list.get(player-1).score+=4; }
               else if( a.foods[i] == 'R' ){list.get(player-1).score+=4; }
               
               
               a.cordinates.get(i)[0]=50;
               a.cordinates.get(i)[1]=50;
               foods++;
           
            } 
       
            }
            
            
            
        for( int i = 0; i < 4; i++ )
        {  
            if( list.get(player-1).position[0]==list.get(i).position[0] && list.get(player-1).position[1]==list.get(i).position[1] && (player-1!=i) ){
                
            list.get(player-1).score-=3;
                list.get(i).score-=3;
      
                
                
          
                   
                if( player-1 == 1 ){
                    list.get(i).position[0] = 0;
                    list.get(i).position[1] = 0;
                }
                else if( player-1 == 2 ){
                    list.get(i).position[0] = 44;
                    list.get(i).position[1] = 0;
                }
                else if( player-1 == 3 ){
                    list.get(i).position[0] = 0;
                    list.get(i).position[1] = 44;
                }
                else if( player-1 == 4 ){
                    list.get(i).position[0] = 44;
                    list.get(i).position[1] = 44;
                }
                
                if( i ==0 ){
                    list.get(i).position[0] = 0;
                    list.get(i).position[1] = 0;
                }
                else if( i == 1 ){
                    list.get(i).position[0] = 44;
                    list.get(i).position[1] = 0;
                }
                else if( i == 2 ){
                    list.get(i).position[0] = 0;
                    list.get(i).position[1] = 44;
                }
                else if( i == 3 ){
                    list.get(i).position[0] = 44;
                    list.get(i).position[1] = 44;
                }
                
            }
        }
        }
        
        
    }
    
    public void reset(DisplayGame board){
        board.InitialConfig();
        this.InitialConfig();
    
    }
    

        public String Display(){
         StringBuffer text = new StringBuffer("\"PLAYERS\":   [");
       //String text = "\"PLAYERS\":   [";
       //String text1 = "[\""+players[i]+"\", "+list.get(i).score+", "+list.get(i).position[0]+", "+list.get(i).position[1]+"]"

        for(int i=0;i<4;i++){
            text.append("[\""+players[i]+"\", "+list.get(i).score+", "+list.get(i).position[0]+", "+list.get(i).position[1]+"]");   //concat the text into a single string
            if(i!=3)
                text.append(",");


       }
       
        
        text.append("]");
        
    return text.toString();
    }
    
    

    void reset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

class PlayerData{                           //created inner class to store player data
    
    protected int[] position= new int[2];
    protected int player;
    protected int score;
    
   
    
    
    
    public PlayerData(int s, int p, int[] ply){
       Update(s,p,ply);
    
    } 
    public void Update(int s,int p,int[] ply){
      player = p;
      score = s;
      position[0] = ply[0];
      position[1] = ply[1];
   }
  

}