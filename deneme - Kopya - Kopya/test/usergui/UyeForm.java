package usergui;
import java.sql.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;

import DataBase.SqlVeriTabaniBag;
import Kutuphane.Uye;

public class UyeForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JScrollPane scrollPane = new JScrollPane();
	private JTable uyetable;
	private JTextField txtUyeNo;
	private JTextField txtUyeAdi;
	private JTextField txtUyeSoyadi;
	private JTextField txtUyeAdresno;
	private JTextField txtUyeTelefon;
	private JTextField txtePosta;
	public DefaultTableModel uyemodel;
	SqlVeriTabaniBag sql=new SqlVeriTabaniBag();
	ArrayList<Uye> uyelistesi=new SqlVeriTabaniBag().UyeListele();
	Object uye[]=new Object[7];
	//DefaultTableModel tm;
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public UyeForm() throws SQLException {
		DefaultTableModel tm;
		setTitle("Uye Ekle");
		setSize(882, 541);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Üye Giriþ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 11, 305, 365);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblUyeNo = new JLabel("Üye No:");
		lblUyeNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUyeNo.setBounds(10, 16, 75, 21);
		panel.add(lblUyeNo);

		JLabel lblUyeAd = new JLabel("Üye Adý:");
		lblUyeAd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUyeAd.setBounds(10, 53, 75, 21);
		panel.add(lblUyeAd);

		JLabel lblUyeSoyad = new JLabel("Üye Soyadý:");
		lblUyeSoyad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUyeSoyad.setBounds(10, 90, 75, 21);
		panel.add(lblUyeSoyad);

		JLabel lblCinsiyet = new JLabel("Cinsiyet:");
		lblCinsiyet.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCinsiyet.setBounds(10, 127, 75, 21);
		panel.add(lblCinsiyet);

		JLabel lblAdresNo = new JLabel("Adres No:");
		lblAdresNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdresNo.setBounds(10, 164, 75, 21);
		panel.add(lblAdresNo);

		JLabel lblTelefon = new JLabel("Telefon:");
		lblTelefon.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefon.setBounds(10, 201, 75, 21);
		panel.add(lblTelefon);

		JLabel lblEPosta = new JLabel("e Posta:");
		lblEPosta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEPosta.setBounds(10, 238, 75, 21);
		panel.add(lblEPosta);

		txtUyeNo = new JTextField();
		txtUyeNo.setBounds(114, 17, 151, 20);
		panel.add(txtUyeNo);
		txtUyeNo.setColumns(10);

		txtUyeAdi = new JTextField();
		txtUyeAdi.setColumns(10);
		txtUyeAdi.setBounds(114, 54, 151, 20);
		panel.add(txtUyeAdi);

		txtUyeSoyadi = new JTextField();
		txtUyeSoyadi.setColumns(10);
		txtUyeSoyadi.setBounds(114, 91, 151, 21);
		panel.add(txtUyeSoyadi);

		txtUyeAdresno = new JTextField();
		txtUyeAdresno.setColumns(10);
		txtUyeAdresno.setBounds(114, 165, 151, 20);
		panel.add(txtUyeAdresno);

		txtUyeTelefon = new JTextField();
		txtUyeTelefon.setColumns(10);
		txtUyeTelefon.setBounds(114, 202, 151, 20);
		panel.add(txtUyeTelefon);

		txtePosta = new JTextField();
		txtePosta.setColumns(10);
		txtePosta.setBounds(114, 239, 151, 20);
		panel.add(txtePosta);

		JRadioButton rdbtnErkek = new JRadioButton("Erkek");
		rdbtnErkek.setBounds(114, 125, 58, 23);
		panel.add(rdbtnErkek);

		JRadioButton rdbtnKadin = new JRadioButton("Kadýn");
		rdbtnKadin.setBounds(187, 125, 58, 23);
		panel.add(rdbtnKadin);

		JButton btnKaydet = new JButton("Kaydet");
		btnKaydet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnKaydet, "Ýþlem Gerçekleþti");
				String gelenUyeAdi=txtUyeAdi.getText(),gelenUyeSoyadi=txtUyeSoyadi.getText(),
				gelenUyeTelefon=txtUyeTelefon.getText(),gelenUyeEposta=txtePosta.getText(),gelenCinsiyet;
				 int donusumAdresNo=Integer.parseInt(txtUyeAdresno.getText());
				 boolean isRdbtnErkekSelected=rdbtnErkek.isSelected();
				 if (isRdbtnErkekSelected) {
					 							gelenCinsiyet=rdbtnErkek.getText();
				} else {
							gelenCinsiyet=rdbtnKadin.getText();
				}
				 Uye ekleUye=new Uye();
				 ekleUye.setUye_Adi(gelenUyeAdi);
				 ekleUye.setUye_Soyadi(gelenUyeSoyadi);
				 ekleUye.setCinsiyet(gelenCinsiyet);
				 ekleUye.setAdres_No(donusumAdresNo);
				 ekleUye.setTelefon(gelenUyeTelefon);
				 ekleUye.setEposta(gelenUyeEposta);
				 sql.UyeEkle(ekleUye);
			}
		});
		btnKaydet.setBounds(14, 270, 83, 38);
		panel.add(btnKaydet);

		JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnSil, "ÝÞLEM GERÇEKLEÞTÝ :)");
				int gelenUyeNo=Integer.parseInt(txtUyeNo.getText());
				Uye silUye=new Uye();
				silUye.setUye_No(gelenUyeNo);
				sql.UyeSil(silUye);
			}
		});
		btnSil.setBounds(111, 270, 83, 38);
		panel.add(btnSil);

		JButton btnGncelle = new JButton("Güncelle");
		btnGncelle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnGncelle, "ÝÞLEM GERÇEKLEÞTÝ :)");
				Uye guncelleUye=new Uye();
				String gelenUyeAdi=txtUyeAdi.getText(),gelenUyeSoyadi=txtUyeSoyadi.getText(),
						gelenAdresNo=txtUyeAdresno.getText(),gelenUyeTelefon=txtUyeTelefon.getText(),
						gelenUyeEposta=txtePosta.getText(),gelenCinsiyet;
						 int donusumAdresNo=Integer.parseInt(gelenAdresNo),gelenUyeNo=Integer.parseInt(txtUyeNo.getText());
						 boolean isRdbtnErkekSelected=rdbtnErkek.isSelected();
						 if (isRdbtnErkekSelected) {
							 							gelenCinsiyet=rdbtnErkek.getText();
						} else {
									gelenCinsiyet=rdbtnKadin.getText();
						}
						 
						 guncelleUye.setUye_No(gelenUyeNo);
						 guncelleUye.setUye_Adi(gelenUyeAdi);
						 guncelleUye.setUye_Soyadi(gelenUyeSoyadi);
						 guncelleUye.setCinsiyet(gelenCinsiyet);
						 guncelleUye.setAdres_No(donusumAdresNo);
						 guncelleUye.setTelefon(gelenUyeTelefon);
						 guncelleUye.setEposta(gelenUyeEposta);
						 sql.UyeGuncelle(guncelleUye);
				
			}
		});
		btnGncelle.setBounds(208, 270, 83, 38);
		panel.add(btnGncelle);
		
		JButton btnListele = new JButton("Listele");
		btnListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UyeListele();
			}
		});
		btnListele.setBounds(10, 318, 283, 36);
		panel.add(btnListele);
		scrollPane.setBorder(new TitledBorder(null, "Üyeler", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(305, 11, 549, 475);
		getContentPane().add(scrollPane);

		uyetable = new JTable();
		uyetable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int satirindex=uyetable.getSelectedRow();
				txtUyeNo.setText(String.valueOf(uyemodel.getValueAt(satirindex, 0)));
				txtUyeAdi.setText((String)uyemodel.getValueAt(satirindex, 1));
				txtUyeSoyadi.setText((String)uyemodel.getValueAt(satirindex, 2));
				txtUyeAdresno.setText(String.valueOf(uyemodel.getValueAt(satirindex, 6)));
				txtUyeTelefon.setText((String)uyemodel.getValueAt(satirindex, 5));
				txtePosta.setText((String)uyemodel.getValueAt(satirindex, 4));
				
			}
		});
		uyetable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "No", "Üye Adý", "Üye Soyadý", "Cinsiyet", "E Posta", "Telefon", "Adres No" }) {
			/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		uyetable.getColumnModel().getColumn(0).setResizable(false);
		uyetable.getColumnModel().getColumn(0).setPreferredWidth(42);
		uyetable.getColumnModel().getColumn(1).setPreferredWidth(66);
		uyetable.getColumnModel().getColumn(2).setPreferredWidth(90);
		scrollPane.setViewportView(uyetable);
		}
public void UyeListele() {
	uyemodel=(DefaultTableModel)uyetable.getModel();
	for (int i = 0; i < uyelistesi.size(); i++) {
		uye[0]=uyelistesi.get(i).getUye_no();
		uye[1]=uyelistesi.get(i).getUyeAdi();
		uye[2]=uyelistesi.get(i).getUye_Soyadi();
		uye[3]=uyelistesi.get(i).getCinsiyet();
		uye[4]=uyelistesi.get(i).getEposta();
		uye[5]=uyelistesi.get(i).getTelefon();
		uye[6]=uyelistesi.get(i).getAdres_No();
		uyemodel.addRow(uye);
	}
}
	}

