package payBank.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import payBank.entity.Account;
import payBank.entity.Customer;

public class AddMoney extends HttpServlet{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("id--"+ (Integer)request.getSession().getAttribute("customerId"));
		//System.out.println("id--"+request.getAttribute("id"));customerAccNum
		int custId=(Integer)request.getSession().getAttribute("customerId");
		int custAccNum=(Integer)request.getSession().getAttribute("customerAccNum");
		int custPrviousBalance=(Integer)request.getSession().getAttribute("customerBalance");
		int accountId= (Integer)request.getSession().getAttribute("AccId");
		System.out.println("accountId"+accountId);
		String stramount=request.getParameter("amount");
		int amount=Integer.parseInt(stramount);
		System.out.println("amount for add -- "+amount);
			
		
    	StandardServiceRegistry sr= new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    	Metadata meta = new MetadataSources(sr).getMetadataBuilder().build(); 
    	
    	SessionFactory factory = meta.getSessionFactoryBuilder().build();  
    	Session session = factory.openSession();  
    	Transaction t = session.beginTransaction(); 
    	int updateAmount=custPrviousBalance+amount;
    System.out.println("updateAmount--- "+updateAmount);
    try {

    		Account accObj = (Account) session.get(Account.class, accountId);
    		accObj.setAccBalance(updateAmount);
    		session.update(accObj);
    		 t.commit();  
    		System.out.println("--Amount added Successfully--");   
    		//RequestDispatcher rd =	getServletContext().getRequestDispatcher("/transaction.jsp");
    		RequestDispatcher rd = request.getRequestDispatcher("/transaction.jsp");
			PrintWriter out= response.getWriter();
			out.println("<h3><font color=green>Money Added Successfully</font></h3>");
			out.println("<p><font color=red>Check YOUR Balance </font></p>");
    		rd.include(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close(); 
			factory.close();  
			
		}
    	}
		
		
		
	
}
