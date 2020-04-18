package Java2_04;

import java.sql.ResultSet;
import java.sql.*;

public class Ex2 {
    public static void main(String[] args) {
        
    }
        // Function Method
        private static void showCustomers () {
            ResultSet resultSet = getResultSet_FromDataBase("northwind", "SELECT * FROM customers");
            printListCustomer_FromResultSet(resultSet);
        }

        private static void findCustomerByName (String customerName){
            String query = "SELECT * FROM customers WHERE ContactName like '%" + customerName + "%'";
            ResultSet resultSet = getResultSet_FromDataBase("northwind", query);
            printListCustomer_FromResultSet(resultSet);
        }

        private static void showProducts () {
            ResultSet resultSet = getResultSet_FromDataBase("northwind", "SELECT * FROM products");
            printListProduct_FromResultSet(resultSet);
        }

        private static void findProductsByPrice ( double priceFrom, double priceTo){
            String query = "SELECT * FROM products WHERE UnitPrice > " + priceFrom + " AND UnitPrice < " + priceTo;
            ResultSet resultSet = getResultSet_FromDataBase("northwind", query);
            printListProduct_FromResultSet(resultSet);
        }

        private static void showOrderDetailsByOrderID ( int OrderID){
            try {
                ResultSet resultSet_Order = getResultSet_FromDataBase("northwind", "SELECT * FROM orders WHERE OrderID = " + OrderID);
                ResultSet resultSet_orderDetailsExtended = getResultSet_FromDataBase("northwind", "SELECT  * FROM `order details extended` WHERE OrderID = " + OrderID);

                String strResutl_Order = "";
                while (resultSet_Order.next()) {
                    //String OrderID = resultSet_Order.getString("");
                    String CustomerID = resultSet_Order.getString("CustomerID");
                    String EmployeeID = resultSet_Order.getString("EmployeeID");
                    String OrderDate = resultSet_Order.getString("OrderDate");
                    String RequiredDate = resultSet_Order.getString("RequiredDate");
                    String ShippedDate = resultSet_Order.getString("ShippedDate");
                    String ShipVia = resultSet_Order.getString("ShipVia");
                    String Freight = resultSet_Order.getString("Freight");
                    String ShipName = resultSet_Order.getString("ShipName");
                    String ShipAddress = resultSet_Order.getString("ShipAddress");
                    String ShipCity = resultSet_Order.getString("ShipCity");
                    String ShipRegion = resultSet_Order.getString("ShipRegion");
                    String ShipPostalCode = resultSet_Order.getString("ShipPostalCode");
                    String ShipCountry = resultSet_Order.getString("ShipCountry");

                    strResutl_Order += "OrderID" +
                            ", CustomerID: " + CustomerID +
                            ", EmployeeID: " + EmployeeID +
                            ", OrderDate: " + OrderDate +
                            ", RequiredDate: " + RequiredDate +
                            ", ShippedDate: " + ShippedDate +
                            ", ShipVia: " + ShipVia +
                            ", Freight: " + Freight +
                            ", ShipName: " + ShipName +
                            ", ShipAddress: " + ShipAddress +
                            ", ShipCity: " + ShipCity +
                            ", ShipRegion: " + ShipRegion +
                            ", ShipPostalCode: " + ShipPostalCode +
                            ", ShipCountry: " + ShipCountry + "\n";
                }

                System.out.println("Đơn hàng có ID = " + OrderID + ": ");
                System.out.println(strResutl_Order);

                String strResult_OrderDetailExtended = "";
                while (resultSet_orderDetailsExtended.next()) {

                    String ProductID = resultSet_orderDetailsExtended.getString("ProductID");
                    String ProductName = resultSet_orderDetailsExtended.getString("ProductName");
                    String UnitPrice = resultSet_orderDetailsExtended.getString("UnitPrice");
                    String Quantity = resultSet_orderDetailsExtended.getString("Quantity");
                    String Discount = resultSet_orderDetailsExtended.getString("Discount");
                    String ExtendedPrice = resultSet_orderDetailsExtended.getString("ExtendedPrice");

                    strResult_OrderDetailExtended += "\tProductID: " + ProductID +
                            ", ProductName: " + ProductName +
                            ", UnitPrice: " + UnitPrice +
                            ", Quantity: " + Quantity +
                            ", Discount: " + Discount +
                            ", ExtendedPrice: " + ExtendedPrice + "\n";
                }
                System.out.println("> CHi tiết đơn hàng: ");
                System.out.println(strResult_OrderDetailExtended);

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        // Common Method
        private static ResultSet getResultSet_FromDataBase (String databaseName, String query){
            try {
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" + databaseName + "?" +
                                "&serverTimezone=UTC" +
                                "&useSSL=false" +
                                "&allowPublicKeyRetrieval=true",
                        "root",
                        ""
                );

                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery(query);
                return resultSet;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return null;
        }

        private static void printListCustomer_FromResultSet (ResultSet resultSet){
            try {
                String strResult = "";
                while (resultSet.next()) {
                    String CustomerID = resultSet.getString("CustomerID");
                    String CompanyName = resultSet.getString("CompanyName");
                    String ContactName = resultSet.getString("ContactName");
                    String ContactTitle = resultSet.getString("ContactTitle");
                    String Address = resultSet.getString("Address");
                    String City = resultSet.getString("City");
                    String Region = resultSet.getString("Region");
                    String PostalCode = resultSet.getString("PostalCode");
                    String Country = resultSet.getString("Country");
                    String Phone = resultSet.getString("Phone");
                    String Fax = resultSet.getString("Fax");

                    strResult += "CustomerID: " + CustomerID +
                            ", CompanyName: " + CompanyName +
                            ", ContactName: " + ContactName +
                            ", ContactTitle: " + ContactTitle +
                            ", Address: " + Address +
                            ", City: " + City +
                            ", Region: " + Region +
                            ", PostalCode: " + PostalCode +
                            ", Country: " + Country +
                            ", Phone: " + Phone +
                            ", Fax: " + Fax + "\n";
                }

                System.out.println("Kết quả truy vấn: [Danh sách khách hàng]");
                System.out.println(strResult);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        private static void printListProduct_FromResultSet (ResultSet resultSet){
            try {
                String strResult = "";
                while (resultSet.next()) {
                    String ProductID = resultSet.getString("ProductID");
                    String ProductName = resultSet.getString("ProductName");
                    String SupplierID = resultSet.getString("SupplierID");
                    String CategoryID = resultSet.getString("CategoryID");
                    String QuantityPerUnit = resultSet.getString("QuantityPerUnit");
                    String UnitPrice = resultSet.getString("UnitPrice");
                    String UnitsInStock = resultSet.getString("UnitsInStock");
                    String UnitsOnOrder = resultSet.getString("UnitsOnOrder");
                    String ReorderLevel = resultSet.getString("ReorderLevel");
                    String Discontinued = resultSet.getString("Discontinued");

                    strResult += "ProductID: " + ProductID +
                            ", ProductName: " + ProductName +
                            ", SupplierID: " + SupplierID +
                            ", CategoryID: " + CategoryID +
                            ", QuantityPerUnit: " + QuantityPerUnit +
                            ", UnitPrice: " + UnitPrice +
                            ", UnitsInStock: " + UnitsInStock +
                            ", UnitsOnOrder: " + UnitsOnOrder +
                            ", ReorderLevel: " + ReorderLevel +
                            ", Discontinued: " + Discontinued + "\n";
                }

                System.out.println("Kết quả truy vấn [Danh sách sản phẩm] :");
                System.out.println(strResult);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

