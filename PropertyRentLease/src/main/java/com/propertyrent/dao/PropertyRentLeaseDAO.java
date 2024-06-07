package com.propertyrent.dao;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.propertyrent.model.PropertyImage;
import com.propertyrent.model.SellerPropertyForm;
import com.propertyrent.model.UsersInfo;
import com.propertyrent.util.ConnectionTable;

public class PropertyRentLeaseDAO {
    public static boolean insert(UsersInfo User) throws ClassNotFoundException, SQLException {
        Connection connection = ConnectionTable.getConnection();
        Connection con = ConnectionTable.getConnection();

        String query = "SELECT email FROM users WHERE email=?";
        PreparedStatement prepare = con.prepareStatement(query);
        prepare.setString(1, User.getEmail());
        ResultSet result = prepare.executeQuery();
        if (!result.next()) {
            String check = "insert into users (user_name,password,email,phonenumber)values(?,?,?,?)";

            PreparedStatement p = con.prepareStatement(check);
            p.setString(1, User.getUsername());
            p.setString(2, User.getPassword());
            p.setString(3, User.getEmail());
            p.setString(4, User.getPhonenumber());

            p.execute();
            System.out.println("registered  successfull");
            return true;
        }

        return false;

    }
    public class BlobToInputStreamConverter {

        public static InputStream convertBlobToInputStream(Blob blob) throws SQLException {
            return blob.getBinaryStream();
        }
    }

    public static boolean logincheck(UsersInfo User) throws ClassNotFoundException, SQLException {
        Connection connection = ConnectionTable.getConnection();
        String query = "select email,password from users where email=? and password=?";
        PreparedStatement prestm = connection.prepareStatement(query);
        prestm.setString(1, User.getEmail());
        prestm.setString(2, User.getPassword());
        ResultSet result = prestm.executeQuery();
        if (!result.next()) {
            return false;
        } else {
            return true;
        }

    }

    public UsersInfo getUserIdByEmail(UsersInfo User) throws ClassNotFoundException, SQLException {
        Connection connection = ConnectionTable.getConnection();
        String query = "select * FROM users WHERE email = ?";
        PreparedStatement prestm = connection.prepareStatement(query);

        prestm.setString(1, User.getEmail());
        ResultSet result = prestm.executeQuery();
        if (result.next()) {
            int id = result.getInt("user_id");
            String name = result.getString("user_name");
            System.out.println(id);
            System.out.println(name);
            User.setId(id);
            User.setUsername(name);

            return User;

        }
        return null;

    }

    public UsersInfo adminlogincheck(UsersInfo user) throws ClassNotFoundException, SQLException {
        Connection connection = ConnectionTable.getConnection();
        String query = "select email,password from users where email=? and password=?";
        PreparedStatement prestm = connection.prepareStatement(query);
        prestm.setString(1, user.getEmail());
        prestm.setString(2, user.getPassword());
        ResultSet result = prestm.executeQuery();
        if (!result.next()) {
            return null;
        } else {
            return user;
        }

    }

    public void sellerdetailsinsert(String location,int ownerid, String propertyType, int sqft, String furnishing, Date availableFrom,
            int rentPrice, String address, Date postedOnDate, InputStream ebBillInputStream,
            List<InputStream> propertyImagesInputStreamList) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionTable.getConnection();
            String insertPropertyDetailsQuery = "INSERT INTO property_details (property_type, sqft, furnishing, available_from, rent, address, posted_on_date, EB_Bill,owner_id,location) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? ,?)";
            preparedStatement = connection.prepareStatement(insertPropertyDetailsQuery);
            preparedStatement.setString(1, propertyType);
            preparedStatement.setInt(2, sqft);
            preparedStatement.setString(3, furnishing);
            preparedStatement.setDate(4, new java.sql.Date(availableFrom.getTime()));
            preparedStatement.setInt(5, rentPrice);
            preparedStatement.setString(6, address);
            preparedStatement.setDate(7, new java.sql.Date(postedOnDate.getTime()));
            preparedStatement.setBinaryStream(8, ebBillInputStream);
            preparedStatement.setInt(9, ownerid);
            preparedStatement.setString(10, location);
            
