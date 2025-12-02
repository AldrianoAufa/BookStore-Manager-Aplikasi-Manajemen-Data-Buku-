package bookstore.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * Main Menu Dashboard
 */
public class MainMenu extends javax.swing.JFrame {

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
        setLocationRelativeTo(null); // Center the form
        getContentPane().setBackground(new java.awt.Color(51, 153, 255)); // Blue Background
        loadDashboardData();
    }

    private void loadDashboardData() {
        try {
            Connection c = Koneksi.configDB();
            Statement s = c.createStatement();
            
            // Total Buku
            ResultSet r = s.executeQuery("SELECT COUNT(*) FROM buku");
            if (r.next()) {
                lblTotalBuku.setText(r.getString(1));
            }
            r.close();
            
            // Total Aset
            r = s.executeQuery("SELECT SUM(harga) FROM buku");
            if (r.next()) {
                String total = r.getString(1);
                lblTotalAset.setText(total == null ? "0" : "Rp " + total);
            }
            r.close();
            s.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat data dashboard: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnDataBuku = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        panelBuku = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblTotalBuku = new javax.swing.JLabel();
        panelAset = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblTotalAset = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main Menu - BookStore Manager");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BOOKSTORE MANAGER");

        jLabel1.setText("BOOKSTORE MANAGER");

        panelBuku.setBackground(new java.awt.Color(255, 255, 255));
        panelBuku.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 153, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Total Buku");

        lblTotalBuku.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTotalBuku.setForeground(new java.awt.Color(51, 153, 255));
        lblTotalBuku.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalBuku.setText("0");

        javax.swing.GroupLayout panelBukuLayout = new javax.swing.GroupLayout(panelBuku);
        panelBuku.setLayout(panelBukuLayout);
        panelBukuLayout.setHorizontalGroup(
            panelBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBukuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(lblTotalBuku, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBukuLayout.setVerticalGroup(
            panelBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBukuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalBuku, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelAset.setBackground(new java.awt.Color(255, 255, 255));
        panelAset.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 153, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Total Nilai Aset");

        lblTotalAset.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotalAset.setForeground(new java.awt.Color(51, 153, 255));
        lblTotalAset.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalAset.setText("Rp 0");

        javax.swing.GroupLayout panelAsetLayout = new javax.swing.GroupLayout(panelAset);
        panelAset.setLayout(panelAsetLayout);
        panelAsetLayout.setHorizontalGroup(
            panelAsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAsetLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(lblTotalAset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelAsetLayout.setVerticalGroup(
            panelAsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAsetLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalAset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnDataBuku.setBackground(new java.awt.Color(255, 255, 255));
        btnDataBuku.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDataBuku.setForeground(new java.awt.Color(51, 153, 255));
        btnDataBuku.setText("Kelola Data Buku");
        btnDataBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataBukuActionPerformed(evt);
            }
        });

        btnLogout.setBackground(new java.awt.Color(255, 102, 102));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelAset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnDataBuku, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBuku, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelAset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(btnDataBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addGap(20, 20, 20))
        );

        pack();
    }

    private void btnDataBukuActionPerformed(java.awt.event.ActionEvent evt) {
        new BookForm().setVisible(true);
        this.dispose();
    }

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        new LoginForm().setVisible(true);
        this.dispose();
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnDataBuku;
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblTotalAset;
    private javax.swing.JLabel lblTotalBuku;
    private javax.swing.JPanel panelAset;
    private javax.swing.JPanel panelBuku;
    // End of variables declaration
}
