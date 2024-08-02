package br.com.projeto.apicliente.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.apicliente.modelo.Pessoa;
import br.com.projeto.apicliente.repositorio.Repositorio;

@RestController
public class Controle {
    
    @Autowired
    private Repositorio acao;

    @PostMapping("/api")
    public Pessoa cadastrar(@RequestBody Pessoa objPessoa){
        return acao.save(objPessoa);
    }

    @GetMapping
    public String mensagem(){
        return "Hello World!";
    }

    @GetMapping("/boasVindas/{nome}")
    public String boasVindas(@PathVariable String nome){
        return "Seja bem vindo(a) " + nome;
    }

    @PostMapping("/pessoa") // O @GetMapping não tem suporte ao @RequestBody
    public Pessoa pessoa(@RequestBody Pessoa objPessoa){
        return objPessoa;
    }

}
