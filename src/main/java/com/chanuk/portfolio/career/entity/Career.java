package com.chanuk.portfolio.career.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "careers")
public class Career {
 @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
 @Column(nullable = false, length = 120) private String company;
 @Column(nullable = false, length = 200) private String role;
 @Column(nullable = false, length = 80) private String period;
 @Column(nullable = false, length = 5000) private String description;
 @Column(name = "is_current", nullable = false) private boolean current;
 @Column(nullable = false) private int displayOrder;
 protected Career() {}
 public Career(String company, String role, String period, String description, boolean current, int displayOrder) { update(company, role, period, description, current, displayOrder); }
 public void update(String company, String role, String period, String description, boolean current, int displayOrder) {
  this.company = company;
  this.role = role;
  this.period = period;
  this.description = description;
  this.current = current;
  this.displayOrder = displayOrder;
 }
 public Long getId() { return id; }
 public String getCompany() { return company; }
 public String getRole() { return role; }
 public String getPeriod() { return period; }
 public String getDescription() { return description; }
 public boolean getCurrent() { return current; }
 public int getDisplayOrder() { return displayOrder; }
}
