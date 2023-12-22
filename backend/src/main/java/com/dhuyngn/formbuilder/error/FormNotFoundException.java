package com.dhuyngn.formbuilder.error;

import jakarta.persistence.*;

public class FormNotFoundException extends EntityNotFoundException {

  public FormNotFoundException() {
    super("Form not found");
  }
  
}
