package org.wlgzs.website.service.serviceimpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wlgzs.website.dao.UserDao;
import org.wlgzs.website.entity.TbUser;
import org.wlgzs.website.service.UserService;
import org.wlgzs.website.util.AesUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author zjg
 * @date 2018/8/14 8:28
 * @Description 用户业务类
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean insertUser(TbUser tbUser) {
        List<TbUser> tbUserDb = userDao.selectList(
                new EntityWrapper<TbUser>().eq("username", tbUser.getUsername())
        );
        if (tbUserDb.size() > 0) {
            return false;
        }
        if (StringUtils.isNotBlank(tbUser.getPassword())){
            AesUtil aesUtil = new AesUtil(tbUser.getPassword());
            //加密后密码
            String encryptPassword = aesUtil.encrypt();
            //加密后的密码加盐
            String encPasswordSalt = encryptPassword+tbUser.getSalt();
            //加密密码加盐后再加密
            aesUtil.setContent(encPasswordSalt);
            String results = aesUtil.encrypt();
            tbUser.setPassword(results);
        }
        userDao.insert(tbUser);
        return true;
    }

    @Override
    public TbUser login(String username, String password) {
        TbUser[] user = {null};
        List<TbUser> userList = userDao.selectList(
                new EntityWrapper<TbUser>().eq("username", username)
        );
        if (userList.size() > 0) {
            AesUtil aesUtil = new AesUtil(password);
            //加密的密码
            String encyptPassword = aesUtil.encrypt();
            //加盐密码验证
            userList.forEach((TbUser u) ->{
                String encyPassSalt = encyptPassword+u.getSalt();
                aesUtil.setContent(encyPassSalt);
                String result = aesUtil.encrypt();
                if (result != null && result.equals(u.getPassword())) {
                    user[0] = u;
                }
            });
            /*Iterator iterator = userList.iterator();
            while(iterator.hasNext()){
                TbUser u = (TbUser) iterator.next();
                //加密的密码加盐
                String encyPassSalt = encyptPassword+u.getSalt();
                aesUtil.setContent(encyPassSalt);
                //加密的密码加盐后再加密
                String result = aesUtil.encrypt();
                System.out.println("加密的密码加盐后再加密"+result);
                if (result != null && result.equals(u.getPassword())) {
                    user[0] = u;
                }
            }*/
        }
        return user[0];
    }
}
