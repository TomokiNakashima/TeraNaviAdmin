package ttc.exception.integration;

import ttc.exception.business.BusinessLogicException;

public class IntegrationException extends BusinessLogicException{
    public IntegrationException(String mess,Throwable t){
        super(mess,t);
    }
}
