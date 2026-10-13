package bakwenatraditionalcouncil;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddMember extends JFrame {
    private final JTextField docNo = new JTextField();
    private final JTextField refNo = new JTextField();
    private final JTextField allocDate = new JTextField();
    private final JTextField lastName = new JTextField();
    private final JTextField firstName = new JTextField();
    private final JTextField idNo = new JTextField();
    private final JRadioButton rMale = new JRadioButton("Male");
    private final JRadioButton rFemale = new JRadioButton("Female");
    private final JTextField pAddress = new JTextField();
    private final JTextField cDetails = new JTextField();
    private final JComboBox<String> mStatus = new JComboBox<>(new String[]{"SELECT STATUS","SINGLE","MARRIED","WIDOW","WIDOWER","LIVING WITH PARTNER"});
    private final JTextField kgoro = new JTextField();
    private final JComboBox<String> village = new JComboBox<>(new String[]{"SELECT VILLAGE","HEBRON","KGABALATSANE","RABOKALA"});
    private final JComboBox<String> section = new JComboBox<>(new String[]{"SELECT SECTION","MOTSENG","MOSEJA","MATSHELAPATA","MIDWAY"});
    private final JComboBox<String> extension = new JComboBox<>(new String[]{"SELECT EXTENSION","EXT1","EXT2"});
    private final JTextField standNo = new JTextField();
    private final JRadioButton s25 = new JRadioButton("25 x 25");
    private final JRadioButton s50 = new JRadioButton("25 x 50");

    private final JTextField f1 = new JTextField();
    private final JTextField id1 = new JTextField();
    private final JComboBox<String> rel1 = new JComboBox<>(new String[]{"SELECT RELATIONSHIP","SON","DAUGHTER","PARENT","SIBLING"});

    public AddMember() {
        setTitle("Add Member");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900,600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lbl = new JLabel("Add Member", SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 22));
        add(lbl, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(0,2,8,8));
        form.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        form.add(new JLabel("Document No:")); form.add(docNo);
        form.add(new JLabel("Reference No:")); form.add(refNo);
        form.add(new JLabel("Allocation Date:")); form.add(allocDate);
        form.add(new JLabel("Last Name:")); form.add(lastName);
        form.add(new JLabel("First Name:")); form.add(firstName);
        form.add(new JLabel("ID No:")); form.add(idNo);
        form.add(new JLabel("Gender:")); JPanel g = new JPanel(); ButtonGroup gg = new ButtonGroup(); gg.add(rMale); gg.add(rFemale); g.add(rMale); g.add(rFemale); form.add(g);
        form.add(new JLabel("Physical Address:")); form.add(pAddress);
        form.add(new JLabel("Contact Details:")); form.add(cDetails);
        form.add(new JLabel("Marital Status:")); form.add(mStatus);
        form.add(new JLabel("Kgoro:")); form.add(kgoro);
        form.add(new JLabel("Village:")); form.add(village);
        form.add(new JLabel("Section:")); form.add(section);
        form.add(new JLabel("Extension:")); form.add(extension);
        form.add(new JLabel("Stand No:")); form.add(standNo);
        form.add(new JLabel("Stand Size:")); JPanel s = new JPanel(); ButtonGroup sg = new ButtonGroup(); sg.add(s25); sg.add(s50); s.add(s25); s.add(s50); form.add(s);

        // beneficiary 1
        form.add(new JLabel("Beneficiary 1 Full Name:")); form.add(f1);
        form.add(new JLabel("Beneficiary 1 ID No:")); form.add(id1);
        form.add(new JLabel("Beneficiary 1 Relationship:")); form.add(rel1);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSave = new JButton("Save");
        JButton btnBack = new JButton("Back");
        buttons.add(btnSave); buttons.add(btnBack);
        add(new JScrollPane(form), BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        btnBack.addActionListener(e -> { new AdminPage("ADMIN").setVisible(true); dispose(); });
        btnSave.addActionListener(e -> saveMember());
    }

    private void saveMember() {
        String gender = rMale.isSelected() ? "Male" : rFemale.isSelected() ? "Female" : "";
        String standSize = s25.isSelected() ? "25x25" : s50.isSelected() ? "25x50" : "";

        if (lastName.getText().trim().isEmpty() || firstName.getText().trim().isEmpty() || idNo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please complete required fields.", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "INSERT INTO members (Doc_No, Ref_No, Allocation_Date, Last_Name, First_Name, Identity_No, Gender, P_Address, C_Details, M_Status, Kgoro, Village, Section, Extension, Stand_No, Stand_Size, F_Name1, ID_No1, Relationship1) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConnectionProvider.getCon();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, docNo.getText().trim());
            pst.setString(2, refNo.getText().trim());
            pst.setString(3, allocDate.getText().trim());
            pst.setString(4, lastName.getText().trim());
            pst.setString(5, firstName.getText().trim());
            pst.setString(6, idNo.getText().trim());
            pst.setString(7, gender);
            pst.setString(8, pAddress.getText().trim());
            pst.setString(9, cDetails.getText().trim());
            pst.setString(10, (String)mStatus.getSelectedItem());
            pst.setString(11, kgoro.getText().trim());
            pst.setString(12, (String)village.getSelectedItem());
            pst.setString(13, (String)section.getSelectedItem());
            pst.setString(14, (String)extension.getSelectedItem());
            pst.setString(15, standNo.getText().trim());
            pst.setString(16, standSize);

            pst.setString(17, f1.getText().trim());
            pst.setString(18, id1.getText().trim());
            pst.setString(19, (String)rel1.getSelectedItem());

            int rows = pst.executeUpdate();
            JOptionPane.showMessageDialog(this, (rows>0) ? "Saved" : "No rows inserted.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
