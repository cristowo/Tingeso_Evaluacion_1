package MilkStgo.Tingeso1;

import MilkStgo.Tingeso1.entities.LlegadaEntity;
import MilkStgo.Tingeso1.repositories.LlegadaRepository;
import MilkStgo.Tingeso1.servicies.LlegadaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;

@SpringBootTest
class LlegadaTest {
    @Autowired
    LlegadaService llegadaService;
    @Autowired
    LlegadaRepository llegadaRepository;
    @Test
    void testGuardarDatosBD() throws ParseException {
        String fecha = "12-04-2023";
        String turno = "Mañana";
        String proveedor = "Proveedor1";
        String kg_leche = "100";
        llegadaService.guardarDatosBD(fecha, turno, proveedor, kg_leche);
    }
    @Test
    void testGuardarDatos(){
        LlegadaEntity llegadaEntity = new LlegadaEntity();
        llegadaService.guardarDatos(llegadaEntity);
    }
    @Test
    void testObtenerdatos(){
        llegadaRepository.deleteAll();
        LlegadaEntity llegadaEntity = new LlegadaEntity();
        llegadaRepository.save(llegadaEntity);
        ArrayList<LlegadaEntity> datos = llegadaService.obtenerDatos();
        assertEquals(datos.size(), 1);
    }
    @Test
    void testObtenerdatos2(){
        llegadaRepository.deleteAll();
        ArrayList<LlegadaEntity> datos = llegadaService.obtenerDatos();
        assertEquals(datos.size(), 0);
    }
}
