package com.tintumpic.expenses_web.service;

import com.tintumpic.expenses_web.data.ExpenseRepository;
import com.tintumpic.expenses_web.model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Page<Expense> findAll(Pageable pageable) {
        return expenseRepository.findAll(pageable);
    }

    public void deleteAll() {
        expenseRepository.deleteAll();
    }
}

