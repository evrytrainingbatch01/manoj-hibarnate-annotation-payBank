package payBank.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

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

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.InterningXmlVisitor;

import payBank.entity.Customer;

public class Login extends HttpServlet{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("bloodgp--"+request.getParameter("userName"));
		System.out.println("bloodgp--"+request.getParameter("password"));

		String loginName=request.getParameter("userName");
		String loginPassword=request.getParameter("password");
    	StandardServiceRegistry sr= new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    	Metadata meta = new MetadataSources(sr).getMetadataBuilder().build(); 
    	
    	SessionFactory factory = meta.getSessionFactoryBuilder().build();  
    	Session session = factory.openSession();  
    	Transaction t = session.beginTransaction(); 
    	
    	Customer obj=new Customer();
    	SQLQuery query= session.createNativeQuery("select * from customer where firstname='"+loginName+"' and password='"+loginPassword+"' ");

    	/*List<Map<String,Object>> custlist= query.list();
    	for (Map<String, Object> map : custlist) {
			System.out.println("map--"+map);
		}*/
    	
    	Customer cust = new Customer();
    	List<Object[]> rows = query.list();
    	if(rows.isEmpty()) {
    		System.out.println("wrong username or password");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			PrintWriter out= response.getWriter();
			out.println("<h3><font color=red>wrong username or password</font></h3>");
			rd.include(request, response);
    	}else {
    		
    		for(Object[] row : rows){
    			//emp.setId(Long.parseLong(row[0].toString()));
    			//	emp.setName(row[1].toString());
    			//emp.setSalary(Double.parseDouble(row[2].toString()));
    			cust.setId(Integer.parseInt(row[0].toString()));
    			cust.setFirstname(row[1].toString());
    			cust.setLastname(row[2].toString());
    			cust.setAge(Integer.parseInt(row[3].toString()));
    			cust.setCity(row[4].toString());
    			cust.setCountry(row[5].toString());
    			cust.setMobile_num(row[6].toString());
    			cust.setEmail_id(row[7].toString());
    			//cust.setDatetime(row[8]);
    			cust.setPassword(row[9].toString());
    			cust.setAccount_num(Integer.parseInt(row[10].toString()));
    			cust.setBalance(Integer.parseInt(row[11].toString()));
    			System.out.println(cust);
    		}
    		
    		//System.out.println(custObj);
    		
    		/*if(custObj.isEmpty()) {
			System.out.println("wrong username or password"+custObj);
		}else {
			System.out.println("login successful"+custObj);
		}*/
    		//session.save(obj);  
    		// t.commit();  
    		System.out.println("--Login  successful--");   
    		request.getSession().setAttribute("customerId", cust.getId()); 
    		request.getSession().setAttribute("customerAccNum", cust.getAccount_num()); 
    		RequestDispatcher rd =	getServletContext().getRequestDispatcher("/transaction.jsp");
    		//RequestDispatcher rd = request.getRequestDispatcher("/transaction.jsp");
    		rd.forward(request, response);
    	}
        session.close(); 
        factory.close();  
		
		
		
		
	//	String errorMsg = null;
/*		if(email == null || email.equals("")){
			errorMsg = "Email ID can't be null or empty.";
		}
		if(password == null || password.equals("")){
			errorMsg = "Password can't be null or empty.";
		}
		if(name == null || name.equals("")){
			errorMsg = "Name can't be null or empty.";
		}
		if(country == null || country.equals("")){
			errorMsg = "Country can't be null or empty.";
		}*/
		
/*		if(errorMsg != null){
			//RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>"+errorMsg+"</font>");
		//	rd.include(request, response);
		}else{*/
		
/*		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("insert into Users(name,email,country, password) values (?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, country);
			ps.setString(4, password);
			
			ps.execute();
			
			logger.info("User registered with email="+email);
			
			//forward to login page to login
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=green>Registration successful, please login below.</font>");
			rd.include(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Database connection problem");
			throw new ServletException("DB Connection problem.");
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				logger.error("SQLException in closing PreparedStatement");
			}
		}*/
	//	}
		
	}
	
}
