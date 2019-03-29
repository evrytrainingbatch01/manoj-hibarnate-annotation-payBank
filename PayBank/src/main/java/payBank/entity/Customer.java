package payBank.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="customer")
//Hibernate Hierarchy -Table per Subclass
@Inheritance(strategy=InheritanceType.JOINED)
public class Customer {
	
	@Id
	private int id;
	
	private String firstname;
	private String lastname;
	private int age;
	private String city;
	private String country;
	private String mobile_num;
	private String email_id;
	private String password;
	private int account_num;
	private int balance;
	private int acc_userType;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_date")
	private Date datetime;
	


	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getMobile_num() {
		return mobile_num;
	}
	public void setMobile_num(String mobile_num) {
		this.mobile_num = mobile_num;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAccount_num() {
		return account_num;
	}
	public void setAccount_num(int account_num) {
		this.account_num = account_num;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getAcc_userType() {
		return acc_userType;
	}
	public void setAcc_userType(int acc_userType) {
		this.acc_userType = acc_userType;
	}

	
	public Customer() {
		super();
	}
	public Customer(int id, String firstname, String lastname, int age, String city, String country, String mobile_num,
			String email_id, String password, int account_num, int balance, int acc_userType) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.city = city;
		this.country = country;
		this.mobile_num = mobile_num;
		this.email_id = email_id;
		this.password = password;
		this.account_num = account_num;
		this.balance = balance;
		this.acc_userType = acc_userType;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", age=" + age + ", city="
				+ city + ", country=" + country + ", mobile_num=" + mobile_num + ", email_id=" + email_id
				+ ", password=" + password + ", account_num=" + account_num + ", balance=" + balance + ", acc_userType="
				+ acc_userType + "]";
	}
}
