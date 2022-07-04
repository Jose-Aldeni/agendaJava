<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="model.JavaBeans"%>
    <%@ page import="java.util.ArrayList"%>
    <%
    	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>)request.getAttribute("contatos");
    	//for (int i = 0; i < lista.size(); i++){
    		//out.println(lista.get(i).getIdcon());
    		//out.println(lista.get(i).getNome());
    		//out.println(lista.get(i).getFone());
    		//out.println(lista.get(i).getEmail());
    	//}
    %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<%@ include file="padrao/title.html" %> <!-- inclui dados padrãos de exibição -->

<link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>
	<h1>Agenda de contatos</h1>
	<a href="novo.html" class="Botao1">Novo Contato</a>
	
	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>Email</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<% for (int i = 0; i < lista.size(); i++){ %>
				<tr>
					<td><%=lista.get(i).getIdcon()%></td>
					<td><%=lista.get(i).getNome()%></td>
					<td><%=lista.get(i).getFone()%></td>
					<td><%=lista.get(i).getEmail()%></td>
					<td><a href="select?idcon=<%=lista.get(i).getIdcon() %>" class="Botao1">Editar</a></td>
				</tr>
			<% } %>
		</tbody>
	</table>
</body>
</html>