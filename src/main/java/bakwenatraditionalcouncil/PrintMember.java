package bakwenatraditionalcouncil;

import javax.swing.*;
import java.awt.*;
import java.awt.print.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PrintMember extends JFrame {
    private final JTextField memberId = new JTextField(10);
    private final JPanel contentPanel = new JPanel(new GridLayout(0,2,6,6));

    public PrintMember() {
        setTitle("Print Member");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900,700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Member ID:")); top.add(memberId);
        JButton btnSearch = new JButton("Search");
        JButton btnPrint = new JButton("Print");
        top.add(btnSearch); top.add(btnPrint);
        add(top, BorderLayout.NORTH);

        add(new JScrollPane(contentPanel), BorderLayout.CENTER);

        btnSearch.addActionListener(e -> loadMemberForPrint());
        btnPrint.addActionListener(e -> printComponent());
    }

    private void loadMemberForPrint() {
        contentPanel.removeAll();
        String id = memberId.getText().trim();
        if (id.isEmpty()) { JOptionPane.showMessageDialog(this, "Enter Member ID"); return; }
        String sql = "SELECT * FROM members WHERE Member_id = ?";
        try (Connection con = ConnectionProvider.getCon();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    contentPanel.add(new JLabel("Document No:")); contentPanel.add(new JLabel(rs.getString("Doc_No")));
                    contentPanel.add(new JLabel("Reference No:")); contentPanel.add(new JLabel(rs.getString("Ref_No")));
                    contentPanel.add(new JLabel("Last Name:")); contentPanel.add(new JLabel(rs.getString("Last_Name")));
                    contentPanel.add(new JLabel("First Name:")); contentPanel.add(new JLabel(rs.getString("First_Name")));
                    contentPanel.add(new JLabel("ID No:")); contentPanel.add(new JLabel(rs.getString("Identity_No")));
                    contentPanel.add(new JLabel("Gender:")); contentPanel.add(new JLabel(rs.getString("Gender")));
                    contentPanel.add(new JLabel("Physical Address:")); contentPanel.add(new JLabel(rs.getString("P_Address")));
                    contentPanel.add(new JLabel("Contact Details:")); contentPanel.add(new JLabel(rs.getString("C_Details")));
                    contentPanel.revalidate();
                    contentPanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(this, "Member not found");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void printComponent() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Print Member");
        job.setPrintable((graphics, pageFormat, pageIndex) -> {
            if (pageIndex > 0) return Printable.NO_SUCH_PAGE;
            Graphics2D g2 = (Graphics2D) graphics;
            g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            double scale = Math.min(pageFormat.getImageableWidth() / contentPanel.getWidth(),
                                    pageFormat.getImageableHeight() / contentPanel.getHeight());
            g2.scale(scale, scale);
            contentPanel.printAll(g2);
            return Printable.PAGE_EXISTS;
        });

        if (job.printDialog()) {
            try { job.print(); } catch (PrinterException ex) { JOptionPane.showMessageDialog(this, ex.getMessage()); }
        }
    }
}
