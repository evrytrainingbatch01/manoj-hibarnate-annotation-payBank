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

import payBank.entity.Account;
import payBank.entity.Customer;

public class Login extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("userName--" + request.getParameter("userName"));
		System.out.println("password--" + request.getParameter("password"));

		String loginName = request.getParameter("userName");
		String loginPassword = request.getParameter("password");
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(sr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			SQLQuery query = session.createNativeQuery(
					"select cust.id,cust.firstname,cust.lastname,cust.age,cust.city,cust.country,cust.mobile_num,cust.email_id,cust.password,cust.account_num,cust.balance,cust.acc_userType,acc.acc_id from customer as cust join account as acc on cust.account_num=acc.acc_account_num where cust.firstname='"
							+ loginName + "' and cust.password='" + loginPassword + "' ");

			Customer cust = new Customer();
			Account acc = new Account();
			List<Object[]> rows = query.list();
			if (rows.isEmpty()) {
				System.out.println("wrong username or password");
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
				PrintWriter out = response.getWriter();
				out.println("<h3><font color=red>wrong username or password</font></h3>");
				rd.include(request, response);
			} else {

				for (Object[] row : rows) {

					cust.setId(Integer.parseInt(row[0].toString()));
					cust.setFirstname(row[1].toString());
					cust.setLastname(row[2].toString());
					cust.setAge(Integer.parseInt(row[3].toString()));
					cust.setCity(row[4].toString());
					cust.setCountry(row[5].toString());
					cust.setMobile_num(row[6].toString());
					cust.setEmail_id(row[7].toString());
					// cust.setDatetime(row[8]);
					cust.setPassword(row[8].toString());
					cust.setAccount_num(Integer.parseInt(row[9].toString()));
					cust.setBalance(Integer.parseInt(row[10].toString()));
					acc.setAccId(Integer.parseInt(row[12].toString()));
					System.out.println(cust);

				}

				System.out.println("--Login  successful--");
				request.getSession().setAttribute("customerId", cust.getId());
				request.getSession().setAttribute("customerAccNum", cust.getAccount_num());
				request.getSession().setAttribute("AccId", acc.getAccId());
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/transaction.jsp");
				// RequestDispatcher rd = request.getRequestDispatcher("/transaction.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			session.close();
			factory.close();

		}
	}

}
