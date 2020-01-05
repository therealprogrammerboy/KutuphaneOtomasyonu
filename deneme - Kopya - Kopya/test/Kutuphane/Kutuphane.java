package Kutuphane;

public class Kutuphane {
public String kutuphaneAdi;
public int kutNo;
public String kutAciklama;
public int kutAdresNo;

public String getKutuphaneAdi() {
	return kutuphaneAdi;
}
public void setKutuphaneAdi(String kut_Adi) {
	kutuphaneAdi=kut_Adi;
}
public int getKutNo() {
	return kutNo;
}
public void setKutNo(int kutno) {
	kutNo=kutno;
}
public String getKutAciklama() {
	return kutAciklama;
}
public void setKutAciklama(String aciklama) {
	kutAciklama=aciklama;
}
public int getNo() {
	return kutAdresNo;
}
public void setNo(int no) {
	kutAdresNo=no;
	
}
public String toString() {
	return kutuphaneAdi;
}
}
