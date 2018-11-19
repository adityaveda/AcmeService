package com.aditya.Acme.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import com.aditya.Acme.Acme;
import com.aditya.Acme.model.Certificate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service("certificateService")
public class CertificateService {
    static List<Certificate> certificates = new ArrayList<>();
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH);
    private static final Logger logger = LoggerFactory.getLogger(Acme.class);

    // Get list of all the certificates
    public List<Certificate> findAllCertificates() {
        return certificates;
    }

    //Find certificates by Domain if already stored
    public Certificate findByDomain(String domain) {
        for (Certificate certificate : certificates) {
            if (certificate.getDomain().equalsIgnoreCase(domain)) {
                return certificate;
            }
        }
        return null;
    }

    //create new Certificate if not found
    public Certificate createNew(String domain, Date date) throws InterruptedException {
        Thread.sleep(10000);
        String expiration = calcExpiration(date);
        //String hash = domain + "temp";
        UUID uuid = UUID.randomUUID();
        String hash = uuid.toString();
        Certificate certificate = new Certificate(domain, hash, expiration);
        certificates.add(certificate);
        return certificate;
    }

    //Expiration logic
    public String calcExpiration(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //manipulate date
        calendar.add(Calendar.MINUTE, 2);
        //Convert to date
        Date expirationDate = calendar.getTime();
        return dateFormat.format(expirationDate);
    }

    //Automatic renewal of  through scheduled task
    @Scheduled(fixedRate = 10000)
    public void renew() throws ParseException {
        for (Certificate certificate : certificates) {
            Date currentExpirationDate = formatter.parse(certificate.getExpiration());
            if (currentExpirationDate.compareTo(new Date()) <= 0) {
                certificate.setExpiration(calcExpiration(currentExpirationDate));
            }
        }
    }
}
