package src;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    //Panel5: Check And Payments
    JPanel feePanel;
    JLabel installFeeLBL;
    JLabel packageFeeLBL;
    JLabel totalFeeLBL;

    //Panel6: Table(Date of Subscription)
    JTable table;
    DefaultTableModel tableModel;
    JPanel p6Panel;

    // Panel 7: Action panel
    JButton saveBTN;
    JButton loadBTN;
    JButton newBTN;
    JPanel p7ActionPanel;

    // Classes and Objects
    Subscriber subscriber;
    Subscription subscription;
    int selectedPackagesPrice = 0;
    int totalPrice;

    // Saving
    ArrayList<Subscription> listToSAve = new ArrayList<>();
    File file;
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

        /*************************************** Panel 5 ********************************/
        feePanel = new JPanel();
        feePanel.setBounds(645, 15, 200, 200);
        feePanel.setLayout(new GridLayout(3,1));
        Border blackLine5 = BorderFactory.createTitledBorder("Fee & Check");

        installFeeLBL = new JLabel("Installation Fee");
        packageFeeLBL = new JLabel("Package Fee: ");
        totalFeeLBL = new JLabel("Total Amount to pay: ");

        feePanel.add(installFeeLBL);
        feePanel.add(packageFeeLBL);
        feePanel.add(totalFeeLBL);

        /*************************************** Panel 6 ********************************/
        p6Panel = new JPanel();
        p6Panel.setBounds(645, 230, 515, 500);
        p6Panel.setLayout(new GridLayout(3, 1));

        Border border6 = BorderFactory.createTitledBorder("Our Customers");
        p6Panel.setBorder(border6);

        // Table
        // 1- table model
        tableModel = new DefaultTableModel();

        // 2- Columns
        table = new JTable(tableModel);
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name:");
        tableModel.addColumn("Phone Number");
        tableModel.addColumn("Start Cycle");
        tableModel.addColumn("End Cycle");
        tableModel.addColumn("Total Fee");

        // 3 - Scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        p6Panel.add(scrollPane);

        /************************************ PANEL 7 ********************************/
        p7ActionPanel = new JPanel();
        p7ActionPanel.setBounds(860, 15, 300, 200);
        Border border7 = BorderFactory.createTitledBorder("Action Tab");
        p7ActionPanel.setBorder(border7);
        p7ActionPanel.setLayout(new GridLayout(4, 1));


        saveBTN = new JButton("Save Subscription");
        loadBTN = new JButton("Load Subscription");
        newBTN = new JButton("New Subscription");

        p7ActionPanel.add(newBTN);
        p7ActionPanel.add(saveBTN);
        p7ActionPanel.add(loadBTN);

        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveSubscriptionToDisk();
            }
        });

        newBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewSubscription();
            }
        });

        loadBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoadDataFromDisk();
            }
        });


        // Adding Panels To JFrame
        setLayout(null); // null layout for Jframe
        add(subscriberPanel); // panel 1
        add(cyclePanel);    // panel 2
        add(packagesPanel); // panel 3
        add(detailsPanel); // panel 4
        add(feePanel); // panel 5
        add(p6Panel); //panel 6
        add(p7ActionPanel); // panel 7

    }

    /*************************************** METHODS *************************************/
    private int DisplaySportsChannels() {
        SportChannel s1 = new SportChannel("AFN Sports", "EN", "SPRT",5);
        SportChannel s2 = new SportChannel("beIN Sports", "FR", "SPRT",3);
        SportChannel s3 = new SportChannel("Eleven Sports", "EN", "SPRT",8);
        SportChannel s4 = new SportChannel("NBA TV", "EN", "SPRT",6);
        SportChannel s5 = new SportChannel("NFL Network", "AR", "SPRT",3);
        SportChannel s6 = new SportChannel("The Ski Channel", "USA", "SPRT",1);


        ArrayList<SportChannel> sportList = new ArrayList<>();
        sportList.add(s1);
        sportList.add(s2);
        sportList.add(s3);
        sportList.add(s4);
        sportList.add(s5);
        sportList.add(s6);

        String sprtChannelString = "";
        int packagePrice = 0;

        for (int i= 0; i < sportList.size() ; i++){
            sprtChannelString +=
                    "     "+ sportList.get(i).getChannelName()
                            + "     "+ sportList.get(i).getLanguage()
                            + "     " + sportList.get(i).getPrice()
                            + "\n";
            packagePrice += sportList.get(i).getPrice();
        }
        channelsAreasSports.setText(sprtChannelString);

        return  packagePrice;


    }

    private int DisplayMoviesChannels() {
        MovieChannel m1 = new MovieChannel("MBC Bundle", "EN", "MOV", 4);
        MovieChannel m2 = new MovieChannel("Cinema One", "EN", "MOV",5);
        MovieChannel m3 = new MovieChannel("Cinema Pro", "RU", "MOV",6);
        MovieChannel m4 = new MovieChannel("Cinema 1", "AR", "MOV",2);
        MovieChannel m5 = new MovieChannel("Movie Home", "GR", "MOV",4);
        MovieChannel m6 = new MovieChannel("Film4", "FR", "MOV",2);

        ArrayList<MovieChannel> movieList = new ArrayList<>();
        movieList.add(m1);
        movieList.add(m2);
        movieList.add(m3);
        movieList.add(m4);
        movieList.add(m5);
        movieList.add(m6);

        String movChannelString = "";
        int packagePrice =0;

        for (int i= 0; i < movieList.size() ; i++){
            movChannelString +=
                    "     "+ movieList.get(i).getChannelName()
                            + "     "+ movieList.get(i).getLanguage()
                            + "     " + movieList.get(i).getPrice()
                            + "\n";
            packagePrice += movieList.get(i).getPrice();
        }
        channelsAreaMovies.setText(movChannelString);
        return packagePrice;
    }

    private int DisplayDocumentaryChannels() {
        DocumentaryChannel m1 = new DocumentaryChannel(
                "NAT GEO", "SP", "DOC", 3);
        DocumentaryChannel m2 = new DocumentaryChannel(
                "PBS America", "EN", "DOC", 2
        );
        DocumentaryChannel m3 = new DocumentaryChannel(
                "Al Jazeera Documentary", "IN", "DOC", 3
        );
        DocumentaryChannel m4 = new DocumentaryChannel(
                "Canal D", "USA", "EN", 4
        );
        DocumentaryChannel m5 = new DocumentaryChannel(
                "Discovery Historia", "AR", "DOC", 5
        );
        DocumentaryChannel m6 = new DocumentaryChannel(
                "World Documentary", "GR", "DOC", 1
        );

        ArrayList<DocumentaryChannel> documentaryChannels = new ArrayList<>();
        documentaryChannels.add(m1);
        documentaryChannels.add(m2);
        documentaryChannels.add(m3);
        documentaryChannels.add(m4);
        documentaryChannels.add(m5);
        documentaryChannels.add(m6);

        String docChannelString = "";
        int packagesPrice = 0;
        for (int i =0 ; i < documentaryChannels.size(); i++) {
            docChannelString +=
                    "     " + documentaryChannels.get(i).getChannelName()
                    + "     "+ documentaryChannels.get(i).getLanguage()
                    + "     " + documentaryChannels.get(i).getPrice()
                    + "\n";
            packagesPrice += documentaryChannels.get(i).getPrice();
        }
        channelsAreaDoc.setText(docChannelString);
        return packagesPrice;
    }

    private void GetSubscriberData() throws ParseException {
        Date currentDAte = new Date();

        // Subscriber Data
        subscriber = new Subscriber(
                subName.getText(),
                subLastName.getText(),
                subCity.getText(),
                Integer.parseInt(subMobile.getText()));

        // Cycle
        Date startCycle = df.parse(startCycleFLD.getText());
        Date endCycle = df.parse(endCycleFLD.getText());

        SubscriptionCycle cycle = new SubscriptionCycle(
                df.format(startCycle),
                df.format(endCycle)
        );

        // Subscription
        subscription = new Subscription(
                Integer.parseInt(numberTVFLD.getText()),
                        subscriber,
                        cycle,
                        df.format(currentDate)
        );

        installFeeLBL.setText("Installation Fee: " +
                                subscription.getTotalFee() + " $");
        showPrice();
    }

    private void showPrice() {

        if (docCHKBX.isSelected()) {
            selectedPackagesPrice += DisplayDocumentaryChannels();
        } else if (moviesCHKBX.isSelected()) {
            selectedPackagesPrice += DisplayMoviesChannels();
        } else if (sportsCHKBX.isSelected()) {
            selectedPackagesPrice += DisplaySportsChannels();
        }

        packageFeeLBL.setText("Packages Fee: " + selectedPackagesPrice + " $");
        totalPrice = subscription.getTotalFee() + selectedPackagesPrice;

        totalFeeLBL.setText("Total Amount To Pay:" + totalPrice + " $");

    }
    private void NewSubscription() {
        // All fields are empty
        subName.setText("");
        subLastName.setText("");
        subCity.setText("");
        subMobile.setText("");

        startCycleFLD.setText("");
        endCycleFLD.setText("");
        numberTVFLD.setText("");

        installFeeLBL.setText("Installation Fee: ");
        packageFeeLBL.setText("Packages Fee: ");
        totalFeeLBL.setText("Total Amount to Pay: ");

        moviesCHKBX.setSelected(false);
        docCHKBX.setSelected(false);
        sportsCHKBX.setSelected(false);


    }

    private void SaveSubscriptionToDisk() {
        listToSAve.add(subscription);
        file = new File(System.getProperty("user.dir"));
//        file = new File(Paths.get("./").toUri());

        try {
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            // saving the list of subscriptions
            oos.writeObject(listToSAve);
            oos.flush();
            oos.close();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void LoadDataFromDisk() {
        ArrayList<Subscription> subscriptionArrayList = new ArrayList<>();
        file = new File(System.getProperty("user.dir"));

        try {
            InputStream inputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            subscriptionArrayList = (ArrayList<Subscription>) objectInputStream.readObject();
            objectInputStream.close();
            inputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (Subscription subscription1: subscriptionArrayList) {
            DisplaySubscriptionInTable();
        }
    }

    private void DisplaySubscriptionInTable(Subscription subscription) {
        // Displaying data from disk to table
        tableModel.addRow(new Object[]{
                subscription.getSubscriber().getfName(),
                subscription.getSubscriber().getlName(),
                subscription.getSubscriber().getPhone(),
                subscription.getCycle().getStartDate(),
                subscription.getCycle().getEndDate(),
                subscription.getTotalFee()
        });
    }

    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
        mainScreen.setBounds(20,10,1200,800);
    }
}
