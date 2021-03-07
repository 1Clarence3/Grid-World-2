import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
public class Agent
{
    private int[][] grid;
    private int[] sorrounding;
    private String direction;
    private int agentX, agentY;
    private GridWorld agent;
    private int monster;
    private int secret;
    public Agent(int[][] grid1, int x, int y, int isMonster) 
    {
        grid = grid1;
        agentX = x;
        agentY = y;
        agent = new GridWorld(grid, x, y);
        monster = isMonster;
        secret = 0;
    }

    public void updateMoves()
    {        
        sense();
        decide();
        act();
    }   

    public void sense()
    { 
        sorrounding = agent.compressedData();
    }

    public void decide()
    {             
        if(sorrounding[0] == 1 && sorrounding[2] == 1 && sorrounding[3] == 1)
        {
            direction = "East"; 
        }
        else if(sorrounding[0] == 1 && sorrounding[1] == 1 && sorrounding[3] == 1)
        {
            direction = "South"; 
        }
        else if(sorrounding[1] == 1 && sorrounding[2] == 1 && sorrounding[3] == 1)
        {
            direction = "North"; 
        }
        else if(sorrounding[0] == 1 && sorrounding[1] == 1 && sorrounding[2] == 1)
        {
            direction = "West"; 
        }

        else if(sorrounding[0] == 1 && sorrounding[3] == 1)
        {
            direction = "East";
        }
        else if(sorrounding[0] == 1 && sorrounding[1] == 1)
        {
            direction = "South";
        }
        else if(sorrounding[1] == 1 && sorrounding[2] == 1)
        {
            direction = "West";
        }
        else if(sorrounding[2] == 1 && sorrounding[3] == 1)
        {
            direction = "North";
        }

        else if(sorrounding[0] == 1)
        {
            direction = "East";
        }
        else if(sorrounding[1] == 1)
        {
            direction = "South";
        }
        else if(sorrounding[2] == 1)
        {
            direction = "West";
        }
        else if(sorrounding[3] == 1)
        {
            direction = "North";
        }
    }

    public void act()
    {        
        int speed = 0;
        if(monster == 1)
        {
            speed = 20;
        }
        else
        {
            speed = 50;
        }        
        if(direction.equals("East"))
        {
            agent.moveEast(monster,speed);            
        }
        else if(direction.equals("South"))
        {
            agent.moveSouth(monster,speed);           
        }
        else if(direction.equals("West"))
        {
            agent.moveWest(monster,speed);               
        }
        else
        {
            agent.moveNorth(monster,speed);              
        }
    }

    public int checkEscape()
    {
        return agent.checkEscape();
    }

    public void setPlayer()
    {
        agent.setPlayer();
    }
    
    public void explode()
    {
        StdDraw.picture(getX(),getY(),"explosion.gif");
    }

    public void checkKey() 
    {
        int[] wall = agent.rawData();
        if (StdDraw.isKeyPressed(87)) {
            if(wall[0] == 0 || secret == 1)
            {
                agent.moveNorth(0,50);
            }
        }
        if (StdDraw.isKeyPressed(83)) {
            if(wall[4] == 0 || secret == 1)
            {
                agent.moveSouth(0,50);
            }
        }
        if (StdDraw.isKeyPressed(65)) {
            if(wall[6] == 0 || secret == 1)
            {
                agent.moveWest(0,50);
            }
        }
        if (StdDraw.isKeyPressed(68)) {
            if(wall[2] == 0 || secret == 1)
            {
                agent.moveEast(0,50);
            }
        }	
    }
    
    public int getX()
    {
        return agent.getX();
    }
    
    public int getY()
    {
        return agent.getY();
    }

    public boolean checkCollision(int x, int y, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int x5, int y5)
    {
        Rectangle a = new Rectangle(x, y, 5, 5);
        Rectangle b = new Rectangle(x1, y1, 5, 5);
        Rectangle c = new Rectangle(x2, y2, 5, 5);
        Rectangle d = new Rectangle(x3, y3, 5, 5);
        Rectangle e = new Rectangle(x4, y4, 5, 5);
        Rectangle f = new Rectangle(x5, y5, 5, 5);
        if(a.intersects(b) || a.intersects(c) || a.intersects(d) || a.intersects(e) || a.intersects(f))
        {
            return true;
        }
        return false;
    } 
    
    public boolean victory(int maze)
    {
        if(maze == 1)
        {
            if(getX() == 51 && getY() == 45)
            {
                return true;
            }
        }
        else
        {
            if(getX() == 75 && getY() == 81)
            {
                return true;
            }
        }
        return false;
    }
    
    public void easterEgg()
    {
        if(getX() == 9 && getY() == 63)
        {
            agent.removeWalls();
            agent.moveEast(0,50);
            secret = 1;
        }
    }
}