package ameisenPack;

import javax.swing.*;
import java.awt.*;

public class Map extends JPanel {



    public static final int NUM_ROWS = Master.FIELD_DIM_X;
    public static final int NUM_COLS = Master.FIELD_DIM_Y;

    public static final int PREFERRED_GRID_SIZE_PIXELS = 100;

    // In reality you will probably want a class here to represent a map tile,
    // which will include things like dimensions, color, properties in the
    // game world.  Keeping simple just to illustrate.

    public Map(){
        int preferredWidth = NUM_COLS * PREFERRED_GRID_SIZE_PIXELS;
        int preferredHeight = NUM_ROWS * PREFERRED_GRID_SIZE_PIXELS;
        setPreferredSize(new Dimension(preferredWidth, preferredHeight));
    }

    @Override
    public void paintComponent(Graphics g) {
        // Important to call super class method
        super.paintComponent(g);
        // Clear the board
        g.clearRect(0, 0, getWidth(), getHeight());
        // Draw the grid
        int rectWidth = getWidth() / NUM_COLS;
        int rectHeight = getHeight() / NUM_ROWS;

        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                // Upper left corner of this terrain rect
                int x = j * rectWidth;
                int y = i * rectHeight;

                Color white = new Color(255,255,255);
                Color black = new Color(0,0,0);

                g.setColor(white);
                g.fillRect(x, y, rectWidth, rectHeight);

                g.setColor(black);
                g.drawRect(x, y, rectWidth, rectHeight);

                Field curField = Master.fieldArray[i][j];

                g.drawString(""+curField.getStacks(2), (x + rectWidth) - rectWidth/4, y + (rectHeight) - (rectHeight/8));     //fStacks
                g.drawString(""+curField.getStacks(3),  x + rectWidth / 4, y + rectHeight - (rectHeight / 8));              //ants ll
               // g.drawString(""+curField.getStacks(1), (x + rectWidth) - rectWidth/4, y + rectHeight - (rectHeight/8));     //pStacks

            }
        }
    }


}