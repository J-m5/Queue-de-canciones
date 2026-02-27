# 🎵 Spotify Simulation usando Cola (FIFO) creada desde cero (Java + Maven)

Este repositorio contiene una simulación tipo Spotify que reproduce canciones **segundo a segundo** usando una **Cola (FIFO)** implementada manualmente (sin `Queue`, `LinkedList`, `ArrayDeque` ni estructuras equivalentes del JDK).

## 📁 Estructura obligatoria del repositorio

```
/umg.edu.gt.data-structure.queue
/queueHandler
/README.md
/evidencias
```

---

# ✅ Requisitos

- Java 8 o superior
- Maven

---

# 🧱 Parte A — Librería: Cola propia (FIFO)

**Proyecto:** `umg.edu.gt.data-structure.queue`

## Diseño

La cola se implementa con **nodos enlazados**:

- `Queue<T>` genérica
- `Node<T>` **privada** (no se expone)
- Referencias privadas `head` y `tail`
- Variable interna `size`

### Complejidad

- `enqueue(T item)` → **O(1)** (se inserta al final usando `tail`)
- `dequeue()` → **O(1)** (se remueve desde `head`)

### Manejo de cola vacía

- Si se intenta `dequeue()` o `peek()` con la cola vacía, se lanza una excepción propia: `QueueEmptyException`.

## Compilar e instalar la librería en local

Desde la carpeta del proyecto:

```bash
cd umg.edu.gt.data-structure.queue
mvn clean install
```

📌 **Evidencia requerida:** captura del `mvn clean install` guardada en `evidencias/`.

---

# 🎧 Parte B — Simulación de reproducción

**Proyecto:** `queueHandler`

Este proyecto **consume la librería** instalada localmente.

## Modelo obligatorio: `Song`

Cada canción incluye:

- `title`
- `artist`
- `duration` (entre **5 y 30** segundos)
- `priority` (1 = alta, 2 = normal)

## Simulación realista (segundo a segundo)

Durante la reproducción:

- Se imprime:
  - `[LOG] Starting playlist...`
  - `[LOG] Now playing: ... (12s)`
  - Progreso **cada segundo** usando `Thread.sleep(1000);`
  - `[LOG] Finished: ...`
  - `[LOG] Playlist finished.`

Además se muestra una **barra de progreso** tipo:

```
[#####-----] 5s / 10s
```

---

# 🔥 Parte C — Sistema de prioridad

Para soportar prioridad sin romper FIFO interno:

- Se usan **dos colas internas**:
  - `highPriority` → prioridad 1
  - `normalPriority` → prioridad 2

Reglas:

- Primero se reproducen todas las canciones de prioridad **1** (en FIFO)
- Luego las de prioridad **2** (en FIFO)

---

# 🚀 Parte D — Extensiones implementadas (>= 2)

En este proyecto se implementaron **3** mejoras:

1) **Historial de canciones reproducidas** (estructura propia `SongStack` con nodos enlazados).

2) **Contador total** de canciones reproducidas y **tiempo total acumulado** (segundos).

3) **Validación anti-duplicados**: si se intenta agregar la misma canción (mismo `title + artist`), se ignora y se registra en logs.

---

# 🧪 Cómo compilar el handler

> Importante: primero debes ejecutar `mvn clean install` en la librería.

Luego, desde `queueHandler`:

```bash
cd queueHandler
mvn clean package
```

📌 **Evidencia requerida:** captura del `mvn clean package` guardada en `evidencias/`.

---

# ▶️ Cómo ejecutar desde consola

Desde `queueHandler` (después de compilar):

```bash
java -jar target/queueHandler-1.0.0.jar
```

📌 **Evidencia requerida:** capturas mostrando:

- ejecución desde consola
- logs de reproducción segundo a segundo
- evidencia de que la prioridad funciona (primero las de prioridad 1)

---

# 🎵 Canciones usadas

- Billie Jean — Michael Jackson
- Shape of You — Ed Sheeran
- Rolling in the Deep — Adele
- Uptown Funk — Bruno Mars
- Smells Like Teen Spirit — Nirvana
- Someone Like You — Adele
- Blinding Lights — The Weeknd

---

# 📝 Notas importantes

- No se utilizan estructuras del JDK para colas.
- El repositorio debe compilar al clonarse siguiendo el orden:
  1) `umg.edu.gt.data-structure.queue` → `mvn clean install`
  2) `queueHandler` → `mvn clean package`
