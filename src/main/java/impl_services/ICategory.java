package impl_services;

import java.util.List;

import model.Category;

public interface ICategory {
	List<Category> getAllCategory();
	public String getCategory(int id);
}
