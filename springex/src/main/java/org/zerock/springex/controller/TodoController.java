package org.zerock.springex.controller;
// 서블릿 중심 MVC에서는 doGet, doPost를 이용해서 제한적인 메소드를 오버라이드 해서 사용했지만
// 스프링 MVC의 경우 하나의 컨트롤러를 이용해서 여러 경로의 호출을 모두 처리가능함


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.service.TodoService;

import java.time.LocalDate;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController { // 여러 개의 컨트롤러를 하나의 클래스로 묶을 수 있고 각 기능을 메소드 단위로 설계 할 수 있음


    private final TodoService todoService;

    @RequestMapping("/list") // 모든 HTTP 메소드 처리가능
    public void list(Model model){
        log.info("todo list.....");
    }

    @GetMapping("/register")
    public void registerGET(){
        log.info("GET todo register....");
    }
    @PostMapping("/register")
    public String registerPost(TodoDTO todoDTO, RedirectAttributes redirectAttributes, BindingResult bindingResult){
        log.info("POST todo register....");

        if(bindingResult.hasErrors()){
            log.info("has errors...");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }

        log.info(todoDTO);

        todoService.register(todoDTO);



        return "redirect:/todo/list";
    }



}
