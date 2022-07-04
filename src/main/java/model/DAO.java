package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	/** Módulo de conexão **/
	// Parametros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "";
	
	//Método de conexão
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		}catch (Exception e){
			System.out.println(e);
			return null;
		}
		
	}
		
		//testando a conexão
	//	public void testeConexao() {
	//		try {
	//			Connection con = conectar();
	//			System.out.println(con);
	//			con.close();
	//		} catch (Exception e) {
	//			System.out.println(e);
	//		}
	//	}
	
	/** CRUD CREATE **/
	public void inserirContato(JavaBeans contato) {
		String create = "INSERT INTO contatos (nome,fone,email) VALUES(?,?,?)";
		try {
			//Abrir conexão
			Connection con = conectar();
			//Prepara a query para a execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			//Substituir os parâmetros (?) pelo conteúdo das variáveis JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			//Executar a query
			pst.executeUpdate();
			//Encerrar a conexão com o banco
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/** CRUD READ **/
	public ArrayList<JavaBeans> listarContatos(){
		// Criando objetos para acessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = "SELECT *FROM contatos ORDER BY nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// laço abaixo será executado enquanto houver contatos
			while(rs.next()) {
				// variáveis de apoio que recebem os dados do banco
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				// populando o ArrayList
				contatos.add(new JavaBeans(idcon,nome,fone,email));					
			}
			con.close();
			return contatos;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/** CRUD SELECT e UPDATE **/
	//selecionar contato
	public void selecionaContato(JavaBeans contato) {
		String read2 = "SELECT *FROM contatos WHERE idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, contato.getIdcon());
			ResultSet rs = pst.executeQuery();
			//setar as variáveis JavaBeans
			while(rs.next()) {
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	//edita o contato
	public void alterarContato(JavaBeans contato) {
		String Update = "UPDATE contatos SET nome=?,fone=?,email=? WHERE idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(Update);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdcon());
			pst.executeUpdate();
			con.close();
					} catch (Exception e) {
			System.out.println(e);
		}
	}
		
}
