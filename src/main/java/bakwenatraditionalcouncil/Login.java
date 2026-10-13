package bakwenatraditionalcouncil;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame {
    private final JTextField txtUser = new JTextField(20);
    private final JPasswordField txtPass = new JPasswordField(20);
    private final JComboBox<String> cmbType = new JComboBox<>(new String[]{"SELECT","ADMIN","USER"});

    public Login() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 260);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("LOGIN PAGE", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        add(lblTitle, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(3,2,8,8));
        center.setBorder(BorderFactory.createEmptyBorder(16,16,16,16));
        center.add(new JLabel("Username:"));
        center.add(txtUser);
        center.add(new JLabel("Password:"));
        center.add(txtPass);
        center.add(new JLabel("User Type:"));
        center.add(cmbType);
        add(center, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnLogin = new JButton("Login");
        JButton btnExit = new JButton("Exit");
        bottom.add(btnLogin);
        bottom.add(btnExit);
        add(bottom, BorderLayout.SOUTH);

        btnLogin.addActionListener(e -> doLogin());
        btnExit.addActionListener(e -> dispose());
    }

    private void doLogin() {
        String username = txtUser.getText().trim();
        String password = new String(txtPass.getPassword()).trim();
        String userType = (String) cmbType.getSelectedItem();

        if (username.isEmpty() || password.isEmpty() || "SELECT".equalsIgnoreCase(userType)) {
            JOptionPane.showMessageDialog(this, "Please complete all fields.", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "SELECT Username, User_Type FROM admin WHERE Username = ? AND Password = ?";

        try (Connection con = ConnectionProvider.getCon();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, username);
            pst.setString(2, password);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String dbType = rs.getString("User_Type");
                    String dbUser = rs.getString("Username");

                    if ("ADMIN".equalsIgnoreCase(userType) && "ADMIN".equalsIgnoreCase(dbType)) {
                        new AdminPage(dbUser).setVisible(true);
                        dispose();
                        return;
                    }
                    if ("USER".equalsIgnoreCase(userType) && "USER".equalsIgnoreCase(dbType)) {
                        new UserPage(dbUser).setVisible(true);
                        dispose();
                        return;
                    }
                    JOptionPane.showMessageDialog(this, "Selected user type does not match account.", "Login", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
