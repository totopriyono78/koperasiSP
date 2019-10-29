package com.koperasi.simpanpinjam;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class LapSimpanan extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6411946003946232233L;
	private JPanel contentPane;
	private JTable table;
	JComboBox<String> comboBoxJenisSimpanan;
	private Map<String, String> mapJenisSimpanan = new HashMap<String, String>();
	private int jenis_simpanan = 1;
	JLabel lblTotal;
	String total_simpanan;
	/**
	 * Launch the application.
	 */

	
	KoneksiDatabase koneksi = new KoneksiDatabase();		
	Connection mysql= koneksi.getConnection();

	/**
	 * Create the frame.
	 */

	public LapSimpanan() {
		setTitle("Laporan Simpanan");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 706, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLaporanSimpananAnggota = new JLabel("Laporan Simpanan Anggota");
		lblLaporanSimpananAnggota.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLaporanSimpananAnggota.setBounds(10, 14, 227, 33);
		contentPane.add(lblLaporanSimpananAnggota);
		
		JLabel lblJenisSimpanan = new JLabel("Jenis Simpanan");
		lblJenisSimpanan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblJenisSimpanan.setBounds(10, 111, 126, 14);
		contentPane.add(lblJenisSimpanan);
		
		comboBoxJenisSimpanan = new JComboBox<String>();
		comboBoxJenisSimpanan.setBounds(146, 107, 127, 22);
			try {	
			Statement stmt = mysql.createStatement();
			String sql="SELECT * FROM jenis_simpanan";
			ResultSet res = stmt.executeQuery(sql);	
			 while(res.next()){
				 comboBoxJenisSimpanan.addItem(res.getString("nama_jenis_simpanan"));
				 mapJenisSimpanan.put(res.getString("nama_jenis_simpanan"),res.getString("id_jenis_simpanan"));
				 mapJenisSimpanan.put(res.getString("nama_jenis_simpanan"),res.getString("id_jenis_simpanan"));	
			
			 }
			 res.close();
		}
		catch ( Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());					
		}		
		contentPane.add(comboBoxJenisSimpanan);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.GRAY);
		panel.setBorder(new LineBorder(Color.GRAY));
		panel.setBounds(10, 154, 670, 209);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 670, 204);
		panel.add(scrollPane);
		
		table = new JTable();
		
		ListSimpanan(jenis_simpanan);
		
		scrollPane.setViewportView(table);
		panel.add(scrollPane);
		
		JButton btnNewButton = new JButton("Show Report");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				jenis_simpanan= Integer.parseInt(mapJenisSimpanan.get(comboBoxJenisSimpanan.getSelectedItem()));
				ListSimpanan(jenis_simpanan);
				TotalSimpanan(jenis_simpanan);
				
			}
		});
		
		
		btnNewButton.setBounds(283, 107, 116, 23);
		contentPane.add(btnNewButton);
		
		lblTotal = new JLabel("Total");
		lblTotal.setBounds(10, 384, 336, 14);
		contentPane.add(lblTotal);
	}
	
	
	public void ListSimpanan(int jenis_simpanan){
		Object[]dataTable = new Object[5];
		String[] header_table = new String[] {"Tgl Transaksi","No Anggota","Nama","Jumlah(Rp)","Petugas"};
		DefaultTableModel model = new DefaultTableModel(null,header_table);

		try {
			
			Statement stmtSimpanan = mysql.createStatement();
			String sqlSimpanan="SELECT tgl_simpanan,nomor_anggota, nama_anggota, nama_jenis_simpanan, FORMAT(s.nilai_simpanan,0) as nilai_simpanan, nama_petugas "
					+ "FROM simpanan AS s "
					+ "JOIN anggota AS a ON a.id_anggota= s.id_anggota "
					+ "JOIN jenis_simpanan AS j ON j.id_jenis_simpanan=s.jenis_simpanan "
					+ "JOIN petugas AS p ON p.id_petugas=s.id_petugas where s.jenis_simpanan="+ jenis_simpanan +" order by s.id_simpanan desc";
						
			ResultSet resSimpanan = stmtSimpanan.executeQuery(sqlSimpanan);		
			resSimpanan.beforeFirst();			
			
			while(resSimpanan.next()) {				
				
				dataTable[0]=resSimpanan.getString("tgl_simpanan");
				dataTable[1]=resSimpanan.getString("nomor_anggota");
				dataTable[2]=resSimpanan.getString("nama_anggota");
				dataTable[3]=resSimpanan.getString("nilai_simpanan");
				dataTable[4]=resSimpanan.getString("nama_petugas");
				model.addRow(dataTable);

			}
						
		}catch (Exception ex ) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

		table.setModel(model);
		table.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
		table.setVisible(true);
		
		//lblTotal.setText(total_simpanan);
		
	}
	
	
	public void TotalSimpanan(int jenis_simpanan) {
		try {
			Statement stmtTotal = mysql.createStatement();			
			String sqlTotal ="SELECT FORMAT(SUM(simpanan.`nilai_simpanan`),0) as total FROM simpanan WHERE simpanan.`jenis_simpanan`="+jenis_simpanan;
			ResultSet resTotal = stmtTotal.executeQuery(sqlTotal);		
			resTotal.beforeFirst();			
			if(resTotal.next()) {
				total_simpanan = resTotal.getString("total");
			}		
		}catch (Exception ex ) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
				
		}

		lblTotal.setText("Total Simpanan : "+ total_simpanan);
	}
	
}
