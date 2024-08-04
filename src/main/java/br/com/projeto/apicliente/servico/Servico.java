package br.com.projeto.apicliente.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.apicliente.modelo.Mensagem;
import br.com.projeto.apicliente.modelo.Pessoa;
import br.com.projeto.apicliente.repositorio.Repositorio;


@Service
public class Servico {

    @Autowired
    private Mensagem mensagem;

    @Autowired
    private Repositorio acao;

    // Método para cadastrar pessoas
    public ResponseEntity<?> cadastrar(Pessoa objPessoa){
        if(objPessoa.getNome().equals("")){
            mensagem.setMensagem("O nome precisa ser preenchido!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(objPessoa.getIdade() < 0){
            mensagem.setMensagem("Informe uma idade válida!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(acao.save(objPessoa), HttpStatus.CREATED);
        }
    }

    // Método para selecionar pessoas
    public ResponseEntity<?> selecionar(){
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }

    // Método para selecionar através do código
    public ResponseEntity<?> selecionarPeloCodigo(int codigo){
        if(acao.countByCodigo(codigo) == 0){
            mensagem.setMensagem("Não encontrada nenhuma pessoa!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(acao.findByCodigo(codigo), HttpStatus.OK);
        }
    }

    // Método para editar dados
    public ResponseEntity<?> editar(Pessoa objPessoa){
        if(acao.countByCodigo(objPessoa.getCodigo()) == 0){
            mensagem.setMensagem("O código informado não existe!");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }else if(objPessoa.getNome().equals("")){
            mensagem.setMensagem("É necessário informar o nome!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(objPessoa.getIdade() < 0){
            mensagem.setMensagem("Informe uma idade válida!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(acao.save(objPessoa), HttpStatus.OK);
        }
    }

}
