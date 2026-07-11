# Semana 17 "Laboratorio"

## 👥 Equipo de Desarrollo
**Laboratorio 7**
* **Docente:** Ing Gomez Peña Jose Peña
* **Integrantes:**
  * Morales Campos, Luis Alejandro
  * Pacoricona Paredes, Sthin Vidal
  * Sosa Navarro, Luis Armando
  * Sanca Tenorio, Julinho Edgar
  * Tomas Criales, Leonard

## 📝 Descripción del proyecto
Este es un repositorio privado creado en el año 2026. El código fuente debe mantenerse confidencial hasta que el sistema sea publicado. El objetivo es aplicar un flujo de trabajo estructurado en el control de versiones mediante Git y GitHub.

## 🛠 Tecnologías y Herramientas utilizadas
* **Lenguaje:** Java
* **Gestión de proyectos:** GitHub Projects organizado en las siguientes columnas: Pendiente, En progreso, En revisión, En pruebas y Finalizado.
* **Automatización:** GitHub Actions. Se utiliza para automatizar procesos como compilación, pruebas automáticas, despliegues, análisis de código y publicación de versiones.

## ⚙️ Instalación
Para trabajar en este proyecto, necesitas crear una copia completa del repositorio remoto en la computadora local. Esto permite trabajar sin conexión, realizar cambios antes de subirlos y facilitar las pruebas sin afectar el repositorio remoto.

```bash
git clone [https://github.com/usuario/repositorio.git](https://github.com/usuario/repositorio.git)
```

---

## 📚 Anexo: Cuestionario y Guía de Buenas Prácticas (Laboratorio 7)

### Ejercicio 1. Creación de un repositorio (Luis Morales)
**1. ¿Qué pasos seguirías para crear el repositorio?**
* Iniciar sesión en GitHub.
* Hacer clic en New Repository.
* Escribir el nombre del repositorio (por ejemplo, Sistema-Inventarios).
* Elegir la visibilidad (público o privado).
* Agregar un archivo README, un archivo .gitignore y una licencia si es necesario.
* Crear el repositorio.

**2. ¿Qué visibilidad elegirías (público o privado)? Justifica.**
Elegiría un repositorio privado, ya que el proyecto pertenece a una empresa y el código fuente debe mantenerse confidencial hasta que el sistema sea publicado.

**3. ¿Qué archivos iniciales agregarías al repositorio?**
* README.md
* .gitignore
* LICENSE (si corresponde)
* Carpeta de documentación (docs/)

### Ejercicio 2. Clonación del proyecto (Luis Morales)
**1. ¿Qué significa clonar un repositorio?**
Es crear una copia completa del repositorio remoto en la computadora local para poder trabajar en él.

**2. ¿Qué comando utilizaría para clonar el proyecto?**
`git clone https://github.com/usuario/repositorio.git`

**3. ¿Qué ventajas tiene trabajar sobre una copia local?**
* Permite trabajar sin conexión.
* Se pueden realizar cambios antes de subirlos.
* Facilita realizar pruebas sin afectar el repositorio remoto.

### Ejercicio 3. Control de versiones (Luis Morales)
**1. ¿Qué herramienta de GitHub le permite revisar el historial?**
El historial de Commits y la pestaña History del repositorio.

**2. ¿Cómo podría restaurar una versión anterior?**
Puede usar:
`git checkout <id-del-commit>` 
o 
`git revert <id-del-commit>` 
según el caso.

**3. ¿Por qué es importante registrar cambios mediante commits?**
Porque permite llevar un historial de cambios, recuperar versiones anteriores y facilitar el trabajo colaborativo.

### Ejercicio 4. Commits significativos (Luis Morales)
**1. ¿Es recomendable esta práctica? ¿Por qué?**
No. Un único commit con muchos cambios dificulta identificar errores y comprender qué modificaciones se realizaron.

**2. Propón un mensaje de commit adecuado.**
"Implementa validación de usuarios y corrige errores en el formulario de inicio de sesión"

**3. ¿Qué beneficios tiene realizar commits pequeños y descriptivos?**
* Facilitan la revisión del código.
* Simplifican la búsqueda de errores.
* Mejoran la colaboración.
* Permiten revertir cambios específicos.

### Ejercicio 5. Trabajo con ramas (Pacoricona Sthin)
**1. ¿Qué estrategia recomendarías?**
Crear una rama independiente para cada nueva funcionalidad.

**2. ¿Cómo se crea una nueva rama?**
`git checkout -b nueva-funcionalidad`

**3. ¿Cuándo debería fusionarse con la rama principal?**
Después de completar las pruebas, revisar el código y aprobar el Pull Request.

