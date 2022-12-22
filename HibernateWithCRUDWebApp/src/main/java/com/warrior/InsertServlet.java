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

public class InsertServlet extends GenericServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5887186944759424635L;
	private SessionFactory factory;

	// non-life cycle init() method
	public void init() throws ServletException {
		factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	}

	// life cycle service() method
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
		
		//open Session
		Session ses=factory.openSession();
		//create POJO class object
		Employee e1=new Employee();
		e1.setEmployeeId(empno);
		e1.setEmployeeName(s2);
		e1.setEmployeeSal(sal);
		e1.setDeptNumber(deptno);
		
		//begin transaction
		Transaction tx=ses.beginTransaction();
		ses.save(e1);
		tx.commit();
		
		PrintWriter out=res.getWriter();
		out.print("<html><body>");
		out.println("<h2>Employee Saved Successfully...</h2> <br>");
		out.println("Back <a href=index.jsp>HOME</a>");
		out.print("</body></html>");
		out.close();
		ses.close();
	}//<-- this is service() method
	//create destroy() method
	public void destroy() {
		factory.close();
	}
	

}
