package com.easyshopping.easyshopping.urlcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EasyshoppingURLController {

    @RequestMapping("/test")
    public String test(){
        return "test";
    }
    
}
