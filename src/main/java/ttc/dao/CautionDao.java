package ttc.dao;

import java.text.SimpleDateFormat;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.sql.Date;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ttc.bean.CautionBean;
import ttc.bean.Bean;
import ttc.bean.UserBean;
import ttc.exception.integration.IntegrationException;
import ttc.util.MySqlConnectionManager;

import ttc.exception.NotLineException;


public class CautionDao implements AbstractDao{
    PreparedStatement pst=null;
    Connection cn=null;
    ResultSet rs=null;

    public List readAll(Map map)throws IntegrationException{
        List result = new ArrayList();
        PreparedStatement pst = null;

        try{
            Connection cn = null;
            cn = MySqlConnectionManager.getInstance().getConnection();
            MySqlConnectionManager.getInstance().beginTransaction();
            StringBuffer sql = new StringBuffer();

            sql.append("select * ");
            sql.append("from cautions order by caution_id desc");

            pst = cn.prepareStatement(new String(sql));


            ResultSet rs = pst.executeQuery();

            sql = new StringBuffer();

            sql.append("select user_name,user_icon_path,user_status_flag ");
            //sql.append("select user_name,user_icon_path,mail_address ");
            sql.append("from users where user_id = ?");

            String uSql = new String(sql);



            while(rs.next()){

                CautionBean bean = new CautionBean();
                bean.setId(rs.getString(1));
                bean.setCautionUser(rs.getString(3));
                bean.setDate(rs.getString(4));
                bean.setTitle(rs.getString(5));
                bean.setCautionBody(rs.getString(6));
                bean.setReportPageUrl(rs.getString(7));
                bean.setCautionFlag(rs.getString(8));

                String userId = rs.getString(2);
                String cautionUser = rs.getString(3);

                pst = cn.prepareStatement(uSql);

                pst.setString(1,userId);

                ResultSet rsx = pst.executeQuery();

                rsx.next();
                UserBean user = new UserBean();
                user.setUserName(rsx.getString(1));
                user.setIconPath(rsx.getString(2));
                user.setMailAddress(rsx.getString(3));

                bean.setUserId(user);

                pst = cn.prepareStatement(uSql);

                pst.setString(1,cautionUser);

                rsx = pst.executeQuery();

                rsx.next();
                user = new UserBean();

                user.setUserName(rsx.getString(1));
                user.setIconPath(rsx.getString(2));
                user.setUserStatus(rsx.getString(3));

                bean.setCautionUserId(user);

                result.add(bean);
            }



        }catch(SQLException e){
            throw new IntegrationException(e.getMessage(),e);
        }finally{
            try{
                if(pst!=null){
                    pst.close();
                }
            }catch(SQLException e){
                throw new IntegrationException(e.getMessage(),e);
            }
        }

        return result;

    }

    public int update(Map map)throws IntegrationException{
            int result=0;
            try{
                Connection cn = MySqlConnectionManager.getInstance().getConnection();
                StringBuffer sql = new StringBuffer();
                sql.append("update cautions set caution_flag=1");

                pst = cn.prepareStatement(new String(sql));
                result = pst.executeUpdate();

                System.out.println("\t処理件数 : "+result);

            }catch(SQLException e){
                MySqlConnectionManager.getInstance().rollback();
                throw new IntegrationException(e.getMessage(),e);
            }finally{
                try{
                    if(pst!=null){
                        pst.close();
                    }
                }catch(SQLException e){
                    throw new IntegrationException(e.getMessage(),e);
                }
            }

            return result;

    }

    public int insert(Map map)throws IntegrationException{

        return 0;
    }
    public Bean read(Map map) throws IntegrationException{
        return null;
    }


}
