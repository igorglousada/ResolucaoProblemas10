package com.example;

import java.util.*;
import lombok.Data;
import com.microsoft.azure.functions.*;

@Data
class Funcionario {
	private Long id;
	private String nome;
	private int idade;
	private double salario;

	public Funcionario(Long id, String nome, int idade, double salario) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.salario = salario;
	}
}

class DAO {
    public List<Funcionario> funcs() {
        List<Funcionario> funcs = new ArrayList<>();
		
        Funcionario f1 = new Funcionario(
                new Long(1), "Func1", 19, 900
        );
		
        Funcionario f2 = new Funcionario(
                new Long(2), "Func2", 22, 1500
        );
		
		Funcionario f3 = new Funcionario(
                new Long(3), "Func3", 25, 1200
        );
		
        funcs.add(f1);
        funcs.add(f2);
        funcs.add(f3);
		
        return funcs;
    }
}

public class Function {

    private DAO dao = new DAO();	

    @FunctionName("createfuncionario")
    public Funcionario create(
        @HttpTrigger(
            name = "createfuncionariorest",
            methods = {HttpMethod.POST},
            route = "funcionario"
        ) Funcionario funcionario) {
			
	// Do something important
	// Some business rules
		
        funcionario.setId(new Long(UUID.randomUUID().toString()));
        dao.funcs().add(funcionario);
		
        return funcionario;
    }

    @FunctionName("readfuncionario")
    public List<> read(
        @HttpTrigger(
            name = "readfuncionariorest",
            methods = {HttpMethod.GET},
            route = "funcionario"
        ) Funcionario funcionario) {
        // Do something important
        // Some business rules
        return dao.funcs();
    }

    @FunctionName("updatefuncionario")
    public Funcionario update(
        @HttpTrigger(
            name = "updatefuncionariorest",
            methods = {HttpMethod.PUT},
            route = "funcionario"
        ) Funcionario funcionario) {
        // Do some update ...
		
        funcionario.setName(funcionario.getName() + " - updated!");
        Funcionario func = dao.funcs().stream().filter(e -> e.getId().equals(funcionario.getId())).findAny().get();
		
        dao.funcs().remove(func);
        dao.funcs().add(funcionario);
		
        return funcionario;
    }

    @FunctionName("deletefuncionario")
    public Funcionario delete(
            @HttpTrigger(
                    name = "deletefuncionariorest",
                    methods = {HttpMethod.DELETE},
                    route = "funcionario"
            ) Funcionario funcionario) {
        // Do some update ...
		
        Funcionario func = dao.funcs().stream().filter(e -> e.getId().equals(funcionario.getId())).findAny().get();
        dao.funcs().remove(func);
        return funcionario;
    }
}




