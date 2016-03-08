package ttc.exception;

import ttc.exception.integration.IntegrationException;

public class ResourceException extends IntegrationException{
    public ResourceException(String mess,Throwable t){
        super(mess,t);
    }
}
