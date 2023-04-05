package MilkStgo.Tingeso1.servicies;

import MilkStgo.Tingeso1.entities.ProveedorEntity;
import MilkStgo.Tingeso1.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;

    public void guardarProveedor(String nombre, String codigo, String categoria, String retencion){
        ProveedorEntity nuevo_proveedor = new ProveedorEntity();
        nuevo_proveedor.setCodigo(Integer.parseInt(codigo));
        nuevo_proveedor.setNombre(nombre);
        nuevo_proveedor.setRetencion(retencion);
        nuevo_proveedor.setCategoria(categoria);
        proveedorRepository.save(nuevo_proveedor);
    }

    public List<ProveedorEntity> showListaProveedores(){
        return (List<ProveedorEntity>) proveedorRepository.findAll();
    }
}
