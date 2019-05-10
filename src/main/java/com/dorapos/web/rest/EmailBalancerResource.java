package com.dorapos.web.rest;

import com.dorapos.service.EmailBalancerService;
import com.dorapos.web.rest.errors.BadRequestAlertException;
import com.dorapos.service.dto.EmailBalancerDTO;
import com.dorapos.service.dto.EmailBalancerCriteria;
import com.dorapos.service.EmailBalancerQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.dorapos.domain.EmailBalancer}.
 */
@RestController
@RequestMapping("/api")
public class EmailBalancerResource {

    private final Logger log = LoggerFactory.getLogger(EmailBalancerResource.class);

    private static final String ENTITY_NAME = "emailBalancer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmailBalancerService emailBalancerService;

    private final EmailBalancerQueryService emailBalancerQueryService;

    public EmailBalancerResource(EmailBalancerService emailBalancerService, EmailBalancerQueryService emailBalancerQueryService) {
        this.emailBalancerService = emailBalancerService;
        this.emailBalancerQueryService = emailBalancerQueryService;
    }

    /**
     * {@code POST  /email-balancers} : Create a new emailBalancer.
     *
     * @param emailBalancerDTO the emailBalancerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new emailBalancerDTO, or with status {@code 400 (Bad Request)} if the emailBalancer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/email-balancers")
    public ResponseEntity<EmailBalancerDTO> createEmailBalancer(@RequestBody EmailBalancerDTO emailBalancerDTO) throws URISyntaxException {
        log.debug("REST request to save EmailBalancer : {}", emailBalancerDTO);
        if (emailBalancerDTO.getId() != null) {
            throw new BadRequestAlertException("A new emailBalancer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmailBalancerDTO result = emailBalancerService.save(emailBalancerDTO);
        return ResponseEntity.created(new URI("/api/email-balancers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /email-balancers} : Updates an existing emailBalancer.
     *
     * @param emailBalancerDTO the emailBalancerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated emailBalancerDTO,
     * or with status {@code 400 (Bad Request)} if the emailBalancerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the emailBalancerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/email-balancers")
    public ResponseEntity<EmailBalancerDTO> updateEmailBalancer(@RequestBody EmailBalancerDTO emailBalancerDTO) throws URISyntaxException {
        log.debug("REST request to update EmailBalancer : {}", emailBalancerDTO);
        if (emailBalancerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmailBalancerDTO result = emailBalancerService.save(emailBalancerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, emailBalancerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /email-balancers} : get all the emailBalancers.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of emailBalancers in body.
     */
    @GetMapping("/email-balancers")
    public ResponseEntity<List<EmailBalancerDTO>> getAllEmailBalancers(EmailBalancerCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EmailBalancers by criteria: {}", criteria);
        Page<EmailBalancerDTO> page = emailBalancerQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /email-balancers/count} : count all the emailBalancers.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/email-balancers/count")
    public ResponseEntity<Long> countEmailBalancers(EmailBalancerCriteria criteria) {
        log.debug("REST request to count EmailBalancers by criteria: {}", criteria);
        return ResponseEntity.ok().body(emailBalancerQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /email-balancers/:id} : get the "id" emailBalancer.
     *
     * @param id the id of the emailBalancerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the emailBalancerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/email-balancers/{id}")
    public ResponseEntity<EmailBalancerDTO> getEmailBalancer(@PathVariable Long id) {
        log.debug("REST request to get EmailBalancer : {}", id);
        Optional<EmailBalancerDTO> emailBalancerDTO = emailBalancerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(emailBalancerDTO);
    }

    /**
     * {@code DELETE  /email-balancers/:id} : delete the "id" emailBalancer.
     *
     * @param id the id of the emailBalancerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/email-balancers/{id}")
    public ResponseEntity<Void> deleteEmailBalancer(@PathVariable Long id) {
        log.debug("REST request to delete EmailBalancer : {}", id);
        emailBalancerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/email-balancers?query=:query} : search for the emailBalancer corresponding
     * to the query.
     *
     * @param query the query of the emailBalancer search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/email-balancers")
    public ResponseEntity<List<EmailBalancerDTO>> searchEmailBalancers(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of EmailBalancers for query {}", query);
        Page<EmailBalancerDTO> page = emailBalancerService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
