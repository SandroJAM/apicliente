package br.com.projeto.apicliente.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.apicliente.modelo.Pessoa;
import br.com.projeto.apicliente.repositorio.Repositorio;
import br.com.projeto.apicliente.servico.Servico;

@RestController
public class Controle {

    @Autowired
    private Repositorio acao;

    @Autowired
    private Servico servico;

    /*@PostMapping("/api")
    public Pessoa cadastrar(@RequestBody Pessoa objPessoa) {
        return acao.save(objPessoa);
    }*/

    @PostMapping("/api")
    public ResponseEntity<?> cadastrar(@RequestBody Pessoa objPessoa){
        return servico.cadastrar(objPessoa);
    }

    /*@GetMapping("/api")
    public List<Pessoa> selecionar() {
        return acao.findAll();
    }*/

    @GetMapping("/api")
    public ResponseEntity<?> selecionar(){
        return servico.selecionar();
    }

    /*@GetMapping("/api/{codigo}")
    public Pessoa selecionarPeloCodigo(@PathVariable int codigo) {
        return acao.findByCodigo(codigo);
    }*/

    @GetMapping("/api/{codigo}")
    public ResponseEntity<?> selecionarPeloCodigo(@PathVariable int codigo){
        return servico.selecionarPeloCodigo(codigo);
    }

    /*@PutMapping("/api")
    public Pessoa editar(@RequestBody Pessoa objPessoa){
        return acao.save(objPessoa);
    }*/

    @PutMapping("/api")
    public ResponseEntity<?> editar(@RequestBody Pessoa objPessoa){
        return servico.editar(objPessoa);
    }

    @DeleteMapping("/api/{codigo}")
    public void remover(@PathVariable int codigo) {
        // Pessoa objPessoa = selecionarPeloCodigo(codigo);
        // acao.delete(objPessoa);
    }

    /* --------------------------------------------------------------------------------- */

    @GetMapping("/api/contador")
    public long contador() {
        return acao.count();
    }

    @GetMapping("/api/ordenarNomes")
    public List<Pessoa> ordenarNomes(){
        return acao.findByOrderByNome();
    }

    @GetMapping("/api/ordenarNomes2")
    public List<Pessoa> ordenarNomes2(){
        return acao.findByNomeOrderByIdadeDesc("Mayra");
    }

    @GetMapping("/api/nomeContem")
    public List<Pessoa> nomeContem(){
        return acao.findByNomeContaining("dro");
    }

    @GetMapping("/api/iniciaCom")
    public List<Pessoa> iniciaCom(){
        return acao.findByNomeStartsWith("S");
    }
    
    @GetMapping("/api/terminaCom")
    public List<Pessoa> terminaCom(){
        return acao.findByNomeEndsWith("a");
    }

    @GetMapping("/api/somaIdades")
    public int somaIdades(){
        return acao.somaIdades();
    }

    @GetMapping("/api/idadeMaiorIgual")
    public List<Pessoa> idadeMaiorIgual(){
        return acao.idadeMaiorIgual(50);
    }

    /*@GetMapping("/status")
    public String status(){
        return "Configurando STATUS";
    }*/

    @GetMapping("/status")
    public ResponseEntity<?> status(){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /* --------------------------------------------------------------------------------- */

    @GetMapping
    public String mensagem() {
        return "Hello World!";
    }

    @GetMapping("/boasVindas/{nome}")
    public String boasVindas(@PathVariable String nome) {
        return "Seja bem vindo(a) " + nome;
    }

    @PostMapping("/pessoa") // O @GetMapping não tem suporte ao @RequestBody
    public Pessoa pessoa(@RequestBody Pessoa objPessoa) {
        return objPessoa;
    }

}
