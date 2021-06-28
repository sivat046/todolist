package com.example.todolist.controller;


import com.example.todolist.model.Todo;
import com.example.todolist.service.TodoRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


//import com.example.codedna.todo.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController extends Todo{
//    @Autowired
//    TodoService service; //System repo

    @Autowired
    TodoRepository repository; //JPA repo

    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        //Date-dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
    @RequestMapping(value="/list-todos",method= RequestMethod.GET)
    public String showTodos(ModelMap model)
    {
        String obj = getLoggedInUserName(model);
        model.put("todos",repository.findByUser(obj));
//        model.put("todos",service.retrieveTodos(obj));
        return "list-todos";
    }

    @RequestMapping(value="/signup",method= RequestMethod.GET)
    public String signupPage(ModelMap model)
    {
        return "signup";
    }

    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    public String createAccount(ModelMap model, @RequestParam String username,@RequestParam String email,@Valid Todo todo,@RequestParam String password,@RequestParam String repassword) {

        System.out.println("Update This is date format: " + todo.getTargetDate());
//        if (result.hasErrors()) {
//            return "todo";
//        }
//        else if(todo.getDesc().length() <6)
//        {
//            model.put("errorMessage"," Description Length must be greater than or equal to 6 ");
//            return "todo";
//        }
        System.out.println(username+email);
        todo.setUser(username);
        todo.setEmailId(email);
        todo.setPrimaryPassword(password);
        todo.setSecondaryPassword(repassword);


        if(password != repassword)
        {
            model.put("errorMessage"," Passwords are nnot same please try again");
            return "createAccount";
        }
//        System.out.println((String) model.get("repassword")+(String) model.get("password")+(String) model.get("email")+ (String)model.get("username"));
        System.out.println("Entry : "+todo.getUser() +" "+todo.getEmailId() +"  "+todo.getPrimaryPassword()+"  "+ todo.getPrimaryPassword());
        repository.save(todo);
        todo.setId(0);
//        service.updateTodo(todo);

        return "redirect:/list-todos";
    }
//        private String getLoggedInUserName(ModelMap model) {
//        return (String) model.get("name");
//    }
    private String getLoggedInUserName(ModelMap model) {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();

        return principal.toString();
    }
    @RequestMapping(value="/add-todo",method= RequestMethod.GET)
    public String showAddTodoPage(ModelMap model)
    {
        model.addAttribute("todo",new Todo(0, getLoggedInUserName(model),"",new Date(),false));
        String obj = getLoggedInUserName(model);
        return "todo";
    }

    @RequestMapping(value="/add-todo",method= RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result)
    {
        System.out.println("tODO Comingsssss");

        if(result.hasErrors()){
            return "todo";
        }
        else if(todo.getDesc().length() <=5)
        {
            model.put("errorMessage"," Description Length must be greater than or equal to 5 ");
            return "todo";
        }
        todo.setUser(getLoggedInUserName(model));
        repository.save(todo);
        System.out.println("This is date formats: " + todo.getTargetDate());
//        service.addTodo(getLoggedInUserName(model), todo.getDesc(), todo.getTargetDate(),false);
        return "redirect:/list-todos";
    }
    @RequestMapping(value="/delete-todo",method= RequestMethod.GET)
    public String deleteTodo(@RequestParam int id)
    {
//        if(id ==1) throw new RuntimeException("Wrong ID");
        System.out.println(id);
        repository.deleteById(id);
//        service.deleteTodo(id);
        return "redirect:/list-todos";
    }
    @RequestMapping(value="/update-todo",method= RequestMethod.GET)
    public String showUpdateTodo(@RequestParam int id, ModelMap model)
    {
        System.out.println(id);
//        Todo todo = service.retrieveTodo(id);
        Optional<Todo> todo = repository.findById(id);
        System.out.println("Hitman show " +todo);
        model.put("todo",todo);
        return "todo";
    }


    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        System.out.println("Update This is date format: " + todo.getTargetDate());
        if (result.hasErrors()) {
            return "todo";
        }
        else if(todo.getDesc().length() <6)
        {
            model.put("errorMessage"," Description Length must be greater than or equal to 6 ");
            return "todo";
        }
        todo.setUser(getLoggedInUserName(model));
        System.out.println("Update This is date format: " + todo.getTargetDate());
        repository.save(todo);
//        service.updateTodo(todo);

        return "redirect:/list-todos";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "login";
    }
}
