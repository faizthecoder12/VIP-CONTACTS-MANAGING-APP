package vipcontactmanager;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.HashMap;

public class VIPContactManagerGUI {
    private JFrame frame;
    private JTextField nameField, phoneField, emailField;
    private JTextArea displayArea;
    private HashMap<String, Contact> contacts;
    // Theme colors
    private Color darkGreen = Color.decode("#0a1d15");
    private Color lightMint = Color.decode("#bfdecc");

    public VIPContactManagerGUI() {
        contacts = new HashMap<>();
        createGUI();
    }

    private void createGUI() {
        // Main Frame Setup
        frame = new JFrame("VIP Contact Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(lightMint);
        frame.setLayout(new BorderLayout(10, 10));

        // Header Panel with perfect centering
        JPanel headerPanel = new JPanel(new GridBagLayout());  // GridBagLayout for perfect centering
        headerPanel.setBackground(darkGreen);
        headerPanel.setPreferredSize(new Dimension(800, 100));

        // Load and scale logo
        ImageIcon logoIcon = new ImageIcon("src/vipcontactmanager/VCMA.png");
        Image image = logoIcon.getImage();
        Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);  // Updated dimensions
        logoIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(logoIcon);

        // Create and style title with larger size
        JLabel titleLabel = new JLabel("VIP Contact Manager Application");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 32));  // Increased font size
        titleLabel.setForeground(lightMint);

        // Create a container panel for perfect alignment
        JPanel containerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));  // Added gap between logo and title
        containerPanel.setBackground(darkGreen);
        containerPanel.add(logoLabel);
        containerPanel.add(titleLabel);

        // Add container to header panel
        headerPanel.add(containerPanel);

        frame.add(headerPanel, BorderLayout.NORTH);

        // Main Content Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(lightMint);

        // Input Panel
        JPanel inputPanel = createInputPanel();
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Display Area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Poppins", Font.PLAIN, 14));
        displayArea.setBackground(Color.WHITE);
        displayArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(darkGreen, 2),
                "Contact List",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Poppins", Font.BOLD, 14),
                darkGreen
            ),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(mainPanel, BorderLayout.CENTER);

        // Footer Panel
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(darkGreen);
        footerPanel.setPreferredSize(new Dimension(800, 30));
        JLabel footerLabel = new JLabel("Made by Faiz Ul Karim Siddiqui");
        footerLabel.setFont(new Font("Poppins", Font.BOLD, 12));
        footerLabel.setForeground(lightMint);
        footerPanel.add(footerLabel);
        frame.add(footerPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBackground(lightMint);
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(darkGreen, 2),
                "Contact Details",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Poppins", Font.BOLD, 14),
                darkGreen
            ),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Name Field
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel nameLabel = createLabel("Name:");
        inputPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        nameField = createTextField();
        inputPanel.add(nameField, gbc);

        // Phone Field
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel phoneLabel = createLabel("Phone:");
        inputPanel.add(phoneLabel, gbc);

        gbc.gridx = 1;
        phoneField = createTextField();
        inputPanel.add(phoneField, gbc);

        // Email Field
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel emailLabel = createLabel("Email:");
        inputPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        emailField = createTextField();
        inputPanel.add(emailField, gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(lightMint);

        JButton addButton = createButton("Add Contact");
        addButton.addActionListener(e -> addContact());

        JButton showButton = createButton("Show Contacts");
        showButton.addActionListener(e -> showContacts());

        JButton deleteButton = createButton("Delete Contact");
        deleteButton.addActionListener(e -> deleteContact());

        JButton clearButton = createButton("Clear Fields");
        clearButton.addActionListener(e -> clearFields());

        buttonPanel.add(addButton);
        buttonPanel.add(showButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        
        

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(buttonPanel, gbc);

        return inputPanel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Poppins", Font.BOLD, 14));
        label.setForeground(darkGreen);
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField(20);
        field.setFont(new Font("Poppins", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(darkGreen),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return field;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Poppins", Font.BOLD, 12));
        button.setBackground(darkGreen);
        button.setForeground(darkGreen);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(darkGreen.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(darkGreen);
            }
        });

        return button;
    }

    private void addContact() {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            showMessage("Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        contacts.put(name, new Contact(phone, email));
        showMessage("Contact added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        clearFields();
        showContacts();
    }

    private void showContacts() {
        if (contacts.isEmpty()) {
            displayArea.setText("No contacts available.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== VIP Contacts ===\n\n");
        
        for (String name : contacts.keySet()) {
            Contact contact = contacts.get(name);
            sb.append("Name: ").append(name).append("\n");
            sb.append("Phone: ").append(contact.getPhoneNumber()).append("\n");
            sb.append("Email: ").append(contact.getEmail()).append("\n");
            sb.append("------------------------\n");
        }

        displayArea.setText(sb.toString());
    }

    private void deleteContact() {
        String name = JOptionPane.showInputDialog(frame, 
            "Enter the name of the contact to delete:", 
            "Delete Contact", 
            JOptionPane.QUESTION_MESSAGE);

        if (name != null && !name.trim().isEmpty()) {
            if (contacts.containsKey(name)) {
                contacts.remove(name);
                showMessage("Contact deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                showContacts();
            } else {
                showMessage("Contact not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void clearFields() {
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        nameField.requestFocus();
    }

    private void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(frame, message, title, messageType);
    }

    public static void main(String[] args) {
        try {
            // Set system look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new VIPContactManagerGUI());
    }
}