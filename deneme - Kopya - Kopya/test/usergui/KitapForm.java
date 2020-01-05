package usergui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import com.sun.glass.events.MouseEvent;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.*;
import DataBase.SqlVeriTabaniBag;
import Kutuphane.Kategori;
import Kutuphane.Kitap;
import Kutuphane.Kutuphane;
import Kutuphane.Yazar;


import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.util.Date;

public class KitapForm extends JFrame {

	/**
	 * 
	 */
	SqlVeriTabaniBag sql=new SqlVeriTabaniBag();
	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTextField txtisbn;
	public JTextField txtkitapAd;
	public JTextField txtSayfaSayisi;
	public JTable kitapTable;
	public DefaultTableModel kitapmodel;
	
	Object kitap[]=new Object[4];
	
	ArrayList<Kitap> liste=new SqlVeriTabaniBag().KitapListele();
	ArrayList<Yazar> Yazarliste=new SqlVeriTabaniBag().YazarListele();
	ArrayList<Kategori> KategoriListe=new SqlVeriTabaniBag().KategoriListele();
	ArrayList<Kutuphane> KutuphaneListe=new SqlVeriTabaniBag().KutuphaneListele();
	
	
			
	
	
	
	
	public JComboBox cmbxKutuphane;
	public JComboBox cmbxYazar;
	public JComboBox cmbxKategori;

	

	/**
	 * Create the frame.
	 */
	public KitapForm() {
		setTitle("Kitap Ekraný");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		setSize(876, 551);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Kitap Ekle", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 319, 485);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIsbn.setBounds(10, 21, 95, 26);
		panel.add(lblIsbn);
		
		JLabel lblKitapAd = new JLabel("Kitap Adý:");
		lblKitapAd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKitapAd.setBounds(10, 68, 95, 26);
		panel.add(lblKitapAd);
		
		JLabel lblYaynTarihi = new JLabel("Yayýn Tarihi:");
		lblYaynTarihi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYaynTarihi.setBounds(10, 115, 95, 26);
		panel.add(lblYaynTarihi);
		
		JLabel lblSayfaSays = new JLabel("Sayfa Sayýsý:");
		lblSayfaSays.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSayfaSays.setBounds(10, 162, 95, 26);
		panel.add(lblSayfaSays);
		
		txtisbn = new JTextField();
		txtisbn.setBounds(115, 21, 175, 26);
		panel.add(txtisbn);
		txtisbn.setColumns(10);
		
		txtkitapAd = new JTextField();
		txtkitapAd.setColumns(10);
		txtkitapAd.setBounds(115, 68, 175, 26);
		panel.add(txtkitapAd);
		
		txtSayfaSayisi = new JTextField();
		txtSayfaSayisi.setColumns(10);
		txtSayfaSayisi.setBounds(115, 162, 175, 26);
		panel.add(txtSayfaSayisi);
		
		JDateChooser dateChooseryayintar = new JDateChooser();
		dateChooseryayintar.setDateFormatString("dd.MM.yyyy");
		dateChooseryayintar.setBounds(115, 115, 175, 26);
		panel.add(dateChooseryayintar);
		
		JButton btnKaydet = new JButton("Kaydet");
		btnKaydet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnKaydet, "Ýþlem Gerçekleþti");
				String gelenISBN=txtisbn.getText(),gelenKitapAdi=txtkitapAd.getText(),gelenSayfaSayisi=txtSayfaSayisi.getText();
				int donusumSayfaSayisi=Integer.parseInt(gelenSayfaSayisi);
				DateFormat df=new SimpleDateFormat("dd.MM.yyyy");
				String date=df.format(dateChooseryayintar.getDate());
				Object gelenKategoriAdi=cmbxKategori.getSelectedItem().toString(),gelenYazarAdi=cmbxYazar.getSelectedItem().toString(),gelenKutuphaneAdi=cmbxKutuphane.getSelectedItem().toString();
				
