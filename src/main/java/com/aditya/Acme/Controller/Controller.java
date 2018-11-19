package com.aditya.Acme.Controller;

import java.util.Date;
import java.util.List;

import com.aditya.Acme.Acme;
import com.aditya.Acme.Service.CertificateService;
import com.aditya.Acme.model.Certificate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Acme.class);

    @Autowired
    CertificateService certificateService;

    // -------------------Retrieve Single Certificate------------------------------------------

    @RequestMapping(value = "/cert/{domain}", method = RequestMethod.GET)
    public ResponseEntity<?> getCertificate(@PathVariable("domain") String domain) throws InterruptedException {
        logger.info("Fetching certificate for domain {}", domain);
        Certificate certificate = certificateService.findByDomain(domain);
        if (certificate == null) {
            //if certificate not found, create new certificate

            logger.info("certificate with domain {} not found, creating new certificate", domain);
            Certificate newCert = certificateService.createNew(domain, new Date());
            return new ResponseEntity<>(newCert, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(certificate, HttpStatus.OK);
        }
    }

    // -------------------Retrieve All Certificates------------------------------------------

    @RequestMapping(value = "/certs", method = RequestMethod.GET)
    public ResponseEntity<List<Certificate>> getAllCertificates() {
        logger.info("Fetching all certificates");
        List<Certificate> certificates = certificateService.findAllCertificates();
        if (certificates.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(certificates, HttpStatus.OK);
    }
}