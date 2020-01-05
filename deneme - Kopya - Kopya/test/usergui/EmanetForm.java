package usergui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import DataBase.SqlVeriTabaniBag;
import Kutuphane.Emanet;
import Kutuphane.Kategori;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class EmanetForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmNo;
	private JTextField txtISBN;
	private JTextField txtUyeNo;
	private JTextField txtKuNo;
	private JTable emanetTable;
	private DefaultTableModel tm;
SqlVeriTabaniBag sql=new SqlVeriTabaniBag();
ArrayList<Emanet>emList=new SqlVeriTabaniBag().EmanetListele();
Object data[]=new Object[6];
	
	/**
	 * Create the frame.
	 */
	public EmanetForm() {
		setTitle("Emanet Pencere");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize( 856, 507);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Emanet Ekle", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 336, 409);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblEmanetNo = new JLabel("Emanet no:");
		lblEmanetNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmanetNo.setBounds(10, 18, 95, 22);
		panel.add(lblEmanetNo);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIsbn.setBounds(10, 58, 95, 22);
		panel.add(lblIsbn);
		
		JLabel lblyeNo = new JLabel("\u00DCye No:");
		lblyeNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblyeNo.setBounds(10, 98, 95, 22);
		panel.add(lblyeNo);
		
		JLabel lblKtphaneNo = new JLabel("Kütüphane No :");
		lblKtphaneNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKtphaneNo.setBounds(10, 138, 95, 22);
		panel.add(lblKtphaneNo);
		
		JLabel lblVeridiiTarih = new JLabel("Verildiği Tarih :");
		lblVeridiiTarih.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVeridiiTarih.setBounds(10, 178, 95, 22);
		panel.add(lblVeridiiTarih);
		
		JLabel lblTeslimTarihi = new JLabel("Teslim Tarihi:");
		lblTeslimTarihi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTeslimTarihi.setBounds(10, 218, 95, 22);
		panel.add(lblTeslimTarihi);
		
		txtEmNo = new JTextField();
		txtEmNo.setBounds(135, 18, 129, 22);
		panel.add(txtEmNo);
		txtEmNo.setColumns(10);
		
		txtISBN = new JTextField();
		txtISBN.setColumns(10);
		txtISBN.setBounds(135, 59, 129, 22);
		panel.add(txtISBN);
		
		txtUyeNo = new JTextField();
		txtUyeNo.setColumns(10);
		txtUyeNo.setBounds(135, 99, 129, 22);
		panel.add(txtUyeNo);
		
		txtKuNo = new JTextField();
		txtKuNo.setColumns(10);
		txtKuNo.setBounds(135, 139, 129, 22);
		panel.add(txtKuNo);
		
		JDateChooser dateChsVerTar = new JDateChooser();
		dateChsVerTar.setBounds(135, 178, 129, 22);
		panel.add(dateChsVerTar);
		
		JDateChooser dateChsTeslimTar = new JDateChooser();
		dateChsTeslimTar.setBounds(135, 218, 129, 22);
		panel.add(dateChsTeslimTar);
		
		JButton btnKaydet = new JButton("Kaydet");
		btnKaydet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int gelenEmNo=Integer.parseInt(txtEmNo.getText()),gelenISBN=Integer.parseInt(txtISBN.getText()),gelenUyeNo=Integer.parseInt(txtUyeNo.getText()),gelenKutNo=Integer.parseInt(txtKuNo.getText());
				DateFormat df=new SimpleDateFormat("dd-MM-yyyy");
				String verildigiTarih=df.format(dateChsVerTar.getDate());
				String teslimTarihi=df.format(dateChsTeslimTar.getDate());
				String url="jdbc:sqlserver://localhost:1433;databaseName=Kutuphane;integratedSecurity=true;";
				String sorgu="insert into Emanet (Emanet_No,ISBN,Uye_No,Kutuphane_No,Emanet_Tarihi,Teslim_Tarihi) values("+gelenEmNo+","+gelenISBN+","+gelenUyeNo+","+gelenKutNo+","+verildigiTarih+","+teslimTarihi+")";
				try (Connection con=DriverManager.getConnection(url)){
					Statement st=(Statement) con.createStatement();
					ResultSet rs=st.executeQuery(sorgu);
					rs.close();
					con.close();
				} catch (Exception e2) {
					//e2.printStackTrace();
				}
				
			}
		});
		btnKaydet.setBounds(17, 279, 89, 36);
		panel.add(btnKaydet);
		
		JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int gelenEmNo=Integer.parseInt(txtEmNo.getText());
				String sorgu="delete from Emanet where Emanet_No="+gelenEmNo+"";
				String url="jdbc:sqlserver://localhost:1433;databaseName=Kutuphane;integratedSecurity=true;";
				try (Connection con=DriverManager.getConnection(url)){
					Statement st=(Statement) con.createStatement();
					ResultSet rs=st.executeQuery(sorgu);
					rs.close();
					con.close();
				} catch (Exception e2) {
					//e2.printStackTrace();
				}
			}
		});
		btnSil.setBounds(123, 279, 89, 36);
		panel.add(btnSil);
		
		JButton btnGncelle = new JButton("Güncelle");
		btnGncelle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int gelenEmNo=Integer.parseInt(txtEmNo.getText()),gelenISBN=Integer.parseInt(txtISBN.getText()),gelenUyeNo=Integer.parseInt(txtUyeNo.getText()),gelenKutNo=Integer.parseInt(txtKuNo.getText());
				DateFormat df=new SimpleDateFormat("dd-MM-yyyy");
				String verildigiTarih=df.format(dateChsVerTar.getDate());
				String teslimTarihi=df.format(dateChsTeslimTar.getDate());
				String url="jdbc:sqlserver://localhost:1433;databaseName=Kutuphane;integratedSecurity=true;";
				String sorgu="update Emanet set ISBN="+gelenISBN+",Uye_No="+gelenUyeNo+",Kutuphane_No="+gelenKutNo+",Emanet_Tarihi='"+verildigiTarih+"',Teslim_Tarihi='"+teslimTarihi+"'";
				try (Connection con=DriverManager.getConnection(url)){
					Statement st=(Statement) con.createStatement();
					ResultSet rs=st.executeQuery(sorgu);
					rs.close();
					con.close();
				} catch (Exception e2) {
					//e2.printStackTrace();
				}
				
			}
		});
		btnGncelle.setBounds(229, 279, 89, 36);
		panel.add(btnGncelle);
		
		
		
		JButton btnListele = new JButton("Listele");
		btnListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmanetListele();
			}
		});
		btnListele.setBounds(16, 326, 302, 72);
		panel.add(btnListele);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Emanet Listesi", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(346, 11, 492, 452);
		contentPane.add(scrollPane);
		
		emanetTable = new JTable();
		emanetTable.addMouseListener(new MouseListener() {
			
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
				int satirindex=emanetTable.getSelectedRow();
				txtEmNo.setText(String.valueOf(tm.getValueAt(satirindex,0)));
				txtISBN.setText((String)tm.getValueAt(satirindex, 1));
				txtUyeNo.setText(String.valueOf(tm.getValueAt(satirindex, 2)));
				txtKuNo.setText(String.valueOf(tm.getValueAt(satirindex, 3)));
				
			}
		});
		
		emanetTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No", "ISBN", "\u00DCye No", "K\u00FCt\u00FCphane No", "Verili\u015F Tarihi", "Teslim Tarihi"
			}
		));
		emanetTable.getColumnModel().getColumn(0).setPreferredWidth(47);
		emanetTable.getColumnModel().getColumn(1).setPreferredWidth(125);
		emanetTable.getColumnModel().getColumn(2).setPreferredWidth(64);
		emanetTable.getColumnModel().getColumn(3).setPreferredWidth(125);
		emanetTable.getColumnModel().getColumn(4).setPreferredWidth(112);
		emanetTable.getColumnModel().getColumn(5).setPreferredWidth(123);
		scrollPane.setViewportView(emanetTable);
	}
	public void EmanetListele() {
		tm=(DefaultTableModel)emanetTable.getModel();
		for (int i = 0; i < emList.size(); i++) {
			data[0]=emList.get(i).getEmanetNo();
			data[1]=emList.get(i).getISBN();
			data[2]=emList.get(i).getUyeNo();
			data[3]=emList.get(i).getKutuphaneNo();
			data[4]=emList.get(i).getEmanetTarihi();
			data[5]=emList.get(i).getTeslimTarihi();
			tm.addRow(data);
		}
	}
}
