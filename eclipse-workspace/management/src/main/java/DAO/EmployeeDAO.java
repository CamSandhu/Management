package DAO;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import bean.Employee;

public class EmployeeDAO {

	private String url = "jdbc:mysql://localhost:3306/university";
	private String user = "root";
	private String password = "";

	private static final String SELECT_EMPLOYEE_BY_ID = "SELECT * FROM employee WHERE id=?";
	private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employee" + "(name,email,salary)VALUES" + "(?,?,?)";
	private static final String SELECT_ALL_EMPLOYEE = "select * from employee";
	private static final String DELETE_EMPLOYEE_SQL = "DELETE FROM employee WHERE id=?";
	private static final String UPDATE_EMPLOYEE_SQL = "update employee set name=?, email=?, salary=? where id=?";

//	--------------------------Connection method------------------------------------------------------

	protected Connection getConnection() {
		Connection connection=null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

//	------------------------------insert employee-----------------------------------------------------------

	public void insertEmployee(Employee employee) {
		System.out.println("INSERT_EMPLOYEE_SQL");

		PreparedStatement preparedStatement;
		try {
			Connection connection = getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(INSERT_EMPLOYEE_SQL);

			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getEmail());
			preparedStatement.setLong(3, employee.getSalary());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	------------------------------delete employee----------------------------------------------

	public boolean delEmployee(int id) {
		boolean employeeDeleted = false;
		System.out.println("DELETE_EMPLOYEE_SQL");
		Connection connection = getConnection();

		try {
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(DELETE_EMPLOYEE_SQL);
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			employeeDeleted = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employeeDeleted;
	}

//	-----------------------------select Employee--------------------------------------------

	public Employee getEmployee(int id) {
		Employee employee = null;
		Connection connection = getConnection();
		try {
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement(SELECT_EMPLOYEE_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				String name = result.getString("name");
				String email = result.getString("email");
				long salary = result.getLong("salary");

				employee = new Employee(id, name, email, salary);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee;
	}

	public List<Employee> getAllEmployee() {
		List<Employee> employees = new ArrayList<>();
		Connection connection = getConnection();
		try {
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(SELECT_ALL_EMPLOYEE);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				String email = result.getString("email");
				long salary = result.getLong("salary");
				employees.add(new Employee(id, name, email, salary));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employees;
	}

//---------------------------------update employee-----------------------------------------------------------

	public boolean updateEmployeeDetails(Employee employee) {
		boolean EmployeeUpdated = false;
		Connection connection = getConnection();

		try {
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(UPDATE_EMPLOYEE_SQL);
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getEmail());
			preparedStatement.setLong(3, employee.getSalary());
			preparedStatement.setInt(4, employee.getId());

			EmployeeUpdated = preparedStatement.executeUpdate() > 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return EmployeeUpdated;

	}

}
