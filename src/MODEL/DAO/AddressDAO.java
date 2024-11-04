package MODEL.DAO;

import MODEL.DBUtil.DBUtil;
import MODEL.DTO.AddressDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddressDAO {

    public static AddressDTO getAddressById(int id) {
        String query = "SELECT * FROM Address WHERE id = ?";
        AddressDTO address = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                address = new AddressDTO();
                address.setId(rs.getInt("id"));
                address.setName(rs.getString("name"));
                address.setParentId(rs.getObject("parent_id") != null ? rs.getInt("parent_id") : null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }

    // Recursive method to retrieve full address path
   public static String getFullAddressPath(int addressId) {
    AddressDTO address = getAddressById(addressId);
    if (address == null) {
        System.out.println("Address not found for ID: " + addressId);
        return null;
    }

    // Base case: no parent, return the name of this address
    if (address.getParentId() == null) {
        return address.getName();
    }

    // Recursive call to get the parent's address path
    String parentPath = getFullAddressPath(address.getParentId());
    if (parentPath != null) {
        return parentPath + ", " + address.getName();
    } else {
        return address.getName(); // Fallback if parent path retrieval fails
    }
}
}
