import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class AccountFrame extends JFrame {
    JLabel accnNoLBL, ownerLBL, balalnceLBL, cityLBL, genderLBL, amountLBL;
    JTextField accNoTXT, ownerTXT, balanceTXT, amountTXT;
    JComboBox<City> citiesCMB;

    JButton newBTN, saveBTN, showBTN, quitBTN, depositBTN, withdrawBTN;
    JRadioButton maleRDB, femaleRDB;
    ButtonGroup genderBTNGRP;
   JList<Account> accountsLST;

   JPanel p1,p2,p3,p4,p5;

   Set<Account> accountSet = new TreeSet<>();
   Account acc, x;
   boolean newRec = true;

   // Combobox Data
    DefaultComboBoxModel<City> citiesCMBDL;
    DefaultListModel<Account> accountsLSTMDL;


    // Table Data
    JTable table;
    DefaultTableModel tableModel;
    ArrayList<Transaction> transList = new ArrayList<>();


    public AccountFrame() {
        super("Account Operations");
        setLayout(null);
        setSize(600, 400);


        // Adding accounts to the Frame
        accnNoLBL = new JLabel("Account No." );
        ownerLBL = new JLabel("Owner");
        balalnceLBL = new JLabel("Balance");
        cityLBL = new JLabel("City");
        genderLBL = new JLabel("Gender");
        amountLBL = new JLabel("Amount");

        // 2- TextFields
        accNoTXT = new JTextField(); accNoTXT.setEnabled(false);
        ownerTXT = new JTextField();
        balanceTXT = new JTextField(); balanceTXT.setEnabled(true);
        amountTXT = new JTextField();
        amountTXT.setPreferredSize(new Dimension(150, 25));


        // ComboBox
        citiesCMBDL = new DefaultComboBoxModel<>();
        citiesCMBDL.addElement(null);
        citiesCMBDL.addElement(new City("Brokleen", "New York"));
        citiesCMBDL.addElement(new City("Dothan", "Alabama"));
        citiesCMBDL.addElement(new City("Panama", "Florida"));
        citiesCMBDL.addElement(new City("Miami", "Florida"));

        // Adding data to JCOMBOBOX
        citiesCMB = new JComboBox<>(citiesCMBDL);

        // 4 Radio Buttons
        maleRDB = new JRadioButton("Male", true);
        femaleRDB = new JRadioButton("Female");

        genderBTNGRP = new ButtonGroup();
        genderBTNGRP.add(maleRDB);
        genderBTNGRP.add(femaleRDB);

        // 5 Buttons
        newBTN = new JButton("New");
        saveBTN = new JButton("Save");
        showBTN = new JButton("Show");
        quitBTN = new JButton("Quit");
        depositBTN = new JButton("Deposit");
        withdrawBTN = new JButton("Withdraw");

        // 6- Table
        accountsLSTMDL = new DefaultListModel<>();
        accountsLST = new JList<>(accountsLSTMDL);

        //7 Panels
        p1 = new JPanel(); p1.setBounds(5, 5, 300, 150);
        p1.setLayout(new GridLayout(5,2));

        p2 = new JPanel(); p2.setBounds(5, 155, 300, 40);
        p2.setLayout(new FlowLayout());

        p3 = new JPanel(); p3.setBounds(5, 195, 600, 40);
        p3.setLayout(new FlowLayout());

        p4 = new JPanel(); p4.setBounds(305, 5, 300, 190);
        p4.setLayout(new BorderLayout());

        p5 = new JPanel(); p5.setBounds(5, 240, 580, 120);
        p5.setLayout(new BorderLayout());

        // adding components to panel
        p1.add(accnNoLBL);
        p1.add(accNoTXT);
        p1.add(ownerLBL);
        p1.add(ownerTXT);
        p1.add(balalnceLBL);
        p1.add(balanceTXT);
        p1.add(cityLBL);
        p1.add(citiesCMB);
        p1.add(maleRDB);
        p1.add(femaleRDB);

        p2.add(newBTN);
        p2.add(saveBTN);
        p2.add(showBTN);
        p2.add(quitBTN);

        p3.add(amountLBL);
        p3.add(amountTXT);
        p3.add(depositBTN);
        p3.add(withdrawBTN);

        p4.add(accountsLST);


        // adding panels to frame
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);

        // table creation
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        tableModel.addColumn("TrsNO");
        tableModel.addColumn("TrsDate");
        tableModel.addColumn("TrsType");
        tableModel.addColumn("TrsAmount");

        JScrollPane scrollPane = new JScrollPane(table);
        p5.add(scrollPane);

        /************************************** Functionality ***************************/
        newBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               accNoTXT.setText("");
               ownerLBL.setText("");
               citiesCMB.setSelectedIndex(0);
               maleRDB.setSelected(true);
               balanceTXT.setText("");
               amountTXT.setText("");
               newRec = true;
            }
        });

        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (newRec) {
                    if (ownerTXT.getText().length() != 0) {
                        acc = new Account(
                              ownerTXT.getText(),
                                (City) citiesCMB.getSelectedItem(),
                                maleRDB.isSelected() ? 'M' : 'F'
                        );
                        accNoTXT.setText(String.valueOf(acc.accNo));
                        accountSet.add(acc);
                        accountsLSTMDL.addElement(acc);
                        newRec = false;
                    } else {
                        JOptionPane.showMessageDialog(null, "Please find all fields");
                    }
                } else {
                    accountSet.remove(acc);

                    int a = Integer.parseInt(accNoTXT.getText());
                    String o = ownerTXT.getText();
                    City c = (City) citiesCMB.getSelectedItem();

                    char g = maleRDB.isSelected() ? 'M' : 'F';
                    double b = Double.parseDouble(balanceTXT.getText());
                    acc = new Account(a,o,c,g,b);
                    accountSet.add(acc);
                    accountsLSTMDL.setElementAt(acc, accountsLST.getSelectedIndex());
                    newRec = false;


                }
            }
        });

    }

    public static void main(String[] args) {
        AccountFrame accountFrame = new AccountFrame();
        accountFrame.setVisible(true);
        accountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
