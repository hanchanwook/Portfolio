package com.chanuk.portfolio.skill.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "skills")
public class Skill {
 @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
 @Column(nullable = false, length = 80) private String name;
 @Column(nullable = false, length = 20) private String category;
 @Column(nullable = false) private int displayOrder;
 protected Skill() {}
 public Skill(String name, String category, int displayOrder) { update(name, category, displayOrder); }
 public void update(String name, String category, int displayOrder) {
  this.name = name;
  this.category = category;
  this.displayOrder = displayOrder;
 }
 public Long getId() { return id; }
 public String getName() { return name; }
 public String getCategory() { return category; }
 public int getDisplayOrder() { return displayOrder; }
}
