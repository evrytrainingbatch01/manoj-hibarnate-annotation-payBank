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

public class CheckBalance extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("name--" + (Integer) request.getSession().getAttribute("customerId"));
		// System.out.println("id--"+request.getAttribute("id"));customerAccNum
		int custId = (Integer) request.getSession().getAttribute("customerId");
		int custAccNum = (Integer) request.getSession().getAttribute("customerAccNum");

		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(sr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			SQLQuery getquery = session
					.createSQLQuery("select * from account where acc_account_num=" + custAccNum + " ");
			List<Object[]> custAccdetails = getquery.list();
			Account acc = new Account();
			if (custAccdetails.isEmpty()) {
				System.out.println("no data found");
			} else {
				// System.out.println("continue...");
				for (Object[] row : custAccdetails) {
					acc.setAccId(Integer.parseInt(row[0].toString()));
					// acc.setAccBalance(Integer.parseInt(row[3].toString()));
					double doubleValue = Double.parseDouble((row[3].toString()));
					int intValue = (int) doubleValue;
					acc.setAccBalance(intValue);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/transaction.jsp");
					// request.getSession().setAttribute("customerId", cust.getId());
					request.getSession().setAttribute("customerBalance", acc.getAccBalance());
					rd.forward(request, response);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			session.close();
			factory.close();
		}
	}
}
