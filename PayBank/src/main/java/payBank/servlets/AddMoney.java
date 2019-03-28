package payBank.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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

import payBank.entity.Customer;

public class AddMoney {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("id--"+ (Integer)request.getSession().getAttribute("customerId"));
		//System.out.println("id--"+request.getAttribute("id"));customerAccNum
		int custId=(Integer)request.getSession().getAttribute("customerId");
		int custAccNum=(Integer)request.getSession().getAttribute("customerAccNum");
		String stramount=request.getParameter("amount");
		int amount=Integer.parseInt(stramount);
		System.out.println("amount for add -- "+amount);
    	StandardServiceRegistry sr= new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    	Metadata meta = new MetadataSources(sr).getMetadataBuilder().build(); 
    	
    	SessionFactory factory = meta.getSessionFactoryBuilder().build();  
    	Session session = factory.openSession();  
    	Transaction t = session.beginTransaction(); 
    	
    	SQLQuery query= session.createNativeQuery("update account set acc_balance ="+amount+" where acc_accountNum="+custAccNum+" ");

    		session.saveOrUpdate(query);  
    		 t.commit();  
    		System.out.println("--Amount added Successfully--");   
    		//RequestDispatcher rd =	getServletContext().getRequestDispatcher("/transaction.jsp");
    		RequestDispatcher rd = request.getRequestDispatcher("/transaction.jsp");
			PrintWriter out= response.getWriter();
			out.println("<h5><font color=green>Money Added Successfully</font></h5>");
			out.println("<p><font >Check YOUR Balance </font></p>");
    		rd.include(request, response);
    		session.close(); 
    		factory.close();  
    	}
		
		
		
	
}
