package br.edu.ifsul.controle;

import br.edu.ifsul.dao.OrientadorDAO;

import br.edu.ifsul.dao.AlunoDAO;
import br.edu.ifsul.modelo.Publicacao;
import br.edu.ifsul.modelo.Orientador;

import br.edu.ifsul.modelo.Aluno;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;


@Named(value = "controleOrientador")
@SessionScoped
public class ControleOrientador implements Serializable {

    @EJB
    private OrientadorDAO<Orientador> dao;
    private Orientador objeto;
    private Boolean editando;
    
    @EJB
    private AlunoDAO<Aluno> daoAluno;
    private Boolean editandoPublicacao;
    private Publicacao publicacao;
    private Boolean novoPublicacao;

    public ControleOrientador() {
        editando = false;
        editandoPublicacao = false;
    }

    public String listar() {
        editando = false;
        return "/privado/orientador/listar?faces-redirect=true";
    }

    public void novo() {
        editando = true;
        editandoPublicacao = false;
        objeto = new Orientador();
    }

    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
            editandoPublicacao = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }

    }

    public void excluir(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro a remover objeto: " + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Sucesso ao persistir objeto");
            editando = false;
            editandoPublicacao = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir: " + Util.getMensagemErro(e));
        }
    }

    public void novoPublicacao() {
        publicacao = new Publicacao();
        editandoPublicacao = true;
        novoPublicacao = true;
    }

    public void salvarPublicacao() {
        if (publicacao.getId() == null) {
            if (novoPublicacao) {
                objeto.adicionarPublicacao(publicacao);
            }
        }
        editandoPublicacao = false;
        Util.mensagemInformacao("Publicacao persistido com sucesso!");
    }

    public void alterarPublicacao(int index) {
        publicacao = objeto.getPublicacoes().get(index);
        editandoPublicacao = true;
        novoPublicacao = false;
    }

    public void excluirPublicacao(int index) {
        objeto.removerPublicacao(index);
        Util.mensagemInformacao("Publicacao removida com sucesso!");
    }

    public Orientador getObjeto() {
        return objeto;
    }

    public void setObjeto(Orientador objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public OrientadorDAO<Orientador> getDao() {
        return dao;
    }

    public void setDao(OrientadorDAO<Orientador> dao) {
        this.dao = dao;
    }

  

    public AlunoDAO<Aluno> getDaoAluno() {
        return daoAluno;
    }

    public void setDaoAluno(AlunoDAO<Aluno> daoAluno) {
        this.daoAluno = daoAluno;
    }

    public Boolean getEditandoPublicacao() {
        return editandoPublicacao;
    }

    public void setEditandoPublicacao(Boolean editandoPublicacao) {
        this.editandoPublicacao = editandoPublicacao;
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    public Boolean getNovoPublicacao() {
        return novoPublicacao;
    }

    public void setNovoPublicacao(Boolean novoPublicacao) {
        this.novoPublicacao = novoPublicacao;
    }
}
