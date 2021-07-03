package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
public class SampleController {

    @GetMapping("/external")
    public void externalRequest(
                               HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String htmlPage = "<html><body onload='document.forms[0].submit()'>" +
                String.format("<form action='%s' method='post'>", "https://google.com") +
                String.format("<input type='hidden' name='Token' value='%s'>", "token1234") +
                //String.format("<input type='hidden' name='RedirectURL' value='%s'>"+ paymentRequestDto.getCallBackUrl()
                "</form></body></html>";
        //response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.write(htmlPage);
        writer.flush();
    }

    @GetMapping("/another-url")
    public ResponseEntity<String> anotherRequest() {
        return new ResponseEntity<>("hello ", HttpStatus.OK);
    }

    @GetMapping("/forward")
    public void forwardRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("param1", "myparam");

        //return "redirect:/"+another-url;
        response.sendRedirect("/another-url");
    }

    //return jsp file as a view, you need to create webapp directory and WEB-INF and jsp directiories within.
    @GetMapping("/view")
    public ModelAndView viewRequest(Model model) {
        model.addAttribute("action","http://localhost:8811/post");
        model.addAttribute("token","token1234");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jspViewFile");
        return modelAndView;
    }

    @PostMapping("/post")
    public ResponseEntity<String> post(@RequestParam(name = "Token") String token){
        Logger log = LoggerFactory.getLogger(SampleController.class);
        log.info("token %s",token);
        return new ResponseEntity(token,HttpStatus.OK);
    }

}
