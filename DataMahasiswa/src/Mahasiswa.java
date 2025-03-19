import java.time.LocalDate;

public class Mahasiswa {
    private String nim;
    private String nama;
    private String jenisKelamin;
    private LocalDate tanggalLahir; // Tambahkan atribut Tanggal Lahir

    public Mahasiswa(String nim, String nama, String jenisKelamin) {
        this.nim = nim;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.tanggalLahir = null;
    }
    public Mahasiswa(String nim, String nama, String jenisKelamin, LocalDate tanggalLahir) {
        this.nim = nim;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.tanggalLahir = tanggalLahir;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public void setTanggalLahir(LocalDate tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getNim() {
        return this.nim;
    }

    public String getNama() {
        return this.nama;
    }

    public String getJenisKelamin() {
        return this.jenisKelamin;
    }

    public LocalDate getTanggalLahir() {
        return this.tanggalLahir;
    }
}