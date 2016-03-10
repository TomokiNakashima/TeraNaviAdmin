package ttc.dao;

import java.util.List;

import java.util.Map;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ttc.bean.Bean;
import ttc.bean.SignUpKeyBean;


import ttc.exception.integration.IntegrationException;
import ttc.exception.integration.SignUpKeyInvalidException;

import ttc.util.MySqlConnectionManager;

public class SignUpKeysDao implements AbstractDao{
    public int insert(Map map)throws IntegrationException{
        Connection cn = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            cn = MySqlConnectionManager.getInstance().getConnection();
            StringBuffer sql = new StringBuffer();
            sql.append("insert into sign_up_keys(sign_up_key,key_valid_date) ");
            sql.append("values(?,?)");
            
            pst = cn.prepareStatement(new String(sql));
            
            pst.setString(1,(String)map.get("key"));
            pst.setString(2,(String)map.get("endDate"));
			
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
	
    public int update(Map map)throws IntegrationException{
//      有効期限の過ぎた登録キーを削除する処理を呼び出してます
		int result = delete();

        return result;
    }
	
	private int delete()throws IntegrationException{
		PreparedStatement pst = null;
        int result = 0;
        try{
            
            Connection cn = null;
            cn = MySqlConnectionManager.getInstance().getConnection();
            MySqlConnectionManager.getInstance().beginTransaction();
            StringBuffer sql = new StringBuffer();
            sql.append("delete from sign_up_keys ");
			sql.append("where key_valid_date < sysdate()");
            

            pst = cn.prepareStatement( new String(sql) );

            //タイトルを変更
            

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
	
    public Bean read(Map map)throws IntegrationException{
        PreparedStatement pst = null;
		SignUpKeyBean bean = null;
		
		try{
			Connection cn = null;
			cn = MySqlConnectionManager.getInstance().getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select sign_up_key ");
			sql.append("from sign_up_keys where key_status=0 and sign_up_key=?");
			
			pst = cn.prepareStatement(new String(sql));
			
			pst.setString(1,(String)map.get("key"));
			
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()){
				bean = new SignUpKeyBean();
				bean.setSignUpKey(rs.getString(1));
				
			}else{
				throw new SignUpKeyInvalidException("登録キーが違います", null);
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
		return bean;
    }
    

    public List readAll(Map map)throws IntegrationException{
        PreparedStatement pst = null;
		
		List result = new ArrayList(); 
		
		try{
			Connection cn = null;
			cn = MySqlConnectionManager.getInstance().getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select sign_up_key,key_valid_date ");
			sql.append("from sign_up_keys where key_valid_date >= sysdate() order by key_valid_date");
			
			pst = cn.prepareStatement(new String(sql));
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				SignUpKeyBean bean = new SignUpKeyBean();
				bean.setSignUpKey(rs.getString(1));
				bean.setKeyValidDate(rs.getString(2));
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
}
