package br.com.youtubeproject.api.rest.service;

import br.com.youtubeproject.api.rest.persistence.entity.User;

public interface Mapper<A, B> {

    B map(A input);
}
