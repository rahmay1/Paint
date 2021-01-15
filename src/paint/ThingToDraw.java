/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.RenderedImage;

/**
 *
 * @author yrahman7112
 */
public class ThingToDraw {

    private Shape myShape;
    private Polygon myPolygon;
    private Color myColor;
    private boolean Translucent;
    private boolean filled;
    private String stringToDraw;
    private int myThickness;
    private double xLocation;
    private double yLocation;
    private int imageWidth;
    private int imageHeight;
    private Image loadedImage;
    private MouseEvent evt;
    PaintWindow myMaster;

    public ThingToDraw(Shape myShape, int myThickness, boolean filled, Color myColor, boolean Translucent) {
        this.myShape = myShape;
        this.myColor = myColor;
        this.myThickness = myThickness;
        this.filled = filled;
        this.Translucent = Translucent;
    }

    public ThingToDraw(Polygon myPolygon, int myThickness, boolean filled, Color myColor, boolean Translucent) {
        this.myPolygon = myPolygon;
        this.myColor = myColor;
        this.myThickness = myThickness;
        this.filled = filled;
        this.Translucent = Translucent;
    }

    public ThingToDraw(String toDraw, double xLocation, double yLocation, int myThickness, Color myColor) {
        this.stringToDraw = toDraw;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.myThickness = myThickness;
        this.myColor = myColor;
    }

    public ThingToDraw(Image loadedImage, int imageWidth, int imageHeight) {
        this.loadedImage = loadedImage;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.myThickness = myThickness;
    }

    public void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(myThickness));

        if (Translucent == true) {
            int red = myColor.getRed();
            int green = myColor.getGreen();
            int blue = myColor.getBlue();
            myColor = new Color(red, green, blue, 30);
            g.setColor(myColor);
        } else {
            g.setColor(myColor);
        }
        if (myShape != null) {
            if (filled == true) {
                g.fill(myShape);
            }
            g.draw(myShape);
        }
        if (myPolygon != null) {
            if (filled == false) {
//                g.setStroke(new BasicStroke(myThickness - 5));
//                g.setColor(Color.white);
                g.drawPolygon(myPolygon);
//                g.setColor(myColor);
//                g.setStroke(new BasicStroke(myThickness));
            }
            
            g.drawPolygon(myPolygon);
        }
        if (stringToDraw != null) {
            Font font = new Font("Serif", Font.PLAIN, myThickness);
            g.setFont(font);

            g.drawString(stringToDraw, (float) xLocation, (float) yLocation);
        }

        if (loadedImage != null) {
            g.drawImage(loadedImage, imageWidth, imageHeight, null);
        }
    }
}
