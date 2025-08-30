package SistemasDistribuidos;

import SistemasDistribuidos.Entity.*;
import SistemasDistribuidos.Entity.Enums.ActividadFisicaEnum;
import SistemasDistribuidos.Entity.Enums.DiaSemana;
import SistemasDistribuidos.Entity.Enums.RoleEnum;
import SistemasDistribuidos.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SistemasDistribuidosApplication implements CommandLineRunner {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	ActividadFisicaRepository actividadFisicaRepository;


	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	ClaseRepository claseRepository;
	@Autowired
	InscriptosRepository inscriptosRepository;
	@Transactional
	@Override
	public void run(String... args) throws Exception {
		/*Rol rol = new Rol();
		rol.setNombre(RoleEnum.USER);

		Rol rol1 = new Rol();
		rol1.setNombre(RoleEnum.ADMIN);

		roleRepository.save(rol);
		roleRepository.save(rol1);



		Rol rol=roleRepository.findById(1L).get();

		Usuario usuario = Usuario.builder()
				.nombre("Francisco")
				.email("franlugea03@gmail.com")
				.contraseña("Pancho50@")
				.roles(Set.of(rol))
				.build();
		usuario = userRepository.save(usuario);

		ActividadFisica actividadFisica = new ActividadFisica();
		actividadFisica.setNombre(ActividadFisicaEnum.CROSSFIT);

		ActividadFisica actividadFisica1 = new ActividadFisica();
		actividadFisica1.setNombre(ActividadFisicaEnum.PILATES);

		ActividadFisica actividadFisica2 = new ActividadFisica();
		actividadFisica2.setNombre(ActividadFisicaEnum.YOGA);

		ActividadFisica actividadFisica3 = new ActividadFisica();
		actividadFisica3.setNombre(ActividadFisicaEnum.ZUMBA);

		ActividadFisica actividadFisica4 = new ActividadFisica();
		actividadFisica4.setNombre(ActividadFisicaEnum.SPINNING);

		actividadFisicaRepository.saveAll(List.of(actividadFisica,actividadFisica1,actividadFisica2,actividadFisica3,actividadFisica4));

		/*ActividadFisica actividadFisica = actividadFisicaRepository.findById(1L).get();
		Clase clase= new Clase();
		clase.setActividadFisica(actividadFisica);
		clase.setCupo_maximo(10);
		clase.setHoraInicio(LocalTime.of(15,30,0));
		clase.setHoraFinal(LocalTime.of(16,30,0));
		clase.setDiaSemana(DiaSemana.JUEVES);
		clase.setInstructor("Francisco");
		clase.setCupo_disponible(5);

		claseRepository.save(clase);

		Usuario usuario = userRepository.findById(1L).get();
		Clase clase1 = claseRepository.findById(1L).get();

		Inscriptos inscriptos = new Inscriptos();
		inscriptos.setUsuario(usuario);
		inscriptos.setClase(clase1);

		inscriptosRepository.save(inscriptos);*/


	}


	public static void main(String[] args) {
		SpringApplication.run(SistemasDistribuidosApplication.class, args);
	}


}
