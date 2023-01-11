package src;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainScreen extends JFrame {

    // Panel1: Subscriber
    JPanel subscriberPanel;
    JTextField subName;
    JTextField subLastName;
    JTextField subMobile;
    JTextField subCity;

    JLabel nameLBL;
    JLabel lastLBL;
    JLabel mobileLBL;
    JLabel cityLBL;

    // Panel2: Cycle
    JPanel cyclePanel;
    JTextField startCycleFLD;
    JTextField endCycleFLD;
    JTextField numberTVFLD;
    JLabel todayLBL;
    SimpleDateFormat df;
    Date currentDate;
    JLabel startCycleLBL;
    JLabel endCycleLBL;
    JLabel numberTVLBL;

    // Panel3: Channels packages
    JCheckBox sportsCHKBX;
    JCheckBox moviesCHKBX;
    JCheckBox docCHKBX;
    JPanel packagesPanel;

    // Panel4: Package details
    JTextArea channelsAreasSports;
    JTextArea channelsAreaMovies;
    JTextArea channelsAreaDoc;
    JPanel detailsPanel;




    public MainScreen() {

        /************************************ PANEL 1 ********************************/
        subscriberPanel = new JPanel();
        Border panel1title = BorderFactory.createTitledBorder("Subscriber Details");
        subscriberPanel.setBorder(panel1title);
        subscriberPanel.setBounds(15, 15, 300, 200);
        subscriberPanel.setLayout(new GridLayout(4, 2));

        nameLBL = new JLabel("First Name: ");
        lastLBL = new JLabel("Last Name: ");
        mobileLBL = new JLabel("Mobile: ");
        cityLBL = new JLabel("City: ");

        subName = new JTextField();
        subLastName = new JTextField();
        subMobile = new JTextField();
        subCity = new JTextField();

        // make opacity for fields
        subName.setOpaque(false);
        subLastName.setOpaque(false);
        subMobile.setOpaque(false);
        subCity.setOpaque(false);

        // Adding components to panel 1
        subscriberPanel.add(nameLBL);
        subscriberPanel.add(subName);
        subscriberPanel.add(lastLBL);
        subscriberPanel.add(subLastName);
        subscriberPanel.add(mobileLBL);
        subscriberPanel.add(subMobile);
        subscriberPanel.add(cityLBL);
        subscriberPanel.add(subCity);

        /************************************** PANEL 2 *********************************/
        cyclePanel = new JPanel();
        cyclePanel.setBounds(15, 230, 300, 500);
        cyclePanel.setLayout(new GridLayout(14, 1));

        Border cycleBorder = BorderFactory.createTitledBorder("Cycle Details");
        cyclePanel.setBorder(cycleBorder);

        // Components of cycle panel
        todayLBL = new JLabel();
        df = new SimpleDateFormat("dd/MM/yyyy");
        currentDate = new Date();
        todayLBL.setText("Today: " + df.format(currentDate));

        // Start Cycle Date
        startCycleLBL = new JLabel("Start Cycle Date(DD/MM/YYYY)");
        startCycleFLD = new JTextField();

        // End Cycle Date
        endCycleLBL = new JLabel("End Cycle Date(DD/MM/YYYY)");
        endCycleFLD = new JTextField();

        // Number of TVs
        numberTVLBL = new JLabel("Number of TV:");
        numberTVFLD = new JTextField();

        cyclePanel.add(todayLBL);
        cyclePanel.add(startCycleLBL);
        cyclePanel.add(startCycleFLD);
        cyclePanel.add(endCycleLBL);
        cyclePanel.add(endCycleFLD);
        cyclePanel.add(numberTVLBL);
        cyclePanel.add(numberTVFLD);

        // make opacity for fields
        startCycleFLD.setOpaque(false);
        endCycleFLD.setOpaque(false);
        numberTVFLD.setOpaque(false);


        /************************************ PANEL 3 ********************************/
        packagesPanel = new JPanel();
        packagesPanel.setBounds(330,15,300,200);
        packagesPanel.setLayout(new GridLayout(5, 1));

        Border packBorder = BorderFactory.createTitledBorder("Available Packages");
        packagesPanel.setBorder(packBorder);

        JLabel packagesLBL = new JLabel("Please select your package");
        sportsCHKBX = new JCheckBox("Sports Package");
        moviesCHKBX = new JCheckBox("Movies Package");
        docCHKBX = new JCheckBox("Documentary Package");

        JButton subscribeBTN = new JButton("Subscribe");

        packagesPanel.add(packagesLBL);
        packagesPanel.add(sportsCHKBX);
        packagesPanel.add(moviesCHKBX);
        packagesPanel.add(docCHKBX);
        packagesPanel.add(subscribeBTN);

        // Checkbox Item Listeners
        sportsCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (sportsCHKBX.isSelected()) {
                    DisplaySportsChannels();
                    //make price changes
                } else {

                }
            }
        });

        moviesCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (moviesCHKBX.isSelected()) {
                    DisplayMoviesChannels();
                } else {

                }
            }
        });

        docCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (docCHKBX.isSelected()) {
                    DisplayDocumentaryChannels();
                } else {

                }
            }
        });

        subscribeBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GetSubscriberData();
                } catch (Exception exception) {
                    
                }
            }

        });

        /*************************************** Panel 4 ********************************/
        detailsPanel = new JPanel();
        detailsPanel.setBounds(330, 230, 300, 500);
        detailsPanel.setLayout(new GridLayout(3, 1));

        Border p4Border = BorderFactory.createTitledBorder("Available Channels");
        detailsPanel.setBorder(p4Border);

        channelsAreasSports = new JTextArea(5, 1);
        channelsAreasSports.setEditable(false);
        channelsAreasSports.setOpaque(false);
        channelsAreasSports.setLineWrap(true);

        channelsAreaMovies = new JTextArea(5, 1);
        channelsAreaMovies.setEditable(false);
        channelsAreaMovies.setOpaque(false);
        channelsAreaMovies.setLineWrap(true);

        channelsAreaDoc = new JTextArea(5, 1);
        channelsAreaDoc.setEditable(false);
        channelsAreaDoc.setOpaque(false);
        channelsAreaDoc.setLineWrap(true);

        detailsPanel.add(channelsAreasSports);
        detailsPanel.add(channelsAreaMovies);
        detailsPanel.add(channelsAreaDoc);


        // Adding Panels To JFrame
        setLayout(null); // null layout for Jframe
        add(subscriberPanel); // panel 1
        add(cyclePanel);    // panel 2
        add(packagesPanel); // panel 3
        add(detailsPanel); // panel 4
    }

    /*************************************** METHODS *************************************/
    private void DisplaySportsChannels() {
    }

    private void DisplayMoviesChannels() {

    }

    private void DisplayDocumentaryChannels() {

    }

    private void GetSubscriberData() {
    }

    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
        mainScreen.setBounds(100,100,1000,800);
    }
}
