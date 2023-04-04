package MilkStgo.Tingeso1.servicies;

import MilkStgo.Tingeso1.repositories.LlegadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LlegadaService {
    @Autowired
    LlegadaRepository llegadaRepository;
}
