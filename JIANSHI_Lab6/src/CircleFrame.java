import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author  Shiji Jiang
 */
public class CircleFrame extends JFrame {
    private JButton north, south, east, west;
    private int x, y;
    private JPanel circle;

    public CircleFrame(String s){
        super(s);
        north = new JButton("North");
        south = new JButton("South");
        east = new JButton("East");
        west = new JButton("West");
        x = 200;
        y = 200;

        setSize(550,500);

        Container cp = getContentPane();

        circle = (new JPanel() {
            public void paintComponent(Graphics g) {
                g.setColor(Color.RED);
                g.fillOval(x, y, 10, 10);
            }
        }
        );

        north.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                y -= 10;
                circle.repaint();
            }
        });
        south.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                y += 10;
                circle.repaint();
            }
        });
        east.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x += 10;
                circle.repaint();
            }
        });
        west.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x -= 10;
                circle.repaint();
            }
        });

        cp.add(north,BorderLayout.NORTH);
        cp.add(south,BorderLayout.SOUTH);
        cp.add(east,BorderLayout.EAST);
        cp.add(west,BorderLayout.WEST);


        circle.setSize(400,400);
        cp.add(circle,BorderLayout.CENTER);

        setVisible(true);

    }

    public static void main(String[] args){

        CircleFrame c = new CircleFrame("Circle");

        // Terminate Application When Jframe is closed
        c.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
