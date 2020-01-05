package usergui;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DataBase.SqlVeriTabaniBag;
import Kutuphane.Kutuphane;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class KutuphaneForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtKuno;
	private JTextField txtKuAdi;
	private JTextField txtKuAdres;
	private JTable KutupTable;
	DefaultTableModel kutupmodel;
	SqlVeriTabaniBag sql=new SqlVeriTabaniBag();
	ArrayList<Kutuphane>kutupliste=new SqlVeriTabaniBag().KutuphaneListele();
	
	
	
	Object data[]=new Object[4];
	

	/**
	 * Create the frame.
	 */
	public KutuphaneForm() throws SQLException {
		setTitle("Kütüphane Ekle");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(653, 441);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Kütüphane Ekle", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 11, 270, 375);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblKNo = new JLabel("K. No:");
		lblKNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKNo.setBounds(10, 30, 79, 26);
		panel.add(lblKNo);

		JLabel lblKAd = new JLabel("K. Adý:");
		lblKAd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKAd.setBounds(10, 72, 79, 26);
		panel.add(lblKAd);

		JLabel lblKAdres = new JLabel("K. Adres:");
		lblKAdres.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKAdres.setBounds(10, 109, 79, 26);
		panel.add(lblKAdres);

		JLabel lblAklama = new JLabel("A\u00E7\u0131klama:");
		lblAklama.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAklama.setBounds(10, 146, 79, 26);
		panel.add(lblAklama);

		txtKuno = new JTextField();
		txtKuno.setBounds(99, 30, 72, 26);
		panel.add(txtKuno);
		txtKuno.setColumns(10);

		txtKuAdi = new JTextField();
		txtKuAdi.setColumns(10);
		txtKuAdi.setBounds(99, 72, 161, 26);
		panel.add(txtKuAdi);

		txtKuAdres = new JTextField();
		txtKuAdres.setColumns(10);
		txtKuAdres.setBounds(99, 109, 161, 26);
		panel.add(txtKuAdres);

		JTextArea txtKuAciklama = new JTextArea();
		txtKuAciklama.setBounds(99, 152, 161, 91);
		panel.add(txtKuAciklama);

		JButton btnKaydet = new JButton("Kaydet");
		btnKaydet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnKaydet, "ÝÞLEM GERÇEKLEÞTÝ :) ");
				String gelenKutuphaneAdi=txtKuAdi.getText(),gelenAciklama=txtKuAciklama.getText();

				String url="jdbc:sqlserver://localhost:1433;databaseName=Kutuphane;integratedSecurity=true;";
				String sorgu="insert into Kutuphane (Kutuphane_ismi,Aciklama) values('"+gelenKutuphaneAdi+"','"+gelenAciklama+"')";
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
		btnKaydet.setBounds(0, 275, 89, 37);
		panel.add(btnKaydet);

		JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnSil, "ÝÞLEM GERÇEKLEÞTÝ :) ");
				String gelenKutuphaneNo=txtKuno.getText();
				int donusumNo=Integer.parseInt(gelenKutuphaneNo);
				String url="jdbc:sqlserver://localhost:1433;databaseName=Kutuphane;integratedSecurity=true;";
				String sorgu="delete from Kutuphane where Kutuphane_No="+donusumNo+"";
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
		btnSil.setBounds(89, 275, 89, 37);
		panel.add(btnSil);

		JButton btnGüncelle = new JButton("G\u00FCncelle");
		btnGüncelle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnGüncelle, "ÝÞLEM GERÇEKLEÞTÝ :) ");
					String gelenKutuphaneNo=txtKuno.getText(),gelenKutuphaneAdi=txtKuAdi.getText(),gelenAciklama=txtKuAciklama.getText();
					int donusumNo=Integer.parseInt(gelenKutuphaneNo);
					String url="jdbc:sqlserver://localhost:1433;databaseName=Kutuphane;integratedSecurity=true;";
					String sorgu="update Kutuphane set Kutuphane_ismi='"+gelenKutuphaneAdi+"',Aciklama='"+gelenAciklama+"' where Kutuphane_No="+donusumNo+"";
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
		btnGüncelle.setBounds(178, 275, 89, 37);
		panel.add(btnGüncelle);
		
		JButton btnListele = new JButton("Listele");
		btnListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				KutuphaneListele();
			}
		});
		btnListele.setBounds(0, 327, 260, 37);
		panel.add(btnListele);

		JScrollPane panel_1 = new JScrollPane();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Kütüphane Listesi",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(274, 11, 361, 375);
		contentPane.add(panel_1);

		KutupTable = new JTable();
		KutupTable.addMouseListener(new MouseListener() {
			
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
				// TODO Auto-generated method stub
				int satirindex=KutupTable.getSelectedRow();
				txtKuno.setText(String.valueOf(kutupmodel.getValueAt(satirindex, 0)));
				txtKuAdi.setText((String)kutupmodel.getValueAt(satirindex, 1));
				txtKuAciklama.setText((String)kutupmodel.getValueAt(satirindex, 2));
				txtKuAdres.setText(String.valueOf(kutupmodel.getValueAt(satirindex, 3)));
			}
		});
		KutupTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No", "Adý", "Açýklama", "Adres No"
			}
		));
		KutupTable.getColumnModel().getColumn(0).setPreferredWidth(49);
		KutupTable.getColumnModel().getColumn(1).setPreferredWidth(144);
		KutupTable.getColumnModel().getColumn(2).setPreferredWidth(169);
		panel_1.setViewportView(KutupTable);
		}
		public void KutuphaneListele() {
			kutupmodel=(DefaultTableModel)KutupTable.getModel();
			for (int i = 0; i < kutupliste.size(); i++) {
				data[0]=kutupliste.get(i).getKutNo();
				data[1]=kutupliste.get(i).getKutuphaneAdi();
				data[2]=kutupliste.get(i).getKutAciklama();
				data[3]=kutupliste.get(i).getNo();
				kutupmodel.addRow(data);
			}
		}
	}
	

