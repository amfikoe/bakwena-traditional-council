package bakwenatraditionalcouncil;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateMember extends JFrame {
    private final JTextField memberId = new JTextField();
    private final JTextField lastName = new JTextField();
    private final JTextField firstName = new JTextField();
    private final JTextField identity = new JTextField();
    private final JTextField gender = new JTextField();
    private final JTextField address = new JTextField();
    private final JTextField contact = new JTextField();
    private final JTextField status = new JTextField();

    // beneficiaries
    private final JTextField f1 = new JTextField();
    private final JTextField id1 = new JTextField();
    private final JTextField rel1 = new JTextField();

    public UpdateMember() {
        setTitle("Update Member");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000,700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Member ID:")); top.add(memberId);
        JButton btnSearch = new JButton("Search");
        top.add(btnSearch);
        add(top, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(0,2,8,8));
        form.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        form.add(new JLabel("Last Name:")); form.add(lastName);
        form.add(new JLabel("First Name:")); form.add(firstName);
        form.add(new JLabel("Identity:")); form.add(identity);
        form.add(new JLabel("Gender:")); form.add(gender);
        form.add(new JLabel("Address:")); form.add(address);
        form.add(new JLabel("Contact:")); form.add(contact);
        form.add(new JLabel("Status:")); form.add(status);

        form.add(new JLabel("Beneficiary 1 Name:")); form.add(f1);
        form.add(new JLabel("Beneficiary 1 ID:")); form.add(id1);
        form.add(new JLabel("Beneficiary 1 Rel:")); form.add(rel1);

        add(new JScrollPane(form), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnUpdate = new JButton("Update");
        JButton btnBack = new JButton("Back");
        bottom.add(btnUpdate); bottom.add(btnBack);
        add(bottom, BorderLayout.SOUTH);

        btnSearch.addActionListener(e -> searchMember());
        btnUpdate.addActionListener(e -> updateMember());
        btnBack.addActionListener(e -> { new AdminPage("ADMIN").setVisible(true); dispose(); });
    }

    private void searchMember() {
        String id = memberId.getText().trim();
        if (id.isEmpty()) { JOptionPane.showMessageDialog(this, "Enter Member ID"); return; }
        String sql = "SELECT * FROM members WHERE Member_id = ?";
        try (Connection con = ConnectionProvider.getCon();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    lastName.setText(rs.getString("Last_Name"));
                    firstName.setText(rs.getString("First_Name"));
                    identity.setText(rs.getString("Identity_No"));
                    gender.setText(rs.getString("Gender"));
                    address.setText(rs.getString("P_Address"));
                    contact.setText(rs.getString("C_Details"));
                    status.setText(rs.getString("M_Status"));

                    f1.setText(rs.getString("F_Name1"));
                    id1.setText(rs.getString("ID_No1"));
                    rel1.setText(rs.getString("Relationship1"));
                } else {
                    JOptionPane.showMessageDialog(this, "Member not found");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateMember() {
        String id = memberId.getText().trim();
        if (id.isEmpty()) { JOptionPane.showMessageDialog(this, "Search member first"); return; }

        String sql = "UPDATE members SET Last_Name=?, First_Name=?, Identity_No=?, Gender=?, P_Address=?, C_Details=?, M_Status=?, F_Name1=?, ID_No1=?, Relationship1=? WHERE Member_id=?";
        try (Connection con = ConnectionProvider.getCon();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, lastName.getText().trim());
            pst.setString(2, firstName.getText().trim());
            pst.setString(3, identity.getText().trim());
            pst.setString(4, gender.getText().trim());
            pst.setString(5, address.getText().trim());
            pst.setString(6, contact.getText().trim());
            pst.setString(7, status.getText().trim());
            pst.setString(8, f1.getText().trim());
            pst.setString(9, id1.getText().trim());
            pst.setString(10, rel1.getText().trim());
            pst.setString(11, id);

            int rows = pst.executeUpdate();
            JOptionPane.showMessageDialog(this, (rows>0) ? "Updated" : "No rows updated");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
