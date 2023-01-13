package src;

import jdk.dynalink.linker.support.SimpleLinkRequest;
import src.Policy.Customer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class MainScreen extends JFrame {

    // Customization
    Font myFont = new Font("SansSerif", Font.BOLD, 20);
    Color myColor = Color.BLUE;

    // Panel 1
    JTextField subFName;
    JTextField subLName;
    JTextField subCity;
    JTextField subPhone;

    // Panel 2
    JTextField model;
    JTextField manufacturer;
    JTextField plateNb;
    JTextField estimated;
    JRadioButton damageRadio1;
    JRadioButton damageRadio2;
    JRadioButton damageRadio3;
    JRadioButton damageRadio4;
    ButtonGroup G1;

    // Panel 3
    JCheckBox obligatoryCHKBX;
    JCheckBox allRiskCHKBX;
    JCheckBox vDamageCHKBX;
    JCheckBox dDamageCHKBX;
    JCheckBox assistCHKBX;
    List<String> coveredRisksList = new ArrayList<>();
    List<Float> premiumRisksList = new ArrayList<>();
    List<Float> coverageRisksList = new ArrayList<>();
    List<Float> ceilingRiskList = new ArrayList<>();

    // Panel 4
    JRadioButton yearRadio;
    JRadioButton yearsRadio2;
    JRadioButton yearsRadio3;
    ButtonGroup G2;
    JLabel todayLBL;
    int validityYEAR = 0;
    SimpleDateFormat df;
    Date currentDate;

    // Panel 5
    JTextArea risksTXT;
    JTextField searchTXT;
    Map<Integer, Customer> customerMap = new TreeMap<>();

    // Panel 7
    JTextArea policyTXT;

    // Panel 8
    JTextArea customerTXT;


    // Panel 9
    JLabel claimingTXT;
    JLabel claimingTXT2;
    JTextField claimingCustomerField;


    // Panel 10
    JLabel claimingCustomerNameLBL;
    JLabel claimStatusLBL2;
    JTextArea claimingCustomerRisksCoveredAREA;
    JLabel claimingCustomerValidDateLBL;
    boolean cond1;
    boolean cond2;
    boolean cond3;

    // Panel 11
    JTextArea settlementAr;
    float totalPremium = 0f;
    float totalCoverage = 0f;
    float totalCeiling = 0f;

    public MainScreen() {
        CustomizePanel1();
        CustomizePanel2();
        CustomizePanel3();
        CustomizePanel4();
        CustomizePanel5();
        CustomizePanel6();
        CustomizePanel7();
        CustomizePanel8();
        CustomizePanel9();
        CustomizePanel10();
        CustomizePanel11();
    }

    private void CustomizePanel1() {
        JPanel p1 = new JPanel();
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                "Customer",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                myFont,
                myColor);
        p1.setBorder(titledBorder);


        JLabel fNameLBL = new JLabel("First Name:");
        JLabel lNAmeLBL = new JLabel("Last Name:");
        JLabel cityLBL = new JLabel("City");
        JLabel phoneLBL = new JLabel("Phone");

        subFName = new JTextField(); subFName.setOpaque(false);
        subLName = new JTextField(); subLName.setOpaque(false);
        subCity = new JTextField(); subCity.setOpaque(false);
        subPhone = new JTextField(); subPhone.setOpaque(false);

        p1.add(fNameLBL);
        p1.add(subFName);
        p1.add(lNAmeLBL);
        p1.add(subLName);
        p1.add(cityLBL);
        p1.add(subCity);
        p1.add(phoneLBL);
        p1.add(subPhone);

        p1.setBounds(15,15,300,200);
        p1.setLayout(new GridLayout(4,2));

        // Adding panels to JFRAME
        setLayout(null);
        add(p1);
    }

    private void  CustomizePanel2() {
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                "Vehicle",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                myFont,
                myColor);
        JPanel p2 = new JPanel();
        p2.setBorder(titledBorder);

        JLabel plateNbLBL = new JLabel("Plate Nb");
        JLabel modelLBL = new JLabel("Model Year");
        JLabel manufacturerLBL = new JLabel("Manufacturer");
        JLabel estimatedLBL = new JLabel("Estimated Value");
        JLabel spaceLBL = new JLabel(" ");
        JLabel damageLBL = new JLabel("Major Damage");

        // JTextFields
        plateNb = new JTextField(); plateNb.setOpaque(false);
        model = new JTextField(); model.setOpaque(false);
        manufacturer = new JTextField(); manufacturer.setOpaque(false);
        estimated = new JTextField(); estimated.setOpaque(false);


        // Radio Buttons
        damageRadio1 = new JRadioButton();
        damageRadio1.setText("Motor");
        damageRadio2 = new JRadioButton();
        damageRadio2.setText("Wheels");
        damageRadio3 = new JRadioButton();
        damageRadio3.setText("Body");
        damageRadio4 = new JRadioButton();
        damageRadio4.setText("None");

        G1 = new ButtonGroup();
        G1.add(damageRadio1);
        G1.add(damageRadio2);
        G1.add(damageRadio3);
        G1.add(damageRadio4);

        // Adding components to panel 2
        p2.add(plateNbLBL);
        p2.add(plateNb);
        p2.add(modelLBL);
        p2.add(model);
        p2.add(manufacturerLBL);
        p2.add(manufacturer);
        p2.add(estimatedLBL);
        p2.add(estimated);
        p2.add(spaceLBL);
        p2.add(damageLBL);
        p2.add(damageRadio1);
        p2.add(damageRadio2);
        p2.add(damageRadio3);
        p2.add(damageRadio4);

        p2.setBounds(15, 250, 300, 500);
        p2.setLayout(new GridLayout(14, 1));
        add(p2);


    }

    private void CustomizePanel3() {
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                "Plan",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                myFont,
                myColor);
        JPanel p3 = new JPanel();
        p3.setBorder(titledBorder);
        p3.setBounds(330, 15, 300,200);
        p3.setLayout(new GridLayout(6,1));

        JLabel packageLBL = new JLabel("Please select your plan");

        // Checkbox
        obligatoryCHKBX = new JCheckBox("Obligatory");
        allRiskCHKBX = new JCheckBox("Al Risk");
        vDamageCHKBX = new JCheckBox("Vehicle Damage");
        dDamageCHKBX = new JCheckBox("Driver Damage");
        assistCHKBX = new JCheckBox("Assistance");

        // Get all risks covered by plan
        GetRisksCoveredByPlan();

        // Adding components to panel3
        p3.add(packageLBL);
        p3.add(obligatoryCHKBX);
        p3.add(allRiskCHKBX);
        p3.add(vDamageCHKBX);
        p3.add(dDamageCHKBX);
        p3.add(assistCHKBX);

        add(p3);
    }

    private void CustomizePanel4() {
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                "Validity Period",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                myFont,
                myColor);
        JPanel p4 = new JPanel();
        p4.setBorder(titledBorder);
        p4.setBounds(330, 250, 300, 250);
        p4.setLayout(new GridLayout(6,1));


        // Radio Buttons
        JLabel spacer2 = new JLabel(" "); spacer2.setOpaque(false);
        yearRadio = new JRadioButton();
        yearRadio.setText(" 1 Year");
        yearsRadio2 = new JRadioButton();
        yearsRadio2.setText(" 2 Years");
        yearsRadio3 = new JRadioButton();
        yearsRadio3.setText(" 3 Years");

        yearRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validityYEAR = 1;
            }
        });

        yearsRadio2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validityYEAR = 2;
            }
        });

        yearsRadio3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validityYEAR = 3;
            }
        });

        // Button Groups
        G2 = new ButtonGroup();
        G2.add(yearRadio);
        G2.add(yearsRadio2);
        G2.add(yearsRadio3);

        // Time & Date
        todayLBL = new JLabel();
        df = new SimpleDateFormat("dd/MM/yyyy");
        currentDate = new Date();
        todayLBL.setText("Today: " + df.format(currentDate));
        todayLBL.setOpaque(false);

        Font font = todayLBL.getFont();
        float size = font.getSize() + 3.0f;
        todayLBL.setFont(font.deriveFont(size));

        // Adding all components to panel 4
        p4.add(spacer2);
        p4.add(spacer2);
        p4.add(todayLBL);
        p4.add(spacer2);
        p4.add(yearRadio);
        p4.add(yearsRadio2);
        p4.add(yearsRadio3);

        // Adding panel4 to jframe
        add(p4);
    }

    private void CustomizePanel5() {
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                "Validity Period",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                myFont,
                myColor);
        JPanel p5 = new JPanel();
        p5.setBounds(330, 520, 300, 230);
        p5.setBorder(titledBorder);
        p5.setLayout(new GridLayout(7, 1));

        JButton saveBTN = new JButton("Save Customer");
        JButton showBTN = new JButton("Show Plan Details");
        JButton loadBTN = new JButton("Load Customer");
        JButton newBTN = new JButton("New Customer");

        JTextField searchTXT = new JTextField("Enter Car Plate Nb");
        searchTXT.setOpaque(false);

        showBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JLabel spacer3 = new JLabel(" ");
        spacer3.setOpaque(false);

        loadBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JLabel spacer4 = new JLabel(" ");
        spacer4.setOpaque(false);
        JLabel spacer5 = new JLabel(" ");
        spacer5.setOpaque(false);
        JLabel spacer6 = new JLabel(" ");
        spacer6.setOpaque(false);

        newBTN.addActionListener((actionEvent) -> {

        });

        p5.add(spacer6);
        p5.add(showBTN);
        p5.add(saveBTN);
        p5.add(newBTN);
        p5.add(spacer5);
        p5.add(searchTXT);
        p5.add(loadBTN);

        add(p5);
    }

    private void CustomizePanel6() {
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                "Covered Risks",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                myFont,
                myColor);
        JPanel p6 = new JPanel();
        p6.setBorder(titledBorder);
        p6.setBounds(645, 15, 300, 200);

        // method area is for .class files heap area is for class instances
        risksTXT = new JTextArea(7,1);
        risksTXT.setEditable(false);
        risksTXT.setOpaque(false);
        risksTXT.setLineWrap(true);

        // Font
        Font font = risksTXT.getFont();
        float size = font.getSize() + 3.0f;
        risksTXT.setFont(font.deriveFont(size));

        p6.add(risksTXT);

        p6.setLayout(new GridLayout(1,1));
        add(p6); // adding p6 to jframe



    }

    private void CustomizePanel7() {
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                "Policy Datails",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                myFont,
                myColor);

        JPanel p7 = new JPanel();
        p7.setBounds(645, 250, 300, 250);
        p7.setBorder(titledBorder);
        p7.setLayout(new GridLayout(6,1));

        policyTXT = new JTextArea(20,1);
        policyTXT.setEditable(false);
        policyTXT.setOpaque(false);
        policyTXT.setLineWrap(true);

        Font font = policyTXT.getFont();
        float size = font.getSize() + 3.0f;
        policyTXT.setFont(font.deriveFont(size));

        p7.add(policyTXT);
        p7.setLayout(new GridLayout(1,1));
        add(p7); //adding jpanel7 to jframe


    }

    private void CustomizePanel8() {
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                "Customer Datails",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                myFont,
                myColor);
        JPanel p8 = new JPanel();
        p8.setBorder(titledBorder);
        p8.setBounds(645, 520, 300, 230);
        p8.setLayout(new GridLayout(6,1));

        customerTXT = new JTextArea(20,1);
        customerTXT.setEditable(false);
        customerTXT.setOpaque(false);
        customerTXT.setLineWrap(true);

        // increase the size of font for the jtextarea
        Font font = customerTXT.getFont();
        float size = font.getSize() + 3.0f;
        customerTXT.setFont(font.deriveFont(size));

        p8.add(customerTXT);

        p8.setLayout(new GridLayout(1,1));
        add(p8);
    }

    private void CustomizePanel9() {
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                "Claims",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                myFont,
                myColor);

        JPanel p9 = new JPanel();
        p9.setBorder(titledBorder);
        p9.setBounds(960, 15, 300, 485);

        claimingTXT = new JLabel("Enter Plate Nb. for the Claiming ");
        JLabel spacer99 = new JLabel("                                     ");
        claimingTXT2 = new JLabel("Select the Type of Damage or Assistance Needed");

        claimingCustomerField = new JTextField();
        claimingCustomerField.setPreferredSize(new Dimension(250, 30));
        claimingCustomerField.setOpaque(false);

        String[] items = { "Fire", "Robbery",
                "Third Party Damage",
                "Vehicle Damage",
                "Driver Damage",
                "Transport",
                "Car Replacement"
        };

        final JList claimList = new JList(items);

        claimList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        claimList.setOpaque(false);
        claimList.setPreferredSize(new Dimension(250, 150));

        JButton searchClaimer = new JButton("Search Customer");
        List<String> coveredRisksByUserLIST = new ArrayList<>();
        searchClaimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton confirmClaimBTN = new JButton(" Confirm Claim");
        confirmClaimBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        p9.add(claimingTXT);
        p9.add(claimingCustomerField);
        p9.add(searchClaimer);
        p9.add(spacer99);
        p9.add(claimingTXT2);
        p9.add(claimList);
        p9.add(confirmClaimBTN);

        add(p9);


    }

    private void CustomizePanel10() {
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                "Claim Status",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                myFont,
                myColor);

        JPanel p10 = new JPanel();
        p10.setBorder(titledBorder);
        p10.setBounds(960, 520, 300,230);
        p10.setLayout(new GridLayout(4,1));

        // Jlabels
        claimingCustomerNameLBL = new JLabel("Claiming Customer: ");
        claimingCustomerValidDateLBL = new JLabel("Date Validity of Policy");
        claimStatusLBL2 = new JLabel("Claiming status: ");

        //JTextArea
        claimingCustomerRisksCoveredAREA = new JTextArea();
        JScrollPane pictureScrollPane = new JScrollPane(claimingCustomerRisksCoveredAREA);
        claimingCustomerRisksCoveredAREA.setOpaque(false);

        p10.add(claimingCustomerNameLBL);
        p10.add(claimingCustomerValidDateLBL);
        p10.add(pictureScrollPane);
        p10.add(claimStatusLBL2);

        add(p10);
    }

    private void CustomizePanel11() {
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                "Payments",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                myFont,
                myColor);

        JPanel p11 =new JPanel();
        p11.setBorder(titledBorder);
        p11.setBounds(1275, 15, 250, 230);
        p11.setLayout(new GridLayout(2,1));

        settlementAr = new JTextArea();
        settlementAr.setOpaque(false);

        // Increasing the size of jtextArea
        Font font = settlementAr.getFont();
        float size = font.getSize() + 4.0f;
        settlementAr.setFont(font.deriveFont(size));

        p11.add(settlementAr);
        add(p11);
    }
    private void GetRisksCoveredByPlan() {
    }

    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
        mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScreen.setTitle("Insurance Company System");
        mainScreen.setBounds(0,0, 1920, 1080);
    }
}
