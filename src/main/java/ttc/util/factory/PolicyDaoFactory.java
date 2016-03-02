package ttc.util.factory;

import java.util.Properties;
import java.io.IOException;

import ttc.dao.AbstractDao;
import ttc.dao.PolicyDao;

import ttc.exception.integration.IntegrationException;

public class PolicyDaoFactory extends AbstractDaoFactory{
    public AbstractDao getAbstractDao(){
        return new PolicyDao();
    }
}
