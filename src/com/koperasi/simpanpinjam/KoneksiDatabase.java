package com.koperasi.simpanpinjam;
import java.sql.*;

public class KoneksiDatabase {

	String dbn, url, pwd, usr;
	
	public KoneksiDatabase() {
		// TODO Auto-generated constructor stub	
		dbn ="db_oop";
		url="jdbc:mysql://localhost/"+dbn+"?autoReconnect=true&useSSL=false";
		usr="root";
		pwd="";
	}

	public Connection getConnection() {
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,usr,pwd);
			//System.out.println("Ok : Driver Ditemukan");
		}
		catch (ClassNotFoundException e) {
			System.out.println("Error : Driver Tidak ditemukan \n"+ e + "\n");
		}
		catch(SQLException e) {
			System.out.println("Error: Tidak Bisa Koneksi ke Database \n"+e+"\n");
		}
		return con;
		
	}
}
