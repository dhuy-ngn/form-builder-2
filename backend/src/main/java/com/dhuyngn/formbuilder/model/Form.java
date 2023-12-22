package com.dhuyngn.formbuilder.model;

import com.dhuyngn.formbuilder.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.*;
import java.util.*;

@Entity
@Table(name = "form", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"userId", "name"})
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"formSubmissions"})
public class Form {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id")
  @NotBlank(message = "User ID cannot be blank")
  private String userId;

  @Column(name = "name")
  @NotBlank(message = "Name cannot be blank")
  private String name;

  @Column(name = "description")
  @Builder.Default
  @NonNull
  private String description = "";

  @Column(name = "created_at")
  @Builder.Default
  @NonNull
  private LocalDateTime createdAt = LocalDateTime.now();

  @Column(name = "status")
  @Builder.Default
  @NonNull
  @Enumerated(EnumType.STRING)
  private FormStatus status = FormStatus.CREATED;

  @Column(name = "visits")
  @Builder.Default
  @NonNull
  private Long visits = 0L;

  @Column(name = "submissions")
  @Builder.Default
  @NonNull
  private Long submissions = 0L;

  @Column(name = "share_url")
  private String shareUrl = UUID.randomUUID().toString().toUpperCase();

  // TODO: Create a model for FormElement instead of stringify them
  @Column(name = "content")
  @Builder.Default
  @NonNull
  private String content = "[]";

  @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
  private List<FormSubmission> formSubmissions;

}
