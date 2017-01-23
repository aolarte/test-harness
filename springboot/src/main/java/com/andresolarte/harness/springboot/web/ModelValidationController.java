package com.andresolarte.harness.springboot.web;

import com.andresolarte.harness.springboot.model.TestModel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/model")
public class ModelValidationController {

    @RequestMapping(method = RequestMethod.POST)
    public String post(@Valid  @RequestBody TestModel model) {
        return "ok: " + model.getPercent();
    }
}
