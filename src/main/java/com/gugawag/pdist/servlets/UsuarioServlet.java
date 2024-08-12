package com.gugawag.pdist.servlets;

import com.gugawag.pdist.ejbs.MensagemService;
import com.gugawag.pdist.ejbs.UsuarioService;
import com.gugawag.pdist.model.Mensagem;
import com.gugawag.pdist.model.Usuario;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/usuario.do", "/mensagem.do"})
public class UsuarioServlet extends javax.servlet.http.HttpServlet {

    @EJB(lookup="java:module/usuarioService")
    private UsuarioService usuarioService;

    @EJB(lookup="java:module/mensagemService")
    private MensagemService mensagemService;

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        String operacao = request.getParameter("oper");
        PrintWriter resposta = response.getWriter();

        if (request.getRequestURI().contains("usuario")) {
            switch (operacao) {
                case "1": {
                    long id = Long.parseLong(request.getParameter("id"));
                    String nome = request.getParameter("nome");
                    usuarioService.inserir(id, nome);
                    break;
                }
                case "2": {
                    for(Usuario usuario: usuarioService.listar()){
                        resposta.println(usuario.getNome());
                    }
                    break;
                }
            }
        } else if (request.getRequestURI().contains("mensagem")) {
            switch (operacao) {
                case "1": {
                    long id = Long.parseLong(request.getParameter("id"));
                    String conteudo = request.getParameter("conteudo");
                    mensagemService.inserir(id, conteudo);
                    break;
                }
                case "2": {
                    for(Mensagem mensagem: mensagemService.listar()){
                        resposta.println(mensagem.getMensagem());
                    }
                    break;
                }
            }
        }
    }
}
