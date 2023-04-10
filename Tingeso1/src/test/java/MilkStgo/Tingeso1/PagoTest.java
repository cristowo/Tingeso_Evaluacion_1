package MilkStgo.Tingeso1;

import MilkStgo.Tingeso1.entities.LlegadaEntity;
import MilkStgo.Tingeso1.entities.PagoEntity;
import MilkStgo.Tingeso1.servicies.PagoService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PagoTest {

    @Autowired
    PagoService pagoService;

    @Test
    // almenos tiene un pago, recordar tener archivos en database
    void testBuscarPagos() {
        PagoEntity pago = new PagoEntity();
        pago.setCodigoProveedor("01003");
        pagoService.buscarPagos("01003");
        assert pagoService.buscarPagos("01003").size() > 0;
    }
    @Test
    // 0 pagos
    void testBuscarPagos2() {
        pagoService.buscarPagos("01004");
        assert pagoService.buscarPagos("01004").size() == 0;
    }
    @Test
    void testObtenerFechaQuincena(){
        LlegadaEntity llegada = new LlegadaEntity();
        Calendar fecha = Calendar.getInstance();
        fecha.set(2021, 5, 12);
        llegada.setFecha(fecha.getTime());
        ArrayList<LlegadaEntity> llegadas = new ArrayList<LlegadaEntity>();
        llegadas.add(llegada);
        assert pagoService.obtenerFechaQuincena(llegadas).equals("2021/6/1");
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
        assert pagoService.totalKgLeche(llegadas) == 150;
    }
    @Test
    void testPagoPorCategoria(){
        LlegadaEntity llegada = new LlegadaEntity();
        LlegadaEntity llegada2 = new LlegadaEntity();
        llegada.setKg_leche(100);
        llegada2.setKg_leche(50);
        String categoria = "A";
        ArrayList<LlegadaEntity> llegadas = new ArrayList<LlegadaEntity>();
        llegadas.add(llegada);
        llegadas.add(llegada2);
        assert pagoService.pagoPorCategoria(llegadas, categoria) == 105000;
    }
    @Test
    void testPagoPorGrasa(){
        LlegadaEntity llegada = new LlegadaEntity();
        LlegadaEntity llegada2 = new LlegadaEntity();
        llegada.setKg_leche(100);
        llegada2.setKg_leche(50);
        Integer grasa = 21;
        ArrayList<LlegadaEntity> llegadas = new ArrayList<LlegadaEntity>();
        llegadas.add(llegada);
        llegadas.add(llegada2);
        assert pagoService.pagoPorGrasa(llegadas, grasa) == 12000;
    }
    @Test
    void testPagoPorSolidos(){
        LlegadaEntity llegada = new LlegadaEntity();
        LlegadaEntity llegada2 = new LlegadaEntity();
        llegada.setKg_leche(100);
        llegada2.setKg_leche(50);
        Integer solidos = 36;
        ArrayList<LlegadaEntity> llegadas = new ArrayList<LlegadaEntity>();
        llegadas.add(llegada);
        llegadas.add(llegada2);
        assert pagoService.pagoPorSolidos(llegadas, solidos) == 22500;
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
    // + de 10 llegadas
    void testbonificacionFrecuencia2(){
        ArrayList<LlegadaEntity> llegadas = new ArrayList<LlegadaEntity>();
        for (int i = 0; i < 11; i++) {
            LlegadaEntity llegada = new LlegadaEntity();
            llegada.setTurno("M");
            llegadas.add(llegada);
        }
        Integer pagoCategoria = 100000;
        assert pagoService.bonificacionFecuencia (llegadas, pagoCategoria) == 12000;
    }
    @Test
    void testFoundIdQuincenaAnterior() throws ParseException {
        String quincena = "2021/6/1";
        String codigoProveedor = "01003";
        assert pagoService.foundIdQuincenaAnterior(quincena, codigoProveedor) == 0;
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
    void descuentoVariacionLeche(){
        Double porcentajeVariacionLeche = -66.0;
        Double pagoAcopio = 1000000.0;
        assert pagoService.descuentoVariacionLeche(porcentajeVariacionLeche, pagoAcopio) == 0;
    }
    @Test
    void descuentoVariacionGrasa(){
        Double porcentajeVariacionGrasa = 16.0;
        Double pagoAcopio = 1000000.0;
        assert pagoService.descuentoVariacionGrasa(porcentajeVariacionGrasa, pagoAcopio) == 120000;
    }
    @Test
    void descuentoVariacionSolidos(){
        Double porcentajeVariacionSolidos = 35.0;
        Double pagoAcopio = 1000000.0;
        assert pagoService.descuentoVariacionSolidos(porcentajeVariacionSolidos, pagoAcopio) == 270000;
    }
}
