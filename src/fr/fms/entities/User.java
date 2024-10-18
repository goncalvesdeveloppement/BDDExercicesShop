package fr.fms.entities;

public class User {
	/* ------------ ATTRIBUTES ------------ */
	
	private int idUser;
	private String login;
	private String password;
	
	/* ------------ CONSTRUCTORS ------------ */
	
	public User(int idUser, String login, String password) {
		this.setIdUser(idUser);
		this.setLogin(login);
		this.setPassword(password);
	}
	
	public User(String login, String password) {
		this.setIdUser(0);
		this.setLogin(login);
		this.setPassword(password);
	}

	/* ------------ METHODS ------------ */
	
	@Override
	public String toString() {
		return "User ["
				+ "idUser = " + this.getIdUser() + ", "
				+ "login = \"" + this.getLogin() + "\", "
				+ "password = \"" + this.getPassword() + "\"]\n";
	}


	/* ------------ ACCESSORS ------------ */
	
	public int getIdUser() {
		return idUser;
	}
	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}	
}
