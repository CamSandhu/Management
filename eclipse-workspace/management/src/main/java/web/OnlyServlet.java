package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EmployeeDAO;
import bean.Employee;

@WebServlet("/")
public class OnlyServlet extends HttpServlet {
	private EmployeeDAO dao;
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		dao = new EmployeeDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {

		case "/insert":
			Insert(request, response);
			break;

		case "/delete":
			Delete(request, response);
			break;

		case "/new":
			newEmployee(request, response);
			break;

		case "/edit":
			Edit(request, response);
			break;
			
		case "/update":
			Update(request, response);
			break;

		default:
			FetchAll(request, response);
			break;
			
		}

	}

	private void newEmployee(HttpServletRequest req, HttpServletResponse res) {
		RequestDispatcher dispatcher = req.getRequestDispatcher("NewEmployee.jsp");
		try {
			dispatcher.forward(req, res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void Delete(HttpServletRequest req, HttpServletResponse res) {
		int id = Integer.parseInt(req.getParameter("id"));
        System.out.println(id);
		dao.delEmployee(id);
		try {
			res.sendRedirect("Employees.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void Insert(HttpServletRequest req, HttpServletResponse res) {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		Long salary = (long) Integer.parseInt(req.getParameter("salary"));

		Employee employee = new Employee(name, email, salary);

		dao.insertEmployee(employee);
		try {
			res.sendRedirect("list");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void Edit(HttpServletRequest req, HttpServletResponse res) {
		int id = Integer.parseInt(req.getParameter("id"));

		Employee employee = dao.getEmployee(id);
		req.setAttribute("employee", employee);
		System.out.println(employee);
		RequestDispatcher dispatcher = req.getRequestDispatcher("NewEmployee.jsp");
		try {
			dispatcher.forward(req, res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
//	-----------------------------fetch-------------------------

	private void FetchAll(HttpServletRequest req, HttpServletResponse res) {
		List<Employee> employees = dao.getAllEmployee();
		req.setAttribute("employees", employees);
		RequestDispatcher dispatcher = req.getRequestDispatcher("Employees.jsp");
		try {
			dispatcher.forward(req, res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void Update(HttpServletRequest req, HttpServletResponse res) {
		int id= Integer.parseInt(req.getParameter("id"));
		String name= req.getParameter("name");
		String email=req.getParameter("email");
		int salary= Integer.parseInt(req.getParameter("salary"));
		Employee employee= new Employee(id,name, email, salary);
		
		dao.updateEmployeeDetails(employee);
		
		try {
			res.sendRedirect("NewEmployee.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
