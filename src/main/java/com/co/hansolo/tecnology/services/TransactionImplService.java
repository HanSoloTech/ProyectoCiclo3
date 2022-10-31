package com.co.hansolo.tecnology.services;

import com.co.hansolo.tecnology.models.Enterprise;
import com.co.hansolo.tecnology.models.Transaction;
import com.co.hansolo.tecnology.repositories.ITransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class TransactionImplService implements ITransactionService {

    LocalDate today = LocalDate.now();
    ITransactionRepository repository;

    public TransactionImplService(ITransactionRepository repository) { this.repository = repository; }

    @Override
    public ArrayList<Transaction> findAllByEnterprise(Long id) {
        return this.repository.findAllByEnterprise(id);
    }

    @Override
    public List<Transaction> findAll() { return this.repository.findAll(); }

    @Override
    public Long totalAmount(Long id) { return this.repository.totalAmount(id); }

    @Override
    public Long total() { return this.repository.total(); }

    @Override
    public Transaction findById(Long id) { return this.repository.findById(id).orElse(null); }

    @Override
    public Long findByEmail(String email) { return this.repository.findByEmail(email); }

    @Override
    public Transaction create(Transaction transaction) {
        transaction.setCreateAt(today);
        return this.repository.save(transaction);
    }

    @Override
    public Transaction update(Long id, Transaction transaction) {
        Transaction transactionDB = this.findById(id);
        transactionDB.setConcept(transaction.getConcept());
        transactionDB.setAmount(transaction.getAmount());
        transactionDB.setUpdateAt(today);

        return this.repository.save(transactionDB);
    }

    @Override
    public Transaction update(Transaction transaction) {
        transaction.setUpdateAt(today);
        return repository.save(transaction);
    }

    @Override
    public void deleteById(Long id) { repository.deleteById(id);}
}
