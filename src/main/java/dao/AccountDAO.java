package dao;


import java.sql.SQLException;



import model.Account;



import model.User;


public class AccountDAO extends DBConnect {
	
	

	
	public void insertUser(User user) {
		String sql = "insert into [User](id,name,phone,email) values(?,?,?,?)";
				
				try {
					connect = getConnection();
					
					pres = connect.prepareStatement(sql);
					
					pres.setInt(1, user.getId());
					
					pres.setString(2, user.getName());
					
					pres.setString(3, user.getPhone());
					
					pres.setString(4, user.getEmail());
					
					pres.executeUpdate();
					
					
				} catch (Exception e) {
				
					e.printStackTrace();
				}finally {
					try {
						res.close();
						pres.close();
						connect.close();
					} catch (SQLException e) {
					
						e.printStackTrace();
					}

				}
	}
	public void updateUser(User user) {
		String sql = "update [User] set name=?,phone=?,email=? where id =?";
				
				try {
					connect = getConnection();
					
					pres = connect.prepareStatement(sql);
					
					pres.setInt(4, user.getId());
					
					pres.setString(1, user.getName());
					
					pres.setString(2, user.getPhone());
					
					pres.setString(3, user.getEmail());
					
					pres.executeUpdate();
					
					
				} catch (Exception e) {
				
					e.printStackTrace();
				}finally {
					try {
						
						pres.close();
						connect.close();
					} catch (SQLException e) {
					
						e.printStackTrace();
					}

				}
	}
	public User getUserById(String accountID) {
		
		String sql = "select * from [User] where id=?";
		
		try {
			connect = getConnection();
			
			pres = connect.prepareStatement(sql);
			
			pres.setString(1, accountID);
			
			res = pres.executeQuery();
			
			if(res.next()) {
				
				return new User(res.getInt(1), res.getString(2), res.getString(3), res.getString(4));
			}
		} catch (Exception e) {
		
			e.printStackTrace();
		}finally {
			try {
				res.close();
				pres.close();
				connect.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}

		}
		
		return null;
	}

	

	public void turnOnSellAccount(int accountID) {
		String sql = "update Account set isSell = 1 where uID= ? ";

		try {

			connect = getConnection();
			
			pres = connect.prepareStatement(sql);
			
			pres.setInt(1, accountID);
			
			pres.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {

				pres.close();
				connect.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}

		}
	}

	

	

	public Account login(String user, String pass) {

		String sql = "select * from Account where [user]= ? and pass=?";

		try {

			connect = getConnection();
			pres = connect.prepareStatement(sql);
			pres.setString(1, user);
			pres.setString(2, pass);
			res = pres.executeQuery();

			if (res.next()) {

				return new Account(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5));

			}

		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			try {
				res.close();
				pres.close();
				connect.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}

		}

		return null;
	}

	public void signUp(String user, String pass) {
		String sql = "insert into Account([user],pass,isSell,isAdmin) values(?,?,0,0)";
		
		try {
			connect = getConnection();
			pres =connect.prepareStatement(sql);
			pres.setString(1, user);
			pres.setString(2, pass);
			pres.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			try {
				pres.close();
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}


		
	}
	public Account checkExist(String user) {
		
		String sql ="select * from Account where [user]=?";
		try {
			connect =getConnection();
			pres = connect.prepareStatement(sql);
			pres.setString(1,user);
			res = pres.executeQuery();
			if (res.next()) {

				return new Account(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5));

			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
	
	
}
