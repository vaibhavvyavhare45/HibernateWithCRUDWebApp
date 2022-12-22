package com.warrior;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DeleteServlet extends GenericServlet {
	private SessionFactory factory;

	public void init() throws ServletException {
		factory = new Configuration().configure().buildSessionFactory();
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// read input value
		String s1 = req.getParameter("empno");
		int empno = Integer.parseInt(s1.trim());
		PrintWriter out = res.getWriter();
		Session ses = factory.openSession();
		Object o = ses.get(Employee.class, empno);
		if (o == null) {
			out.print("<html><body>");
			out.println("<h2>Employee number not found,so row is not deleted!!</h2> <br>");
			out.println("Back <a href=index.jsp>HOME</a>");
			out.print("</body></html>");
			return;
		}
		Transaction tx = ses.beginTransaction();
		ses.delete(o);
		tx.commit();
		out.print("<html><body>");
		out.println("<h2>Employee deleted successfully...</h2> <br>");
		out.println("Back <a href=index.jsp>HOME</a>");
		out.print("</body></html>");
		out.close();
		ses.close();
	}//service() method
	public void destroy() {
		factory.close();
	}

}