### Ejercicio 6. Resolución de conflictos (Pacoricona Sthin)
**1. ¿Qué es un conflicto de fusión?**
Es una situación en la que Git no puede combinar automáticamente cambios realizados sobre la misma parte de un archivo.

**2. ¿Cómo puede resolverse?**
* Revisar el archivo en conflicto.
* Elegir o combinar los cambios.
* Guardar el archivo.
* Realizar un commit de la resolución.

**3. ¿Cómo evitar conflictos frecuentes?**
* Actualizar la rama con frecuencia.
* Hacer commits pequeños.
* Dividir el trabajo entre los desarrolladores.
* Mantener buena comunicación.

### Ejercicio 7. Pull Request (Pacoricona Sthin)
**1. ¿Qué es un Pull Request?**
Es una solicitud para integrar los cambios de una rama a otra, normalmente hacia la rama principal.

**2. ¿Qué información debería incluir?**
* Descripción de los cambios.
* Objetivo de la funcionalidad.
* Evidencias o capturas de pantalla.
* Issues relacionados.

**3. ¿Por qué es importante la revisión antes de aceptar el cambio?**
Porque ayuda a detectar errores, mejorar la calidad del código y asegurar que se cumplan los estándares del proyecto.

### Ejercicio 8. Code Review (Pacoricona Sthin)
**1. ¿Cuál es el objetivo del Code Review?**
Revisar el código antes de integrarlo para mejorar su calidad.

**2. ¿Qué aspectos deberían revisarse?**
* Correcto funcionamiento.
* Calidad del código.
* Seguridad.
* Rendimiento.
* Buenas prácticas.
* Documentación.

**3. ¿Qué beneficios aporta al proyecto?**
* Reduce errores.
* Mejora el aprendizaje del equipo.
* Incrementa la calidad del software.
* Facilita el mantenimiento.

### Ejercicio 9. Issues (Leonard Tomas)
**1. ¿Cómo registrarías este problema en GitHub?**
Creando un Issue con un título descriptivo.

**2. ¿Qué información incluirías?**
* Descripción del error.
* Pasos para reproducirlo.
* Resultado esperado.
* Resultado obtenido.
* Capturas de pantalla.
* Sistema operativo y navegador.

**3. ¿Cómo asignarías el Issue a un desarrollador?**
Usando la opción Assignees y seleccionando al desarrollador responsable.

### Ejercicio 10. Projects (Leonard Tomas)
**1. ¿Qué herramienta ofrece GitHub para ello?**
GitHub Projects.

**2. ¿Qué columnas crearías?**
* Pendiente
* En progreso
* En revisión
* En pruebas
* Finalizado

**3. ¿Cómo se relacionan los Issues con el tablero?**
Cada Issue puede convertirse en una tarjeta dentro del tablero y moverse entre columnas según su avance.

### Ejercicio 11. Etiquetas (Labels) (Leonard Tomas)
**1. ¿Cómo ayudan las etiquetas?**
Permiten clasificar y organizar las incidencias según su tipo, prioridad o estado.

**2. Propón cinco etiquetas útiles.**
* Bug
* Mejora
* Documentación
* Alta prioridad
* Pendiente de revisión

**3. ¿Qué ventajas ofrecen para la gestión del proyecto?**
* Facilitan la búsqueda.
* Priorizan tareas.
* Mejoran la organización.
* Ayudan en la planificación.

### Ejercicio 12. Releases (Leonard Tomas)
**1. ¿Qué es un Release?**
Es una versión oficial del software preparada para su distribución.

**2. ¿Qué información debe contener?**
* Número de versión.
* Fecha.
* Novedades.
* Corrección de errores.
* Archivos descargables.

**3. ¿Qué diferencia existe entre un commit y un Release?**
Un commit registra cambios individuales, mientras que un Release representa una versión estable del proyecto.

### Ejercicio 13. GitHub Actions
**1. ¿Qué herramienta utilizarías?**
GitHub Actions.

**2. ¿Qué beneficios ofrece?**
* Automatiza procesos.
* Reduce errores manuales.
* Ahorra tiempo.
* Mejora la integración continua.

**3. ¿Qué actividades puede automatizar?**
* Compilación.
* Pruebas automáticas.
* Despliegues.
* Análisis de código.
* Publicación de versiones.

### Ejercicio 14. README
**1. ¿Qué problemas ocasiona no tener uno?**
* Dificulta comprender el proyecto.
* Complica la instalación.
* Reduce la colaboración.

**2. ¿Qué información debería contener el README?**
* Descripción del proyecto.
* Requisitos.
* Instalación.
* Uso.
* Tecnologías utilizadas.
* Contribución.
* Licencia.

