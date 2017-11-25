package dvr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.dvr.model.Imovel;
import br.com.dvr.repository.ImovelRepository;
import br.com.dvr.service.ImovelService;
import br.com.dvr.validators.Result;

@RunWith(MockitoJUnitRunner.class)
public class ImovelServiceTest {
	
	@InjectMocks
	private ImovelService imovelService;
	
	@Mock
	private ImovelRepository imovelRepository;
	
	@Before
	public void setup(){
		Imovel imovelResponse = mock(Imovel.class);
		when(imovelRepository.save(any(Imovel.class))).thenReturn(imovelResponse);

	}
	
	@Test
	public void deveTestarCadastroDeImovelComSucesso(){
		Imovel imovelRequest = gerarImovelPopulado(222, 444, 3, 4, 210);
		
		ResponseEntity<Result<Imovel>> response = imovelService.addImovel(imovelRequest);
		
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(1, response.getBody().getStatus());
		assertEquals(null, response.getBody().getValidators());
	}
	
	@Test
	public void deveTestarCadastroDeImovelComErroCamposNulos(){
		Imovel imovelRequest = gerarImovelPopulado(null, null, 3, 4, 210);
				
		ResponseEntity<Result<Imovel>> response = imovelService.addImovel(imovelRequest);
		
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(0, response.getBody().getStatus());
		assertEquals("Campos de delimitaçao devem ser preenchidos", response.getBody().getValidators().get(0).getMessage());
	}
	
	@Test
	public void deveTestarCadastroDeImovelComErroCampoXInvalido(){
		Imovel imovelRequest = gerarImovelPopulado(1600, 444, 3, 4, 210);
		
		ResponseEntity<Result<Imovel>> response = imovelService.addImovel(imovelRequest);
		
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(0, response.getBody().getStatus());
		assertEquals("Valor do campo X inválido", response.getBody().getValidators().get(0).getMessage());
	}
	
	@Test
	public void deveTestarCadastroDeImovelComErroCampoYInvalido(){
		Imovel imovelRequest = gerarImovelPopulado(222, 1001, 3, 4, 210);
		
		ResponseEntity<Result<Imovel>> response = imovelService.addImovel(imovelRequest);
		
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(0, response.getBody().getStatus());
		assertEquals("Valor do campo Y inválido", response.getBody().getValidators().get(0).getMessage());
	}
	
	@Test
	public void deveTestarCadastroDeImovelComErroCampoBedsInvalido(){
		Imovel imovelRequest = gerarImovelPopulado(222, 444, 3, 0, 210);
		
		ResponseEntity<Result<Imovel>> response = imovelService.addImovel(imovelRequest);
		
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(0, response.getBody().getStatus());
		assertEquals("Quantidade de quarto deve ser no mínimo 1 e no máximo 5", response.getBody().getValidators().get(0).getMessage());
	}
	
	@Test
	public void deveTestarCadastroDeImovelComErroCampoBathsInvalido(){
		Imovel imovelRequest = gerarImovelPopulado(222, 444, 5, 3, 210);
		
		ResponseEntity<Result<Imovel>> response = imovelService.addImovel(imovelRequest);
		
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(0, response.getBody().getStatus());
		assertEquals("Quantidade de quarto deve ser no mínimo 1 e no máximo 4", response.getBody().getValidators().get(0).getMessage());
	}
	
	@Test
	public void deveTestarCadastroDeImovelComErroCampoSquareMetersInvalido(){
		Imovel imovelRequest = gerarImovelPopulado(222, 444, 2, 3, 10);
		
		ResponseEntity<Result<Imovel>> response = imovelService.addImovel(imovelRequest);
		
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(0, response.getBody().getStatus());
		assertEquals("Metragem inválida, máximo 240 metros quadrados, e no mínimo 20", response.getBody().getValidators().get(0).getMessage());
	}
	
	@Test
	public void deveTestarCadastroDeImovelComErroEmDoisCampos(){
		Imovel imovelRequest = gerarImovelPopulado(222, 444, 0, 3, 10);
		
		ResponseEntity<Result<Imovel>> response = imovelService.addImovel(imovelRequest);
		
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(0, response.getBody().getStatus());
		assertEquals(2, response.getBody().getValidators().size());
	}
	
	private Imovel gerarImovelPopulado(Integer x, Integer y, Integer baths, Integer beds, Integer meters){
		Imovel imovel = new Imovel();
		imovel.setX(x);
		imovel.setY(y);
		imovel.setBaths(baths);
		imovel.setBeds(beds);
		imovel.setSquareMeters(meters);
		imovel.setPrice(new BigInteger("1250000"));
		imovel.setTitle("Imóvel código 1, com 5 quartos e 4 banheiros");
		imovel.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		return imovel;
	}

}
