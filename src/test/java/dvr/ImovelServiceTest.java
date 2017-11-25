package dvr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigInteger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
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
	
	@Test
	public void deveTestarCadastroDeImovelComSucesso(){
		Imovel imovelRequest = gerarImovelPopulado(222, 444, 3, 4, 210);
		Imovel imovelResponse = mock(Imovel.class);
		
		when(imovelRepository.save(imovelRequest)).thenReturn(imovelResponse);
		
		ResponseEntity<Result<Imovel>> response = imovelService.addImovel(imovelRequest);
		assertNotNull(response);
		assertEquals(1, response.getBody().getStatus());
		assertEquals(null, response.getBody().getValidators());
		
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

//			A área total de Spotippos é definida da seguinte forma 0 <= x <= 1400 e 0 <= y <= 1000, e a delimitação de suas províncias são encontradas neste json.
//			Um imóvel em Spotippos tem as seguintes características:
//			No máximo 5 quartos (beds), e no mínimo 1
//			No máximo 4 banheiros (baths), e no mínimo 1
//			No máximo 240 metros quadrados, e no mínimo 20
}
