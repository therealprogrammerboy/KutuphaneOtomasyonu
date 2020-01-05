package usergui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DataBase.SqlVeriTabaniBag;
import Kutuphane.Yazar;

import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Yazarform extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtyzrNo;
	private JTextField txtyzrAdi;
	private JTextField txtyzrSoyadi;
	private JTable yzrtable;
	private DefaultTableModel yazarmodel;
		
	SqlVeriTabaniBag sql=new SqlVeriTabaniBag();
	ArrayList<Yazar> yazarliste=sql.YazarListele();
	
	private JButton btnyzrListele;
	private JButton btnKaydet;
	private JButton btnSil;
	private JButton btnGuncelle;
	


	/**
	 * Create the frame.
	 */
	public Yazarform() {
		setTitle("Yazar  Ekle");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setBounds(100, 100, 573, 313);
		setSize(675, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel yzrekle = new JPanel();
		yzrekle.setBorder(new TitledBorder(null, "Yazar Ekle", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		yzrekle.setBounds(0, 0, 300, 254);
		contentPane.add(yzrekle);
		yzrekle.setLayout(null);
		
		JLabel lblYazarNo = new JLabel("Yazar No:");
		lblYazarNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYazarNo.setBounds(10, 29, 85, 23);
		yzrekle.add(lblYazarNo);
		
		JLabel lblYazarAd = new JLabel("Yazar Adý:");
		lblYazarAd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYazarAd.setBounds(10, 63, 85, 23);
		yzrekle.add(lblYazarAd);
		
		JLabel lblYazarSoyad = new JLabel("Yazar Soyadý:");
		lblYazarSoyad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYazarSoyad.setBounds(10, 97, 85, 23);
		yzrekle.add(lblYazarSoyad);
		
		txtyzrNo = new JTextField();
		txtyzrNo.setBounds(105, 29, 183, 23);
		yzrekle.add(txtyzrNo);
		txtyzrNo.setColumns(10);
		
		txtyzrAdi = new JTextField();
		txtyzrAdi.setColumns(10);
		txtyzrAdi.setBounds(105, 63, 183, 23);
		yzrekle.add(txtyzrAdi);
		
		txtyzrSoyadi = new JTextField();
		txtyzrSoyadi.setColumns(10);
		txtyzrSoyadi.setBounds(105, 97, 183, 23);
		yzrekle.add(txtyzrSoyadi);
		
		btnKaydet = new JButton("Kaydet");
		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(btnKaydet, "Kaydedildi");
				Integer yzno= Integer.parseInt(txtyzrNo.getText());
				String yzadi= txtyzrAdi.getText();
				String yzsoyadi= txtyzrSoyadi.getText();
				Yazar yazar=new Yazar();
				yazar.setYazar_No(yzno);
				yazar.setYazar_Adi(yzadi);
				yazar.setYazar_Soyadi(yzsoyadi);
				sql.YazarEkle(yazar);
				
				
			}
		});
		btnKaydet.setBounds(7, 142, 89, 42);
		yzrekle.add(btnKaydet);
		
		btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(btnSil, "Ýþlem Gerçekleþti!");
			Integer yzno= Integer.parseInt(txtyzrNo.getText());
			Yazar silyazar=new Yazar();
			silyazar.setYazar_No(yzno);
			sql.YazarSil(silyazar);
			
			}
		});
		btnSil.setBounds(103, 142, 89, 42);
		yzrekle.add(btnSil);
		
		btnGuncelle = new JButton("Güncelle");
		btnGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(btnGuncelle, "Ýþlem Gerçekleþti!");
				Integer yzno= Integer.parseInt(txtyzrNo.getText());
				String yzadi= txtyzrAdi.getText();
				String yzsoyadi= txtyzrSoyadi.getText();
				Yazar guncelleyazar=new Yazar();
				guncelleyazar.setYazar_No(yzno);
				guncelleyazar.setYazar_Adi(yzadi);
				guncelleyazar.setYazar_Soyadi(yzsoyadi);
				sql.YazarGuncelle(guncelleyazar);
			}
		});
		btnGuncelle.setBounds(199, 142, 89, 42);
		yzrekle.add(btnGuncelle);
		
		btnyzrListele = new JButton("Yazarlarý Listele");
		btnyzrListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				YazarListele();
			}
		});
		btnyzrListele.setBounds(7, 195, 278, 42);
		yzrekle.add(btnyzrListele);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Yazar Liste", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(302, 0, 355, 356);
		contentPane.add(scrollPane);
		
		yzrtable = new JTable();
		yzrtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int satýrindex=yzrtable.getSelectedRow();
				
				txtyzrNo.setText(String.valueOf(yazarmodel.getValueAt(satýrindex, 0)) );		
				txtyzrAdi.setText((String) yazarmodel.getValueAt(satýrindex, 1));
				txtyzrSoyadi.setText((String) yazarmodel.getValueAt(satýrindex, 2));
				
			}
		});
		yzrtable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Yazar No", "Yazar Adý", "Yazar Soyadý"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		yzrtable.getColumnModel().getColumn(1).setPreferredWidth(118);
		yzrtable.getColumnModel().getColumn(2).setPreferredWidth(154);
		scrollPane.setViewportView(yzrtable);
		
		
	}
	
	public void YazarListele(){
		
		yazarmodel =(DefaultTableModel) yzrtable.getModel();
		
		for (int i = 0; i < yazarliste.size(); i++) {
			Object o[]=new Object[3];
			o[0]=yazarliste.get(i).getYazar_No();
			o[1]=yazarliste.get(i).getYazar_Adi();
			o[2]=yazarliste.get(i).getYazar_Soyadi();
			
			yazarmodel.addRow(o);
		}
		
		
		
		
		
	}
}