**3. ¿Por qué es importante mantenerlo actualizado?**
Porque garantiza que la información sea correcta y útil para los colaboradores y usuarios.

### Ejercicio 15. Fork
**1. ¿Qué funcionalidad debe utilizar?**
Fork.

**2. ¿Cuál es la diferencia entre un Fork y un Clone?**
* Fork: crea una copia del repositorio en otra cuenta de GitHub.
* Clone: crea una copia local del repositorio en la computadora.

**3. ¿Cómo enviaría posteriormente sus mejoras?**
Creando un Pull Request desde su Fork hacia el repositorio original.

### Ejercicio 16. Gestión de colaboradores
**1. ¿Cómo agregarías colaboradores?**
Desde Settings → Collaborators, invitando a los desarrolladores por su usuario o correo electrónico.

**2. ¿Qué permisos pueden asignarse?**
* Read (Lectura)
* Triage
* Write (Escritura)
* Maintain
* Admin

**3. ¿Por qué es importante controlar los permisos?**
Para proteger el proyecto, evitar cambios no autorizados y asignar responsabilidades de acuerdo con el rol de cada integrante.

### Ejercicio 17. Protección de ramas
**1. ¿Qué medida implementarías?**
Activar la protección de la rama principal.

**2. ¿Qué reglas establecerías?**
* Prohibir cambios directos.
* Exigir Pull Request.
* Requerir revisiones antes del merge.
* Exigir pruebas automáticas exitosas.
* Restringir quién puede hacer merge.

**3. ¿Qué beneficios aporta esta configuración?**
Reduce errores en producción, mejora la calidad del código y protege la rama principal.

### Ejercicio 18. Documentación colaborativa
**1. ¿Qué herramientas de GitHub utilizarías?**
* README.
* Wiki.
* Carpeta docs/.
* GitHub Pages (si se requiere publicar documentación).

**2. ¿Cómo organizarías la documentación?**
En secciones:
* Instalación.
* Arquitectura.
* Manual de usuario.
* API.
* Guía para desarrolladores.

**3. ¿Qué ventajas tiene mantener la documentación en el mismo repositorio?**
* Siempre está sincronizada con el código.
* Facilita las actualizaciones.
* Mejora la colaboración.
* Centraliza toda la información.

### Ejercicio 19. Gestión de versiones
**1. ¿Cómo organizarías las ramas?**
* main para producción.
* release/1.0.
* release/2.0.
* Ramas de funcionalidades para cada versión.

**2. ¿Qué estrategia recomendarías para mantener ambas versiones?**
Mantener ramas de mantenimiento para la versión 1.0 y desarrollar nuevas funciones en la rama de la versión 2.0.

**3. ¿Cómo evitarías mezclar cambios entre ellas?**
* Trabajar siempre en la rama correspondiente.
* Revisar cuidadosamente los Pull Requests.
* Aplicar cambios entre ramas solo cuando sea necesario mediante cherry-pick o fusiones controladas.

### Ejercicio 20. Proyecto colaborativo completo
**1. ¿Cómo organizarías las ramas del proyecto?**
* main: versión estable.
* develop: integración.
* feature/*: nuevas funcionalidades.
* hotfix/*: corrección de errores críticos.
* release/*: preparación de nuevas versiones.

**2. ¿Qué flujo de trabajo propondrías?**
Cada estudiante desarrolla su módulo en una rama feature, realiza commits frecuentes, actualiza su rama con develop, crea un Pull Request y, tras la revisión y las pruebas, se fusionan los cambios.

**3. ¿Qué funcionalidades de GitHub utilizarías durante todo el desarrollo?**
* Repositorios.
* Branches.
* Commits.
* Pull Requests.
* Code Review.
* Issues.
* Projects.
* Labels.
* GitHub Actions.
* Releases.
* Wiki o README.

**4. ¿Cómo garantizarías la calidad del código antes de integrar los cambios?**
* Revisiones de código.
* Pruebas automáticas con GitHub Actions.
* Protección de ramas.
* Pull Requests obligatorios.
* Commits claros y descriptivos.

**5. ¿Qué buenas prácticas recomendarías al equipo?**
* Realizar commits pequeños y con mensajes descriptivos.
* Trabajar siempre en ramas independientes.
* Mantener el repositorio actualizado con frecuencia.
* Documentar el proyecto en el README.
* Resolver conflictos oportunamente.
* Revisar el código antes de hacer merge.
* Utilizar Issues y Projects para organizar el trabajo.
* Mantener una comunicación constante entre los integrantes.
