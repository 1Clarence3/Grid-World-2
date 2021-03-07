import java.util.Arrays;
import java.lang.Math;
import java.awt.Font;
public class GridWorld
{
    private int[][] grid1; //Original grid 
    private int rows, cols; //Rows & Columns of grid
    private int resolution;
    private double x, y; //X & Y coordinates for StdDraw
    private double scale; //Used for width/height when drawing squares
    private int agentX, agentY;
    private int gridRow, gridCol;

    public GridWorld(int[][] grid)
    {
        grid1 = grid;
        resolution = 100/grid1.length;
        rows = grid1.length;
        cols = grid1[0].length;     
        scale = (double)resolution/2;
    }

    public GridWorld(int[][] grid, int x, int y)
    {
        grid1 = grid;
        resolution = 100/grid1.length;
        rows = grid1.length;
        cols = grid1[0].length;     
        scale = (double)resolution/2;
        agentX = x;
        agentY = y;
        gridCol = agentX/resolution;
        gridRow = grid1.length-1-(agentY/resolution);
    }

    //Sets canvas size and creates grid
    public void setUp() 
    {
        StdDraw.setCanvasSize(800,800);
        StdDraw.setXscale(0,100);
        StdDraw.setYscale(0,100);
        StdDraw.enableDoubleBuffering();  
        StdDraw.setPenColor(StdDraw.BLACK);
        //Draws grid        
        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < cols; c++)
            {    
                if(grid1[r][c] == 1)
                {                     
                    x = scale + (c * resolution);
                    y = scale + (r * resolution);
                    StdDraw.picture(x,grid1.length*scale*2-y,"wall.gif");  
                }     
                else
                {
                    x = scale + (c * resolution);
                    y = scale + (r * resolution);
                    StdDraw.picture(x,grid1.length*scale*2-y,"grass.gif");
                }
            }    
        } 
        StdDraw.show();
    } 

    public void setPlayer()
    {
        StdDraw.picture(agentX,agentY,"agentD.gif");
    }

    public void setUpGrid1Potion()
    {
        StdDraw.picture(51,45,"potion.gif");
    }

    public void setUpGrid2Potion()
    {
        StdDraw.picture(75,81,"potion.gif");
        StdDraw.picture(9,63,"potion2.gif");
    }

    public int[] rawData()
    {
        int[] raw = new int[8];
        raw[0] = grid1[gridRow-1][gridCol];
        raw[1] = grid1[gridRow-1][gridCol+1];
        raw[2] = grid1[gridRow][gridCol+1];
        raw[3] = grid1[gridRow+1][gridCol+1];
        raw[4] = grid1[gridRow+1][gridCol];
        raw[5] = grid1[gridRow+1][gridCol-1];
        raw[6] = grid1[gridRow][gridCol-1];
        raw[7] = grid1[gridRow-1][gridCol-1];
        return raw;
    }

    public int[] compressedData()
    {
        int[] raw1 = rawData();
        int[] compressed = new int[4];
        for(int i = 0; i < 8; i+=2)
        {
            if((raw1[i] == 1) || (raw1[i+1] == 1))
            {            
                compressed[i/2] = 1;            
            }
            else
            {
                compressed[i/2] = 0;     
            }
        }
        return compressed;
    }    

    public int checkEscape()
    {
        if(gridRow - 1 < 0 || gridRow + 1 > grid1.length - 1 || gridCol - 1 < 0 || gridCol + 1 > grid1[0].length - 1)
        {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setFont(new Font("Arial",Font.BOLD,60));
            StdDraw.text(50,50,"MAZE ESCAPED!");
            StdDraw.show();
            return 1;
        }
        return 0;
    }  

    public void removeWalls()
    {
        StdDraw.setFont(new Font("Arial",Font.BOLD,60));
        StdDraw.text(50,50,"WALLS DESTROYED!");
        StdDraw.show(1000);
        for(int r = 1; r < rows-1; r++)
        {
            for(int c = 1; c < cols-1; c++)
            {    
                x = scale + (c * resolution);
                y = scale + (r * resolution);
                StdDraw.picture(x,grid1.length*scale*2-y,"grass.gif");
            }    
        } 
        StdDraw.show();
    }

    public int getX()
    {
        return agentX;
    }

    public int getY()
    {
        return agentY;
    }

    public void moveNorth(int enemy, int speed)
    {
        StdDraw.picture(agentX,agentY,"grass.gif");
        agentY += scale*2;
        if(enemy == 0)
        {
            StdDraw.picture(agentX,agentY,"agentU.gif");
        }
        else
        {
            StdDraw.picture(agentX,agentY,"enemy.gif");
        }
        gridCol = agentX/resolution;
        gridRow = grid1.length-1-(agentY/resolution);
        StdDraw.show();
        StdDraw.pause(speed);
    }

    public void moveEast(int enemy, int speed)
    {
        StdDraw.picture(agentX,agentY,"grass.gif");
        agentX += scale*2;
        if(enemy == 0)
        {
            StdDraw.picture(agentX,agentY,"agentR.gif");
        }
        else
        {
            StdDraw.picture(agentX,agentY,"enemy.gif");
        }
        gridCol = agentX/resolution;
        gridRow = grid1.length-1-(agentY/resolution);
        StdDraw.show();
        StdDraw.pause(speed);
    }    

    public void moveSouth(int enemy, int speed)
    {
        StdDraw.picture(agentX,agentY,"grass.gif");
        agentY -= scale*2;
        if(enemy == 0)
        {
            StdDraw.picture(agentX,agentY,"agentD.gif");
        }
        else
        {
            StdDraw.picture(agentX,agentY,"enemy.gif");
        }
        gridCol = agentX/resolution;
        gridRow = grid1.length-1-(agentY/resolution);
        StdDraw.show();
        StdDraw.pause(speed);
    }

    public void moveWest(int enemy, int speed)
    {
        StdDraw.picture(agentX,agentY,"grass.gif");
        agentX -= scale*2;
        if(enemy == 0)
        {
            StdDraw.picture(agentX,agentY,"agentL.gif");
        }
        else
        {
            StdDraw.picture(agentX,agentY,"enemy.gif");
        }
        gridCol = agentX/resolution;
        gridRow = grid1.length-1-(agentY/resolution);
        StdDraw.show();
        StdDraw.pause(speed);
    }   
}