package bookstore.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.FileWriter;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 * Book Management Form (CRUD)
 */
public class BookForm extends javax.swing.JFrame {

    private DefaultTableModel model;

    /**
     * Creates new form BookForm
     */
    public BookForm() {
        initComponents();
        setLocationRelativeTo(null);
        getContentPane().setBackground(new java.awt.Color(51, 153, 255)); // Blue Background
        
        // Initialize Table Model
        model = new DefaultTableModel();
        tableBuku.setModel(model);
        model.addColumn("ID");
        model.addColumn("Judul");
        model.addColumn("Penulis");
        model.addColumn("Penerbit");
        model.addColumn("Harga");
        model.addColumn("Tahun");
        
        loadData();
    }
    
    private void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        try {
            Connection c = Koneksi.configDB();
            Statement s = c.createStatement();
            
            // Base SQL
            String sql = "SELECT * FROM buku WHERE 1=1";
            
            // Filter Logic
            if (cmbFilter != null && cmbFilter.getSelectedItem() != null) {
                String filter = cmbFilter.getSelectedItem().toString();
                if (filter.equals("< 50.000")) {
                    sql += " AND harga < 50000";
                } else if (filter.equals("50.000 - 100.000")) {
                    sql += " AND harga BETWEEN 50000 AND 100000";
                } else if (filter.equals("> 100.000")) {
                    sql += " AND harga > 100000";
                }
            }
            
            // Search Logic (if search text exists)
            if (txtCari != null && !txtCari.getText().isEmpty()) {
                sql += " AND (judul LIKE '%" + txtCari.getText() + "%' OR penulis LIKE '%" + txtCari.getText() + "%')";
            }
            
            // Sort Logic
            if (cmbSort != null && cmbSort.getSelectedItem() != null) {
                String sort = cmbSort.getSelectedItem().toString();
                if (sort.equals("Harga Termurah")) {
                    sql += " ORDER BY harga ASC";
                } else if (sort.equals("Harga Termahal")) {
                    sql += " ORDER BY harga DESC";
                } else if (sort.equals("Tahun Terbaru")) {
                    sql += " ORDER BY tahun DESC";
                } else if (sort.equals("Tahun Terlama")) {
                    sql += " ORDER BY tahun ASC";
                }
            }
            
            ResultSet r = s.executeQuery(sql);
            
            while (r.next()) {
                Object[] o = new Object[6];
                o[0] = r.getString("id");
                o[1] = r.getString("judul");
                o[2] = r.getString("penulis");
                o[3] = r.getString("penerbit");
                o[4] = r.getString("harga");
                o[5] = r.getString("tahun");
                
                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat data: " + e.getMessage());
        }
    }
    
