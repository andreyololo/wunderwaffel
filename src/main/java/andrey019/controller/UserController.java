package andrey019.controller;

import andrey019.dao.TodoListDao;
import andrey019.dao.UserDao;
import andrey019.model.JsonMessage;
import andrey019.model.dao.TodoList;
import andrey019.model.dao.User;
import andrey019.model.dao.UserConfirmation;
import andrey019.service.HtmlGenerator;
import andrey019.service.dao.TodoService;
import andrey019.service.maintenance.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private LogService logService;

    @Autowired
    private TodoListDao todoListDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TodoService todoService;

    @Autowired
    private HtmlGenerator htmlGenerator;

    @RequestMapping({"/", ""})
    public String userPage() {
        return "user_page";
    }

    @RequestMapping(value = "/loadLists", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String loadLists(@RequestBody JsonMessage jsonMessage) {
        return htmlGenerator.generateTodoListsHtml(todoService.getAllTodoLists(getUserEmail()));
    }

    @RequestMapping("/ololo")
    public String userololo() {
        logService.accessToPage("user/ololo");
        return "ololo";
    }

    @RequestMapping("/json")
    public String tesingJson() {
        UserConfirmation userConfirmation = new UserConfirmation();
        userConfirmation.setDate(564354);
        userConfirmation.setCode("codeset");
        userConfirmation.setPassword("passset");
        userConfirmation.setEmail("emailset");
        return "json_test";//h
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String test(@RequestBody JsonMessage jsonMessage) {
        System.out.println("!!!  !!!");
        System.out.println(jsonMessage);
        return jsonMessage.getListId() + " = " + getUserEmail() + "<button id=\"99999\" class=\"btn btn-default\" type=\"button\" onclick=\"oneMore(event)\">json button</button>";//sdf
    }

    @RequestMapping("testdao")
    @ResponseBody
    public String testDao() {
        innerDao();
        return "done";
    }

    private String getUserEmail(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    private void innerDao() {
        User user = userDao.getById(1);
        TodoList todoList = new TodoList();
        todoList.setUser(user);
        todoList.addUsers(user);
        todoList.setName("spring name ололо");
        todoListDao.save(todoList);
        System.out.println(todoListDao.getByUsers(1).size());
        System.out.println(todoListDao.getUsersByTodoListId(1).size());
    }
}
