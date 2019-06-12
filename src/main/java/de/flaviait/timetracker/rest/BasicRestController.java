package de.flaviait.timetracker.rest;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public interface BasicRestController {

    default URI getLocationHeader(String id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

}
