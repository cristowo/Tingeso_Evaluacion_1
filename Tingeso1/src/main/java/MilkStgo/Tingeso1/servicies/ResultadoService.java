package MilkStgo.Tingeso1.servicies;

import MilkStgo.Tingeso1.entities.ResultadoEntity;
import MilkStgo.Tingeso1.repositories.ResultadoRepository;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class ResultadoService {
    @Autowired
    ResultadoRepository resultadoRepository;

    private final Logger logger = LoggerFactory.getLogger(LlegadaService.class);

    public ArrayList<ResultadoEntity> obtenerDatosResultado(){
        return (ArrayList<ResultadoEntity>) resultadoRepository.findAll();
    }

    @Generated
    public String guardarResultado(MultipartFile archivo){
        String nombre = archivo.getOriginalFilename();
        if(nombre != null) {
            if (!archivo.isEmpty()) {
                try {
                    byte[] bytes = archivo.getBytes();
                    Path path = Paths.get(archivo.getOriginalFilename());
                    Files.write(path, bytes);
                    logger.info("Archivo Guardado");
                } catch (IOException e) {
                    logger.error("Error", e);
                }
            }
            return "Archivo Guardado con exito";
        }
        else{
            return "Error al Guardar el Archivo";
        }
    }

    public void guardarDatosResultado(ResultadoEntity datos){
        resultadoRepository.save(datos);
    }

    public void guardarDatosBDResultado(String proveedor, String sodio, String grasa){
        ResultadoEntity aux = new ResultadoEntity();
        aux.setProveedor(proveedor);
        aux.setPorcentaje_sodio(Integer.parseInt(sodio));
        aux.setPorcentaje_grasa(Integer.parseInt(grasa));
        aux.setTiempo("No_configurado");
        guardarDatosResultado(aux);
    }

    public void eliminarDatos(ArrayList<ResultadoEntity> datos){
        resultadoRepository.deleteAll(datos);
    }
    public void actual(){
        ArrayList<ResultadoEntity> datos = resultadoRepository.findAllByTiempo("No_configurado");
        for(int i = 0; i < datos.size(); i++){
            datos.get(i).setTiempo("Actual");
            resultadoRepository.save(datos.get(i));
        }
    }
    public void pasado(){
        ArrayList<ResultadoEntity> datos = resultadoRepository.findAllByTiempo("Actual");
        for(int i = 0; i < datos.size(); i++){
            datos.get(i).setTiempo("Pasado");
            resultadoRepository.save(datos.get(i));
        }
    }
    public void obsoleto(){
        ArrayList<ResultadoEntity> datos = resultadoRepository.findAllByTiempo("Pasado");
        for(int i = 0; i < datos.size(); i++){
            datos.get(i).setTiempo("Obsoleto");
            resultadoRepository.save(datos.get(i));
        }
    }

    @Generated
    public String leerCsv(String archivo){
        BufferedReader bf = null;
        resultadoRepository.deleteAll();
        try{
            bf = new BufferedReader(new FileReader(archivo));
            String temp = "";
            String bfRead;
            int count = 1;
            while((bfRead = bf.readLine()) != null){
                if(count == 1){
                    count = 0;
                }else{
                    guardarDatosBDResultado(bfRead.split(";")[0], bfRead.split(";")[1], bfRead.split(";")[2]);
                    temp = temp + "\n" + bfRead;
                }
            }
            obsoleto();
            pasado();
            actual();
            return "Resultados Cargados";
        }catch(Exception e){
            return "Error al cargar resultados";
        }finally{
            if(bf != null){
                try{
                    bf.close();
                }catch(IOException e){
                    logger.error("ERROR", e);
                }
            }
        }
    }
}
