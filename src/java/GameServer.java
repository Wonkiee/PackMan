import java.io.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;

public final class GameServer extends HttpServlet {

   
    
    public int playerNum = 0;
    boolean go = false;

    public Players players = new Players();
    public DisplayGame game = new DisplayGame();
   
    @Override
    public void init(final ServletConfig config) {
        game.InitialConfig();
        Logger.getGlobal().log(Level.INFO, "Game Starts");
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        int currPlayer = 0;
        Object pl;
        pl = session.getAttribute("player");

        if (pl == null) {
            if (playerNum <= 4) {
                playerNum++;
                if(playerNum==4){
                    //players.Move = true;
                    players.go=true; 
                    players.Move = true;
                    players.InitialConfig();
                    players.Display();
                }
                
                
                session.setAttribute("player", playerNum + "");
                currPlayer = playerNum;
            }
        } else {
            currPlayer = Integer.parseInt((String) session.getAttribute("player"));
        }
        
        
        
        String keyStroke = request.getParameter("keypress");
        synchronized (players) {
            players.MovePosition(currPlayer, Integer.parseInt(keyStroke),game);
            players.Scoring(game, currPlayer);
            players.notifyAll();
        }
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/event-stream;charset=UTF-8");
       
        
        
        Object pl;
        int current;

        try (final PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            pl = session.getAttribute("player");

            if ( pl == null ) {
                if (playerNum <= 4) {
                    playerNum++;
                    if(playerNum==1){
                    players.go=true; //4 players connected, players can move
                    players.Move = true;
                    players.InitialConfig();
                    out.println("data:{" + game.DisplayBrd() + ", " + players.Display() + "}");
                    out.println();
                    out.flush();
                }
                    session.setAttribute("player", playerNum + "");
                    current = playerNum;
                }
            } else current = Integer.parseInt((String) session.getAttribute("player"));
                
            
           
            while (!Thread.interrupted()) {
                
                synchronized (players) {
                    out.println("data:{" + game.DisplayBrd() + ", " + players.Display() + "}");
                    out.println();
                    out.flush();
               
                    if(players.foods == 12){
                       Thread.sleep(1000);
                       players.foods = 0;
                    }
                
                    players.wait();
                }
            }
            
        } catch (InterruptedException ex) {
            
        }
    }



}