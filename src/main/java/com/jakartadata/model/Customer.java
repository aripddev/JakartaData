package com.jakartadata.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Customer implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column
  private String firstName;

  @Column
  private String lastName;

  public Customer() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 47 * hash + Objects.hashCode(this.firstName);
    hash = 47 * hash + Objects.hashCode(this.lastName);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Customer other = (Customer) obj;
    if (!Objects.equals(this.firstName, other.firstName)) {
      return false;
    }
    return Objects.equals(this.lastName, other.lastName);
  }

  @Override
  public String toString() {
    return "Customer{" + "firstName=" + firstName + ", lastName=" + lastName + '}';
  }

}
