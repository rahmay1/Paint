/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

/**
 *
 * @author yrahman7112
 */
public class PaintWindow extends javax.swing.JFrame {

    /**
     * Creates new form PaintWindow
     */
    // Intitalized Global Variables
    String inputtedText;
    static double prevX = 0;
    static double prevY = 0;
    static int maximumSize;
    double initialX;
    double initialY;
    double scaledAmount = 1;
    boolean isFirstTime = true;
    boolean drawLine = true;
    boolean filledOrNot = true;
    boolean userPressedMouse = false;
    boolean allowDraw = true;
    boolean erase = false;
    boolean drawText = false;
    boolean drawRect = false;
    boolean drawCircle = false;
    boolean drawTriangle = false;
    boolean drawRoundRect = false;
    boolean drawPolygon = false;
    boolean followCursor = true;
    boolean isTranslucent = false;
    boolean setBackgroundColor = false;
    boolean fillTool = false;
    boolean colorFinderTool = false;
    static boolean tempSelect = false;
    Color activeColor = Color.black;
    Color backgroundColor = Color.white;
    JFrame frameToolColor;
    JFrame frameSave;
    JFrame frameLoad;
    JFrame frameText;
    JPopupMenu popUpMenuText;
    Save mySaveSlave;
    Load myLoadSlave;
    static BufferedImage canvasImage;

    public BufferedImage createImage(JPanel panel) {

        int w = panel.getWidth();
        int h = panel.getHeight();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        panel.paint(g);
        return bi;

    }

