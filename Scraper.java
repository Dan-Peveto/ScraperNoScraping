import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scraper extends JFrame {
    // Fields
    JTextField urlTextField;
    DefaultTableModel tableModel;
    JTable jTable;
    JComboBox<String> regexComboBox;
    JButton btn;
    String userSelection;
    JButton resetBtn;
    HashSet<String> matches = new HashSet<String>();
    // Methods

    public void searchPage(ActionEvent e) throws MalformedURLException
    {
        tableModel.setRowCount(0);
        try {
            URL url = new URL(urlTextField.getText());    
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            
            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                // regex pattern matching
                if(regexComboBox.getSelectedItem().toString().equals("Phone Number")) {
                    userSelection = "\\d{3}\\-\\d{3}\\-\\d{4}";
                } else if(regexComboBox.getSelectedItem().toString().equals("E-mail")) {
                    userSelection = "[A-Za-z0-9\\.]+\\@[A-Za-z0-9]+\\.[A-Za-z0-9]+";
                }
                Pattern pattern = Pattern.compile(userSelection);
                Matcher match = pattern.matcher(line);

                if(match.find()) {

                    if(matches.contains(match.group())){
                        // do nothing
                    } else {
                        matches.add(match.group());
                        tableModel.addRow(new Object[]{String.valueOf(tableModel.getRowCount() +1), match.group()});
                    }

                    
                }
                // add to table



            }
        } catch(Exception exception) {

        }
    }

    public void Reset(ActionEvent e) {
        tableModel.setRowCount(0);
        matches.clear();
    }

    // CTOR
    public Scraper() {
        super("Scraper No Scraping");


        setLayout(new BorderLayout());
     
        urlTextField = new JTextField("Enter URL: ");
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Line Number");
        tableModel.addColumn("Result");
        
        jTable = new JTable(tableModel);


       

        add(urlTextField, BorderLayout.NORTH);
        JScrollPane scrollBar = new JScrollPane(jTable);
        add(scrollBar);

        regexComboBox = new JComboBox<String>();
        regexComboBox.addItem("Phone Number");
        regexComboBox.addItem("E-mail");
        //regexTextField = new JTextField(20);
        JPanel southJPanel = new JPanel();
        southJPanel.add(regexComboBox);
        add(southJPanel, BorderLayout.SOUTH);

        btn = new JButton("Click Me");
        btn.addActionListener(arg0 -> {
            try {
                searchPage(arg0);
            } catch (MalformedURLException e) {
               
                e.printStackTrace();
            }
        });

        resetBtn = new JButton("Reset");
        resetBtn.addActionListener(e -> Reset(e));
        southJPanel.add(btn);
        southJPanel.add(resetBtn);
        

        setSize(800, 800);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
