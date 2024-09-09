import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    private static final Font BOLD_FONT = new Font("Aharoni", Font.BOLD, 15);
    
    private AdminMethods adminMethods;

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        adminMethods = new AdminMethods();

        // Panel 0 (Header)
        JPanel panel0 = new JPanel();
        panel0.setBackground(new Color(0x8F1402));
        panel0.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel LabelLogo1 = new JLabel("I-Go!");
        LabelLogo1.setFont(new Font("Aharoni", Font.BOLD, 50));
        LabelLogo1.setForeground(Color.WHITE);
        
        JLabel LabelLogo2 = new JLabel("Public Scheduling Transportation System");
        LabelLogo2.setFont(new Font("Aharoni", Font.BOLD, 15));
        LabelLogo2.setForeground(Color.WHITE);
        
        panel0.add(LabelLogo1, gbc);
        gbc.gridy = 1;
        panel0.add(LabelLogo2, gbc);

        // Tabbed pane to hold different functionalities
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Manage User", adminMethods.manageUser());
        tabbedPane.addTab("Create Schedule", adminMethods.createSchedulePanel());
        tabbedPane.addTab("Update Schedule", adminMethods.updateSchedulePanel());
        tabbedPane.addTab("Assign Transport", adminMethods.assignTransportPanel());

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(BOLD_FONT);
        JButton quitButton = new JButton("Quit");
        quitButton.setFont(BOLD_FONT);

        buttonPanel.add(logoutButton);
        buttonPanel.add(quitButton);

        quitButton.addActionListener(e -> System.exit(0));

        // Add components to the frame
        add(panel0, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listener for logout button
        logoutButton.addActionListener(e -> {
            new HomePage().setVisible(true); // Open the home page
            dispose(); // Close the current AdminDashboard window
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new AdminDashboard();
    }
}