    public PaintWindow() {
        initComponents();
        // Initializing Frame Tool Color
        frameToolColor = new JFrame("Color Window");
        ToolColor c = new ToolColor(this);
        frameToolColor.getContentPane().add(c);
        frameToolColor.pack();

        // Initializing Frame Save
        frameSave = new JFrame("Color Window");
        Save s = new Save(this);
        frameSave.getContentPane().add(s);
        frameSave.pack();

        // Initializing Frame Load
        frameLoad = new JFrame("Color Window");
        Load l = new Load(this);
        frameLoad.getContentPane().add(l);
        frameLoad.pack();

        // Initializing Frame Text
        frameText = new JFrame();
        TextInput t = new TextInput(this);
        frameText.getContentPane().add(t);
        frameText.pack();

        // Loading Various Button and Menu Item Images
        try {
            Image drawImg = ImageIO.read(new File("drawicon.png"));
            Image textImg = ImageIO.read(new File("texticon.png"));
            Image eraserImg = ImageIO.read(new File("erasericon.png"));
            Image zoomImg = ImageIO.read(new File("zoomicon.png"));
            Image newImg = ImageIO.read(new File("newicon.png"));
            Image saveImg = ImageIO.read(new File("saveicon.png"));
            Image loadImg = ImageIO.read(new File("loadicon.png"));
            Image rectangleImg = ImageIO.read(new File("rectangleicon.png"));
            Image circleImg = ImageIO.read(new File("circleicon.png"));
            Image triangleImg = ImageIO.read(new File("triangleicon.png"));
            Image roundedRectangleImg = ImageIO.read(new File("roundedrecticon.png"));
            Image colorFinderImg = ImageIO.read(new File("colorfindericon.png"));
            Image zoomInImg = ImageIO.read(new File("zoominicon.png"));
            Image zoomOutImg = ImageIO.read(new File("zoomouticon.png"));
            Image undoImg = ImageIO.read(new File("undoicon.png"));
            Image redoImg = ImageIO.read(new File("redoicon.png"));
            Image fillImg = ImageIO.read(new File("fillicon.png"));
            Image selectImg = ImageIO.read(new File("selecticon.png"));
            buttonDraw.setIcon(new ImageIcon(drawImg));
            buttonText.setIcon(new ImageIcon(textImg));
            buttonEraser.setIcon(new ImageIcon(eraserImg));
            buttonZoomIn.setIcon(new ImageIcon(zoomImg));
            menuItemNew.setIcon(new ImageIcon(newImg));
            menuItemSave.setIcon(new ImageIcon(saveImg));
            menuItemLoad.setIcon(new ImageIcon(loadImg));
            buttonRectangle.setIcon(new ImageIcon(rectangleImg));
            buttonCircle.setIcon(new ImageIcon(circleImg));
            buttonTriangle.setIcon(new ImageIcon(triangleImg));
            buttonRoundedRectangle.setIcon(new ImageIcon(roundedRectangleImg));
            buttonColorFinder.setIcon(new ImageIcon(colorFinderImg));
            buttonZoomIn.setIcon(new ImageIcon(zoomInImg));
            buttonZoomOut.setIcon(new ImageIcon(zoomOutImg));
            buttonUndo.setIcon(new ImageIcon(undoImg));
            buttonRedo.setIcon(new ImageIcon(redoImg));
            buttonFill.setIcon(new ImageIcon(fillImg));
            buttonSelect.setIcon(new ImageIcon(selectImg));

        } catch (Exception e) {
            System.out.println("Error: Cannot find file");
        }

        frameText.setVisible(false);
        panelCanvas.setPreferredSize(panelCanvas.getMaximumSize());
        panelCanvas.revalidate();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        button1 = new java.awt.Button();
        sliderThickness = new javax.swing.JSlider();
        buttonErase = new javax.swing.JButton();
        panelTools = new javax.swing.JPanel();
        panelToolColor = new java.awt.Panel();
        labelColor = new javax.swing.JLabel();
        toggleButtonFill = new javax.swing.JToggleButton();
        buttonDraw = new javax.swing.JButton();
        buttonText = new javax.swing.JButton();
        buttonEraser = new javax.swing.JButton();
        buttonZoomIn = new javax.swing.JButton();
        labelTools = new javax.swing.JLabel();
        labelShapes = new javax.swing.JLabel();
        buttonPolygon = new javax.swing.JButton();
        buttonTriangle = new javax.swing.JButton();
        buttonRectangle = new javax.swing.JButton();
        buttonCircle = new javax.swing.JButton();
        buttonRoundedRectangle = new javax.swing.JButton();
        toggleButtonFollowCursor = new javax.swing.JToggleButton();
        buttonColorFinder = new javax.swing.JButton();
        buttonZoomOut = new javax.swing.JButton();
        toggleButtonTranslucent = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        buttonFill = new javax.swing.JButton();
        buttonSelect = new javax.swing.JButton();
        buttonUndo = new javax.swing.JButton();
        buttonRedo = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        panelCanvas = new Canvas();
        labelPromptZoom = new javax.swing.JLabel();
        labelOutputZoom = new javax.swing.JLabel();
        PaintMenuBar = new javax.swing.JMenuBar();
        FileButton = new javax.swing.JMenu();
        menuItemNew = new javax.swing.JMenuItem();
        menuItemSave = new javax.swing.JMenuItem();
        menuItemLoad = new javax.swing.JMenuItem();
        EditButton = new javax.swing.JMenu();
        menuItemFont = new javax.swing.JMenuItem();
        menuItemSize = new javax.swing.JMenuItem();
        ToolButton = new javax.swing.JMenu();
        menuItemBrush = new javax.swing.JMenuItem();
        menuItemPencil = new javax.swing.JMenuItem();
        menuItemText = new javax.swing.JMenuItem();
        menuItemEraser = new javax.swing.JMenuItem();
        ViewButton = new javax.swing.JMenu();
        menuItemZoom = new javax.swing.JMenu();
        menuItemZoomIn = new javax.swing.JMenuItem();
        menuItemZoomOut = new javax.swing.JMenuItem();
        SettingButton = new javax.swing.JMenu();
        menuItemToolColor = new javax.swing.JMenuItem();
        menuItemBackgroundColor = new javax.swing.JMenuItem();
        menuItemCropImage = new javax.swing.JMenuItem();
        HelpButton = new javax.swing.JMenu();
        menuItemHelpful = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        button1.setLabel("button1");
        button1.setVisible(false);

        sliderThickness.setMaximum(50);
        sliderThickness.setOrientation(javax.swing.JSlider.VERTICAL);
        sliderThickness.setPaintTicks(true);
        sliderThickness.setSnapToTicks(true);
        sliderThickness.setValue(10);

        buttonErase.setText("Erase");
        buttonErase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEraseActionPerformed(evt);
            }
        });

        panelTools.setBackground(javax.swing.UIManager.getDefaults().getColor("CheckBox.light"));

        panelToolColor.setBackground(new java.awt.Color(0, 0, 0));
        panelToolColor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelToolColorMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelToolColorLayout = new javax.swing.GroupLayout(panelToolColor);
        panelToolColor.setLayout(panelToolColorLayout);
        panelToolColorLayout.setHorizontalGroup(
            panelToolColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );
        panelToolColorLayout.setVerticalGroup(
            panelToolColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        labelColor.setText("Tool Color:");

        toggleButtonFill.setText("On");
        toggleButtonFill.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                toggleButtonFillStateChanged(evt);
            }
        });

        buttonDraw.setPreferredSize(new java.awt.Dimension(30, 30));
        buttonDraw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonDrawMousePressed(evt);
            }
        });

        buttonText.setPreferredSize(new java.awt.Dimension(30, 30));
        buttonText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonTextMousePressed(evt);
            }
        });

        buttonEraser.setPreferredSize(new java.awt.Dimension(30, 30));
        buttonEraser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonEraserMousePressed(evt);
            }
        });

        buttonZoomIn.setPreferredSize(new java.awt.Dimension(30, 30));
        buttonZoomIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonZoomInMousePressed(evt);
            }
        });

        labelTools.setText("Tools");

        labelShapes.setText("Shapes");

        buttonPolygon.setPreferredSize(new java.awt.Dimension(30, 30));
        buttonPolygon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonPolygonMousePressed(evt);
            }
        });

        buttonTriangle.setPreferredSize(new java.awt.Dimension(30, 30));
        buttonTriangle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonTriangleMousePressed(evt);
            }
        });

        buttonRectangle.setPreferredSize(new java.awt.Dimension(30, 30));
        buttonRectangle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonRectangleMousePressed(evt);
            }
        });

        buttonCircle.setPreferredSize(new java.awt.Dimension(30, 30));
        buttonCircle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonCircleMousePressed(evt);
            }
        });

        buttonRoundedRectangle.setPreferredSize(new java.awt.Dimension(30, 30));
        buttonRoundedRectangle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonRoundedRectangleMousePressed(evt);
            }
        });

        toggleButtonFollowCursor.setText("On");
        toggleButtonFollowCursor.setToolTipText("");
        toggleButtonFollowCursor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                toggleButtonFollowCursorStateChanged(evt);
            }
        });

        buttonColorFinder.setPreferredSize(new java.awt.Dimension(30, 30));
        buttonColorFinder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonColorFinderMousePressed(evt);
            }
        });

        buttonZoomOut.setPreferredSize(new java.awt.Dimension(30, 30));
        buttonZoomOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonZoomOutMousePressed(evt);
            }
        });

        toggleButtonTranslucent.setText("Off");
        toggleButtonTranslucent.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                toggleButtonTranslucentStateChanged(evt);
            }
        });

        jLabel1.setText("Following Cursor:");

        jLabel2.setText("Translucency:");

        jLabel3.setText("Fill:");

        buttonFill.setPreferredSize(new java.awt.Dimension(30, 30));
        buttonFill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonFillMousePressed(evt);
            }
        });

        buttonSelect.setPreferredSize(new java.awt.Dimension(30, 30));
        buttonSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonSelectMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelToolsLayout = new javax.swing.GroupLayout(panelTools);
        panelTools.setLayout(panelToolsLayout);
        panelToolsLayout.setHorizontalGroup(
            panelToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelToolsLayout.createSequentialGroup()
                .addGroup(panelToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelToolsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(labelColor)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(toggleButtonFill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelToolsLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(panelToolColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(toggleButtonFollowCursor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(toggleButtonTranslucent, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)))
                    .addGroup(panelToolsLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(panelToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelToolsLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(labelTools))
                            .addGroup(panelToolsLayout.createSequentialGroup()
                                .addGroup(panelToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(buttonRectangle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonTriangle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonPolygon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonCircle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonRoundedRectangle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelToolsLayout.createSequentialGroup()
                                .addGroup(panelToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(buttonDraw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonZoomIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonEraser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonFill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonColorFinder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonZoomOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelToolsLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(labelShapes)))))
                .addContainerGap())
        );
        panelToolsLayout.setVerticalGroup(
            panelToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelToolsLayout.createSequentialGroup()
                .addComponent(labelTools)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonDraw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonEraser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonColorFinder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonZoomIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonZoomOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonFill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(labelShapes)
                .addGap(13, 13, 13)
                .addGroup(panelToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCircle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRectangle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonTriangle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRoundedRectangle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonPolygon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toggleButtonFollowCursor)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toggleButtonTranslucent)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toggleButtonFill)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelColor)
                    .addComponent(panelToolColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        buttonUndo.setPreferredSize(new java.awt.Dimension(35, 35));
        buttonUndo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonUndoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                buttonUndoMouseReleased(evt);
            }
        });

        buttonRedo.setPreferredSize(new java.awt.Dimension(35, 35));
        buttonRedo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonRedoMousePressed(evt);
            }
        });

        scrollPane.setToolTipText("");
        scrollPane.setPreferredSize(new java.awt.Dimension(595, 440));

        panelCanvas.setBackground(new java.awt.Color(255, 255, 255));
        panelCanvas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelCanvas.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        panelCanvas.setMaximumSize(new java.awt.Dimension(1132, 800));
        panelCanvas.setPreferredSize(new java.awt.Dimension(595, 440));
        panelCanvas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelCanvasMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                panelCanvasMouseMoved(evt);
            }
        });
        panelCanvas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelCanvasMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panelCanvasMouseReleased(evt);
            }
        });
        panelCanvas.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                panelCanvasComponentResized(evt);
            }
        });
        panelCanvas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                panelCanvasKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout panelCanvasLayout = new javax.swing.GroupLayout(panelCanvas);
        panelCanvas.setLayout(panelCanvasLayout);
        panelCanvasLayout.setHorizontalGroup(
            panelCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 593, Short.MAX_VALUE)
        );
        panelCanvasLayout.setVerticalGroup(
            panelCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 438, Short.MAX_VALUE)
        );

        scrollPane.setViewportView(panelCanvas);

        labelPromptZoom.setText("Zoom:");

        labelOutputZoom.setText("100%");

        FileButton.setText("File");
        FileButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        FileButton.setMaximumSize(new java.awt.Dimension(29, 32767));

        menuItemNew.setText("New");
        FileButton.add(menuItemNew);

        menuItemSave.setText("Save");
        menuItemSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSaveActionPerformed(evt);
            }
        });
        FileButton.add(menuItemSave);

        menuItemLoad.setText("Load");
        menuItemLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemLoadActionPerformed(evt);
            }
        });
        FileButton.add(menuItemLoad);

        PaintMenuBar.add(FileButton);

        EditButton.setText("Edit");

        menuItemFont.setText("Font");
        EditButton.add(menuItemFont);

        menuItemSize.setText("Size");
        EditButton.add(menuItemSize);

        PaintMenuBar.add(EditButton);

        ToolButton.setText("Tools");

        menuItemBrush.setText("Brush");
        ToolButton.add(menuItemBrush);

        menuItemPencil.setText("Pencil");
        menuItemPencil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemPencilActionPerformed(evt);
            }
        });
        ToolButton.add(menuItemPencil);

        menuItemText.setText("Text");
        ToolButton.add(menuItemText);

        menuItemEraser.setText("Eraser");
        ToolButton.add(menuItemEraser);

        PaintMenuBar.add(ToolButton);

        ViewButton.setText("View");

        menuItemZoom.setText("Zoom");

        menuItemZoomIn.setText("In");
        menuItemZoomIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemZoomInActionPerformed(evt);
            }
        });
        menuItemZoom.add(menuItemZoomIn);

        menuItemZoomOut.setText("Out");
        menuItemZoomOut.setToolTipText("");
        menuItemZoom.add(menuItemZoomOut);

        ViewButton.add(menuItemZoom);

        PaintMenuBar.add(ViewButton);

        SettingButton.setText("Settings");

        menuItemToolColor.setText("Tool Color");
        menuItemToolColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemToolColorActionPerformed(evt);
            }
        });
        SettingButton.add(menuItemToolColor);

        menuItemBackgroundColor.setText("Background Color");
        menuItemBackgroundColor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuItemBackgroundColorMousePressed(evt);
            }
        });
        SettingButton.add(menuItemBackgroundColor);

        menuItemCropImage.setText("Crop Image");
        menuItemCropImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCropImageActionPerformed(evt);
            }
        });
        SettingButton.add(menuItemCropImage);

        PaintMenuBar.add(SettingButton);

        HelpButton.setText("Help");

        menuItemHelpful.setSelected(true);
        menuItemHelpful.setText("Helpful yet?");
        menuItemHelpful.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemHelpfulActionPerformed(evt);
            }
        });
        HelpButton.add(menuItemHelpful);

        PaintMenuBar.add(HelpButton);

        setJMenuBar(PaintMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(panelTools, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sliderThickness, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(buttonErase, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(buttonUndo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(buttonRedo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(labelOutputZoom)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 766, Short.MAX_VALUE)
                        .addComponent(labelPromptZoom)
                        .addGap(36, 36, 36))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sliderThickness, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonErase, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(buttonRedo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttonUndo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(labelPromptZoom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelOutputZoom)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(panelTools, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemHelpfulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemHelpfulActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuItemHelpfulActionPerformed

    private void menuItemToolColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemToolColorActionPerformed
        // TODO add your handling code here:
        frameToolColor.setVisible(true);
    }//GEN-LAST:event_menuItemToolColorActionPerformed

    public void setColor(Color c) {
        activeColor = c;
        panelToolColor.setBackground(activeColor);
    }

    public void canvasBackgroundColor(Color c) {
        backgroundColor = c;
        panelCanvas.setBackground(backgroundColor);
    }

    public void hideColorPanel() {
        frameToolColor.setVisible(false);
    }

    private void menuItemCropImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCropImageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuItemCropImageActionPerformed

    private void menuItemZoomInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemZoomInActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_menuItemZoomInActionPerformed

    private void menuItemPencilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemPencilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuItemPencilActionPerformed

    private void menuItemLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemLoadActionPerformed
        // TODO add your handling code here:
        frameLoad.setVisible(true);
    }//GEN-LAST:event_menuItemLoadActionPerformed

    private void buttonEraseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEraseActionPerformed
        // TODO add your handling code here:
        ((Canvas) panelCanvas).clear();
        repaint();
    }//GEN-LAST:event_buttonEraseActionPerformed

    private void menuItemSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSaveActionPerformed
        // TODO add your handling code here:
        frameSave.setVisible(true);

    }//GEN-LAST:event_menuItemSaveActionPerformed

    private void panelToolColorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelToolColorMousePressed
        // TODO add your handling code here:
        frameToolColor.setVisible(true);
    }//GEN-LAST:event_panelToolColorMousePressed

    private void toggleButtonFillStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_toggleButtonFillStateChanged
        // TODO add your handling code here:
        if (toggleButtonFill.isSelected() == true) {

            toggleButtonFill.setText("Off");
            filledOrNot = false;
        } else {
            toggleButtonFill.setText("On");
            filledOrNot = true;
        }
    }//GEN-LAST:event_toggleButtonFillStateChanged

    private void buttonDrawMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonDrawMousePressed
        // TODO add your handling code here:
        erase = false;
        drawLine = true;
        drawText = false;
        drawRect = false;
        drawCircle = false;
        drawTriangle = false;
        drawRoundRect = false;
        drawPolygon = false;
        allowDraw = true;
        fillTool = false;
        tempSelect = false;

    }//GEN-LAST:event_buttonDrawMousePressed

    private void buttonTextMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonTextMousePressed
        // TODO add your handling code here:
        frameText.setVisible(true);
        erase = false;
        drawLine = false;
        drawText = true;
        drawRect = false;
        drawCircle = false;
        drawTriangle = false;
        drawRoundRect = false;
        drawPolygon = false;
        allowDraw = true;
        fillTool = false;
        tempSelect = false;

    }//GEN-LAST:event_buttonTextMousePressed

    private void buttonEraserMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonEraserMousePressed
        // TODO add your handling code here:
        erase = true;
        drawLine = false;
        drawText = false;
        drawRect = false;
        drawCircle = false;
        drawTriangle = false;
        drawRoundRect = false;
        drawPolygon = false;
        allowDraw = true;
        fillTool = false;
        tempSelect = false;

    }//GEN-LAST:event_buttonEraserMousePressed

    private void buttonZoomInMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonZoomInMousePressed
        // TODO add your handling code here:
        if (scaledAmount < 5 && scaledAmount >= 1) {
            scaledAmount++;
            ((Canvas) panelCanvas).scaleFactor = ((Canvas) panelCanvas).scaleFactor + scaledAmount;
            panelCanvas.repaint();
            resizeScrollPane();
        } else if (scaledAmount < 1 && scaledAmount > 0) {
            scaledAmount = scaledAmount + 0.2;
            ((Canvas) panelCanvas).scaleFactor = ((Canvas) panelCanvas).scaleFactor = scaledAmount;
            panelCanvas.repaint();
            resizeScrollPane();
        } else if (scaledAmount >= 5) {
            scaledAmount = 1;
            ((Canvas) panelCanvas).scaleFactor = ((Canvas) panelCanvas).scaleFactor = 1;
            panelCanvas.repaint();
            resizeScrollPane();
        }

        DecimalFormat df = new DecimalFormat("#%");
        df.setRoundingMode(RoundingMode.CEILING);
        labelOutputZoom.setText(df.format(((Canvas) panelCanvas).scaleFactor));
        labelOutputZoom.repaint();
    }//GEN-LAST:event_buttonZoomInMousePressed

    private void buttonTriangleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonTriangleMousePressed
        // TODO add your handling code here:
        erase = false;
        drawLine = false;
        drawText = false;
        drawRect = false;
        drawCircle = false;
        drawTriangle = true;
        drawRoundRect = false;
        drawPolygon = false;
        allowDraw = true;
        fillTool = false;
        tempSelect = false;

    }//GEN-LAST:event_buttonTriangleMousePressed

    private void buttonRectangleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonRectangleMousePressed
        // TODO add your handling code here:
        erase = false;
        drawLine = false;
        drawText = false;
        drawRect = true;
        drawCircle = false;
        drawTriangle = false;
        drawRoundRect = false;
        drawPolygon = false;
        allowDraw = true;
        fillTool = false;
        tempSelect = false;

    }//GEN-LAST:event_buttonRectangleMousePressed

    private void buttonCircleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCircleMousePressed
        // TODO add your handling code here:
        erase = false;
        drawLine = false;
        drawText = false;
        drawRect = false;
        drawCircle = true;
        drawTriangle = false;
        drawRoundRect = false;
        drawPolygon = false;
        allowDraw = true;
        fillTool = false;
        tempSelect = false;

    }//GEN-LAST:event_buttonCircleMousePressed

    private void buttonRoundedRectangleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonRoundedRectangleMousePressed
        // TODO add your handling code here:
        erase = false;
        drawLine = false;
        drawText = false;
        drawRect = false;
        drawCircle = false;
        drawTriangle = false;
        drawRoundRect = true;
        drawPolygon = false;
        allowDraw = true;
        fillTool = false;
        tempSelect = false;

    }//GEN-LAST:event_buttonRoundedRectangleMousePressed

    private void buttonPolygonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonPolygonMousePressed
        // TODO add your handling code here:
        erase = false;
        drawLine = false;
        drawText = false;
        drawRect = false;
        drawCircle = false;
        drawTriangle = false;
        drawRoundRect = false;
        drawPolygon = true;
        allowDraw = true;
        fillTool = false;
        tempSelect = false;

    }//GEN-LAST:event_buttonPolygonMousePressed

    private void toggleButtonFollowCursorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_toggleButtonFollowCursorStateChanged
        // TODO add your handling code here:
        if (toggleButtonFollowCursor.isSelected() == true) {

            toggleButtonFollowCursor.setText("Off");
            followCursor = false;

        } else {

            toggleButtonFollowCursor.setText("On");
            followCursor = true;

        }
    }//GEN-LAST:event_toggleButtonFollowCursorStateChanged

    private void buttonColorFinderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonColorFinderMousePressed
        // TODO add your handling code here:
        colorFinderTool = true;
        allowDraw = false;

    }//GEN-LAST:event_buttonColorFinderMousePressed

    private void buttonZoomOutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonZoomOutMousePressed
        // TODO add your handling code here:
        if (scaledAmount <= 5 && scaledAmount > 1 || scaledAmount <= 0.6) {
            scaledAmount = 1;
            ((Canvas) panelCanvas).scaleFactor = ((Canvas) panelCanvas).scaleFactor = 1;
            panelCanvas.repaint();
            resizeScrollPane();
        } else if (scaledAmount <= 1 && scaledAmount > 0.6) {
            scaledAmount = scaledAmount - 0.2;
            ((Canvas) panelCanvas).scaleFactor = scaledAmount;
            panelCanvas.repaint();
            resizeScrollPane();
        }

        DecimalFormat df = new DecimalFormat("#%");
        df.setRoundingMode(RoundingMode.CEILING);
        labelOutputZoom.setText(df.format(((Canvas) panelCanvas).scaleFactor));
        labelOutputZoom.repaint();
    }//GEN-LAST:event_buttonZoomOutMousePressed

    private void toggleButtonTranslucentStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_toggleButtonTranslucentStateChanged
        // TODO add your handling code here:
        if (toggleButtonTranslucent.isSelected() == true) {

            toggleButtonTranslucent.setText("On");
            isTranslucent = true;

        } else {

            toggleButtonTranslucent.setText("Off");
            isTranslucent = false;

        }
    }//GEN-LAST:event_toggleButtonTranslucentStateChanged

    private void buttonUndoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonUndoMousePressed
        // TODO add your handling code here:
