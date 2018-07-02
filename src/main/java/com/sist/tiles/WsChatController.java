package com.sist.tiles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class WsChatController {
    @RequestMapping("main/wschat.do")
    public String wsChat_main()
    {
   	 return "wschat/wsChat_main";
    }
}
