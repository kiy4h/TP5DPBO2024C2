# Janji
Saya Zakiyah Hasanah dengan NIM 2305274 mengerjakan Tugas Praktikum 5 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan.

# Desain Program
Program ini adalah aplikasi sederhana untuk mengelola data mahasiswa menggunakan Java Swing GUI dan database MySQL untuk penyimpanan data. Aplikasi ini memungkinkan pengguna untuk menambah, mengupdate, dan menghapus data mahasiswa dengan validasi input. Data mahasiswa yang disimpan meliputi:
- NIM (harus diisi, harus unik)
- Nama (harus diisi)
- Jenis kelamin (harus dipilih)
- Tanggal lahir (harus diisi)

### Fitur Program dengan Validasi
1. **Tambah Data** 
   - Semua field harus diisi
   - NIM harus unik (tidak boleh duplikat)
   - Jenis kelamin harus dipilih (tidak boleh kosong)
   - Tanggal lahir harus dipilih

2. **Update Data** 
   - Semua field harus diisi
   - Validasi sama seperti tambah data (kecuali pengecekan unik NIM, karena field NIM dikunci)

3. **Hapus Data** 
   - Konfirmasi dialog sebelum menghapus
   - Hanya bisa menghapus data yang dipilih di tabel

4. **Clear Form** 
   - Mengosongkan semua field
   - Mereset DatePicker
   - Mematikan tombol "Update" dan "Delete", serta menyalakan tombol "Add"

### Penjelasan Class
* **Mahasiswa.java** - Kelas ini merepresentasikan entitas mahasiswa dengan atribut NIM, nama, jenis kelamin, dan tanggal lahir. Kelas ini menyediakan getter dan setter untuk setiap atribut.
* **Menu.java** - Kelas antarmuka utama yang berisi komponen GUI dan logika aplikasi. Menggunakan:
  - Text field untuk NIM dan nama
  - Combo box untuk jenis kelamin (opsi: "", "Laki-laki", "Perempuan")
  - DatePicker untuk memilih tanggal lahir (harus diisi)
  - Tabel untuk menampilkan data
  - Tombol untuk Add/Update, Delete, dan Cancel
* **Database.java** - Kelas yang menangani koneksi dan operasi database MySQL.


# Penjelasan Alur
### 1. **Inisialisasi Program** 
```mermaid
%%{init: {'themeVariables': {'fontSize': '10px'}}%%
graph TD
    A[Start] --> B[Koneksi Database] --> C[Load Data ke Tabel] --> D[Tampilkan Form Kosong]
```

### 2. **Menambah Data**
```mermaid
    graph TD
        A[Isi Form] --> B{Validasi}
        B -->|Valid| C[Insert ke Database]
        B -->|Tidak Valid| D[Tampilkan Pesan Error]
        C --> E[Refresh Tabel]
        E --> F[Clear Form]
```

### 3. **Mengupdate Data**
```mermaid
    graph TD
        A[Pilih Data di Tabel] --> B[Load ke Form]
        B --> C[User Edit Data]
        C --> D{Validasi}
        D -->|Valid| E[Update Database]
        D -->|Invalid| F[Tampilkan Error]
        E --> G[Refresh Tabel]
        G --> H[Clear Form]
```

### 4. **Menghapus Data**
```mermaid
    graph TD
        A[Pilih Data] --> B{Konfirmasi}
        B -->|Ya| C[Delete dari Database]
        B -->|Tidak| D[Batal]
        C --> E[Refresh Tabel]
        E --> F[Clear Form]
        F --> G[Notifikasi berhasil dihapus]
```

### 5. **Validasi Input**
```mermaid
graph LR
    A[NIM Kosong] --> E[Error]
    F[NIM Duplikat] --> E
    B[Nama Kosong] --> E
    C[Jenis Kelamin Kosong] --> E
    D[Tanggal Lahir Kosong] --> E
```

# Dokumentasi
![dokum](Screenshots/dpbo_tp5_demo.gif)
