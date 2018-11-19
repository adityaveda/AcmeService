# AcmeService
A mock certificate services which retrieves certificates and automatically renews them

CERTIFICATE-SERVICE
1 – /cert/{domain}

Fetch a certificate based on domain, otherwise timeout for 10 seconds and create a new certificate and return a JSON response.

Example: 
localhost:8080/cert/av
RESPONSE:

{
    "domain": "av",
    "uuID": "a2c36c5e-c084-4133-9ee4-2dd54b2d5b9b",
    "expiration": "2018/11/19 10:12:09"
}

2 – /certs

Fetch a list of all certificates as a JSON response.

Example:

Localhost:8080/certs
RESPONSE:


[
    {
        "domain": "av",
        "uuID": "a2c36c5e-c084-4133-9ee4-2dd54b2d5b9b",
        "expiration": "2018/11/19 10:12:09"
    },
    {
        "domain": "ayala",
        "uuID": "6f8ce6d4-2d58-4a26-b3d8-bdcc6dcb0cf3",
        "expiration": "2018/11/19 10:12:09"
    },
    {
        "domain": "dumm2",
        "uuID": "6fa51997-becb-4bb1-b1a8-a8d94e00d196",
        "expiration": "2018/11/19 10:12:09"
    },
    {
        "domain": "dummy1",
        "uuID": "b48ee5bf-65d3-4b5c-97a3-37adb81842b3",
        "expiration": "2018/11/19 10:12:09"
    }
]

 Installation:
Option 1:
1.	git clone
2.	sudo apt install maven
3.	mvn spring-boot:run
Option 2:
To Build .jar and execute through jar
Mvn install -DskipTests
java -jar ./target/AcmeService-1.0-SNAPSHOT.jar






