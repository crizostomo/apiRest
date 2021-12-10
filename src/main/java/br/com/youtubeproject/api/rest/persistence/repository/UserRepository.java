package br.com.youtubeproject.api.rest.persistence.repository;

import br.com.youtubeproject.api.rest.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
