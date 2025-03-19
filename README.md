# Janji
Saya Zakiyah Hasanah dengan NIM 2305274 mengerjakan Tugas Praktikum 4 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

# Desain Program
Program ini adalah aplikasi sederhana untuk mengelola data mahasiswa menggunakan Java Swing. Aplikasi ini memungkinkan pengguna untuk menambah, mengupdate, dan menghapus data mahasiswa. Data mahasiswa yang disimpan meliputi NIM, nama, jenis kelamin, dan __tanggal lahir__. Program ini menggunakan GUI (Graphical User Interface) untuk memudahkan interaksi pengguna.

### Penjelasan Class
* Mahasiswa.java - Kelas ini merepresentasikan entitas mahasiswa dengan atribut NIM, nama, jenis kelamin, dan tanggal lahir. Kelas ini juga menyediakan getter dan setter untuk setiap atribut.
* Menu.java - Kelas ini merupakan *interface* utama aplikasi. Ini berisi komponen GUI seperti text field, combo box, tabel, dan button untuk mengelola data mahasiswa. Program ini juga menggunakan library [`LGoodDatePicker`](https://github.com/LGoodDatePicker/LGoodDatePicker) untuk memilih tanggal lahir.

### Fitur Program
1. Tambah Data - Pengguna dapat menambahkan data mahasiswa baru dengan mengisi form dan menekan tombol "Add".
2. Update Data - Pengguna dapat mengupdate data mahasiswa yang sudah ada dengan memilih baris di tabel, mengubah data di form, dan menekan tombol "Update".
3. Hapus Data - Pengguna dapat menghapus data mahasiswa dengan memilih baris di tabel dan menekan tombol "Delete". Sebelum menghapus, program akan menampilkan konfirmasi untuk memastikan pengguna benar-benar ingin menghapus data.
4. Clear Form - Pengguna dapat membersihkan form dengan menekan tombol "Cancel".

# Penjelasan Alur
1. Inisialisasi Program - Saat program dijalankan, window utama akan muncul dengan tabel yang berisi data mahasiswa dan form untuk menginput data.
2. Menambah Data
    - Pengguna mengisi form dengan NIM, nama, jenis kelamin, dan tanggal lahir.
    - Setelah form diisi, pengguna menekan tombol "Add" untuk menambahkan data ke dalam tabel.
3. Mengupdate Data
    - Pengguna memilih baris di tabel yang ingin diupdate.
    - Data dari baris yang dipilih akan muncul di form.
    - Pengguna mengubah data yang diperlukan dan menekan tombol "Update" untuk menyimpan perubahan.
4. Menghapus Data
    - Pengguna memilih baris di tabel yang ingin dihapus.
    - Sebelum menghapus, program akan menampilkan dialog konfirmasi.
    - Jika pengguna menekan "Yes", data akan dihapus dari tabel.
5. Membersihkan Form
    - Pengguna dapat membersihkan form dengan menekan tombol "Cancel". Ini akan mengosongkan semua field dan mengembalikan tombol "Update" ke "Add".

# Dokumentasi
![dokum](Screenshots/dpbo_tp4_demo.gif)
