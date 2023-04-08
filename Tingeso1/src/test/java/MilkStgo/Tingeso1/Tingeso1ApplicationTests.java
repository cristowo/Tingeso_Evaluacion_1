package MilkStgo.Tingeso1;

import MilkStgo.Tingeso1.servicies.PagoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Tingeso1ApplicationTests {

	@Autowired
	PagoService pagoService;
	@Test
	void contextLoads() {
		pagoService.setPago("01003");
	}

}
