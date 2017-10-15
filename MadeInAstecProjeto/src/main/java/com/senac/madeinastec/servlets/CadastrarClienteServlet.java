/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.madeinastec.servlets;

import com.senac.madeinastec.dao.ClienteDAO;
import com.senac.madeinastec.model.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Debaza
 */
@WebServlet(name = "CadastrarClienteServlet", urlPatterns = {"/CadastrarCliente"})
public class CadastrarClienteServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String destino;
        
        HttpSession sessao = request.getSession();
        if (sessao.getAttribute("cliente") != null) {
            request.setAttribute("cliente", sessao.getAttribute("cliente"));
            // Remove o atributo da sessao para usuario nao ficar preso na tela de resultados
            sessao.removeAttribute("cliente");
            
            destino = "clientes";
        } else {
            destino = "cadastroCliente.jsp";
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(destino);
        dispatcher.forward(request, response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("name");
        String sobrenome = request.getParameter("sobrenome");
        String sexo = request.getParameter("sexo");
        String dataNasc = request.getParameter("dataNasc");
        String cpf = request.getParameter("cpf");
        String rg = request.getParameter("rg");
        String tel1 = request.getParameter("tel1");
        String tel2 = request.getParameter("tel2");
        String email = request.getParameter("email");
        String numCasa = request.getParameter("numCasa");
        String complemento = request.getParameter("complemento");
        String cidade = request.getParameter("cidade");
        
        Cliente novoCliente = new Cliente();
        novoCliente.setNome(nome);
        novoCliente.setSobrenome(sobrenome);
        novoCliente.setCpf(cpf);
        novoCliente.setSexo(sexo);
        novoCliente.setIdade(dataNasc);
        novoCliente.setRg(rg);
        novoCliente.setTelefone(tel1);
        novoCliente.setTelefone2(tel2);
        novoCliente.setEmail(email);
        novoCliente.setNumero(numCasa);
        novoCliente.setComplemento(complemento);
        novoCliente.setCidade(cidade);
        
        ClienteDAO clientedao = new ClienteDAO();
        clientedao.inserirCliente(novoCliente);
        
        HttpSession sessao = request.getSession();
        sessao.setAttribute("cliente", novoCliente);
        
        response.sendRedirect(request.getContextPath() + "/CadastrarCliente");
        
    }
    
}
