package waterwise;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class FileWrapper
{

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;   
    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    Controller c = new Controller();
    
    private void createConnection() throws SQLException, ClassNotFoundException
    {
	Class.forName("com.mysql.jdbc.Driver");

	connect = DriverManager.getConnection("jdbc:mysql://localhost/waterwise", "sqluser", "sqluserpw");

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
        
        private boolean checkIfDone(String table, String ID) throws Exception
	{
		try
		{
			createConnection();
			int numOfMatch = 0;
			boolean done = false;
			preparedStatement = connect.prepareStatement("SELECT COUNT(status) as keyCount FROM waterWise." + table + " WHERE ID like ? and status like ?;");
			preparedStatement.setString(1, ID);
                        preparedStatement.setString(2, "Afsluttet");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
			{
				numOfMatch = resultSet.getInt(1);
			}
			if (numOfMatch == 1)
			{
				done = true;
			}
			else if (numOfMatch == 0)
			{
				done = false;
			}
			return done;
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
	public void createIncomingOrder(Incoming order)
			throws Exception
	{
                String ID = order.getOrderID();
		if (checkIfDuplicateString("incomingOrder", "ID", order.getOrderID()) == false)
		{
                    try
			{
                            createConnection();
                            preparedStatement = connect.prepareStatement("insert into  waterWise.incomingOrder values (? , ?, ?, ?, ?, ?, ?, ?)");
                            preparedStatement.setString(1, ID);
                            preparedStatement.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
                            preparedStatement.setDate(3, null);
                            preparedStatement.setDouble(4, order.getPriceTotal());
                            preparedStatement.setString(5, order.getPaymentType());
                            preparedStatement.setString(6, order.getDeliveryType());
                            preparedStatement.setString(7, order.getOrderStatus());
                            preparedStatement.setInt(8, order.getCustomerPhonenumber());
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
			HashMap<Product, Integer> map = new HashMap<>(order.getListOfProducts());
                        for (Product key : map.keySet())
			{
				int value = map.get(key);
				createIncomingOrderLine(ID, key.getProductID(), value);
			}
		}
		else
		{
                    updateIncomingOrder(order);
		}
	}

	public void updateIncomingOrder(Incoming order)
			throws SQLException
	{
            String ID = order.getOrderID();
            String status = order.getOrderStatus();
            deleteOrderLines("incomingOrderLine", ID);
		try
		{
                    boolean done = checkIfDone("incomingOrder", ID);
                    createConnection();
                    if((done == true && status.equals("Afsluttet"))||(done == false && status.equals("Uafsluttet")))
                    {
                        preparedStatement = connect.prepareStatement("UPDATE  waterWise.incomingOrder SET priceTotal = ?, paymentType = ?, deliveryType = ?, status = ? WHERE ID = ?");
                        preparedStatement.setDouble(1, order.getPriceTotal());
                        preparedStatement.setString(2, order.getPaymentType());
                        preparedStatement.setString(3, order.getDeliveryType());
                        preparedStatement.setString(4, order.getOrderStatus());
                        preparedStatement.setString(5, ID);
                        preparedStatement.executeUpdate();
                    }
                    else if(status.equals("Afsluttet"))
                    {
                        preparedStatement = connect.prepareStatement("UPDATE  waterWise.incomingOrder SET closedDate = ?, priceTotal = ?, paymentType = ?, deliveryType = ?, status = ? WHERE ID = ?");
                        preparedStatement.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
                        preparedStatement.setDouble(2, order.getPriceTotal());
                        preparedStatement.setString(3, order.getPaymentType());
                        preparedStatement.setString(4, order.getDeliveryType());
                        preparedStatement.setString(5, order.getOrderStatus());
                        preparedStatement.setString(6, ID);
                        preparedStatement.executeUpdate();
                    }
                    else if(status.equals("Uafsluttet"))
                    {
                        preparedStatement = connect.prepareStatement("UPDATE  waterWise.incomingOrder SET closedDate = ?, priceTotal = ?, paymentType = ?, deliveryType = ?, status = ? WHERE ID = ?");
                        preparedStatement.setDate(1, null);
                        preparedStatement.setDouble(2, order.getPriceTotal());
                        preparedStatement.setString(3, order.getPaymentType());
                        preparedStatement.setString(4, order.getDeliveryType());
                        preparedStatement.setString(5, order.getOrderStatus());
                        preparedStatement.setString(6, ID);
                        preparedStatement.executeUpdate();
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
                HashMap<Product, Integer> map = new HashMap<>(order.getListOfProducts());
                for (Product key : map.keySet())
		{
                    int value = map.get(key);
                    createIncomingOrderLine(ID, key.getProductID(), value);
		}
	}
        
        public Incoming loadIncomingOrder(String ID) throws Exception
        {
            HashMap map = loadOrderLine("incomingOrderLine", ID);
            try
		{
			createConnection();
			preparedStatement = connect.prepareStatement("SELECT * from waterWise.incomingOrder WHERE ID like ?");
			preparedStatement.setString(1, ID);
			resultSet = preparedStatement.executeQuery();
                        String startDate = null;
                        String closedDate = null;
                        String paymentType = null;
                        String deliveryType = null;
                        String orderStatus = null;
                        int customerPhone = 0;
                        
                        if(resultSet.next())
                        {
                            startDate = df.format(resultSet.getDate("startDate"));
                            if(resultSet.getDate("closedDate") == null)
                            {
                                closedDate = "";
                            }
                            else
                            {
                                closedDate = df.format(resultSet.getDate("closedDate"));
                            }
                            paymentType = resultSet.getString("paymentType");
                            deliveryType = resultSet.getString("deliveryType");
                            orderStatus = resultSet.getString("status");
                            customerPhone = resultSet.getInt("customerPhone");
                        }
                        
                        Incoming order = new Incoming(ID, startDate, closedDate, map, paymentType, deliveryType, orderStatus, customerPhone, false);
                        return order;
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
        
        public ArrayList<Order> loadIncomingOrderList() throws Exception
	{
		try
		{
			createConnection();
			ArrayList<Order> list = new ArrayList<Order>();
			preparedStatement = connect.prepareStatement("SELECT * from waterWise.incomingOrder");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
                            String orderID = resultSet.getString("ID");
                            String startDate = df.format(resultSet.getDate("startDate"));
                            String closedDate = null;
                            if(resultSet.getDate("closedDate") == null)
                            {
                                closedDate = "";
                            }
                            else
                            {
                                closedDate = df.format(resultSet.getDate("closedDate"));
                            }
                            String paymentType = resultSet.getString("paymentType");
                            String deliveryType = resultSet.getString("deliveryType");
                            String orderStatus = resultSet.getString("status");
                            int customerPhone = resultSet.getInt("customerPhone");
			
                            Order order = new Incoming(orderID, startDate, closedDate, null, paymentType, deliveryType, orderStatus, customerPhone, false);
                            list.add(order);
			}
                        for(Order order : list)
                        {
                            HashMap<Product, Integer> map = loadOrderLine("incomingOrderLine", order.getOrderID());
                            order.setListOfProducts(map);
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
	public void createOutgoingOrder(Outgoing order)
			throws Exception
	{
            String ID = order.getOrderID();
            if (checkIfDuplicateString("outgoingOrder", "ID", ID) == false)
            {
        	try
		{
                    createConnection();
                    preparedStatement = connect.prepareStatement("insert into  waterWise.outgoingOrder values (? , ?, ?, ?, ?, ?, ?, ?)");
                    preparedStatement.setString(1, ID);
                    preparedStatement.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
                    preparedStatement.setDate(3, null);
                    preparedStatement.setDouble(4, order.getPriceTotal());
                    preparedStatement.setString(5, order.getPaymentType());
                    preparedStatement.setString(6, order.getDeliveryType());
                    preparedStatement.setString(7, order.getOrderStatus());
                    preparedStatement.setString(8, c.packageSupplier(order.getSupplierName(), order.getSupplierEmail(), order.getOwnAddress(), order.getOwnCity(), order.getOwnZip(), order.getOwnCountry(), order.getOwnPhonenumber()));
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
                
		HashMap<Product, Integer> map = new HashMap<>(order.getListOfProducts());
                for (Product key : map.keySet())
		{
                    int value = map.get(key);
                    createOutgoingOrderLine(ID, key.getProductID(), value);
		}
		}
            else
            {
		updateOutgoingOrder(order);
            }

	}

	public void updateOutgoingOrder(Outgoing order)
			throws SQLException
	{
            String ID = order.getOrderID();
            String status = order.getOrderStatus();
            deleteOrderLines("outgoingOrderLine", ID);
		try
		{
                    boolean done = checkIfDone("outgoingOrder", ID);
                    createConnection();
                    if((done == true && status.equals("Afsluttet"))||(done == false && status.equals("Uafsluttet")))
                    {
                        preparedStatement = connect.prepareStatement("UPDATE  waterWise.outgoingOrder SET priceTotal = ?, paymentType = ?, deliveryType = ?, status = ?, supplier = ? WHERE ID = ?");
			preparedStatement.setDouble(1, order.getPriceTotal());
			preparedStatement.setString(2, order.getPaymentType());
			preparedStatement.setString(3, order.getDeliveryType());
			preparedStatement.setString(4, order.getOrderStatus());
			preparedStatement.setString(5, c.packageSupplier(order.getSupplierName(), order.getSupplierEmail(), order.getOwnAddress(), order.getOwnCity(), order.getOwnZip(), order.getOwnCountry(), order.getOwnPhonenumber()));
			preparedStatement.setString(6, ID);
			preparedStatement.executeUpdate();
                    }
                    else if(status.equals("Afsluttet"))
                    {
                        preparedStatement = connect.prepareStatement("UPDATE  waterWise.outgoingOrder SET closedDate = ?, priceTotal = ?, paymentType = ?, deliveryType = ?, status = ?, supplier = ? WHERE ID = ?");
                        preparedStatement.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
                        preparedStatement.setDouble(2, order.getPriceTotal());
			preparedStatement.setString(3, order.getPaymentType());
			preparedStatement.setString(4, order.getDeliveryType());
			preparedStatement.setString(5, order.getOrderStatus());
			preparedStatement.setString(6, c.packageSupplier(order.getSupplierName(), order.getSupplierEmail(), order.getOwnAddress(), order.getOwnCity(), order.getOwnZip(), order.getOwnCountry(), order.getOwnPhonenumber()));
			preparedStatement.setString(7, ID);
                        preparedStatement.executeUpdate();
                    }
                    else if(status.equals("Uafsluttet"))
                    {
                        preparedStatement = connect.prepareStatement("UPDATE  waterWise.outgoingOrder SET closedDate = ?, priceTotal = ?, paymentType = ?, deliveryType = ?, status = ?, supplier = ? WHERE ID = ?");
                        preparedStatement.setDate(1, null);
                        preparedStatement.setDouble(2, order.getPriceTotal());
			preparedStatement.setString(3, order.getPaymentType());
			preparedStatement.setString(4, order.getDeliveryType());
			preparedStatement.setString(5, order.getOrderStatus());
			preparedStatement.setString(6, c.packageSupplier(order.getSupplierName(), order.getSupplierEmail(), order.getOwnAddress(), order.getOwnCity(), order.getOwnZip(), order.getOwnCountry(), order.getOwnPhonenumber()));
			preparedStatement.setString(7, ID);
                        preparedStatement.executeUpdate();
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
		HashMap<Product, Integer> map = new HashMap<>(order.getListOfProducts());
                for (Product key : map.keySet())
		{
                    int value = map.get(key);
                    createOutgoingOrderLine(ID, key.getProductID(), value);
		}
	}
        
        public Outgoing loadOutgoingOrder(String ID) throws Exception
        {
            HashMap map = loadOrderLine("outgoingOrderLine", ID);
            try
		{
			createConnection();
			preparedStatement = connect.prepareStatement("SELECT * from waterWise.outgoingOrder WHERE ID like ?");
			preparedStatement.setString(1, ID);
			resultSet = preparedStatement.executeQuery();
                        String startDate = null;
                        String closedDate = null;
                        String paymentType = null;
                        String deliveryType = null;
                        String orderStatus = null;
                        String suppName = null;
                        String suppEmail = null;
                        String ownAddress = null;
                        String ownCity = null;
                        String ownZip = null;
                        String ownCountry = null;
                        String ownPhone = null;
                        
                        if(resultSet.next())
                        {
                            startDate = df.format(resultSet.getDate("startDate"));
                            if(resultSet.getDate("closedDate") == null)
                            {
                                closedDate = "";
                            }
                            else
                            {
                                closedDate = df.format(resultSet.getDate("closedDate"));
                            }
                            paymentType = resultSet.getString("paymentType");
                            deliveryType = resultSet.getString("deliveryType");
                            orderStatus = resultSet.getString("status");
                            String completeSupplier = resultSet.getString("supplier");
                            String[] supplier = c.unpackage(completeSupplier);
                            suppName = supplier[0];
                            suppEmail = supplier[1];
                            ownAddress = supplier[2];
                            ownCity = supplier[3];
                            ownZip = supplier[4];
                            ownCountry = supplier[5];
                            ownPhone = supplier[6];
                        }
                        
                        Outgoing order = new Outgoing(ID, startDate, closedDate, map, paymentType, deliveryType, orderStatus, suppName, suppEmail, ownAddress, ownCity, ownZip, ownCountry, ownPhone, false);
                        return order;
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
        
        public ArrayList<Order> loadOutgoingOrderList() throws Exception
	{
		try
		{
			createConnection();
			ArrayList<Order> list = new ArrayList<Order>();
			preparedStatement = connect.prepareStatement("SELECT * from waterWise.outgoingOrder");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
                            String orderID = resultSet.getString("ID");
                            String startDate = df.format(resultSet.getDate("startDate"));
                            String closedDate = null;
                            if(resultSet.getDate("closedDate") == null)
                            {
                                closedDate = "";
                            }
                            else
                            {
                                closedDate = df.format(resultSet.getDate("closedDate"));
                            }
                            String paymentType = resultSet.getString("paymentType");
                            String deliveryType = resultSet.getString("deliveryType");
                            String orderStatus = resultSet.getString("status");
                            String completeSupplier = resultSet.getString("supplier");
                            String[] supplier = c.unpackage(completeSupplier);
                            String suppName = supplier[0];
                            String suppEmail = supplier[1];
                            String ownAddress = supplier[2];
                            String ownCity = supplier[3];
                            String ownZip = supplier[4];
                            String ownCountry = supplier[5];
                            String ownPhone = supplier[6];
			
                            Order order = new Outgoing(orderID, startDate, closedDate, null, paymentType, deliveryType, orderStatus, suppName, suppEmail, ownAddress, ownCity, ownZip, ownCountry, ownPhone, false);
                            list.add(order);
			}
                        for(Order order : list)
                        {
                            HashMap<Product, Integer> map = loadOrderLine("outgoingOrderLine", order.getOrderID());
                            order.setListOfProducts(map);
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
	public ResultSet loadOrderResult(String table, String ID) throws Exception
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
        
        public HashMap<Product,Integer> loadOrderLine(String table, String orderID) throws Exception
	{
            try
		{
                    HashMap<Product,Integer> productMap = new HashMap<>();
                    HashMap<Integer, Integer> intMap = new HashMap<>();
                    createConnection();
                    preparedStatement = connect.prepareStatement("SELECT * from waterWise." + table + " where orderID like ?");
                    preparedStatement.setString(1, orderID);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next())
                    {
                        int productID = resultSet.getInt("productID");
                        int amount = resultSet.getInt("amount");
                        intMap.put(productID, amount);
                    }
                    
                    for(int key : intMap.keySet())
                    {
                        createConnection();
                        preparedStatement = connect.prepareStatement("SELECT * from waterWise.product where ID = ?");
                        preparedStatement.setInt(1, key);
                        resultSet = preparedStatement.executeQuery();
                        if(resultSet.next())
                        {
                            int ID = resultSet.getInt("ID");
                            String name = resultSet.getString("name");
                            int amount = resultSet.getInt("amount");
                            double weight = resultSet.getDouble("weight");
                            String size = resultSet.getString("size");
                            double unitPrice = resultSet.getDouble("unitPrice");
                            int reOrderAmount = resultSet.getInt("reOrderAmount");
                            Product product = new Product(ID, name, amount, weight, size, unitPrice, reOrderAmount, false);
                            productMap.put(product, intMap.get(product.getProductID()));
                        }
                    }
                    return productMap;
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
	public void createCustomer(Customer customer) throws SQLException
	{
		try
		{
                    if (checkIfDuplicateInt("customer", "phoneNumber", customer.getPhoneNumber()) == false)
                    {
                    	createConnection();
			preparedStatement = connect.prepareStatement("insert into  waterWise.customer values (? , ?, ?, ?, ?)");
			preparedStatement.setInt(1, customer.getPhoneNumber());
			preparedStatement.setString(2, customer.getCustomerEmail());
			preparedStatement.setString(3, c.packageCustomerAddress(customer.getDeliveryAddress(), customer.getDeliveryCityAddress(), customer.getDeliveryZipAddress(), customer.getDeliveryCountryAddress()));
			preparedStatement.setString(4, customer.getCustomerName());
			preparedStatement.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
			preparedStatement.executeUpdate();
                    }
                    else
                    {
			updateCustomer(customer);
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

	public void updateCustomer(Customer customer) throws SQLException
	{
		try
		{
			createConnection();
			preparedStatement = connect
					.prepareStatement("UPDATE  waterWise.customer SET email = ?, deliveryAddress = ?, name = ? WHERE phoneNumber = ?");
			preparedStatement.setString(1, customer.getCustomerEmail());
			preparedStatement.setString(2, c.packageCustomerAddress(customer.getDeliveryAddress(), customer.getDeliveryCityAddress(), customer.getDeliveryZipAddress(), customer.getDeliveryCountryAddress()));
			preparedStatement.setString(3, customer.getCustomerName());
			preparedStatement.setInt(4, customer.getPhoneNumber());
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
        
        public ArrayList<Customer> loadCustomerList() throws Exception
	{
		try
		{
			createConnection();
			ArrayList<Customer> list = new ArrayList<Customer>();
			preparedStatement = connect.prepareStatement("SELECT * from waterWise.customer");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
                            int phoneNumber = resultSet.getInt("phoneNumber");
                            String email = resultSet.getString("email");
                            String completeAddress = resultSet.getString("deliveryAddress");
                            String name = resultSet.getString("name");
                            String creationDate = df.format(resultSet.getDate("creationDate"));
                            String[] address = c.unpackage(completeAddress);
                            String deliveryAddress = address[0];
                            String deliveryCityAddress = address[1];
                            String deliveryZipAddress = address[2];
                            String deliveryCountryAddress = address[3];
                            Customer customer = new Customer(phoneNumber, email, name, deliveryAddress, deliveryCityAddress, deliveryZipAddress, deliveryCountryAddress, creationDate);
                            list.add(customer);
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

	public Customer loadCustomer(int phone) throws Exception
	{
		try
		{
			createConnection();
			preparedStatement = connect.prepareStatement("SELECT * from waterWise.customer where phoneNumber = ?");
			preparedStatement.setInt(1, phone);
			resultSet = preparedStatement.executeQuery();
			int phoneNumber = 0;
			String email = null;
			String completeAddress = null;
			String name = null;
			String creationDate = null;
                         
			if (resultSet.next())
			{
				phoneNumber = resultSet.getInt("phoneNumber");
				email = resultSet.getString("email");
				completeAddress = resultSet.getString("deliveryAddress");
				name = resultSet.getString("name");
				creationDate = df.format(resultSet.getDate("creationDate"));
			}
                        String[] address = c.unpackage(completeAddress);
                        String deliveryAddress = address[0];
                        String deliveryCityAddress = address[1];
                        String deliveryZipAddress = address[2];
                        String deliveryCountryAddress = address[3];
                        
			Customer customer = new Customer(phoneNumber, email, name, deliveryAddress, deliveryCityAddress, deliveryZipAddress, deliveryCountryAddress, creationDate);
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
	public void createProduct(Product product) throws SQLException
	{
		try
		{
			if (checkIfDuplicateInt("product", "ID", product.getProductID()) == false)
			{
				createConnection();
				preparedStatement = connect.prepareStatement("insert into  waterWise.product values (?, ?, ?, ?, ?, ?, ?)");
				preparedStatement.setInt(1, product.getProductID());
				preparedStatement.setString(2, product.getProductName());
				preparedStatement.setInt(3, product.getAmountInStorage());
				preparedStatement.setDouble(4, product.getWeight());
				preparedStatement.setString(5, product.getSize());
				preparedStatement.setDouble(6, product.getUnitPrice());
				preparedStatement.setInt(7, product.getReorderAmount());
				preparedStatement.executeUpdate();
			}
			else
			{
				updateProduct(product);
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

	public void updateProduct(Product product) throws SQLException
	{
		try
		{
			createConnection();
			preparedStatement = connect
					.prepareStatement("UPDATE  waterWise.product SET ID = ?, name = ?, amount = ?, weight = ?, size = ?, unitPrice = ?, reOrderAmount = ? WHERE ID = ?");

			preparedStatement.setInt(1, product.getProductID());
			preparedStatement.setString(2, product.getProductName());
			preparedStatement.setInt(3, product.getAmountInStorage());
			preparedStatement.setDouble(4, product.getWeight());
			preparedStatement.setString(5, product.getSize());
			preparedStatement.setDouble(6, product.getUnitPrice());
			preparedStatement.setInt(7, product.getReorderAmount());
			preparedStatement.setInt(8, product.getProductID());
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

	public ArrayList<Product> loadProductList() throws Exception
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

	public Product loadProduct(int productID) throws Exception
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
	public void deleteOrder(String table, String column, String idValue) throws SQLException
	{
		if (table.equals("incomingOrder"))
		{
			deleteOrder("incomingOrderLine", "orderID", idValue);
		}
		else if (table.equals("outgoingOrder"))
		{
			deleteOrder("outgoingOrderLine", "orderID", idValue);
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
			deleteOrder("incomingOrder", "ID", order);
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
