package ttc.command;

import ttc.context.RequestContext;
import ttc.context.ResponseContext;

import ttc.util.MySqlConnectionManager;

import ttc.exception.business.BusinessLogicException;
import ttc.exception.integration.IntegrationException;

import ttc.util.factory.AbstractDaoFactory;
import ttc.dao.AbstractDao;
import java.util.Map;
import java.util.HashMap;
import ttc.bean.UserBean;
import java.util.List;

public class LockingCommand extends AbstractCommand{
    public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
		try{
            RequestContext reqc = getRequestContext();

            String userStatus = reqc.getParameter("userStatus")[0];

            Map params = new HashMap();
            params.put("userStatus",userStatus);
            params.put("where","where user_status_flag>0 and user_status_flag<? ");

            MySqlConnectionManager.getInstance().beginTransaction();

            AbstractDaoFactory factory = AbstractDaoFactory.getFactory("users");
            AbstractDao dao = factory.getAbstractDao();

            MySqlConnectionManager.getInstance().commit();
            MySqlConnectionManager.getInstance().closeConnection();

            List result = dao.readAll(params);
            resc.setResult(result);
            resc.setTarget("cautionResult");

            return resc;

        }catch(IntegrationException e){
            throw new BusinessLogicException(e.getMessage(),e);
        }catch(Exception e){
			throw new BusinessLogicException(e.getMessage(),e);
        }
    }
}
