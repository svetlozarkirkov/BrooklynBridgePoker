package game;

import display.Display;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;
import states.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable{
    private Display display;
    public int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private InputHandler inputHandler;
    private BufferStrategy bs;
    private Graphics g;

    private BufferedImage img;
    private SpriteSheet sh;

    //States
    private State gameState;
    private State menuState;
    private State settingsState;

    //Player
    public static Player player;
    public static Rectangle enemy;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    //Initializes all the graphics and it will get
    //everything ready for our game
    private void init() {
        //Initializing a new display.Display object
        display = new Display(this.title, this.width, this.height);
        img = ImageLoader.loadImage("/textures/bckg.jpg");
        sh = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));


        this.inputHandler = new InputHandler(this.display);
        Assets.init();

        //Initializing all the states
        gameState = new GameState();
        menuState = new MenuState();
        settingsState = new SettingsState();
        //Setting the currentState to gameState because we do not have
        //any more states set up
        StateManager.setState(gameState);

        player = new Player();
        enemy = new Rectangle(50, 50, 20, 20);
    }


    //The method that will update all the variables
    private void tick() {
        //Checks if a State exists and tick()
        if (StateManager.getState() != null) {
            StateManager.getState().tick();
        }
        player.tick();
        if(player.Intersects(enemy)) {
            System.out.print("You died");
            stop();
        }

    }

    //The method that will draw everything on the canvas
    private void render() {
        //Setting the bufferStrategy to be the one used in our canvas
        //Gets the number of buffers that the canvas should use.
        this.bs = display.getCanvas().getBufferStrategy();
        //If our bufferStrategy doesn't know how many buffers to use
        //we create some manually
        if (bs == null) {
            //Create 2 buffers
            display.getCanvas().createBufferStrategy(2);
            //returns out of the method to prevent errors
            return;
        }
        //Instantiates the graphics related to the bufferStrategy
        g = bs.getDrawGraphics();
        //Clear the screen at every frame
        g.clearRect(0, 0, this.width, this.height);
        //Beginning of drawing things on the screen

        g.drawImage(img, 0, 0, this.width, this.height, null);

        player.render(g);
        g.setColor(Color.red);
        g.fillRect(this.enemy.x, this.enemy.y, this.enemy.width, this.enemy.height);

        //Checks if a State exists and render()
        if (StateManager.getState() != null){
            StateManager.getState().render(this.g);
        }

        //End of drawing objects

        //Enables the buffer
        bs.show();
        //Shows everything stored in the Graphics object
        g.dispose();
    }

    //Implementing the interface's method
    @Override
    public void run() {
        init();

        //Sets the frames per seconds
        int fps = 30;
        //1 000 000 000 nanoseconds in a second. Thus we measure time in nanoseconds
        //to be more specific. Maximum allowed time to run the tick() and render() methods
        double timePerTick = 1_000_000_000.0 / fps;
        //How much time we have until we need to call our tick() and render() methods
        double delta = 0;
        //The current time in nanoseconds
        long now;
        //Returns the amount of time in nanoseconds that our computer runs.
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            //Sets the now variable to the current time in nanoseconds
            now = System.nanoTime();
            //Amount of time passed divided by the max amount of time allowed.
            delta += (now-lastTime) / timePerTick;
            //Adding to the timer the time passed
            timer += now - lastTime;
            //Setting the lastTime with the values of now time after we have calculated the delta
            lastTime = now;

            //If enough time has passed we need to tick() and render() to achieve 60 fps
            if (delta >= 1) {
                tick();
                render();
                //Reset the delta
                ticks++;
                delta--;
            }

            if (timer >= 1_000_000_000) {
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        //Calls the stop method to ensure everything has been stopped
        stop();
    }

    //Creating a start method for the Thread to start our game
    //Synchronized is used because our method is working with threads
    //so we ensure ourselves that nothing will go bad
    public synchronized void start() {
        //If the game is running exit the method
        //This is done in order to prevent the game to initialize
        //more than enough threads
        if(running) {
            return;
        }
        //Setting the while-game-loop to run
        running = true;
        //Initialize the thread that will work with "this" class (game.Game)
        thread = new Thread(this);
        //The start method will call start the new thread and it will call
        //the run method in our class
        thread.start();
    }

    //Creating a stop method for the Thread to stop our game
    public synchronized void stop() {
        //If the game is not running exit the method
        //This is done to prevent the game from stopping a
        //non-existing thread and cause errors
        if(!running) {
            return;
        }
        running = false;
        //The join method stops the current method from executing and it
        //must be surrounded in try-catch in order to work
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
