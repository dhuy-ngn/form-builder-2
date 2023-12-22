package com.dhuyngn.formbuilder.repository;

import com.dhuyngn.formbuilder.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface FormSubmissionRepository extends JpaRepository<Form, Long> {
}
