package br.com.lucas.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.lucas.cursomc.domain.Cliente;
import br.com.lucas.cursomc.domain.enums.TipoCliente;
import br.com.lucas.cursomc.dto.ClienteNewDTO;
import br.com.lucas.cursomc.repositories.ClienteRepository;
import br.com.lucas.cursomc.resources.exceptions.FieldMessage;
import br.com.lucas.cursomc.services.validation.utils.BR;


public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}
	
 	@Override
 	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
 		
 		List<FieldMessage> list = new ArrayList<>();
 		
 		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
 			list.add(new FieldMessage("cpfOuCnpj", "O CPF é inválido!"));
 		}
 		
 		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
 			list.add(new FieldMessage("cpfOuCnpj", "O CNPJ é inválido!"));
 		}
 		
 		Cliente aux = repo.findByEmail(objDto.getEmail());
 		if (aux != null) {
 			list.add(new FieldMessage("Email", "O e-mail já existe em nosso cadastro!"));
 		}

 		for (FieldMessage e : list) {
 			context.disableDefaultConstraintViolation();
 			context.buildConstraintViolationWithTemplate(e.getMessage())
 			.addPropertyNode(e.getFieldName()).addConstraintViolation();
 		}
 		return list.isEmpty();
 	}
}