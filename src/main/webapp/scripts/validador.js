/**
 * Validação de formulário
 * @author Jose aldeni 
 */
 
 function validar(){
	let nome = frmCOntato.nome.value
	let fone = frmCOntato.fone.value
	if(nome === ""){
		alert('Preencha o campo nome')
		frmContato.nome.focus()
		return false
	}else if(fone === ""){
		alert('Preencha o campo fone')
		frmContato.fone.focus()
		return false
	}else{
		document.forms["frmContatos"].submit()
	}
}