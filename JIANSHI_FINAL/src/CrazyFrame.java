import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class CrazyFrame extends JFrame {
	private JButton bt_do, bt_change;
	private int index;
	
	public CrazyFrame(String title, ActionListener[] acts){
		super(title);
		

		setSize(100,100);
		Container cp = getContentPane();
        cp.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        bt_do =  new JButton("Do");
        bt_change = new JButton("Change");
        
        index = (int)(Math.random()*acts.length);
        
        
        // in this case, the index is determined in runtime
        bt_change.addActionListener(e -> {index = (int)(Math.random()*acts.length);});
        bt_do.addActionListener(e -> {acts[index].actionPerformed(e);});
        
       
        
        cp.add(bt_do);
        cp.add(bt_change);
        setVisible(true);
	}
}
