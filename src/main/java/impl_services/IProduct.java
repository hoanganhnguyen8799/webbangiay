package impl_services;

import java.util.List;

import model.Product;

public interface IProduct {
	public List<Product> getAllProducts();
	public Product getLastProduct() ;
	public Product getProductById(String id) ;
	public List<Product> getProductByCategory(String category);
//	public boolean checkProductCart(int proID, int cartID) ;
	public List<Product> getProductPaging(int inex);
	public int pageNumber();
	public List<Product> getSellProduct(int accountID);
	void addNewProductSell(Product product);
	void updateProductSell(Product product);
	void deleteProductSell(int pid, int sellID);
	public List<Product> findProductByName(String name);
}
