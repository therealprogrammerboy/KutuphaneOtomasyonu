package usergui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.util.Locale;

public class frmAdresEkle extends JFrame {

	private JPanel contentPane;
	private JTextField txtCadde;
	private JTextField txtMahalle;
	private JTextField txtBina;
	private JTextField txtSehir;
	private JTextField txtPosta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmAdresEkle frame = new frmAdresEkle();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmAdresEkle() {
		setLocale(new Locale("tr", "TR"));
		setTitle("Adres Ekle");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 358, 203);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Adres Ekleme", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 414, 150);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadde :");
		lblNewLabel.setBounds(26, 24, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mahalle :");
		lblNewLabel_1.setBounds(26, 49, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Bina No :");
		lblNewLabel_2.setBounds(26, 74, 46, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u015Eehir :");
		lblNewLabel_3.setBounds(38, 99, 46, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Posta Kodu :");
		lblNewLabel_4.setBounds(10, 124, 74, 14);
		panel.add(lblNewLabel_4);
		
		txtCadde = new JTextField();
		txtCadde.setBounds(82, 21, 86, 20);
		panel.add(txtCadde);
		txtCadde.setColumns(10);
		
		txtMahalle = new JTextField();
		txtMahalle.setBounds(82, 46, 86, 20);
		panel.add(txtMahalle);
		txtMahalle.setColumns(10);
		
		txtBina = new JTextField();
		txtBina.setBounds(82, 71, 86, 20);
		panel.add(txtBina);
		txtBina.setColumns(10);
		
		txtSehir = new JTextField();
		txtSehir.setBounds(82, 96, 86, 20);
		panel.add(txtSehir);
		txtSehir.setColumns(10);
		
		txtPosta = new JTextField();
		txtPosta.setBounds(82, 121, 86, 20);
		panel.add(txtPosta);
		txtPosta.setColumns(10);
		
		JButton btnNewButton = new JButton("Ekle");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String cadde=txtCadde.getText(),mah=txtMahalle.getText(),no=txtBina.getText(),sehir=txtSehir.getText();
				int kod=Integer.parseInt(txtPosta.getText());
				String url="jdbc:sqlserver://localhost:1433;databaseName=Kutuphane;integratedSecurity=true;";
				String sorgu="insert into Adresler(Cadde,Mahalle,Bina_No,Sehir,Posta_Kodu)values('"+cadde+"','"+mah+"','"+no+"','"+sehir+"',"+kod+")";
				try (Connection con=DriverManager.getConnection(url)){
					Statement st=(Statement) con.createStatement();
					ResultSet rs=st.executeQuery(sorgu);
					rs.close();
					con.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(206, 70, 89, 23);
		panel.add(btnNewButton);
	}
}
