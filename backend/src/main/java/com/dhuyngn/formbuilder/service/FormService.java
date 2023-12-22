package com.dhuyngn.formbuilder.service;

import com.dhuyngn.formbuilder.enums.*;
import com.dhuyngn.formbuilder.error.*;
import com.dhuyngn.formbuilder.model.*;
import com.dhuyngn.formbuilder.repository.*;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@Service
public class FormService {

  @Autowired
  private FormRepository repository;

  public List<Form> getAllForms() {
    return repository.findAll();
  }

  public List<Form> getFormByStatus(FormStatus status) {
    return repository.findByStatus(status);
  }

  public Form createForm(@Valid @RequestBody Form form) {
    try {
      return repository.save(form);
    } catch (DataIntegrityViolationException e) {
      throw new FormAlreadyExistsException();
    }
  }

  public Form findById(@PathVariable @Min(1) Long id) {
    return repository.findById(id)
        .orElseThrow(FormNotFoundException::new);
  }

  public Form publishForm(@PathVariable @Min(1) Long id) {
    return repository.findById(id)
        .map(form -> {
          form.setStatus(FormStatus.PUBLISHED);

          return repository.save(form);
        })
        .orElseThrow(FormNotFoundException::new);
  }

  public Form pauseForm(@PathVariable @Min(1) Long id) {
    return repository.findById(id)
        .map(form -> {
          form.setStatus(FormStatus.PAUSED);

          return repository.save(form);
        })
        .orElseThrow(FormNotFoundException::new);
  }

  public Form closeForm(@PathVariable @Min(1) Long id) {
    return repository.findById(id)
        .map(form -> {
          form.setStatus(FormStatus.CLOSED);

          return repository.save(form);
        })
        .orElseThrow(FormNotFoundException::new);
  }

}
