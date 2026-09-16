package com.chanuk.portfolio.project.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "projects")
public class Project {
 @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
 @Column(nullable = false, length = 120) private String title;
 @Column(nullable = false, length = 20) private String category;
 @Column(nullable = false, length = 5000) private String description;
 @Column(nullable = false, length = 200) private String role;
 @Column(length = 80) private String period;
 @Column(nullable = false) private boolean pending;
 @Column(nullable = false) private int displayOrder;
 protected Project() {}
 public Project(String title, String category, String description, String role, String period, boolean pending, int displayOrder) { update(title, category, description, role, period, pending, displayOrder); }
 public void update(String title, String category, String description, String role, String period, boolean pending, int displayOrder) {
  this.title = title;
  this.category = category;
  this.description = description;
  this.role = role;
  this.period = period;
  this.pending = pending;
  this.displayOrder = displayOrder;
 }
 public Long getId() { return id; }
 public String getTitle() { return title; }
 public String getCategory() { return category; }
 public String getDescription() { return description; }
 public String getRole() { return role; }
 public String getPeriod() { return period; }
 public boolean getPending() { return pending; }
 public int getDisplayOrder() { return displayOrder; }
}
