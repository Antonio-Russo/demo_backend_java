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
JWT_SECRET=[inserisci una stringa di sicurezza random per la firma del JWT]
JWT_HOUR_EXPIRATION=[indica in numero di ore di durata del token, inserire un long]
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
* Implementato un sistema di autenticazione tramite token stateless in standard JWT e inserito 2 controller 
    * uno per ottenere il token tramite un payload json di Username e Password che controlla sul DB Mysql
    * uno per fare un refresh del token ed estenderne la validità
    * aggiunto uno Unit Test per UserService
    * aggiunti due Test di integrazione per i metodi del controller di autorizzazione
    * piccoli fix su bug della JwtUtils per errori trovati con i test di integrazione
* ---

