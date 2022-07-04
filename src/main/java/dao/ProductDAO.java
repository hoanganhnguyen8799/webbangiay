package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import impl_services.IProduct;
import model.Product;

public class ProductDAO extends DBConnect implements IProduct {
	@Override
	public List<Product> getProductPaging(int inex) {
		List<Product> list = new ArrayList<Product>();
		String sql = "select * from product order by id\r\n"
				+ "	offset ? rows\r\n"
				+ "	fetch first 9 rows only";
		try {
			connect = new DBConnect().getConnection();
			
			pres = connect.prepareStatement(sql);
			
			pres.setInt(1, inex);
			
			res = pres.executeQuery();
			
			while(res.next()) {
				Product product = new Product(res.getInt(1), res.getString(2), res.getString(3), res.getDouble(4), res.getString(5),
						res.getString(6));
				list.add(product);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally
		 {
			try {
				if (res!=null)
					res.close();
				if(pres!=null)
					pres.close();
				if(connect!=null)
					connect.close();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}  
		 }
		if(list.isEmpty())
			return null;
		else	
			return list;
	}
	@Override
	public int pageNumber() {
		String sql = "select count(*) from product";
		try {
			connect = new DBConnect().getConnection();
			
			pres = connect.prepareStatement(sql);
						
			res = pres.executeQuery();
			
			if(res.next()) {
				int count = res.getInt(1);
				int pageNumber = count/9;
				if(pageNumber%9 !=0) {
					pageNumber++;
				}
				return pageNumber;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally
		 {
			try {
				if (res!=null)
					res.close();
				if(pres!=null)
					pres.close();
				if(connect!=null)
					connect.close();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}  
		 }
		return 0;
	}
	public List<Product> getAllProducts() {
		List<Product> list = new ArrayList<Product>();
		String sql = "select * from product";
		try {
			connect = new DBConnect().getConnection();
			pres = connect.prepareStatement(sql);
			res = pres.executeQuery();
			Product product = null;
			while (res.next()) {
				
				product = new Product(res.getInt(1), res.getString(2), res.getString(3), res.getDouble(4), res.getString(5),
						res.getString(6));
				list.add(product);
			}

		} catch (Exception e) {
			System.out.println("Error get all products");
			e.printStackTrace();
		}
		if (connect != null) {
			try {
				res.close();
				pres.close();
				connect.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return list;

	}
	
	public Product getLastProduct() {
		Product p = null;

		String sql = "select *  from product\r\n" + "	where\r\n" + "		id = (select max(id) from product)";

		try {
			connect = new DBConnect().getConnection();
			pres = connect.prepareStatement(sql);
			res = pres.executeQuery();
			if (res.next()) {
				p = new Product(res.getInt(1), res.getString(2), res.getString(3), res.getDouble(4), res.getString(5),
						res.getString(6));
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally
		 {
			try {
				if (res!=null)
					res.close();
				if(pres!=null)
					pres.close();
				if(connect!=null)
					connect.close();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}  
		 }
		return p;
	}
	public List<Product> getProductByCategory(String category) {
		List<Product> list = new ArrayList<Product>();

		String sql = "select * from product where cateID = ?";

		try {

			connect = new DBConnect().getConnection();
			pres = connect.prepareStatement(sql);
			pres.setString(1, category);
			res = pres.executeQuery();

			while (res.next()) {

				list.add(new Product(res.getInt(1), res.getString(2), res.getString(3), res.getDouble(4), res.getString(5),
						res.getString(6)));

			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally
		 {
			try {
				if (res!=null)
					res.close();
				if(pres!=null)
					pres.close();
				if(connect!=null)
					connect.close();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}  
		 }
		return list;
	}
	public Product getProductById(String id) {

		String sql = "select * from product where id = ?";

		try {

			connect = new DBConnect().getConnection();
			pres = connect.prepareStatement(sql);
			pres.setString(1, id);
			res = pres.executeQuery();

			if (res.next()) {
				return new Product(res.getInt(1), res.getString(2), res.getString(3), res.getDouble(4), res.getString(5),
						res.getString(6),res.getInt(7),res.getInt(8));
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally
		 {
			try {
				if (res!=null)
					res.close();
				if(pres!=null)
					pres.close();
				if(connect!=null)
					connect.close();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}  
		 }

		return null;
	}
	public static void main(String[] args) {
		List<Product> list = new ProductDAO().getSellProduct(20);
		System.out.println(list);
		for (Product product : list) {
			System.out.println(product);
		}
	}
	@Override
	public List<Product> getSellProduct(int accountID) {
		List<Product> list = new ArrayList<Product>();
		
		String sql="select * from product where sell_ID= ?";
		try {
			connect = getConnection();
			
			pres = connect.prepareStatement(sql);
			
			pres.setInt(1, accountID);
			
			res = pres.executeQuery();
			
			while(res.next()) {
				list.add(new Product(res.getInt(1), res.getString(2), res.getString(3), res.getDouble(4), res.getString(5),
						res.getString(6)));
			}
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally
		 {
			try {
				if (res!=null)
					res.close();
				if(pres!=null)
					pres.close();
				if(connect!=null)
					connect.close();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}  
		 }
		
		if(list.isEmpty())
			return null;
		else
			return list;
	}
	@Override
	public void addNewProductSell(Product product) {
		String sql = "insert into product(name,image,price,title,description,cateID,sell_ID) values (?,?,?,?,?,?,?)";
		
		try {

			connect = new DBConnect().getConnection();
			pres = connect.prepareStatement(sql);
			pres.setString(1, product.getName());
			pres.setString(2, product.getImage());
			pres.setDouble(3, product.getPrice());
			pres.setString(4, product.getTitle());
			pres.setString(5, product.getDescription());
			pres.setInt(6, product.getCateID());
			pres.setInt(7, product.getSell_ID());
			
			pres.executeUpdate();

			

		} catch (Exception e) {

			e.printStackTrace();
		} finally
		 {
			try {
				
				if(pres!=null)
					pres.close();
				if(connect!=null)
					connect.close();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}  
		 }
		
	}
	@Override
	public void updateProductSell(Product product) {
		String sql = "update product set name=?,image=?,price=?,title=?,description=?,cateID=?,sell_ID=? where id=?";
		
		try {

			connect = new DBConnect().getConnection();
			pres = connect.prepareStatement(sql);
			pres.setString(1, product.getName());
			pres.setString(2, product.getImage());
			pres.setDouble(3, product.getPrice());
			pres.setString(4, product.getTitle());
			pres.setString(5, product.getDescription());
			pres.setInt(6, product.getCateID());
			pres.setInt(7, product.getSell_ID());
			pres.setInt(8, product.getId());
			pres.executeUpdate();

			

		} catch (Exception e) {

			e.printStackTrace();
		} finally
		 {
			try {
				
				if(pres!=null)
					pres.close();
				if(connect!=null)
					connect.close();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}  
		 }
		
		
	}
	@Override
	public void deleteProductSell(int pid, int sellID) {
String sql = "delete from product where id= ? and sell_ID=?";
		
		try {

			connect = new DBConnect().getConnection();
			pres = connect.prepareStatement(sql);
			pres.setInt(1, pid);
			pres.setInt(2, sellID);
			pres.executeUpdate();

			

		} catch (Exception e) {

			e.printStackTrace();
		} finally
		 {
			try {
				
				if(pres!=null)
					pres.close();
				if(connect!=null)
					connect.close();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}  
		 }
	}
	
	
}
