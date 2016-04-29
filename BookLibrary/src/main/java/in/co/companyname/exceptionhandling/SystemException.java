package in.co.companyname.exceptionhandling;

public class SystemException extends Exception {

    /**
     * This is an User Defined Exception Class. This Class deals with the Database
     * Exceptions
     */
    private static final long serialVersionUID = 1L;

    public SystemException(String message){
        super(message);
    }
}
