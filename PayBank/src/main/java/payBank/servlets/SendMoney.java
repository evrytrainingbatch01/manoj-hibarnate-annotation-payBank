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

public class SendMoney extends HttpServlet{

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("id--"+ (Integer)request.getSession().getAttribute("customerId"));
		//System.out.println("id--"+request.getAttribute("id"));customerAccNum
		int FromcustId=(Integer)request.getSession().getAttribute("customerId");
		int FromcustAccNum=(Integer)request.getSession().getAttribute("customerAccNum");
		int FromcustPrviousBalance=(Integer)request.getSession().getAttribute("customerBalance");
		
		String stramount=request.getParameter("amountToTrans");
		int tocustAmount=Integer.parseInt(stramount);
		System.out.println("amount for add -- "+tocustAmount);
		
		String strcustAccNum=request.getParameter("customerAccNum");
		int tocustAccNum=Integer.parseInt(strcustAccNum);
		System.out.println("amount for add -- "+tocustAmount);
		
    	StandardServiceRegistry sr= new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    	Metadata meta = new MetadataSources(sr).getMetadataBuilder().build(); 
    	
    	SessionFactory factory = meta.getSessionFactoryBuilder().build();  
    	Session session = factory.openSession();  
    	Transaction t = session.beginTransaction(); 
    	
    	int updateAmount=FromcustPrviousBalance-tocustAmount;
    	
    	Account acc=new Account();
    	SQLQuery query= session.createNativeQuery("select * from account where acc_account_num="+tocustAccNum+" ");
	
    	//Customer cust = new Customer();
    	List<Object[]> rows = query.list();
    	if(rows.isEmpty()) {
    		System.out.println("Wrong Customer Account Number");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/transaction.jsp");
			PrintWriter out= response.getWriter();
			out.println("<h3><font color=red>Wrong Customer Account Number, Please Try Again !!</font></h3>");
			rd.include(request, response);
    	}else {
    		
    		for(Object[] row : rows){

    			acc.setAccId(Integer.parseInt(row[0].toString()));
    			acc.setAccNum(Integer.parseInt(row[1].toString()));
    			//acc.setAccBalance(Integer.parseInt(row[3].toString()));
    			double doubleValue = Double.parseDouble((row[3].toString()));
    			int intValue = (int)doubleValue;
    			acc.setAccBalance(intValue);
    			System.out.println(acc);

    		}
    		
    		Account FromAccObj = (Account) session.get(Account.class, FromcustId);
    		FromAccObj.setAccBalance(updateAmount);
    		session.update(FromAccObj);
    		
    		Account ToAccObj = (Account) session.get(Account.class, acc.getAccId());
    		ToAccObj.setAccBalance(acc.getAccBalance()+tocustAmount);
    		session.update(ToAccObj);
    		
    		 t.commit();  
    		System.out.println("--Amount Transformed Successfully--");   
    		//RequestDispatcher rd =	getServletContext().getRequestDispatcher("/transaction.jsp");
    		RequestDispatcher rd = request.getRequestDispatcher("/transaction.jsp");
			PrintWriter out= response.getWriter();
			out.println("<h5><font color=green>Money Transformed Successfully</font></h5>");
			out.println("<p><font color=Red>Check YOUR Balance </font></p>");
    		rd.include(request, response);
    		session.close(); 
    		factory.close();  
    	}
}
}
