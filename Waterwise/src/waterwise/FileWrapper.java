package waterwise;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class FileWrapper
{

    private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private void createConnection() throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/todo?" + "user=sqluser&password=sqluserpw");
		statement = connect.createStatement();
	}

	// private methods
	private void closeConnection()
	{
		try
		{
			if (resultSet != null)
			{
				resultSet.close();
			}
			if (statement != null)
			{
				statement.close();
			}
			if (connect != null)
			{
				connect.close();
			}
		}
		catch (Exception e)
		{

		}
	}

	private boolean checkIfDuplicateString(String table, String column, String value) throws Exception
	{
		try
		{
			createConnection();
			int numOfMatch = 0;
			boolean duplicate = false;
			preparedStatement = connect.prepareStatement("SELECT COUNT(" + column + ") as keyCount FROM waterWise." + table + " WHERE " + column
					+ " like ?;");
			preparedStatement.setString(1, value);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
			{
				numOfMatch = resultSet.getInt(1);
			}
			if (numOfMatch >= 1)
			{
				duplicate = true;
			}
			else if (numOfMatch == 0)
			{
				duplicate = false;
			}
			return duplicate;
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			closeConnection();
		}

	}

	private boolean checkIfDuplicateInt(String table, String column, int value) throws Exception
	{
		try
		{
			createConnection();
			int numOfMatch = 0;
			boolean duplicate = false;
			preparedStatement = connect.prepareStatement("SELECT COUNT(" + column + ") as keyCount FROM waterWise." + table + " WHERE " + column
					+ " = ?;");
			preparedStatement.setInt(1, value);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
			{
				numOfMatch = resultSet.getInt(1);
			}
			if (numOfMatch >= 1)
			{
				duplicate = true;
			}
			else if (numOfMatch == 0)
			{
				duplicate = false;
			}
			return duplicate;
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			closeConnection();
		}

	}

	// Incoming Order
	public void createIncomingOrder(int customerPhone, String customerEmail, String customerAddress, String customerName, String orderID, HashMap<Integer, Integer> orderMap, double priceTotal, String paymentType, String deliveryType, String orderStatus)
			throws Exception
	{
		createCustomer(customerPhone, customerEmail, customerAddress, customerName);
		if (checkIfDuplicateString("incomingOrder", "ID", orderID) == false)
		{
			try
			{
				createConnection();
				preparedStatement = connect.prepareStatement("insert into  waterWise.incomingOrder values (? , ?, ?, ?, ?, ?, ?, ?)");
				preparedStatement.setString(1, orderID);
				preparedStatement.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
				preparedStatement.setDate(3, null);
				preparedStatement.setDouble(4, priceTotal);
				preparedStatement.setString(5, paymentType);
				preparedStatement.setString(6, deliveryType);
				preparedStatement.setString(7, orderStatus);
				preparedStatement.setInt(8, customerPhone);
				preparedStatement.executeUpdate();
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			finally
			{
				closeConnection();
			}
			for (int key : orderMap.keySet())
			{
				int value = orderMap.get(key);
				createIncomingOrderLine(orderID, key, value);
			}
		}
		else
		{
			updateIncomingOrder(orderID, orderMap, priceTotal, paymentType, deliveryType, orderStatus, customerPhone);
		}
	}

	public void updateIncomingOrder(String orderID, HashMap<Integer, Integer> orderMap, double priceTotal, String paymentType, String deliveryType, String orderStatus, int customerPhone)
			throws SQLException
	{
		deleteOrderLines("incomingOrderLine", orderID);
		try
		{
			createConnection();
			preparedStatement = connect
					.prepareStatement("UPDATE  waterWise.incomingOrder SET priceTotal = ?, paymentType = ?, deliveryType = ?, status = ?, customerPhone = ? WHERE ID = ?");
			preparedStatement.setDouble(1, priceTotal);
			preparedStatement.setString(2, paymentType);
			preparedStatement.setString(3, deliveryType);
			preparedStatement.setString(4, orderStatus);
			preparedStatement.setInt(5, customerPhone);
			preparedStatement.setString(6, orderID);
			preparedStatement.executeUpdate();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection();
		}
		for (int key : orderMap.keySet())
		{
			int value = orderMap.get(key);
			createIncomingOrderLine(orderID, key, value);
		}
	}

	// Incoming Order Line
	public void createIncomingOrderLine(String orderID, int productID, int amount) throws SQLException
	{
		try
		{
			createConnection();
			preparedStatement = connect.prepareStatement("insert into  waterWise.incomingOrderLine values (? , ?, ?)");
			preparedStatement.setString(1, orderID);
			preparedStatement.setInt(2, productID);
			preparedStatement.setInt(3, amount);
			preparedStatement.executeUpdate();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection();
		}
	}

	// Outgoing Order
	public void createOutgoingOrder(String orderID, HashMap<Integer, Integer> orderMap, double priceTotal, String paymentType, String deliveryType, String orderStatus, String supplier)
			throws Exception
	{
		if (checkIfDuplicateString("outgoingOrder", "ID", orderID) == false)
		{
			try
			{
				createConnection();
				preparedStatement = connect.prepareStatement("insert into  waterWise.outgoingOrder values (? , ?, ?, ?, ?, ?, ?, ?)");

				preparedStatement.setString(1, orderID);
				preparedStatement.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
				preparedStatement.setDate(3, null);
				preparedStatement.setDouble(4, priceTotal);
				preparedStatement.setString(5, paymentType);
				preparedStatement.setString(6, deliveryType);
				preparedStatement.setString(7, orderStatus);
				preparedStatement.setString(8, supplier);
				preparedStatement.executeUpdate();
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			finally
			{
				closeConnection();
			}
			for (int key : orderMap.keySet())
			{
				int value = orderMap.get(key);
				createOutgoingOrderLine(orderID, key, value);
			}
		}
		else
		{
			updateOutgoingOrder(orderID, orderMap, priceTotal, paymentType, deliveryType, orderStatus, supplier);
		}

	}

	public void updateOutgoingOrder(String orderID, HashMap<Integer, Integer> orderMap, double priceTotal, String paymentType, String deliveryType, String orderStatus, String supplier)
			throws SQLException
	{
		deleteOrderLines("outgoingOrderLine", orderID);
		try
		{
			createConnection();
			preparedStatement = connect
					.prepareStatement("UPDATE  waterWise.outgoingOrder SET priceTotal = ?, paymentType = ?, deliveryType = ?, status = ?, supplier = ? WHERE ID = ?");
			preparedStatement.setDouble(1, priceTotal);
			preparedStatement.setString(2, paymentType);
			preparedStatement.setString(3, deliveryType);
			preparedStatement.setString(4, orderStatus);
			preparedStatement.setString(5, supplier);
			preparedStatement.setString(6, orderID);
			preparedStatement.executeUpdate();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection();
		}
		for (int key : orderMap.keySet())
		{
			int value = orderMap.get(key);
			createOutgoingOrderLine(orderID, key, value);
		}
	}

	// OutgoingOrderLine
	public void createOutgoingOrderLine(String orderID, int productID, int amount) throws SQLException
	{
		try
		{
			createConnection();
			preparedStatement = connect.prepareStatement("insert into  waterWise.outgoingOrderLine values (? , ?, ?)");
			preparedStatement.setString(1, orderID);
			preparedStatement.setInt(2, productID);
			preparedStatement.setInt(3, amount);
			preparedStatement.executeUpdate();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection();
		}
	}

	// General Order
	public ResultSet loadOrder(String table, String ID) throws Exception
	{
		try
		{
			createConnection();
			preparedStatement = connect.prepareStatement("SELECT * from waterWise." + table + " WHERE ID like ?");
			preparedStatement.setString(1, ID);
			resultSet = preparedStatement.executeQuery();
			return resultSet;
		}
		catch (Exception e)
		{
			throw e;
		}
		// finally
		// {
		// closeConnection();
		// }
	}

	public void printOrder(ResultSet rs)
	{
		try
		{
			if (rs.next())
			{
				System.out.println(rs.getString(1));
				System.out.println(rs.getDate(2));
				System.out.println(rs.getDate(3));
				System.out.println(rs.getDouble(4));
				System.out.println(rs.getString(5));
				System.out.println(rs.getString(6));
				System.out.println(rs.getString(7));
				System.out.println(rs.getString(8));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void deleteOrderLines(String table, String orderID) throws SQLException
	{
		try
		{
			createConnection();
			preparedStatement = connect.prepareStatement("delete from waterWise." + table + " WHERE orderID like ?;");
			preparedStatement.setString(1, orderID);
			preparedStatement.executeUpdate();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection();
		}
	}

	// Customer
	public void createCustomer(int phone, String email, String address, String name) throws SQLException
	{
		try
		{
			if (checkIfDuplicateInt("customer", "phoneNumber", phone) == false)
			{
				createConnection();
				preparedStatement = connect.prepareStatement("insert into  waterWise.customer values (? , ?, ?, ?, ?)");
				preparedStatement.setInt(1, phone);
				preparedStatement.setString(2, email);
				preparedStatement.setString(3, address);
				preparedStatement.setString(4, name);
				preparedStatement.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
				preparedStatement.executeUpdate();
			}
			else
			{
				updateCustomer(phone, email, address, name);
			}

		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection();
		}

	}

	public void updateCustomer(int phone, String email, String address, String name) throws SQLException
	{
		try
		{
			createConnection();
			preparedStatement = connect
					.prepareStatement("UPDATE  waterWise.customer SET email = ?, deliveryAddress = ?, name = ? WHERE phoneNumber = ?");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, address);
			preparedStatement.setString(3, name);
			preparedStatement.setInt(4, phone);

			preparedStatement.executeUpdate();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection();
		}
	}

	public Customer getCustomer(int phone) throws Exception
	{
		try
		{
			createConnection();
			preparedStatement = connect.prepareStatement("SELECT * from waterWise.customer where phoneNumber = ?");
			preparedStatement.setInt(1, phone);
			resultSet = preparedStatement.executeQuery();
			int phoneNumber = 0;
			String email = null;
			String deliveryAddress = null;
			String name = null;
			Date creationDate = null;

			if (resultSet.next())
			{
				phoneNumber = resultSet.getInt("phoneNumber");
				email = resultSet.getString("email");
				deliveryAddress = resultSet.getString("deliveryAddress");
				name = resultSet.getString("name");
				creationDate = resultSet.getDate("creationDate");
			}
			Customer customer = new Customer(phoneNumber, email, 
                                name, deliveryAddress, null, null, null);
			return customer;
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			closeConnection();
		}
	}

	// Product
	public void createProduct(int ID, String name, int amount, double weight, String size, double unitPrice, int reOrderAmount) throws SQLException
	{
		try
		{
			if (checkIfDuplicateInt("product", "ID", ID) == false)
			{
				createConnection();
				preparedStatement = connect.prepareStatement("insert into  waterWise.product values (?, ?, ?, ?, ?, ?, ?)");
				preparedStatement.setInt(1, ID);
				preparedStatement.setString(2, name);
				preparedStatement.setInt(3, amount);
				preparedStatement.setDouble(4, weight);
				preparedStatement.setString(5, size);
				preparedStatement.setDouble(6, unitPrice);
				preparedStatement.setInt(7, reOrderAmount);
				preparedStatement.executeUpdate();
			}
			else
			{
				updateProduct(ID, name, amount, weight, size, unitPrice, reOrderAmount);
			}

		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection();
		}

	}

	public void updateProduct(int ID, String name, int amount, double weight, String size, double unitPrice, int reOrderAmount) throws SQLException
	{
		try
		{
			createConnection();
			preparedStatement = connect
					.prepareStatement("UPDATE  waterWise.product SET ID = ?, name = ?, amount = ?, weight = ?, size = ?, unitPrice = ?, reOrderAmount = ? WHERE ID = ?");

			preparedStatement.setInt(1, ID);
			preparedStatement.setString(2, name);
			preparedStatement.setInt(3, amount);
			preparedStatement.setDouble(4, weight);
			preparedStatement.setString(5, size);
			preparedStatement.setDouble(6, unitPrice);
			preparedStatement.setInt(7, reOrderAmount);
			preparedStatement.setInt(8, ID);
			preparedStatement.executeUpdate();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection();
		}
	}

	public ArrayList<Product> getProductList() throws Exception
	{
		try
		{
			createConnection();
			ArrayList<Product> list = new ArrayList<Product>();
			preparedStatement = connect.prepareStatement("SELECT * from waterWise.product");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				int ID = resultSet.getInt("ID");
				String name = resultSet.getString("name");
				int amount = resultSet.getInt("amount");
				double weight = resultSet.getDouble("weight");
				String size = resultSet.getString("size");
				double unitPrice = resultSet.getDouble("unitPrice");
				int reOrderAmount = resultSet.getInt("reOrderAmount");
				Product product = new Product(ID, name, amount, weight, size, unitPrice, reOrderAmount, false);
				list.add(product);
			}
			return list;
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			closeConnection();
		}
	}

	public Product getProduct(int productID) throws Exception
	{
		try
		{
			createConnection();
			preparedStatement = connect.prepareStatement("SELECT * from waterWise.product where ID = ?");
			preparedStatement.setInt(1, productID);
			resultSet = preparedStatement.executeQuery();
			int ID = 0;
			String name = null;
			int amount = 0;
			double weight = 0;
			String size = null;
			double unitPrice = 0;
			int reOrderAmount = 0;

			if (resultSet.next())
			{
				ID = resultSet.getInt("ID");
				name = resultSet.getString("name");
				amount = resultSet.getInt("amount");
				weight = resultSet.getDouble("weight");
				size = resultSet.getString("size");
				unitPrice = resultSet.getDouble("unitPrice");
				reOrderAmount = resultSet.getInt("reOrderAmount");
			}
			Product product = new Product(ID, name, amount, weight, size, unitPrice, reOrderAmount, false);
			return product;
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			closeConnection();
		}
	}

	// Delete
	public void deleteByString(String table, String column, String idValue) throws SQLException
	{
		if (table.equals("incomingOrder"))
		{
			deleteByString("incomingOrderLine", "orderID", idValue);
		}
		else if (table.equals("outgoingOrder"))
		{
			deleteByString("outgoingOrderLine", "orderID", idValue);
		}
		try
		{
			createConnection();
			preparedStatement = connect.prepareStatement("delete from waterWise." + table + " WHERE " + column + " like ?;");
			preparedStatement.setString(1, idValue);
			preparedStatement.executeUpdate();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection();
		}
	}

	public void deleteCustomer(int phone) throws SQLException
	{
		ArrayList<String> orderList = new ArrayList<String>();
		try
		{
			createConnection();
			preparedStatement = connect.prepareStatement("SELECT ID from waterWise.incomingOrder where customerPhone = ?");
			preparedStatement.setInt(1, phone);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				String order = resultSet.getString("ID");
				orderList.add(order);
			}
		}
		catch (ClassNotFoundException e1)
		{
			e1.printStackTrace();
		}
		for (String order : orderList)
		{
			deleteByString("incomingOrder", "ID", order);
		}
		try
		{
			createConnection();
			preparedStatement = connect.prepareStatement("delete from waterWise.customer WHERE phoneNumber = ?;");
			preparedStatement.setInt(1, phone);
			preparedStatement.executeUpdate();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection();
		}
	}

	public void deleteProduct(int productID) throws SQLException
	{
		try
		{
			// Delete form incomingOrderLine
			createConnection();
			preparedStatement = connect.prepareStatement("delete from waterWise.incomingOrderLine WHERE productID = ?;");
			preparedStatement.setInt(1, productID);
			preparedStatement.executeUpdate();
			closeConnection();
			// Delete from outgoingOrderLine
			createConnection();
			preparedStatement = connect.prepareStatement("delete from waterWise.outgoingOrderLine WHERE productID = ?;");
			preparedStatement.setInt(1, productID);
			preparedStatement.executeUpdate();
			closeConnection();
			// Delete form product
			createConnection();
			preparedStatement = connect.prepareStatement("delete from waterWise.product WHERE ID = ?;");
			preparedStatement.setInt(1, productID);
			preparedStatement.executeUpdate();
			closeConnection();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
    
}
