/*
 * created by Katies Truong - s3671053
 * 31/07/2017
 * subclass ScheduledTour
 * Stage 3
 */
public class ScheduledTour extends Attraction {
    String tourDate, tourLeader;
    int maxGroupSize;

    // defines constructor that passes id, description and admission fees
    // from Attraction class to Scheduled Tour
    public ScheduledTour(String tourID,
                         String tourDescription,
                         double tourAdminFee,
                         String tourDate,
                         int maxGroupSize,
                         String tourLeader) {
        super(tourID ,tourDescription, tourAdminFee);
        // allows access to instance variables local to ScheduledTour class
        this.tourDate = tourDate;
        this.maxGroupSize = maxGroupSize;
        this.tourLeader = tourLeader;
    }

    // declaring mutator/setter class for maximum group sizes
    public void setMaxGroupSize(int maxGroupSize) {
        this.maxGroupSize = maxGroupSize;
    }

    // overrides sellPasses method in Attraction class
    public double sellPasses(int tourists) throws BookingException {
        if (tourists <= 0) {
            throw new BookingException("Error! Can't sell passes with zero tourists.");
        }
        else {
            super.sellPasses(tourists);
            return getBookingCount();
        }
    }

    // overrides printDetails method in Attraction class
    public void printDetails() {
        super.printDetails();
        System.out.println("Tour Date: " + tourDate);
        System.out.println("Tour Leader: " + tourLeader);
        System.out.println("Max Group Size: " + maxGroupSize);
    }
}
