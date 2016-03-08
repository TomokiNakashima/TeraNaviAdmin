package ttc.util.factory;

import java.util.Properties;
import java.io.IOException;

import ttc.dao.AbstractDao;
import ttc.dao.ChatDao;

import ttc.exception.integration.IntegrationException;

public class ChatDaoFactory extends AbstractDaoFactory{
    public AbstractDao getAbstractDao(){
        return new ChatDao();
    }
}
