package org.zerock.springex.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.TodoDTO;

@Controller
@Log4j2
public class SampleController {

    @GetMapping("/hello")
    public void hello() {
        log.info("hello");
    }
    @GetMapping("/ex4")
    public void ex4(Model model){

        log.info(".......");
        model.addAttribute("message", "Hello World");
    }
    @GetMapping("/ex4_1")
    public void ex4_1(TodoDTO todoDTO, Model model){ // 스프링의 MVC의 컨트롤러는 자바 빈즈 형식의 사용자 정의 클래스 인 경우 별도의 처리 없이 화면으로 객체 전달함
        //그래서 해당 todoDTO 객체를 별도의 처리 없이 ${todoDTO}를 이용할 수 있다
        log.info(".......");
    }
    @GetMapping("/ex5")
    public String ex5(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("name", "ABC");
        redirectAttributes.addFlashAttribute("result", "success");

        return "redirect:/ex6";
    }
    @GetMapping("/ex6")
    public void ex6(){

    }
    @GetMapping("/ex7")
    public void ex7(String p1, int p2){
        log.info("p1......." + p1);
        log.info("p2......." + p2);
    }

}
