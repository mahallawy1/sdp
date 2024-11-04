package MODEL.DAO;

import MODEL.DTO.DonationRecordDTO;
import MODEL.DTO.DonationRecordTypeDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DonationRecordDAO {

    private final Connection connection;

    public DonationRecordDAO(Connection connection) {
        this.connection = connection;
    }

    public void createDonationRecord(DonationRecordDTO donationRecord, List<DonationRecordTypeDTO> donationTypes) throws SQLException {
        String donationRecordSql = "INSERT INTO donationrecord (user_id, donate_date, CumilativeAmount, status) VALUES (?, ?, ?, ?)";
        String donationRecordTypeSql = "INSERT INTO donationrecordtype (donation_record_id, donation_type_name, amount) VALUES (?, ?, ?)";

        try (PreparedStatement donationRecordStmt = connection.prepareStatement(donationRecordSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            donationRecordStmt.setInt(1, donationRecord.getUserId());
            donationRecordStmt.setDate(2, new Date(donationRecord.getDonateDate().getTime()));
            donationRecordStmt.setInt(3, donationRecord.getCumulativeAmount());
            donationRecordStmt.setBoolean(4, donationRecord.isStatus());
            donationRecordStmt.executeUpdate();

            var generatedKeys = donationRecordStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int donationRecordId = generatedKeys.getInt(1);
                try (PreparedStatement donationRecordTypeStmt = connection.prepareStatement(donationRecordTypeSql)) {
                    for (DonationRecordTypeDTO type : donationTypes) {
                        donationRecordTypeStmt.setInt(1, donationRecordId);
                        donationRecordTypeStmt.setString(2, type.getDonationTypeName());
                        donationRecordTypeStmt.setInt(3, type.getAmount());
                        donationRecordTypeStmt.executeUpdate();
                    }
                }
            }
        }
    }
}
