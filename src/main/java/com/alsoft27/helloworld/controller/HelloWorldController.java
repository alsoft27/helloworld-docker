package com.alsoft27.helloworld.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@RequestMapping(value = "/helloworld")
	public String hello() {
		return "Hello world by alsoft27";
	}
}