            preparedStatement.executeUpdate();


            int propertyId;
            String getLastInsertedIdQuery = "SELECT LAST_INSERT_ID()";
            preparedStatement = connection.prepareStatement(getLastInsertedIdQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            propertyId = resultSet.getInt(1);

            
            String insertPropertyImagesQuery = "INSERT INTO property_images (images, property_id) VALUES (?, ?)";
            for (InputStream propertyImageInputStream : propertyImagesInputStreamList) {
                preparedStatement = connection.prepareStatement(insertPropertyImagesQuery);
                preparedStatement.setBlob(1, propertyImageInputStream);
                preparedStatement.setInt(2, propertyId);
                preparedStatement.executeUpdate();
                // Close the PreparedStatement inside the loop
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void subscriptionInsert(int ownerId, String userID, String cardNumber, String expiry, String cvv) {
        try {

            Connection connection = ConnectionTable.getConnection();

            String sql = "INSERT INTO property_subscription (owner_id, subscription_duration, payment_amount, payment_status) VALUES (?, ?, ?, ?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, ownerId);
            pstmt.setInt(2, 90);
            pstmt.setInt(3, 200);
            pstmt.setBoolean(4, true);

            pstmt.executeUpdate();

            pstmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
    }

    public List<SellerPropertyForm> getPropertyDetails() throws ClassNotFoundException, SQLException {
        List<SellerPropertyForm> val = new ArrayList<SellerPropertyForm>();
        Connection connection = ConnectionTable.getConnection();
        String query = "SELECT * FROM property_details";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SellerPropertyForm spf = new SellerPropertyForm();
                spf.setPropertyId(resultSet.getInt("property_id"));
                spf.setPropertyType(resultSet.getString("property_type"));
                spf.setSqft(resultSet.getInt("sqft"));
                spf.setFurnishing(resultSet.getString("furnishing"));
                spf.setAvailableFrom(resultSet.getDate("available_from"));
                spf.setRent(resultSet.getInt("rent"));
                spf.setAddress(resultSet.getString("address"));
                spf.setPostedOnDate(resultSet.getDate("posted_on_date"));
                spf.setEbBillStream(resultSet.getAsciiStream("eb_bill"));
                spf.setOwnerId(resultSet.getInt("owner_id"));
                spf.setApproval(false);
                Blob blob = resultSet.getBlob("EB_Bill");
                spf.setLocation(resultSet.getString("location"));
                InputStream inputStream = BlobToInputStreamConverter.convertBlobToInputStream(blob);

                val.add(spf);
                System.out.println(val);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return val;

    }
    
    public List<PropertyImage> getPropertyImages( int propertyId) throws SQLException, ClassNotFoundException {
        List<PropertyImage> propertyImages = new ArrayList<>();
        Connection connection = ConnectionTable.getConnection();
        String sql = "SELECT * FROM property_images WHERE property_id=?";
        
        try (PreparedStatement imageStatement = connection.prepareStatement(sql)) {
            imageStatement.setInt(1, propertyId);
            try (ResultSet imageResultSet = imageStatement.executeQuery()) {
                while (imageResultSet.next()) {
                    int imageId = imageResultSet.getInt("image_id");
                    byte[] imageBytes = imageResultSet.getBytes("images");
                    propertyImages.add(new PropertyImage(imageId, imageBytes, propertyId));
                }
            }
        }
        return propertyImages;
    }

        public void approveProperty(int propertyId) throws SQLException, ClassNotFoundException {
            Connection connection = null;
            PreparedStatement statement = null;
            try {
                connection = ConnectionTable.getConnection();
                String sql = "update property_details set is_approval =true  where property_id ='?'";
                statement = connection.prepareStatement(sql);
              
                statement.setInt(1, propertyId);
                statement.executeUpdate();
            } finally {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        }
    

}