package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;
/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = {"/Controller", "/main", "/insert", "/select", "/update"}) //aqui adiciono os parametros de ação clicada
public class Controller extends HttpServlet { 
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO(); //objeto DAO para conexão
	JavaBeans contato = new JavaBeans(); //objeto contato acessa os métodos publicos da classe JavaBeans no arquivo JavaBeans.java
       
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
		}else if(action.equals("/select")) { 
			listarContato(request, response); //aciona o método listarContanto abaixo
		}else if(action.equals("/update")) { 
			editarContato(request, response); //aciona o método editarContato abaixo
		} else {
			response.sendRedirect("index.html");
		}
		
	}
		
		/* Método Listar contatos ------------------ */
		protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// response.sendRedirect("agenda.jsp"); -- redireciona para a pagina agenda.jsp
			// criando um objeto que irá receber dados do JavaBeans
			ArrayList<JavaBeans> lista = dao.listarContatos();
			//teste de recebimento de lista
			//for (int i = 0; i < lista.size(); i++) {
			//	System.out.println(lista.get(i).getIdcon());
			//	System.out.println(lista.get(i).getNome());
			//	System.out.println(lista.get(i).getFone());
			//	System.out.println(lista.get(i).getEmail());
			//}
			
			// Encaminhar a lista para o documento agenda.jsp
			request.setAttribute("contatos", lista);
			RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
			rd.forward(request, response);
		}
		// fim do  Método Listar contatos ----------
		
		/* Método Novo contato --------------------- */
		protected void novoContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//teste para verificar os dados do formulário
			//System.out.println(request.getParameter("nome"));
			//System.out.println(request.getParameter("fone"));
			//System.out.println(request.getParameter("email"));
			
			//setar as variáveis no JavaBeans
			contato.setNome(request.getParameter("nome"));
			contato.setFone(request.getParameter("fone"));
			contato.setEmail(request.getParameter("email"));
			// invocar o método inserirContato passando o objeto contato
			dao.inserirContato(contato);
			// redireciona  para o documento agenda.jsp
			response.sendRedirect("main");
		}
		/* fim do Método Novo contato -------------- */
		
		/* Método listar contato ------------------- */
		protected void listarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//recebimento do id contato que será editado
			String idcon = request.getParameter("idcon");
			// Setar a variável JavaBeans
			contato.setIdcon(idcon);
			// Executar o método selecionarContato(DAO)
			dao.selecionaContato(contato);
			//teste de recebimento
			//System.out.println(contato.getIdcon());
			//System.out.println(contato.getNome());
			//System.out.println(contato.getFone());
			//System.out.println(contato.getEmail());
			//Setar os atributos do formulário com o conteúdo JavaBeans
			request.setAttribute("idcon", contato.getIdcon());
			request.setAttribute("nome", contato.getNome());
			request.setAttribute("fone", contato.getFone());
			request.setAttribute("email", contato.getEmail());
			// Encaminhar ao documento editar.jsp
			RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
			rd.forward(request, response);
		}
		/* fim do Método listar contato ------------ */
		
		/* Métod editar contato -------------------- */
		protected void editarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//Teste de recebimento de informações Print tela
			//System.out.println(request.getParameter("idcon"));
			//System.out.println(request.getParameter("nome"));
			//System.out.println(request.getParameter("fone"));
			//System.out.println(request.getParameter("email"));
			//setar as variáveis no JavaBeans
			contato.setIdcon(request.getParameter("idcon"));
			contato.setNome(request.getParameter("nome"));
			contato.setFone(request.getParameter("fone"));
			contato.setEmail(request.getParameter("email"));
			// executa o método alterar Contato
			dao.alterarContato(contato);
			// redireciona para o documento agenda.jsp atualizando
			response.sendRedirect("main");			
		}
		/* fim do Método editar contato ------------ */
		
			//teste de conexão
		//dao.testeConexao();
	}

