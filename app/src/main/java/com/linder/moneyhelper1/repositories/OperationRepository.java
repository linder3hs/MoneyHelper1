package com.linder.moneyhelper1.repositories;

import com.linder.moneyhelper1.model.Operation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linderhassinger on 9/14/17.
 */

public class OperationRepository {

    public static OperationRepository _INSTANCE = null;

    private OperationRepository(){}

    public static OperationRepository getInstance(){
        if(_INSTANCE == null)
            _INSTANCE = new OperationRepository();
        return _INSTANCE;
    }

    private List<Operation> operations = new ArrayList<>();

    public List<Operation> getOperations() {return this.operations;}

    public void addOperation(Operation operation){
        this.operations.add(operation);
    }

}
