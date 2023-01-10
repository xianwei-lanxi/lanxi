package org.qf.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.fastinfoset.util.ValueArray;
import org.qf.entity.User;
import org.qf.mapper.UserMapper;
import org.qf.utils.ResultVO;
import org.qf.vo.RequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;


@Service
public class UserService {

    //调用mapper层接口
    @Autowired
    private UserMapper userMapper;


    //登录功能
    public ResultVO login(String name, String password) {
        User u = new User();
        u.setUsername(name);
        User user = userMapper.selectOne(u);
        if (user == null) {
            //用户不存在
            return new ResultVO(1000, "登录失败，用户不存在!", null);
        } else {
            if (password.equals(user.getPassword())) {
                //登录成功
                return new ResultVO(1001, "登录成功", user);
            } else {
                return new ResultVO(1002, "登录失败，密码错误！", null);
            }
        }
    }

    /**
     * 条件分页查询：模糊查询，分页功能，查询所有
     *
     * @param pageNum  页码
     * @param pageSize 一页显示的数据量
     * @return PageHelper 插件实现分页查询功能：
     * 在查询所有的方法语句上使用  PageHelper.startPage(pageNum,pageSize); 代码即可实现分页
     */
    public ResultVO pageUser(int pageNum, int pageSize, String name) {
        try {
            // 可能会出异常
            PageHelper.startPage(pageNum, pageSize);
//        查询所有
//            List<User> userList = userMapper.selectAll();
//            插件工具，使用工具管理参数，拼接sql
            Example example = new Example(User.class);
            Example.Criteria criteria = example.createCriteria();
//            模糊查询参数设置  StringUtils.isEmpty(name) String 工具，检验字符串是否为null或""，
//            如果为null或""则返回true
            if (!StringUtils.isEmpty(name)) {
                /*
                最终sql变成：
                select * from user where username like "%name%" limit pageNum,pageSize
                需要两个参数：
                property: 属性
                value: 值
                */
                criteria.andLike("username", "%" + name + "%");
            }
            List<User> userList = userMapper.selectByExample(example);
            // userPageInfo 包含了分页的所有信息
            PageInfo<User> userPageInfo = new PageInfo<>(userList);
            return new ResultVO(2000, "请求成功", userPageInfo);

        } catch (Exception e) {
            e.printStackTrace();// 打印异常
            return new ResultVO(9999, "网络异常", null);
        }

    }

    /**
     * 新增用户功能
     *
     * @param user
     * @return
     */
    public ResultVO add(User user) {
        try {
//            new Date() 当前时间
            user.setCreatetime(new Date());
            //新增方法  返回值是影响行数——如果插入一条数据，返回值为 1， 如果插入10条数据，返回值10
            int i = userMapper.insert(user);
            if (i > 0) { // 说明 新增成功
                return new ResultVO(3000, "新增成功", i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultVO(9999, "网络异常", null);
    }

    /**
     * 删除功能
     */
    public ResultVO del(int uid) {
        try {
//            根据主键删除
            int i = userMapper.deleteByPrimaryKey(uid);
            if (i > 0) {
                return new ResultVO(4000, "删除成功", i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultVO(9999, "网络异常", null);
    }

    /**
     * 修改用户
     * @param requestVo
     * @return
     */
    public ResultVO update(RequestVo requestVo) {
        try {
            User user = new User();
            user.setId(requestVo.getId());
            user.setUsername(requestVo.getUsername());
            user.setSex(requestVo.getSex());
            user.setAddress(requestVo.getAddress());
            user.setPhone(requestVo.getPhone());
            System.out.println(user.toString());
//        使用 hutool 工具转换日期格式
            Date birthday = DateUtil.parse(requestVo.getBirthday());
            user.setBirthday(birthday);
//        修改时间
            user.setUpdatetime(new Date());
//        修改，此时参数是 user 对象
            int i = userMapper.updateByPrimaryKeySelective(user);
            if (i > 0) {
                return new ResultVO(5000, "修改成功", i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultVO(9999, "网络异常", null);
    }

    public ResultVO FindSbs(int pageNum, int pageSize) {
        try{
            PageHelper.startPage(pageNum, pageSize);
            List<User> users = userMapper.selectAll();
            return new ResultVO(300,"请求成功",users);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResultVO(301,"网络异常",null);
        }

    }
}
