package MilkStgo.Tingeso1;

import MilkStgo.Tingeso1.entities.ResultadoEntity;
import MilkStgo.Tingeso1.repositories.ResultadoRepository;
import MilkStgo.Tingeso1.servicies.ResultadoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ResultadosTest {
    @Autowired
    ResultadoService resultadoService;
    @Autowired
    ResultadoRepository resultadoRepository;

    @Test
    void testGuardarDatosBDResultado() throws ParseException {
        String proveedor = "Proveedor1";
        String pGrasa = "3";
        String pSolidos = "15";
        resultadoService.guardarDatosBDResultado(proveedor, pGrasa, pSolidos);
    }
    @Test
    void testGuardarDatos(){
        ResultadoEntity resultadoEntity = new ResultadoEntity();
        resultadoService.guardarDatosResultado(resultadoEntity);
    }
    @Test
    void testObtenerdatos(){
        resultadoRepository.deleteAll();
        ResultadoEntity resultadosEntity = new ResultadoEntity();
        resultadoRepository.save(resultadosEntity);
        ArrayList<ResultadoEntity> datos = resultadoService.obtenerDatosResultado();
        assertEquals(datos.size(), 1);
    }
    @Test
    void testObtenerdatos2(){
        resultadoRepository.deleteAll();
        ArrayList<ResultadoEntity> datos = resultadoService.obtenerDatosResultado();
        assertEquals(datos.size(), 0);
    }
}
