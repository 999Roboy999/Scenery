//Import libraries 
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.Polygon;
import java.awt.geom.GeneralPath;
import java.awt.Graphics2D;


// Whole class
public class Scenery extends JPanel {

    private int background; // 1 for day theme, 2 for night theme
    private int weather; // 1 for sun theme, 2 for cloud theme, 3 for rain theme
    static Random r = new Random();

    private int[] rainX = new int[200];
    private int[] rainY = new int[200];

    private int[] cloudX = new int[10];
    private int[] cloudY = new int[10];
    private int[] cloudW = new int[10];
    private int[] cloudH = new int[10];

    private int[] carX = new int[8];
    private int[] carSpeed = new int[8];
    private Color[] carColorList = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.PINK};
    private Color[] carColors = new Color[8];

    // Initialize tree positions
    int numTrees = r.nextInt(3, 10);
    int[] treeX = new int[numTrees];

    Color black = new Color(0, 0, 0);
    Color lightBlue = new Color(78, 153, 212); // Day color
    Color darkPurple = new Color(39, 39, 107); // Night Color
    Color yellow = new Color(255, 255, 0); // Sun color
    Color darkBlue = new Color(72, 89, 127); // Water color
    Color gray = new Color(107, 114, 108); // TT color 
    Color grayShadowColor = new Color(50, 60, 55); // a darker version of the TT building color
    Color grayHighlightColor = new Color(160, 165, 155); // Ground color
    Color white = new Color(255, 255, 255); // Cloud color
    Color blue = new Color(0, 0, 255); // Rain color
    Color brown = new Color(53, 33, 0); // Separate sea and building
    Color lightBrown = new Color(124, 100, 96); // Building color
    Color darkMatteBrown = new Color(82, 75, 59); // Building color
    Color darkGray = new Color(77, 71, 75); // Ground color
    Color tan = new Color(170, 160, 157); // Empire state building color
    Color tanShadowColor = new Color(140, 130, 127); // a darker version of the Empire state building color
    Color darkTan = new Color(174, 164, 156); // Empire state building topper
    Color mustardYellow = new Color(242, 171, 32); // Road line color
    Color matteBrown = new Color(189, 140, 91);
    Color matteBrownShadowColor = new Color(139, 100, 71); // a darker version of the matteBrown color
    Color green = new Color(77, 196, 51); // Leaf color
    Color shadowGreen = new Color(33, 127, 25); // Leaf shadow color
    Color brickRed = new Color(139, 69, 19);
    Color mortar = new Color(128, 128, 128);
    Color peach = new Color(255, 229, 180);
    Color peachShadowColor = new Color(204, 153, 102);
    Color pink = new Color(221, 170, 180);
    Color pinkShadowColor = new Color (170, 130, 140);

    
    // Instantiate class
    public Scenery(int background, int weather) {
        this.background = background;
        this.weather = weather;

        // Initialize cloud positions
        for (int i = 0; i < 10; i++) {
            cloudX[i] = r.nextInt(800);
            cloudY[i] = r.nextInt(50);
            cloudW[i] = r.nextInt(30, 100);
            cloudH[i] = r.nextInt(20, 30);
        }

        // Initialize rain positions
        for (int i = 0; i < 200; i++) {
            rainX[i] = r.nextInt(800);
            rainY[i] = r.nextInt(100); // Start at top of screen
        }

        // Intialize car color
        for (int i = 0; i < 8; i++) {
            carX[i] = r.nextInt(10, 790);
            carSpeed[i] = r.nextInt(1, 5); // Random speed between 1 and 5 pixels per frame
            carColors[i] = carColorList[r.nextInt(carColorList.length)];
        }

        // Initialize tree positions
        for (int i = 0; i < numTrees; i++) {
            treeX[i] = r.nextInt(10, 790);
        }

        // Create a timer to control the animation loop
        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update car positions
                for (int i = 0; i < 8; i++) {
                    carX[i] += carSpeed[i];
                    if (carX[i] > 800) {
                        carX[i] = 0;
                    }
                }

                // Update rain positions
                for (int i = 0; i < 200; i++) {
                    rainY[i] += 8;
                    if (rainY[i] > 550) {
                        rainX[i] = r.nextInt(800);
                        rainY[i] = r.nextInt(100);
                    }
                }

                // Redraw the panel
                repaint();
            }
        });
        timer.start();
    }

    @Override 

    // Get dimensions
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    @Override 

    // Create graphic component
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Background code
        if (background == 1) {

            // draw the background
            g.setColor(lightBlue); // Day
            g.fillRect(0, 0, 800, 600);
        }

        else if (background == 2) {
            g.setColor(darkPurple); // Night
            g.fillRect(0, 0, 800, 600);
        }
        

        // Draw the ground
        drawGround(g, 0, 400, 800, 25, darkGray,mustardYellow);

        // Draw cars
        for (int i = 0; i < 8; i++) {
            drawCar(g, carX[i], 413, carColors[i]);
        }

        // Draw the sea
        drawSea(g, 0, 425, 800, 200, darkBlue, white, brown);
    
        // Drawing buildings

        // -- Background Buildings -- //
        drawWindows(g, -5, 300, 100, 100, 0, 0, 0, 0, lightBlue, lightBrown, darkMatteBrown);
        drawWindows(g, 100, 300, 80, 80, 10, 10, 5, 10, white, peach, peachShadowColor);
        brickPattern(g, 685, 250, 110, 150, lightBrown, darkMatteBrown, 25, 5, brickRed, mortar, 10, 2, 2, 2);
        
        
        // -- Twin Towers -- //
        drawBuilding(g, 275, 50, 100, 350 , gray, grayShadowColor, 35, 10); // tt 1
        g.setColor(grayHighlightColor);
        g.fillRect(375, 160, 36, 10);
        g.fillRect(375, 300, 36, 10); 
        drawBuilding(g, 425, 50, 100, 350, gray, grayShadowColor, 35, 10); // tt 2
        g.setColor(grayHighlightColor);
        g.fillRect(525, 160, 36, 10); 
        g.fillRect(525, 300, 36, 10);

        buildingLines(g, 561, 250, 100, 150, black, pink, pinkShadowColor, 15, 5); // bg building
        buildingLines(g, 561, 225, 75, 25, black, pink, pinkShadowColor, 15, 5); // bg building topper
        // ----------------- // 

        // -- Empire State Building -- //
        buildingLines(g, 50, 75, 75, 325, black, tan, tanShadowColor, 35, 10); // ESB

        drawWindows(g, 135, 250, 105, 150, 50, 20, 30, 10, lightBlue, matteBrown, matteBrownShadowColor); // BG building
        drawBuilding(g, 135, 250, 105, 10, darkMatteBrown, darkMatteBrown, 35, 10); // BG Building Topper

        drawBuilding(g, 60, 60, 55, 15, darkTan, tanShadowColor, 35, 10); // ESB topper
        drawBuilding(g, 70, 45, 35, 15, darkTan, tanShadowColor, 35, 10); // ESB topper 
        drawBuilding(g, 80, 15, 15, 30, darkTan, tanShadowColor, 25, 0); // ESB tip
        // --------------------------- // 

        // Trees
        for (int i = 0; i < numTrees; i++) {
            drawTree(g, treeX[i], 405, 10, 20, matteBrown, matteBrownShadowColor, green, shadowGreen);
        }

        // Weather Code
        weather(g, weather);
        
    }

    public void weather(Graphics g, int choice) {
        if (weather == 1) {

            // Draw the sun
            drawSun(g, 10, 10);
        }

        if (weather == 2) {

            // Draw the clouds
            drawClouds(g, white);
        }

        if (weather == 3) {

            // Draw the Rain
            drawClouds(g, new Color(156, 157, 161));
            rain(g);
        }
    }

    public void drawGround(Graphics g, int x, int y, int w, int h, Color ground, Color line) {
        g.setColor(new Color(155,148,129));
        g.fillRect(0, 375, 800, 25);
        g.setColor(ground);
        g.fillRect(x, y, w, h);

        g.setColor(line);
        for (int i = 0; i <= 80; i++) {
            g.drawLine((x + (20 * i)), y + 11, ((x + (20 * i) + 10)), y + 11); // Draw a short line
        }
    }

    public void drawSea(Graphics g, int x, int y, int w, int h, Color seaColor, Color waveColor, Color lineColor) {
        
        // Sea
        g.setColor(seaColor);
        g.fillRect(x, y, w, h);

        // Wave
        g.setColor(waveColor);
        Graphics2D g2d = (Graphics2D) g;
        int waveSpacingX = 100; // Decreased spacing between waves
        int waveSpacingY = 70; // Increased spacing between rows
        int waveRows = 4;
        int waveColumns = 7; // Increased number of columns to fit within the x interval
        int waveHeight = 10;
        for (int row = 0; row < waveRows; row++) {
            for (int col = 0; col < waveColumns; col++) {
                int startX = 50 + col * waveSpacingX; // Start x at 50
                int startY = 450 + row * waveSpacingY; // Start y at 420
                if (row % 2 == 0) {
                    startX += waveSpacingX / 2;
                }
                int endX = startX + waveSpacingX / 2;
                int endY = startY;
                int controlX = startX + waveSpacingX / 4;
                int controlY = startY - 10; // Adjusted control y to create a more wave-like shape

                GeneralPath path = new GeneralPath();
                path.moveTo(startX, startY);
                path.quadTo(controlX, controlY, endX, endY);
                g2d.draw(path);
            }
        }

        // Line
        g.setColor(lineColor);
        g.fillRect(x, y, w, 3);
    }

    public void drawSun(Graphics g, int x, int y) {
        g.setColor(yellow);
        g.fillOval(x, y, 50, 50);
        
    }

    public void drawClouds(Graphics g, Color cloud) {
        g.setColor(cloud); // Set the cloud color
        for (int i = 0; i < 10; i++) {
            g.fillOval(cloudX[i], cloudY[i], cloudW[i], cloudH[i]);
        }
    }

    public void rain(Graphics g) {
        g.setColor(blue);
        for (int i = 0; i < 200; i++) {
            g.drawLine(rainX[i], rainY[i], rainX[i], rainY[i] + 10);
        }
    }

    public void drawBuilding(Graphics g, int x, int y, int w, int h, Color buildingColor, Color shadowColor, int xoffset, int yoffset) {
        
        // Duplicate
        g.setColor(shadowColor);
        g.drawRect((x + xoffset), (y - yoffset), w, h);

        // Connect
        g.drawLine((x + xoffset), (y - yoffset), x, y);
        g.drawLine((x + w), y, ((x + xoffset) + w), (y - yoffset));
        g.drawLine(x, (y + h), (x + xoffset), ((y - yoffset) + h));
        g.drawLine((x + w), (y + h), ((x - xoffset) + w), ((y - yoffset) + h));

        // Shade
        int [] top_x_points = {(x + xoffset), x, (x + w), ((x + xoffset) + w)};
        int [] top_y_points = {(y - yoffset), y, y, (y - yoffset)};
        int top_n_points = top_x_points.length;
        Polygon topFace = new Polygon(top_x_points, top_y_points, top_n_points);
        g.fillPolygon(topFace);

        int [] side_x_points = {((x + xoffset) + w), (x + w), (x + w), ((x + xoffset) + w)};
        int [] side_y_points = {(y - yoffset), y, (y + h), ((y - yoffset) + h)};
        int side_n_points = side_x_points.length;
        Polygon sideFace = new Polygon(side_x_points, side_y_points, side_n_points);
        g.fillPolygon(sideFace);
        
        // Draw the front face
        g.setColor(buildingColor);
        g.fillRect(x, y, w, h);
    }

    public void drawWindows(Graphics g, int x, int y, int w, int h, int windowSpacingX, int windowSpacingY, int windowWidth, int windowHeight, Color windowColor, Color buildingColor, Color shadowColor) {
        if (windowSpacingX == 0) {
            windowSpacingX = 15; // Default value
        }
        
        if (windowSpacingY == 0) {
            windowSpacingY = 15; // Default value
        }

        if (windowWidth == 0) {
            windowWidth = 5; // Default value
        }

        if (windowHeight == 0) {
            windowHeight = 10; // Default value
        }

        drawBuilding(g, x, y, w, h, buildingColor, shadowColor, 35, 10);

        for (int row = 0; row < h / windowSpacingY; row++) {
            for (int col = 0; col < w / windowSpacingX; col++) {
                int windowX = x + (col * windowSpacingX) + (windowSpacingX - windowWidth) / 2;
                int windowY = y + (row * windowSpacingY) + (windowSpacingY - windowHeight) / 2;

                g.setColor(windowColor);
                g.fillRect(windowX, windowY, windowWidth, windowHeight);
            }
        }
    }

    public void drawTree(Graphics g, int x, int y, int w, int h, Color trunk, Color trunkShadow, Color leaf, Color leafShadow) {
        drawBuilding(g, x, y, w, h, trunk, trunkShadow, 3, 2);

        g.setColor(leafShadow);
        g.fillOval(x + 1, y - 15, 15, 15);
        g.setColor(leaf);
        g.fillOval(x - 2, y - 14, 15, 15);
    }

    public void buildingLines(Graphics g, int x, int y, int w, int h, Color lineColor, Color buildingColor, Color shadowColor, int z, int j) {
        drawBuilding(g, x, y, w, h, buildingColor, shadowColor, z, j);

        // Draw vertical lines
        int verticalSpacing = 15; // adjust this value to change the spacing between vertical lines
        for (int i = x; i < x + w; i += verticalSpacing) {
            g.setColor(lineColor);
            g.drawLine(i, y, i, y + h);
        }

        // Draw horizontal lines
        int horizontalSpacing = 20; // adjust this value to change the spacing between horizontal lines
        for (int i = y; i < y + h; i += horizontalSpacing) {
            g.setColor(lineColor);
            g.drawLine(x, i, x + w, i);
        }
    }
    
    public void brickPattern(Graphics g, int x, int y, int w, int h, Color buildingColor, Color shadowColor, int z, int j, Color brickColor, Color mortarColor, int brickWidth, int brickHeight, int mortarWidth, int mortarHeight) {
        drawBuilding(g, x, y, w, h, buildingColor, shadowColor, z, j);

        for (int i = 0; i < h; i += brickHeight + mortarHeight) {
            for (int k = 0; k < w; k += brickWidth + mortarWidth) {
                g.setColor(brickColor);
                g.fillRect(x + k, y + i, brickWidth, brickHeight);
                g.setColor(mortarColor);
                g.fillRect(x + k + brickWidth, y + i, mortarWidth, brickHeight);
            }

            // Alternate the brick pattern for each row
            if (i + brickHeight + mortarHeight < h) {
                g.setColor(brickColor);
                g.fillRect(x, y + i + brickHeight + mortarHeight, brickWidth, brickHeight);
                g.setColor(mortarColor);
                g.fillRect(x + brickWidth, y + i + brickHeight + mortarHeight, mortarWidth, brickHeight);
            }
        }
    }

    public void drawCar(Graphics g, int x, int y, Color carColor) {
        // Body of the car
        int[] bodyX = {x, x + 10, x + 20, x + 30};
        int[] bodyY = {y, y - 10, y - 10, y};
        Polygon body = new Polygon(bodyX, bodyY, bodyX.length);
        g.setColor(carColor);
        g.fillPolygon(body);

        // Wheels
        g.setColor(black);
        g.fillOval(x + 5, y, 6, 6); // Left wheel
        g.fillOval(x + 20, y, 6, 6); // Right wheel
    }

}