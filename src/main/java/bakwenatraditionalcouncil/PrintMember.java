package bakwenatraditionalcouncil;

import javax.swing.*;
import java.awt.*;
import java.awt.print.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PrintMember extends JFrame {
    private final JTextField txtMemberId = new JTextField(12);
    private final JButton btnSearch = new JButton("SEARCH");
    private final JButton btnPrint = new JButton("PRINT");

    private final JPanel panel = new JPanel(new GridLayout(0,2,8,8));

    public PrintMember() {
        setTitle("Print");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1100, 900);
        setLocationRelativeTo(null);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("ENTER MEMBER ID:")); top.add(txtMemberId); top.add(btnSearch); top.add(btnPrint);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(panel), BorderLayout.CENTER);

        btnSearch.addActionListener(e -> onSearch());
        btnPrint.addActionListener(e -> onPrint());
    }

    private void onSearch() {
        String memberID = txtMemberId.getText().trim();
        if (memberID.isEmpty()) { JOptionPane.showMessageDialog(this, "Enter Member ID"); return; }

        String sql = "SELECT * FROM members WHERE Member_id = ?";
        try (Connection con = ConnectionProvider.getCon();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, memberID);
            try (ResultSet rs = pst.executeQuery()) {
                panel.removeAll();
                if (rs.next()) {
                    addLabelValue("DOCUMENT NO:", rs.getString("Doc_No"));
                    addLabelValue("REFERENCE NO:", rs.getString("Ref_No"));
                    addLabelValue("ALLOCATION DATE:", rs.getString("Allocation_Date"));
                    addLabelValue("LAST NAME:", rs.getString("Last_Name"));
                    addLabelValue("FIRST NAME:", rs.getString("First_Name"));
                    addLabelValue("IDENTITY NUMBER:", rs.getString("Identity_No"));
                    addLabelValue("GENDER:", rs.getString("Gender"));
                    addLabelValue("PHYSICAL ADDRESS:", rs.getString("P_Address"));
                    addLabelValue("CONTACT DETAILS:", rs.getString("C_Details"));
                    addLabelValue("KGORO:", rs.getString("Kgoro"));
                    addLabelValue("VILLAGE:", rs.getString("Village"));
                    addLabelValue("SECTION:", rs.getString("Section"));
                    addLabelValue("EXTENSION:", rs.getString("Extension"));
                    addLabelValue("STAND NO:", rs.getString("Stand_No"));
                    addLabelValue("STAND SIZE:", rs.getString("Stand_Size"));

                    addLabelValue("BENEFICIARY 1:", rs.getString("F_Name1") + " | " + rs.getString("ID_No1") + " | " + rs.getString("Relationship1"));
                    addLabelValue("BENEFICIARY 2:", rs.getString("F_Name2") + " | " + rs.getString("ID_No2") + " | " + rs.getString("Relationship2"));
                    addLabelValue("BENEFICIARY 3:", rs.getString("F_Name3") + " | " + rs.getString("ID_No3") + " | " + rs.getString("Relationship3"));
                    addLabelValue("BENEFICIARY 4:", rs.getString("F_Name4") + " | " + rs.getString("ID_No4") + " | " + rs.getString("Relationship4"));
                    addLabelValue("BENEFICIARY 5:", rs.getString("F_Name5") + " | " + rs.getString("ID_No5") + " | " + rs.getString("Relationship5"));

                    panel.revalidate(); panel.repaint();
                } else {
                    JOptionPane.showMessageDialog(this, "Member ID does not exist");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addLabelValue(String label, String value) {
        panel.add(new JLabel(label));
        panel.add(new JLabel(value == null ? "" : value));
    }

    private void onPrint() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Print Data");
        job.setPrintable((graphics, pageFormat, pageNum) -> {
            if (pageNum > 0) return Printable.NO_SUCH_PAGE;
            Graphics2D g2 = (Graphics2D) graphics;
            g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            double sx = pageFormat.getImageableWidth() / this.getWidth();
            double sy = pageFormat.getImageableHeight() / this.getHeight();
            double scale = Math.min(sx, sy);
            g2.scale(scale, scale);
            this.printAll(g2);
            return Printable.PAGE_EXISTS;
        });

        if (job.printDialog()) {
            try { job.print(); } catch (PrinterException e) { JOptionPane.showMessageDialog(this, e.getMessage(), "Print Error", JOptionPane.ERROR_MESSAGE); }
        }
    }
}
