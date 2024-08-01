package com.tintumpic.expenses_web.data;

import com.tintumpic.expenses_web.model.Expense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long>, PagingAndSortingRepository<Expense, Long> {
}
