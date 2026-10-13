package bakwenatraditionalcouncil;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateMember extends JFrame {
    private final JTextField txtMemberId = new JTextField();
    private final JButton btnSearch = new JButton("SEARCH");
    private final JButton btnUpdate = new JButton("UPDATE");
    private final JButton btnBack = new JButton("BACK");

    private final JTextField txtLastName = new JTextField();
    private final JTextField txtFirstName = new JTextField();
    private final JTextField txtId = new JTextField();
    private final JTextField txtGender = new JTextField();
    private final JTextField txtPAddress = new JTextField();
    private final JTextField txtCDetails = new JTextField();
    private final JTextField txtMStatus = new JTextField();

    private final JTextField txtKgoro = new JTextField();
    private final JTextField txtVillage = new JTextField();
    private final JTextField txtSection = new JTextField();
    private final JTextField txtExtension = new JTextField();
    private final JTextField txtStandNo = new JTextField();
    private final JTextField txtStandSize = new JTextField();

    private final JTextField txtName1 = new JTextField();
    private final JTextField txtId1 = new JTextField();
    private final JTextField txtRel1 = new JTextField();

    private final JTextField txtName2 = new JTextField();
    private final JTextField txtId2 = new JTextField();
    private final JTextField txtRel2 = new JTextField();

    private final JTextField txtName3 = new JTextField();
    private final JTextField txtId3 = new JTextField();
    private final JTextField txtRel3 = new JTextField();

    private final JTextField txtName4 = new JTextField();
    private final JTextField txtId4 = new JTextField();
    private final JTextField txtRel4 = new JTextField();

    private final JTextField txtName5 = new JTextField();
    private final JTextField txtId5 = new JTextField();
    private final JTextField txtRel5 = new JTextField();

    public UpdateMember() {
        setTitle("Update Member");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 900);
        setLocationRelativeTo(null);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("MEMBER ID:")); top.add(txtMemberId); top.add(btnSearch);

        JPanel form = new JPanel(new GridLayout(0,2,8,8));
        form.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        form.add(new JLabel("LAST NAME:")); form.add(txtLastName);
        form.add(new JLabel("FIRST NAME:")); form.add(txtFirstName);
        form.add(new JLabel("IDENTITY NUMBER:")); form.add(txtId);
        form.add(new JLabel("GENDER:")); form.add(txtGender);
        form.add(new JLabel("PHYSICAL ADDRESS:")); form.add(txtPAddress);
        form.add(new JLabel("CONTACT DETAILS:")); form.add(txtCDetails);
        form.add(new JLabel("MARITAL STATUS:")); form.add(txtMStatus);

        form.add(new JLabel("KGORO:")); form.add(txtKgoro);
        form.add(new JLabel("VILLAGE:")); form.add(txtVillage);
        form.add(new JLabel("SECTION:")); form.add(txtSection);
        form.add(new JLabel("EXTENSION:")); form.add(txtExtension);
        form.add(new JLabel("STAND NO:")); form.add(txtStandNo);
        form.add(new JLabel("STAND SIZE:")); form.add(txtStandSize);

        form.add(new JLabel("F_NAME1:")); form.add(txtName1);
        form.add(new JLabel("ID_NO1:")); form.add(txtId1);
        form.add(new JLabel("RELATIONSHIP1:")); form.add(txtRel1);

        form.add(new JLabel("F_NAME2:")); form.add(txtName2);
        form.add(new JLabel("ID_NO2:")); form.add(txtId2);
        form.add(new JLabel("RELATIONSHIP2:")); form.add(txtRel2);

        form.add(new JLabel("F_NAME3:")); form.add(txtName3);
        form.add(new JLabel("ID_NO3:")); form.add(txtId3);
        form.add(new JLabel("RELATIONSHIP3:")); form.add(txtRel3);

        form.add(new JLabel("F_NAME4:")); form.add(txtName4);
        form.add(new JLabel("ID_NO4:")); form.add(txtId4);
        form.add(new JLabel("RELATIONSHIP4:")); form.add(txtRel4);

        form.add(new JLabel("F_NAME5:")); form.add(txtName5);
        form.add(new JLabel("ID_NO5:")); form.add(txtId5);
        form.add(new JLabel("RELATIONSHIP5:")); form.add(txtRel5);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(btnUpdate); buttons.add(btnBack);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(form), BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        btnSearch.addActionListener(e -> onSearch());
        btnUpdate.addActionListener(e -> onUpdate());
        btnBack.addActionListener(e -> dispose());
    }

    private void onSearch() {
        String memberID = txtMemberId.getText().trim();
        if (memberID.isEmpty()) { JOptionPane.showMessageDialog(this, "Enter Member ID"); return; }

        String sql = "SELECT * FROM members WHERE Member_id = ?";
        try (Connection con = ConnectionProvider.getCon();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, memberID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    txtLastName.setText(rs.getString("Last_Name"));
                    txtFirstName.setText(rs.getString("First_Name"));
                    txtId.setText(rs.getString("Identity_No"));
                    txtGender.setText(rs.getString("Gender"));
                    txtPAddress.setText(rs.getString("P_Address"));
                    txtCDetails.setText(rs.getString("C_Details"));
                    txtMStatus.setText(rs.getString("M_Status"));

                    txtKgoro.setText(rs.getString("Kgoro"));
                    txtVillage.setText(rs.getString("Village"));
                    txtSection.setText(rs.getString("Section"));
                    txtExtension.setText(rs.getString("Extension"));
                    txtStandNo.setText(rs.getString("Stand_No"));
                    txtStandSize.setText(rs.getString("Stand_Size"));

                    txtName1.setText(rs.getString("F_Name1")); txtId1.setText(rs.getString("ID_No1")); txtRel1.setText(rs.getString("Relationship1"));
                    txtName2.setText(rs.getString("F_Name2")); txtId2.setText(rs.getString("ID_No2")); txtRel2.setText(rs.getString("Relationship2"));
                    txtName3.setText(rs.getString("F_Name3")); txtId3.setText(rs.getString("ID_No3")); txtRel3.setText(rs.getString("Relationship3"));
                    txtName4.setText(rs.getString("F_Name4")); txtId4.setText(rs.getString("ID_No4")); txtRel4.setText(rs.getString("Relationship4"));
                    txtName5.setText(rs.getString("F_Name5")); txtId5.setText(rs.getString("ID_No5")); txtRel5.setText(rs.getString("Relationship5"));
                } else {
                    JOptionPane.showMessageDialog(this, "Member ID does not exist");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onUpdate() {
        String memberid = txtMemberId.getText().trim();
        if (memberid.isEmpty()) { JOptionPane.showMessageDialog(this, "Search a member first"); return; }

        String sql = "UPDATE members SET Last_Name = ?, First_Name = ?, Identity_No = ?, Gender = ?, P_Address = ?, C_Details = ?, M_Status = ?, Kgoro = ?, Village = ?, Section = ?, Extension = ?, Stand_No = ?, Stand_Size = ?, F_Name1 = ?, ID_No1 = ?, Relationship1 = ?, F_Name2 = ?, ID_No2 = ?, Relationship2 = ?, F_Name3 = ?, ID_No3 = ?, Relationship3 = ?, F_Name4 = ?, ID_No4 = ?, Relationship4 = ?, F_Name5 = ?, ID_No5 = ?, Relationship5 = ? WHERE Member_id = ?";

        try (Connection con = ConnectionProvider.getCon();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, txtLastName.getText().trim());
            pst.setString(2, txtFirstName.getText().trim());
            pst.setString(3, txtId.getText().trim());
            pst.setString(4, txtGender.getText().trim());
            pst.setString(5, txtPAddress.getText().trim());
            pst.setString(6, txtCDetails.getText().trim());
            pst.setString(7, txtMStatus.getText().trim());
            pst.setString(8, txtKgoro.getText().trim());
            pst.setString(9, txtVillage.getText().trim());
            pst.setString(10, txtSection.getText().trim());
            pst.setString(11, txtExtension.getText().trim());
            pst.setString(12, txtStandNo.getText().trim());
            pst.setString(13, txtStandSize.getText().trim());

            pst.setString(14, txtName1.getText().trim()); pst.setString(15, txtId1.getText().trim()); pst.setString(16, txtRel1.getText().trim());
            pst.setString(17, txtName2.getText().trim()); pst.setString(18, txtId2.getText().trim()); pst.setString(19, txtRel2.getText().trim());
            pst.setString(20, txtName3.getText().trim()); pst.setString(21, txtId3.getText().trim()); pst.setString(22, txtRel3.getText().trim());
            pst.setString(23, txtName4.getText().trim()); pst.setString(24, txtId4.getText().trim()); pst.setString(25, txtRel4.getText().trim());
            pst.setString(26, txtName5.getText().trim()); pst.setString(27, txtId5.getText().trim()); pst.setString(28, txtRel5.getText().trim());

            pst.setString(29, memberid);

            // Note: we have 28 params above for beneficiaries + earlier 13 = 28? Adjust indices accordingly
            // To keep correct indexing, we actually need to set all parameters in correct order. We'll rebuild using 28 + previous count.
        } catch (Exception ex) {
            // We'll implement update with correct parameter indexing below to avoid compilation mismatch
        }

        // implement correct update with exact parameter ordering
        String sql2 = "UPDATE members SET Last_Name=?, First_Name=?, Identity_No=?, Gender=?, P_Address=?, C_Details=?, M_Status=?, Kgoro=?, Village=?, Section=?, Extension=?, Stand_No=?, Stand_Size=?, F_Name1=?, ID_No1=?, Relationship1=?, F_Name2=?, ID_No2=?, Relationship2=?, F_Name3=?, ID_No3=?, Relationship3=?, F_Name4=?, ID_No4=?, Relationship4=?, F_Name5=?, ID_No5=?, Relationship5=? WHERE Member_id=?";
        try (Connection con = ConnectionProvider.getCon();
             PreparedStatement pst = con.prepareStatement(sql2)) {

            pst.setString(1, txtLastName.getText().trim());
            pst.setString(2, txtFirstName.getText().trim());
            pst.setString(3, txtId.getText().trim());
            pst.setString(4, txtGender.getText().trim());
            pst.setString(5, txtPAddress.getText().trim());
            pst.setString(6, txtCDetails.getText().trim());
            pst.setString(7, txtMStatus.getText().trim());
            pst.setString(8, txtKgoro.getText().trim());
            pst.setString(9, txtVillage.getText().trim());
            pst.setString(10, txtSection.getText().trim());
            pst.setString(11, txtExtension.getText().trim());
            pst.setString(12, txtStandNo.getText().trim());
            pst.setString(13, txtStandSize.getText().trim());

            pst.setString(14, txtName1.getText().trim());
            pst.setString(15, txtId1.getText().trim());
            pst.setString(16, txtRel1.getText().trim());

            pst.setString(17, txtName2.getText().trim());
            pst.setString(18, txtId2.getText().trim());
            pst.setString(19, txtRel2.getText().trim());

            pst.setString(20, txtName3.getText().trim());
            pst.setString(21, txtId3.getText().trim());
            pst.setString(22, txtRel3.getText().trim());

            pst.setString(23, txtName4.getText().trim());
            pst.setString(24, txtId4.getText().trim());
            pst.setString(25, txtRel4.getText().trim());

            pst.setString(26, txtName5.getText().trim());
            pst.setString(27, txtId5.getText().trim());
            pst.setString(28, txtRel5.getText().trim());

            pst.setString(29, txtMemberId.getText().trim());

            int rows = pst.executeUpdate();
            if (rows > 0) JOptionPane.showMessageDialog(this, "Data Successfully Updated");
            else JOptionPane.showMessageDialog(this, "No changes were saved.", "Warning", JOptionPane.WARNING_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Update Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
