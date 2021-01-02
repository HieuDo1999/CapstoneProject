package entity.bike;

import java.sql.Timestamp;

public interface CalculateFees {
      long calculateFees(Timestamp start, Timestamp end);
      double calculateDeposit();
}
