package ttc.exception.business;

public class IntegrationException extends BusinessLogicException{
    public IntegrationException(String mess,Throwable t){
        super(mess,t);
    }
}
