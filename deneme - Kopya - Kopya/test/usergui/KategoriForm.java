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
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DataBase.SqlVeriTabaniBag;
import Kutuphane.Kategori;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class KategoriForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable kategoriTable;
	private JTextField txtktgNo;
	private JTextField txtktgAdi;
	private JButton btnKaydet;
	private JButton btnSil;
	private JButton btnGncelle;
	private DefaultTableModel katmodel;
	SqlVeriTabaniBag sql=new SqlVeriTabaniBag();
	ArrayList<Kategori>katListe=new SqlVeriTabaniBag().KategoriListele();
	
	
	Object kat[]=new Object[2];
	private JButton btnListele;

	
	/**
	 * Create the frame.
	 */
	public KategoriForm() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize( 501, 375);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Kategori ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 11, 244, 309);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNo = new JLabel("No:");
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNo.setBounds(10, 36, 82, 28);
		panel.add(lblNo);
		
		JLabel lblKategoriAd = new JLabel("Kategori Ad\u0131:");
		lblKategoriAd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKategoriAd.setBounds(10, 75, 82, 28);
		panel.add(lblKategoriAd);
		
		txtktgNo = new JTextField();
		txtktgNo.setBounds(102, 36, 122, 28);
		panel.add(txtktgNo);
		txtktgNo.setColumns(10);
		
		txtktgAdi = new JTextField();
		txtktgAdi.setColumns(10);
		txtktgAdi.setBounds(102, 75, 122, 28);
		panel.add(txtktgAdi);
		
		btnKaydet = new JButton("Kaydet");
btnKaydet.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(btnKaydet, "İŞLEM GERÇEKLEŞTİ :) ");
		// TODO Auto-generated method stub
		String gelenKatAdi=txtktgAdi.getText();
		int katno=Integer.parseInt(txtktgNo.getText());
		String url="jdbc:sqlserver://localhost:1433;databaseName=Kutuphane;integratedSecurity=true;";
		String sorgu="insert into Kategoriler(Kategori_No,Kategori_Adi) values("+katno+",'"+gelenKatAdi+"')";
		try (Connection con=DriverManager.getConnection(url)){
			Statement st=(Statement) con.createStatement();
			st.executeUpdate(sorgu);
			con.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
});
		btnKaydet.setBounds(18, 127, 89, 35);
		panel.add(btnKaydet);
		
		btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnSil, "İŞLEM GERÇEKLEŞTİ :) ");
				// TODO Auto-generated method stub
				int gelenKatNo=Integer.parseInt(txtktgNo.getText());
				String url="jdbc:sqlserver://localhost:1433;databaseName=Kutuphane;integratedSecurity=true;";
				String sorgu="delete from  Kategoriler where Kategori_No="+gelenKatNo+"";
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
		btnSil.setBounds(125, 127, 89, 35);
		panel.add(btnSil);
		
		btnGncelle = new JButton("G\u00FCncelle");
		btnGncelle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnGncelle, "İŞLEM GERÇEKLEŞTİ :) ");
				int gelenKatNo=Integer.parseInt(txtktgNo.getText());
				String gelenKatAdi=txtktgAdi.getText();
				String url="jdbc:sqlserver://localhost:1433;databaseName=Kutuphane;integratedSecurity=true;";
				String sorgu="update Kategoriler set Kategori_Adi='"+gelenKatAdi+"' where Kategori_No="+gelenKatNo+"";
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
		btnGncelle.setBounds(18, 173, 196, 51);
		panel.add(btnGncelle);
		
		btnListele = new JButton("Listele");
		btnListele.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(btnListele, "İŞLEM GERÇEKLEŞTİ :) ");
				KategoriListele();
				
			}
		});
		btnListele.setBounds(18, 247, 196, 51);
		panel.add(btnListele);
		
		JScrollPane panel_1 = new JScrollPane();
		panel_1.setBorder(new TitledBorder(null, "Kategori Liste", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(244, 11, 229, 309);
		contentPane.add(panel_1);
		
		kategoriTable = new JTable();
		kategoriTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int satirindex=kategoriTable.getSelectedRow();
				txtktgNo.setText(String.valueOf(kategoriTable.getValueAt(satirindex, 0)));
				txtktgAdi.setText((String)kategoriTable.getValueAt(satirindex, 1));
				
				
			}
		});
		kategoriTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No", "Kategori Ad\u0131"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		kategoriTable.getColumnModel().getColumn(0).setPreferredWidth(63);
		kategoriTable.getColumnModel().getColumn(1).setPreferredWidth(154);
		panel_1.setViewportView(kategoriTable);
	}
	public void KategoriListele() {
		katmodel=(DefaultTableModel)kategoriTable.getModel();
		for (int i = 0; i < katListe.size(); i++) {
			kat[0]=katListe.get(i).getKategoriNo();
			kat[1]=katListe.get(i).getKategoriAdi();
			katmodel.addRow(kat);
		}
	}
}
