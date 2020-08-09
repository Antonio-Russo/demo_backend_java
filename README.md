# Demo SpringBoot Java project backend
In questo progetto impareremo le basi per la creazione di un Backend Java basato su architettura a 
microservizi API REST.

### Requisiti 
- Database mysql su cui collegarsi con l'app ( basta un db vuoto poi le migration lo configureranno)
- Consigliato un IDE tipo Intellij o Eclipse
- Nelle migration di esempio l'utente predefinito ha queste credenziali di prova 
    - UserName: Antonio
    - Password: Antonio

Enviroment Variable
```
DB_CONN=[stringa di connesione al tuo DB ex: jdbc:mysql://localhost:3306/demo_java?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC]
DB_USER= [Il tuo user DB] 
DB_PWD=[la tua password DB] 
SPRING_PROFILES_ACTIVE=dev
```

Just launch
```
* create your Mysql DB
mvn install
mvn compile
```


Seguendo la linea dei commit imparerai in ordine a :

* Inizializzare un progetto SpringBoot con aggiunta di Swagger per documentare le API secondo le specifiche OpenAPi V3.0
* Collegare un DB Mysql tramite JPA e l'orm Hibernate per interrogare il tuo repository
* Eseguire e salvare i log della tua applicazione tramite Slf4j
* Creare un sistema di versionamento/autocreazione del DB tramite un sistema di migration Flyway
* Implementare semplici unitTest tramite Junit e supporto di un memory DB H2 
* --- da finire con API, entity, service e tutto ciò che serve
* ---

