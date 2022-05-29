import java.sql.*;

public class Connect {
	
	Connection connect;
	
	ResultSet rs;
	PreparedStatement ps;
	
	public Connect() {
		try {
			String url = "jdbc:mysql://localhost:3306/menu";
			String username = "root";
			String password = "";
			
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url, username, password);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	public ResultSet getMenu() {
		
		try {
			ps = connect.prepareStatement("select * from menu");
			rs = ps.executeQuery();
		} catch (Exception e) {
			
		}
		
		return rs;
	}
	
	public ResultSet insertMenu(int id, String kode, String nama, String harga, String stok) {
		
		try {
			ps = connect.prepareStatement("insert into menu (`id`, `kode`, `nama`, `harga`, `stok`) values (?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, kode);
			ps.setString(3, nama);
			ps.setString(4, harga);
			ps.setString(5, stok);
			ps.execute();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return rs;
	}
	
	public ResultSet deleteMenu(String id) {
		
		try {
			ps = connect.prepareStatement("delete from menu where `id` = (?)");
			ps.setString(1, id);
			ps.execute();
		} catch (Exception e) {
			
		}
		
		return rs;
	}
	
	public ResultSet updateMahasiswa (String id, String kode, String nama, String harga, String stok) {
		
		try {
			ps = connect.prepareStatement("Update menu set kode = ? , nama = ?, harga = ?, stok = ? where id = ?");
			ps.setString(1, kode);
			ps.setString(2, nama);
			ps.setString(3, harga);
			ps.setString(4, stok);
			ps.setString(5, id);
			
			ps.execute();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return rs;
	}
	
}
