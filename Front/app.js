const API = "http://localhost:8080";

let token = localStorage.getItem("token");

if (token) {
    document.getElementById("loginStatus").innerText = "Sesión activa";
}

function mostrar(data, id) {

    document.getElementById(id).textContent =
        JSON.stringify(data, null, 2);

}

async function request(url, method = "GET", body = null, idResultado) {

    const options = {

        method: method,

        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        }

    };

    if (body) {
        options.body = JSON.stringify(body);
    }

    const response = await fetch(API + url, options);

    const data = await response.json();

    mostrar(data, idResultado);

}


async function login() {

    const usuario = document.getElementById("usuario").value;
    const password = document.getElementById("password").value;

    const response = await fetch(API + "/auth/login", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({
            usuario: usuario,
            contrasena: password
        })

    });

    const data = await response.json();

    if (response.ok) {

        token = data.token;

        localStorage.setItem("token", token);

        document.getElementById("loginStatus").innerText = "✔ Sesión iniciada";

    } else {

        document.getElementById("loginStatus").innerText = "❌ Error de login";

    }

}


function listarProductos() {
    request("/api/productos", "GET", null, "resProductos");
}

function buscarProducto() {
    const id = document.getElementById("buscarProductoId").value;
    request("/api/productos/" + id, "GET", null, "resProductos");
}

function eliminarProducto() {
    const id = document.getElementById("delProductoId").value;
    request("/api/productos/" + id, "DELETE", null, "resProductos");
}

function crearProducto() {

    request("/api/productos", "POST", {

        nombre: prodNombre.value,
        categoria: prodCategoria.value,
        tamano: prodTamano.value,
        precioMensual: parseFloat(prodPrecio.value)

    }, "resProductos");

}

function actualizarProducto() {

    request("/api/productos/" + prodId.value, "PUT", {

        nombre: prodNombre.value,
        categoria: prodCategoria.value,
        tamano: prodTamano.value,
        precioMensual: parseFloat(prodPrecio.value)

    }, "resProductos");

}


function listarBodegas() {
    request("/api/bodegas", "GET", null, "resBodegas");
}

function buscarBodega() {
    request("/api/bodegas/" + buscarBodegaId.value, "GET", null, "resBodegas");
}

function eliminarBodega() {
    request("/api/bodegas/" + delBodegaId.value, "DELETE", null, "resBodegas");
}

function crearBodega() {

    request("/api/bodegas", "POST", {

        nombre: bodNombre.value,
        ubicacion: bodUbicacion.value,
        capacidad: parseInt(bodCapacidad.value),
        idEncargado: parseInt(bodEncargado.value)

    }, "resBodegas");

}

function actualizarBodega() {

    request("/api/bodegas/" + bodId.value, "PUT", {

        nombre: bodNombre.value,
        ubicacion: bodUbicacion.value,
        capacidad: parseInt(bodCapacidad.value),
        idEncargado: parseInt(bodEncargado.value)

    }, "resBodegas");

}

function listarEmpleados() {
    request("/api/empleados", "GET", null, "resEmpleados");
}

function buscarEmpleado() {
    request("/api/empleados/" + buscarEmpleadoId.value, "GET", null, "resEmpleados");
}

function eliminarEmpleado() {
    request("/api/empleados/" + delEmpleadoId.value, "DELETE", null, "resEmpleados");
}

function crearEmpleado() {

    request("/api/empleados", "POST", {

        nombre: empNombre.value,
        cargo: empCargo.value

    }, "resEmpleados");

}

function listarInventarios() {
    request("/api/inventarios", "GET", null, "resInventarios");
}

function buscarInventario() {
    request("/api/inventarios/" + buscarInventarioId.value, "GET", null, "resInventarios");
}

function eliminarInventario() {
    request("/api/inventarios/" + delInventarioId.value, "DELETE", null, "resInventarios");
}

function crearInventario() {

    request("/api/inventarios", "POST", {

        idBodega: parseInt(invBodega.value),
        idProducto: parseInt(invProducto.value),
        cantidad: parseInt(invCantidad.value)

    }, "resInventarios");

}

function actualizarInventario() {

    request("/api/inventarios/" + invId.value, "PUT", {

        idBodega: parseInt(invBodega.value),
        idProducto: parseInt(invProducto.value),
        cantidad: parseInt(invCantidad.value)

    }, "resInventarios");

}


function listarMovimientos() {
    request("/api/movimientos", "GET", null, "resMovimientos");
}

function buscarMovimiento() {
    request("/api/movimientos/" + buscarMovId.value, "GET", null, "resMovimientos");
}

function eliminarMovimiento() {
    request("/api/movimientos/" + delMovId.value, "DELETE", null, "resMovimientos");
}

function crearMovimiento() {

    request("/api/movimientos", "POST", {

        tipoMovimiento: movTipo.value,
        idEmpleado: parseInt(movEmpleado.value),
        idBodegaOrigen: parseInt(movOrigen.value),
        idBodegaDestino: parseInt(movDestino.value)

    }, "resMovimientos");

}

function actualizarMovimiento() {

    request("/api/movimientos/" + movId.value, "PUT", {

        tipoMovimiento: movTipo.value,
        idEmpleado: parseInt(movEmpleado.value),
        idBodegaOrigen: parseInt(movOrigen.value),
        idBodegaDestino: parseInt(movDestino.value)

    }, "resMovimientos");

}

function buscarDetalle() {
    request("/api/movimientos-detalle/" + buscarDetId.value, "GET", null, "resDetalle");
}

function eliminarDetalle() {
    request("/api/movimientos-detalle/" + delDetId.value, "DELETE", null, "resDetalle");
}

function crearDetalle() {

    request("/api/movimientos-detalle", "POST", {

        idMovimiento: parseInt(detMovimiento.value),
        idProducto: parseInt(detProducto.value),
        cantidad: parseInt(detCantidad.value)

    }, "resDetalle");

}