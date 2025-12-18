import java.util.Scanner;

// Menyimpan satu data pendaftar magang
class Pendaftar {
    String nama, nim, prodi, perusahaan, status;
    int semester;

    public Pendaftar(String nama, String nim, String prodi, String perusahaan, int semester, String status){
        this.nama = nama;
        this.nim = nim;
        this.prodi = prodi;
        this.perusahaan = perusahaan;
        this.semester = semester;
        this.status = status;
    }
}

public class CM2Magang26 {
    static Scanner sc = new Scanner(System.in);

    // Gunakan array biasa (maksimal 100 data)
    static Pendaftar[] data = new Pendaftar[100];
    static int jumlahData = 0;

    public static void main(String[] args) {
        int menu;

        do {
            System.out.println("\n=== Sistem Pendaftaran Magang Mahasiswa ===");
            System.out.println(" 1. Tambah Data Magang");
            System.out.println(" 2. Tampilkan Semua Pendaftar Magang");
            System.out.println(" 3. Cari Pendaftar Berdasarkan Program Studi");
            System.out.println(" 4. Hitung Jumlah Pendaftar untuk Setiap Status");
            System.out.println(" 5. Keluar");
            System.out.print("Pilih menu (1-5): ");

            menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {
                case 1: tambahData(); break;
                case 2: tampilData(); break;
                case 3: cariProdi(); break;
                case 4: hitungStatus(); break;
                case 5:
                    System.out.println("Program selesai.");
                    break;
                default:
                    System.out.println("Menu tidak valid!");
            }
        } while (menu != 5);
    }

    // 1. Tambah Data
    static void tambahData() {
        if (jumlahData >= 100) {
            System.out.println("Kuota penyimpanan penuh!");
            return;
        }

        System.out.print("Nama Mahasiswa: ");
        String nama = sc.nextLine();

        System.out.print("NIM: ");
        String nim = sc.nextLine();

        System.out.print("Program Studi: ");
        String prodi = sc.nextLine();

        System.out.print("Perusahaan Tujuan Magang: ");
        String perusahaan = sc.nextLine();

        int semester;
        while (true) {
            System.out.print("Semester pengambilan magang (6 atau 7): ");
            semester = sc.nextInt();
            sc.nextLine();
            if (semester == 6 || semester == 7) break;
            System.out.println("Semester hanya boleh 6 atau 7!");
        }

        String status;
        while (true) {
            System.out.print("Status magang (Diterima/Menunggu/Ditolak): ");
            status = sc.nextLine();
            if (status.equalsIgnoreCase("Diterima") ||
                status.equalsIgnoreCase("Menunggu") ||
                status.equalsIgnoreCase("Ditolak")) break;
            System.out.println("Status tidak valid!");
        }

        // Simpan ke array
        data[jumlahData] = new Pendaftar(nama, nim, prodi, perusahaan, semester, status);
        jumlahData++;

        System.out.println("Data berhasil disimpan! NIM: " + nim);
    }

    // 2. Tampilkan semua data
    static void tampilData() {
        if (jumlahData == 0) {
            System.out.println("Belum ada pendaftar.");
            return;
        }

        System.out.println("\nNo\tNama\t\tNIM\t\tProdi\t\tPerusahaan\t\tStatus");
        System.out.println("========================================================================================");

        for (int i = 0; i < jumlahData; i++) {
            Pendaftar p = data[i];
            System.out.printf("%d\t%-10s\t%-10s\t%-15s\t%-15s\t%s\n",
                    (i+1), p.nama, p.nim, p.prodi, p.perusahaan, p.status);
        }
    }

    // 3. Cari berdasarkan prodi
    static void cariProdi() {
        System.out.print("Masukkan Program Studi: ");
        String cari = sc.nextLine();

        System.out.println("\nNo\tNama\t\tNIM\t\tProdi\t\tPerusahaan");

        int no = 1;
        boolean found = false;

        for (int i = 0; i < jumlahData; i++) {
            Pendaftar p = data[i];
            if (p.prodi.equalsIgnoreCase(cari)) {
                System.out.printf("%d\t%-10s\t%-10s\t%-15s\t%s\n",
                        no++, p.nama, p.nim, p.prodi, p.perusahaan);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Tidak ada pendaftar dari program studi tersebut.");
        }
    }

    // 4. Hitung pendaftar per status
    static void hitungStatus() {
        if (jumlahData == 0) {
            System.out.println("Belum ada pendaftar.");
            return;
        }

        int diterima = 0, menunggu = 0, ditolak = 0;

        for (int i = 0; i < jumlahData; i++) {
            String s = data[i].status.toLowerCase();
            if (s.equals("diterima")) diterima++;
            else if (s.equals("menunggu")) menunggu++;
            else if (s.equals("ditolak")) ditolak++;
        }

        // Hitung rasio diterima (desimal)
        double rasioDiterima = (double) diterima / jumlahData;

        System.out.println("\n=== Rekap Status Pendaftar ===");
        System.out.println("Diterima : " + diterima);
        System.out.println("Menunggu : " + menunggu);
        System.out.println("Ditolak  : " + ditolak);
        System.out.println("Total Pendaftar: " + jumlahData);
        System.out.printf("Rasio Diterima : %.2f\n", rasioDiterima);
    }
}
