package ttc.util.factory;

import java.util.Properties;
import java.io.IOException;

import ttc.dao.AbstractDao;
import ttc.dao.DirectMessageDao;

import ttc.exception.integration.IntegrationException;

public class DirectMessageDaoFactory extends AbstractDaoFactory{
    public AbstractDao getAbstractDao(){
        return new DirectMessageDao();
    }
}
