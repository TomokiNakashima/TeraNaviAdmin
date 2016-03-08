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
import ttc.util.mailer.AbstractMailer;
import ttc.util.mailer.GmailMailer;

public class CautionCommand extends AbstractCommand{
    public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
		try{
            RequestContext reqc = getRequestContext();

            String toUserId = reqc.getParameter("toUserId")[0];

            Map params = new HashMap();
            params.put("toUserId",toUserId);
            params.put("where","user_id = ?");
            params.put("value",toUserId);

            MySqlConnectionManager.getInstance().beginTransaction();

            AbstractDaoFactory factory = AbstractDaoFactory.getFactory("users");
            AbstractDao dao = factory.getAbstractDao();
            UserBean ub = (UserBean)dao.read(params);
            MySqlConnectionManager.getInstance().commit();
            MySqlConnectionManager.getInstance().closeConnection();

            String toAddress = ub.getMailAddress();

            String title = "TeraNaviからの警告";
            String mess = "違反行為が見られました。";

            AbstractMailer mailer = new GmailMailer();
            mailer.setAddress("teranavi.info@gmail.com");
            mailer.setAccount("teranavi.info@gmail.com");
            mailer.setPass("#TeraInfomation");
            String result = mailer.sendMail(toAddress, title, mess);

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
