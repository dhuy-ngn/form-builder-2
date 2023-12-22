package com.dhuyngn.formbuilder.error;

import jakarta.persistence.*;

public class FormAlreadyExistsException extends EntityExistsException {

  public FormAlreadyExistsException() {
    super("Form already exists");
  }
  
}
