package com.jakartadata.repository;

import com.jakartadata.model.Customer;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Delete;
import jakarta.data.repository.Find;
import jakarta.data.repository.Insert;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;
import jakarta.data.repository.Update;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

  @Insert
  void addCustomer(Customer customer);

  @Delete
  void removeCustomer(Customer customer);

  @Update
  void updateCustomer(Customer customer);

  @Find
  Optional<Customer> findById(Long id);

  @Query("FROM Customer c where c.firstName = ?1")
  List<Customer> byFirstName(String firstName);

  @Query("FROM Customer c ORDER BY c.id")
  List<Customer> all();

  @Query("where lastName like :lastName")
  List<Customer> findByLastNameLike(String lastName);

}
