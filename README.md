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

### Ejercicio 1. Creación de un repositorio
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

### Ejercicio 2. Clonación del proyecto
**1. ¿Qué significa clonar un repositorio?**
Es crear una copia completa del repositorio remoto en la computadora local para poder trabajar en él.

**2. ¿Qué comando utilizaría para clonar el proyecto?**
`git clone https://github.com/usuario/repositorio.git`

**3. ¿Qué ventajas tiene trabajar sobre una copia local?**
* Permite trabajar sin conexión.
* Se pueden realizar cambios antes de subirlos.
* Facilita realizar pruebas sin afectar el repositorio remoto.

### Ejercicio 3. Control de versiones
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

### Ejercicio 4. Commits significativos
**1. ¿Es recomendable esta práctica? ¿Por qué?**
No. Un único commit con muchos cambios dificulta identificar errores y comprender qué modificaciones se realizaron.

**2. Propón un mensaje de commit adecuado.**
"Implementa validación de usuarios y corrige errores en el formulario de inicio de sesión"

**3. ¿Qué beneficios tiene realizar commits pequeños y descriptivos?**
* Facilitan la revisión del código.
* Simplifican la búsqueda de errores.
* Mejoran la colaboración.
* Permiten revertir cambios específicos.

### Ejercicio 5. Trabajo con ramas
**1. ¿Qué estrategia recomendarías?**
Crear una rama independiente para cada nueva funcionalidad.

**2. ¿Cómo se crea una nueva rama?**
`git checkout -b nueva-funcionalidad`
