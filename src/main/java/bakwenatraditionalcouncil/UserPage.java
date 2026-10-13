package bakwenatraditionalcouncil;

import javax.swing.*;
import java.awt.*;

public class UserPage extends JFrame {
    public UserPage(String username) {
        setTitle("User Home");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 420);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lbl = new JLabel("Welcome, "+username, SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 22));
        add(lbl, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(2,2,10,10));
        grid.setBorder(BorderFactory.createEmptyBorder(16,16,16,16));

        JButton btnAdd = new JButton("Add Member");
        JButton btnUpdate = new JButton("Update Member");
        JButton btnView = new JButton("View Members");
        JButton btnExit = new JButton("Exit");

        btnAdd.addActionListener(e -> { new AddMember().setVisible(true); dispose(); });
        btnUpdate.addActionListener(e -> { new UpdateMember().setVisible(true); dispose(); });
        btnView.addActionListener(e -> { new ViewMember().setVisible(true); dispose(); });
        btnExit.addActionListener(e -> dispose());

        grid.add(btnAdd);
        grid.add(btnUpdate);
        grid.add(btnView);
        grid.add(btnExit);

        add(grid, BorderLayout.CENTER);
    }
}
