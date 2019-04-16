import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Shiji Jiang
 */
public class ScoreFrame extends JFrame{
    private JButton b_am, b_rm;
    private JLabel result, last, winner;
    private int score_am, score_rm;

    public ScoreFrame(String s){
        super(s);

        setSize(180,200);
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout(FlowLayout.CENTER));

        b_am = new JButton("AC Milan");
        b_am.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                score_am ++;
                result.setText(currentScore());
                last.setText("Last Scorer: AC Milan");
                winner.setText(currentWinner());
            }
        });

        b_rm = new JButton("Real Madrid");
        b_rm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                score_rm ++;
                result.setText(currentScore());
                last.setText("Last Scorer: Real Madrid");
                winner.setText(currentWinner());
            }
        });
        cp.add(b_am);
        cp.add(b_rm);

        result = new JLabel(currentScore());
        last = new JLabel("Last Scorer: N/A");
        winner = new JLabel("Winner: Draw");
        cp.add(result);
        cp.add(last);
        cp.add(winner);

        score_am = 0;
        score_rm = 0;
        setVisible(true);
    }

    private String currentScore(){
        return "Result: " + score_am + " X " + score_rm;
    }

    private String currentWinner(){
        if(score_rm == score_am){
            return "Winner: Draw";
        }
        return score_rm > score_am? "Winner: Real Madrid" : "Winner: AC Milan";
    }

    public static void main(String[] args){

        ScoreFrame m = new ScoreFrame("Match");

        // Terminate Application When Jframe is closed
        m.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