				Kitap ekleKitap=new Kitap();
				ekleKitap.setISBN(gelenISBN);
				ekleKitap.setKitapAdi(gelenKitapAdi);
				ekleKitap.setSayfaSayisi(donusumSayfaSayisi);
				ekleKitap.setYayinTarihi(date);
				ekleKitap.setKategoriAdi((String) gelenKategoriAdi);
				ekleKitap.setYazari((String) gelenYazarAdi);
				ekleKitap.setKutuphaneAdi((String) gelenKutuphaneAdi);
				sql.KitapEkle(ekleKitap);
			}
		});
		btnKaydet.setBounds(10, 359, 89, 38);
		panel.add(btnKaydet);
		
		JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnSil, "Ýþlem Gerçekleþti");
				String gelenISBN=txtisbn.getText();
				Kitap silKitap=new Kitap();
				silKitap.setISBN(gelenISBN);
				sql.KitapSil(silKitap);
			}
		});
		btnSil.setBounds(112, 359, 89, 38);
		panel.add(btnSil);
		
		JButton btnGuncelle = new JButton("Güncelle");
		btnGuncelle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnGuncelle, "Ýþlem Gerçekleþti");
				String gelenISBN=txtisbn.getText(),gelenKitapAdi=txtkitapAd.getText(),gelenSayfaSayisi=txtSayfaSayisi.getText();
				int donusumSayfaSayisi=Integer.parseInt(gelenSayfaSayisi);
				DateFormat df=new SimpleDateFormat("dd.MM.yyyy");
				String date=df.format(dateChooseryayintar.getDate());
				Kitap guncelleKitap=new Kitap();
				guncelleKitap.setISBN(gelenISBN);
				guncelleKitap.setKitapAdi(gelenKitapAdi);
				guncelleKitap.setSayfaSayisi(donusumSayfaSayisi);
				guncelleKitap.setYayinTarihi(date);
				sql.KitapGuncelle(guncelleKitap);
			}
		});
		btnGuncelle.setBounds(214, 359, 89, 38);
		panel.add(btnGuncelle);
		
		JLabel lblKategoriAd = new JLabel("Kategori Adý:");
		lblKategoriAd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKategoriAd.setBounds(10, 208, 95, 26);
		panel.add(lblKategoriAd);
		
		cmbxKategori = new JComboBox(KategoriListe.toArray());
		cmbxKategori.setBounds(115, 208, 175, 26);
		panel.add(cmbxKategori);
		
		JLabel lblYazar = new JLabel("Yazarý:");
		lblYazar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYazar.setBounds(10, 255, 95, 26);
		panel.add(lblYazar);
		
		cmbxYazar = new JComboBox(Yazarliste.toArray());
		cmbxYazar.setBounds(115, 255, 175, 26);
		panel.add(cmbxYazar);
		
		JLabel lblKtphane = new JLabel("Kütüphane:");
		lblKtphane.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKtphane.setBounds(10, 299, 95, 26);
		panel.add(lblKtphane);
		
		cmbxKutuphane = new JComboBox(KutuphaneListe.toArray());
		cmbxKutuphane.setBounds(115, 299, 175, 26);
		panel.add(cmbxKutuphane);
		
		JButton btnListele = new JButton("Listele");
		
		btnListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				KitapListele();
				
			}
		});
		btnListele.setBounds(10, 412, 293, 38);
		panel.add(btnListele);
		
		JScrollPane panel_1 = new JScrollPane();
		panel_1.setBorder(new TitledBorder(null, "Kitap Listesi", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(339, 11, 509, 485);
		contentPane.add(panel_1);
		
		kitapTable = new JTable();
		kitapTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int satýrindex=kitapTable.getSelectedRow();
				txtisbn.setText((String)kitapmodel.getValueAt(satýrindex, 0));
				txtkitapAd.setText((String)kitapmodel.getValueAt(satýrindex, 1));
				dateChooseryayintar.setDateFormatString((String)kitapmodel.getValueAt(satýrindex, 2));
				txtSayfaSayisi.setText(String.valueOf(kitapmodel.getValueAt(satýrindex, 3)));
			}
		});
		kitapTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ISBN", "Kitap Ad\u0131", "Yay\u0131n Tarihi", "Sayfa Say\u0131s\u0131"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		kitapTable.getColumnModel().getColumn(0).setPreferredWidth(114);
		kitapTable.getColumnModel().getColumn(1).setPreferredWidth(226);
		kitapTable.getColumnModel().getColumn(2).setPreferredWidth(96);
		panel_1.setViewportView(kitapTable);
	}
	public void KitapListele(){
		kitapmodel =(DefaultTableModel) kitapTable.getModel();
		for(int i=0;i<liste.size();i++){
			kitap[0]=liste.get(i).getISBN();
			kitap[1]=liste.get(i).getKitapAdi();
			kitap[2]=liste.get(i).getYayinTarihi();
			kitap[3]=liste.get(i).getSayfaSayisi();
			kitapmodel.addRow(kitap);
			
		}
	}}
	





