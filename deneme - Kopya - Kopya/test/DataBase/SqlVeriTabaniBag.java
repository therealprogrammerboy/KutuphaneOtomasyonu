package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Kutuphane.Emanet;
import Kutuphane.Kategori;
import Kutuphane.Kitap;
import Kutuphane.Kutuphane;
import Kutuphane.Yazar;
import Kutuphane.Uye;

public class SqlVeriTabaniBag {
	
	
	String url="jdbc:sqlserver://localhost:1433;"
			+ "databasename=Kutuphane;IntegratedSecurity=true;";
	
	
	public Connection getconnection(){
		
		Connection baglantý=null;
		try {
			baglantý = DriverManager.getConnection(url);
			//System.out.println("Baglandý");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println("Baglanmadý");
		}
		
		
		return baglantý;
		
	}
	public ArrayList<Kutuphane> KutuphaneListele(){
		ArrayList<Kutuphane> kutuphaneListe=new ArrayList<Kutuphane>();
		Connection baglantý=getconnection();
		try {
			Statement sorgu=baglantý.createStatement();
			ResultSet rs=sorgu.executeQuery("select Kutuphane_No,Kutuphane_ismi,Aciklama,Adres_No from Kutuphane");
			while (rs.next()) {
				Kutuphane kutuphane=new Kutuphane();
				kutuphane.setKutNo(rs.getInt(1));
				kutuphane.setKutuphaneAdi(rs.getString(2));
				kutuphane.setKutAciklama(rs.getString(3));
				kutuphane.setNo(rs.getInt(4));
				kutuphaneListe.add(kutuphane);
			}
			baglantý.close();
			sorgu.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kutuphaneListe;
	}
	public ArrayList<Kategori> KategoriListele(){
		ArrayList<Kategori> kategoriListe=new ArrayList<Kategori>();
		Connection baglantý=getconnection();
		try {
			Statement sorgu=baglantý.createStatement();
			ResultSet rs=sorgu.executeQuery("select * from Kategoriler");
			while (rs.next()) {
				Kategori kategori=new Kategori();
				kategori.setKategoriNo(rs.getInt(1));
				kategori.setKategoriAdi(rs.getString(2));
				kategoriListe.add(kategori);
			}
			baglantý.close();
			sorgu.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kategoriListe;
	}
	public ArrayList<Emanet> EmanetListele(){
		ArrayList<Emanet> emanetListe=new ArrayList<Emanet>();
		Connection baglantý=getconnection();
		try {
			Statement sorgu=baglantý.createStatement();
			ResultSet rs=sorgu.executeQuery("select * from Emanet");
			while (rs.next()) {
				Emanet emanet=new Emanet();
				emanet.setemanetNo(rs.getInt(1));
				emanet.setISBN(rs.getString(2));
				emanet.setUyeNo(rs.getInt(3));
				emanet.setKutuphaneNo(rs.getInt(4));
				emanet.setEmanetTarihi(rs.getString(5));
				emanet.setTeslimTarihi(rs.getString(6));
				emanetListe.add(emanet);
			}
			baglantý.close();
			sorgu.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emanetListe;
	}
	public ArrayList<Yazar> YazarListele(){
		
		ArrayList<Yazar> liste=new ArrayList<Yazar>();
		Connection baglantý=getconnection();
		try {
			Statement sorgu=baglantý.createStatement();
			ResultSet rs=sorgu.executeQuery("Select * from Yazarlar");
			
			while(rs.next()){
				Yazar yazar=new Yazar();
				yazar.setYazar_No(rs.getInt(1));
				yazar.setYazar_Adi(rs.getString(2));
				yazar.setYazar_Soyadi(rs.getString(3));
				liste.add(yazar);
			}
			
			baglantý.close();
			sorgu.close();
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return liste;
			
	}

	public ArrayList<Kitap> KitapListele(){
		
		 ArrayList<Kitap> liste=new ArrayList<>();
		 
		 Connection baglantý=getconnection();
		 
		 try {
			Statement sorgu=baglantý.createStatement();
			ResultSet rs=sorgu.executeQuery("select * from Kitaplar");
		
			while(rs.next()){
				
				Kitap kitap=new Kitap();
				
				kitap.setISBN(rs.getString("ISBN"));
				kitap.setKitapAdi(rs.getString("Kitap_Adi"));
				kitap.setYayinTarihi(rs.getString(3));
				kitap.setSayfaSayisi(rs.getInt(4));
				
				liste.add(kitap);
				
			}
			baglantý.close();
			sorgu.close();
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return liste;
	}

	/**
	 * @return
	 */
	public ArrayList<Uye> UyeListele(){
		ArrayList<Uye> uyeliste=new ArrayList<Uye>();
		Connection baglantý=getconnection();
		try {
			Statement sorgu=baglantý.createStatement();
			ResultSet rs=sorgu.executeQuery("select * from UYELER");
			while (rs.next()) {
				Uye uye=new Uye();
				uye.setUye_No(rs.getInt("Uye_No"));
				uye.setUye_Adi(rs.getString("Uye_Adi"));
				uye.setUye_Soyadi(rs.getString("Uye_Soyadi"));
				uye.setCinsiyet(rs.getString("Cinsiyet"));
				uye.setEposta(rs.getString("E_posta"));
				uye.setTelefon(rs.getString("Telefon"));
				uye.setAdres_No(rs.getInt("Adres_No"));
				uyeliste.add(uye);
			}
			baglantý.close();
			sorgu.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return uyeliste;
	}
	
	public void YazarEkle(Yazar yazar){
		 Connection baglantý=getconnection();
		 
		 String yzekle="insert into Yazarlar (Yazar_No,Yazar_Adi,Yazar_Soyadi) values"
		 		+ "('"+yazar.getYazar_No()+"',"
		 		+ "'"+yazar.getYazar_Adi()+"',"
		 		+ "'"+yazar.getYazar_Soyadi()+"') ";
		 
		 try {
			Statement st=baglantý.createStatement();
			st.executeUpdate(yzekle);
			baglantý.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
	}

	public void YazarSil(Yazar silyazar){
		Connection baglantý=getconnection();
		 
		 String yzsil="delete from Yazarlar where Yazar_No='"+silyazar.getYazar_No()+"'";
		 
		 try {
			Statement st=baglantý.createStatement();
			st.executeUpdate(yzsil);
			baglantý.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
	}
	
	public void YazarGuncelle(Yazar guncelleyazar){
		Connection baglantý=getconnection();
		 
		 String yzguncelle="update Yazarlar set "
		 		+ "Yazar_Adi='"+guncelleyazar.getYazar_Adi()+"', "
		 		+ "Yazar_Soyadi='"+guncelleyazar.getYazar_Soyadi()+"'"
		 		+ "where Yazar_No='"+guncelleyazar.getYazar_No()+"'";
		 
		 try {
			Statement st=baglantý.createStatement();
			st.executeUpdate(yzguncelle);
			baglantý.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	public void KitapEkle(Kitap ekleKitap) {
		Connection baglantý=getconnection();
		String kitapEkle="insert into Kitaplar(ISBN,Kitap_Adi,Yayin_Tarihi,S_Sayisi) values('"+ekleKitap.getISBN()+"','"+ekleKitap.getKitapAdi()+"','"+ekleKitap.getYayinTarihi()+"',"+ekleKitap.getSayfaSayisi()+")";
		String kategoriEkle="insert into Kategoriler(Kategori_Adi) values('"+ekleKitap.getKategoriAdi()+"')";
		String yazarEkle="insert into Yazarlar(Yazar_Adi) values('"+ekleKitap.getYazari()+"')";
		String kutuphaneEkle="insert into Kutuphane(Kutuphane_ismi) values('"+ekleKitap.getKutuphaneAdi()+"')";
		try {
			Statement st=baglantý.createStatement();
			st.executeQuery(kitapEkle);
			st.executeQuery(kategoriEkle);
			st.executeQuery(yazarEkle);
			st.executeQuery(kutuphaneEkle);
			baglantý.close();
			st.close();
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
		}
	}
	
	public void KitapSil(Kitap silKitap) {
		Connection baglantý=getconnection();
		String kitapSil="delete from Kitaplar where ISBN='"+silKitap.getISBN()+"'";
		try {
			Statement st=baglantý.createStatement();
			st.executeUpdate(kitapSil);
			baglantý.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	public void KitapGuncelle(Kitap guncelleKitap) {
		Connection baglantý=getconnection();
		String kitapGuncelle="update Kitaplar set Kitap_Adi='"+guncelleKitap.getKitapAdi()+"',Yayin_Tarihi='"+guncelleKitap.getYayinTarihi()+"',S_Sayisi="+guncelleKitap.getSayfaSayisi()+"where ISBN='"+guncelleKitap.getISBN()+"'";

		 try {
			Statement st=baglantý.createStatement();
			st.executeUpdate(kitapGuncelle);
			baglantý.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void UyeEkle(Uye ekleUye) {
		Connection baglantý=getconnection();
		String uyeEkle="insert into UYELER(Uye_Adi,Uye_Soyadi,Cinsiyet,Adres_No,Telefon,E_posta) values('"+ekleUye.getUyeAdi()+"','"+ekleUye.getUye_Soyadi()+"','"+ekleUye.getCinsiyet()+"',"+ekleUye.getAdres_No()+",'"+ekleUye.getTelefon()+"','"+ekleUye.getEposta()+"')";
		try {
			Statement st=baglantý.createStatement();
			st.executeQuery(uyeEkle);
			baglantý.close();
			st.close();
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
		}
	}
	public void UyeSil(Uye silUye) {
		Connection baglantý=getconnection();
		String uyeSil="delete from UYELER where Uye_No="+silUye.getUye_no()+"";
		try {
			Statement st=baglantý.createStatement();
			st.executeQuery(uyeSil);
			baglantý.close();
			st.close();
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
		}
	}
	public void UyeGuncelle(Uye guncelleUye) {
		Connection baglantý=getconnection();
		String uyeGuncelle="update UYELER set Uye_Adi='"+guncelleUye.getUyeAdi()+"',Uye_Soyadi='"+guncelleUye.getUye_Soyadi()+""
				+ "', Cinsiyet='"+guncelleUye.getCinsiyet()+"',Adres_No="+guncelleUye.getAdres_No()+""
						+ ",Telefon='"+guncelleUye.getTelefon()+"',E_posta='"+guncelleUye.getEposta()+""
								+ "' where Uye_No="+guncelleUye.getUye_no()+"";
		try {
			Statement st=baglantý.createStatement();
			st.executeUpdate(uyeGuncelle);
			baglantý.close();
			st.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public void KutuphaneList() {
		
	}
}
