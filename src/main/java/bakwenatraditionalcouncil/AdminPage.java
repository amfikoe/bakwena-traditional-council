package bakwenatraditionalcouncil;

import javax.swing.*;
import java.awt.*;

public class AdminPage extends JFrame {
    public AdminPage(String username) {
        setTitle("Admin Home");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 420);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lbl = new JLabel("Welcome, "+username, SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 22));
        add(lbl, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(2,3,10,10));
        grid.setBorder(BorderFactory.createEmptyBorder(16,16,16,16));

        JButton btnAdd = new JButton("Add Member");
        JButton btnUpdate = new JButton("Update Member");
        JButton btnView = new JButton("View Members");
        JButton btnPrint = new JButton("Print Member");
        JButton btnExit = new JButton("Exit");

        btnAdd.addActionListener(e -> { new AddMember().setVisible(true); dispose(); });
        btnUpdate.addActionListener(e -> { new UpdateMember().setVisible(true); dispose(); });
        btnView.addActionListener(e -> { new ViewMember().setVisible(true); dispose(); });
        btnPrint.addActionListener(e -> { new PrintMember().setVisible(true); dispose(); });
        btnExit.addActionListener(e -> dispose());

        grid.add(btnAdd);
        grid.add(btnUpdate);
        grid.add(btnView);
        grid.add(btnPrint);
        grid.add(btnExit);

        add(grid, BorderLayout.CENTER);
    }
}
