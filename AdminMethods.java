import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminMethods {

    // Define consistent fonts
    private static final Font STANDARD_FONT = new Font("Aharoni", Font.PLAIN, 15);
    private static final Font BOLD_FONT = new Font("Aharoni", Font.BOLD, 15);

    // Data storage for users and schedules
    private ArrayList<Object[]> users = new ArrayList<>();
    private ArrayList<Object[]> schedules = new ArrayList<>();

    // Constructor to initialize data
    public AdminMethods() {
        // Initialize schedules and add schedule data
        schedules = new ArrayList<>();
        // Example schedule data added in constructor for demonstration
        schedules.add(new Object[]{"TN001", "TB001", "Train", "08:00", "09:00", "Line 1"});
        schedules.add(new Object[]{"BS001", "TB001", "Bus", "08:00", "09:00", "Route 1"});
        schedules.add(new Object[]{"TN002", "TB002", "Train", "09:00", "10:00", "Line 2"});
        schedules.add(new Object[]{"BS002", "TB002", "Bus", "09:00", "10:00", "Route 2"});
    }

    public ArrayList<Object[]> getSchedules() {
        return schedules;
    }

    public ArrayList<Object[]> getTrainSchedules() {
        ArrayList<Object[]> trainSchedules = new ArrayList<>();
        for (Object[] schedule : schedules) {
            if ("Train".equals(schedule[1])) {
                trainSchedules.add(schedule);
            }
        }
        return trainSchedules;
    }

    public ArrayList<Object[]> getBusSchedules() {
        ArrayList<Object[]> busSchedules = new ArrayList<>();
        for (Object[] schedule : schedules) {
            if ("Bus".equals(schedule[1])) {
                busSchedules.add(schedule);
            }
        }
        return busSchedules;
    }

    // Method to create the panel for "Manage User"
    public JPanel manageUser() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Manage User");
        titleLabel.setFont(BOLD_FONT);
        titleLabel.setBounds(250, 20, 200, 30);
        panel.add(titleLabel);

        JLabel UserRoleLabel = new JLabel("User Role:");
        UserRoleLabel.setFont(STANDARD_FONT);
        UserRoleLabel.setBounds(100, 80, 150, 30);
        panel.add(UserRoleLabel);

        JComboBox<String> transportTypeCombo = new JComboBox<>(new String[]{"Public", "Driver", "Admin"});
        transportTypeCombo.setFont(STANDARD_FONT);
        transportTypeCombo.setBounds(300, 80, 250, 30);
        panel.add(transportTypeCombo);

        JLabel f_nameLabel = new JLabel("First name:");
        f_nameLabel.setFont(STANDARD_FONT);
        f_nameLabel.setBounds(100, 130, 150, 30);
        panel.add(f_nameLabel);

        JTextField f_nameField = new JTextField();
        f_nameField.setFont(STANDARD_FONT);
        f_nameField.setBounds(300, 130, 250, 30);
        panel.add(f_nameField);

        JLabel l_nameLabel = new JLabel("Last name:");
        l_nameLabel.setFont(STANDARD_FONT);
        l_nameLabel.setBounds(100, 180, 150, 30);
        panel.add(l_nameLabel);

        JTextField l_nameField = new JTextField();
        l_nameField.setFont(STANDARD_FONT);
        l_nameField.setBounds(300, 180, 250, 30);
        panel.add(l_nameField);

        JLabel DOBLabel = new JLabel("D.O.B. (dd-MM-yyyy):");
        DOBLabel.setFont(STANDARD_FONT);
        DOBLabel.setBounds(100, 230, 150, 30);
        panel.add(DOBLabel);

        JTextField DOBField = new JTextField();
        DOBField.setFont(STANDARD_FONT);
        DOBField.setBounds(300, 230, 250, 30);
        panel.add(DOBField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(STANDARD_FONT);
        emailLabel.setBounds(100, 280, 150, 30);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setFont(STANDARD_FONT);
        emailField.setBounds(300, 280, 250, 30);
        panel.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(STANDARD_FONT);
        passwordLabel.setBounds(100, 330, 150, 30);
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(STANDARD_FONT);
        passwordField.setBounds(300, 330, 250, 30);
        panel.add(passwordField);

        JLabel UserIdLabel = new JLabel("User ID:");
        UserIdLabel.setFont(STANDARD_FONT);
        UserIdLabel.setBounds(100, 380, 150, 30);
        panel.add(UserIdLabel);

        JTextField UserIdField = new JTextField();
        UserIdField.setFont(STANDARD_FONT);
        UserIdField.setBounds(300, 380, 250, 30);
        panel.add(UserIdField);

        JButton createButton = new JButton("Create");
        createButton.setFont(BOLD_FONT);
        createButton.setBounds(400, 410, 140, 45);
        panel.add(createButton);

        // Add a table to display users using DefaultTableModel
        String[] columns = {"User ID", "First Name", "Last Name", "Email", "Role"};
        DefaultTableModel userModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(userModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 470, 610, 200);
        panel.add(scrollPane);

        // Initialize the table with existing data
        for (Object[] user : users) {
            userModel.addRow(user);
        }


        // Add action listener to create button to add new user to the list and refresh table 
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String role = (String) transportTypeCombo.getSelectedItem();
                String firstName = f_nameField.getText();
                String lastName = l_nameField.getText();
                String dob = DOBField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String userId = UserIdField.getText();

                // Add new user to the list
                users.add(new Object[]{userId, firstName, lastName, email, role});

                // Refresh table
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(new Object[]{userId, firstName, lastName, email, role});

                // Clear input fields
                f_nameField.setText("");
                l_nameField.setText("");
                DOBField.setText("");
                emailField.setText("");
                passwordField.setText("");
                UserIdField.setText("");
            }
        });

        return panel;
    }

    // Method to create the panel for "Create Schedule"
    public JPanel createSchedulePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Create New Schedule");
        titleLabel.setFont(BOLD_FONT);
        titleLabel.setBounds(250, 20, 200, 30);
        panel.add(titleLabel);

        JLabel transportTypeLabel = new JLabel("Transport Type:");
        transportTypeLabel.setFont(STANDARD_FONT);
        transportTypeLabel.setBounds(100, 80, 150, 30);
        panel.add(transportTypeLabel);

        JComboBox<String> transportTypeCombo = new JComboBox<>(new String[]{"Bus", "Train"});
        transportTypeCombo.setFont(STANDARD_FONT);
        transportTypeCombo.setBounds(250, 80, 250, 30);
        panel.add(transportTypeCombo);

        JLabel departureLabel = new JLabel("Departure Time:");
        departureLabel.setFont(STANDARD_FONT);
        departureLabel.setBounds(100, 130, 150, 30);
        panel.add(departureLabel);

        JTextField departureField = new JTextField();
        departureField.setFont(STANDARD_FONT);
        departureField.setBounds(250, 130, 250, 30);
        panel.add(departureField);

        JLabel arrivalLabel = new JLabel("Arrival Time:");
        arrivalLabel.setFont(STANDARD_FONT);
        arrivalLabel.setBounds(100, 180, 150, 30);
        panel.add(arrivalLabel);

        JTextField arrivalField = new JTextField();
        arrivalField.setFont(STANDARD_FONT);
        arrivalField.setBounds(250, 180, 250, 30);
        panel.add(arrivalField);

        JLabel routeLabel = new JLabel("Route:");
        routeLabel.setFont(STANDARD_FONT);
        routeLabel.setBounds(100, 230, 150, 30);
        panel.add(routeLabel);

        JTextField routeField = new JTextField();
        routeField.setFont(STANDARD_FONT);
        routeField.setBounds(250, 230, 250, 30);
        panel.add(routeField);

        JButton createButton = new JButton("Create");
        createButton.setFont(BOLD_FONT);
        createButton.setBounds(360, 260, 140, 45);
        panel.add(createButton);

        // Add a table to display schedules using DefaultTableModel
        String[] columns = {"Transport ID", "Schedule ID", "Transport Type", "Departure Time", "Arrival Time", "Route"};
        DefaultTableModel scheduleModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(scheduleModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 320, 610, 200);
        panel.add(scrollPane);

        // Initialize the table with existing data
        for (Object[] schedule : schedules) {
            scheduleModel.addRow(schedule);
        }

        
        // Add action listener to create button to add new schedule to the list and refresh table
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String transportType = (String) transportTypeCombo.getSelectedItem();
                String departureTime = departureField.getText();
                String arrivalTime = arrivalField.getText();
                String route = routeField.getText();

                // Generate a schedule ID
                String transportID = transportType.equals("Bus") ? "BS" + (schedules.size() + 1) : "TN" + (schedules.size() + 1);
                String scheduleID = "TB" + (schedules.size() + 1);


                // Add new schedule to the list
                schedules.add(new Object[]{transportID, scheduleID, transportType, departureTime, arrivalTime, route});

                // Refresh table
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(new Object[]{transportID, scheduleID, transportType, departureTime, arrivalTime, route});

                // Clear input fields
                departureField.setText("");
                arrivalField.setText("");
                routeField.setText("");
            }
        });

        return panel;
    }

    // Method to create the panel for "Update Schedule"
    public JPanel updateSchedulePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Update Existing Schedule");
        titleLabel.setFont(BOLD_FONT);
        titleLabel.setBounds(250, 20, 200, 30);
        panel.add(titleLabel);

        JLabel scheduleIDLabel = new JLabel("Schedule ID:");
        scheduleIDLabel.setFont(STANDARD_FONT);
        scheduleIDLabel.setBounds(100, 80, 200, 30);
        panel.add(scheduleIDLabel);

        JComboBox<String> scheduleIDCombo = new JComboBox<>(new String[]{"TB001", "TB002"});
        scheduleIDCombo.setFont(STANDARD_FONT);
        scheduleIDCombo.setBounds(280, 80, 250, 30);
        panel.add(scheduleIDCombo);

        JLabel newDepartureLabel = new JLabel("New Departure Time:");
        newDepartureLabel.setFont(STANDARD_FONT);
        newDepartureLabel.setBounds(100, 130, 200, 30);
        panel.add(newDepartureLabel);

        JTextField newDepartureField = new JTextField();
        newDepartureField.setFont(STANDARD_FONT);
        newDepartureField.setBounds(280, 130, 250, 30);
        panel.add(newDepartureField);

        JLabel newArrivalLabel = new JLabel("New Arrival Time:");
        newArrivalLabel.setFont(STANDARD_FONT);
        newArrivalLabel.setBounds(100, 180, 200, 30);
        panel.add(newArrivalLabel);

        JTextField newArrivalField = new JTextField();
        newArrivalField.setFont(STANDARD_FONT);
        newArrivalField.setBounds(280, 180, 250, 30);
        panel.add(newArrivalField);

        JButton updateButton = new JButton("Update");
        updateButton.setFont(BOLD_FONT);
        updateButton.setBounds(390, 210, 140, 45);
        panel.add(updateButton);

        // Add a table to display schedules using DefaultTableModel
        String[] columns = {"Transport ID", "Schedule ID", "Transport Type", "Departure Time", "Arrival Time", "Route"};
        DefaultTableModel scheduleModel = new DefaultTableModel(columns, 0);  // No initial rows
        JTable table = new JTable(scheduleModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 270, 610, 200);
        panel.add(scrollPane);

        // Initialize the table with existing data from schedules list
        for (Object[] schedule : schedules) {
            scheduleModel.addRow(schedule);
        }

        JLabel scheduleIDLabel2 = new JLabel("Schedule ID:");
        scheduleIDLabel2.setFont(STANDARD_FONT);
        scheduleIDLabel2.setBounds(100, 480, 200, 30);
        panel.add(scheduleIDLabel2);

        JComboBox<String> scheduleIDCombo2 = new JComboBox<>(new String[]{"TB001", "TB002"});
        scheduleIDCombo2.setFont(STANDARD_FONT);
        scheduleIDCombo2.setBounds(280, 480, 250, 30);
        panel.add(scheduleIDCombo2);
        
        JButton deleteButton = new JButton("Delete");
        deleteButton.setFont(BOLD_FONT);
        deleteButton.setBounds(390, 510, 140, 45);
        panel.add(deleteButton);
        
        // Add action listener to update button to update the schedule and refresh table
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String scheduleID = (String) scheduleIDCombo.getSelectedItem();
                String newDeparture = newDepartureField.getText();
                String newArrival = newArrivalField.getText();

                // Find the schedule and update it
                for (Object[] schedule : schedules) {
                    if (schedule[1].equals(scheduleID)) {
                        schedule[3] = newDeparture;
                        schedule[4] = newArrival;
                        break;
                    }
                }

                // Refresh table
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);  // Clear table
                for (Object[] schedule : schedules) {
                    model.addRow(schedule);  // Add updated schedule data
                }
            }
        });

        // Add action listener to delete button to remove the schedule and refresh table
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String scheduleID = (String) scheduleIDCombo2.getSelectedItem();

                // Find and remove the schedule
                schedules.removeIf(schedule -> schedule[1].equals(scheduleID));

                // Refresh table
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);  // Clear table
                for (Object[] schedule : schedules){
                    model.addRow(schedule);  // Add updated schedule data
                }   
            }
        });
        
        return panel;
    }

    // Method to create the panel for "Assign Transport"
    public JPanel assignTransportPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Assign Transport to Schedule");
        titleLabel.setFont(BOLD_FONT);
        titleLabel.setBounds(200, 20, 250, 30);
        panel.add(titleLabel);

        JLabel scheduleIDLabel = new JLabel("Schedule ID:");
        scheduleIDLabel.setFont(STANDARD_FONT);
        scheduleIDLabel.setBounds(100, 80, 150, 30);
        panel.add(scheduleIDLabel);

        JComboBox<String> scheduleIDCombo = new JComboBox<>(new String[]{"TB001", "TB002"});
        scheduleIDCombo.setFont(STANDARD_FONT);
        scheduleIDCombo.setBounds(250, 80, 250, 30);
        panel.add(scheduleIDCombo);

        JLabel transportIDLabel = new JLabel("Transport ID:");
        transportIDLabel.setFont(STANDARD_FONT);
        transportIDLabel.setBounds(100, 130, 150, 30);
        panel.add(transportIDLabel);

        JComboBox<String> transportIDCombo = new JComboBox<>(new String[]{"BS001", "TN002"});
        transportIDCombo.setFont(STANDARD_FONT);
        transportIDCombo.setBounds(250, 130, 250, 30);
        panel.add(transportIDCombo);

        JLabel driverIDLabel = new JLabel("Driver ID:");
        driverIDLabel.setFont(STANDARD_FONT);
        driverIDLabel.setBounds(100, 180, 150, 30);
        panel.add(driverIDLabel);

        JComboBox<String> driverIdCombo = new JComboBox<>(new String[]{"DV001", "DV002"});
        driverIdCombo.setFont(STANDARD_FONT);
        driverIdCombo.setBounds(250, 180, 250, 30);
        panel.add(driverIdCombo);

        JButton assignButton = new JButton("Assign");
        assignButton.setFont(BOLD_FONT);
        assignButton.setBounds(350, 235, 140, 45);
        panel.add(assignButton);

        // Add a table to display assignments using DefaultTableModel
        String[] columns = {"Schedule ID", "Transport ID", "Driver ID", "Driver Name"};
        DefaultTableModel assignModel = new DefaultTableModel(columns, 0);  // No initial rows
        JTable table = new JTable(assignModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 290, 610, 200);
        panel.add(scrollPane);

        // Initialize the table with existing data dynamically
        for (Object[] assignment : schedules) {  // Replace schedules with actual assignments list
            assignModel.addRow(assignment);
        }

        // Add action listener to assign button to add new assignment to the list and refresh table
        // Lookup driver name from the 'users' list
        assignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String scheduleID = (String) scheduleIDCombo.getSelectedItem();
                String transportID = (String) transportIDCombo.getSelectedItem();
                String driverID = (String) driverIdCombo.getSelectedItem();
                String driverName = "";

                // Find the driver name dynamically from the users list
                for (Object[] user : users) {
                    if (user[0].equals(driverID)) {
                        driverName = (String) user[1];  // Assuming first name is at index 1
                        break;
                    }
                }

                // Add assignment to table (this could be stored similarly as schedules/users)
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(new Object[]{scheduleID, transportID, driverID, driverName});

                // Clear selections
                scheduleIDCombo.setSelectedIndex(0);
                transportIDCombo.setSelectedIndex(0);
                driverIdCombo.setSelectedIndex(0);
            }
        });

        return panel;
    }
}
