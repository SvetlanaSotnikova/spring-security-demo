// Application.java
package ru.gb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.gb.model.Role;
import ru.gb.model.User;
import ru.gb.repository.RoleRepository;
import ru.gb.repository.UserRepository;

import java.util.Set;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner initData(UserRepository userRepository, RoleRepository roleRepository) {
		return args -> {
			Role adminRole = new Role();
			adminRole.setName("admin");
			roleRepository.save(adminRole);

			Role readerRole = new Role();
			readerRole.setName("reader");
			roleRepository.save(readerRole);

			saveUser(userRepository, "admin", "admin", Set.of(adminRole));
			saveUser(userRepository, "reader", "reader", Set.of(readerRole));
			saveUser(userRepository, "user", "user", Set.of(readerRole));
		};
	}

	private void saveUser(UserRepository repository, String login, String password, Set<Role> roles) {
		User user = new User();
		user.setLogin(login);
		user.setPassword(new BCryptPasswordEncoder().encode(password));
		user.setRoles(roles);
		repository.save(user);
	}
}
