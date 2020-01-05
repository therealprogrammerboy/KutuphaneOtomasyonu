package usergui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.awt.event.ActionEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;




public class AnaEkran extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private Dimension btnsize=new Dimension(140, 90);
	private JButton btnYazarEkle,btnKitapEkle,btnUyeEkle,btnKuEkle,
	btnEmanetEkle,btnKategoriEkle;
	private JButton btnAdresEkle;
	private JMenuBar menuBar;
	private JMenu mnYardm;
	private JMenuItem mnýtmHakknda;
	private JMenu mnRaporlar;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnaEkran frame = new AnaEkran();
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
	public AnaEkran() {
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Kütüphane Otomasyonu");
		//setBounds(100, 100, 450, 300);
		setSize(535,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnYazarEkle = new JButton("Yazar Ekle");
		btnYazarEkle.setBounds(34, 15, 140, 90);
		btnYazarEkle.setActionCommand("yazarekle");
		btnYazarEkle.setPreferredSize(btnsize);
		btnYazarEkle.addActionListener(btnListener);
		getContentPane().setLayout(null);
		getContentPane().add(btnYazarEkle);
		
		btnKitapEkle = new JButton("Kitap Ekle");
		btnKitapEkle.setBounds(194, 15, 140, 90);
		btnKitapEkle.setActionCommand("kitapekle");
		btnKitapEkle.addActionListener(btnListener);
		btnKitapEkle.setPreferredSize(btnsize);
		getContentPane().add(btnKitapEkle);
		
		btnUyeEkle = new JButton("Uye Ekle");
		btnUyeEkle.setBounds(354, 15, 140, 90);
		btnUyeEkle.setPreferredSize(btnsize);
		btnUyeEkle.setActionCommand("uyeekle");
		btnUyeEkle.addActionListener(btnListener);
		getContentPane().add(btnUyeEkle);
		
		btnKuEkle = new JButton("Kütüphane Ekle");
		btnKuEkle.setBounds(34, 120, 140, 90);
		btnKuEkle.setActionCommand("kuekle");
		btnKuEkle.addActionListener(btnListener);
		btnKuEkle.setPreferredSize(btnsize);
		getContentPane().add(btnKuEkle);
		
		btnKategoriEkle = new JButton("Kategori Ekle");
		btnKategoriEkle.setBounds(194, 120, 140, 90);
		btnKategoriEkle.setPreferredSize(new Dimension(140, 90));
		btnKategoriEkle.setActionCommand("ktgekle");
		btnKategoriEkle.addActionListener(btnListener);
		getContentPane().add(btnKategoriEkle);
		
		
		btnEmanetEkle = new JButton("Emanet Ekle");
		btnEmanetEkle.setBounds(354, 120, 140, 90);
		btnEmanetEkle.setPreferredSize(new Dimension(140, 90));
		btnEmanetEkle.setActionCommand("emanetekle");
		btnEmanetEkle.addActionListener(btnListener);
		getContentPane().add(btnEmanetEkle);
		
		btnAdresEkle = new JButton("Adres Ekle");
		btnAdresEkle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
              frmAdresEkle yeni=new frmAdresEkle();
              yeni.setVisible(true);
				
			}
		});
		btnAdresEkle.setActionCommand("adresEkle");
		btnAdresEkle.setBounds(34, 221, 460, 90);
		getContentPane().add(btnAdresEkle);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnRaporlar = new JMenu("Raporlar");
		mnRaporlar.setHorizontalAlignment(SwingConstants.TRAILING);
		menuBar.add(mnRaporlar);
		
		mnYardm = new JMenu("Yard\u0131m");
		menuBar.add(mnYardm);
		
		mnýtmHakknda = new JMenuItem("Hakk\u0131nda");
		mnYardm.add(mnýtmHakknda);
				
		
		

	}
	
	private ActionListener btnListener=new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//JOptionPane.showMessageDialog(rootPane, "Ömer Akincir 181809029\nErol Bilen 181809013\nTAKLÝTLER ASLINI YAÞATIR!");
			
			if (e.getActionCommand()=="yazarekle") {
				Yazarform frmyzrekle=new Yazarform();
				frmyzrekle.setVisible(true);
			} else if (e.getActionCommand()=="kitapekle") {
				KitapForm frmkitapekle=new KitapForm();
				frmkitapekle.setVisible(true);

			}
			else if (e.getActionCommand()=="emanetekle") {
				EmanetForm frmemEkle=new EmanetForm();
				frmemEkle.setVisible(true);

			}
			else if (e.getActionCommand()=="ktgekle") {
				KategoriForm frmktgEkle = null;
				frmktgEkle = new KategoriForm();
			
				frmktgEkle.setVisible(true);
			
			}else if (e.getActionCommand()=="kuekle") {
				KutuphaneForm frmkuEkle = null;
				try {
					frmkuEkle = new KutuphaneForm();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frmkuEkle.setVisible(true);
			
			}else if (e.getActionCommand()=="kuekle") {
				KutuphaneForm frmkuEkle = null;
				try {
					frmkuEkle = new KutuphaneForm();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frmkuEkle.setVisible(true);
			
			}else if (e.getActionCommand()=="uyeekle") {
				
				UyeForm frmUyeEkle = null;
				try {
					frmUyeEkle = new UyeForm();
					JOptionPane.showMessageDialog(btnUyeEkle, "LÜTFEN PROGRAMIN SORUNSUZ ÇALIÞMASI ÝÇÝN ÜYE EKLEMEDEN ÖNCE ADRESÝNÝ EKLEYÝNÝZ!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frmUyeEkle.setVisible(true);
			}
			
		}
	
	};	
	
	
	
}