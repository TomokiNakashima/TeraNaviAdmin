package ttc.util.factory;

import java.util.Properties;
import java.io.IOException;

import ttc.dao.AbstractDao;
import ttc.dao.RulesDao;

import ttc.exception.integration.IntegrationException;

public class RulesDaoFactory extends AbstractDaoFactory{
    public AbstractDao getAbstractDao(){
        return new RulesDao();
    }
}
