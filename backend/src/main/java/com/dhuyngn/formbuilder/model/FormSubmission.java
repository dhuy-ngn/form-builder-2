package com.dhuyngn.formbuilder.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Entity
@Table(name = "form_submission")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormSubmission {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "submitted_at")
  @Builder.Default
  @NonNull
  private LocalDateTime submittedAt = LocalDateTime.now();

  @ManyToOne
  @NonNull
  @JoinColumn(name = "form_id")
  private Form form;

  @Column(name = "content")
  private String content;
}
