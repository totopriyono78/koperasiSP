package com.koperasi.simpanpinjam;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JenisSimpanan extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6678033306702451327L;
	private JPanel contentPane;
	private JTextField tFJenisSimpanan;
	private JTable table;
	private String id_jenis_simpanan;
	
	KoneksiDatabase koneksi = new KoneksiDatabase();		
	Connection mysql= koneksi.getConnection();



	/**
	 * Create the frame.
	 */
	public JenisSimpanan() {
		setTitle("Jenis Simpanan");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 496, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//agar frame berada ditengah
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(10, 11, 460, 120);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Jenis Simpanan");
		lblNewLabel.setBounds(30, 32, 99, 14);
		panel.add(lblNewLabel);
		
		tFJenisSimpanan = new JTextField();
		tFJenisSimpanan.setBounds(130, 29, 254, 20);
		panel.add(tFJenisSimpanan);
		tFJenisSimpanan.setColumns(10);
		
		JButton btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddJenisSimpanan();
				listJenisSimpanan();
			}
		});
		btnTambah.setBounds(130, 73, 92, 23);
		panel.add(btnTambah);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateJenisSimpanan();
				listJenisSimpanan();
				
			}
		});
		btnEdit.setBounds(232, 73, 71, 23);
		panel.add(btnEdit);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DelJenisSimpanan(id_jenis_simpanan);
				listJenisSimpanan();
			}
		});
		btnHapus.setBounds(313, 73, 71, 23);
		panel.add(btnHapus);
				
								
		//create panel
		JPanel panelTable = new JPanel();
		panelTable.setBounds(10, 147, 460, 164);
		contentPane.add(panelTable);
		
		//create scrollpane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 6, 440, 192);
		panelTable.add(scrollPane);
		
		//create table
		table = new JTable();
		table.setBounds(10, 6, 440, 192);
		
		//attach scrollpane to panel and contentPane.
		scrollPane.setViewportView(table);
		
		table.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
		table.setVisible(true);

		listJenisSimpanan();

		
	}
	
	
	public void listJenisSimpanan() {

		
		//prepare header table
		Object[]dataTable = new Object[2];
		String[] header_table = new String[] {"ID","Nama Simpanan"};
		DefaultTableModel model = new DefaultTableModel(null,header_table);
		
		//select all data and listed to table
		try {
			
			Statement stmt = mysql.createStatement();
			String sql ="SELECT * FROM jenis_simpanan";
			
			ResultSet res = stmt.executeQuery(sql);
			res.beforeFirst();
			while( res.next()) {
				dataTable[0]= res.getString("id_jenis_simpanan");
				dataTable[1]= res.getString("nama_jenis_simpanan");
				model.addRow(dataTable);	
			} 
			
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		table.setModel(model);		

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int selectedRowIndex = table.getSelectedRow();								
				id_jenis_simpanan = model.getValueAt(selectedRowIndex, 0).toString();
				tFJenisSimpanan.setText(model.getValueAt(selectedRowIndex,1).toString());
				
			}
		});
	}
	
	
	public void AddJenisSimpanan() {
		
		try {
			Statement smt = mysql.createStatement();
			String sql = "INSERT INTO jenis_simpanan (nama_jenis_simpanan) VALUES ('"+tFJenisSimpanan.getText()+"')";
			smt.executeUpdate(sql);
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void DelJenisSimpanan(String id_jenis_simpanan) {
		try {
			Statement smt = mysql.createStatement();
			String sql = "DELETE FROM jenis_simpanan WHERE id_jenis_simpanan="+id_jenis_simpanan;
			smt.executeUpdate(sql);
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	
	public void UpdateJenisSimpanan() {
		try {
			Statement smt = mysql.createStatement();
			String sql = "UPDATE jenis_simpanan SET nama_jenis_simpanan='"+ tFJenisSimpanan.getText() +"' WHERE id_jenis_simpanan="+id_jenis_simpanan;
			smt.executeUpdate(sql);
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	
}
