package com.sist.tiles;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.data.KeyWordsVO;


@Controller
public class MainController {
    @RequestMapping("main/main.do")
    public String main_main(Model model)
    {
   	 
    	
    	
   	 return "main";
    }
}