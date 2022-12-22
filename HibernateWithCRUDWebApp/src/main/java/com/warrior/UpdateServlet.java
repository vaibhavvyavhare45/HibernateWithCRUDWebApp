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

public class UpdateServlet extends GenericServlet {
	private SessionFactory factory;

	public void init() throws ServletException {
		factory = new Configuration().configure().buildSessionFactory();
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// read input values
		String s1 = req.getParameter("empno");
		String s2 = req.getParameter("ename");
		String s3 = req.getParameter("sal");
		String s4 = req.getParameter("deptno");

		// wrapping of this String type
		int empno = Integer.parseInt(s1.trim());
		int sal = Integer.parseInt(s3.trim());
		int deptno = Integer.parseInt(s4.trim());

		PrintWriter out = res.getWriter();
		Session ses = factory.openSession();
		Object o = ses.get(Employee.class, empno);
		if (o == null) {
			out.println("<html><body>");
			out.println("<h2> Employee number is not found.so row is not updated</h2>");
			out.println("<a href=index.jsp>HOME</a>");
			out.println("</body></html>");
			return;
		}
		Employee e1 = (Employee) o;
		Transaction tx = ses.beginTransaction();
		e1.setEmployeeName(s2);
		e1.setEmployeeSal(sal);
		e1.setDeptNumber(deptno);
		tx.commit();
		out.println("<html><body>");
		out.println("<h2> Employee updated successfully</h2>");
		out.println("<a href=index.jsp>HOME</a>");
		out.println("</body></html>");
		out.close();
		ses.close();
	}// service() method

	public void destroy() {
		factory.close();
	}

}
