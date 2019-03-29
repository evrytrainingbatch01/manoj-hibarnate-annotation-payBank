package payBank.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import payBank.entity.Account;
import payBank.entity.Customer;



public class Registration extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("first name--- "+request.getParameter("firstname"));
		String firstname=request.getParameter("firstname");
		String lastName=request.getParameter("lastName");
		String password=request.getParameter("password");
		String age=request.getParameter("age");
		String city=request.getParameter("city");
		String country=request.getParameter("country");
		String mobileNum=request.getParameter("mobileNum");
		String emailId=request.getParameter("emailId");
		
		int randomValueForAccountNum=new Random().nextInt(10000000);
		Date currentDate=new Date();
    	StandardServiceRegistry sr= new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    	Metadata meta = new MetadataSources(sr).getMetadataBuilder().build(); 
    	
    	SessionFactory factory = meta.getSessionFactoryBuilder().build();  
    	Session session = factory.openSession();  
    	Transaction t = session.beginTransaction(); 
    	try {
    	Customer cust=new Customer();
    	
    	cust.setFirstname(firstname);
    	cust.setLastname(lastName);
    	cust.setAge(Integer.parseInt(age));
    	cust.setCity(city);
    	cust.setCountry(country);
    	cust.setMobile_num(mobileNum);
    	cust.setEmail_id(emailId);
    	cust.setPassword(password);
    	cust.setAccount_num(randomValueForAccountNum);
    	cust.setBalance(0);
    	cust.setAcc_userType(2);
    	cust.setDatetime(currentDate);
    	
    	int userId = (Integer)session.save(cust);
    	System.out.println("userId--- "+userId);
    	Account acc=new Account();
    	acc.setAccNum(randomValueForAccountNum);
    	acc.setAccCustomerId(userId);
    	acc.setAccBalance(0);
    	acc.setAccLoan(0);
    	acc.setAccAproval(0);
    	acc.setAccUserType(2);
    	acc.setAccLoanRequested(0);
    	
    	session.save(acc);
        t.commit();  
        System.out.println("--Customer Successfully Added--");    
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
		PrintWriter out= response.getWriter();
		out.println("<h3><font color=Green>Account Created Successfully</font></h3>");
		rd.include(request, response);
    	}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			factory.close();  
			session.close(); 
		}
		
		
	}
}
