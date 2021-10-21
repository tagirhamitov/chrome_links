# This extension allows you to use short links like 'go/link' in your favorite web browser (assuming it's Chrome)

# How it works:
Short link consists of __area__ and __name__.
If short link is 'go/link' then 'go' is an __area__
and 'link' is a __name__

__name__ can be omited, e.g. you can type just 'go/'.
In this case __area__ is 'go' and __name__ is an empty string.

To see all your shortcuts, type:
```all/```

To add new shortcut, type:
```add/<area>/<name>?t=<target_url>```

If you don't need __name__, just type:
```add/<area>?t=<target_url>```

To delete shortcut, type:
```del/<area>/<name>```

To delete shortcut without __name__, type:
```del/<area>```

To delete all shortcuts with given __area__, type:
```del/<area>?all=true```

That's it, pleasant use :)

# Installation process:

# Attention! This part is wrong. For correct installation please reach to t.me/tagirhamitov

This is the hardest part

1) Clone this repository:<br>
```git clone https://github.com/tagirhamitov/chrome-links.git```

2) Install mysql on your computer

3) Create a new user in mysql. Let's assume that login is LOGIN and password is PASSWORD

4) Create a new database. Let's assume that name is DB_NAME

5) Create a file chrome-links-server/src/main/resources/application.properties with the following content:<br>
```
spring.datasource.url=jdbc:mysql://localhost:3306/DB_NAME
spring.datasource.username=LOGIN
spring.datasource.password=PASSWORD
spring.jpa.hibernate.ddl-auto=update

server.port=57335
```

6) Change directory to chrome-links-server:<br>
```cd chrome-links-server```

7) Build the backend:<br>
```./mvnw clean package```<br>
Or (if you use Windows):<br>
```./mvnw.cmd clean package```

8) Copy .jar file:<br>
```cp target/chrome-links-server-0.0.1-SNAPSHOT.jar ../```

9) Change directory back:<br>
```cd ..```

10) Rename it:<br>
```mv chrome-links-server-0.0.1-SNAPSHOT.jar chrome-links-server.jar```

11) Run ```enable.sh```:<br>
```sh enable.sh```

12) That's all with backend. Let's install a chrome extension.

13) Type chrome://extensions in your browser

14) Turn on the developer mode in the top right corner

15) Click on 'Load unpacked extension'

16) Choose the 'chrome-extension' folder (it's included in this repository)

17) That's it. Enjoy :)
