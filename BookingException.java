/*
 * created by Katies Truong - s3671053
 * 19/08/2017
 * class BookingException and subclass Exception
 * Stage 5
 */
// custom Exception class that allows the message to appear when thrown in BookingSystem
// class.
public class BookingException extends Exception{
    // creating own Exception class
    public BookingException(String message) {
        super(message);
    }
    public BookingException() {
        super();
    }
}
