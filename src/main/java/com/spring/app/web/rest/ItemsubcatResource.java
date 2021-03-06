package com.spring.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.spring.app.domain.Itemsubcat;
import com.spring.app.repository.ItemsubcatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * REST controller for managing Itemsubcat.
 */
@RestController
@RequestMapping("/api")
public class ItemsubcatResource {

    private final Logger log = LoggerFactory.getLogger(ItemsubcatResource.class);

    @Inject
    private ItemsubcatRepository itemsubcatRepository;

    /**
     * POST  /itemsubcats -> Create a new itemsubcat.
     */
    @RequestMapping(value = "/itemsubcats",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Itemsubcat itemsubcat) throws URISyntaxException {
        log.debug("REST request to save Itemsubcat : {}", itemsubcat);
        if (itemsubcat.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new itemsubcat cannot already have an ID").build();
        }
        itemsubcatRepository.save(itemsubcat);
        return ResponseEntity.created(new URI("/api/itemsubcats/" + itemsubcat.getId())).build();
    }

    /**
     * PUT  /itemsubcats -> Updates an existing itemsubcat.
     */
    @RequestMapping(value = "/itemsubcats",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Itemsubcat itemsubcat) throws URISyntaxException {
        log.debug("REST request to update Itemsubcat : {}", itemsubcat);
        if (itemsubcat.getId() == null) {
            return create(itemsubcat);
        }
        itemsubcatRepository.save(itemsubcat);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /itemsubcats -> get all the itemsubcats.
     */
    @RequestMapping(value = "/itemsubcats",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Itemsubcat> getAll() {
        log.debug("REST request to get all Itemsubcats");
        return itemsubcatRepository.findAll();
    }

    /**
     * GET  /itemsubcats/:id -> get the "id" itemsubcat.
     */
    @RequestMapping(value = "/itemsubcats/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Itemsubcat> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Itemsubcat : {}", id);
        Itemsubcat itemsubcat = itemsubcatRepository.findOne(id);
        if (itemsubcat == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(itemsubcat, HttpStatus.OK);
    }

    /**
     * DELETE  /itemsubcats/:id -> delete the "id" itemsubcat.
     */
    @RequestMapping(value = "/itemsubcats/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Itemsubcat : {}", id);
        itemsubcatRepository.delete(id);
    }
}
