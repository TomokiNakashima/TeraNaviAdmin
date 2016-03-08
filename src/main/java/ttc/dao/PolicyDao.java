package ttc.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ttc.util.MySqlConnectionManager;
import ttc.bean.Bean;
import ttc.bean.PolicyBean;
import ttc.bean.UserBean;
import ttc.exception.integration.IntegrationException;

public class PolicyDao implements AbstractDao{



    public int update(Map map)throws IntegrationException{
        return 0;
    }

    public int insert(Map map)throws IntegrationException{
        PreparedStatement pst = null;
        int result = 0;
        try{
            Connection cn = null;
            cn = MySqlConnectionManager.getInstance().getConnection();
            MySqlConnectionManager.getInstance().beginTransaction();

            String sql="insert into policy(policy_date,policy_body) values(sysdate(),?)";

            pst = cn.prepareStatement(sql);

            pst.setString(1, (String)map.get("policy"));


            result = pst.executeUpdate();

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

    public List readAll(Map map)throws IntegrationException{
        PreparedStatement pst = null;
		List result = new ArrayList();

		try{

			Connection cn = null;
			cn = MySqlConnectionManager.getInstance().getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("select policy_id,policy_date,policy_body ");
			sql.append("from policy order by policy_date desc");

			pst = cn.prepareStatement(new String(sql));
			ResultSet rs = pst.executeQuery();

			while(rs.next()){
				PolicyBean bean = new PolicyBean();
				bean.setId(rs.getString(1));
				bean.setDate(rs.getString(2));
				bean.setBody(rs.getString(3));

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

    public Bean read(Map map)throws IntegrationException{
        PreparedStatement pst = null;
		PolicyBean bean = null;

		try{
			Connection cn = null;
			cn = MySqlConnectionManager.getInstance().getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select policy_id,policy_date,policy_body ");
			sql.append("from policy");

			boolean flag = map.containsKey("where");

			if(flag){
				sql.append(map.get("where"));

			}

			sql.append(" order by policy_date desc");

			pst = cn.prepareStatement(new String(sql));

			if(flag){
				pst.setString(1,(String)map.get("value"));
			}

			ResultSet rs = pst.executeQuery();
			rs.next();

			bean = new PolicyBean();
			bean.setId(rs.getString(1));
			bean.setDate(rs.getString(2));
			bean.setBody(rs.getString(3));



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
		return bean;
    }

}
