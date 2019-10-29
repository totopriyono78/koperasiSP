package com.koperasi.simpanpinjam;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Angsuran extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNoAnggota;
	private JTextField textFieldNamaAnggota;
	private JTextField textFieldTenor;
	private JLabel lblIdAnggota;
	private String id_pinjaman;
	private JPanel panel;
	private JScrollPane scrollPane;
	private String id_Anggota;
	private String id_angsuran;
	private int angsuran_ke;

	
	
	private JTable tableAngsuran;

	KoneksiDatabase koneksi = new KoneksiDatabase();		
	Connection mysql= koneksi.getConnection();
	
	private JTextField textFieldJumlah;
	private JTextField textFieldBunga;
	private JTextField textFieldTglPinjam;
	private JTextField textFieldJumlahAngsuran;
	private JTextField textFieldAngsuranKe;
	private JTextField textFieldStatus;
	private JTextField textFieldPinjamanBunga;


	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Angsuran frame = new Angsuran();
		frame.setVisible(true);
		
	}*/

	
	/*****************************************************
	 * Create the frame layout.
	 * 
	 *****************************************************/

	public Angsuran() {
		
		setTitle("Transaksi Angsuran");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 776, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//agar frame berada ditengah
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		panel = new JPanel();
		panel.setBounds(10, 238, 747, 243);
		contentPane.add(panel);
		panel.setLayout(null);		
	
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 728, 247);
		tableAngsuran = new JTable();
		//ListPinjaman();
		scrollPane.setViewportView(tableAngsuran);		
		panel.add(scrollPane);
	
		JLabel lblNoAnggota = new JLabel("No Anggota");
		lblNoAnggota.setBounds(27, 31, 88, 14);
		contentPane.add(lblNoAnggota);
		
		lblIdAnggota = new JLabel();
		contentPane.add(lblIdAnggota);
		
		textFieldNoAnggota = new JTextField();
		textFieldNoAnggota.setBounds(135, 29, 110, 20);
		contentPane.add(textFieldNoAnggota);
		textFieldNoAnggota.setColumns(10);
		
		JButton btnCari = new JButton("Cari");
		btnCari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				try {
					
					Statement stmt = mysql.createStatement();
					String sql="SELECT *, format(jumlah_angsuran,0) as jumlah_angsuran_format, "
							+ "format(jumlah_pinjaman,0) as jumlah_pinjaman_format, "
							+ "format(total_pinjaman_bunga,0) as total_pinjaman_bunga_format, date_format(tgl_pinjaman,'%d %M %Y %H:%i:%s') as tgl_pinjaman_format "
							+ "FROM pinjaman JOIN anggota ON anggota.`id_anggota`=pinjaman.`id_anggota` "
							+ "WHERE anggota.`nomor_anggota`='"+textFieldNoAnggota.getText()+"'";
					
					String sql2="SELECT count(*) as angsuran_ke FROM angsuran JOIN pinjaman ON angsuran.`id_pinjaman`=pinjaman.`id_pinjaman` JOIN anggota ON pinjaman.`id_anggota`=anggota.`id_anggota` JOIN petugas ON angsuran.id_petugas=petugas.id_petugas WHERE anggota.`nomor_anggota`='"+textFieldNoAnggota.getText()+"'";
					
					ResultSet res2 = stmt.executeQuery(sql2);	
					if(res2.next()) {
						angsuran_ke =Integer.parseInt(res2.getString("angsuran_ke"))+1;
					}

					ResultSet res = stmt.executeQuery(sql);	
					
					if ( res.next()) {
						textFieldNamaAnggota.setText(res.getString("nama_anggota"));
						textFieldJumlah.setText(res.getString("jumlah_pinjaman_format"));
						textFieldTenor.setText(res.getString("tenor_pinjaman"));
						textFieldBunga.setText(res.getString("bunga_pinjaman"));
						textFieldStatus.setText(res.getString("status_pinjaman"));
						textFieldTglPinjam.setText(res.getString("tgl_pinjaman_format"));
						textFieldJumlahAngsuran.setText(res.getString("jumlah_angsuran"));
						textFieldAngsuranKe.setText(Integer.toString(angsuran_ke));
						textFieldPinjamanBunga.setText(res.getString("total_pinjaman_bunga_format"));
						
						id_Anggota = res.getString("id_anggota");
						id_pinjaman = res.getString("id_pinjaman");
						
						ListAngsuran(id_Anggota);
						
						
					}else {
						JOptionPane.showMessageDialog(null, "Data Anggota Tidak Memiliki Pinjaman !");
						Clear();
					}
					
				}
				catch ( Exception ex ){
				
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}				
			}
		});
		
		btnCari.setBounds(253, 28, 89, 23);
		contentPane.add(btnCari);
		
		JLabel lblNama = new JLabel("Nama Anggota");
		lblNama.setBounds(27, 77, 95, 14);
		contentPane.add(lblNama);
		
		JLabel lblJumlah = new JLabel("Jumlah Pinjaman");
		lblJumlah.setBounds(27, 102, 110, 14);
		contentPane.add(lblJumlah);
		
		JLabel lblTenor = new JLabel("Tenor");
		lblTenor.setBounds(440, 101, 48, 14);
		contentPane.add(lblTenor);
		
		textFieldNamaAnggota = new JTextField();
		textFieldNamaAnggota.setEditable(false);
		textFieldNamaAnggota.setBounds(135, 75, 207, 20);
		contentPane.add(textFieldNamaAnggota);
		textFieldNamaAnggota.setColumns(10);
		
		textFieldTenor = new JTextField();
		textFieldTenor.setEditable(false);
		textFieldTenor.setBounds(527, 99, 48, 20);
		contentPane.add(textFieldTenor);
		textFieldTenor.setColumns(10);
		
		JButton btnTambah = new JButton("Bayarkan");
		btnTambah.setBounds(135, 204, 95, 23);
		contentPane.add(btnTambah);
		
		// clear form
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clear();
			}
		});
		btnClear.setBounds(346, 204, 89, 23);
		contentPane.add(btnClear);

		
		// add data handler
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDataAngsuran();
			}
		});
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteDataSimpanan();
			}
		});
		btnHapus.setBounds(244, 204, 89, 23);
		contentPane.add(btnHapus);
		
		textFieldJumlah = new JTextField();
		textFieldJumlah.setEditable(false);
		textFieldJumlah.setBounds(135, 99, 207, 20);
		contentPane.add(textFieldJumlah);
		textFieldJumlah.setColumns(10);
		
		JLabel lblBunga = new JLabel("Bunga");
		lblBunga.setBounds(440, 127, 48, 14);
		contentPane.add(lblBunga);
		
		textFieldBunga = new JTextField();
		textFieldBunga.setEditable(false);
		textFieldBunga.setBounds(527, 124, 48, 20);
		contentPane.add(textFieldBunga);
		textFieldBunga.setColumns(10);
		
		JLabel lblBulan = new JLabel("Bulan");
		lblBulan.setBounds(587, 105, 48, 14);
		contentPane.add(lblBulan);
		
		JLabel label = new JLabel("%");
		label.setBounds(585, 130, 48, 14);
		contentPane.add(label);
		
		JLabel lblTglPinjam = new JLabel("Tgl Pinjam");
		lblTglPinjam.setBounds(440, 78, 77, 14);
		contentPane.add(lblTglPinjam);
		
		JLabel lblJumlahAngsuran = new JLabel("Jumlah Angsuran");
		lblJumlahAngsuran.setBounds(27, 176, 119, 14);
		contentPane.add(lblJumlahAngsuran);
		
		textFieldTglPinjam = new JTextField();
		textFieldTglPinjam.setEditable(false);
		textFieldTglPinjam.setBounds(527, 75, 160, 20);
		contentPane.add(textFieldTglPinjam);
		textFieldTglPinjam.setColumns(10);
		
		textFieldJumlahAngsuran = new JTextField();
		textFieldJumlahAngsuran.setBounds(135, 173, 96, 20);
		contentPane.add(textFieldJumlahAngsuran);
		textFieldJumlahAngsuran.setColumns(10);
		
		JLabel lblAngsuranKe = new JLabel("Angsuran Ke -");
		lblAngsuranKe.setBounds(27, 151, 88, 14);
		contentPane.add(lblAngsuranKe);
		
		textFieldAngsuranKe = new JTextField();
		textFieldAngsuranKe.setEditable(false);
		textFieldAngsuranKe.setBounds(135, 148, 32, 20);
		contentPane.add(textFieldAngsuranKe);
		textFieldAngsuranKe.setColumns(10);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(440, 151, 48, 14);
		contentPane.add(lblStatus);
		
		textFieldStatus = new JTextField();
		textFieldStatus.setEditable(false);
		textFieldStatus.setBounds(527, 148, 160, 20);
		contentPane.add(textFieldStatus);
		textFieldStatus.setColumns(10);
		
		JLabel lblPinjamanBunga = new JLabel("Pinjaman + Bunga");
		lblPinjamanBunga.setBounds(27, 127, 110, 14);
		contentPane.add(lblPinjamanBunga);
		
		textFieldPinjamanBunga = new JTextField();
		textFieldPinjamanBunga.setEditable(false);
		textFieldPinjamanBunga.setBounds(135, 124, 207, 20);
		contentPane.add(textFieldPinjamanBunga);
		textFieldPinjamanBunga.setColumns(10);

		
	}
	

	/*****************************************************
	 * Clear Form Data handler
	 * 
	 *****************************************************/

	public void Clear() {
		textFieldTenor.setText("");
		textFieldNoAnggota.setText("");
		textFieldNamaAnggota.setText("");
		textFieldJumlah.setText("");
		textFieldBunga.setText("");
		textFieldAngsuranKe.setText("");
		textFieldStatus.setText("");
		textFieldJumlahAngsuran.setText("");
		textFieldTglPinjam.setText("");
		textFieldPinjamanBunga.setText("");
		ListAngsuran("0");
	}
	
	
	/*****************************************************
	 * Delete Data handler
	 * 
	 *****************************************************/
	
	public void DeleteDataSimpanan() {
		try {
			
			Statement stmt = mysql.createStatement();
			String sqldelete="DELETE FROM angsuran WHERE id_angsuran=" + id_angsuran;
			int i = stmt.executeUpdate(sqldelete);
			
			if(i==1) {
				JOptionPane.showMessageDialog(null,"Data Angsuran berhasil dihapus !");
				ListAngsuran(id_Anggota);
			}else {
				JOptionPane.showMessageDialog(null,"Tidak ada data yang berhasil dihapus!");
			}	
		}
		catch ( Exception ex ){				
			JOptionPane.showMessageDialog(null, ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	
	/*****************************************************
	 * Add Data handler
	 * 
	 *****************************************************/

	public void AddDataAngsuran() {
	
		String id_Petugas;
		try {
			
			Statement stmt = mysql.createStatement();
			String sqlIdPetugas="SELECT * FROM petugas WHERE is_login=1";
			ResultSet resIdPetugas = stmt.executeQuery(sqlIdPetugas);
			
			if (resIdPetugas.next()) {
				id_Petugas= resIdPetugas.getString("id_petugas");
				
			}else {
				id_Petugas="1";
			}
			
			if ( id_Anggota.length()>0 ) {
				//float jumlah_angsuran =0;
								
				float jumlah_angsuran = Float.valueOf(textFieldJumlahAngsuran.getText()).floatValue();
				String sqlInsert ="INSERT INTO angsuran (`tgl_angsuran`, `id_pinjaman`, `nilai_angsuran`, `id_petugas`) VALUES (NOW(), '"+id_pinjaman+"', '"+jumlah_angsuran+"', '"+id_Petugas+"')";
				
				System.out.println(sqlInsert);
				
				if(stmt.executeUpdate(sqlInsert)>0) {
					JOptionPane.showMessageDialog(null,"Data berhasil disimpan");
					ListAngsuran(id_Anggota);
				
				}else {
					JOptionPane.showMessageDialog(null, "Gagal menyimpan");
				}
			}
		}
		catch ( Exception ex ){
		
			JOptionPane.showMessageDialog(null, ex.getMessage());
			ex.printStackTrace();
		}
	}
	

	/*****************************************************
	 * List Data handler
	 * 
	 *****************************************************/

	public void ListAngsuran(String id_Anggota) {
		
		tableAngsuran.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) tableAngsuran.getModel();
				int selectedRowIndex = tableAngsuran.getSelectedRow();								
				id_angsuran = model.getValueAt(selectedRowIndex, 0).toString();
				textFieldAngsuranKe.setText(model.getValueAt(selectedRowIndex,1).toString());
				
			}
		});

		Object[]dataTable = new Object[6];
		String[] header_table = new String[] {"ID","Ang. ke", "Tgl angsuran","Jumlah Angsuran","Sisa Pinjaman","Petugas"};
		DefaultTableModel model = new DefaultTableModel(null,header_table);

		try {
			
			Statement stmtAngsuran = mysql.createStatement();
			String sqlAngsuran="SELECT id_angsuran, date_format(tgl_angsuran,'%d/%m/%Y %H:%i:%s') as tgl_angsuran, format(jumlah_angsuran,0) as jumlah_angsuran_format, "
					+ "jumlah_angsuran, format(total_pinjaman_bunga,0) as total_pinjaman_bunga_format,"
					+ "total_pinjaman_bunga, nama_petugas FROM angsuran JOIN pinjaman ON angsuran.`id_pinjaman`=pinjaman.`id_pinjaman` JOIN anggota ON pinjaman.`id_anggota`=anggota.`id_anggota` JOIN petugas ON angsuran.id_petugas=petugas.id_petugas WHERE anggota.`id_anggota`="+id_Anggota+" ORDER By angsuran.tgl_angsuran ASC";
			ResultSet resAngsuran = stmtAngsuran.executeQuery(sqlAngsuran);		
			
			System.out.println(sqlAngsuran);
			resAngsuran.beforeFirst();			
			int angsuranKe=1;
			
			while(resAngsuran.next()) {	
				String pattern = "###,###.###";
				DecimalFormat decimalFormat = new DecimalFormat(pattern);
				
				dataTable[0]= Integer.parseInt(resAngsuran.getString("id_angsuran"));
				dataTable[1]= angsuranKe;
				dataTable[2]= resAngsuran.getString("tgl_angsuran");
				dataTable[3]= resAngsuran.getString("jumlah_angsuran_format");
				dataTable[4]= decimalFormat.format(Float.valueOf((resAngsuran.getString("total_pinjaman_bunga")))-angsuranKe*Float.valueOf((resAngsuran.getString("jumlah_angsuran"))));
				dataTable[5]=resAngsuran.getString("nama_petugas");
				model.addRow(dataTable);
				angsuranKe++;
			}
		}catch (Exception ex ) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

		tableAngsuran.setModel(model);		
		tableAngsuran.getColumnModel().getColumn(1).setPreferredWidth(10);
				
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		tableAngsuran.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		tableAngsuran.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		tableAngsuran.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		
		tableAngsuran.setAutoCreateRowSorter(false);
		tableAngsuran.removeColumn(tableAngsuran.getColumnModel().getColumn(0));		
		tableAngsuran.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
		tableAngsuran.setVisible(true);
		

	}
}