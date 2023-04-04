package MilkStgo.Tingeso1.servicies;

import MilkStgo.Tingeso1.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;
}
