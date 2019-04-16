package dev.shiji.bishops.cs321.hw4;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


class L_Gui extends JFrame {

    /**
     * default constructor
     */
    public L_Gui() {
        initComponents();
    }

    // all the initializations in one method
    private void initComponents() {

        jLabel0 = new JLabel("generation");
        jLabel1 = new JLabel("axiom");
        jLabel2 = new JLabel("rule");
        jLabel3 = new JLabel("angle");
        jLabel4 = new JLabel("scale");
        jLabel5 = new JLabel("x-coor");
        jLabel6 = new JLabel("y-coor");
        jLabel7 = new JLabel("direction");
        jSpace1 = new JLabel("");
        jSpace2 = new JLabel("");
        jSpace3 = new JLabel("");

        jTextField0 = new JTextField();
        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();
        jTextField4 = new JTextField();
        jTextField5 = new JTextField();
        jTextField6 = new JTextField();
        jTextField7 = new JTextField();


        drawButton = new JButton("DRAW");
        loadButton = new JButton("LOAD");

        // make a drawing surface
        p = new DrawBot();
        p.setBackground(/*new Color(255, 255, 255*/  Color.white);



        // make the panel of buttons
        controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(100, 600));
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setBackground(new Color(225, 225, 123));

        // set the Layout to FlowLayout
        getContentPane().setLayout(new FlowLayout());

