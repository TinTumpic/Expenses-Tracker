package com.tintumpic.expenses_web.web.controller;

import com.tintumpic.expenses_web.data.ExpenseRepository;
import com.tintumpic.expenses_web.model.Expense;
import com.tintumpic.expenses_web.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/expenses")
public class ExpensesController {

    private final ExpenseRepository expenseRepository;
    private final ExpenseService expenseService;

    public ExpensesController(ExpenseService expenseService, ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
        this.expenseService = expenseService;
    }

    @ModelAttribute("expenses")
    public Page<Expense> getExpenses(@PageableDefault(size = 5) Pageable page) {
        return expenseService.findAll(page);
    }

    @GetMapping
    public String showExpensesPageAndSum(Model model) {
            Iterable<Expense> expenses = expenseRepository.findAll();
            BigDecimal sum = BigDecimal.ZERO;
            for(Expense expense : expenses) {
                sum = sum.add(expense.getPrice());
            }
            model.addAttribute("sum", sum);
        return "expenses";
    }

    @ModelAttribute
    public Expense expense() {
        return new Expense();
    }

    @PostMapping
    public String saveExpense(@Valid Expense expense, Errors errors) {
        if(!errors.hasErrors()) {
            expenseRepository.save(expense);
            return "redirect:expenses";
        }
        return "expenses";
    }

    @PostMapping(params = "action=delete")
    public String deleteExpense(@RequestParam List<Long> selections) {
        System.out.println(selections);
        if (!selections.isEmpty()) {
            expenseRepository.deleteAllById(selections);
        }
        return "redirect:expenses";
    }

    @PostMapping(params = "action=edit")
    public String editExpense(@RequestParam List<Long> selections, Model model) {
        if (!selections.isEmpty()) {
            Optional<Expense> expense = expenseRepository.findById(selections.get(0));
            model.addAttribute("expense", expense);
        }
        return "expenses";
    }

    @PostMapping(params = "action=reset")
    public String deleteAll() {
        expenseService.deleteAll();
        return "expenses";
    }
}
