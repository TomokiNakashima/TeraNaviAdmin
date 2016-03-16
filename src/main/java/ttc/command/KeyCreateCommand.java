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

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

public class KeyCreateCommand extends AbstractCommand {

	public ResponseContext execute(ResponseContext resc) throws BusinessLogicException {
		try {
			RequestContext reqc = getRequestContext();

			int count = Integer.parseInt(reqc.getParameter("count")[0]);

			//有効期限の設定のための日付の取得
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, count);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String endDate = formatter.format(calendar.getTime());
			
//			登録キーとして使用するランダム文字列の取得
			String signKey = RandomStringUtils.randomAlphanumeric(5);

			MySqlConnectionManager.getInstance().beginTransaction();
			AbstractDaoFactory factory = AbstractDaoFactory.getFactory("keyCreate");
			AbstractDao dao = factory.getAbstractDao();

//			使わなくなった登録キーをクリーンアップするためのメソッドの呼び出し
//			updateメソッドの内部でdelete用のメソッドを呼び出してます
			dao.update(new HashMap());
			
			Map param = new HashMap();
			param.put("key", signKey);
			param.put("endDate",endDate);
			dao.insert(param);
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
