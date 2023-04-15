package MilkStgo.Tingeso1;

import MilkStgo.Tingeso1.entities.LlegadaEntity;
import MilkStgo.Tingeso1.entities.PagoEntity;
import MilkStgo.Tingeso1.entities.ProveedorEntity;
import MilkStgo.Tingeso1.entities.ResultadoEntity;
import MilkStgo.Tingeso1.repositories.LlegadaRepository;
import MilkStgo.Tingeso1.repositories.PagoRepository;
import MilkStgo.Tingeso1.repositories.ProveedorRepository;
import MilkStgo.Tingeso1.repositories.ResultadoRepository;
import MilkStgo.Tingeso1.servicies.PagoService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PagoTest {

    @Autowired
    PagoService pagoService;
    @Autowired
    PagoRepository pagoRepository;

    @Test
    void testBuscarPagos() {
        PagoEntity pago = new PagoEntity();
        pago.setCodigoProveedor("01003");
        pagoService.buscarPagos("01003");
        assert pagoService.buscarPagos("01003").size() > 0;
    }
    @Test
    void testBuscarPagos2() {
        assertEquals(0, pagoService.buscarPagos("01004").size());
    }

    @Test
    void testObtenerFechaQuincena(){
        LlegadaEntity llegada = new LlegadaEntity();
        Calendar fecha = Calendar.getInstance();
        fecha.set(2021, 5, 12);
        llegada.setFecha(fecha.getTime());
        ArrayList<LlegadaEntity> llegadas = new ArrayList<LlegadaEntity>();
        llegadas.add(llegada);
        assertEquals("2021/6/1", pagoService.obtenerFechaQuincena(llegadas));
    }

    @Test
    void testObtenerFechaQuincena2(){
        LlegadaEntity llegada = new LlegadaEntity();
        Calendar fecha = Calendar.getInstance();
        fecha.set(2021, 5, 20);
        llegada.setFecha(fecha.getTime());
        ArrayList<LlegadaEntity> llegadas = new ArrayList<LlegadaEntity>();
        llegadas.add(llegada);
        assertEquals("2021/6/2", pagoService.obtenerFechaQuincena(llegadas));
    }

    @Test
    void testTotalKgLeche(){
        LlegadaEntity llegada = new LlegadaEntity();
        LlegadaEntity llegada2 = new LlegadaEntity();
        llegada.setKg_leche(100);
        llegada2.setKg_leche(50);
        ArrayList<LlegadaEntity> llegadas = new ArrayList<LlegadaEntity>();
        llegadas.add(llegada);
        llegadas.add(llegada2);
        assertEquals(150, pagoService.totalKgLeche(llegadas));
    }

    @Test
    void testPagoPorCategoriaA(){
        LlegadaEntity llegada = new LlegadaEntity();
        LlegadaEntity llegada2 = new LlegadaEntity();
        llegada.setKg_leche(100);
        llegada2.setKg_leche(50);
        String categoria = "A";
        ArrayList<LlegadaEntity> llegadas = new ArrayList<LlegadaEntity>();
        llegadas.add(llegada);
        llegadas.add(llegada2);
        assertEquals(105000, pagoService.pagoPorCategoria(llegadas, categoria));
    }

    @Test
    void testPagoPorCategoriaB(){
        LlegadaEntity llegada = new LlegadaEntity();
        LlegadaEntity llegada2 = new LlegadaEntity();
        llegada.setKg_leche(100);
        llegada2.setKg_leche(50);
        String categoria = "B";
        ArrayList<LlegadaEntity> llegadas = new ArrayList<LlegadaEntity>();
        llegadas.add(llegada);
        llegadas.add(llegada2);
        assertEquals(82500, pagoService.pagoPorCategoria(llegadas, categoria));
    }

    @Test
    void testPagoPorCategoriaC(){
        LlegadaEntity llegada = new LlegadaEntity();
        LlegadaEntity llegada2 = new LlegadaEntity();
        llegada.setKg_leche(100);
        llegada2.setKg_leche(50);
        String categoria = "C";
        ArrayList<LlegadaEntity> llegadas = new ArrayList<LlegadaEntity>();
        llegadas.add(llegada);
        llegadas.add(llegada2);
        assertEquals(60000, pagoService.pagoPorCategoria(llegadas, categoria));
    }
    @Test
    void testPagoPorCategoriaD(){
        LlegadaEntity llegada = new LlegadaEntity();
        LlegadaEntity llegada2 = new LlegadaEntity();
        llegada.setKg_leche(100);
        llegada2.setKg_leche(50);
        String categoria = "D";
        ArrayList<LlegadaEntity> llegadas = new ArrayList<LlegadaEntity>();
        llegadas.add(llegada);
        llegadas.add(llegada2);
        assertEquals(37500, pagoService.pagoPorCategoria(llegadas, categoria));
    }

    @Test
    void testPagoPorGrasaResultadoGrasaMenorIgualA20(){
        LlegadaEntity llegada = new LlegadaEntity();
        LlegadaEntity llegada2 = new LlegadaEntity();
        llegada.setKg_leche(100);
        llegada2.setKg_leche(50);
        Integer grasa = 20;
        ArrayList<LlegadaEntity> llegadas = new ArrayList<LlegadaEntity>();
        llegadas.add(llegada);
        llegadas.add(llegada2);
        assertEquals(4500, pagoService.pagoPorGrasa(llegadas, grasa));
    }

    @Test
    void testPagoPorGrasaResultadoGrasaEntre21Y45(){
        LlegadaEntity llegada = new LlegadaEntity();
        LlegadaEntity llegada2 = new LlegadaEntity();
        llegada.setKg_leche(100);
        llegada2.setKg_leche(50);
        Integer grasa = 30;
        ArrayList<LlegadaEntity> llegadas = new ArrayList<LlegadaEntity>();
        llegadas.add(llegada);
        llegadas.add(llegada2);
        assertEquals(12000, pagoService.pagoPorGrasa(llegadas, grasa));
    }

    @Test
    void testPagoPorGrasaResultadoGrasaMayorA45(){
        LlegadaEntity llegada = new LlegadaEntity();
        LlegadaEntity llegada2 = new LlegadaEntity();
        llegada.setKg_leche(100);
        llegada2.setKg_leche(50);
        Integer grasa = 50;
        ArrayList<LlegadaEntity> llegadas = new ArrayList<LlegadaEntity>();
        llegadas.add(llegada);
        llegadas.add(llegada2);
        assertEquals(18000, pagoService.pagoPorGrasa(llegadas, grasa));
    }
    @Test
    void testPagoPorSolidosResultadoMenorIgualA7() {
        LlegadaEntity llegada = new LlegadaEntity();
        LlegadaEntity llegada2 = new LlegadaEntity();
        llegada.setKg_leche(100);
        llegada2.setKg_leche(50);
        Integer solidos = 7;
        ArrayList<LlegadaEntity> llegadas = new ArrayList<LlegadaEntity>();
        llegadas.add(llegada);
        llegadas.add(llegada2);
        assertEquals(-19500, pagoService.pagoPorSolidos(llegadas, solidos));
    }

    @Test
    void testPagoPorSolidosResultadoMayorA7MenorIgualA18() {
        LlegadaEntity llegada = new LlegadaEntity();
        LlegadaEntity llegada2 = new LlegadaEntity();
        llegada.setKg_leche(100);
        llegada2.setKg_leche(50);
        Integer solidos = 18;
        ArrayList<LlegadaEntity> llegadas = new ArrayList<LlegadaEntity>();
        llegadas.add(llegada);
        llegadas.add(llegada2);
        assertEquals(-13500, pagoService.pagoPorSolidos(llegadas, solidos));
    }

    @Test
    void testPagoPorSolidosResultadoMayorA18MenorIgualA35() {
        LlegadaEntity llegada = new LlegadaEntity();
        LlegadaEntity llegada2 = new LlegadaEntity();
        llegada.setKg_leche(100);
        llegada2.setKg_leche(50);
        Integer solidos = 35;
        ArrayList<LlegadaEntity> llegadas = new ArrayList<LlegadaEntity>();
        llegadas.add(llegada);
        llegadas.add(llegada2);
        assertEquals(14250, pagoService.pagoPorSolidos(llegadas, solidos));
    }

    @Test
    void testPagoPorSolidosResultadoMayorA35() {
        LlegadaEntity llegada = new LlegadaEntity();
        LlegadaEntity llegada2 = new LlegadaEntity();
        llegada.setKg_leche(100);
        llegada2.setKg_leche(50);
        Integer solidos = 36;
        ArrayList<LlegadaEntity> llegadas = new ArrayList<LlegadaEntity>();
        llegadas.add(llegada);
        llegadas.add(llegada2);
        assertEquals(22500, pagoService.pagoPorSolidos(llegadas, solidos));
    }
    @Test
    void testbonificacionFrecuencia(){
        LlegadaEntity llegada = new LlegadaEntity();
        llegada.setTurno("M");
        ArrayList<LlegadaEntity> llegadas = new ArrayList<LlegadaEntity>();
        llegadas.add(llegada);
        Integer pagoCategoria = 100000;
        assert pagoService.bonificacionFecuencia (llegadas, pagoCategoria) == 0.0;
    }
    @Test
    void testBonificacionFrecuenciaAmbosTurnosMayorIgualA10(){
        ArrayList<LlegadaEntity> llegadas = new ArrayList<LlegadaEntity>();
        Integer pagoCategoria = 100000;
        for (int i = 0; i < 10; i++) {
            LlegadaEntity llegadaM = new LlegadaEntity();
            LlegadaEntity llegadaT = new LlegadaEntity();
            llegadaM.setTurno("M");
            llegadaT.setTurno("T");
            llegadas.add(llegadaM);
            llegadas.add(llegadaT);
        }
        assertEquals(20000., pagoService.bonificacionFecuencia(llegadas, pagoCategoria));
    }
    @Test
    void testBonificacionFrecuenciaTurnoMMayorIgualA10(){
        ArrayList<LlegadaEntity> llegadas = new ArrayList<LlegadaEntity>();
        Integer pagoCategoria = 100000;
        for (int i = 0; i < 10; i++) {
            LlegadaEntity llegadaM = new LlegadaEntity();
            llegadaM.setTurno("M");
            llegadas.add(llegadaM);
        }
        assertEquals(12000, pagoService.bonificacionFecuencia(llegadas, pagoCategoria));
    }

    @Test
    void testBonificacionFrecuenciaTurnoTMayorIgualA10(){
        ArrayList<LlegadaEntity> llegadas = new ArrayList<LlegadaEntity>();
        Integer pagoCategoria = 100000;
        for (int i = 0; i < 10; i++) {
            LlegadaEntity llegadaT = new LlegadaEntity();
            llegadaT.setTurno("T");
            llegadas.add(llegadaT);
        }
        Double expected = pagoCategoria * 0.08;
        assertEquals(8000, pagoService.bonificacionFecuencia(llegadas, pagoCategoria));
    }
    @Test
    void testFoundIdQuincenaAnterior() throws ParseException {
        String quincena = "2021/6/1";
        String codigoProveedor = "01003";
        assert pagoService.foundIdQuincenaAnterior(quincena, codigoProveedor) == 0;
    }
    @Test
    void testFoundIdQuincenaAnterior2() throws ParseException {
        pagoRepository.deleteAll();
        PagoEntity pagoAnterior = new PagoEntity();
        pagoAnterior.setQuincena("2021/6/1");
        pagoAnterior.setCodigoProveedor("01003");
        pagoRepository.save(pagoAnterior);
        String quincena = "2021/6/2";
        String codigoProveedor = "01003";
        assert pagoService.foundIdQuincenaAnterior(quincena, codigoProveedor) != 0;
    }
    @Test
    void testFoundIdQuincenaAnterior3() throws ParseException {
        pagoRepository.deleteAll();
        PagoEntity pagoAnterior = new PagoEntity();
        pagoAnterior.setQuincena("2021/6/2");
        pagoAnterior.setCodigoProveedor("01003");
        pagoRepository.save(pagoAnterior);
        String quincena = "2021/7/1";
        String codigoProveedor = "01003";
        assert pagoService.foundIdQuincenaAnterior(quincena, codigoProveedor) != 0;
    }
    @Test
    void testPorcentajeVariacionLeche(){
        PagoEntity pagoAnterior = new PagoEntity();
        pagoAnterior.setId_pago(1000);
        pagoAnterior.setTotalKlsLeche(10);
        Integer totalLecheActual = 30;
        assert pagoService.porcentajeVariacionLeche(pagoAnterior, totalLecheActual) == -66.66666666666666;
    }
    @Test
    void testPorcentajeVariacionGrasa(){
        PagoEntity pagoAnterior = new PagoEntity();
        pagoAnterior.setId_pago(1000);
        pagoAnterior.setPorcentajeGrasa(10);
        Integer PorcentajeGrasaActual = 20;
        assert pagoService.porcentajeVariacionGrasa(pagoAnterior, PorcentajeGrasaActual) == -0.5;
    }
    @Test
    void testPorcentajeVariacionSolidos(){
        PagoEntity pagoAnterior = new PagoEntity();
        pagoAnterior.setId_pago(1000);
        pagoAnterior.setPorcentajeSolidosTotales(30);
        Integer PorcentajeSolidosActual = 10;
        assert pagoService.porcentajeVariacionSolidos(pagoAnterior, PorcentajeSolidosActual) == 2;
    }
    @Test
    void testDescuentoVariacionLechePorcentajeMenorIgualA8(){
        Double porcentajeVariacionLeche = 8.0;
        Double pagoAcopio = 1000000.0;
        assert pagoService.descuentoVariacionLeche(porcentajeVariacionLeche, pagoAcopio) == 0.0;
    }

    @Test
    void testDescuentoVariacionLechePorcentajeMenorIgualA25(){
        Double porcentajeVariacionLeche = 25.0;
        Double pagoAcopio = 1000000.0;
        assert pagoService.descuentoVariacionLeche(porcentajeVariacionLeche, pagoAcopio) == 0.07 * pagoAcopio;
    }

    @Test
    void testDescuentoVariacionLechePorcentajeMenorIgualA45(){
        Double porcentajeVariacionLeche = 45.0;
        Double pagoAcopio = 1000000.0;
        assert pagoService.descuentoVariacionLeche(porcentajeVariacionLeche, pagoAcopio) == 0.15 * pagoAcopio;
    }

    @Test
    void testDescuentoVariacionLechePorcentajeMayorA45(){
        Double porcentajeVariacionLeche = 66.0;
        Double pagoAcopio = 1000000.0;
        assert pagoService.descuentoVariacionLeche(porcentajeVariacionLeche, pagoAcopio) == 0.30 * pagoAcopio;
    }
    @Test
    void testDescuentoVariacionGrasaPorcentajeMenorIgualA15(){
        Double porcentajeVariacionGrasa = 15.0;
        Double pagoAcopio = 1000000.0;
        assert pagoService.descuentoVariacionGrasa(porcentajeVariacionGrasa, pagoAcopio) == 0.0;
    }

    @Test
    void testDescuentoVariacionGrasaPorcentajeMenorIgualA25(){
        Double porcentajeVariacionGrasa = 25.0;
        Double pagoAcopio = 1000000.0;
        assert pagoService.descuentoVariacionGrasa(porcentajeVariacionGrasa, pagoAcopio) == 0.12 * pagoAcopio;
    }

    @Test
    void testDescuentoVariacionGrasaPorcentajeMenorIgualA40(){
        Double porcentajeVariacionGrasa = 40.0;
        Double pagoAcopio = 1000000.0;
        assert pagoService.descuentoVariacionGrasa(porcentajeVariacionGrasa, pagoAcopio) == 0.20 * pagoAcopio;
    }

    @Test
    void testDescuentoVariacionGrasaPorcentajeMayorA40(){
        Double porcentajeVariacionGrasa = 66.0;
        Double pagoAcopio = 1000000.0;
        assert pagoService.descuentoVariacionGrasa(porcentajeVariacionGrasa, pagoAcopio) == 0.30 * pagoAcopio;
    }

    @Test
    void testDescuentoVariacionSolidosPorcentajeMenorIgualA6(){
        Double porcentajeVariacionSolidos = 6.0;
        Double pagoAcopio = 1000000.0;
        assert pagoService.descuentoVariacionSolidos(porcentajeVariacionSolidos, pagoAcopio) == 0.0;
    }

    @Test
    void testDescuentoVariacionSolidosPorcentajeMenorIgualA12(){
        Double porcentajeVariacionSolidos = 12.0;
        Double pagoAcopio = 1000000.0;
        assert pagoService.descuentoVariacionSolidos(porcentajeVariacionSolidos, pagoAcopio) == 0.18 * pagoAcopio;
    }

    @Test
    void testDescuentoVariacionSolidosPorcentajeMenorIgualA35(){
        Double porcentajeVariacionSolidos = 35.0;
        Double pagoAcopio = 1000000.0;
        assert pagoService.descuentoVariacionSolidos(porcentajeVariacionSolidos, pagoAcopio) == 0.27 * pagoAcopio;
    }

    @Test
    void testDescuentoVariacionSolidosPorcentajeMayorA35(){
        Double porcentajeVariacionSolidos = 66.0;
        Double pagoAcopio = 1000000.0;
        assert pagoService.descuentoVariacionSolidos(porcentajeVariacionSolidos, pagoAcopio) == 0.45 * pagoAcopio;
    }

}
