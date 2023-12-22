package com.dhuyngn.formbuilder.repository;

import com.dhuyngn.formbuilder.enums.*;
import com.dhuyngn.formbuilder.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
  List<Form> findByStatus(FormStatus status);
}
