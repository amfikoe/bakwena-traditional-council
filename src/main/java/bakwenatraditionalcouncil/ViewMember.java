package bakwenatraditionalcouncil;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewMember extends JFrame {
    private final JTable table = new JTable();

    public ViewMember() {
        setTitle("View Members");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000,600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JButton btnLoad = new JButton("Load Members");
        JButton btnBack = new JButton("Back");
        JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        top.add(btnLoad);
        top.add(btnBack);
        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnLoad.addActionListener(e -> loadMembers());
        btnBack.addActionListener(e -> { new AdminPage("ADMIN").setVisible(true); dispose(); });
    }

    private void loadMembers() {
        String sql = "SELECT * FROM members";
        try (Connection con = ConnectionProvider.getCon();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            table.setModel(ResultSetTableModel.buildTableModel(rs));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
