import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import com.github.lgooddatepicker.components.*;
import java.time.LocalDate; // untuk handle date
import java.sql.*;

public class Menu extends JFrame {
    public static void main(String[] args) {
        // buat object window
        Menu menu = new Menu();

        // atur ukuran window
        menu.setSize(600, 600);

        // letakkan window di tengah layar
        menu.setLocationRelativeTo(null);

        // isi window
        menu.setContentPane(menu.mainPanel);

        // ubah warna background
        menu.getContentPane().setBackground(Color.WHITE);

        // tampilkan window
        menu.setVisible(true);

        // agar program ikut berhenti saat window diclose
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // index baris yang diklik
    private int selectedIndex = -1;
    // list untuk menampung semua mahasiswa
    private ArrayList<Mahasiswa> listMahasiswa;
    private Database database;

    private JPanel mainPanel;
    private JTextField nimField;
    private JTextField namaField;
    private JTable mahasiswaTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox jenisKelaminComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel nimLabel;
    private JLabel namaLabel;
    private JLabel jenisKelaminLabel;

    private JLabel tanggalLahirLabel;
    private DatePicker tanggalLahirPicker;

    // constructor
    public Menu() {
        // inisialisasi listMahasiswa
        listMahasiswa = new ArrayList<>();

        database = new Database();

        // isi tabel mahasiswa
        mahasiswaTable.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // atur isi combo box
        String[] jenisKelaminData = { "", "Laki-laki", "Perempuan" };
        jenisKelaminComboBox.setModel(new DefaultComboBoxModel<>(jenisKelaminData));

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });
        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex >= 0) {
                    deleteData();
                }
            }
        });
        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        // saat salah satu baris tabel ditekan
        mahasiswaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = mahasiswaTable.getSelectedRow();

                // simpan value textfield dan combo box
                String curNim = mahasiswaTable.getModel().getValueAt(selectedIndex, 1).toString();
                String curNama = mahasiswaTable.getModel().getValueAt(selectedIndex, 2).toString();
                String curJenisKelamin = mahasiswaTable.getModel().getValueAt(selectedIndex, 3).toString();
                Object tmp = mahasiswaTable.getModel().getValueAt(selectedIndex, 4);
                String curTanggalLahir = (tmp != null) ? tmp.toString() : "";

                // ubah isi textfield dan combo box
                nimField.setText(curNim);
                nimField.setEditable(false); // disable edit on NIM field
                namaField.setText(curNama);
                jenisKelaminComboBox.setSelectedItem(curJenisKelamin);
                if (curTanggalLahir == null || curTanggalLahir.equals("")) {
                    tanggalLahirPicker.clear(); // Clear the DatePicker if
                } else {
                    tanggalLahirPicker.setDate(LocalDate.parse(curTanggalLahir));
                }

                // ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");

                // tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }

    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] cols = { "No", "NIM", "Nama", "Jenis Kelamin", "Tanggal Lahir" };

        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel tmp = new DefaultTableModel(null, cols);

        try {
            ResultSet resultSet = database.selectQuery("SELECT * FROM mahasiswa");
            // isi tabel dengan listMahasiswa
            int i = 0;
            while (resultSet.next()) {
                Object[] row = new Object[cols.length];
                row[0] = i + 1; // nomor nya
                row[1] = resultSet.getString("nim");
                row[2] = resultSet.getString("nama");
                row[3] = resultSet.getString("jenis_kelamin");
                row[4] = resultSet.getString("tanggal_lahir");
                tmp.addRow(row);
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return tmp;
    }

    public void insertData() {
        // ambil value dari textfield dan combobox
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        LocalDate tanggalLahir = tanggalLahirPicker.getDate();

        // input tidak boleh kosong
        if (nim.isEmpty() || nama.isEmpty() || jenisKelamin.isEmpty() || tanggalLahir == null) {
            JOptionPane.showMessageDialog(null,
                    "Semua data harus diisi!\n(NIM, Nama, Jenis Kelamin, dan Tanggal Lahir wajib diisi)",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // cek apakah NIM sudah ada
        try {
            ResultSet resultSet = database.selectQuery("SELECT COUNT(*) FROM mahasiswa WHERE nim = '" + nim + "'");
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null,
                        "NIM sudah terdaftar!\nGunakan NIM yang berbeda",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // tambahkan data ke dalam db
        String sql = "INSERT INTO mahasiswa VALUES (null, '" + nim + "', '" + nama + "', '" + jenisKelamin + "', '"
                + tanggalLahir + "')";
        database.insertUpdateDeleteQuery(sql);

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Insert berhasil");
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
    }

    public void updateData() {
        // ambil data dari form
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        LocalDate tanggalLahir = tanggalLahirPicker.getDate();

        // input tidak boleh kosong
        if (nim.isEmpty() || nama.isEmpty() || jenisKelamin.isEmpty() || tanggalLahir == null) {
            JOptionPane.showMessageDialog(null,
                    "Semua data harus diisi!\n(NIM, Nama, Jenis Kelamin, dan Tanggal Lahir wajib diisi)",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ubah data mahasiswa di db
        String sql = "UPDATE mahasiswa SET " +
                "nama = '" + nama + "', " +
                "jenis_kelamin = '" + jenisKelamin + "', " +
                "tanggal_lahir = '" + tanggalLahir + "' " +
                "WHERE nim = '" + nim + "'";
        database.insertUpdateDeleteQuery(sql);

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Update berhasil");
        JOptionPane.showMessageDialog(null, "Data berhasil diubah");
    }

    public void deleteData() {
        // hapus data dari db
        String nim = nimField.getText(); // Ambil data NIM dari form atau tabel yang dipilih

        // Konfirmasi sebelum menghapus
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus data ini?",
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        // Delete data from database
        String sql = "DELETE FROM mahasiswa WHERE nim = '" + nim + "'";
        database.insertUpdateDeleteQuery(sql);

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Delete berhasil");
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
    }

    public void clearForm() {
        // kosongkan semua texfield dan combo box
        nimField.setText("");
        namaField.setText("");
        nimField.setEditable(true); // enable again
        jenisKelaminComboBox.setSelectedIndex(0);
        tanggalLahirPicker.clear();

        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;

    }
}