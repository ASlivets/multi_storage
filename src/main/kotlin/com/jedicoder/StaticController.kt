package com.jedicoder

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

@RestController
class StaticController {

    @RequestMapping("/")
    fun index(): ModelAndView = ModelAndView("index")

    @RequestMapping("/create")
    fun create(): ModelAndView = ModelAndView("create")

    @RequestMapping("/read")
    fun read(): ModelAndView = ModelAndView("read")

    @RequestMapping("/update")
    fun update(): ModelAndView = ModelAndView("update")

    @RequestMapping("/delete")
    fun delete(): ModelAndView = ModelAndView("delete")
}
