package Kutuphane;

public class Kitap {
	
	private String ISBN;
	private String kitapAdi;
	private String yayinTarihi;
	private int sayfaSayisi;
	private String Yazari;
	private String KategoriAdi;
	private String KutuphaneAdi;
	
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getKitapAdi() {
		return kitapAdi;
	}
	public void setKitapAdi(String kitapAdi) {
		this.kitapAdi = kitapAdi;
	}
	public String getYayinTarihi() {
		return yayinTarihi;
	}
	public void setYayinTarihi(String yayinTarihi) {
		this.yayinTarihi = yayinTarihi;
	}
	public int getSayfaSayisi() {
		return sayfaSayisi;
	}
	public void setSayfaSayisi(int sayfaSayisi) {
		this.sayfaSayisi = sayfaSayisi;
	}
	public String getYazari() {
		return Yazari;
	}
	public void setYazari(String yazari) {
		Yazari = yazari;
	}
	public String getKategoriAdi() {
		return KategoriAdi;
	}
	public void setKategoriAdi(String kategoriAdi) {
		KategoriAdi = kategoriAdi;
	}
	public String getKutuphaneAdi() {
		return KutuphaneAdi;
	}
	public void setKutuphaneAdi(String kutuphaneAdi) {
		KutuphaneAdi = kutuphaneAdi;
	}
	
	

}
