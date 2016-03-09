package ttc.command;

import ttc.context.RequestContext;
import ttc.context.ResponseContext;

import ttc.util.MySqlConnectionManager;

import ttc.exception.integration.IntegrationException;
import ttc.exception.business.BusinessLogicException;

import java.util.Map;
import java.util.HashMap;


import ttc.util.factory.AbstractDaoFactory;
import ttc.dao.AbstractDao;

import java.util.List;


public class ShowKeyCommand extends AbstractCommand {

	public ResponseContext execute(ResponseContext resc) throws BusinessLogicException {
		try {
			RequestContext reqc = getRequestContext();

			MySqlConnectionManager.getInstance().beginTransaction();
			AbstractDaoFactory factory = AbstractDaoFactory.getFactory("keyCreate");
			AbstractDao dao = factory.getAbstractDao();

			Map param = new HashMap();
			
			List result = dao.readAll(param);

			MySqlConnectionManager.getInstance().commit();
			MySqlConnectionManager.getInstance().closeConnection();


			resc.setResult(result);
			resc.setTarget("signkeyResult");
			
			return resc;
		} catch (IntegrationException e) {
			throw new BusinessLogicException(e.getMessage(), e);
		}
	}
}