    private void clearForm() {
        txtId.setText("");
        txtJudul.setText("");
        txtPenulis.setText("");
        txtPenerbit.setText("");
        txtHarga.setText("");
        txtTahun.setText("");
        txtJudul.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtJudul = new javax.swing.JTextField();
        txtPenulis = new javax.swing.JTextField();
        txtPenerbit = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        txtTahun = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBuku = new javax.swing.JTable();
        btnSimpan = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        cmbSort = new javax.swing.JComboBox<>();
        cmbFilter = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kelola Data Buku - BookStore Manager");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DATA BUKU");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Judul Buku");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Penulis");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Penerbit");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Harga");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tahun Terbit");

        txtId.setEditable(false);
        txtId.setVisible(false); // Hide ID field

        tableBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBukuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableBuku);

        btnSimpan.setBackground(new java.awt.Color(102, 255, 102));
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(255, 255, 102));
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnHapus.setBackground(new java.awt.Color(255, 102, 102));
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnKembali.setBackground(new java.awt.Color(255, 255, 255));
        btnKembali.setForeground(new java.awt.Color(51, 153, 255));
        btnKembali.setText("Kembali ke Menu");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Cari Buku");

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        btnExport.setBackground(new java.awt.Color(204, 204, 255));
        btnExport.setText("Export CSV");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        cmbSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Urutkan...", "Harga Termurah", "Harga Termahal", "Tahun Terbaru", "Tahun Terlama" }));
        cmbSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSortActionPerformed(evt);
            }
        });

        cmbFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua Harga", "< 50.000", "50.000 - 100.000", "> 100.000" }));
        cmbFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFilterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnKembali))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtJudul)
                            .addComponent(txtPenulis)
                            .addComponent(txtPenerbit)
                            .addComponent(txtHarga)
                            .addComponent(txtTahun, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(txtCari)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCari)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnExport)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbSort, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnSimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReset)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnKembali))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari)
                    .addComponent(btnExport)
                    .addComponent(cmbSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtPenulis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnEdit)
                    .addComponent(btnHapus)
                    .addComponent(btnReset))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {
        // Validasi Input
        if (txtJudul.getText().isEmpty() || txtPenulis.getText().isEmpty() || 
            txtPenerbit.getText().isEmpty() || txtHarga.getText().isEmpty() || 
            txtTahun.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua data harus diisi!");
            return;
        }
        
        // Validasi Angka
        try {
            Integer.parseInt(txtHarga.getText());
            Integer.parseInt(txtTahun.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Harga dan Tahun harus berupa angka!");
            return;
        }

        try {
            String sql = "INSERT INTO buku (judul, penulis, penerbit, harga, tahun) VALUES ('"
                    + txtJudul.getText() + "','" + txtPenulis.getText() + "','"
                    + txtPenerbit.getText() + "','" + txtHarga.getText() + "','"
                    + txtTahun.getText() + "')";
            Connection c = Koneksi.configDB();
            PreparedStatement p = c.prepareStatement(sql);
            p.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
            loadData();
            clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Simpan: " + e.getMessage());
        }
    }

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Pilih data yang akan diedit dari tabel!");
            return;
        }
        
        try {
            String sql = "UPDATE buku SET judul='" + txtJudul.getText()
                    + "', penulis='" + txtPenulis.getText()
                    + "', penerbit='" + txtPenerbit.getText()
                    + "', harga='" + txtHarga.getText()
                    + "', tahun='" + txtTahun.getText()
                    + "' WHERE id='" + txtId.getText() + "'";
            Connection c = Koneksi.configDB();
            PreparedStatement p = c.prepareStatement(sql);
            p.execute();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            loadData();
            clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Edit: " + e.getMessage());
        }
    }

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Pilih data yang akan dihapus dari tabel!");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(null, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM buku WHERE id='" + txtId.getText() + "'";
                Connection c = Koneksi.configDB();
                PreparedStatement p = c.prepareStatement(sql);
                p.execute();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                loadData();
                clearForm();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal Hapus: " + e.getMessage());
            }
        }
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
        clearForm();
    }

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {
        new MainMenu().setVisible(true);
        this.dispose();
    }

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {
        loadData();
    }

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Simpan File CSV");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            // Ensure extension is .csv
            if (!fileToSave.getAbsolutePath().endsWith(".csv")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".csv");
            }
            
            try {
                FileWriter fw = new FileWriter(fileToSave);
                // Write Header
                for (int i = 0; i < model.getColumnCount(); i++) {
                    fw.write(model.getColumnName(i) + ",");
                }
                fw.write("\n");
                
                // Write Data
                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        fw.write(model.getValueAt(i, j).toString() + ",");
                    }
                    fw.write("\n");
                }
                
                fw.close();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diexport ke " + fileToSave.getAbsolutePath());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal Export: " + e.getMessage());
            }
        }
    }

    private void cmbSortActionPerformed(java.awt.event.ActionEvent evt) {
        loadData();
    }

    private void cmbFilterActionPerformed(java.awt.event.ActionEvent evt) {
        loadData();
    }

    private void tableBukuMouseClicked(java.awt.event.MouseEvent evt) {
        int baris = tableBuku.rowAtPoint(evt.getPoint());
        String id = tableBuku.getValueAt(baris, 0).toString();
        txtId.setText(id);
        String judul = tableBuku.getValueAt(baris, 1).toString();
        txtJudul.setText(judul);
        String penulis = tableBuku.getValueAt(baris, 2).toString();
        txtPenulis.setText(penulis);
        String penerbit = tableBuku.getValueAt(baris, 3).toString();
        txtPenerbit.setText(penerbit);
        String harga = tableBuku.getValueAt(baris, 4).toString();
        txtHarga.setText(harga);
        String tahun = tableBuku.getValueAt(baris, 5).toString();
        txtTahun.setText(tahun);
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableBuku;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtJudul;
    private javax.swing.JTextField txtPenerbit;
    private javax.swing.JTextField txtPenulis;
    private javax.swing.JTextField txtTahun;
    private javax.swing.JComboBox<String> cmbSort;
    private javax.swing.JComboBox<String> cmbFilter;
    // End of variables declaration
}
