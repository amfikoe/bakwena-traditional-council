package bakwenatraditionalcouncil;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddMember extends JFrame {
    private final JTextField txtDocNo = new JTextField();
    private final JTextField txtRefNo = new JTextField();
    private final JTextField txtAllocDate = new JTextField();
    private final JTextField txtLastName = new JTextField();
    private final JTextField txtFirstName = new JTextField();
    private final JTextField txtIdNumber = new JTextField();
    private final JRadioButton rMale = new JRadioButton("MALE");
    private final JRadioButton rFemale = new JRadioButton("FEMALE");
    private final JTextField txtPAddress = new JTextField();
    private final JTextField txtCDetails = new JTextField();
    private final JComboBox<String> cmbMStatus = new JComboBox<>(new String[]{"SELECT STATUS","SINGLE","MARRIED","WIDOW","WIDOWER","LIVING WITH PARTNER"});
    private final JTextField txtKgoro = new JTextField();
    private final JComboBox<String> cmbVillage = new JComboBox<>(new String[]{"SELECT VILLAGE","HEBRON","KGABALATSANE","RABOKALA"});
    private final JComboBox<String> cmbSection = new JComboBox<>(new String[]{"SELECT SECTION","MOTSENG","MOSEJA","MATSHELAPATA","MIDWAY","MIDAS","BANGLADESH","BLOCK E","MERITING","MAGASENG","GREENSIDE","MOROLONG","MMOTONG","MICHA VIEW","AIRPORT","NKANDLA","NKANDLA VIEW","LETSHENG"});
    private final JComboBox<String> cmbExtension = new JComboBox<>(new String[]{"SELECT EXTENSION","EXTENSION 1","EXTENSION 2","EXTENSION 3","EXTENSION 4"});
    private final JTextField txtStandNo = new JTextField();
    private final JRadioButton r25x25 = new JRadioButton("25m x 25m");
    private final JRadioButton r25x50 = new JRadioButton("25m x 50m");

    // beneficiaries (5)
    private final JTextField txtName1 = new JTextField();
    private final JTextField txtId1 = new JTextField();
    private final JComboBox<String> cmbRel1 = new JComboBox<>(new String[]{"SELECT RELATIONSHIP","SON","DAUGHTER","PARENT","SIBLING","COUSIN","FAMILY MEMBER","FRIEND"});

    private final JTextField txtName2 = new JTextField();
    private final JTextField txtId2 = new JTextField();
    private final JComboBox<String> cmbRel2 = new JComboBox<>(new String[]{"SELECT RELATIONSHIP","SON","DAUGHTER","PARENT","SIBLING","COUSIN","FAMILY MEMBER","FRIEND"});

    private final JTextField txtName3 = new JTextField();
    private final JTextField txtId3 = new JTextField();
    private final JComboBox<String> cmbRel3 = new JComboBox<>(new String[]{"SELECT RELATIONSHIP","SON","DAUGHTER","PARENT","SIBLING","COUSIN","FAMILY MEMBER","FRIEND"});

    private final JTextField txtName4 = new JTextField();
    private final JTextField txtId4 = new JTextField();
    private final JComboBox<String> cmbRel4 = new JComboBox<>(new String[]{"SELECT RELATIONSHIP","SON","DAUGHTER","PARENT","SIBLING","COUSIN","FAMILY MEMBER","FRIEND"});

    private final JTextField txtName5 = new JTextField();
    private final JTextField txtId5 = new JTextField();
    private final JComboBox<String> cmbRel5 = new JComboBox<>(new String[]{"SELECT RELATIONSHIP","SON","DAUGHTER","PARENT","SIBLING","COUSIN","FAMILY MEMBER","FRIEND"});

    private final JButton btnSave = new JButton("SAVE");
    private final JButton btnBack = new JButton("BACK");

    public AddMember() {
        setTitle("Add Member");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 900);
        setLocationRelativeTo(null);

        JPanel header = new JPanel(new BorderLayout());
        JLabel title = new JLabel("PROOF OF RESIDENTIAL STAND ALLOCATION", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.add(title, BorderLayout.CENTER);

        JPanel content = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;

        int row = 0;
        addLabelField(content, c, row++, "DOCUMENT NUMBER:", txtDocNo);
        addLabelField(content, c, row++, "REFERENCE NUMBER:", txtRefNo);
        addLabelField(content, c, row++, "ALLOCATION DATE:", txtAllocDate);
        addLabelField(content, c, row++, "LAST NAME:", txtLastName);
        addLabelField(content, c, row++, "FIRST NAME:", txtFirstName);
        addLabelField(content, c, row++, "IDENTITY NUMBER:", txtIdNumber);

        // gender
        c.gridx = 0; c.gridy = row; c.gridwidth = 1; content.add(new JLabel("GENDER:"), c);
        JPanel pGender = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup gGroup = new ButtonGroup(); gGroup.add(rMale); gGroup.add(rFemale);
        pGender.add(rMale); pGender.add(rFemale);
        c.gridx = 1; c.gridy = row++; c.gridwidth = 3; content.add(pGender, c);

        addLabelField(content, c, row++, "PHYSICAL ADDRESS:", txtPAddress);
        addLabelField(content, c, row++, "CONTACT DETAILS:", txtCDetails);
        addLabelField(content, c, row++, "MARITAL STATUS:", cmbMStatus);

        addLabelField(content, c, row++, "KGORO:", txtKgoro);
        addLabelField(content, c, row++, "VILLAGE:", cmbVillage);
        addLabelField(content, c, row++, "SECTION:", cmbSection);
        addLabelField(content, c, row++, "EXTENSION:", cmbExtension);
        addLabelField(content, c, row++, "STAND NUMBER:", txtStandNo);

        // stand size
        c.gridx = 0; c.gridy = row; c.gridwidth = 1; content.add(new JLabel("STAND SIZE:"), c);
        JPanel pSize = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup sGroup = new ButtonGroup(); sGroup.add(r25x25); sGroup.add(r25x50);
        pSize.add(r25x25); pSize.add(r25x50);
        c.gridx = 1; c.gridy = row++; c.gridwidth = 3; content.add(pSize, c);

        // beneficiaries header
        c.gridx = 0; c.gridy = row++; c.gridwidth = 4; content.add(new JLabel("BENEFICIARIES"), c);

        addLabelField(content, c, row++, "FULL NAME:", txtName1); addLabelField(content, c, row-1, "ID NUMBER:", txtId1); addComponent(content, c, row-1, 2, cmbRel1);
        addLabelField(content, c, row++, "FULL NAME:", txtName2); addLabelField(content, c, row-1, "ID NUMBER:", txtId2); addComponent(content, c, row-1, 2, cmbRel2);
        addLabelField(content, c, row++, "FULL NAME:", txtName3); addLabelField(content, c, row-1, "ID NUMBER:", txtId3); addComponent(content, c, row-1, 2, cmbRel3);
        addLabelField(content, c, row++, "FULL NAME:", txtName4); addLabelField(content, c, row-1, "ID NUMBER:", txtId4); addComponent(content, c, row-1, 2, cmbRel4);
        addLabelField(content, c, row++, "FULL NAME:", txtName5); addLabelField(content, c, row-1, "ID NUMBER:", txtId5); addComponent(content, c, row-1, 2, cmbRel5);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(btnSave); buttons.add(btnBack);
        btnSave.addActionListener(e -> onSave());
        btnBack.addActionListener(e -> dispose());

        JScrollPane scroll = new JScrollPane(content);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(header, BorderLayout.NORTH);
        getContentPane().add(scroll, BorderLayout.CENTER);
        getContentPane().add(buttons, BorderLayout.SOUTH);
    }

    private void addLabelField(JPanel p, GridBagConstraints c, int row, String label, Component field) {
        c.gridx = 0; c.gridy = row; c.gridwidth = 1; p.add(new JLabel(label), c);
        c.gridx = 1; c.gridy = row; c.gridwidth = 3; p.add(field, c);
    }

    private void addLabelField(JPanel p, GridBagConstraints c, int row, String label, JComboBox<?> combo) {
        addLabelField(p, c, row, label, (Component)combo);
    }

    private void addComponent(JPanel p, GridBagConstraints c, int row, int col, Component comp) {
        c.gridx = col; c.gridy = row; c.gridwidth = 1; p.add(comp, c);
    }

    private void onSave() {
        String docno = txtDocNo.getText().trim();
        String refno = txtRefNo.getText().trim();
        String allocdate = txtAllocDate.getText().trim();
        String lname = txtLastName.getText().trim();
        String fname = txtFirstName.getText().trim();
        String id = txtIdNumber.getText().trim();
        String gender = rMale.isSelected() ? "MALE" : rFemale.isSelected() ? "FEMALE" : "";
        String paddress = txtPAddress.getText().trim();
        String cdetails = txtCDetails.getText().trim();
        String mstatus = (String) cmbMStatus.getSelectedItem();
        String kgoro = txtKgoro.getText().trim();
        String village = (String) cmbVillage.getSelectedItem();
        String section = (String) cmbSection.getSelectedItem();
        String extension = (String) cmbExtension.getSelectedItem();
        String standno = txtStandNo.getText().trim();
        String standsize = r25x25.isSelected() ? "25m x 25m" : r25x50.isSelected() ? "25m x 50m" : "";

        String name1 = txtName1.getText().trim(); String idno1 = txtId1.getText().trim(); String rln1 = (String)cmbRel1.getSelectedItem();
        String name2 = txtName2.getText().trim(); String idno2 = txtId2.getText().trim(); String rln2 = (String)cmbRel2.getSelectedItem();
        String name3 = txtName3.getText().trim(); String idno3 = txtId3.getText().trim(); String rln3 = (String)cmbRel3.getSelectedItem();
        String name4 = txtName4.getText().trim(); String idno4 = txtId4.getText().trim(); String rln4 = (String)cmbRel4.getSelectedItem();
        String name5 = txtName5.getText().trim(); String idno5 = txtId5.getText().trim(); String rln5 = (String)cmbRel5.getSelectedItem();

        if (lname.isEmpty() || fname.isEmpty() || id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill required fields (last, first, id).", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "INSERT INTO members(Doc_No, Ref_No, Allocation_Date, Last_Name, First_Name, Identity_No, Gender, P_Address, C_Details, M_Status, Kgoro, Village, Section, Extension, Stand_No, Stand_Size, F_Name1, ID_No1, Relationship1, F_Name2, ID_No2, Relationship2, F_Name3, ID_No3, Relationship3, F_Name4, ID_No4, Relationship4, F_Name5, ID_No5, Relationship5) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection con = ConnectionProvider.getCon();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, docno);
            pst.setString(2, refno);
            pst.setString(3, allocdate);
            pst.setString(4, lname);
            pst.setString(5, fname);
            pst.setString(6, id);
            pst.setString(7, gender);
            pst.setString(8, paddress);
            pst.setString(9, cdetails);
            pst.setString(10, mstatus);
            pst.setString(11, kgoro);
            pst.setString(12, village);
            pst.setString(13, section);
            pst.setString(14, extension);
            pst.setString(15, standno);
            pst.setString(16, standsize);

            pst.setString(17, name1);
            pst.setString(18, idno1);
            pst.setString(19, rln1);
            pst.setString(20, name2);
            pst.setString(21, idno2);
            pst.setString(22, rln2);
            pst.setString(23, name3);
            pst.setString(24, idno3);
            pst.setString(25, rln3);
            pst.setString(26, name4);
            pst.setString(27, idno4);
            pst.setString(28, rln4);
            pst.setString(29, name5);
            pst.setString(30, idno5);
            pst.setString(31, rln5);

            int rows = pst.executeUpdate();
            if (rows > 0) JOptionPane.showMessageDialog(this, "Data Successfully Captured");
            else JOptionPane.showMessageDialog(this, "No data inserted.", "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
