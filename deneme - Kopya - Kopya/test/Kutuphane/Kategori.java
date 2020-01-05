package Kutuphane;

public class Kategori {
private int kategoriNo;
private String kitapKategori;
public Kategori() {}
public int getKategoriNo() {
	return kategoriNo;
}

public void setKategoriNo(int katno) {
	kategoriNo=katno;
}

public String getKategoriAdi() {
	return kitapKategori;
}
public void setKategoriAdi(String kategoriAdi) {
	kitapKategori = kategoriAdi;
}
public String toString() {
	return kitapKategori;
}
}
