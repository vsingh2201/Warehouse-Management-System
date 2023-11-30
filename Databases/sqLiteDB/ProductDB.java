package sqLiteDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import productModule.IProduct;

public class ProductDB {
	// List of products
	private static List<IProduct> productList;
	
	
	public static List<IProduct> getProductList() {
		return productList;
	}

	public static void setProductList(List<IProduct> productList) {
		ProductDB.productList = productList;
	}

	public static void connect() {
		Connection conn = null;
		try {
			// create a database connection
			conn = DriverManager.getConnection("jdbc:sqlite:/Users/vikramjeetsingh/DBBrowser/ProductDB.db");
			Statement statement = conn.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.

			System.out.println("Connection to SQLite has been established");
			// List of Products
			productList = new ArrayList<IProduct>();

			ResultSet rs = statement.executeQuery("select * from products");
			 while(rs.next())
		      {
		        // read the result set
				// Printing the result set
		        System.out.println("Product Id = " + rs.getInt("Id"));
		        System.out.println("Product Name = " + rs.getString("Name"));
		        System.out.println("Current Stock Quantity = " + rs.getInt("Current Stock Quantity"));
		        System.out.println("Unit Price= " + rs.getInt("Unit Price"));
		        System.out.println("Max Stock Quantity = " + rs.getInt("Max Stock Quantity"));
		        System.out.println("Restock Schedule= " + rs.getInt("Restock Schedule"));
		        System.out.println("Discount Strategy= " + rs.getString("Discount Strategy"));
		        
		        IProduct aProduct = new IProduct();
		        aProduct.setProductID(rs.getInt("Id"));
		        aProduct.setProductName(rs.getString("Name"));
		        
		        
		      }

		} catch(SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}

	}
	
	public static void main(String[] args) {
		connect();
	}

}