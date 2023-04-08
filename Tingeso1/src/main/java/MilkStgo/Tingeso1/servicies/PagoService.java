package MilkStgo.Tingeso1.servicies;

import MilkStgo.Tingeso1.entities.LlegadaEntity;
import MilkStgo.Tingeso1.entities.PagoEntity;
import MilkStgo.Tingeso1.entities.ProveedorEntity;
import MilkStgo.Tingeso1.entities.ResultadoEntity;
import MilkStgo.Tingeso1.repositories.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class PagoService {
    @Autowired
    PagoRepository pagoRepository;

    public void setPago(String codigo) throws ParseException {
        ProveedorEntity proveedor = pagoRepository.findByCodigoProveedor(codigo);
        PagoEntity pago = new PagoEntity();
        //----------- Atributos Basicos-----------------
        //id_proveedor
        pago.setId_proveedor(proveedor.getId_proveedor());
        //codigo proveedor
        pago.setCodigoProveedor(proveedor.getCodigo());
        //nombre proveedor
        pago.setNombreProveedor(proveedor.getNombre());
        //----------- Quincena ----------------
        ArrayList<LlegadaEntity> llegadas = pagoRepository.findAllByCodigoProveedor(codigo);
        //Revisamos en que quincena nos encontramos
        String quincena = obtenerFechaQuincena(llegadas);
        pago.setQuincena(quincena);
        //------------- Kg Leche -------------------
        int totalKgLeche = totalKgLeche(llegadas);
        pago.setTotalKlsLeche(totalKgLeche);
        //---------- Numero de dias  ----------------
        int numeroDiasEnvio = pagoRepository.countByProveedor(codigo);
        pago.setNumDiasEnvioLeche(numeroDiasEnvio);
        //------- Promedio Diario de Kg Leche  -------------
        double promedioDiarioKlsLeche = totalKgLeche/numeroDiasEnvio;
        pago.setPromedioDiarioKlsLeche(promedioDiarioKlsLeche);
        //------------ Porcentaje Grasa --------------------
        ResultadoEntity resultado = pagoRepository.findResultadosByCodigoProveedor(codigo);
        int porcentajeGrasa = resultado.getPorcentaje_grasa();
        pago.setPorcentajeGrasa(porcentajeGrasa);
        //------------ Porcentaje Solidos Totales --------------------
        int porcentajeSolidosTotales = resultado.getPorcentaje_sodio();
        pago.setPorcentajeSolidosTotales(porcentajeSolidosTotales);
        //------------ Pago por Leche ------(categoria)-------
        double pagoPorLeche = pagoPorCategoria(llegadas, proveedor.getCategoria());
        pago.setPagoPorLeche(pagoPorLeche);
        //------------ Pago por Grasa --------------------
        double pagoPorGrasa = pagoPorGrasa(llegadas, resultado.getPorcentaje_grasa());
        pago.setPagoPorGrasa(pagoPorGrasa);
        //------------ Pago por Solidos Totales --------------------
        double pagoPorSolidosTotales = pagoPorSolidos(llegadas, resultado.getPorcentaje_sodio());
        pago.setPagoPorSolidosTotales(pagoPorSolidosTotales);
        //------------ Bonificacion por Frecuencia --------------------
        double bonificacionFrecuencia = bonificacionFecuencia(llegadas, (int) pagoPorLeche);
        pago.setBonificacionPorFrecuencia(bonificacionFrecuencia);

        //---------- Calculo utilizando la quincena anterior -----------
        int idQuincenaAnterior = foundIdQuincenaAnterior(quincena, codigo);
        if(idQuincenaAnterior != 0){
            //--------------------------- Porcentaje variacion de leche ------------------------------------
            Optional<PagoEntity> pagoAnterior = pagoRepository.findById(idQuincenaAnterior);
            double totalKgLecheAnterior = pagoAnterior.get().getTotalKlsLeche();
            //recordar que si es - es porke el nuevo es mayor, osea que les dieron mas
            Double porcentajeVariacionLeche = (((totalKgLecheAnterior-totalKgLeche)/totalKgLeche)*100);
            pago.setPorcentajeVariacionLeche(porcentajeVariacionLeche);
        }else{
            pago.setPorcentajeVariacionLeche(0);
        }

        //------------ Sistem out de todos los datos ------------
        System.out.println("id_proveedor: "+pago.getId_proveedor());
        System.out.println("codigo proveedor: "+pago.getCodigoProveedor());
        System.out.println("nombre proveedor: "+pago.getNombreProveedor());
        System.out.println("quincena: "+pago.getQuincena());
        System.out.println("totalKlsLeche: "+pago.getTotalKlsLeche());
        System.out.println("numDiasEnvioLeche: "+pago.getNumDiasEnvioLeche());
        System.out.println("promedioDiarioKlsLeche: "+pago.getPromedioDiarioKlsLeche());
        System.out.println("porcentajeGrasa: "+pago.getPorcentajeGrasa());
        System.out.println("porcentajeSolidosTotales: "+pago.getPorcentajeSolidosTotales());
        System.out.println("pagoPorLeche: "+pago.getPagoPorLeche());
        System.out.println("pagoPorGrasa: "+pago.getPagoPorGrasa());
        System.out.println("pagoPorSolidosTotales: "+pago.getPagoPorSolidosTotales());
        System.out.println("bonificacionPorFrecuencia: "+pago.getBonificacionPorFrecuencia());
        System.out.println("idQuincenaAnterior: "+idQuincenaAnterior);
        System.out.println("porcentajeVariacionLeche: "+pago.getPorcentajeVariacionLeche());
        
    }

    public String obtenerFechaQuincena(ArrayList<LlegadaEntity> llegadas){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(llegadas.get(0).getFecha());
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        if(dia <= 15){
            return Integer.toString(ano)+"/"+Integer.toString(mes)+"/"+1;
        }else{
            return Integer.toString(ano)+"/"+Integer.toString(mes)+"/"+2;
        }
    }

    public Integer totalKgLeche(ArrayList<LlegadaEntity> llegadas){
        int total =0;
        for(int i = 0; i < llegadas.size(); i++){
            total = total + (llegadas.get(i).getKg_leche());
        }
        return total;
    }

    public Integer pagoPorCategoria(ArrayList<LlegadaEntity> llegadas, String categoriaLetra){
        // Pago por kilo de leche según categoría
        int categoria;
        if(categoriaLetra.equals("A")){
            categoria = 700;
        }else if(categoriaLetra.equals("B")){
            categoria = 550;
        }else if(categoriaLetra.equals("C")){
            categoria = 400;
        }else{
            categoria = 250;
        }
        // Acumulador de pago en categoría
        int pagoCategoria =0;
        for(int i = 0; i < llegadas.size(); i++){
            pagoCategoria = pagoCategoria + (categoria * llegadas.get(i).getKg_leche());
        }
        return pagoCategoria;
    }

    public Integer pagoPorGrasa(ArrayList<LlegadaEntity> llegadas, Integer resultadoGrasa){
        // Pago por grasa
        int grasa;
        if(resultadoGrasa <= 20){
            grasa = 30;
        }else if(resultadoGrasa <= 45){
            grasa = 80;
        }else{
            grasa = 120;
        }
        int pagoGrasa = 0;
        for(int i = 0; i < llegadas.size(); i++){
            pagoGrasa = pagoGrasa + (grasa * llegadas.get(i).getKg_leche());
        }
        return pagoGrasa;
    }

    public Integer pagoPorSolidos(ArrayList<LlegadaEntity> llegadas, Integer resultadoSolidos){
        // Pago por sólidos
        int solidos;
        if(resultadoSolidos <= 7){
            solidos = -130;
        }else if(resultadoSolidos <= 18){
            solidos = -90;
        }else if(resultadoSolidos <= 35){
            solidos = 95;
        }else{
            solidos = 150;
        }
        int pagoSolidos = 0;
        for(int i = 0; i < llegadas.size(); i++){
            pagoSolidos = pagoSolidos + (solidos * llegadas.get(i).getKg_leche());
        }
        return pagoSolidos;
    }

    public Double bonificacionFecuencia(ArrayList<LlegadaEntity> llegadas, Integer pagoCategoria){
        // Bonificación por frecuencia
        int mSum = 0, tSum = 0;
        for(int i = 0; i < llegadas.size(); i++){
            if(llegadas.get(i).getTurno() == "M"){
                mSum++;
            }else{
                tSum++;
            }
        }
        Double bonificacionFrecuencia = 0.0;
        if(mSum >= 10 && tSum >= 10){
            bonificacionFrecuencia = pagoCategoria * 1.2;
        }else if(mSum >= 10){
            bonificacionFrecuencia = pagoCategoria * 1.12;
        }else if(tSum >= 10){
            bonificacionFrecuencia = pagoCategoria * 1.08;
        }
        return bonificacionFrecuencia;
    }

    public Integer foundIdQuincenaAnterior(String quincena, String codigo) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date date = format.parse(quincena);
        //obtenemos la fecha de la quincena anterior (sabiendo que dd es 1 o 2)
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        String quincenaAnterior = "";
        if (dia == 1) {
            //caso especial de enero
            if (mes == 0) {
                quincenaAnterior = Integer.toString(ano - 1) + "/12/2";
            } else {
                quincenaAnterior = Integer.toString(ano) + "/" + Integer.toString(mes) + "/2";
            }
        } else {
            //vamos a la quincena anterior (mes y dia 1)
            quincenaAnterior = Integer.toString(ano) + "/" + Integer.toString(mes + 1) + "/1";
        }
        System.out.println(quincenaAnterior);
        //caso no hay quincena anterior
        PagoEntity quincenaEncontrada = pagoRepository.findPagoAnterior(quincenaAnterior, codigo);
        if (quincenaEncontrada == null) {
            return 0;
        } else {
            //caso si hay quincena anterior
            return quincenaEncontrada.getId_pago();
        }
    }
}
