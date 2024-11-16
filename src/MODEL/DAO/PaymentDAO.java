package MODEL.DAO;

import MODEL.DTO.Donation.PaymentDTO;
import MODEL.DTO.Donation.PaymentMethodDTO;
import MODEL.Patterns.paymentstrategy.PaymentMethode;
import MODEL.Patterns.paymentstrategy.PaymentStategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class PaymentDAO {
    private final Connection connection;

    public PaymentDAO(Connection connection) {
        this.connection = connection;
    }

    // Ensure Payment Methods are added to the paymentmethod table if they don't already exist
    public void addPaymentMethodIfNotExists(String paymentMethodName) throws SQLException {
        String sql = "SELECT id FROM paymentmethod WHERE name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, paymentMethodName);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                String insertSql = "INSERT INTO paymentmethod (name) VALUES (?)";
                try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
                    insertStmt.setString(1, paymentMethodName);
                    insertStmt.executeUpdate();
                }
            }
        }
    }

    // Create a payment and handle the cumulative amount
    public int createPayment(PaymentDTO payment, PaymentStategy paymentStrategy) throws SQLException {
        // Ensure required payment methods are present in the database
        addPaymentMethodIfNotExists("Fawry");
        addPaymentMethodIfNotExists("Credit Card");

        String sql = "INSERT INTO payment (payment_method_id, timestamp, is_deleted) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, payment.getPaymentMethodId());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(payment.getTimestamp()));
            stmt.setBoolean(3, payment.getIsDeleted());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int paymentId = generatedKeys.getInt(1);

                    // Using the Strategy Pattern to handle payment processing
                    PaymentMethode paymentMethod = new PaymentMethode(paymentStrategy);
                    paymentMethod.executePayment(payment);

                    return paymentId;
                } else {
                    throw new SQLException("Failed to create payment, no ID obtained.");
                }
            }
        }
    }

    // Link DonationRecord to Payment in the donationrecord_payment table
    public void linkDonationToPayment(int donationId, int paymentId) throws SQLException {
        String sql = "INSERT INTO donationrecord_payment (donation_id, payment_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, donationId);
            stmt.setInt(2, paymentId);
            stmt.executeUpdate();
        }
    }

    // Retrieve payment details by ID
    public PaymentDTO getPaymentById(int paymentId) throws SQLException {
        String sql = "SELECT id, payment_method_id, timestamp, is_deleted FROM payment WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, paymentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    PaymentDTO payment = new PaymentDTO();
                    payment.setId(rs.getInt("id"));
                    payment.setPaymentMethodId(rs.getInt("payment_method_id"));
                    payment.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
                    payment.setIsDeleted(rs.getBoolean("is_deleted"));
                    return payment;
                }
            }
        }
        return null;
    }
}
