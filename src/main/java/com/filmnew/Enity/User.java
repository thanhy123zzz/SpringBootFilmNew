package com.filmnew.Enity;

public class User {
	private String name;
	private String pass;
	private String fullname;
	private String email;
	private String numberphone;
	private String avatar;
	private String gender;
	private String dateofbirth;
	private int idfunction;

	private String repass;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getRepass() {
		return this.repass;
	}

	public void setRepass(String repass) {
		this.repass = repass;
	}


	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumberphone() {
		return this.numberphone;
	}

	public void setNumberphone(String numberphone) {
		this.numberphone = numberphone;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateofbirth() {
		return this.dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public int getIdfunction() {
		return this.idfunction;
	}

	public void setIdfunction(int idfunction) {
		this.idfunction = idfunction;
	}

	public User(String name, String pass, String fullname, String email, String numberphone, String avatar,
			String gender, String dateofbirth) {
		super();
		this.name = name;
		this.pass = pass;
		this.fullname = fullname;
		this.email = email;
		this.numberphone = numberphone;
		this.avatar = avatar;
		this.gender = gender;
		this.dateofbirth = dateofbirth;
	}

	public User(String name, String pass, String fullname, String email, String numberphone, String avatar,
			String gender, String dateofbirth, int idfunction) {
		this.name = name;
		this.pass = pass;
		this.fullname = fullname;
		this.email = email;
		this.numberphone = numberphone;
		this.avatar = avatar;
		this.gender = gender;
		this.dateofbirth = dateofbirth;
		this.idfunction = idfunction;
	}

	public User(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}

	public User(String name, String pass,String repass) {
		this.name = name;
		this.pass = pass;
		this.repass = repass;
	}

	public User() {
	}
}
