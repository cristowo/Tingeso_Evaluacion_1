package MilkStgo.Tingeso1.servicies;

import MilkStgo.Tingeso1.entities.ProveedorEntity;
import MilkStgo.Tingeso1.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;

    @PostMapping("/cargarProveedor")
    public String cargarProveedor(@RequestParam("nombre") String nombre,
                                  @RequestParam("codigo") Integer codigo,
                                  @RequestParam("categoria") String categoria,
                                  @RequestParam("retencion") Boolean retencion){
        return "ola";
    }

    public void guardarProveedor(String nombre, Integer codigo, String categoria, Boolean retencion){
        ProveedorEntity nuevo_proveedor = new ProveedorEntity();
        nuevo_proveedor.setCodigo(codigo);
        nuevo_proveedor.setNombre(nombre);
        nuevo_proveedor.setRetencion(retencion);
        nuevo_proveedor.setCategoria(categoria);
        proveedorRepository.save(nuevo_proveedor);
    }
}
