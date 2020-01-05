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
		
		Connection baglant�=null;
		try {
			baglant� = DriverManager.getConnection(url);
			//System.out.println("Bagland�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println("Baglanmad�");
		}
		
		
		return baglant�;
		
	}
	public ArrayList<Kutuphane> KutuphaneListele(){
		ArrayList<Kutuphane> kutuphaneListe=new ArrayList<Kutuphane>();
		Connection baglant�=getconnection();
		try {
			Statement sorgu=baglant�.createStatement();
			ResultSet rs=sorgu.executeQuery("select Kutuphane_No,Kutuphane_ismi,Aciklama,Adres_No from Kutuphane");
			while (rs.next()) {
				Kutuphane kutuphane=new Kutuphane();
				kutuphane.setKutNo(rs.getInt(1));
				kutuphane.setKutuphaneAdi(rs.getString(2));
				kutuphane.setKutAciklama(rs.getString(3));
				kutuphane.setNo(rs.getInt(4));
				kutuphaneListe.add(kutuphane);
			}
			baglant�.close();
			sorgu.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kutuphaneListe;
	}
	public ArrayList<Kategori> KategoriListele(){
		ArrayList<Kategori> kategoriListe=new ArrayList<Kategori>();
		Connection baglant�=getconnection();
		try {
			Statement sorgu=baglant�.createStatement();
			ResultSet rs=sorgu.executeQuery("select * from Kategoriler");
			while (rs.next()) {
				Kategori kategori=new Kategori();
				kategori.setKategoriNo(rs.getInt(1));
				kategori.setKategoriAdi(rs.getString(2));
				kategoriListe.add(kategori);
			}
			baglant�.close();
			sorgu.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kategoriListe;
	}
	public ArrayList<Emanet> EmanetListele(){
		ArrayList<Emanet> emanetListe=new ArrayList<Emanet>();
		Connection baglant�=getconnection();
		try {
			Statement sorgu=baglant�.createStatement();
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
			baglant�.close();
			sorgu.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emanetListe;
	}
	public ArrayList<Yazar> YazarListele(){
		
		ArrayList<Yazar> liste=new ArrayList<Yazar>();
		Connection baglant�=getconnection();
		try {
			Statement sorgu=baglant�.createStatement();
			ResultSet rs=sorgu.executeQuery("Select * from Yazarlar");
			
			while(rs.next()){
				Yazar yazar=new Yazar();
				yazar.setYazar_No(rs.getInt(1));
				yazar.setYazar_Adi(rs.getString(2));
				yazar.setYazar_Soyadi(rs.getString(3));
				liste.add(yazar);
			}
			
			baglant�.close();
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
		 
		 Connection baglant�=getconnection();
		 
		 try {
			Statement sorgu=baglant�.createStatement();
			ResultSet rs=sorgu.executeQuery("select * from Kitaplar");
		
			while(rs.next()){
				
				Kitap kitap=new Kitap();
				
				kitap.setISBN(rs.getString("ISBN"));
				kitap.setKitapAdi(rs.getString("Kitap_Adi"));
				kitap.setYayinTarihi(rs.getString(3));
				kitap.setSayfaSayisi(rs.getInt(4));
				
				liste.add(kitap);
				
			}
			baglant�.close();
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
		Connection baglant�=getconnection();
		try {
			Statement sorgu=baglant�.createStatement();
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
			baglant�.close();
			sorgu.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return uyeliste;
	}
	
	public void YazarEkle(Yazar yazar){
		 Connection baglant�=getconnection();
		 
		 String yzekle="insert into Yazarlar (Yazar_No,Yazar_Adi,Yazar_Soyadi) values"
		 		+ "('"+yazar.getYazar_No()+"',"
		 		+ "'"+yazar.getYazar_Adi()+"',"
		 		+ "'"+yazar.getYazar_Soyadi()+"') ";
		 
		 try {
			Statement st=baglant�.createStatement();
			st.executeUpdate(yzekle);
			baglant�.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
	}

	public void YazarSil(Yazar silyazar){
		Connection baglant�=getconnection();
		 
		 String yzsil="delete from Yazarlar where Yazar_No='"+silyazar.getYazar_No()+"'";
		 
		 try {
			Statement st=baglant�.createStatement();
			st.executeUpdate(yzsil);
			baglant�.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
	}
	
	public void YazarGuncelle(Yazar guncelleyazar){
		Connection baglant�=getconnection();
		 
		 String yzguncelle="update Yazarlar set "
		 		+ "Yazar_Adi='"+guncelleyazar.getYazar_Adi()+"', "
		 		+ "Yazar_Soyadi='"+guncelleyazar.getYazar_Soyadi()+"'"
		 		+ "where Yazar_No='"+guncelleyazar.getYazar_No()+"'";
		 
		 try {
			Statement st=baglant�.createStatement();
			st.executeUpdate(yzguncelle);
			baglant�.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	public void KitapEkle(Kitap ekleKitap) {
		Connection baglant�=getconnection();
		String kitapEkle="insert into Kitaplar(ISBN,Kitap_Adi,Yayin_Tarihi,S_Sayisi) values('"+ekleKitap.getISBN()+"','"+ekleKitap.getKitapAdi()+"','"+ekleKitap.getYayinTarihi()+"',"+ekleKitap.getSayfaSayisi()+")";
		String kategoriEkle="insert into Kategoriler(Kategori_Adi) values('"+ekleKitap.getKategoriAdi()+"')";
		String yazarEkle="insert into Yazarlar(Yazar_Adi) values('"+ekleKitap.getYazari()+"')";
		String kutuphaneEkle="insert into Kutuphane(Kutuphane_ismi) values('"+ekleKitap.getKutuphaneAdi()+"')";
		try {
			Statement st=baglant�.createStatement();
			st.executeQuery(kitapEkle);
			st.executeQuery(kategoriEkle);
			st.executeQuery(yazarEkle);
			st.executeQuery(kutuphaneEkle);
			baglant�.close();
			st.close();
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
		}
	}
	
	public void KitapSil(Kitap silKitap) {
		Connection baglant�=getconnection();
		String kitapSil="delete from Kitaplar where ISBN='"+silKitap.getISBN()+"'";
		try {
			Statement st=baglant�.createStatement();
			st.executeUpdate(kitapSil);
			baglant�.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	public void KitapGuncelle(Kitap guncelleKitap) {
		Connection baglant�=getconnection();
		String kitapGuncelle="update Kitaplar set Kitap_Adi='"+guncelleKitap.getKitapAdi()+"',Yayin_Tarihi='"+guncelleKitap.getYayinTarihi()+"',S_Sayisi="+guncelleKitap.getSayfaSayisi()+"where ISBN='"+guncelleKitap.getISBN()+"'";

		 try {
			Statement st=baglant�.createStatement();
			st.executeUpdate(kitapGuncelle);
			baglant�.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void UyeEkle(Uye ekleUye) {
		Connection baglant�=getconnection();
		String uyeEkle="insert into UYELER(Uye_Adi,Uye_Soyadi,Cinsiyet,Adres_No,Telefon,E_posta) values('"+ekleUye.getUyeAdi()+"','"+ekleUye.getUye_Soyadi()+"','"+ekleUye.getCinsiyet()+"',"+ekleUye.getAdres_No()+",'"+ekleUye.getTelefon()+"','"+ekleUye.getEposta()+"')";
		try {
			Statement st=baglant�.createStatement();
			st.executeQuery(uyeEkle);
			baglant�.close();
			st.close();
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
		}
	}
	public void UyeSil(Uye silUye) {
		Connection baglant�=getconnection();
		String uyeSil="delete from UYELER where Uye_No="+silUye.getUye_no()+"";
		try {
			Statement st=baglant�.createStatement();
			st.executeQuery(uyeSil);
			baglant�.close();
			st.close();
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
		}
	}
	public void UyeGuncelle(Uye guncelleUye) {
		Connection baglant�=getconnection();
		String uyeGuncelle="update UYELER set Uye_Adi='"+guncelleUye.getUyeAdi()+"',Uye_Soyadi='"+guncelleUye.getUye_Soyadi()+""
				+ "', Cinsiyet='"+guncelleUye.getCinsiyet()+"',Adres_No="+guncelleUye.getAdres_No()+""
						+ ",Telefon='"+guncelleUye.getTelefon()+"',E_posta='"+guncelleUye.getEposta()+""
								+ "' where Uye_No="+guncelleUye.getUye_no()+"";
		try {
			Statement st=baglant�.createStatement();
			st.executeUpdate(uyeGuncelle);
			baglant�.close();
			st.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public void KutuphaneList() {
		
	}
}