        // add a WindowListener to terminate the program when
        // window is closed
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(1);
            }
        });


        // set up drawButton
        drawButton.setPreferredSize(new Dimension(80, 25));
        drawButton.setBackground(new Color(0, 0, 235));
        drawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                p.getData(p.MAX_Gen);
                p.generation ++;
                jTextField0.setText(p.generation + "");
                p.repaint();
            }
        });

        jTextField0.setPreferredSize(new Dimension(80, 25));
        jTextField1.setPreferredSize(new Dimension(80, 25));
        jTextField2.setPreferredSize(new Dimension(80, 25));
        jTextField3.setPreferredSize(new Dimension(80, 25));
        jTextField4.setPreferredSize(new Dimension(80, 25));
        jTextField5.setPreferredSize(new Dimension(80, 25));
        jTextField6.setPreferredSize(new Dimension(80, 25));
        jTextField7.setPreferredSize(new Dimension(80, 25));

        jTextField0.setHorizontalAlignment(JTextField.CENTER);
        jTextField1.setHorizontalAlignment(JTextField.CENTER);
        jTextField2.setHorizontalAlignment(JTextField.CENTER);
        jTextField3.setHorizontalAlignment(JTextField.CENTER);
        jTextField4.setHorizontalAlignment(JTextField.CENTER);
        jTextField5.setHorizontalAlignment(JTextField.CENTER);
        jTextField6.setHorizontalAlignment(JTextField.CENTER);
        jTextField7.setHorizontalAlignment(JTextField.CENTER);

        jSpace1.setPreferredSize(new Dimension(80, 20));
        jSpace2.setPreferredSize(new Dimension(80, 20));
        jSpace3.setPreferredSize(new Dimension(80, 20));

        // Fix up loadButton
        loadButton.setPreferredSize(new Dimension(80, 25));
        loadButton.setBackground(new Color(235, 235, 235));



        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextField0.setText(-1 + "");
                // **** CODE to read in L_System parameters

                try {
                    Scanner in = new Scanner(new File("L_System1.txt"));
                    // Send Parameters from file to GUI
                    jTextField1.setText(in.nextLine());
                    jTextField2.setText(in.nextLine());
                    jTextField3.setText(in.nextLine());
                    jTextField4.setText(in.nextLine());
                    jTextField5.setText(in.nextLine());
                    jTextField6.setText(in.nextLine());
                    jTextField7.setText(in.nextLine());

                    System.out.println("Axiom: " + p.S + "\nRule: " + p.rule);
                    System.out.println("Angle: " + p.angle + "\nStep: " + p.step);

                } catch (FileNotFoundException e) {
                    System.out.println("NO File found");
                    System.exit(0);
                }
                // pass value to turtle
                p.getData(p.MAX_Gen);
            }
        });

        // Add the components to the control panel
        controlPanel.add(jSpace1);
        controlPanel.add(jTextField1);
        controlPanel.add(jLabel1);
        controlPanel.add(jTextField2);
        controlPanel.add(jLabel2);
        controlPanel.add(jTextField3);
        controlPanel.add(jLabel3);
        controlPanel.add(jTextField4);
        controlPanel.add(jLabel4);
        controlPanel.add(jTextField5);
        controlPanel.add(jLabel5);
        controlPanel.add(jTextField6);
        controlPanel.add(jLabel6);
        controlPanel.add(jTextField7);
        controlPanel.add(jLabel7);
        controlPanel.add(jSpace2);
        controlPanel.add(loadButton);
        controlPanel.add(drawButton);
        controlPanel.add(jSpace3);
        controlPanel.add(jTextField0);
        controlPanel.add(jLabel0);


        // add the panels to the L_GUI
        getContentPane().add(controlPanel);
        getContentPane().add(p);

        pack();
        setVisible(true);
    } // _________  end of InitComponents


    ////////////   the DRAWBOT class ////////////////////////////////

    private class DrawBot extends JPanel {
        private String axiom;
        private String rule;
        private double angle;    // in degrees
        private int step;    // units in a forward motion
        private int Xcoor;    // initial x-coordinate position
        private int Ycoor;    // initial y-coordinate position
        private double direction;

        private int generation;
        private int MAX_Gen;    // maximum # of generations
        private String S;


        // Holding Current Turtle Status
        private DrawBotStatus curStat;
        private Stack<DrawBotStatus> branches;

        public DrawBot() {
            setBackground(Color.pink);
            setPreferredSize(new Dimension(600, 600));
            generation = -1;
            MAX_Gen = 6;
            S = "";

            // Init
            rule = "";
            branches = new Stack<>();
            curStat = new DrawBotStatus(Xcoor,Ycoor,direction);

        }

        /**
         * Gets data from TextFields and loads it into the drawbot
         */
        public void getData(int maxGenerations) {
            axiom = jTextField1.getText();
            rule = jTextField2.getText();
            angle = Double.parseDouble(jTextField3.getText());
            step = Integer.parseInt(jTextField4.getText());
            Xcoor = Integer.parseInt(jTextField5.getText());
            Ycoor = Integer.parseInt(jTextField6.getText());
            direction = Double.parseDouble(jTextField7.getText());
            generation = Integer.parseInt(jTextField0.getText());
            MAX_Gen = maxGenerations;
            curStat.updatePos(Xcoor, Ycoor);
            curStat.angle = direction;

            S = this.axiom;

            // debugging help.. print them out
            System.out.println(axiom);
            System.out.println(rule);
            System.out.println(angle);
            System.out.println(step);
            System.out.println(Xcoor);
            System.out.println(Ycoor);
            System.out.println(direction);
            System.out.println(generation);
        }

        /**
         * Paints the drawing
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            System.out.println("Drawbot paint component called");
            if(generation >= 0){
                ArrayList<Line2D.Double> lines;
                lines = interpreter(axiom, generation);
                if(generation > 0){

                    lines.addAll(interpreter(axiom.replace("F", rule), generation));
                }

                for(Line2D.Double l : lines){
                    // 2D lines to Shape
                    Shape s = l;
                    // Draw
                    g2.draw(s);
                }
            }
        }

        /**
         * Generate list of lines for the L system
         * @param depth depth
         * @return  List of lines
         */
        public ArrayList<Line2D.Double> interpreter(String rule, int depth){
            ArrayList<Line2D.Double> result = new ArrayList<>();
            char[] chars = rule.toCharArray();
            for(char c : chars){
                switch (c){
                    case '+':
                        curStat.turnLeft(angle);
                        break;
                    case '-':
                        curStat.turnRight(angle);
                        break;
                    case '[':
                        branches.push(new DrawBotStatus(curStat));
                        break;
                    case ']':
                        curStat = branches.pop();
                        break;
                    case 'F':
                        if(depth > 1){
                            result.addAll(interpreter(rule,depth-1));
                        } else {
                            double endX = curStat.x
                                    +  (Math.cos(Math.toRadians(curStat.angle)) * step);
                            double endY = curStat.y
                                    +  (Math.sin(Math.toRadians(curStat.angle)) * step);

                            result.add(new Line2D.Double(curStat.x,curStat.y,endX,endY));
                            curStat.updatePos(endX,endY);
                        }
                        break;
                }
            }

            return result;
        }

        /**
         * Holds the status of the turtle including point and direction
         */
        private class DrawBotStatus{
            double x;
            double y;
            double angle;

            /**
             * Default Constructor with everything to 0
             */
            public DrawBotStatus(){
                x = 0;
                y = 0;
                angle = 0;
            }

            /**
             * Construct by copy
             */
            public DrawBotStatus(DrawBotStatus ds){
                this.x = ds.x;
                this.y = ds.y;
                this.angle = ds.angle;
            }

            /**
             * Custom Constructor
             * @param x x
             * @param y y
             * @param angle direction
             */
            public DrawBotStatus(double x, double y, double angle){
                this.x = x;
                this.y = y;
                this.angle = angle;
                this.angle %= 360;
            }

            /**
             * Turn right
             * @param deg degree
             */
            public void turnRight(double deg){
                angle += deg;
                angle %= 360;
            }

            /**
             * Turn left
             * @param deg degree
             */
            public void turnLeft(double deg){
                angle -= deg;
                angle %= 360;
            }

            /**
             * Update the current position of the turtle
             * @param x x
             * @param y y
             */
            public void updatePos(double x, double y){
                this.x = x;
                this.y = y;
            }

        }

    }// end===============================DrawBot ==============================


    // Variable declarations
    private JLabel jLabel0;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jSpace1;
    private JLabel jSpace2;
    private JLabel jSpace3;

    private JTextField jTextField0;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextField jTextField4;
    private JTextField jTextField5;
    private JTextField jTextField6;
    private JTextField jTextField7;

    private JButton drawButton;
    private JButton loadButton;

    private DrawBot p;
    private JPanel controlPanel;


    static public void main(String[] args) {

        L_Gui l = new L_Gui();

    }
}
