package com.cat.test.web.controller;

import com.cat.test.log.BaseLogger;
import com.cat.test.web.entity.User;
import com.cat.test.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController implements BaseLogger {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/save")
    @ResponseBody
    public List<User> save() {
        logger.debug("user web>>>>>>>>>>>>>>>");
        List<User> list = new ArrayList<>();
        User zsy = new User(176L, "zsy", 32);
        User cjj = new User(182L, "cjj", 32);
        User crg = new User(96L, "crg", 33);
        userService.save(zsy);
        userService.save(cjj);
        userService.save(crg);

        list.add(zsy);
        list.add(cjj);
        list.add(crg);
        return list;
    }
}
