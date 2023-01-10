package org.qf.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.qf.entity.User;
import org.qf.service.UserService;
import org.qf.utils.ResultVO;
import org.qf.vo.RequestVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController  //异步请求
@Api(value = "提供用户信息登录、查询等相关的接口", tags = "用户管理")
@CrossOrigin  //运行跨域请求
public class UserController {

    // 调用业务层接口
    @Resource
    private UserService userService;

    @ApiOperation("用户登录接口")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(dataType = "string", name = "name", value = "用户姓名"),
                    @ApiImplicitParam(dataType = "string", name = "password", value = "用户密码")
            }
    )
    @GetMapping("/login")  //请求路径
    public ResultVO login(String name, String password) {
        System.out.print(name + "----------------------------");
        System.out.println(password + "--------------------");
        return userService.login(name, password);
    }

    /**
     * 条件分页查询：模糊查询，分页功能，查询所有
     *
     * @param pageNum  分页查询的页码
     * @param pageSize 分页查询的 一页显示的数据量
     * @return
     * @RequestParam指请求参数设置 defaultValue 设置默认值 当请求参数值为null，值就是默认值
     */
    @GetMapping("/findAll")
    public ResultVO findAll(@RequestParam(defaultValue = "1") int pageNum,
                            @RequestParam(defaultValue = "3") int pageSize,
                            String name) {
        System.out.println(pageNum + "================" + pageSize);
        System.out.println("模糊查询：" + name);
        return userService.pageUser(pageNum, pageSize, name);
    }

    /**
     * 新增用户
     */
    @PostMapping("/addUser")
    public ResultVO addUser(User user) {
        System.out.println("新增用户功能：" + user);
        return userService.add(user);
    }

    /**
     * 删除用户
     * 路径传参,局限性：太多参数不适合使用路径传参方式
     * /delUser/{uid}/{a}
     * http://localhost:8090/delUser/13/2
     */
    @GetMapping("/delUser/{uid}")
    public ResultVO delUser(@PathVariable("uid") int uid) {
        System.out.println("删除功能：" + uid);
        return userService.del(uid);
    }

    /**
     * 修改功能
     * Failed to convert property value of type 'java.lang.String'
     *  to required type 'java.util.Date' for property 'birthday'
     */
    @GetMapping("/updateUser")
    public ResultVO updateUser(RequestVo requestVo){
        System.out.println("修改用户功能："+requestVo);
        return userService.update(requestVo);
    }
    
    @GetMapping("/findSbs")
    public ResultVO findSbs(@RequestParam(defaultValue = "1") int pageNum,
                            @RequestParam(defaultValue = "3") int pageSize,
                            String name) {
        System.out.println(pageNum + "================" + pageSize);
        System.out.println("模糊查询：" + name);
        return userService.FindSbs(pageNum, pageSize);
    }

}
