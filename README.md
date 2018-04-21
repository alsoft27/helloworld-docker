# SpringBoot helloworld en Docker

[![N|Solid](https://cdn-images-1.medium.com/max/740/1*cFEH7PQEJo7C9iRlGukWkg.png)](https://nodesource.com/products/nsolid)

Proyecto SpringBoot para crear una imagen Docker y subirlo a Docker Hub

### Requerimientos
- Linux
- Maven 3.2+
- Java 8 +
- Docker
- Id Docker Hub

#### 1.  Construir imagen con Maven

Para construir la imagen de nuestro proyecto utilizaremos un plugin de Spotify que nos facilita esta creación.
El repository tendrá el nombre de imagen que indiquemos en <configuration>..<repository>.. en este caso sera alsoft27/helloworlddocker

```xml
<properties>
   <docker.image.prefix>alsoft27</docker.image.prefix>
</properties>
<build>
    <plugins>
        <plugin>
            <groupId>com.spotify</groupId>
            <artifactId>dockerfile-maven-plugin</artifactId>
            <version>1.3.6</version>
            <!-- agregamos esta configuración para subir al imagen automaticamente -->
            <executions>
               <execution>
                 <id>default</id>
                 <phase>install</phase>
                 <goals>
                   <goal>build</goal>
                   <goal>push</goal>
                 </goals>
               </execution>
            </executions>
            <configuration>
                <repository>${docker.image.prefix}/${project.artifactId}</repository>
                <buildArgs>
                  <JAR_FILE>target/${project.build.finalName}.jar</JAR_FILE>
                </buildArgs>
             </configuration>
        </plugin>
    </plugins>
</build>
```

ejecutamos:

```ssh
$ mvn install dockerfile:build
```

#### 2.  Subir imagen a Docker Hub

Para subir la imagen, primero debemos crear un repositorio en Docker Hub.
El nombre sera el artifactId tal como indicamos en el plugin de Maven (el nombre debe ser en minúsculas).
Una vez creado el repositorio, nos logamos en Docker Hub (ejecutar como root) y hacemos push de la imagen

```ssh
$ sudo su
$ docker login
$ mvn dockerfile:push
```

#### 3.  Descargar y ejecutar

Como ya hemos subido la imagen al Docker Hub, la podemos descargar:

```ssh
$ docker pull alsoft27/helloworlddocker
```
Para ejecutar:

```ssh
$ docker run -p 8080:8080 -t
alsoft27/helloworlddocker
```

La aplicación estará accesible en http://localhost:8080, para probar esta aplicación tenemos el endpoint http://localhost:8080/helloworld


Puedes consultar el post en https://alsoft27.blogspot.com/
 
