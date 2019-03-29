package payBank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="account")

// Hibernate Hierarchy -Table per Subclass
@PrimaryKeyJoinColumn(name="acc_customerId")
public class Account {
	@Id
	@Column(name="acc_id")
	private int accId;
	@Column(name="acc_account_num")
	private int accNum;
	@Column(name="acc_customerId")
	private int accCustomerId;
	@Column(name="acc_balance")
	private int accBalance;
	@Column(name="acc_loan")
	private int accLoan;
	@Column(name="acc_approval")
	private int accAproval;
	@Column(name="acc_userType")
	private int accUserType;
	@Column(name="acc_loan_Amount_req")
	private int accLoanRequested;
	
	public int getAccId() {
		return accId;
	}
	public void setAccId(int accId) {
		this.accId = accId;
	}
	public int getAccNum() {
		return accNum;
	}
	public void setAccNum(int accNum) {
		this.accNum = accNum;
	}
	public int getAccCustomerId() {
		return accCustomerId;
	}
	public void setAccCustomerId(int accCustomerId) {
		this.accCustomerId = accCustomerId;
	}
	public int getAccBalance() {
		return accBalance;
	}
	public void setAccBalance(int accBalance) {
		this.accBalance = accBalance;
	}
	public int getAccLoan() {
		return accLoan;
	}
	public void setAccLoan(int accLoan) {
		this.accLoan = accLoan;
	}
	public int getAccAproval() {
		return accAproval;
	}
	public void setAccAproval(int accAproval) {
		this.accAproval = accAproval;
	}
	public int getAccUserType() {
		return accUserType;
	}
	public void setAccUserType(int accUserType) {
		this.accUserType = accUserType;
	}
	public int getAccLoanRequested() {
		return accLoanRequested;
	}
	public void setAccLoanRequested(int accLoanRequested) {
		this.accLoanRequested = accLoanRequested;
	}
	public Account(int accId, int accNum, int accCustomerId, int accBalance, int accLoan, int accAproval,
			int accUserType, int accLoanRequested) {
		super();
		this.accId = accId;
		this.accNum = accNum;
		this.accCustomerId = accCustomerId;
		this.accBalance = accBalance;
		this.accLoan = accLoan;
		this.accAproval = accAproval;
		this.accUserType = accUserType;
		this.accLoanRequested = accLoanRequested;
	}
	public Account() {
		super();
	}

	
}
