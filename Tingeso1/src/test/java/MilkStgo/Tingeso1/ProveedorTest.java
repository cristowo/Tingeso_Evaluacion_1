package MilkStgo.Tingeso1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import MilkStgo.Tingeso1.servicies.ProveedorService;

@SpringBootTest
class ProveedorTest {
    @Autowired
    ProveedorService proveedorService;

    @Test
    void testGuardarProveedor(){
        proveedorService.guardarProveedor("ten gris", "77777", "B", "No");
    }   
    @Test
    void testShowListaProveedores(){
        proveedorService.showListaProveedores();
    }

}
