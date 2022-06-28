package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = {"/Controller", "/main", "/insert"}) //aqui adiciono os parametros de ação clicada
public class Controller extends HttpServlet { 
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
       
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		System.out.println(action);
		
		if(action.equals("/main")) {
			contatos(request, response); //aciona o método contatos abaixo
		} else if(action.equals("/insert")) { 
			novoContato(request, response); //aciona o método novoContato abaixo
		}else {
			response.sendRedirect("index.html");
		}
		
	}
		
		// Listar contatos
		protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.sendRedirect("agenda.jsp");
		}
		
		// Novo contato
		protected void novoContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//teste para verificar os dados do formulário
			System.out.println(request.getParameter("nome"));
			System.out.println(request.getParameter("fone"));
			System.out.println(request.getParameter("email"));
		}
			
			//teste de conexão
		//dao.testeConexao();
	}