//        do {
        ((Canvas) panelCanvas).undo();
        repaint();
//            try {
//
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                System.out.println(e);
//            }
//        } while (!(evt.getID() == MouseEvent.MOUSE_RELEASED));

    }//GEN-LAST:event_buttonUndoMousePressed

    private void buttonUndoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonUndoMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonUndoMouseReleased

    private void buttonRedoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonRedoMousePressed
        // TODO add your handling code here:
        ((Canvas) panelCanvas).redo();
        repaint();
    }//GEN-LAST:event_buttonRedoMousePressed

    private void panelCanvasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_panelCanvasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_panelCanvasKeyPressed

    private void panelCanvasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCanvasMouseReleased
        // TODO add your handling code here:
        userPressedMouse = false;
        isFirstTime = true;
        if (tempSelect == true) {
            ((Canvas) panelCanvas).undo();
            repaint();
        }
    }//GEN-LAST:event_panelCanvasMouseReleased

    private void panelCanvasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCanvasMousePressed
        // TODO add your handling code here:
        userPressedMouse = true;
        if (tempSelect == true) {
            initialX = evt.getX();
            initialY = evt.getY();
        }
        if (followCursor == false && allowDraw == true) {
            drawSomethingFunc(evt);

        } else if (allowDraw == false && colorFinderTool == true) {
            getColorAtPoint(evt);
        } else if (fillTool == true && allowDraw == false) {
            canvasImage = createImage(panelCanvas);
            //The person who showed me how to make the flood but never actually bothers to explain why its so buggy...https://www.youtube.com/watch?v=vK8b8smbGZI
            flood(evt.getX(), evt.getY());
            ((Canvas) panelCanvas).clear();
            ThingToDraw d = new ThingToDraw(canvasImage, panelCanvas.getX(), panelCanvas.getY());
            ((Canvas) panelCanvas).addShape(d);
            panelCanvas.repaint();
        }
    }//GEN-LAST:event_panelCanvasMousePressed

    private void panelCanvasMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCanvasMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_panelCanvasMouseMoved

    private void panelCanvasMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCanvasMouseDragged
        // TODO add your handling code here:

        if (userPressedMouse == true && followCursor == true && allowDraw == true) {
            drawSomethingFunc(evt);
        }

    }//GEN-LAST:event_panelCanvasMouseDragged

    private void panelCanvasComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelCanvasComponentResized
        // TODO add your handling code here:
