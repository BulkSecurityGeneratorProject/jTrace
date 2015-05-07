package com.spring.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.spring.app.domain.Workorderheader;
import com.spring.app.repository.WorkorderheaderRepository;
import com.spring.app.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
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
 * REST controller for managing Workorderheader.
 */
@RestController
@RequestMapping("/api")
public class WorkorderheaderResource {

    private final Logger log = LoggerFactory.getLogger(WorkorderheaderResource.class);

    @Inject
    private WorkorderheaderRepository workorderheaderRepository;

    /**
     * POST  /workorderheaders -> Create a new workorderheader.
     */
    @RequestMapping(value = "/workorderheaders",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Workorderheader workorderheader) throws URISyntaxException {
        log.debug("REST request to save Workorderheader : {}", workorderheader);
        if (workorderheader.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new workorderheader cannot already have an ID").build();
        }
        workorderheaderRepository.save(workorderheader);
        return ResponseEntity.created(new URI("/api/workorderheaders/" + workorderheader.getId())).build();
    }

    /**
     * PUT  /workorderheaders -> Updates an existing workorderheader.
     */
    @RequestMapping(value = "/workorderheaders",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Workorderheader workorderheader) throws URISyntaxException {
        log.debug("REST request to update Workorderheader : {}", workorderheader);
        if (workorderheader.getId() == null) {
            return create(workorderheader);
        }
        workorderheaderRepository.save(workorderheader);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /workorderheaders -> get all the workorderheaders.
     */
    @RequestMapping(value = "/workorderheaders",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Workorderheader>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<Workorderheader> page = workorderheaderRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/workorderheaders", offset, limit);
        return new ResponseEntity<List<Workorderheader>>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /workorderheaders/:id -> get the "id" workorderheader.
     */
    @RequestMapping(value = "/workorderheaders/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Workorderheader> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Workorderheader : {}", id);
        Workorderheader workorderheader = workorderheaderRepository.findOne(id);
        if (workorderheader == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(workorderheader, HttpStatus.OK);
    }

    /**
     * DELETE  /workorderheaders/:id -> delete the "id" workorderheader.
     */
    @RequestMapping(value = "/workorderheaders/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Workorderheader : {}", id);
        workorderheaderRepository.delete(id);
    }
}
