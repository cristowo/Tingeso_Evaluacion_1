package MilkStgo.Tingeso1.servicies;

import MilkStgo.Tingeso1.repositories.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagoService {
    @Autowired
    PagoRepository pagoRepository;
}
