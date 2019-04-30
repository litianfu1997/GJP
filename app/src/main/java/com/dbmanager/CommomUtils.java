package com.dbmanager;

import android.content.Context;

import com.gwj.mygreendao.greendao.gen.AccountDao;
import com.gwj.mygreendao.greendao.gen.UserDao;
import com.nnxy.gjp.entity.Account;
import com.nnxy.gjp.entity.User;


import java.util.List;


public class CommomUtils {
    private  DaoManager manager;


    public CommomUtils(Context context){
        this.manager=DaoManager.getInstance();
        this.manager.init(context);
    }

    /***
     * 关闭数据库
     */
    public void close(){
        manager.claoseConnection();
    }

    /**
     * 增加用户
     * */
    public  boolean insertUser(User user){
        boolean flag = false;
       flag = manager.getDaoSession().insert(user) != -1 ? true : false;

        return flag;

    }

    /**
     * 增加账务
     * */
    public  boolean insertAccount(Account account){
        boolean flag = false;
        flag = manager.getDaoSession().insert(account) != -1 ? true : false;

        return flag;

    }


    /***
     * 更新用户
     * @param user
     * @return
     */

    public void updateUser(User user){
        manager.getDaoSession().update(user);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteUser(Integer id){
        manager.getDaoSession().getUserDao().deleteByKey(id);
    }
    /***
     * 查询用户
     * @return
     */
    public List<User> queryAllUser(){//查询全部
        return manager.getDaoSession().loadAll(User.class);
    }

    public List<User> queryUser(Integer id){
        return  manager.getDaoSession().queryBuilder(User.class).where(UserDao.Properties.UserId.eq(id)).list();
    }

    public String queryById4Password(Integer key){
        return  manager.getDaoSession().load(User.class,key).getPassword();
    }
    public List<User> queryUser(String username){
        return  manager.getDaoSession().queryBuilder(User.class).where(UserDao.Properties.UserCode.eq(username)).list();
    }
    public List<User> queryUserId(String username){

        return  manager.getDaoSession().queryBuilder(User.class).where(UserDao.Properties.UserCode.eq(username)).list();
    }

    /**
     * 查询账务
     */
    public List<Account> queryAllAccount(Integer id){
        return manager.getDaoSession().queryBuilder(Account.class).where(AccountDao.Properties.UserId.eq(id)).list();
    }

    /**
     * 删除账务
     */
    public void deleteAccount(Integer id){
        manager.getDaoSession().getAccountDao().deleteByKey(id);
    }



}
