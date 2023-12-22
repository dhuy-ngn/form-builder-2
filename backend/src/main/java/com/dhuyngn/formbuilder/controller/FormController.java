package com.dhuyngn.formbuilder.controller;

import com.dhuyngn.formbuilder.enums.*;
import com.dhuyngn.formbuilder.model.*;
import com.dhuyngn.formbuilder.service.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/forms")
@Validated
public class FormController {

  @Autowired
  private FormService service;

  @GetMapping
  public List<Form> getAllForms() {
    return service.getAllForms();
  }

  @GetMapping
  public List<Form> getFormByStatus(@Valid @RequestParam FormStatus status) {
    return service.getFormByStatus(status);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Form createForm(@Valid @RequestBody Form form) {
    return service.createForm(form);
  }

  @GetMapping("/{id}")
  public Form findById(@PathVariable @Min(1) Long id) {
    return service.findById(id);
  }

  @PatchMapping("/publish/{id}")
  public Form publishForm(@PathVariable Long id) {
    return service.publishForm(id);
  }

  @PatchMapping("/pause/{id}")
  public Form pauseForm(@PathVariable Long id) {
    return service.pauseForm(id);
  }

  @PatchMapping("/close/{id}")
  public Form closeForm(@PathVariable Long id) {
    return service.closeForm(id);
  }

}
