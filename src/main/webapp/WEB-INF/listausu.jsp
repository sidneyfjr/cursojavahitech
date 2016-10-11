<%@page import="br.com.fabricadeprogramador.persistencia.entidade.Usuario" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de usuários</title>

<script type="text/javascript">

	function confirmaExclusao(id){
		
		if (window.confirm("Tem certeza de que deseja excluir?")){
			
			location.href="usucontroller.do?acao=exc&id="+id;
			
		}
		
	}
	
	function novo(){
		
		location.href='usucontroller.do?acao=cad';
	}

</script>

</head>
<body>

<%@include file="menu.jsp" %>

<%
	List<Usuario> lista= (List<Usuario>)request.getAttribute("lista");
%>
   <table border=1>
   <tr><th>ID</th> <th>Nome</th><th>Ação</th>
   
   <% for (Usuario u:lista ){ %>
	<tr>
		<td><% out.print(u.getId() ); %></td>
		<td><%=u.getNome() %></td>
		<td><a href="javascript:confirmaExclusao(<%=u.getId()%>)">Excluir</a> | <a href="usucontroller.do?acao=alt&id=<%=u.getId() %>" >Alterar</a> </td>
	  
	</tr>
   <% } %>   
   
   </table> 
   
   <input type="button" value="Novo" onclick="javascript:novo()" >
</body>
</html>