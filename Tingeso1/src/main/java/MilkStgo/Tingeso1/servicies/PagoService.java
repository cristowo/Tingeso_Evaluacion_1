package MilkStgo.Tingeso1.servicies;

import MilkStgo.Tingeso1.entities.LlegadaEntity;
import MilkStgo.Tingeso1.entities.PagoEntity;
import MilkStgo.Tingeso1.entities.ProveedorEntity;
import MilkStgo.Tingeso1.entities.ResultadoEntity;
import MilkStgo.Tingeso1.repositories.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;

@Service
public class PagoService {
    @Autowired
    PagoRepository pagoRepository;

    public void setPago(String codigo){
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
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(llegadas.get(0).getFecha());
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        if(dia <= 15){
            pago.setQuincena(Integer.toString(ano)+"/"+Integer.toString(mes)+"/"+1);
        }else{
            pago.setQuincena(Integer.toString(ano)+"/"+Integer.toString(mes)+"/"+2);
        }
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
        


        //------------ Sistem out de todos los datos ------------
        System.out.println("id_proveedor: "+pago.getId_proveedor());
        System.out.println("codigo proveedor: "+pago.getCodigoProveedor());
        System.out.println("nombre proveedor: "+pago.getNombreProveedor());
        System.out.println("quincena: "+pago.getQuincena());
        System.out.println("totalKlsLeche: "+pago.getTotalKlsLeche());
        System.out.println("numDiasEnvioLeche: "+pago.getNumDiasEnvioLeche());
        System.out.println("promedioDiarioKlsLeche: "+pago.getPromedioDiarioKlsLeche());
        System.out.println("porcentajeGrasa: "+pago.getPorcentajeGrasa());
    }
    
    public Integer totalKgLeche(ArrayList<LlegadaEntity> llegadas){
        int total =0;
        for(int i = 0; i < llegadas.size(); i++){
            total = total + (llegadas.get(i).getKg_leche());
        }
        return total;
    }


}