//        panelCanvas.setPreferredSize(panelCanvas.getSize());
//        panelCanvas.revalidate();
    }//GEN-LAST:event_panelCanvasComponentResized

    private void menuItemBackgroundColorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuItemBackgroundColorMousePressed
        // TODO add your handling code here:
        setBackgroundColor = true;
        frameToolColor.setVisible(true);
    }//GEN-LAST:event_menuItemBackgroundColorMousePressed

    private void buttonFillMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonFillMousePressed
        // TODO add your handling code here:
        allowDraw = false;
        fillTool = true;
    }//GEN-LAST:event_buttonFillMousePressed

    private void buttonSelectMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSelectMousePressed
        // TODO add your handling code here:
        tempSelect = true;
        erase = false;
        drawLine = false;
        drawText = false;
        drawRect = false;
        drawCircle = false;
        drawTriangle = false;
        drawRoundRect = false;
        drawPolygon = false;
        allowDraw = true;
        fillTool = false;
//        https://examples.javacodegeeks.com/desktop-java/awt/event/move-and-resize-objects-using-mouse-events/
//        https://community.oracle.com/thread/3770725
//        https://coderanch.com/t/623964/java/Hot-select-region-mouse-java
//        https://stackoverflow.com/questions/32162216/drawing-a-rectangle-on-screen-as-an-area-selection-tool-java

    }//GEN-LAST:event_buttonSelectMousePressed

    private void drawSomethingFunc(MouseEvent evt) {

        double xPosition = evt.getX() / ((Canvas) panelCanvas).scaleFactor;
        double yPosition = evt.getY() / ((Canvas) panelCanvas).scaleFactor;
        initialX = initialX / ((Canvas) panelCanvas).scaleFactor;
        initialY = initialY / ((Canvas) panelCanvas).scaleFactor;

        int radius = sliderThickness.getValue() / 2;

        ThingToDraw d = new ThingToDraw(new Line2D.Double(prevX, prevY, xPosition, yPosition), sliderThickness.getValue(), false, activeColor, isTranslucent);
        if (drawLine == true) {
            d = new ThingToDraw(new Line2D.Double(prevX, prevY, xPosition, yPosition), sliderThickness.getValue(), false, activeColor, isTranslucent);
        }
        if (erase == true) {
            d = new ThingToDraw(new Ellipse2D.Double(xPosition - radius, yPosition - radius, sliderThickness.getValue(), sliderThickness.getValue()), 1, true, backgroundColor, false);
        }
        if (drawText == true) {
            d = new ThingToDraw(inputtedText, xPosition, yPosition, sliderThickness.getValue(), activeColor);
        }
        if (drawRect == true) {

            d = new ThingToDraw(new Rectangle2D.Double(xPosition - radius, yPosition - radius, sliderThickness.getValue(), sliderThickness.getValue()), 1, filledOrNot, activeColor, isTranslucent);
        }
        if (drawCircle == true) {
            d = new ThingToDraw(new Ellipse2D.Double(xPosition - radius, yPosition - radius, sliderThickness.getValue(), sliderThickness.getValue()), 1, filledOrNot, activeColor, isTranslucent);
        }
        if (drawTriangle == true) {

            Polygon triangle = new Polygon();
//            for (int i = 0; i < 5; i++) {
//                p.addPoint((int) (100 + 50 * Math.cos(i * 2 * Math.PI / 5)),
//                        (int) (100 + 50 * Math.sin(i * 2 * Math.PI / 5)));
            triangle.addPoint((int) xPosition, (int) yPosition);
            triangle.addPoint((int) xPosition - 5, (int) yPosition + 10);
            triangle.addPoint((int) xPosition + 5, (int) yPosition + 10);

            d = new ThingToDraw(triangle, sliderThickness.getValue(), filledOrNot, activeColor, isTranslucent);
        }
        if (drawRoundRect == true) {
            d = new ThingToDraw(new RoundRectangle2D.Double(xPosition - radius, yPosition - radius, sliderThickness.getValue(), sliderThickness.getValue(), 15, 15), 1, filledOrNot, activeColor, isTranslucent);
        }
        if (drawPolygon == true) {
            Polygon polygon = new Polygon();
//            for (int i = 0; i <= 5; i++) {
//                polygon.addPoint((int) (xPosition + yPosition * Math.cos(i * 2 * Math.PI / 5)),
//                        (int) (xPosition + yPosition * Math.sin(i * 2 * Math.PI / 5)));
//            }
            d = new ThingToDraw(polygon, sliderThickness.getValue(), filledOrNot, activeColor, isTranslucent);
        }
        if (tempSelect == true) {
            Polygon tempBox = new Polygon();
            tempBox.addPoint((int) initialX, (int) initialY);
            tempBox.addPoint((int) xPosition, (int) initialY);
            tempBox.addPoint((int) xPosition, (int) yPosition);
            tempBox.addPoint((int) initialX, (int) yPosition);

            d = new ThingToDraw(tempBox, 1, true, Color.blue, false);

            if (tempSelect == true) {
                ((Canvas) panelCanvas).undo();
                repaint();
            }
        }
        if (!isFirstTime || followCursor == false) {
            ((Canvas) panelCanvas).addShape(d);
        }

        isFirstTime = false;
        prevX = xPosition;
        prevY = yPosition;

        panelCanvas.repaint();
    }

    public int panelCanvasWidth() {
        int width = panelCanvas.getWidth();
        return width;
    }

    public int panelCanvasHeight() {
        int height = panelCanvas.getHeight();
        return height;
    }

    private void resizeScrollPane() {

        double width = panelCanvas.getMaximumSize().width * ((Canvas) panelCanvas).scaleFactor;
        double height = panelCanvas.getMaximumSize().height * ((Canvas) panelCanvas).scaleFactor;

        panelCanvas.setPreferredSize(new Dimension((int) width, (int) height));

        panelCanvas.revalidate();

    }

    public void loadImage(File selectedImage) {
        Image loadedImage = null;

        try {
            loadedImage = ImageIO.read(selectedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ThingToDraw d = new ThingToDraw(loadedImage, panelCanvas.getX(), panelCanvas.getY());
        ((Canvas) panelCanvas).addShape(d);
        panelCanvas.repaint();
    }

    public void saveMethod(String fileName, String chosenDirectory) {

        String filePath = chosenDirectory + ".png";
        File file = new File(filePath);
        BufferedImage canvasImage = createImage(panelCanvas);
        String ext = "png";
        try {
            ImageIO.write((RenderedImage) canvasImage, ext, file);  // ignore returned boolean
        } catch (IOException e) {
            System.out.println("Write error for " + file.getPath() + ": " + e.getMessage());
        }
    }

    private void getColorAtPoint(MouseEvent evt) {

        BufferedImage canvasImage = createImage(panelCanvas);
        int RGB = canvasImage.getRGB(evt.getX(), evt.getY());
        int red = (RGB & 0x00ff0000) >> 16;
        int green = (RGB & 0x0000ff00) >> 8;
        int blue = RGB & 0x000000ff;
        Color colorAtPixel = new Color(red, green, blue);
        activeColor = colorAtPixel;
        panelToolColor.setBackground(colorAtPixel);
        allowDraw = true;
        colorFinderTool = false;
    }

    public static class ListItem {

        public static int x;
        public static int y;

        public ListItem(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    List< ListItem> done = new ArrayList<ListItem>();

    public void flood(int x, int y) {

        if (x < 0) {
            return;
        }
        if (y < 0) {
            return;
        }
        if (x >= canvasImage.getWidth()) {
            return;
        }
        if (y >= canvasImage.getHeight()) {
            return;
        }
        if (canvasImage.getRGB(x, y) == activeColor.getRGB()) {
            return;
        }
        canvasImage.setRGB(x, y, activeColor.getRGB());
        flood(x - 1, y);
        flood(x + 1, y);
        flood(x, y - 1);
        flood(x, y + 1);
//        flood(x - 1, y - 1);
//        flood(x + 1, y + 1);
//        flood(x - 1, y + 1);
//        flood(x + 1, y - 1);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PaintWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaintWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaintWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaintWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaintWindow().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu EditButton;
    private javax.swing.JMenu FileButton;
    private javax.swing.JMenu HelpButton;
    private javax.swing.JMenuBar PaintMenuBar;
    private javax.swing.JMenu SettingButton;
    private javax.swing.JMenu ToolButton;
    private javax.swing.JMenu ViewButton;
    private java.awt.Button button1;
    private javax.swing.JButton buttonCircle;
    private javax.swing.JButton buttonColorFinder;
    private javax.swing.JButton buttonDraw;
    private javax.swing.JButton buttonErase;
    private javax.swing.JButton buttonEraser;
    private javax.swing.JButton buttonFill;
    private javax.swing.JButton buttonPolygon;
    private javax.swing.JButton buttonRectangle;
    private javax.swing.JButton buttonRedo;
    private javax.swing.JButton buttonRoundedRectangle;
    private javax.swing.JButton buttonSelect;
    private javax.swing.JButton buttonText;
    private javax.swing.JButton buttonTriangle;
    private javax.swing.JButton buttonUndo;
    private javax.swing.JButton buttonZoomIn;
    private javax.swing.JButton buttonZoomOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JLabel labelColor;
    private javax.swing.JLabel labelOutputZoom;
    private javax.swing.JLabel labelPromptZoom;
    private javax.swing.JLabel labelShapes;
    private javax.swing.JLabel labelTools;
    private javax.swing.JMenuItem menuItemBackgroundColor;
    private javax.swing.JMenuItem menuItemBrush;
    private javax.swing.JMenuItem menuItemCropImage;
    private javax.swing.JMenuItem menuItemEraser;
    private javax.swing.JMenuItem menuItemFont;
    private javax.swing.JCheckBoxMenuItem menuItemHelpful;
    private javax.swing.JMenuItem menuItemLoad;
    private javax.swing.JMenuItem menuItemNew;
    private javax.swing.JMenuItem menuItemPencil;
    private javax.swing.JMenuItem menuItemSave;
    private javax.swing.JMenuItem menuItemSize;
    private javax.swing.JMenuItem menuItemText;
    private javax.swing.JMenuItem menuItemToolColor;
    private javax.swing.JMenu menuItemZoom;
    private javax.swing.JMenuItem menuItemZoomIn;
    private javax.swing.JMenuItem menuItemZoomOut;
    private javax.swing.JPanel panelCanvas;
    private java.awt.Panel panelToolColor;
    private javax.swing.JPanel panelTools;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JSlider sliderThickness;
    private javax.swing.JToggleButton toggleButtonFill;
    private javax.swing.JToggleButton toggleButtonFollowCursor;
    private javax.swing.JToggleButton toggleButtonTranslucent;
    // End of variables declaration//GEN-END:variables
}
