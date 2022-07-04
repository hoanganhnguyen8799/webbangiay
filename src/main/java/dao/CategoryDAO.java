package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import impl_services.ICategory;
import model.Category;

public class CategoryDAO extends DBConnect implements ICategory{

	@Override
	public List<Category> getAllCategory() {
		List<Category> list = new ArrayList<Category>();

		String sql = "select * from Category";

		try {
			connect = getConnection();
			pres = connect.prepareStatement(sql);
			res = pres.executeQuery();

			while (res.next()) {
				Category category = new Category(res.getInt(1), res.getString(2));
				list.add(category);
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
		return list;
	}

	@Override
	public String getCategory(int id) {
		List<Category> list = getAllCategory();
		for (Category category : list) {
			if(category.getCid()==id)
				return category.getCname();
		}
		return null;
	}

}
