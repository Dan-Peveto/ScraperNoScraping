import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.*;

public class Scraper extends JFrame {
    
    JTextField urlTextField;
    JTable jTable;
    JTextField regexTextField;
    JButton btn;
    
    public Scraper() {
        super("Scraper No Scraping");


        setLayout(new BorderLayout());
        String columns[] = {"ID", "Name", "Salary"};
        String data[][] = {{"1", "Melissa", "30K"}, {"2", "Daniel", "0.00"}};
        urlTextField = new JTextField("Enter URL: ");
        jTable = new JTable(data, columns);
        add(urlTextField, BorderLayout.NORTH);
        JScrollPane scrollBar = new JScrollPane(jTable);
        add(scrollBar);

        regexTextField = new JTextField(20);
        JPanel southJPanel = new JPanel();
        southJPanel.add(regexTextField);
        add(southJPanel, BorderLayout.SOUTH);

        btn = new JButton("Click Me");
        southJPanel.add(btn);
        

        setSize(800, 800);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
