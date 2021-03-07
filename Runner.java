import java.awt.Font;
import sun.audio.*;
import java.io.*;
import javax.swing.JOptionPane;
public class Runner
{
    public static void main (String [] args) {
        javax.swing.JOptionPane.showMessageDialog(null,"Welcome to GridWorld");
        String maze = "a"; //Records the option player choice
        String response = "a";
        int errorResponse = 0; //Determines if player inputted something out of bounds
        while(!(maze.equals("1") || maze.equals("2")))
        {
            if(errorResponse == 1)
            {
                javax.swing.JOptionPane.showMessageDialog(null,"Please enter a \"1\" or \"2\"");
            }
            maze = javax.swing.JOptionPane.showInputDialog("Which maze would you like to escape from: \n 1. Spiral \n 2. Squiggly");
            errorResponse = 1;
        } 
        errorResponse = 0; 
        while(!(response.equals("1") || response.equals("2")))
        {
            if(errorResponse == 1)
            {
                javax.swing.JOptionPane.showMessageDialog(null,"Please enter a \"1\" or \"2\"");
            }
            response = javax.swing.JOptionPane.showInputDialog("What game mode would you like to play: \n 1. AI \n 2. Manual");
            errorResponse = 1;
        }

        int [][] grid1 = 
            { {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1 },
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 },        
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
                {1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1 },
                {1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1 },
                {1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1 },
                {1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1 },
                {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
                {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
                {1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1 },
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }};

        int [][] grid2 =               
            { {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, },
                {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, },
                {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, },
                {1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, },        
                {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, },  
                {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, },  
                {1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, },  
                {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, },  
                {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, },  
                {1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, },  
                {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, },  
                {1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, },  
                {1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, },  
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, },  
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, },  
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, }};

        int [][] manualGrid1 = 
            { {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1 },
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 },        
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
                {1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1 },
                {1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1 },
                {1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1 },
                {1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1 },
                {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
                {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
                {1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1 },
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }};

        int [][] manualGrid2 = 
            { {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, },
                {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, },
                {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, },
                {1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, },        
                {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, },  
                {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, },  
                {1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, },  
                {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, },  
                {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, },  
                {1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, },  
                {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, },  
                {1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, },  
                {1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, },  
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, },  
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, },  
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1  }};

        GridWorld run;

        if(maze.equals("1"))
        {
            if(response.equals("2"))
            {
                run = new GridWorld(manualGrid1);   
            }
            else
            {
                run = new GridWorld(grid1);
            }
            run.setUp();           
        }
        else 
        {
            if(response.equals("2"))
            {
                run = new GridWorld(manualGrid2);   
            }
            else
            {
                run = new GridWorld(grid2);
            } 
            run.setUp();
        }
        Agent david;
        if(response.equals("1"))
        {
            playMusic("D:\\Grade 10\\Hon. AI\\03-gridworld\\Castlevania music.wav");
            if(maze.equals("1"))
            {
                david = new Agent(grid1,9,87,0);
            }
            else
            {
                david = new Agent(grid2,9,9,0);
            }
            while(true)
            {     
                david.updateMoves();  
                if(david.checkEscape() == 1)
                {
                    break;
                }
            }
        }
        else
        {
            int victory = 0;
            if(maze.equals("1"))
            {
                javax.swing.JOptionPane.showMessageDialog(null,"Welcome to the GridWorld game! \n Player: David \n Enemies: Evil Davids \n Objectives: Reach the potions to defeat the evil davids!" + 
                    "\n Note: Please do not spam the arrowkeys to ensure David doesn't go mad and go through the walls");
                playMusic("D:\\Grade 10\\Hon. AI\\03-gridworld\\Castlevania music.wav");
                while(true)
                {
                    victory = 0;
                    run.setUp();
                    Agent monster1 = new Agent(manualGrid1,9,9,1);
                    Agent monster2 = new Agent(manualGrid1,51,51,1);
                    Agent monster3 = new Agent(manualGrid1,81,15,1);
                    Agent monster4 = new Agent(manualGrid1,15,63,1);
                    Agent monster5 = new Agent(manualGrid1,81,87,1);
                    david = new Agent(manualGrid1,9,87,0);
                    run.setUpGrid1Potion();
                    david.setPlayer();
                    while(true) 
                    {
                        if(StdDraw.hasNextKeyTyped())
                        {
                            david.checkKey();
                            if(david.checkCollision(david.getX(),david.getY(),monster1.getX(),monster1.getY(),
                                monster2.getX(),monster2.getY(),monster3.getX(),monster3.getY(),
                                monster4.getX(),monster4.getY(),monster5.getX(),monster5.getY()))
                            {
                                break;
                            }
                            if(david.victory(1))
                            {
                                victory = 1;
                                break;
                            }
                        } 
                        monster1.updateMoves();
                        run.setUpGrid1Potion();
                        monster2.updateMoves();    
                        run.setUpGrid1Potion();
                        monster3.updateMoves();
                        run.setUpGrid1Potion();
                        monster4.updateMoves();
                        run.setUpGrid1Potion();
                        monster5.updateMoves();
                        run.setUpGrid1Potion();
                    }
                    if(victory == 1)
                    {
                        monster1.explode();
                        monster2.explode();
                        monster3.explode();
                        monster4.explode();
                        monster5.explode();
                        StdDraw.setPenColor(StdDraw.BLUE);
                        StdDraw.setFont(new Font("Arial",Font.BOLD,60));
                        StdDraw.text(50,50,"EVIL DAVIDS ELIMINATED!");
                        StdDraw.show(2000);
                    }
                    else
                    {
                        StdDraw.setFont(new Font("Arial",Font.BOLD,60));
                        StdDraw.setPenColor(StdDraw.RED);
                        StdDraw.text(50,50,"EVIL DAVID ATE YOU!");
                        StdDraw.show(2000);
                    }
                }
            }
            else
            {
                javax.swing.JOptionPane.showMessageDialog(null,"Welcome to the GridWorld game! \n Player: David \n Enemies: Evil Davids \n Objectives: Reach the potions to defeat the evil davids!" + 
                    "\n Note: Please do not spam the arrowkeys to ensure David doesn't go mad and go through the walls");
                playMusic("D:\\Grade 10\\Hon. AI\\03-gridworld\\Castlevania music.wav");
                while(true)
                {
                    victory = 0;
                    run.setUp();
                    Agent monster6 = new Agent(manualGrid2,87,87,1);
                    Agent monster7 = new Agent(manualGrid2,33,15,1);
                    Agent monster8 = new Agent(manualGrid2,63,51,1);
                    Agent monster9 = new Agent(manualGrid2,15,63,1);
                    Agent monster10 = new Agent(manualGrid2,51,87,1);
                    david = new Agent(manualGrid2,9,9,0);
                    run.setUpGrid2Potion();
                    david.setPlayer();
                    while(true) 
                    {
                        if(StdDraw.hasNextKeyTyped())
                        {
                            david.checkKey();
                            if(david.checkCollision(david.getX(),david.getY(),monster6.getX(),monster6.getY(),
                                monster7.getX(),monster7.getY(),monster8.getX(),monster8.getY(),
                                monster9.getX(),monster9.getY(),monster10.getX(),monster10.getY()))
                            {
                                break;
                            }
                            if(david.victory(2))
                            {
                                victory = 1;
                                break;
                            }
                            david.easterEgg();
                        } 
                        monster6.updateMoves();
                        run.setUpGrid2Potion();
                        monster7.updateMoves();    
                        run.setUpGrid2Potion();
                        monster8.updateMoves();
                        run.setUpGrid2Potion();
                        monster9.updateMoves();
                        run.setUpGrid2Potion();
                        monster10.updateMoves();
                        run.setUpGrid2Potion();
                    }
                    if(victory == 1)
                    {                        
                        monster6.explode();
                        monster7.explode();
                        monster8.explode();
                        monster9.explode();
                        monster10.explode();
                        StdDraw.setPenColor(StdDraw.BLUE);
                        StdDraw.setFont(new Font("Arial",Font.BOLD,60));
                        StdDraw.text(50,50,"EVIL DAVIDS ELIMINATED!");
                        StdDraw.show(2000);
                    }
                    else
                    {
                        StdDraw.setFont(new Font("Arial",Font.BOLD,60));
                        StdDraw.setPenColor(StdDraw.RED);
                        StdDraw.text(50,50,"EVIL DAVID ATE YOU!");
                        StdDraw.show(2000);
                    }
                }
            }
        }
    }   

    public static void playMusic(String filePath)
    {        
        InputStream music;
        try
        {
            music = new FileInputStream(new File(filePath));
            AudioStream audio = new AudioStream(music);
            AudioPlayer.player.start(audio);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Let's Begin!");
        }        
    }
}
