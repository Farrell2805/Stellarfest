	package model;
	
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	
	
	import util.Connect;
import util.UserSession;
	
	public class User {
		private String userId;
		private String userEmail;
		private String userName;
		private String userPassword;
		private String userRole;
		
		private static final Connect connect;
		
		static {
			connect = Connect.getInstance();
		}
		
		
		public User() {
		}
	
		public String getUserId() {
			return userId;
		}
	
		public void setUserId(String userId) {
			this.userId = userId;
		}
	
		public String getUserEmail() {
			return userEmail;
		}
	
		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}
	
		public String getUserName() {
			return userName;
		}
	
		public void setUserName(String userName) {
			this.userName = userName;
		}
	
		public String getUserPassword() {
			return userPassword;
		}
	
		public void setUserPassword(String userPassword) {
			this.userPassword = userPassword;
		}
	
		public String getUserRole() {
			return userRole;
		}
	
		public void setUserRole(String userRole) {
			this.userRole = userRole;
		}
		
		public boolean register(String email, String name, String password, String role) {
			String id = generateUserId();
			String query = "INSERT INTO users (userId, userEmail, userName, userPassword, userRole) VALUES (?, ?, ?, ?, ?)";
			
			try (PreparedStatement ps = connect.prepareStatement(query)){
				ps.setString(1, id);
				ps.setString(2, email);
				ps.setString(3, name);
				ps.setString(4, password);
				ps.setString(5, role);
				
				int status = ps.executeUpdate();
				if(status > 0) {
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return false;
			
		}
		
		public String generateUserId() {
			String query = "SELECT COUNT(*) as total FROM users";
			int total = 1;
			
			ResultSet rs = connect.execQuery(query);
			try {
				if(rs.next()) {
					total = rs.getInt("total") + 1;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return String.format("US%05d", total);
		}
		
		public User login(String email, String password) {
			String query = "SELECT * FROM users WHERE userEmail = ? AND userPassword = ?";
			
			User user = null;
			try (PreparedStatement ps = connect.prepareStatement(query)){
				ps.setString(1, email);
				ps.setString(2, password);
				try(ResultSet rs = ps.executeQuery()){
					if(rs.next()) {
						user = new User();
						user.setUserEmail(rs.getString("userEmail"));
						user.setUserId(rs.getString("userId"));
						user.setUserName(rs.getString("userName"));
						user.setUserPassword(rs.getString("userPassword"));
						user.setUserRole(rs.getString("userRole"));
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return user;
			
		}
		
		public String changeProfile(String email, String name, String oldPass, String newPass) {
			User currentUser = UserSession.getCurrentUser();
			if(currentUser == null) {
				return "User not found";
			}
			
			if(currentUser.getUserPassword().equals(oldPass) == false) {
				return "invalid old pass";
			}
			
			boolean status = checkChangeProfileInput(email, name, oldPass, newPass);
			
			if(status == false) {
				return "Invalid inputs";
			}
			
			String query = "UPDATE user SET userName = ?, userNmail = ?, userPassword = ? WHERE userId = ?";
			PreparedStatement ps = connect.prepareStatement(query);
			try {
				ps.setString(1, name);
				ps.setString(2, email);
				ps.setString(3, newPass);
				ps.setString(4, currentUser.getUserId());
				
				int rowsAffected = ps.executeUpdate();
				if(rowsAffected > 0) {
					return "Success";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "failed";
		}
		
		public User getUserByEmail(String email) {
			String query = "SELECT * FROM users WHERE userEmail = ?";
			PreparedStatement ps = connect.prepareStatement(query);
			User user = null;
			try {
				ps.setString(1, email);
				
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					user = new User();
					user.setUserEmail(rs.getString("userEmail"));
					user.setUserId(rs.getString("userId"));
					user.setUserName(rs.getString("userName"));
					user.setUserPassword(rs.getString("userPassword"));
					user.setUserRole(rs.getString("userRole"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return user;
			
		}
		
		public  User getUserByUsername(String name) {
			String query = "SELECT * FROM users WHERE userName = ?";
			PreparedStatement ps = connect.prepareStatement(query);
			User user = null;
			try {
				ps.setString(1, name);
				
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					user = new User();
					user.setUserEmail(rs.getString("userEmail"));
					user.setUserId(rs.getString("userId"));
					user.setUserName(rs.getString("userName"));
					user.setUserPassword(rs.getString("userPassword"));
					user.setUserRole(rs.getString("userRole"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return user;
		}
		
		public String checkRegisterInput(String name, String password, String email, String role) {
			if(name.isEmpty() || password.isEmpty() || email.isEmpty() || role.isEmpty()) {
				return "All fields must be filled!";
			}
			
			if(getUserByEmail(email) != null) {
				return "Email must be unique";
			}
			
			if(getUserByUsername(name) != null) {
				return "Username must be unique";
			}
			
			if(password.length() < 5) {
				return "password must at least 5 letters";
			}
			
			return null;
		}
		
		public boolean checkChangeProfileInput(String email, String name, String oldPass, String newPass) {
			
			if(email.isEmpty() || name.isEmpty() || newPass.isEmpty() || oldPass.isEmpty()) {
				return false;
			}
			
			if(newPass.length() < 5) {
				return false;
			}	
			
			return true;
		}
		
		
	
	}
